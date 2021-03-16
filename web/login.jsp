<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <style>
            .display{
                margin: 0px;
                height: 969px;
                background-color: #F5F5F5;
            }

            .display-1{
                width: 1748px;
                max-width: 100%;
                margin: 0 auto;
                text-align: center;
            }

            .display-0{
                height: 120px;
                background-image: linear-gradient(0, #fe6433, #f53e2d);
            }

            .login_title{
                margin: auto;
                padding-top: 40px;
                color: white;
            }

            .form{
                margin-top: 200px;
            }

            .login_form{
                font-size: 30px;
                width: 500px;
                margin: auto;
                border-top: 8px solid #fe6433;
                border-bottom: 8px solid #fe6433;
                text-align:center;
                background-color: white
            }

            .txt_user{
                width:300px;
                height:35px;
                margin-top:10px;
            }

            .txt_pass{
                width:300px;
                height:35px;
                margin-top:10px;
            }

            .btn_login{
                width:200px;
                height:40px;
                margin-top:10px;
                background-color: #fe6433;
                color: white;
                font-size:20px;
                border: none;
            }

            .txt_msg{
                height:30px;
                width:70%;
                text-align: center;
                color: red;
                border: none;
                pointer-events: none;
                font-size: 15px;
            }

            .txt_header{
                margin-top: 15px;
                width:310px;
                border: none;
                pointer-events: none;
                font-size: 30px;
            }

            .txt_text{
                font-size: 20px;
                border: none;
                width:10%;
                pointer-events: none;
                color: burlywood;
                text-align: center;
            }

            #hr{
                width: 45%;
                color: burlywood;
            }

            .display-2{
                display: flex;
            }

            .g-recaptcha{
                margin-left: auto;
                margin-right: auto;
                width: 300px;
            }

            .loginFace {
                font: normal 20px Arial;
                text-decoration: none;
                margin-top:10px;
                background-color: #fe6433;
                color: white;
                padding-top: 9px;
                padding-bottom: 9px;
                padding-left: 29px;
                padding-right: 29px;
            }

        </style>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body class="display">
        <div class="display-0">
            <header class="display-1">
                <h1 class="login_title">Login Page</h1>
            </header>
        </div>
        <div class="form">
            <div class="login_form">
                <form action="login" method="POST">
                    <input type="text" class="txt_header" readonly="readonly" value="Sign In"/>
                    <br>
                    <input type="text" name="txtUser" class="txt_user" placeholder="Your mail"/>
                    <br>
                    <input type="password" name="txtPass" class="txt_pass" placeholder="Password"/>
                    <br>
                    <font class="txt_msg" color ="red">${sessionScope.MSG}</font><br>
                    <div class="g-recaptcha"
                         data-sitekey="6LfoYHkaAAAAAED76tofqjjh4zs6dnCjdEsUKXja"></div>
                    <input type="submit" value="Login" class="btn_login"/>
                    <a class="loginFace" href="https://www.facebook.com/dialog/oauth?client_id=184949363155666&redirect_uri=http://localhost:8084/CarRental/login">Login Facebook</a>
                </form>
                <div class="display-2">
                    <div id="hr">
                        <hr  width="90%" align="right" />
                    </div>
                    <input type="text" class="txt_text" readonly="readonly" value="Or"/>
                    <div id="hr">
                        <hr  width="90%" align="left" />
                    </div>
                </div>
                <form action="createAccount">
                    <input type="submit" value="Create new Account" class="btn_login" style="width: 252px; margin-bottom: 10px;"/> <br> 
                    <a class="loginFace" href="https://www.facebook.com/dialog/oauth?client_id=184949363155666&redirect_uri=http://localhost:8084/CarRental/createRecord">Create with Facebook</a>
                </form>
                <br>
            </div>
        </div>
    </body>
</html>
