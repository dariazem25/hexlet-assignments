<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- BEGIN -->
 <!DOCTYPE html>
 <html>
     <head>
         <meta charset="UTF-8">
         <title>Users</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
             rel="stylesheet"
             integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
             crossorigin="anonymous">
     </head>
     <body>
        <table class="table table-bordered" class="w-100">
            <tr><th scope="col">id</th><td>${user.get("id")}</td></tr>
            <tr><th scope="col">fullName</th><td>${user.get("firstName")} ${user.get("lastName")}</td></tr>
            <tr><th scope="col">email</th><td>${user.get("email")}</td></tr>
        </table>
        <a href='/users/delete?id=${user.get("id")}'>Delete the current user?</a>
     </body>
 </html>
<!-- END -->
