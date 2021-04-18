<%-- 
    Document   : history
    Created on : Feb 13, 2021, 10:49:03 AM
    Author     : tring
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Page</title>
        <style>
            .display{
                margin: 0px;
                background-color: #F5F5F5;
            }

            .display-1{
                width: 1748px;
                max-width: 100%;
                margin: 0 auto;
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
                width: 25%;
            }

            .display-4{
                display: inline-block;
                font-size: 25px;
                color: white;
                text-decoration: none;
            }

            .display-5{
                text-align: center;
                margin: auto;
                width: 1500px;
                margin-top: 30px;
                border: 2px solid #fe6433;
                display: flex;
                flex-direction: column;
            }

            .table{
                margin: auto;
                font-size: 20px;
                width: 100%;
                border-color: #fe6433;
                margin-bottom: 20px;
            }

            .display-6{
                height: 70px;
                background-color: #fe6433;
                font-size: 40px;
                text-align: center;
                padding-top: 10px;
                color: white;
            }

            .btn_paging{
                background-color: #fe6433;
                border-radius: 50%;
                margin: 20px 10px 10px 0px;
            }

            .header_search{
                background-color: white;
                flex: 1;
                height: 40px;
                display: flex;
                justify-content: space-between;
                border-radius: 2px;
            }

            .header_search_input{
                width: 100%;
                height: 100%;
                border: none;
                outline: none;
                font-size: 1.4rem;
                padding: 0 10px;
            }

            .header_search_select{
                width:270px;
                text-align: center;
                line-height: 11px;
                border-left: 1px solid #ccc;
            }

            .header_search_select {
                position: relative;
                display: inline-block;
            }

            .header_search_icon{
                width: 130px;
                background-color: #fe6433;
                margin: 2px;
                border-radius: 5%;
            } 

            .display-7{
                width: 50%;
            }

            .display-8{
                list-style: none;
                width: 25%;
                text-align: right;
            }

            .display-9{
                margin: auto;
                display: flex;
            }

            .dateSearch{
                font-size: 15px;
                color: white;
            }

            .rate {
                float: left;
                height: 46px;
            }
            .rate:not(:checked) > input {
                position:absolute;
                top:-9999px;
            }
            .rate:not(:checked) > label {
                float:right;
                width:1em;
                overflow:hidden;
                white-space:nowrap;
                cursor:pointer;
                font-size:30px;
                color:#ccc;
            }
            .rate:not(:checked) > label:before {
                content: '★ ';
            }
            .rate > input:checked ~ label {
                color: #ffc700;    
            }
            .rate:not(:checked) > label:hover,
            .rate:not(:checked) > label:hover ~ label {
                color: #deb217;  
            }
            .rate > input:checked + label:hover,
            .rate > input:checked + label:hover ~ label,
            .rate > input:checked ~ label:hover,
            .rate > input:checked ~ label:hover ~ label,
            .rate > label:hover ~ input:checked ~ label {
                color: #c59b08;
            }
            
            .btn_feedback{
                width:200px;
                height:40px;
                margin-top:10px;
                background-color: #fe6433;
                color: white;
                font-size:20px;
                border: none;
            }
        </style>
    </head>
    <body class="display">
        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
        <c:set var="pos" value="${sessionScope.RESULTPOS}"/>
        <c:set var="result" value="${sessionScope.RESULT}"/>
        <c:set var="role" value="${sessionScope.ROLE}"/>
        <div class="display-0">
            <header class="display-1">
                <nav class="display-2">
                    <ul class="display-3">
                        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
                        <c:if test="${role eq true}">
                            <li class="display-4">Welcome, ${fullname} - Admin</li>
                            </c:if>
                            <c:if test="${role eq false}">
                            <li class="display-4">Welcome, ${fullname} - User</li>
                            </c:if>
                        <br>
                        <br>
                        <a class="display-4" href="logout">Logout</a>
                    </ul>
                    <ul class="display-8">
                        <a class="display-4" href="studentPage">Back to Dashboard</a>
                    </ul>
                </nav>
            </header>
        </div>
        <form action="sendFeedback">
            <input type="hidden" name="txtOrderID" value="${param.orderID}" />
            <input type="hidden" name="carID" value="${param.carID}" />
            <div style="padding-top: 50px; text-align: center;">
                <textarea name="txtFeedback" rows="15" cols="100" style="font-size: 20px;" placeholder="Write your feedback..."></textarea>
            </div>
            <div style="padding-top: 50px; padding-left: 400px;">
            Rating on a	scale from 0 to 10:
            <br>
            <div class="rate">
                <input type="radio" id="star10" name="rate" value="10" />
                <label for="star10" title="text">10 stars</label>
                <input type="radio" id="star9" name="rate" value="9" />
                <label for="star9" title="text">9 stars</label>
                <input type="radio" id="star8" name="rate" value="8" />
                <label for="star8" title="text">8 stars</label>
                <input type="radio" id="star7" name="rate" value="7" />
                <label for="star7" title="text">7 stars</label>
                <input type="radio" id="star6" name="rate" value="6" />
                <label for="star6" title="text">6 star</label>
                <input type="radio" id="star5" name="rate" value="5" />
                <label for="star5" title="text">5 stars</label>
                <input type="radio" id="star4" name="rate" value="4" />
                <label for="star4" title="text">4 stars</label>
                <input type="radio" id="star3" name="rate" value="3" />
                <label for="star3" title="text">3 stars</label>
                <input type="radio" id="star2" name="rate" value="2" />
                <label for="star2" title="text">2 stars</label>
                <input type="radio" id="star1" name="rate" value="1" />
                <label for="star1" title="text">1 star</label>
            </div>
            </div>
            <div style="padding-top: 50px; padding-left: 400px;">
                <input type="submit" value="Send feedback" class="btn_feedback"/>
            </div>
        </form>
    </body>
</html>
