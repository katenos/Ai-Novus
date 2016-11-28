
$(document).ready(function () {
    var gd=35;
    gd+=52;
    $('#login').button().click(function () {
        var usern = $('#username').val();
        $.trim(usern);
        $.getJSON("Authentication", {'id': usern, 'password': $('#password').val()}, function (data) {
            var select = $("#select_partsElement_more");
            var option = $("<option>", {value: data.element.id});
            option.html("(" + data.element.stringType + ") " + data.element.name + " " + data.element.serialNumber + " " + $("#select_part_amount").val() + ' шт.');
            select.append(option);
        });
    });
});

function fillPart() {
    var usern = $('#username').val();
    $.trim(usern);
    $.getJSON("Authentication", {'id': usern, 'password': $('#password').val()}, function (data) {
        var select = $("#select_partsElement_more");
        var option = $("<option>", {value: data.element.id});
        option.html("(" + data.element.stringType + ") " + data.element.name + " " + data.element.serialNumber + " " + $("#select_part_amount").val() + ' шт.');
        select.append(option);
    });
}
