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
        <!-- bootstrap wysihtml5 - text editor -->
        <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="bower_components/select2/dist/css/select2.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        <script>

        </script>
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
                                    <h3 class="box-title">Templates</h3>
                                </div>
                                <form id="formID" action="editEmail" method="post">
                                    <!-- /.box-header -->
                                    <div class="box-body">

                                        <div class="form-group">
                                            <select onchange="handleChange(this.value)" class="validate[required] form-control " name="messageContent.subject"
                                                    style="width: 100%;">

                                                <option>Select</option>
                                                <s:iterator value="messageContentList">
                                                    <option><s:property value="subject"/></option>
                                                </s:iterator>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" id="messageSubject"  name="messageContent.emailSubject" class="validate[required] form-control" />

                                        </div>
                                        <div class="form-group">
                                            <textarea id="messageBody"  name="messageContent.body" class="validate[required] form-control" style="height: 300px">
                                            </textarea>
                                        </div>

                                    </div>

                                    <!-- /.box-body -->
                                    <div class="box-footer">
                                        <div class="pull-right">

                                            <button type="submit" class="btn btn-primary"><i class="fa fa-envelope-o"></i> Save</button>
                                        </div>

                                    </div>
                                </form>
                                <!-- /.box-footer -->
                            </div>
                            <!-- /. box -->
                        </div>

                        <!-- general form elements disabled -->

                        <!-- /.box -->

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
        <!-- Select2 -->
        <script src="bower_components/select2/dist/js/select2.full.min.js"></script>
        <!-- InputMask -->
        <script src="plugins/input-mask/jquery.inputmask.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <!-- date-range-picker -->
        <script src="bower_components/moment/min/moment.min.js"></script>
        <script src="bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
        <!-- bootstrap datepicker -->
        <script src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
        <!-- bootstrap color picker -->
        <script src="bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
        <!-- bootstrap time picker -->
        <script src="plugins/timepicker/bootstrap-timepicker.min.js"></script>
        <!-- SlimScroll -->
        <script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- iCheck 1.0.1 -->
        <script src="plugins/iCheck/icheck.min.js"></script>
        <!-- FastClick -->
        <script src="bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="dist/js/demo.js"></script>
        <!-- Page script -->
        <script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
        <link rel="stylesheet" href="css/jquery.validationEngine.css" type="text/css"/>
        <script src="js/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8">
        </script>
        <script src="js/jquery.validationEngine.js" type="text/javascript" charset="utf-8">
        </script>
        <script>
            $(document).ready(function() {

                $("#dashboardli").removeClass("active");
                $("#schmeLi").removeClass("active");
                $("#schemePoolLi").removeClass("active");
                $("#schemeDetailLi").removeClass("active");
                $("#schmeNewLi").removeClass("active");
                $("#joiningLi").removeClass("active");
                $("#emailLi").addClass("active");
                $("#emailNewLi").removeClass("active");
                $("#emailDetailLi").removeClass("active");
                $("#emailTemplateLi").addClass("active");
                $("#smsLi").removeClass("active");
                $("#smsNewLi").removeClass("active");
                $("#smsDetailLi").removeClass("active");
                $("#smsTemplateLi").removeClass("active");
                $("#chatroomLi").removeClass("active");
                $("#userLi").removeClass("active");
                $("#userNewLi").removeClass("active");
                $("#userListLi").removeClass("active");
                $("#userDetailLi").removeClass("active");
                $("#visitorLi").removeClass("active");
                $("#paymentLi").removeClass("active");
                $("#accountLi").removeClass("active");
                $("#settingsLi").removeClass("active");

            });


            $(function() {
                //Initialize Select2 Elements
                $('.select2').select2()
                //Add text editor
                $("#compose-textarea").wysihtml5();
            });

            function handleChange(value) {
                $('#compose-textarea').val("");
                $.ajax({
                    url: "showEmailTemplates?valueToSubmit=" + value,
                    type: "GET",
                    dataType: 'json',
                    success: function(response) {

                        $('#messageSubject').val(response.emailSubject);
                        $('#messageBody').val(response.body);
                    }
                });
//                                                    window.location = "sms?valueToSubmit=" + value; //or you can submit a form from here or make an ajax call
            }

            jQuery(document).ready(function() {
                // binds form submission and fields to the validation engine
                jQuery("#formID").validationEngine('attach');
            });
        </script>
    </body>
</html>
