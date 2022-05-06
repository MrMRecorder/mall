package com.recorder.mall.web; /**
 * @author 紫英
 * @version 1.0
 * @discription
 */

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import com.recorder.mall.entity.Member;
import com.recorder.mall.service.MemberService;
import com.recorder.mall.service.impl.MemberServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@SuppressWarnings("all")
public class MemberServlet extends BasicServlet {

    private MemberService memberService = new MemberServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Member member = memberService.login(new Member(username, password));
        if (member != null) {
            System.out.println(member + "登陆成功");
            HttpSession session = request.getSession();
            session.setAttribute("user", member);
            request.getRequestDispatcher("/views/member/login_ok.jsp").forward(request, response);
        } else {
            System.out.println("登陆失败");
            request.setAttribute("msg", "用户名或密码错误");//错误信息
            request.setAttribute("username", username);//用于回显用户名
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);

        }
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //获取验证码
        String code = request.getParameter("code");
        //获取session中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println("code=" + code + "token= " + token);
        //获取之后立即删除session中的验证码，防止重复使用
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //判断验证码
        if (token != null && token.equalsIgnoreCase(code)) {
            //判断用户是否存在
            if (!memberService.ifUsernameExists(username)) {
                //注册
                Member member = new Member(null, username, password, email);
                if (memberService.registerMember(member)) {
                    System.out.println("注册成功！");
                    //请求转发
                    request.getRequestDispatcher("/views/member/register_ok.jsp").forward(request, response);
                } else {
                    System.out.println("注册失败");
                    request.getRequestDispatcher("/views/member/register_fail.jsp").forward(request, response);
                }

            } else {
                //返回注册界面
                request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            }
        }else {
            //验证码不正确返回注册界面
            request.setAttribute("msg","验证码不正确,请重新输入");
            //显示注册界面
            request.setAttribute("active","register");
            //回显某些数据
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);

        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();//销毁当前用户的session
        resp.sendRedirect(req.getContextPath() + "/index.jsp");//重定向
    }
    //用户名是否存在
    protected void isUsernameExist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean usernameExists = memberService.ifUsernameExists(username);
        //使用json格式返回——先将要返回的数据放在map中，再将map转为json格式
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("isExits", usernameExists);
        String jsonStr = new Gson().toJson(resMap);
        //返回json格式的数据
        resp.getWriter().write(jsonStr);

    }
    //验证码是否正确

    protected void isTokenRight(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        boolean tokenRight = token.equalsIgnoreCase(code);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("isRight", tokenRight);
        String jsonStr = new Gson().toJson(resMap);
        resp.getWriter().write(jsonStr);

    }
}
