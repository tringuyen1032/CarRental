<%-- 
    Document   : student
    Created on : Feb 9, 2021, 7:41:06 PM
    Author     : tring
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
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
                width: 350px;
            }

            .display-33{
                list-style: none;
                padding-right: 0%;
                width: 350px;
                text-align: right;
            }

            .display-4{
                display: inline-block;
                font-size: 25px;
                color: white;
                text-decoration: none;
            }

            .display-5{
                width: 1500px;
                margin-left: auto;
                margin-right: auto;
                border: 2px solid #fe6433;
                margin-top: 30px;
            }

            .display-6{
                height: 70px;
                background-color: #fe6433;
                font-size: 40px;
                text-align: center;
                padding-top: 10px;
                color: white;
            }

            .display-7{
                border: 2px solid #fe6433;
                width: 650px;
                margin-left: auto;
                margin-right: auto;
                font-size: 30px;
            }

            .display-8{
                margin-bottom: 20px;
            }

            .btn_doquiz{
                width:200px;
                height:40px;
                margin-top:10px;
                background-color: #fe6433;
                color: white;
                font-size:20px;
                border: none;
                outline: none;
            }

            .display-9{
                text-align: right;
            }

            .food_name{
                background-color: #fe6433;
                font-size: 30px;
            }

            .list_border{
                width: 340px;
                height: 230px;
                display: flex;
                padding-top: 25px;
                padding-left: 10px;
                padding-right: 10px;
                padding-bottom: 10px;
            }

            .display-10{
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                height: 150px;
                width: 250px;
                padding-left: 18px;
                text-align: left;
            }

            .list_foods{
                border-color: #fe6433;
                border-width: 8px;
                margin: auto;
                margin-top: 50px;
                font-size: 20px;
            }

            .btn_paging{
                background-color: #fe6433;
                border-radius: 50%;
                margin: 20px 10px 10px 0px;
            }

            .display-11{
                display: flex;
                margin: auto;
            }

            .display-12 {
                display: flex;
                flex-direction: column;
            }

            .display-13{
                width: 50%;
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

            .header_search_icon{
                width: 130px;
                background-color: #fe6433;
                margin: 2px;
                border-radius: 5%;
            } 

            .header_search_status{
                width:200px;
                text-align: center;
                line-height: 11px;
                border-left: 1px solid #ccc;
            }

            .header_search_status {
                position: relative;
                display: inline-block;
            }

        </style>
    </head>
    <body class="display">
        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
        <c:set var="role" value="${sessionScope.ROLE}"/>
        <c:set var="count" value="${sessionScope.NUM}"/>
        <c:set var="valueSearch" value="${sessionScope.SEARCHLAST}"/>
        <c:set var="category" value="${sessionScope.CATEGORYLIST}"/>
        <div class="display-0">
            <header class="display-1">
                <nav class="display-2">
                    <ul class="display-3">
                        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
                        <c:if test="${not empty fullname}">
                            <c:if test="${role eq true}">
                                <li class="display-4">Welcome, ${fullname} - Admin</li>
                                </c:if>
                                <c:if test="${role eq false}">
                                <li class="display-4">Welcome, ${fullname} - User</li>
                                </c:if>
                            </c:if>
                    </ul>
                    <ul class="display-13">
                        <form action="search">
                            <div class="header_search">
                                <input type="text" class="header_search_input" name="txtSeachValue" value="${valueSearch}">
                                <div class="header_search_status">
                                    <span class="header_search_select_label">
                                        <div class="list_select"></br>
                                            <select name="status" id="cars">
                                                <c:forEach var="list" items="${category}">
                                                    <option value="${list}">${list}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </span>
                                </div>
                                <input type="submit" value="Search" name="btAction" class="header_search_icon"/>
                            </div>
                        </form>
                    </ul>
                    <ul class="display-33">
                        <a class="display-4" href="history">History</a>
                        <br>
                        <br>
                        <a class="display-4" href="logout">Logout</a>
                    </ul>
                </nav>
            </header>
        </div>
        <div class="display-12">
            <c:if test="${not empty sessionScope.CARLIST}">
                <div class="display-8">
                    <table border="1" class="list_foods">
                        <c:forEach begin="0" end="9" varStatus="pos">
                            <tr>
                                <c:forEach var="dto" items="${sessionScope.CARLIST}" varStatus="counter" begin="${(pos.count-1)*2}" end="${(pos.count-1)*2+1}">
                                    <th>
                                        <div class="display-7">
                                            <form action="doQuiz">
                                                <input type="hidden" name="txtSubjectID" value="${dto.id}" />
                                                <nav class="food_name">
                                                    ${dto.name}
                                                </nav>
                                                <nav class="list_border">
                                                    <img class="food_logo" src="image/${dto.image}">
                                                    <nav class="display-10">
                                                        <nav>
                                                            Price: ${dto.price} <br>
                                                            Quantity: ${dto.quantity} <br>
                                                            Category: ${dto.category} <br>
                                                            Color: <select name="color" onchange="select(this)" style="font-size: 20px;">
                                                                <option style="font-size: 20px;">Color</option>
                                                                <c:forEach var="color" items="${dto.color}">
                                                                    <option value="${color}" style="background-color: ${color}; font-size: 30px;"></option>
                                                                </c:forEach>
                                                            </select><br>
                                                            <script>
                                                                function select(list) {
                                                                    if (list.value !== 'Color') {
                                                                        list.style.backgroundColor = list.value;
                                                                    } else {
                                                                        list.style.backgroundColor = 'white';
                                                                    }
                                                                }
                                                            </script>
                                                        </nav>
                                                        <nav>
                                                            <div class="display-9">
                                                                <input class="btn_doquiz" type="submit" value="Rent" />
                                                            </div>
                                                        </nav>
                                                    </nav>
                                                </nav>
                                            </form>
                                        </div>
                                    </th>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="display-11">
                    <c:forEach var="paging" begin="0" end="${(count - 1) / 20}" varStatus="counter">
                        <form action="search">
                            <input type="hidden" name="txtSeachValue" value="${valueSearch}" />
                            <c:if test="${counter.count eq pos}">
                                <button class="btn_paging" type="submit" value="${counter.count}" disabled="disabled">${counter.count}</button>
                            </c:if>
                            <c:if test="${counter.count ne pos}">
                                <button class="btn_paging" type="submit" value="${counter.count}">${counter.count}</button>
                            </c:if>
                            <input type="hidden" name="btnCount" value="${counter.count}" />
                        </form>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.CARLIST}">
                <h2>
                    No Record is matched !!!
                </h2>
            </c:if>
        </div>
        <br>
    </body>
</html>
