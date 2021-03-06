<%--
    Document   : include
    Created on : Jan 16, 2018, 11:30:07 AM
    Author     : nishant.vibhute
--%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <style>
            #circle{width: 40px; height: 40px; border-radius: 50px; border: 2px solid orange; }
            #circle div { margin-top:  8px;  color: white; text-align:center; }

            .loader {
                border: 16px solid #f3f3f3;
                border-radius: 50%;
                border-top: 16px solid #3498db;
                width: 120px;
                height: 120px;
                -webkit-animation: spin 2s linear infinite; /* Safari */
                animation: spin 2s linear infinite;
            }

            /* Safari */
            @-webkit-keyframes spin {
                0% { -webkit-transform: rotate(0deg); }
                100% { -webkit-transform: rotate(360deg); }
            }

            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }

        </style>
        <script>
            $(document).ready(function() {
                var i = 0;
                $.ajax({
                    type: "post",
                    url: "getUnreadMessage",
                    dataType: 'json',
                    success: function(response) {
                        jQuery.each(response, function(index, value) {
                            i = i + 1;
                            var val = '<li> <a href="#"><div class="pull-left"> <div style="background:#' + value.hashValue + '" id="circle"><div>' + value.shortForm + '</div></div></div><h4>' + value.name + '<small><i class="fa fa-clock-o"></i> ' + value.time + '</small></h4><p>' + value.message + '</p></a></li>';

                            $("#unreadmessages").append(val);

                        })

                        $("#countMsghead").text(i);
                        $("#countMsg").text(i);
                    }
                });


            });
        </script>

    </head>
    <body>



        <header class="main-header">
            <!-- Logo -->
            <a href="dashboard" class="logo">
                <!-- mini logo for sidebar mini 50x50 pixels -->

                <!-- logo for regular state and mobile devices -->
                <span class="logo-lg"><b>Bussi</b>Pool</span>
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top">
                <!-- Sidebar toggle button-->
                <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>

                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <!-- Messages: style can be found in dropdown.less-->
                        <li class="dropdown messages-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-envelope-o"></i>
                                <span class="label label-success"><span id="countMsghead">0</span></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">You have <span id="countMsg">0</span> messages</li>
                                <li>
                                    <!-- inner menu: contains the actual data -->
                                    <ul class="menu" id="unreadmessages">

                                    </ul>
                                </li>
                                <li class="footer"><a href="chatroom">See All Messages</a></li>
                            </ul>
                        </li>
                        <!-- Notifications: style can be found in dropdown.less -->
                        
                        <!-- Tasks: style can be found in dropdown.less -->
                        
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="dist/img/default_user.png" class="user-image" alt="User Image">
                                <span class="hidden-xs">${session.user.firstName} ${session.user.lastName}</span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header">
                                    <img src="dist/img/default_user.png" class="img-circle" alt="User Image">

                                    <p>
                                        ${session.user.firstName} ${session.user.lastName}
                                    </p>
                                </li>
                                <!-- Menu Body -->
                                <li class="user-body">
                                    <div class="row">
                                        <div class="col-xs-4 text-center">
                                            <a href="#">Followers</a>
                                        </div>
                                        <div class="col-xs-4 text-center">
                                            <a href="#">Sales</a>
                                        </div>
                                        <div class="col-xs-4 text-center">
                                            <a href="#">Friends</a>
                                        </div>
                                    </div>
                                    <!-- /.row -->
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat">Profile</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <!-- Control Sidebar Toggle Button -->
                        <li>
                            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <aside class="main-sidebar">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="dist/img/default_user.png" class="img-circle" alt="User Image">
                    </div>
                    <div class="pull-left info">
                        <p>${session.user.firstName} ${session.user.lastName}</p>
                        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                    </div>
                </div>

                <!-- /.search form -->
                <!-- sidebar menu: : style can be found in sidebar.less -->
                <ul class="sidebar-menu" data-widget="tree">
                    <li class="header">MAIN NAVIGATION</li>
                    <li id="dashboardli">
                        <a href="dashboard">
                            <i class="fa fa-dashboard"></i> <span>Dashboard</span>

                        </a>

                    </li>

                    <li id="schmeLi" class="treeview">
                        <a href="#">
                            <i class="fa fa-files-o"></i>
                            <span>Schemes</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li id="schemePoolLi"><a href="poolScheme"><i class="fa fa-circle-o"></i> Pool</a></li>
                            <li id="schemeDetailLi"><a href="detailScheme"><i class="fa fa-circle-o"></i> List</a></li>
                            <li id="schmeNewLi"><a href="newscheme"><i class="fa fa-circle-o"></i> New</a></li>



                        </ul>
                    </li>

                    <li id="joiningLi" >
                        <a href="joining">
                            <i class="fa fa-dashboard"></i> <span>Joining </span>

                        </a>

                    </li>
                    <li id="emailLi" class="treeview">
                        <a href="#">
                            <i class="fa fa-envelope"></i> <span>Email </span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li id="emailNewLi"><a href="email"><i class="fa fa-circle-o"></i> Send</a></li>
                            <li id="emailDetailLi"><a href="showSentEmail"><i class="fa fa-circle-o"></i> List</a></li>
                            <li id="emailTemplateLi"><a href="redirectToEmailTemplates"><i class="fa fa-circle-o"></i> Templates</a></li>



                        </ul>
                    </li>


                    <li id="smsLi" class="treeview">
                        <a href="#">
                            <i class="fa fa-envelope"></i> <span>SMS </span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li id="smsNewLi"><a href="newMessage"><i class="fa fa-circle-o"></i> Send</a></li>
                            <li id="smsDetailLi"><a href="showSentSMS"><i class="fa fa-circle-o"></i> List</a></li>
                            <li id="smsTemplateLi"><a href="redirectToTemplates"><i class="fa fa-circle-o"></i> Templates</a></li>



                        </ul>
                    </li>

                    <li id="chatroomLi" >
                        <a href="chatroom">
                            <i class="fa fa-inbox"></i> <span>Chat Room </span>

                        </a>

                    </li>


                    <li id="userLi" class="treeview">
                        <a href="#">
                            <i class="fa fa-envelope"></i> <span>Users </span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li id="userNewLi"><a href="redirectcreateuser"><i class="fa fa-circle-o"></i> New</a></li>
                            <li id="userListLi"><a href="users"><i class="fa fa-circle-o"></i> List</a></li>
                            <li id="userDetailLi"><a href="redirectUserDetails"><i class="fa fa-circle-o"></i> Details</a></li>




                        </ul>
                    </li>


                    <li id="visitorLi" >
                        <a href="visitors">
                            <i class="fa fa-inbox"></i> <span>Visitors</span>

                        </a>

                    </li>
                    <li id="paymentLi" >
                        <a href="paymentRelease">
                            <i class="fa fa-credit-card"></i> <span>Payment</span>

                        </a>

                    </li>
                    <li id="accountLi" >
                        <a href="account">
                            <i class="fa fa-book"></i> <span>Account</span>

                        </a>

                    </li>
                    <li id="settingsLi" >
                        <a href="settings">
                            <i class="fa fa-circle-o"></i> <span>Settings</span>

                        </a>

                    </li>
                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
        <!-- Left side column. contains the logo and sidebar -->

        <!-- /.control-sidebar -->
    </body>
</html>
