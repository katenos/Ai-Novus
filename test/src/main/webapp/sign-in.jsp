<%-- 
    Document   : sign-in
    Created on : 28.11.2016, 21:47:46
    Author     : kate_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="auto/sign-in.js"></script>
        <title>Вход</title>
    </head>
    <body>
        <span id="header" style="color: orange" >Необходимо ввести учетные данные</span>
        <div>
            <table>                
                <tr>
                    <th align="left">Имя пользователя</th>
                    <th><input id="username" type="text"></th>
                    <th>Регистрация</th>                    
                </tr>
                <tr>
                    <th align="left">Пароль</th>
                    <th><input id="password" type="text"></th>                                      
                </tr>
                <tr>
                    <th></th>
                    <th><button id="login" type="text" align="left">Войти</button></th>                                                          
                </tr>
            </table>
        </div>

    </body>
</html>
