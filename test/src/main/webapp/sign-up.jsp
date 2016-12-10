<%-- 
    Document   : sign-up
    Created on : 29.11.2016, 10:07:30
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <script src="jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
        <script src="jquery/jquery-ui-1.8.18.custom.min.js" type="text/javascript"></script>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/sign-up.js"></script>  
        <title>Регистрация</title>
    </head>
    <body>
        <span id="header" style="color: red; margin-top: 20px; margin-left: 20px" ></span>        
        <table style="margin-left: 20px">                
            <tr>
                <th align="left">Имя пользователя</th>
                <th><input id="username" type="text"></th>                                        
            </tr>
            <tr>
                <th align="left">Пароль</th>
                <th><input id="password" type="password"></th>                                      
            </tr>
            <tr>
                <th align="left">Повтор пароля</th>
                <th><input id="password_doubling" type="password"></th>                                      
            </tr>
            <tr>
                <th></th>
                <th><button id="sign-up" type="text" align="left">Регистрация</button></th>                                                          
            </tr>
        </table>
    </body>
</html>
