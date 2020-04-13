package servlet;

import org.eclipse.jetty.http.HttpStatus;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("delete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        try {
            if(userService.deleteUser(id)){
                resp.getWriter().println("The user was successfully deleted=)");
                resp.setStatus(HttpStatus.OK_200);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }else {
                resp.getWriter().println("Error deleting a user=(");
                resp.setStatus(HttpStatus.BAD_REQUEST_400);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
}
