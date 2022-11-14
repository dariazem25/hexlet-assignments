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
        <c:out value="Do you really want to remove"/>
        <c:out value="${user.firstName}"/>
        <c:out value="${user.lastName}"/>
        <c:out value="user?"/>
        <form action="delete?id=${user.get("id")}" method="post">
            <button type="submit" class="btn btn-danger">Remove</button>
        </form>
     </body>
 </html>
<!-- END -->