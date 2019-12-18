<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>KumuluzEE JSF sample</title>
</head>
<body>
<h2 style='font-family: "Droid Sans", Arial, sans-serif'>Seznam</h2>
<strong>Privzeto dodajamo v prvi nakupovalni seznam</strong>
<hr>
    <div id="inputs" style="padding:5px;float:left;border:1px solid gray;border-radius: 5px;">
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
    <div id="priporocila" style="margin-left:10em;padding:5px;float:left;border:1px solid gray;border-radius: 5px;">
            <b>Danes priporočamo:</b>
            <ol>
                <jsp:include page="${pageContext.request.contextPath}/servletList"/>
            </ol>
    </div>

    <!--
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        var submit = document.getElementById("submit");
        submit.addEventListener("click", function() {
            var input = document.getElementById("artikel");
            var vnos = input.value;

            $.get('servletSpell', {naziv: vnos}, function(data) {
                //console.log("Dobljeno:");
                console.log(data);
                if(!(vnos.localeCompare(data)))
                    document.getElementById("suggestion").value="Ste morda mislili "+data+"?"
            });
        });
    </script>
    -->
</body>
</html>