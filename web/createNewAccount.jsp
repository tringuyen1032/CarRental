<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Create new Account</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .display{
                margin: 0px;
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

            .display-2{
                display: flex;
                justify-content: space-between;
            }

            .display-3{
                list-style: none;
                padding-left: 0%;
            }

            .display-4{
                display: inline-block;
                font-size: 25px;
                color: white;
                text-decoration: none;
                width: 200px;
            }

            .login_title{
                margin: auto;
                padding-top: 24px;
                color: white;
                font-size: 32px;
            }

            .display-4{
                display: inline-block;
                font-size: 25px;
                color: white;
                text-decoration: none;
            }

            .form{
                margin-top: 200px;
            }

            .login_form{
                font-size: 20px;
                width: 500px;
                margin: auto;
                border-top: 8px solid #fe6433;
                border-bottom: 8px solid #fe6433;
                background-color: white
            }

            .txt_user{
                width:300px;
                height:35px;
                margin-top:10px;
                margin-left: 20px;
            }

            .btn_create{
                width:250px;
                height:40px;
                margin-right:10px;
                margin-top:10px;
                background-color: #fe6433;
                color: white;
                font-size:20px;
                border: none;
                outline: none;
            }

            .btn{
                text-align: center;
            }

            .txt_msg{
                margin-left: 20px;
            }
        </style>
    </head>
    <body class="display">
        <div class="display-0">
            <header class="display-1">
                <nav class="display-2">
                    <ul class="display-3">
                        <form action="loginPage" method="POST">
                            <a class="display-4" href="loginPage">Back to login page</a>
                        </form>
                    </ul>
                    <ul>
                        <h1 class="login_title">Create new Account</h1>
                    </ul>
                    <ul><a class="display-4"></a></ul>
                </nav>
            </header>
        </div> 
        <div class="form">
            <div class="login_form">
                <form action="createRecord" method="POST">
                    <c:set var="errors" value="${sessionScope.CREATEER}"/>
                    <input type="text" class="txt_user" name="txtUsername" placeholder="Mail*" value="${sessionScope.USERNAMESIGNUP}"/>(Ex: a@gmail.com) <br/>
                    <c:if test="${not empty errors.usernameLengthErr}">
                        <font class="txt_msg" color ="red">${errors.usernameLengthErr}</font>
                    </c:if>
                    <c:if test="${not empty errors.usernameIsExisted}">
                        <font class="txt_msg" color ="red">${errors.usernameIsExisted}</font>
                    </c:if>
                    <input type="password" class="txt_user" placeholder="Password*" name="txtPassword" value="" />(6 - 20 chars) <br/>
                    <c:if test="${not empty errors.passwordLengthErr}">
                        <font class="txt_msg" color ="red">${errors.passwordLengthErr}</font>
                    </c:if>
                    <input type="password" class="txt_user" placeholder="Confirm*" name="txtConfirm" value="" /><br/>
                    <c:if test="${not empty errors.confirmationNotMatched}">
                        <font class="txt_msg" color ="red">${errors.confirmationNotMatched}</font>
                    </c:if>
                    <input type="text" class="txt_user" name="txtFullname" placeholder="Full name*" value="${sessionScope.FULLNAMESIGNUP}" />(6 - 50 chars)<br/>
                    <c:if test="${not empty errors.fullnameLengthErr}">
                        <font class="txt_msg" color ="red">${errors.fullnameLengthErr}</font>
                    </c:if>
                    <div class="btn">
                        <input type="submit" class="btn_create" value="Create new account" name="btAction" />
                    </div>
                    <br>
                </form>
            </div>
        </div>
    </body>
</html>
