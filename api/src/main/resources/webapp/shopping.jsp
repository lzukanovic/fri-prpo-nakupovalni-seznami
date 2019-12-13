<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>KumuluzEE JSF sample</title>
</head>
<body>
<h2>Seznam</h2>
<hr>
    <div id="inputs" style="margin-right:10px;float:left;border:1px solid gray;">
        <form action="${pageContext.request.contextPath}/servletList" method="post">
            <label for="artikel">
                <input type="text" id="artikel" name="artikel"/>
            </label>
            <input type="submit" id="submit" name="submit" value="Add"/>
        </form>
        <ul>
            <jsp:include page="${pageContext.request.contextPath}/servletArtikel"/>
        </ul>
    </div>
    <div id="priporocila" style="margin-right:10px;float:left;border:1px solid gray;">
        <h3>Danes priporočamo: </h3>
        <jsp:include page="${pageContext.request.contextPath}/servletList"/>
    </div>

</body>
</html>