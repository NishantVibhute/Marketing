<%-- 
    Document   : index
    Created on : Jul 17, 2017, 6:30:27 PM
    Author     : nishant.vibhute
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="s" uri="/struts-tags"%>
        <link rel="stylesheet" href="css/login.css" type="text/css" />
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>


        <div class="main">


            <div class="container">
                <center>
                    <div class="middle">
                        <div id="login">
                            <s:actionerror cssStyle="color:white"/>
                            <s:actionmessage cssStyle="color:white"/>
                            </br>
                            <form action="login">

                                <fieldset class="clearfix">

                                    <p ><span class="fa fa-user"></span><input type="text" name="userName"  Placholder="Username" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
                                    <p><span class="fa fa-lock"></span><input type="password" name="password" Placeholder="Password" required></p> <!-- JS because of IE support; better: placeholder="Password" -->

                                    <div>
                                        <span style="width:48%; text-align:left;  display: inline-block;"><a class="small-text" href="#">Forgot
                                                password?</a></span>
                                        <span style="width:50%; text-align:right;  display: inline-block;"><input type="submit" value="Sign In"></span>
                                    </div>

                                </fieldset>
                                <div class="clearfix"></div>
                            </form>

                            <div class="clearfix"></div>

                        </div> <!-- end login -->
                        <div class="logo">LOGO

                            <div class="clearfix"></div>
                        </div>

                    </div>
                </center>
            </div>

        </div>
    </body>
</html>








