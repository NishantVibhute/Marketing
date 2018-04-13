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
            tr:hover {
                background: #337AB7 !important;
                color: #fff;
            }
            .highlighted {
                background: #337AB7;
                color: #fff;
            }

            #data tr {
                background: white;
                color: black;
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
                                    <h3 class="box-title">User Details</h3>
                                </div>
                                <div class="box-body">


                                    <label  class="col-sm-2  control-label">Select User</label>
                                    <div class="col-sm-7">
                                        <select id="USERMode" required="required"   class="form-control">
                                            <option value="0">Select</option>
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
                                                        <br/>
                                                        <div class="col-md-4">
                                                            <!-- DONUT CHART -->
                                                            <div class="box box-danger ">
                                                                <div class="box-header  with-border">
                                                                    <h3 class="box-title">Balance</h3>

                                                                    <div class="box-tools pull-right">


                                                                    </div>
                                                                </div>
                                                                <div class="box-body chart-responsive">
                                                                    <div class="chart" id="sales-chart" style="height: 300px; position: relative;"></div>
                                                                </div>
                                                                <!-- /.box-body -->
                                                            </div>
                                                            <!-- /.box -->
                                                            <!-- /.box -->


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
                                                                        <li role="presentation"><a href="#poolDetails" aria-controls="poolDetails" role="tab" data-toggle="tab">Bussiness Pool</a></li>
                                                                        <li role="presentation"><a href="#paymentDetails" aria-controls="paymentDetails" role="tab" data-toggle="tab">Payment</a></li>
                                                                        <li role="presentation"><a href="#accountDetails" aria-controls="accountDetails" role="tab" data-toggle="tab">Account</a></li>
                                                                    </ul>

                                                                    <!-- Tab panes -->
                                                                    <div class="tab-content">
                                                                        <div role="tabpanel" class="tab-pane active" id="joiningDetails">
                                                                            <div class="panel panel-default">
                                                                                <div class="panel-body">
                                                                                    <table id="joiningDet" class="table table-bordered">
                                                                                        <thead>
                                                                                            <tr>
                                                                                                <th style="width: 15%">Request Date</th>
                                                                                                <th style="width: 15%">Join Date</th>
                                                                                                <th style="width: 10%">Requested Payment</th>
                                                                                                <th style="width: 20%">Payment</th>

                                                                                                <th  style="width: 10%">Status</th>
                                                                                                <th  style="width: 10%">Pool Completed</th>
                                                                                                <th  style="width: 10%">Payment Release</th>
                                                                                                <th  style="width: 10%">Release Id</th>

                                                                                            </tr>
                                                                                        </thead>
                                                                                        <tbody>


                                                                                        </tbody>
                                                                                    </table>
                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                        <div role="tabpanel" class="tab-pane" id="poolDetails">
                                                                            <div class="panel panel-default">
                                                                                <div class="panel-body">
                                                                                    <table id="tablePool" class="table table-bordered table-striped">
                                                                                        <thead>
                                                                                            <tr>
                                                                                                <th colspan="2" style="text-align: center;width: 25%">Parent</th>
                                                                                                <th colspan="2" style="text-align: center;width: 25%">Member 1</th>
                                                                                                <th colspan="2" style="text-align: center;width: 25%">Member 2</th>
                                                                                                <th colspan="2" style="text-align: center;width: 25%">Member 3</th>

                                                                                            </tr>
                                                                                            <tr>
                                                                                                <th >Name</th>
                                                                                                <th >Joined On</th>
                                                                                                <th >Name</th>
                                                                                                <th >Joined On</th>
                                                                                                <th >Name</th>
                                                                                                <th >Joined On</th>
                                                                                                <th >Name</th>
                                                                                                <th >Joined On</th>
                                                                                            </tr>
                                                                                        </thead>
                                                                                        <tbody>


                                                                                        </tbody>

                                                                                    </table>
                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                        <div role="tabpanel" class="tab-pane" id="paymentDetails">
                                                                            <div class="panel panel-default">
                                                                                <div class="panel-body">
                                                                                    <table id="tablePay" class="table table-bordered table-striped">
                                                                                        <thead>
                                                                                            <tr>
                                                                                                <th >Id</th>
                                                                                                <th >Payment</th>
                                                                                                <th >Date</th>

                                                                                            </tr>
                                                                                        </thead>
                                                                                        <tbody>


                                                                                        </tbody>

                                                                                    </table>

                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div role="tabpanel" class="tab-pane" id="accountDetails">
                                                                            <div class="panel panel-default">
                                                                                <div class="panel-body">
                                                                                    <table id="passbook" class="table table-bordered table-hover">
                                                                                        <thead>
                                                                                            <tr>
                                                                                                <th width="5%">Sr No.</th>
                                                                                                <th width="20%">Date</th>
                                                                                                <th>Particulars</th>
                                                                                                <th width="10%">Withdrawl</th>
                                                                                                <th width="10%">Deposit</th>
                                                                                                <th width="10%">Balance</th>

                                                                                            </tr>
                                                                                        </thead>
                                                                                        <tbody>
                                                                                        </tbody>

                                                                                    </table>

                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                    </div>



                                                                </div>
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
        <!-- DataTables -->
        <script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
        <!-- Morris.js charts -->
        <script src="bower_components/raphael/raphael.min.js"></script>
        <script src="bower_components/morris.js/morris.min.js"></script>
        <!-- FastClick -->
        <script src="bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="dist/js/demo.js"></script>
        <!-- page script -->
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
                                                $("#userNewLi").removeClass("active");
                                                $("#userListLi").removeClass("active");
                                                $("#userDetailLi").addClass("active");
                                                $("#visitorLi").removeClass("active");
                                                $("#paymentLi").removeClass("active");
                                                $("#accountLi").removeClass("active");


                                            });
                                            var donut = "";
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
                                                $('#tablePool').DataTable({
                                                    'paging': true,
                                                    'lengthChange': true,
                                                    'searching': true,
                                                    'ordering': false,
                                                    'info': true,
                                                    'autoWidth': false,
                                                    'aaSorting': [],
                                                    'searchHighlight': true,
                                                });


                                                $('#tablePay').DataTable({
                                                    'paging': true,
                                                    'lengthChange': true,
                                                    'searching': true,
                                                    'ordering': false,
                                                    'info': true,
                                                    'autoWidth': false,
                                                    'aaSorting': [],
                                                    'searchHighlight': true,
                                                });

                                                donut = new Morris.Donut({
                                                    element: 'sales-chart',
                                                    resize: true,
                                                    colors: ["#3c8dbc", "#f56954", "#00a65a", "red", "blue"],
                                                    data: [{"label": "Select User", "value": 0}],
                                                    hideHover: 'auto'
                                                });

                                            });

                                            function getData()
                                            {
                                                var a = $('#USERMode').val();

                                                if (a === '0')
                                                {
                                                    alert("Select User Name");
                                                    return;
                                                }

                                                $.ajax({
                                                    type: "post",
                                                    url: "getUserDetails?val=" + a,
                                                    dataType: 'json',
                                                    success: function(response) {
//                                                        alert(value.firstName);
                                                        var value = JSON.parse(response);

                                                        $("#joinDate").val(value.joinDate);
                                                        //                                                        $("#balance").val(value.balance);
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

                                                var data = [];
                                                $.ajax({
                                                    type: "post", url: "getSchemeUserBalanceDetails?val=" + a,
                                                    dataType: 'json',
                                                    success: function(response) {
                                                        $("#schemes").find("tr:gt(0)").remove();
                                                        jQuery.each(response, function(index, value) {
                                                            var row = "<tr id='schemetr_" + value.schemeId + "' onclick=getUserData(" + value.schemeId + ")><td>" + value.schemeId + "</td><td>" + value.schemeName + "</td><td>" + value.balance + "</td></tr>"
                                                            $("#schemes").append(row);
                                                            var b = {"label": value.schemeName, "value": value.balance};

                                                            data.push(b);
                                                        })
                                                        console.log(data);
                                                        donut.setData(data);
//                                                        alert(data);

                                                    }
                                                });

                                            }

                                            function getUserData(shcemeId)
                                            {
                                                var userId = $('#USERMode').val();

                                                $('#schemes tr').removeClass('highlighted');
                                                //                                                $(this).toggleClass('highlighted');
                                                $("#schemetr_" + shcemeId).toggleClass('highlighted');
                                                $.ajax({
                                                    type: "post",
                                                    url: "getUserSchemeJoiningDetails?userId=" + userId + "&schemeId=" + shcemeId,
                                                    dataType: 'json',
                                                    success: function(response) {
                                                        $('#joiningDet').DataTable().rows()
                                                                .remove()
                                                                .draw();

                                                        jQuery.each(response, function(index, value) {
                                                            var requestJoinDate = value.requestdate;
                                                            var joindate = value.joindate;


                                                            var actualPay = value.paymenttype;

                                                            if (value.paymenttype === 1) {
                                                                actualPay = "by Cash<br>Amount :" + value.amount;
                                                            } else if (value.paymenttype === 2) {
                                                                actualPay = "by Cheque<br>Amount :" + value.amount + "<br>Cheque Dated :" + value.cheque_date + "<br>Cheque No :" + value.chequeno + "<br>Bank Name :" + value.bank_name;
                                                            }
                                                            else if (value.paymenttype === 3) {
                                                                actualPay = "by Netbanking<br>Amount :" + value.amount + "<br>Bank Name :" + value.bank_name + "<br>UTR No :" + value.utrno;
                                                            }
                                                            else if (value.paymenttype === 4)
                                                            {
                                                                actualPay = "by Company<br>Amount :" + value.amount;
                                                            }
                                                            else if (value.paymenttype === 5)
                                                            {
                                                                actualPay = "by Rejoining<br>Amount :" + value.amount;
                                                            }
                                                            else if (value.paymenttype === 6)
                                                            {
                                                                actualPay = "Rejected";
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
                                                            else if (value.payment_modeid === 5)
                                                            {
                                                                reuestPay = "by Rejoining"
                                                            }
                                                            else if (value.payment_modeid === 6)
                                                            {
                                                                reuestPay = "Rejected";
                                                            }



                                                            var poolComp = value.isExit;
                                                            if (value.isExit === 0)
                                                            {
                                                                poolComp = "NO";
                                                            } else {
                                                                poolComp = "YES";
                                                            }

                                                            var payRealease = value.isPaymentRealease;
                                                            if (value.isPaymentRealease === 0)
                                                            {
                                                                payRealease = "NO";
                                                            } else {
                                                                payRealease = "YES";
                                                            }


                                                            var releaseId = value.paymentid;

                                                            var status = value.userstatus;

                                                            if (value.userstatus == 1)
                                                            {
                                                                status = "Pending";
                                                            }
                                                            else if (value.userstatus == 2) {
                                                                status = "Confirmed";
                                                            } else if (value.userstatus == 3) {
                                                                status = "Rejected";
                                                            }


                                                            $('#joiningDet').dataTable().fnAddData([
                                                                requestJoinDate,
                                                                joindate,
                                                                reuestPay,
                                                                actualPay,
                                                                status,
                                                                poolComp,
                                                                payRealease,
                                                                releaseId

                                                            ]);

                                                        })



                                                    }
                                                });

                                                $.ajax({
                                                    type: "post",
                                                    url: "getSchemePoolByNameByUserId?userId=" + userId + "&schemeId=" + shcemeId,
                                                    dataType: 'json',
                                                    success: function(response) {
                                                        $('#tablePool').DataTable().rows()
                                                                .remove()
                                                                .draw();
                                                        jQuery.each(response, function(index, value) {

                                                            var pr = "";


                                                            if (value.pmemberType === 1) {
                                                                pr = "<span class='badge bg-green'>P</span> " + value.pname
                                                            } else if (value.pmemberType === 2) {
                                                                pr = "<span class='badge bg-red'>V</span> " + value.pname
                                                            }

                                                            var ch1 = "";


                                                            if (value.ch1memberType === 1) {
                                                                ch1 = "<span class='badge bg-green'>P</span> " + value.ch1name
                                                            } else if (value.ch1memberType === 2) {
                                                                ch1 = "<span class='badge bg-red'>V</span> " + value.ch1name
                                                            }

                                                            var ch2 = "";


                                                            if (value.ch2memberType === 1) {
                                                                ch2 = "<span class='badge bg-green'>P</span> " + value.ch2name
                                                            } else if (value.ch2memberType === 2) {
                                                                ch2 = "<span class='badge bg-red'>V</span> " + value.ch2name
                                                            }

                                                            var ch3 = "";


                                                            if (value.ch3memberType === 1) {
                                                                ch3 = "<span class='badge bg-green'>P</span> " + value.ch3name
                                                            } else if (value.ch3memberType === 2) {
                                                                ch3 = "<span class='badge bg-red'>V</span> " + value.ch3name
                                                            }



                                                            $('#tablePool').dataTable().fnAddData([
                                                                pr,
                                                                value.pjoinDate,
                                                                ch1,
                                                                value.ch1joinDate,
                                                                ch2,
                                                                value.ch2joinDate,
                                                                ch3,
                                                                value.ch3joinDate,
                                                            ]);
                                                        })

                                                    }
                                                });


                                                $.ajax({
                                                    type: "post",
                                                    url: "getPaymentDetails?userId=" + userId + "&schemeId=" + shcemeId,
                                                    dataType: 'json',
                                                    success: function(response) {
                                                        $('#tablePay').DataTable().rows()
                                                                .remove()
                                                                .draw();

                                                        jQuery.each(response, function(index, value) {
                                                            var payId = value.paymentid;


                                                            var actualPay = value.paymenttype;

                                                            if (value.paymenttype === 1) {
                                                                actualPay = "by Cash<br>Amount :" + value.amount;
                                                            } else if (value.paymenttype === 2) {
                                                                actualPay = "by Cheque<br>Amount :" + value.amount + "<br>Cheque Dated :" + value.cheque_date + "<br>Cheque No :" + value.chequeno + "<br>Bank Name :" + value.bank_name;
                                                            }
                                                            else if (value.paymenttype === 3) {
                                                                actualPay = "by Netbanking<br>Amount :" + value.amount + "<br>Bank Name :" + value.bank_name + "<br>UTR No :" + value.utrno;
                                                            }
                                                            else if (value.paymenttype === 4)
                                                            {
                                                                actualPay = "by Company<br>Amount :" + value.amount;
                                                            }
                                                            else if (value.paymenttype === 5)
                                                            {
                                                                actualPay = "by Rejoining<br>Amount :" + value.amount;
                                                            }
                                                            else if (value.paymenttype === 6)
                                                            {
                                                                actualPay = "Rejected";
                                                            }



                                                            var payDate = value.paymentdate;


                                                            $('#tablePay').dataTable().fnAddData([
                                                                payId,
                                                                actualPay,
                                                                payDate
                                                            ]);

                                                        })



                                                    }
                                                });

                                                $.ajax({
                                                    type: "post",
                                                    url: "getUserSchemePassbook?userId=" + userId + "&schemeId=" + shcemeId,
                                                    dataType: 'json',
                                                    success: function(response) {
                                                        $('#passbook').DataTable().rows()
                                                                .remove()
                                                                .draw();
                                                        jQuery.each(response, function(index, value) {
                                                            $('#passbook').dataTable().fnAddData([
                                                                value.srNo,
                                                                value.date,
                                                                value.particulars,
                                                                value.withdrawl,
                                                                value.deposit,
                                                                value.balance
                                                            ]);
                                                        })

                                                    }
                                                });

                                            }
        </script>
    </body>
</html>
