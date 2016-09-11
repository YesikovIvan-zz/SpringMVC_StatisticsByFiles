<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Statistics by file</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Statistics by file</span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>File contents</th>
                    <th>Number of lines</th>
                    <th>Longest word</th>
                    <th>Shortest word</th>
                    <th>Total length</th>
                    <th>Average word length</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${statisticsFiles.content}</td>
                        <td>${statisticsFiles.numberOfLines}</td>
                        <td>${statisticsFiles.longestWord}</td>
                        <td>${statisticsFiles.shortestWord}</td>
                        <td>${statisticsFiles.totalLength}</td>
                        <td>${statisticsFiles.averageWordLength}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    </div>
    <div class="well">
        Go to <a href="<c:url value='/files-list' />">Files list</a>
    </div>
</div>
</body>
</html>