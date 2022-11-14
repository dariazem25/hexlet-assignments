package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        var param = request.getParameter("search");
        PrintWriter out = response.getWriter();
        if (request.getQueryString() == null || param == null || param.isEmpty()) {
            getCompanies().forEach(out::println);
        } else {
            List<String> definedCompanies = getCompanies().stream()
                    .filter(c -> c.contains(param))
                    .collect(Collectors.toList());

            if (definedCompanies.isEmpty()) {
                out.println("Companies not found");
            } else {
                definedCompanies.forEach(out::println);
            }
        }
        out.close();
        // END
    }
}
