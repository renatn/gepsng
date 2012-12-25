<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         session="false" %>
<%@page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>GEPS New Generation</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }
    </style>

    <script src="static/js/lib/jquery-1.8.3.min.js"></script>
    <script src="static/js/lib/bootstrap.min.js"></script>
    <script src="static/js/lib/underscore-min.js"></script>
    <script src="static/js/lib/backbone-min.js"></script>
    <script>
        _.templateSettings = {
            interpolate : /\{\{(.+?)\}\}/g
        };
    </script>
    <script>
        <% if (request.isUserInRole("user")) { %>
            var AUTHENTICATED = true;
        <%} else {%>
            var AUTHENTICATED = false;
        <% } %>
    </script>
</head>
<body>

<%
    String fbURL = "http://www.facebook.com/dialog/oauth?client_id=505845966105387&redirect_uri=" + URLEncoder.encode("http://gepsapp.renatn.com:8080/geps/signin", "UTF-8") + "&scope=email";
%>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="#!/">ГЭПС</a>
            <div class="btn-group pull-right">
                <a class="btn btn-primary" href="<%= fbURL %>">Connect with facebook</a>
<%--
                <button class="btn btn-inverse dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">Выход</a></li>
                </ul>
--%>
            </div>
        </div>
    </div>
</div>

<div class="container">

    <div class="alert alert-info">
        <strong>Госпочта.</strong> В настоящий момент раздел предназначен для получения информационных сообщений от органов власти и организаций.
    </div>

    <div id="gepsapp">
    </div>

</div>

<jsp:include page="WEB-INF/templates/messages-table.jsp" />
<jsp:include page="WEB-INF/templates/message-view.jsp"/>
<jsp:include page="WEB-INF/templates/message-edit.jsp" />

<script src="static/js/model/message.js"></script>
<script src="static/js/model/user.js"></script>
<script src="static/js/collection/messages.js"></script>
<script src="static/js/collection/organizations.js"></script>
<script src="static/js/view/message_row.js"></script>
<script src="static/js/view/message_list.js"></script>
<script src="static/js/view/message_view.js"></script>
<script src="static/js/view/organizations.js"></script>
<script src="static/js/view/message_edit.js"></script>
<script src="static/js/layout_manager.js"></script>
<script src="static/js/router.js"></script>

</body>
</html>