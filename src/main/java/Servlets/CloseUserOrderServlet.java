package Servlets;

import Enums.UserOrderStatus;
import Services.BookOrderService;
import Services.UserOrderService;
import jakarta.servlet.annotation.WebServlet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet for closing user order.
 */
@WebServlet(name = "CloseUserOrderServlet", urlPatterns = "/close_user_order")
public class CloseUserOrderServlet extends HttpServlet {
    private final UserOrderService userOrderService = UserOrderService.getInstance();
    private final BookOrderService bookOrderService = BookOrderService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("userOrderId") != null) {
                int userOrderId = Integer.parseInt(request.getParameter("userOrderId"));
                bookOrderService.deleteById(userOrderId);
                userOrderService.setUserOrderStatus(userOrderId, UserOrderStatus.CLOSED);
            }
            response.sendRedirect("/book_order");
        } catch (SQLException | IllegalArgumentException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }
}