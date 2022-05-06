package com.recorder.mall.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 紫英
 * @version 1.0
 * @discription 基于 druid 数据库连接池的工具类
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;
    //使用threadlocal来存储一次请求中的connection，保证所有dao使用的是同一个链接
    //来保证事务的实现
    private static ThreadLocal<Connection> threadLocalConn = new ThreadLocal<>();

    //在静态代码块完成 ds 初始化
    static {

        Properties properties = new Properties();
        try {
            //web项目的路径需要改成out目录下的
            //properties.load(new FileInputStream("src\\druid.properties"));
            //使用类加载器找到路径，再使用流资源找到文件
            properties.load(JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //编写 getConnection 方法
    //public static Connection getConnection() throws SQLException {
    //    return ds.getConnection();
    //}

    /*
        改进getConnection 方法——从threadlocal中获取连接
     */
    public static Connection getConnection() {
        Connection connection = threadLocalConn.get();
        if (null == connection) {
            try {
                //判断 如果threadlocal中没有的话就从连接池中取一个放进去
                connection = ds.getConnection();
                //设置成手动提交事务
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        threadLocalConn.set(connection);
        //将threadlocal中获取的连接返回
        return connection;
    }

    //提交事务
    //事务 + 线程 + threadlocal机制 + 过滤器（filter机制） 实现
    public static void commit() {

        Connection connection = threadLocalConn.get();
        if (connection != null) {
            //判断连接存在后提交
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //完成后释放连接
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //事务提交之后将threadlocal中的connection对象移除
        threadLocalConn.remove();
    }

    //事务回滚——回滚和某个连接相关联的sql语句
    public static void rollback() {

        Connection connection = threadLocalConn.get();
        if (connection != null) {
            //保证是有效链接
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //完成后释放连接
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //事务提交之后将threadlocal中的connection对象移除
        threadLocalConn.remove();
    }




    //关闭连接,这里注意： 在数据库连接池技术中，close 不是真的断掉连接
    // 而是把使用的 Connection 对象放回连接池，因为这里的connection是上面的return ds.getConnection()得到的
    public static void close(ResultSet set, Statement statement, Connection connection) {


        try {
            //判断是否为空
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            //将编译异常转成运行异常抛出
            throw new RuntimeException(e);
        }
    }

}
