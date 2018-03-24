<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <%@ taglib prefix="s" uri="/struts-tags"%>

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>BussiPool</title>
        <link rel="shortcut icon" href="Images/BussiPoolLogo.jpg" />
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">


            <%@include file="/include/include.jsp"%>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">

                    <s:if test="successMsg!=''">
                        <div class="alert alert-success">
                            <strong><s:property value = 'successMsg'/></strong>
                        </div>
                    </s:if>

                    <s:if test="errorMsg!=''">
                        <div class="alert alert-danger">
                            <strong><s:property value = 'errorMsg'/></strong>
                        </div>
                    </s:if>

                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-9">
                            <div class="box box-primary">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Modify Messages</h3><br>
                                </div>
                                <!-- /.box-header -->
                                <!-- form start -->
                                <form id="formID" class="form-horizontal" action="editMessage">
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label>Select</label>
                                            <select class="validate[required] form-control" onchange="handleChange(this.value)" name="messageContent.subject">
                                                <option value="">Select</option>
                                                <option>Welcome MSG</option>
                                                <option>Business pool Completed Msg</option>
                                                <option>Payment Release Msg</option>
                                                <option>Business Exit Msg</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <textarea name="messageContent.body" id= "messageBody" class="validate[required] form-control" style="height: 300px; width : 650px"></textarea>
                                        </div>
                                    </div>
                                    <div class="box-footer">
                                        <div class="pull-right">

                                            <button type="submit" class="btn btn-primary">Save</button>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                    <!-- /.row -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->


            <%@include file="/include/includefooter.jsp"%>

        </div>
        <!-- ./wrapper -->

        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="dist/js/demo.js"></script>

        <!-- bootstrap datepicker -->
        <script src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
        <script>
                                                $(document).ready(function() {

                                                    $("#dashboardli").removeClass("active");
                                                    $("#schmeLi").removeClass("active");
                                                    $("#schemePoolLi").removeClass("active");
                                                    $("#schemeDetailLi").removeClass("active");
                                                    $("#schmeNewLi").removeClass("active");
                                                    $("#joiningLi").removeClass("active");
                                                    $("#emailLi").removeClass("active");
                                                    $("#smsLi").addClass("active");
                                                    $("#smsNewLi").removeClass("active");
                                                    $("#smsDetailLi").removeClass("active");
                                                    $("#smsTemplateLi").addClass("active");
                                                    $("#chatroomLi").removeClass("active");
                                                    $("#userLi").removeClass("active");
                                                    $("#userNewLi").removeClass("active");
                                                    $("#userListLi").removeClass("active");
                                                    $("#userDetailLi").removeClass("active");
                                                    $("#visitorLi").removeClass("active");
                                                    $("#paymentLi").removeClass("active");

                                                });
                                                $(function() {

                                                    //Date picker
                                                    $('#datepickerDate').datepicker({
                                                        autoclose: true,
                                                        format: 'dd/mm/yyyy'
                                                    });

                                                });
                                                function handleChange(value) {
                                                    $('#messageBody').val("");
                                                    $.ajax({
                                                        url: "showTemplates?valueToSubmit=" + value,
                                                        type: "GET",
                                                        dataType: 'json',
                                                        success: function(response) {
                                                            $('#messageBody').val(response.body);
                                                        }
                                                    });
//                                                    window.location = "sms?valueToSubmit=" + value; //or you can submit a form from here or make an ajax call
                                                }

        </script>
        <link rel="stylesheet" href="css/jquery.validationEngine.css" type="text/css"/>
        <!--<script src="bower_components/jquery/dist/jquery.min.js"></script>-->
        <!--<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>-->
        <script src="js/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8">
        </script>
        <script src="js/jquery.validationEngine.js" type="text/javascript" charset="utf-8">
        </script>
        <script>
            jQuery(document).ready(function() {
                // binds form submission and fields to the validation engine
                jQuery("#formID").validationEngine('attach');
            });
        </script>
    </body>
</html>
