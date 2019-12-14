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
        <p id="suggestion">${data}</p>
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

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        var vnos;
        var submit = document.getElementById("submit");
        submit.addEventListener("click", function() {
            var input = document.getElementById("artikel");
            vnos = input.value;

            $.getJSON('/servletSpell', {naziv: vnos}, function(data) {
                console.log("Dobljeno:");
                console.log(data);
            });

        });


    </script>
</body>
</html>