package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<Map<String, String>> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path path = Paths.get("src/main/resources/", "users.json").toAbsolutePath().normalize();
        String users = Files.readString(path);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(users, new TypeReference<>() {
        });
        // END
    }

    private Map<String, String> getUserById(String id) throws IOException {
        // BEGIN
        return getUsers().stream()
                .filter(u -> u.get("id").equals(id))
                .findAny()
                .orElse(null);
        // END
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {

        // BEGIN
        PrintWriter out = response.getWriter();

        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Users</title>
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                    <body>
                      <table class="table table-bordered" class="w-100">
                      <tr>
                         <th scope=\"col\">id</th>
                         <th scope=\"col\">fullName</th>
                      </tr>
                """);

        for (Map<String, String> m : getUsers()) {
            body.append("<tr><td>" + m.get("id") + "</td><td>");
            body.append("<a href=\"/users/" + m.get("id") + "\">");
            body.append(m.get("firstName") + " " + m.get("lastName") + "</td></tr>");
        }
        body.append("</table></body></html>");
        response.setContentType("text/html;charset=UTF-8");
        out.println(body);
        out.close();
        // END
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        // BEGIN
        PrintWriter out = response.getWriter();
        Map<String, String> user = getUserById(id);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\"> 
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                    <body>
                      <table class="table table-bordered">
                """);

        body.append("<tr><th scope=\"col\">firstName</th>" + "<td>" + user.get("firstName") + "</td>" + "</tr>");
        body.append("<tr><th scope=\"col\">lastName</th>" + "<td>" + user.get("lastName") + "</td>" + "</tr>");
        body.append("<tr><th scope=\"col\">id</th>" + "<td>" + user.get("id") + "</td>" + "</tr>");
        body.append("<tr><th scope=\"col\">email</th>" + "<td>" + user.get("email") + "</td>" + "</tr>");
        body.append("</table></body></html>");
        response.setContentType("text/html;charset=UTF-8");
        out.println(body);
        out.close();
        // END
    }
}
