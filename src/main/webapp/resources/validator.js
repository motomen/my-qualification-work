
function validatePass(){
    var old =  document.forms["form"]["pwd1"].value;
    var newP =  document.forms["form"]["pwd"].value;
    var curr_pass = document.forms["form"]["user_pass"].value;

    if (old == null || old =="")return false;
    if (newP == null || newP == "") return false;

    if (curr_pass.isEqual(MD5(old))){
        alert('Старый пароль задан не верно');
        return false;
    }
    return false;
}