package Servlets;

import services.UserOrderService;
import entity.User;
import jakarta.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "MakeNewUserOrderServlet", urlPatterns = "/userorderfromcatalog")
public class MakeNewUserOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookId = Integer.valueOf(request.getParameter("bookId"));
            HttpSession session = request.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("currentSessionUser");
                UserOrderService userOrderService = UserOrderService.getInstance();
                userOrderService.createNewUserOrder(bookId, user.getId());
                request.removeAttribute("bookId");
                response.sendRedirect("/userprofile");
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException | IllegalArgumentException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}