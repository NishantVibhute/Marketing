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
                        <!-- left column -->
                        <div class="col-md-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Email sent Details</h3>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <table id="example2" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th width="5%">Sr No.</th>
                                                <th width="10%">Sent To</th>
                                                <th width="10%">Template</th>
                                                <th >Message</th>
                                                <th width="10%">Sent on</th>


                                            </tr>
                                        </thead>
                                        <tbody>

                                            <s:iterator value="sentList" var="sb">
                                                <tr>
                                                    <td><s:property value="id" /></td>
                                                    <td><s:property value="to" /></td>
                                                    <s:if test="tempId == 1">
                                                        <td>Sign Up</td>
                                                    </s:if>
                                                    <s:elseif test="tempId == 2">
                                                        <td>Welcome Email</td>
                                                    </s:elseif>
                                                    <s:elseif test="tempId == 0">
                                                        <td>Web</td>
                                                    </s:elseif>

                                                    <td><s:property value="message" /> </td>
                                                    <td ><s:property value="sendDate" /> </td>

                                                </tr>
                                            </s:iterator>



                                        </tbody>

                                    </table>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->


                        </div>
                        <!-- /.col -->


                        <!--/.col (left) -->
                        <!-- right column -->
                        <div class="col-md-10">
                            <!-- Horizontal Form -->

                            <!-- /.box -->
                            <!-- general form elements disabled -->

                            <!-- /.box -->
                        </div>
                        <!--/.col (right) -->
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
        <!-- DataTables -->
        <script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
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
                $("#emailDetailLi").addClass("active");
                $("#emailTemplateLi").removeClass("active");
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

            });
            $(function() {

                //Date picker
                $('#datepickerDate').datepicker({
                    autoclose: true,
                    format: 'dd/mm/yyyy'
                });

            });

            $(function() {
                $('#example2').DataTable({
                    'paging': true,
                    'lengthChange': true,
                    'searching': true,
                    'ordering': true,
                    'info': true,
                    'autoWidth': false,
                    'aaSorting': []
                })
            })
        </script>
    </body>
</html>
