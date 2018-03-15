<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <%@ taglib prefix="s" uri="/struts-tags"%>

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AdminLTE 2 | General Form Elements</title>
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
                        <div class="col-md-4">
                            <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Scheme</h3>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body" style="overflow: auto;height:250px">
                                    <table class="table table-bordered">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Scheme</th>

                                            <th style="width: 50px">Requests</th>

                                        </tr>

                                        <s:iterator value="pendingJoinRequestList"  >
                                            <tr onclick="getSchemePendinInfo(<s:property value="id" />)">
                                                <td><s:property value="id" /></td>
                                                <td><s:property value="name" /></td>
                                                <td><span class="badge bg-green"><s:property value="count" /></span></td>


                                            </tr>
                                        </s:iterator>


                                    </table>
                                </div>
                                <!-- /.box-body -->

                            </div>
                            <!-- /.box -->
                            

                        </div>
                        <!-- /.col -->
                        <div class="col-md-7">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Users</h3>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <table id="userDetail" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th style="text-align: center;width: 5%">Customer Name</th>
                                                <th>Amount</th>
                                                <th style="text-align: center;width: 15%">Joining Dates</th>


                                            </tr>


                                        </thead>
                                        <tbody>


                                        </tbody>

                                    </table>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->


                        </div>
                        <!-- /.col -->
                    </div>



                    <!--/.col (left) -->

                    <!-- /.row -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <div class="modal fade" id="modal-default">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Payment Details</h4>
                        </div>
                        <form method="post" action="savePaymentDetails">
                            <div class="modal-body">
                                <div class="row">
  <div class="col-sm-6"><div class="box-body">
                                    <table id="joinDates" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                               <th style="text-align: center;width: 15%">Joining Dates</th>
</tr>


                                        </thead>
                                        <tbody>


                                        </tbody>

                                    </table>
                                </div></div>
  <div class="col-sm-6"><div class="form-group">
                                    <label>Payment Mode</label>
                                    <input type="hidden" id="joinId" name="joiningId"/>
                                    <select id="paymode"  name="paymentModeId" class="form-control">

                                        <option value="1">by Cash</option>
                                        <option value="2">by Cheque</option>
                                        <option value="3">by Netbanking</option>
                                        

                                    </select>

                                </div>
                                <div class="form-group">
                                    <label for="amount">Amount</label>
                                    <input type="text" class="form-control" id="amount" name="amount" placeholder="Amount">
                                </div>

                                <div id="cheque" style="display:none">
                                    <div class="form-group">
                                        <label for="chequeNo">Cheque No.</label>
                                        <input type="text" class="form-control" id="amount" name="chequeNo" placeholder="Cheque No">
                                    </div>
                                    <div class="form-group">
                                        <label for="amount">Cheque Date</label>
                                        <input type="text" class="form-control" id="datepickerDate" name="chequeDate" placeholder="Cheque Date">
                                    </div>
                                    <div class="form-group">
                                        <label for="amount">Bank Name</label>
                                        <input type="text" class="form-control" id="amount" name ="bankName" placeholder="Bank Name">
                                    </div>


                                </div>

                                <div id="netBanking" style="display:none">
                                    <div class="form-group">
                                        <label for="amount">UTR No.</label>
                                        <input type="text" class="form-control" id="amount" name="UTRNo" placeholder="UTR No">
                                    </div>

                                </div>



                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </div></div>

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
                                                    $("#schmeLi").removeClass("active");
                                                    $("#schemePoolLi").removeClass("active");
                                                    $("#schemeDetailLi").removeClass("active");
                                                    $("#schmeNewLi").removeClass("active");
                                                    $("#joiningLi").addClass("active");
                                                    $("#emailLi").removeClass("active");
                                                    $("#smsLi").removeClass("active");
                                                    $("#chatroomLi").removeClass("active");

                                                });
                                                $(function() {

                                                    //Date picker
                                                    $('#datepickerDate').datepicker({
                                                        autoclose: true,
                                                        format: 'dd/mm/yyyy'
                                                    });


                                                })

                                                $(function() {
                                                    $('#userDetail').DataTable({
                                                        'paging': true,
                                                        'lengthChange': true,
                                                        'searching': true,
                                                        'ordering': true,
                                                        'info': true,
                                                        'autoWidth': false,
                                                        'aaSorting': []
                                                    })
                                                })

                                                function getSchemePendinInfo(id)
                                                {
                                                    $.ajax({
                                                        
                                                        type: "post",
                                                        url: "getPaymentRealeaseRequest?val=" + id,
                                                        dataType: 'json',
                                                        success: function(response) {

                                                            $('#userDetail').DataTable().rows()
                                                                    .remove()
                                                                    .draw();
                                                            jQuery.each(response, function(index, value) {
//    var dt = "<tr><td>"+index+"</td><td>"+value.name+"</td><td><button type='button' class='btn btn-block btn-success'>Accept</button></td><td><button type='button' class='btn btn-block  btn-danger'>Deny</button></td></tr>";
//                                                                var userId=value.userId;
                                                                var name= value.userName;
                                                                var amount =value.amount;
                                                                var dat=value.joinDates;
                                                           
                                                                $('#userDetail').dataTable().fnAddData([
                                                                    
                                                                    name,
                                                                    amount,
                                                                "<button type='button' class='btn btn-block btn-success'  onClick=showPayModal('"+dat+"',"+amount+")>View</button>"]);


                                                            });
                                                        }
                                                    });


                                                   
                                                }

                                                $('#paymode').on('change', function() {
                                                    if (this.value == "1")
                                                    {
                                                        $('#cheque').hide();
                                                        $('#netBanking').hide();

                                                    }
                                                    if (this.value == "2")
                                                    {
                                                        $('#cheque').show();
                                                        $('#netBanking').hide();
                                                    }
                                                    if (this.value == "3")
                                                    {
                                                        $('#cheque').hide();
                                                        $('#netBanking').show();

                                                    }
                                                })

                                                function showPayModal(id,amount)
                                                {
                                                    var res = id.split(",");
                                                     for (i = 0; i < res.length; i++) { 
                    $("#joinDates").append("<tr><td>"+res[i]+"</td></tr>");
                                                 
                
}
                                                    
                                                        $("#joinId").val(id);
                                                        $('#modal-default').modal('show');
                                                        
                                                   
                                                }
        </script>
    </body>
</html>
