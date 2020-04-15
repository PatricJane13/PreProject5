package servlet;

import model.User;
import org.eclipse.jetty.http.HttpStatus;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            long age = Long.parseLong(req.getParameter("age"));
            if (name.trim().length() != 0 && password.trim().length() != 0 && req.getParameter("age").trim().length() != 0 && userService.addUser(new User(name, password, age))) {
                resp.getWriter().println("You have successfully registered=)");
                resp.setStatus(HttpStatus.OK_200);
                req.setAttribute("users", userService.getAllUsers());
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                resp.getWriter().println("Failed to register=(");
                resp.setStatus(HttpStatus.BAD_REQUEST_400);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}