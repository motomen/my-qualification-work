$(document).ready(function () {
    var lock = new Auth0Lock(
        // All these properties are set in auth0-variables.js
        AUTH0_CLIENT_ID,
        AUTH0_DOMAIN
    );

    var userProfile;

    $('.btn-login').click(function (e) {
        e.preventDefault();

        lock.show({
            socialBigButtons: true,
            usernameStyle: 'username',
            loginAfterSignup: false,
            focusInput: false,
            signupLink: 'http://localhost:8080/registration'
        }, function (err, profile, token) {
            if (err) {
                // Error callback
                console.log("There was an error");
                alert("There was an error logging in" + err);
            } else {
                // Success calback

                // Save the JWT token.
                localStorage.setItem('userToken', token);

                // Save the profile
                userProfile = profile;

                $('.login-box').hide();
                $('.logged-in-box').show();
                $('.nick').text(profile.name);
                $('.avatar').attr('src', profile.picture);
            }
        });
    });

    //      lock.logout({ ref: window.location.href });
});

$.ajaxSetup({
    'beforeSend': function (xhr) {
        if (localStorage.getItem('userToken')) {
            xhr.setRequestHeader('Authorization',
                'Bearer ' + localStorage.getItem('userToken'));
        }
    }
});

$('.btn-api').click(function (e) {

    // Just call your API here. The header will be sent
    $.ajax({
        url: "/apicheck?username=root&password=root",
        method: 'POST',
        success: function (data) {
            alert('fuck yeah!')
        }
    }).then(function (data, textStatus, jqXHR) {
        alert("The request to the secured enpoint was successfull");
    }, function () {
        alert("You need to download the server seed and start it to call this API");
    });
});
