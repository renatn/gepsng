<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         session="false" %>
<%@page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>GEPS New Generation</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="static/css/bootstrap-responsive.min.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }
    </style>

    <script src="static/js/lib/jquery-1.8.3.min.js"></script>
    <script src="static/js/lib/bootstrap.min.js"></script>

</head>
<body>
<%
    String fbURL = "http://www.facebook.com/dialog/oauth?client_id=${facebook.client.id}&redirect_uri=" + URLEncoder.encode("${facebook.site.url}", "UTF-8") + "&scope=email";
%>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="./">ГЭПС</a>
            <div class="btn-group pull-right">
                <a class="btn btn-primary" href="<%= fbURL %>">Connect with facebook</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>