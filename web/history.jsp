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
        <title>History Page</title>
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
                width: 700px;
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

        </style>
    </head>
    <body class="display">
        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
        <c:set var="pos" value="${sessionScope.RESULTPOS}"/>
        <c:set var="result" value="${sessionScope.RESULT}"/>
        <div class="display-0">
            <header class="display-1">
                <nav class="display-2">
                    <ul class="display-3">
                        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
                        <c:if test="${not empty fullname}">
                            <li class="display-4">Welcome, ${fullname} - Student</li>
                            </c:if>
                        <br>
                        <br>
                        <a class="display-4" href="studentPage">Back to Dashboard</a>
                    </ul>
                    <ul class="display-7">
                        <form action="history">
                            <div class="header_search">
                                <input type="text" class="header_search_input" name="txtSeachValue" value="${sessionScope.SEARCHHISTORY}">
                                
                                <input type="submit" value="Search" name="btAction" class="header_search_icon"/>
                            </div>
                        </form>
                    </ul>
                    <ul class="display-8">
                        <a class="display-4" href="logout">Logout</a>
                    </ul>
                </nav>
            </header>
        </div>
        <div class="display-5">
            <div class="display-6">
                History overview
            </div>
            <c:if test="${not empty result}">
                <table border="1" class="table">
                    <thead>
                        <tr>
                            <th>No. </th>
                            <th>User ID</th>
                            <th>Subject ID</th>
                            <th>Point</th>
                            <th>Submit date</th>
                            <th>View detail</th>
                        </tr>
                    </thead>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                        <c:set var="count" value="${dto.rowNum}"/>
                        <tbody>
                            <tr>
                                <td>${counter.count + (pos - 1)*20}.</td>
                                <td>${dto.userID}</td>
                                <td>${dto.subjectID}</td>
                                <td>${dto.point}</td>
                                <td>${dto.dateSubmit}</td>
                                <td><a href="viewDetail?resultID=${dto.resultID}">Detail</a></td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table>
                <div class="display-9">
                    <c:forEach var="paging" begin="0" end="${(count - 1) / 20}" varStatus="counter">
                        <form action="history">
                            <input type="hidden" name="txtSeachValue" value="${sessionScope.SEARCHHISTORY}">
                            <c:if test="${counter.count eq pos}">
                                <button  class="btn_paging" type="submit" value="${counter.count}" disabled="disabled">${counter.count}</button>
                            </c:if>
                            <c:if test="${counter.count ne pos}">
                                <button class="btn_paging" type="submit" value="${counter.count}">${counter.count}</button>
                            </c:if>
                            <input type="hidden" name="btnCount" value="${counter.count}" />
                        </form>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty result}">
                <h2>
                    No Record is matched !!!
                </h2>
            </c:if>
        </div>
    </body>
</html>
