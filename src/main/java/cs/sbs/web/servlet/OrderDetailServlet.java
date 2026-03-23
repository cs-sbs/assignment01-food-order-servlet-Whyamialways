package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import cs.sbs.web.model.DataStore;
import cs.sbs.web.model.Order;

public class OrderDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String path = req.getPathInfo(); // e.g. /1001
        if (path == null || path.length() <= 1) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("Error: order id missing");
            return;
        }

        String idStr = path.substring(1);
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error: invalid order id");
            return;
        }

        Order order = DataStore.findOrder(id);
        if (order == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("Error: order not found");
            return;
        }

        resp.getWriter().println("Order Detail\n\n" + order.toString());
    }
}
