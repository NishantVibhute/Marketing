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
                                    <h3 class="box-title">Product Details</h3>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <table id="example2" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>No.</th>
                                                <th>Title</th>
                                                <th >Description</th>
                                                <th >Start Date</th>
                                                <th >Amount</th>
                                                <th >Member Perc</th>
                                                <th >Company Perc</th>
                                                <th >Video ID</th>
                                                <th >Active/Deactive</th>
                                                <th >Progess/Closed</th>
                                                <th >Edit</th>

                                            </tr>
                                        </thead>
                                        <tbody>

                                            <s:iterator value="schemeList" var="sb">
                                                <tr>
                                                    <td><s:property value="id" /></td>
                                                    <td><s:property value="schemeName" /></td>
                                                    <td><s:property value="schemeDescription" /></td>
                                                    <td><s:property value="startDate" /></td>
                                                    <td><s:property value="amount" /> /-</td>
                                                    <td><s:property value="memberPerc" /> %</td>
                                                    <td><s:property value="companyPerc" /> %</td>
                                                    <td><s:property value="videoId" /></td>
                                                    <s:if test="isSchemeActive == 1">
                                                        <td style="color: green">Active</td>
                                                    </s:if>
                                                    <s:else>
                                                        <td style="color: red">Deactivate</td>
                                                    </s:else>

                                                    <s:if test="isClosed == 1">
                                                        <td style="color: red">Closed</td>
                                                    </s:if>
                                                    <s:else>
                                                        <td style="color: green">In Progress</td>
                                                    </s:else>
                                                    <td><button type="button" onclick="getSchemeDetail(<s:property value="id" />)" class="btn btn-block  btn-info"  >EDIT</button></td>
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
            <div class="modal fade" id="modal-default"  >
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Product Details</h4>
                        </div>
                        <form class="form-horizontal" action="editScheme">
                            <div class="modal-body">


                                <!-- /.box-header -->
                                <!-- form start -->


                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">Name</label>

                                    <div class="col-sm-9">
                                        <input type="text" name="schemeName" class="form-control" id="schemeName" placeholder="Name">
                                        <input type="hidden" name="id" id="schemeId"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">Date</label>


                                    <div class="col-sm-9">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text" name="startDate" class="form-control pull-right" id="datepickerDate">
                                        </div>
                                        <!-- /.input group -->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">Description</label>

                                    <div class="col-sm-9">
                                        <textarea id="schemeDescription" name="schemeDescription" rows="5" cols="60"></textarea>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">Amount</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="amount"  name="amount" placeholder="0.0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">Member Perc</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="memberPerc"   name="memberPerc" placeholder="0.0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">Video Id</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="videoId"   name="videoId" placeholder="0.0">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>

                                    <div class="col-sm-9">
                                        <select id="isActive" name="isSchemeActive" class="form-control">
                                            <option value="1">Active</option>
                                            <option value="0">Deactive</option>


                                        </select>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">State</label>

                                    <div class="col-sm-9">
                                        <select  id="isClosed" name="isClosed" class="form-control">
                                            <option value="0">InProgress</option>
                                            <option value="1">Completed</option>


                                        </select>
                                    </div>

                                </div>



                                <!-- /.box-footer -->


                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                                <input type="submit" class="btn btn-primary" value="Save"/>
                            </div>
                        </form>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->

            <%@include file="/include/includefooter.jsp"%>

        </div>
        <!-- ./wrapper -->

        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- DataTables -->
        <script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
        <!-- SlimScroll -->
        <script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
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
                                                            $("#schmeLi").addClass("active");
                                                            $("#schemePoolLi").removeClass("active");
                                                            $("#schemeDetailLi").addClass("active");
                                                            $("#schmeNewLi").removeClass("active");
                                                            $("#joiningLi").removeClass("active");
                                                            $("#emailLi").removeClass("active");
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
                                                            $(function() {
                                                                $('#example2').DataTable({
                                                                    'paging': true,
                                                                    'lengthChange': false,
                                                                    'searching': false,
                                                                    'ordering': true,
                                                                    'info': true,
                                                                    'autoWidth': false
                                                                })
                                                            })

                                                        })

                                                        function getSchemeDetail(id)
                                                        {

//$.ajax({
//        url: 'getSchemeDetails'+id,
//        type: "POST",
//        dataType: 'json'
//    }).success(function(response) {
//       alert(response);
//    });

                                                            $.ajax({
                                                                type: "post",
                                                                url: "getSchemeDetails?val=" + id,
                                                                dataType: 'json',
                                                                success: function(response) {

                                                                    $('#schemeName').val(response.schemeName);
                                                                    $('#datepickerDate').val(response.startDate);
                                                                    $('#schemeDescription').val(response.schemeDescription);
                                                                    $('#amount').val(response.amount);
                                                                    $('#amount').val(response.amount);
                                                                    $('#videoId').val(response.videoId);

                                                                    $('#memberPerc').val(response.memberPerc);

                                                                    $("#isClosed").val(response.isClosed);
                                                                    $("#isActive").val(response.isSchemeActive);

                                                                    $("#schemeId").val(response.id);

                                                                    if (response.isStarted === 1)
                                                                    {
                                                                        $('#schemeName').attr('readonly', true);
                                                                        $('#datepickerDate').attr('readonly', true);
                                                                        $('#schemeDescription').attr('readonly', true);
                                                                        $('#amount').attr('readonly', true);
                                                                        $('#memberPerc').attr('readonly', true);
                                                                        $('#companyPerc').attr('readonly', true);
                                                                    }
                                                                    $('#modal-default').modal('show');
                                                                }
                                                            });
                                                        }
        </script>

    </body>
</html>
