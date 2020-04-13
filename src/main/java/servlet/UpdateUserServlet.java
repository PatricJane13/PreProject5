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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", user);
        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String oldName = req.getParameter("oldName");
            String oldPassword = req.getParameter("oldPassword");
            Long oldAge = Long.parseLong(req.getParameter("oldAge"));
            String newName = req.getParameter("newName");
            String newPassword = req.getParameter("newPassword");
            Long newAge = Long.parseLong(req.getParameter("newAge"));
            if (newPassword.trim().length() != 0 && newName.trim().length() != 0
                    && oldPassword.trim().length() != 0
                    && oldName.trim().length() != 0
                    && userService.updateUser(oldName, oldPassword, oldAge, newName, newPassword, newAge)) {
                resp.getWriter().println("The user was successfully changed=)");
                resp.setStatus(HttpStatus.OK_200);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                resp.getWriter().println("Error changing the user=(");
                resp.setStatus(HttpStatus.BAD_REQUEST_400);
            }
        } catch (NumberFormatException e) {
            resp.getWriter().println("Error changing the user=(");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            e.printStackTrace();
        }
    }
}