package com.example.myitem.config;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //实际上发送了两次请求，第一次为 OPTIONS 请求，第二次才 GET/POST… 请求在OPTIONS请求中，不会携带请求头的参数，所以在拦截器上获取请求头为空。
        //自定义的拦截器拦截成功第一次请求不能通过，就不能获取第二次的请求了 GET/POST…第一次请求不带参数，第二次请求才带参数
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            return true;
        }
        String token = request.getHeader("token");
        try {
            new JwtUtils().verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            System.out.println("无效签名！ 错误 ->"+ e);
            return false;
        } catch (TokenExpiredException e) {
            System.out.println("token过期！ 错误  ->"+ e);
            return false;
        } catch (AlgorithmMismatchException e) {
            System.out.println("token算法不一致！ 错误 ->"+ e);
            return false;
        } catch (Exception e) {
            System.out.println("token无效！ 错误 ->"+ e);
            return false;
        }
    }
}
