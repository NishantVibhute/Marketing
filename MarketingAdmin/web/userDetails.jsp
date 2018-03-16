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
                                    <h3 class="box-title">User Details</h3>
                                </div>
                                <div class="box-body">


                                    <label  class="col-sm-2  control-label">Select User</label>
                                    <div class="col-sm-7">
                                        <select id="USERMode"   class="form-control">
                                            <s:iterator value="userList" var="sb">
                                                <option value="<s:property value="id" />"><s:property value="firstName" /> <s:property value="middleName" /> <s:property value="lastName" /></option>

                                            </s:iterator>
                                        </select>
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="button" onclick="getData()" class="btn btn-info">SEARCH</button>
                                    </div>
                                </div>
                            </div>



                            <div class="box">

                                <!-- /.box-header -->
                                <div class="box-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <!-- Nav tabs --><div class="card">
                                                <ul class="nav nav-tabs" role="tablist">
                                                    <li role="presentation" class="active"><a href="#userDetails" aria-controls="userDetails" role="tab" data-toggle="tab">Profile</a></li>
                                                    <li role="presentation"><a href="#overallDetails" aria-controls="overallDetails" role="tab" data-toggle="tab">Scheme</a></li>

                                                </ul>

                                                <!-- Tab panes -->
                                                <div class="tab-content">
                                                    <!--user details div-->

                                                    <div role="tabpanel" class="tab-pane active" id="userDetails">
                                                        <div class="row col-md-8">
                                                            <form class="form-horizontal" >
                                                                <br/>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="email">Join Date :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="joinDate" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="email">Balance :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="balance" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="email">User id :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="userId" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">First Name:</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="fname" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Middle Name :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="mname" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Last Name :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="lname" >
                                                                    </div>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Email Id :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="email" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Mobile No :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="phone" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Address :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="address" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Pan Card :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="pan" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Aadhar Card :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="aadhar" >
                                                                    </div>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Bank Name :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="bankname" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Bank Branch :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="bankbranch" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Account No :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="acc" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-sm-2" for="pwd">Ifsc Code :</label>
                                                                    <div class="col-sm-10">
                                                                        <input type="text" class="form-control" id="ifsc" >
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <div class="col-sm-offset-2 col-sm-10">
                                                                        <button type="submit" class="btn btn-default">Submit</button>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <!--user details div-->
                                                    <div role="tabpanel" class="tab-pane" id="overallDetails">

                                                        <div class="row">
                                                            <div class="col-sm-3"><table id="schemes" class="table table-bordered">
                                                                    <tr>
                                                                        <th style="width: 10px">#</th>
                                                                        <th>Scheme</th>
                                                                        <th style="width: 50px">Balance</th>
                                                                    </tr>
                                                                </table></div>
                                                            <div class="col-sm-9">
                                                                <div class="card">
                                                                    <ul class="nav nav-tabs" role="tablist">
                                                                        <li role="presentation" class="active"><a href="#joiningDetails" aria-controls="userDetails" role="tab" data-toggle="tab">Joining</a></li>
                                                                        <li role="presentation"><a href="#poolDetails" aria-controls="poolDetails" role="tab" data-toggle="tab">Messages</a></li>
                                                                        <li role="presentation"><a href="#paymentDetails" aria-controls="paymentDetails" role="tab" data-toggle="tab">Settings</a></li>
                                                                    </ul>

                                                                    <!-- Tab panes -->
                                                                    <div class="tab-content">
                                                                        <div role="tabpanel" class="tab-pane active" id="joiningDetails">

                                                                            <table id="joiningDet" class="table table-bordered">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th style="width: 10px">Join Request Date</th>
                                                                                        <th style="width: 10px">Join Date</th>
                                                                                        <th>Requested Payment By</th>
                                                                                        <th>Payment By</th>
                                                                                        <th>Amount</th>
                                                                                        <th>Payment Status</th>
                                                                                        <th>Pool Completed</th>
                                                                                        <th>Payment Release</th>
                                                                                        <th>Release Id</th>
                                                                                       
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>


                                                                                </tbody>
                                                                            </table>

                                                                        </div>
                                                                        <div role="tabpanel" class="tab-pane" id="paymentDetails">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passage..</div>

                                                                    </div></div>




                                                            </div>

                                                        </div>


                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

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
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Alert</h4>
                        </div><form method="post" action="updatevisitorstatus">
                            <div class="modal-body">
                                <input type="hidden" id="userIdMod" name="id"/>
                                <input type="hidden" id="valMod" name="isBlocked"/>
                                <p><span id="msg"></span></p>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-default" value="YES">
                                <button type="button" class="btn btn-default" data-dismiss="modal">NO</button>
                            </div>
                        </form>
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
                                            $(function() {
                                                $('#joiningDet').DataTable({
                                                    'paging': true,
                                                    'lengthChange': true,
                                                    'searching': true,
                                                    'ordering': false,
                                                    'info': true,
                                                    'autoWidth': false,
                                                    'aaSorting': [],
                                                    'searchHighlight': true,
                                                });

                                            });

                                            function getData()
                                            {
                                                var a = $('#USERMode').val();


                                                $.ajax({
                                                    type: "post",
                                                    url: "getUserDetails?val=" + a,
                                                    dataType: 'json',
                                                    success: function(response) {
//                                                        alert(value.firstName);
                                                        var value = JSON.parse(response);

                                                        $("#joinDate").val(value.joinDate);
                                                        $("#balance").val(value.balance);
                                                        $("#userId").val(value.id);
                                                        $("#fname").val(value.firstName);
                                                        $("#mname").val(value.middleName);
                                                        $("#lname").val(value.lastName);
                                                        $("#email").val(value.emailId);
                                                        $("#phone").val(value.mobileNo);
                                                        $("#address").val(value.address);
                                                        $("#pan").val(value.panCardNo);
                                                        $("#aadhar").val(value.aadharCardNo);
                                                        $("#bankname").val(value.bankDetails.bankName);
                                                        $("#bankbranch").val(value.bankDetails.branchName);
                                                        $("#acc").val(value.bankDetails.bankAccNo);
                                                        $("#ifsc").val(value.bankDetails.ifscCode);



                                                    }
                                                });


                                                $.ajax({
                                                    type: "post",
                                                    url: "getSchemeUserBalanceDetails?val=" + a,
                                                    dataType: 'json',
                                                    success: function(response) {
                                                        $("#schemes").find("tr:gt(0)").remove();
                                                        jQuery.each(response, function(index, value) {
                                                            var row = "<tr onclick=getUserData(" + value.schemeId + ")><td>" + value.schemeId + "</td><td>" + value.schemeName + "</td><td>" + value.balance + "</td></tr>"

                                                            $("#schemes").append(row);
                                                        })



                                                    }
                                                });


                                            }

                                            function getUserData(shcemeId)
                                            {
                                                var userId = $('#USERMode').val();

                                                $.ajax({
                                                    type: "post",
                                                    url: "getUserSchemeJoiningDetails?userId=" + userId + "&schemeId=" + shcemeId,
                                                    dataType: 'json',
                                                    success: function(response) {

                                                        jQuery.each(response, function(index, value) {
                                                            var requestJoinDate = value.requestdate;
                                                            var joindate = value.joindate;

var amount = value.amount;
                                                            var actualPay = value.paymenttype;

                                                            if (value.paymenttype === 1) {
                                                                actualPay = "by Cash<br>Amount :"+value.amount;
                                                            } else if (value.paymenttype === 2) {
                                                                actualPay = "by Cheque<br>Amount :"+value.amount+"<br>Cheque Dated :"+value.cheque_date+"<br>Cheque No :"+value.chequeno+"<br>Bank Name :"+value.bank_name;
                                                            }
                                                            else if (value.paymenttype === 3) {
                                                                actualPay = "by Netbanking<br>Amount :"+value.amount+"<br>Bank Name :"+value.bank_name+"<br>UTR No :"+value.utrno;
                                                            }
                                                            else if (value.paymenttype === 4)
                                                            {
                                                                actualPay = "by Company<br>Amount :"+value.amount;
                                                            }
                                                            else
                                                            {
                                                                actualPay = "by Rejoining<br>Amount :"+value.amount;
                                                            }


                                                            var reuestPay = value.payment_modeid;

                                                            if (value.payment_modeid === 1) {
                                                                    reuestPay = "by Cash"
                                                                } else if (value.payment_modeid === 2) {
                                                                    reuestPay = "by Cheque"
                                                                }
                                                                else if (value.payment_modeid === 3) {
                                                                    reuestPay = "by Netbanking"
                                                                }
                                                                else if (value.payment_modeid === 4)
                                                                {
                                                                    reuestPay = "by Company"
                                                                }
                                                                else
                                                                {
                                                                    reuestPay = "by Rejoining"
                                                                }


                                                                var payStatus

var poolComp = value.isExit;
                                                                if(value.isExit===0)
                                                                {
                                                                    poolComp="NO";
                                                                }else{
                                                                    poolComp="YES";
                                                                }
                                                            
                                                            var payRealease = value.isPaymentRealease;
                                                                if(value.isPaymentRealease===0)
                                                                {
                                                                    payRealease="NO";
                                                                }else{
                                                                    payRealease="YES";
                                                                }


                                                            var releaseId = value.paymentid;

                                                            var status = value.userstatus;
                                                           
                                                            
                                                            $('#joiningDet').dataTable().fnAddData([
                                                                requestJoinDate,
                                                                joindate,
                                                                reuestPay,
                                                                actualPay,
                                                                amount,
                                                                status,
                                                                poolComp,
                                                                payRealease,
                                                                releaseId

                                                            ]);

                                                        })

                          

                                                    }
                                                });

                                            }
        </script>
    </body>
</html>
