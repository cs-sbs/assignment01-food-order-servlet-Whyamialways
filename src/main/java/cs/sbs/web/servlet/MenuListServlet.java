package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import cs.sbs.web.model.DataStore;
import cs.sbs.web.model.MenuItem;

public class MenuListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String name = req.getParameter("name");
        List<MenuItem> menu = DataStore.MENU;

        StringBuilder sb = new StringBuilder();
        sb.append("Menu List:\n\n");

        int idx = 1;
        for (MenuItem m : menu) {
            if (name == null || m.getName().toLowerCase().contains(name.toLowerCase())) {
                sb.append(idx++).append(". ").append(m.toString()).append("\n");
            }
        }

        if (idx == 1) {
            sb.setLength(0);
            sb.append("No menu item found");
        }

        resp.getWriter().println(sb.toString());
    }
}
