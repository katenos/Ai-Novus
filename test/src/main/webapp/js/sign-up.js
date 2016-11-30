$(document).ready(function () {
    $('#sign-up').button().click(function () {
        $.getJSON("Registration", {'username': $('#username').val(), 'password': $('#password').val(), 'password_doubling': $('#password_doubling').val()}, function (data) {
            if (data.message === "Created") {
                $.getJSON("Authentication", {'username': $('#username').val(), 'password': $('#password').val()}, function (data) {
                    if (data.success) {
                        setCookie();
                        window.location = './welcome.jsp';
                    }
                });
            }
            else{
                $('#header').html(data.message);
            }
        });
    });
});

function setCookie() { 
  var username = encodeURIComponent($('#username').val());
  var updatedCookie = "username=" + username;
  document.cookie = updatedCookie;
}


