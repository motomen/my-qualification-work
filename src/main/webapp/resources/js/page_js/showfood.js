/**
 * Created by Yaroslav on 13.03.2015.
 */
function ratingclick(rating) {
    $.ajax({
        type: 'GET',
        url: "/rating/" + rating + "/${id}",
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (value) {
            var respContent = "<h4>rating:" + value + "</h4>";
            $("#rating").html(respContent);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }

    });
}

function addcalcvalue() {
    var calcvalue = $('#calcvalue').val();
    $.ajax({
        url: "/calc/save/${food.idFood}/" + calcvalue,
        type: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (suc) {
            if (suc) {
                var resp = '<p style="color: #398439">Ви успішно зїли товар</p>';
                $("#calcinformation").html(resp);
            } else {
                var resp = '<p style="color: red">Сталася помилка</p>';
                $("#calcinformation").html(resp);
            }
        }
    });
}