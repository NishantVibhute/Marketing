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
                    <div class="box">
            <div class="box-body">
              
                        
                        <label  class="col-sm-2  control-label">Select Scheme</label>
                        <div class="col-sm-7">
                            <select id="schemeMode"   class="form-control">
                                <s:iterator value="schemeList" var="sb">
                                    <option value="<s:property value="id" />"><s:property value="schemeName" /></option>

                                </s:iterator>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <button type="button" onclick="getData()" class="btn btn-info">SEARCH</button>
                        </div>
                    </div>
                    </div>
                        </div>
                    <div class="row">
                    
                        <div class="col-md-12">
                            <!-- DONUT CHART -->
                            <div class="box box-danger ">
                                <div class="box-header  with-border">
                                    <h3 class="box-title">Pool Stats</h3>

                                    <div class="box-tools pull-right">
                                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                        </button>

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
                    <div class="row">
                        <!-- /.col -->
                        <div class="col-md-12">
                            
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Scheme Pool</h3>
            </div>
                            <div class="box-body">
              <table id="tablePool" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th colspan="2" style="text-align: center;width: 25%">Parent</th>
                  <th colspan="2" style="text-align: center;width: 25%">Child 1</th>
                  <th colspan="2" style="text-align: center;width: 25%">Child 2</th>
                  <th colspan="2" style="text-align: center;width: 25%">Child 3</th>
                  
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
                        <!-- /.col -->
                        
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
            
                                  var donut = "";
                                  $(function() {
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
                                                    
                                               

                                      //DONUT CHART
                                      donut = new Morris.Donut({
                                          element: 'sales-chart',
                                          resize: true,
                                          colors: ["#3c8dbc", "#f56954", "#00a65a", "red", "blue"],
                                          data: [{"label": "Select Scheme", "value": 0}],
                                          hideHover: 'auto'
                                      });




                                  });
                                  function getData()
                                  {
                                      var a = $('#schemeMode').val();


                                      $.ajax({
                                          type: "post",
                                          url: "getSchemeStats?val=" + a,
                                          dataType: 'json',
                                          success: function(response) {

                                              donut.setData(response);
                                          }
                                      });

$.ajax({
                                        type: "post",
                                        url: "getSchemePoolByName?val2=" + a,
                                        dataType: 'json',
                                        success: function(response) {
                                            jQuery.each(response, function(index, value) {
                                                
                                                var pr ="";
                                                
                                                
                                                                if (value.pmemberType === 1) {
                                                                    pr = "<span class='badge bg-green'>P</span> "+value.pname
                                                                } else if (value.pmemberType === 2) {
                                                                    pr = "<span class='badge bg-red'>V</span> "+value.pname
                                                                }
                                                                
                                                                var ch1 ="";
                                                
                                                
                                                                if (value.ch1memberType === 1) {
                                                                    ch1 = "<span class='badge bg-green'>P</span> "+value.ch1name
                                                                } else if (value.ch1memberType === 2) {
                                                                    ch1 = "<span class='badge bg-red'>V</span> "+value.ch1name
                                                                }
                                                
                                                var ch2 ="";
                                                
                                                
                                                                if (value.ch2memberType === 1) {
                                                                    ch2 = "<span class='badge bg-green'>P</span> "+value.ch2name
                                                                } else if (value.ch2memberType === 2) {
                                                                    ch2 = "<span class='badge bg-red'>V</span> "+value.ch2name
                                                                }
                                                                
                                                                var ch3 ="";
                                                
                                                
                                                                if (value.ch3memberType === 1) {
                                                                    ch3 = "<span class='badge bg-green'>P</span> "+value.ch3name
                                                                } else if (value.ch3memberType === 2) {
                                                                    ch3 = "<span class='badge bg-red'>V</span> "+value.ch3name
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

                                  }

        </script>
    </body>
</html>
