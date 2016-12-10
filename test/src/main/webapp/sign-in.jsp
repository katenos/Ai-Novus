<%-- 
    Document   : sign-in
    Created on : 28.11.2016, 21:47:46
    Author     : kate_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
        <script src="jquery/jquery-ui-1.8.18.custom.min.js" type="text/javascript"></script>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/sign-in.js"></script>        
        <title>Вход</title>
    </head>
    <body>
        <span id="header" style="color: orange; margin-top: 20px; margin-left: 20px">Необходимо ввести учетные данные</span>
        <table style="margin-left: 20px">                
            <tr>
                <th align="left">Имя пользователя</th>
                <th><input id="username" type="text"></th>
                <th><a href="sign-up.jsp">Регистрация</a></th>                    
            </tr>
            <tr>
                <th align="left">Пароль</th>
                <th><input id="password" type="password"></th>                                      
            </tr>
            <tr>
                <th></th>
                <th><button id="login" type="text" align="left">Войти</button></th>                                                          
            </tr>
        </table>

    </body>
</html>
