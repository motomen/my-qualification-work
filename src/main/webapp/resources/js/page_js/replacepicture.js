/**
 * Created by Yaroslav on 13.03.2015.
 */
function handleFileSelect(evt) {
    function changeImage(a) {
        document.getElementById("photouser").src = a.src;
    }

    var files = evt.target.files; // FileList object
    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {

        // Only process image files.
        if (!f.type.match('image.*')) {
            continue;
        }

        var reader = new FileReader();

        // Closure to capture the file information.
        reader.onload = (function (theFile) {
            return function (e) {
                // Render thumbnail.
                var content;
                content = "<img class=\"img-thumbnail\" height=\"140\" width=\"140\" src=\"" + e.target.result + "\"/>";
                $("#imgreplace").html(content);
            };
        })(f);

        // Read in the image file as a data URL.
        reader.readAsDataURL(f);
    }
}

document.getElementById('files').addEventListener('change', handleFileSelect, false);