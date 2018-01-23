<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <%@ taglib prefix="s" uri="/struts-tags"%>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Dashboard</title>
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
  <!-- Morris chart -->
  <link rel="stylesheet" href="bower_components/morris.js/morris.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="bower_components/jvectormap/jquery-jvectormap.css">
  <!-- Date Picker -->
  <link rel="stylesheet" href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap wysihtml5 - text editor -->
  <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
  <style>
      #circle{width: 40px; height: 40px; border-radius: 50px; border: 2px solid orange; }
#circle div { margin-top:  8px;  color: white; text-align:center; }

.info-div {
  
  width: 100%;
  
  z-index: 10;
  height: 500px;
  max-height: 1340px;
  overflow-y: scroll;
}

::-webkit-scrollbar {
    width: 12px;
}

::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
    border-radius: 10px;
}

::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5); 
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
        <section class="col-lg-12 ">
        <!-- Chat box -->
          <div class="box box-success">
            <div class="box-header">
              <i class="fa fa-comments-o"></i>

              <h3 class="box-title">Chat</h3>

              
            </div>
            <div class="box-body chat info-div"  id="chat-box">
              <!-- chat item -->
              <s:iterator value="chatList">
                  <div  class="item" id="msg<s:property value="id"/>">
                      <div style="background:#<s:property value="hashValue"/>" id="circle"><div><s:property value="shortForm"/></div></div>
                             

                <p class="message">
                  <a href="#" class="name">
                      <small class="text-muted pull-right"><i  class="fa fa-clock-o"></i> <s:property value="time"/></small>
                    <s:property value="name"/> <i onclick="showDiv(<s:property value="id"/>,'<s:property value="name"/>')" class="fa fa-reply"></i>
                  </a>
                  
                      <s:if test="status=='UNREAD'"><small class="text-muted pull-right"> <b><s:property value="status"/></b></small></s:if>
                    <s:if test="replyToName!=null">
                        <span style="color: blue;cursor: pointer" onclick="scrollChat(<s:property value="replyTo"/>)">@<s:property value="replyToName"/> </span><s:property value="message"/>
                    </s:if>
                        <s:else>
<s:property value="message"/>
</s:else>
                  
                </p>
                
              </div>
              <!-- /.item -->
              </s:iterator>
              
            </div>
            <!-- /.chat -->
            <div class="box-footer">
                <form action="sendReply" method="post">
                    <input type="hidden" name="replyTo" id="reply"/>
                <span id="replyName" >
                                        
                </span>
              <div class="input-group">
                   
                <input id="msgInput" required class="form-control" name="message" placeholder="Type message...">

                <div class="input-group-btn">
                  <button type="submit" class="btn btn-success">SEND</button>
                </div>
              </div>
                </form>
            </div>
          </div>
          <!-- /.box (chat box) -->
        </section>
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
<!-- jQuery UI 1.11.4 -->
<script src="bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $(document).ready(function(){
          
          $("#dashboardli").removeClass("active");
          $("#schmeLi").removeClass("active");
          $("#schemePoolLi").removeClass("active");
          $("#schemeDetailLi").removeClass("active");
          $("#schmeNewLi").removeClass("active");
          $("#joiningLi").removeClass("active");
          $("#emailLi").removeClass("active");
                    $("#smsLi").removeClass("active");
                    $("#chatroomLi").addClass("active");


      });
    
    
  $.widget.bridge('uibutton', $.ui.button);
  $(function () {
    
$("#chat-box").scrollTop($("#chat-box")[0].scrollHeight);
    
  })
  
  function scrollChat(id)
  {
      
    $('#chat-box').animate({
        scrollTop: $("#msg"+id).offset().top},
        'slow');


$("#msg"+id).fadeOut('slow', function(){
    $(this).fadeIn('slow', function(){
        
    });
});


  }
  function showDiv(id,name)
{
    $("#reply").val(id);
    $("#replyName").text("@"+name);
    $('#msgInput').focus();
    
}
  
</script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="bower_components/raphael/raphael.min.js"></script>
<script src="bower_components/morris.js/morris.min.js"></script>
<!-- Sparkline -->
<script src="bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="bower_components/moment/min/moment.min.js"></script>
<script src="bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>

<!-- FastClick -->
<script src="bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
</body>
</html>
