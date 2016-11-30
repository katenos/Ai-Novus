
$(document).ready(function () {
    getHello();
    $('#logout').button().click(function () {
        $.getJSON("Logout", {}, function () {
            window.location = './sign-in.jsp';
        });
    });
});

function getHello() {
    $.getJSON("GetUsername", {}, function (data) {
        $("#hello").html(getTimesOfDay() + " " + data.username + "!");
    });
}

function getTimesOfDay() {
    var date = new Date();
    if (date.getHours() >= 6 && date.getHours() < 10) {
        return "Доброе утро,";
    }
    if (date.getHours() >= 10 && date.getHours() < 18) {
        return "Добрый день,";
    }
    if (date.getHours() >= 18 && date.getHours() < 22) {
        return "Добрый вечер,";
    }
    if (date.getHours() >= 22 && date.getHours() < 6) {
        return "Добрая ночь,";
    }
}
