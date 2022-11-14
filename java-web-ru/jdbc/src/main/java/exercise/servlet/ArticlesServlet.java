package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String query1 = "SELECT COUNT(*) FROM articles";
        double numberOfArticles;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            rs.next();
            numberOfArticles = rs.getInt(1);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        List<Map<String, String>> articles = new ArrayList<>();
        String param = request.getParameter("page");


        int page = 0;
        int limitPerPage = 10;
        if (param == null || param.equals("1")) {
            page = 1;
        } else if (Integer.parseInt(param) <= 0 || Integer.parseInt(param) > Math.ceil(numberOfArticles / limitPerPage))
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        else {
            page = Integer.parseInt(param);
        }
        String query2 = "SELECT id, title, body FROM articles ORDER BY id LIMIT ? OFFSET ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.setInt(1, limitPerPage);
            statement.setInt(2, page == 1 ? 0 : (page - 1) * limitPerPage + 1);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                articles.add(Map.of(
                                "id", rs.getString("id"),
                                "title", rs.getString("title"),
                                "body", rs.getString("body")
                        )
                );
            }

        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        request.setAttribute("articles", articles);
        request.setAttribute("page", page);

        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        Map<String, String> article = new HashMap<>();
        String query = "SELECT title, body FROM articles WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, getId(request));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                article.put("title", rs.getString("title"));
                article.put("body", rs.getString("body"));
            }

        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        if (article.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
