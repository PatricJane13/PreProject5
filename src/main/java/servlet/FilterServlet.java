package servlet;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class FilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();
        String role = httpSession.getAttribute("role") == null ? "" : (String) httpSession.getAttribute("role");
        String path = httpServletRequest.getServletPath();
        if (role.equals("admin") && path.contains("admin") || role.equals("user") && path.contains("user")){
            httpSession.setAttribute("access", true);
        }else {
            httpSession.setAttribute("access", false);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
