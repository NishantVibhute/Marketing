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
        <style>
            .loader {
                border: 16px solid #f3f3f3; /* Light grey */
                border-top: 16px solid #3498db; /* Blue */
                border-radius: 50%;
                width: 120px;
                height: 120px;
                animation: spin 2s linear infinite;

                top: 50%;
                left: 50%;


            }

            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }
        </style>
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
                                    <h3 class="box-title">Create User</h3>
                                </div>

                                <!-- /.box-header -->
                                <div class="box-body">
                                    <div class="panel panel-default">
                                        <form action="createuser"  method="POST" enctype="multipart/form-data">

                                            <input type="file" name="fileUpload"/>
                                            <br>
                                            <input type="submit"  class="btn-primary" name="submit" data-toggle="modal" data-target="#exampleModal"/>

                                        </form>
                                    </div>



                                    <table id="userStauts" class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th style="width: 10%">Email Id</th>
                                                <th style="width: 10%">First Name</th>
                                                <th style="width: 10%">Middle Name</th>
                                                <th style="width: 10%">Last Name</th>

                                                <th  style="width: 10%">Mobile No</th>
                                                <th  style="width: 10%">Status</th>
                                                <th >Reason</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <s:iterator value="userStatus" status="sb">
                                                <tr>

                                                    <td><s:property value="emailId" /></td>
                                                    <td><s:property value="firstName" /></td>
                                                    <td> <s:property value="middleName" /></td>
                                                    <td>  <s:property value="lastName" /></td>


                                                    <td><s:property value="mobileNo" /></td>



                                                    <s:if test="status!='Success'">

                                                        <td style="color: red"><s:property value="status" /></td>

                                                    </s:if>

                                                    <s:else >
                                                        <td style="color: green"><s:property value="status" /></td>
                                                    </s:else>
                                                    <td><s:property value="reason" /></td>


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
            <!-- Modal -->

            <div class="modal" id="exampleModal" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Please Wait</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body" id="loader">
                            <center> <div class="loader"></div></center>
                        </div>

                    </div>
                </div>
            </div>

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
        <!-- DataTables -->
        <script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>

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
                $("#emailNewLi").removeClass("active");
                $("#emailDetailLi").removeClass("active");
                $("#emailTemplateLi").removeClass("active");
                $("#smsLi").removeClass("active");
                $("#smsNewLi").removeClass("active");
                $("#smsDetailLi").removeClass("active");
                $("#smsTemplateLi").removeClass("active");
                $("#chatroomLi").removeClass("active");
                $("#userLi").addClass("active");
                $("#userNewLi").addClass("active");
                $("#userListLi").removeClass("active");
                $("#userDetailLi").removeClass("active");
                $("#visitorLi").removeClass("active");
                $("#paymentLi").removeClass("active");


            });
            $(function() {

                //Date picker

                $('#userStauts').DataTable({
                    'paging': true,
                    'lengthChange': true,
                    'searching': true,
                    'ordering': true,
                    'info': true,
                    'autoWidth': false,
                    'aaSorting': []
                })

            });

            function deactivate(id, val)
            {

                $('#userIdMod').val(id);
                $('#valMod').val(val);

                if (val === 0)
                {

                    $('#msg').text("Are you Sure you want to ACTIVATE the User");
                }
                else {
                    $('#msg').text("Are you Sure you want to DEACTIVATE the User");
                }

                $('#myModal').modal('show');
            }


        </script>
    </body>
</html>
