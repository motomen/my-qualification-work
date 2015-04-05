<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 well">
        <form class="form-signin" id="form" action="/j_spring_security_check" method="POST">
            <label for="us_name">Логін</label>
            <input type="text" id="us_name" class="form-control" placeholder="" name="username" required
                   autofocus></br>
            <label for="us_pass">Пароль</label>
            <input type="password" id="us_pass" name="password" class="form-control" placeholder="" required>

            <div align="right" style="margin-top: 15px">
                <label for="remember">Запомнить:</label>
                <input type="checkbox" id="remember" name="_spring_security_remember_me">
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Увійти</button>
        </form>
        </br>
        <span style="color:red">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>

        <form id="fb_signin" action="<c:url value="/auth/facebook"/>" method="POST">
            <input type="hidden" name="scope" value="email, publish_actions, publish_stream, user_photos, offline_access">
            <input type="submit" name="submitBtn" value="Connect">
            <p><button type="submit"></button></p>
            <label for="postToWall"><input id="postToWall" type="checkbox" name="postToWall" /> Tell your friends about Spring Social Showcase on your Facebook wall</label>
        </form>
    </div>
</div>

