package com.recorder.mall.test;

import com.recorder.mall.utils.JDBCUtilsByDruid;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 紫英
 * @version 1.0
 * @discription 工具类测试
 */
public class JDBCUtilsByDruid_Test {

  @Test
   public void getConnection() throws SQLException {
      //测试连接方法
      Connection connection = JDBCUtilsByDruid.getConnection();
      System.out.println(connection);
      JDBCUtilsByDruid.close(null,null,connection);
  }

}
