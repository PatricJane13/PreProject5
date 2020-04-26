package servlet;

import model.User;
import org.eclipse.jetty.http.HttpStatus;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/add")
public class AddUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("access").equals(true)) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String nameHttp = (String) httpSession.getAttribute("name");
        String passwordHttp = (String) httpSession.getAttribute("password");
        if (userService.checkingUser(nameHttp, passwordHttp) && httpSession.getAttribute("access").equals(true)) {
            try {
                String name = req.getParameter("name");
                String password = req.getParameter("password");
                long age = Long.parseLong(req.getParameter("age"));
                User user = new User(name, password, age, req.getParameter("role"));
                if (name.trim().length() != 0 && password.trim().length() != 0
                        && req.getParameter("age").trim().length() != 0) {
                    userService.addUser(user);
                    resp.setStatus(HttpStatus.OK_200);
                    resp.sendRedirect("/admin/all");
                } else {
                    resp.getWriter().println("Failed to register =(");
                    resp.setStatus(HttpStatus.BAD_REQUEST_400);
                }
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}