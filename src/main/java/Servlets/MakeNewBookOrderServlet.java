package Servlets;

import Enums.BookOption;
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
 * Servlet for making new book order.
 */
@WebServlet(name = "MakeNewBookOrderServlet", urlPatterns = "/makeBookOrder")
public class MakeNewBookOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final int userOrderId = Integer.parseInt(request.getParameter("userOrderId"));
            final int bookInstanceId = Integer.parseInt(request.getParameter("bookInstanceId"));
            final BookOption bookOption = BookOption.valueOf(request.getParameter("bookOption"));
            request.removeAttribute("userOrderId");
            request.removeAttribute("bookInstanceId");
            request.removeAttribute("bookOption");

            final BookOrderService bookOrderService = BookOrderService.getInstance();
            bookOrderService.create(bookInstanceId, userOrderId, bookOption);

            final UserOrderService userOrderService = UserOrderService.getInstance();
            userOrderService.setUserOrderStatus(userOrderId, UserOrderStatus.IN_PROGRESS);

            response.sendRedirect("/librarian");
        } catch (SQLException | IllegalArgumentException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}