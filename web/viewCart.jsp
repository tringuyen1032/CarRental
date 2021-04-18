<%-- 
    Document   : viewCart
    Created on : Oct 19, 2020, 1:37:30 PM
    Author     : tring
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cart Page</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <style>
            html {
                height: 100%;
            }

            body {
                height: 100%;
                margin: 0;
            }

            .food_logo{
                width: 80px;
                height: 80px;
            }

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

            .login_title{
                margin: auto;
                padding-top: 40px;
                color: white;
            }

            .display-2{
                width: 1500px;
                margin: auto;
            }

            .bill{
                font-size: 30px;
                border-color: #fe6433;
                border-width: 8px;
                text-align: center;
            }

            .btn{
                font-size: 25px;
                background-color: #fe6433;
                color: black;
            }

            .checkbox[type=checkbox]{
                transform: scale(2.5);
            }   

            .btn_display{
                margin-top: 30px;
            }

            .display-2{
                margin-top: 50px;
                margin-bottom: 50px;
            }

            .buttons_added {
                opacity:1;
                display:inline-block;
                display:-ms-inline-flexbox;
                display:inline-flex;
                white-space:nowrap;
                vertical-align:top;
            }
            .is-form {
                overflow:hidden;
                position:relative;
                background-color:#f9f9f9;
                height:2.2rem;
                width:1.9rem;
                padding:0;
                text-shadow:1px 1px 1px #fff;
                border:1px solid #ddd;
            }
            .is-form:focus,.input-text:focus {
                outline:none;
            }
            .is-form.minus {
                border-radius:4px 0 0 4px;
            }
            .is-form.plus {
                border-radius:0 4px 4px 0;
            }
            [id^='number'] {
                background-color:#fff;
                height: 37.5px;
                width: 40px;
                text-align:center;
                font-size:24px;
                display:inline-block;
                vertical-align:top;
                margin:0;
                border-top:1px solid #000;
                border-bottom:1px solid #000;
                border-left:0;
                border-right:0;
                padding:0;
                border-radius: 0%;
            }

            #reduce {
                width: 30px;
                height: 40px;
                border-radius:4px 0 0 4px;
            }
            #increment{
                width: 30px;
                height: 40px;
                border-radius:0 4px 4px 0;
            }

            [id^='priceValue'] {
                background-color: #f9f9f9;
                border: none;
                text-align: center;
                font-size: 25px;
                padding: 0;
                margin: 0;
                width: 150px;
            }

            #priceTotal{
                background-color: #f9f9f9;
                border: none;
                text-align: center;
                font-size: 25px;
            }

            .table_info{
                border-color: #fe6433;
                border-width: 8px;
                margin: auto;
                margin-top: 50px;
                margin-bottom:30px;
                font-size: 40px;
            }

            .submit{
                width: 100%;
                text-align: center;
                padding-bottom: 50px
            }

            .display-111{
                width: 1748px;
                max-width: 100%;
                margin: 0 auto;
            }

            .display-222{
                display: flex;
                justify-content: space-between;
            }

            .display-333{
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

            .display-444{
                display: inline-block;
                font-size: 25px;
                color: white;
                text-decoration: none;
            }

        </style>
    </head>
    <body class="display">
        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
        <c:set var="role" value="${sessionScope.ROLE}"/>
        <c:if test="${not empty sessionScope.MESSAGE}">
            <script>
                alert("${sessionScope.MESSAGE}");
            </script>
        </c:if>

        <c:set var ="prices" value="${sessionScope.PRICE}" />
        <div class="display-0">
            <header class="display-111">
                <nav class="display-222">
                    <ul class="display-333">
                        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
                        <c:if test="${not empty fullname}">
                            <c:if test="${role eq true}">
                                <li class="display-444">Welcome, ${fullname} - Admin</li>
                                </c:if>
                                <c:if test="${role eq false}">
                                <li class="display-444">Welcome, ${fullname} - User</li>
                                </c:if>
                            <br>
                            <br>
                            <a class="display-444" href="logout">Logout</a>
                        </c:if>
                        <c:if test="${empty fullname}">
                            <a class="display-444" href="loginPage">Login</a>
                        </c:if>
                    </ul>
                    <ul class="display-33">
                        <a class="display-444" href="studentPage">Continue shopping</a>
                    </ul>
                </nav>
            </header>
        </div>
        <div class="display-2">
            <c:if test="${not empty sessionScope.CUSTCART}">
                <c:set var ="cart" value="${sessionScope.CUSTCART}"/>
                <c:if test="${not empty cart}">
                    <c:if test="${not empty cart.items}">
                        <table border="1" class="bill">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th style="padding-left: 150px; padding-right: 150px;">Car name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Date Book</th>
                                    <th>Date Back</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                            <form action="removeItem">
                                <c:forEach var="item" items="${cart.items}" varStatus="counter">
                                    <tr>
                                        <td>
                                            ${counter.count}  
                                            .</td>
                                        <td>
                                            ${item.key}
                                        </td>
                                        <c:set var="cout" value="${item.value}"/>
                                        <td>
                                            <input id="priceValue${counter.count}" readonly="readonly" type="text" value="${prices[counter.count - 1]*cout}" />
                                            <input id="priceValueStatic${counter.count}" type="hidden" value="${prices[counter.count - 1]*cout}" />
                                            <input id="priceStaticValue${counter.count}" type="hidden" value="${prices[counter.count - 1]*cout}" />
                                        </td>
                                        <td>
                                            <div class="buttons_added">
                                                <input type="button" id="reduce" onclick="reduceValue${counter.count}()" value="-" />
                                                <script>
                                                    function reduceValue${counter.count}()
                                                    {
                                                        var value = parseInt(document.getElementById('number${counter.count}').value);
                                                        var priceValue = parseInt(document.getElementById('priceValue${counter.count}').value);
                                                        var priceTotal = parseInt(document.getElementById('priceTotal').value);
                                                        var priceValueStatic = parseInt(document.getElementById('priceValueStatic${counter.count}').value);
                                                        if (value > 1) {
                                                            value--;
                                                            document.getElementById('number${counter.count}').value = value;
                                                            document.getElementById('number${counter.count}').setAttribute("value", value);
                                                            document.getElementById('quantity-${counter.count}').setAttribute("value", value);
                                                    <c:set var="cout" value="${cout - 1}"/>
                                                            document.getElementById('priceValue${counter.count}').value = priceValue - priceValueStatic;
                                                            document.getElementById('priceTotal').value = priceTotal - priceValueStatic;
                                                        }
                                                        document.getElementById("nameOfFood=${counter.count}").value = document.getElementById("nameOfFood-${counter.count}").value;
                                                        document.getElementById("quantity=${counter.count}").value = document.getElementById("quantity-${counter.count}").value;
                                                        document.getElementById("priceValue-${counter.count}").value = document.getElementById("priceValue${counter.count}").value;
                                                        document.getElementById("nameOfFood==${counter.count}").value = document.getElementById("nameOfFood-${counter.count}").value;
                                                        document.getElementById("quantity==${counter.count}").value = document.getElementById("quantity-${counter.count}").value;
                                                        document.getElementById("priceValue--${counter.count}").value = document.getElementById("priceValue${counter.count}").value;
                                                    }
                                                </script>
                                                <input type="text" readonly="readonly" id="number${counter.count}" value="${item.value}" min="0" name="quantity"/>
                                                <input type="hidden" name="nameOfFood-${counter.count}" value="${item.key}" id="nameOfFood-${counter.count}"/>
                                                <input type="hidden" name="quantity-${counter.count}" id="quantity-${counter.count}" value="${item.value}" />
                                                <input type="hidden" name="size" value="${cart.items.size()}" />

                                                <input type="button" id="increment" onclick="incrementValue${counter.count}()" value="+"/>
                                                <script>
                                                    function incrementValue${counter.count}()
                                                    {
                                                        var value = parseInt(document.getElementById('number${counter.count}').value);
                                                        var priceValue = parseInt(document.getElementById('priceValue${counter.count}').value);
                                                        var priceTotal = parseInt(document.getElementById('priceTotal').value);
                                                        var priceValueStatic = parseInt(document.getElementById('priceValueStatic${counter.count}').value);
                                                        value++;
                                                        document.getElementById('number${counter.count}').value = value;
                                                        document.getElementById('number${counter.count}').setAttribute("value", value);
                                                        document.getElementById('quantity-${counter.count}').setAttribute("value", value);
                                                    <c:set var="cout" value="${cout + 1}"/>
                                                        document.getElementById('priceValue${counter.count}').value = priceValueStatic + priceValue;
                                                        document.getElementById('priceTotal').value = priceTotal + priceValueStatic;
                                                        document.getElementById("nameOfFood=${counter.count}").value = document.getElementById("nameOfFood-${counter.count}").value;
                                                        document.getElementById("quantity=${counter.count}").value = document.getElementById("quantity-${counter.count}").value;
                                                        document.getElementById("priceValue-${counter.count}").value = document.getElementById("priceValue${counter.count}").value;
                                                        document.getElementById("nameOfFood==${counter.count}").value = document.getElementById("nameOfFood-${counter.count}").value;
                                                        document.getElementById("quantity==${counter.count}").value = document.getElementById("quantity-${counter.count}").value;
                                                        document.getElementById("priceValue--${counter.count}").value = document.getElementById("priceValue${counter.count}").value;
                                                    }
                                                </script>
                                            </div>
                                        </td>
                                        <td><input type="text" id="datepicker${counter.count}" name="txtDatePick"></td>
                                        <td><input type="text" id="dateback${counter.count}" name="txtDateBack"></td>
                                    <script>
                                        $(function () {
                                            $('#datepicker${counter.count}').datepicker({
                                                minDate: "+1",
                                                onSelect: function (date) {
                                                    showDate(${counter.count});
                                                },
                                            });
                                        });
                                        $(function () {
                                            $('#dateback${counter.count}').datepicker({
                                                minDate: "+1",
                                                onSelect: function (date) {
                                                    showDate(${counter.count});
                                                },
                                            });
                                        });
                                        function showDate(id) {
                                            var dateRent = document.getElementById('datepicker' + id).value;
                                            var dateBack = document.getElementById('dateback' + id).value;
                                            if (dateBack != '' && dateRent != '') {
                                                var total = (new Date(dateBack) - new Date(dateRent)) / (3600000 * 24);
                                                if (total < 1) {
                                                    alert('Date back must be more than date rent')
                                                    document.getElementById("datepicker" + id).value = '';
                                                    document.getElementById("dateback" + id).value = '';
                                                } else {
                                                    document.getElementById('priceTotal').value = parseInt(document.getElementById('priceTotal').value) - parseInt(document.getElementById('priceValue' + id).value);
                                                    document.getElementById('priceValue' + id).value = parseInt(document.getElementById('priceValue' + id).value) * parseInt(total);
                                                    document.getElementById('priceValue' + id).value = parseInt(document.getElementById('priceValue' + id).value) / (parseInt(document.getElementById('priceValueStatic' + id).value) / parseInt(document.getElementById('priceStaticValue' + id).value));
                                                    document.getElementById('priceValueStatic' + id).value = parseInt(document.getElementById('priceStaticValue' + id).value) * parseInt(total);
                                                    document.getElementById('priceTotal').value = parseInt(document.getElementById('priceTotal').value) + parseInt(document.getElementById('priceValue' + id).value);
                                                    document.getElementById("nameOfFood=${counter.count}").value = document.getElementById("nameOfFood-${counter.count}").value;
                                                    document.getElementById("quantity=${counter.count}").value = document.getElementById("quantity-${counter.count}").value;
                                                    document.getElementById("dateback-" + id).value = document.getElementById("dateback" + id).value;
                                                    document.getElementById("datepicker-" + id).value = document.getElementById("datepicker" + id).value;
                                                    document.getElementById("priceValue-" + id).value = document.getElementById("priceValue" + id).value;
                                                    document.getElementById("dateback--" + id).value = document.getElementById("dateback" + id).value;
                                                    document.getElementById("datepicker--" + id).value = document.getElementById("datepicker" + id).value;
                                                    document.getElementById("priceValue--" + id).value = document.getElementById("priceValue" + id).value;
                                                }
                                            }
                                        }
                                    </script>
                                    <td>
                                        <input class="checkbox" type="checkbox" value="${item.key}" name="chkItem" />
                                    </td>
                                    </tr>
                                </c:forEach>
                                <div class="display-12">
                                    <tr>
                                        <td colspan="6" class="price">
                                            <c:forEach var="item" items="${cart.items}" varStatus="counter">
                                                <c:set var ="price" value="${price + item.value*prices[counter.count - 1]}"/>
                                            </c:forEach>
                                            Total price: <input id="priceTotal" readonly="readonly" type="text" name="" value="${price}" />
                                        </td>
                                        <td>
                                            <input class="btn" type = "submit" value = "Remove Selected" onclick="clicked(event)">
                                            <script>
                                                function clicked(e)
                                                {
                                                    if (!confirm('Are you sure to remove all selected Food?')) {
                                                        e.preventDefault();
                                                    }
                                                }
                                            </script>
                                        </td>
                                    </tr>
                                </div>
                            </form>
                            </tbody>
                        </table>
                        <br/>
                        <div class="btn_display">
                            <form action="checkout" method="post">
                                <input class="btn" type = "submit" value = "Check out">
                                <c:forEach var="item" begin="1" end="${cart.size}" varStatus="counter">
                                    <input type="hidden" name="nameOfFood-${counter.count}" id="nameOfFood=${counter.count}"/>
                                    <input type="hidden" name="quantity=${counter.count}" id="quantity=${counter.count}"/>
                                    <input type="hidden" id="datepicker-${counter.count}" name="datepicker-${counter.count}">
                                    <input type="hidden" id="dateback-${counter.count}" name="dateback-${counter.count}">
                                    <input id="priceValue-${counter.count}" type="hidden" name="priceValue=${counter.count}"/>
                                    <script>
                                        document.getElementById("nameOfFood=${counter.count}").value = document.getElementById("nameOfFood-${counter.count}").value;
                                        document.getElementById("quantity=${counter.count}").value = document.getElementById("quantity-${counter.count}").value;
                                        document.getElementById("priceValue-${counter.count}").value = document.getElementById("priceValue${counter.count}").value;
                                    </script>
                                </c:forEach>
                            </form>
                            <br>
                            <form action="checkoutPaypal" method="post">
                                <input class="btn" type="submit" name="submit" value="Check out by Paypal">
                                <input type="text" name="txtDiscount" placeholder="Discount code" style="font-size: 20px; margin-left: 30px;"/> Discount only apply with paypal
                                <c:forEach var="item" begin="1" end="${cart.size}" varStatus="counter">
                                    <input type="hidden" name="nameOfFood-${counter.count}" id="nameOfFood==${counter.count}"/>
                                    <input type="hidden" name="quantity=${counter.count}" id="quantity==${counter.count}"/>
                                    <input type="hidden" id="datepicker--${counter.count}" name="datepicker-${counter.count}">
                                    <input type="hidden" id="dateback--${counter.count}" name="dateback-${counter.count}">
                                    <input id="priceValue--${counter.count}" type="hidden" name="priceValue=${counter.count}"/>
                                    <script>
                                        document.getElementById("nameOfFood==${counter.count}").value = document.getElementById("nameOfFood-${counter.count}").value;
                                        document.getElementById("quantity==${counter.count}").value = document.getElementById("quantity-${counter.count}").value;
                                        document.getElementById("priceValue--${counter.count}").value = document.getElementById("priceValue${counter.count}").value;
                                    </script>
                                </c:forEach>
                            </form>
                        </div>
                    </c:if>
                </c:if>
            </c:if>
            <div style="text-align: center;">
                <c:if test="${empty cart.items}">
                    <h2>
                        No cart is existed
                    </h2>
                    <a href="studentPage">Click here to shopping</a>
                </c:if>
            </div>
        </div>
    </form>
</body>
</html>
