
$(document).ready(function () {
    $("#username").val(getCookie());
    $.getJSON("GetStatusLogin", {}, function (data) {
        if (data.logout != null) {
            $("#header").html("Вы вышли из приложения");
            $("#header").css({color: "green"});
        }
    });

    $('#login').button().click(function () {
        $.getJSON("Authentication", {'username': $('#username').val(), 'password': $('#password').val()}, function (data) {
            if (data.success) {
                setCookie();
                window.location = './welcome.jsp';
            }
            else {
                $("#header").html("Имя пользователя и пароль не подходят");
                $("#header").css({color: "red"});
            }
        });
    });
});



function setCookie() {
    var username = encodeURIComponent($('#username').val());
    var updatedCookie = "username=" + username;
    document.cookie = updatedCookie;
}

function getCookie() {
    var name = "username";
    var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
            ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}
