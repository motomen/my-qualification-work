/**
 * Created by Yaroslav on 13.03.2015.
 */
function clicklink(name) {
    //disable all other links
    //  var json = { "namecategory" : name};
    $.ajax({
        type: 'GET',
        url: "/getsubcategory/" + name,
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (list) {
            var respContent = "";
            var img = "";
            jQuery.each(list, function (index, value) {
                respContent += "<a href = \"foodsubcategory/" + value.idSubCategory + "?p=1\" >";
                respContent += " <div class=\"col-lg-2 col-md-3 well colorsubcategory\"> ";
                respContent += " <h4 class=\"title\" align=\"center\">" + value.name + "</h4> ";
                img = value.img;
                respContent += " <img class=\"img-thumbnail subcategory\" height=\"100\" width=\"100\" name=\"myImg\" src=\"data:image/jpg;base64,";
                respContent += value.img + "\">";
                respContent += " </div> ";
                respContent += "</a>";
            });
            $("#placesubcategory").html(respContent);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }

    });
}
