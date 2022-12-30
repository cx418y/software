package fudan.se.lab2.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  return  true;
        //uri = /login
     /* if(request.getSession().getAttribute("username") == null){
          String path = request.getContextPath();
          System.out.println(path);
          response.sendRedirect(path + "/api/login");
          return false;
      }

      return true;*/
    }

}
