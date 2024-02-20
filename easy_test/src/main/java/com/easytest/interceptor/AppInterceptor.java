//package com.easytest.interceptor;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@Component("appInterceptor")
//public class AppInterceptor implements HandlerInterceptor {
//
//    //    public boolean preHandle(HttpServletRequest request, HttpServletResponse response , Object handler) throws Exception{
////
////    }
//    @Override//controller方法放行之前执行，返回true，放行。返回false,不放行
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        System.out.println(requestURI);
//        return false;
//    }
//
//
//    @Override//controller方法放行之后执行
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override//最后执行，finally
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}
