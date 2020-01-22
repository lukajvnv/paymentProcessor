<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!--     <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
 -->    <script src="js/jquery-3.1.1.min.js"></script>
<!--     <script type="text/javascript" th:src="@{/jquery-3.3.1.min.js}"></script>
 --></head>
<body>
    <p th:text="'Hello, ' + ${name} + '!'" />
    <form name="myRedirectForm" onSubmit="send()" method="post">
    <input name="name"  value="xyz" />
    <input name="phone"  value="9898989898" />
    
        <input type="submit" value="Click here to continue" />
    
</form>
    <script type="text/javascript">

        function send(){
        	var proba = {broj: 5, tekst: "fld"};
        	alert('lfa');
        	$.post({
        		url : 'https://localhost:8841/test/testHelloSubmitJsp',
        		contentType : 'application/json',
        		data : JSON.stringify(proba),
        		success : function(articleFilterResponse){
        			alert("uspeh");
        			console.log("uspeh");
        		},
        		error : function(a, b, c){
        			 alert("fail");
        			 console.log("uspeh");
        		}
        	});
        }

    </script>
</body>
</html>