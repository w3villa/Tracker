<jsp:include page="header.jsp"/>
success...
Welcome ${sessionScope.userEntityBean.email}

<script type="text/javascript">
/*function doAjax() {
    $.ajax({
      type: 'POST',
      url: 'getUsers',
      data: ({userName : $("#userName").val(),email : $("email").val()}),
      success: function(data) {
        $('#showDiv').html("<h1>data.userName</h1><h2>data.email</h2>");
      }
    });
  }
*/
function doAjax() {
	userName = $("#userName").val();
	email = $("#email").val();
	$("#showDiv").animate({height:300},"slow");
    $("#showDiv").animate({width:300},"slow");
    $("#showDiv").animate({height:100},"slow");
    $("#showDiv").animate({width:100},"slow");
    $.post("${pageContext.servletContext.contextPath}/getUsers",
      "userName="+userName+"&email="+ email,
      function(data) {
    	if(data.status=="SUCCESS"){
    		$('#showDiv').html("<h1>"+data.result.userName+"</h1><h2>"+data.result.email+"</h2>");
    	}
    	else{
    		alert("Response unsuccessfull.");
    	}
    	
      }
    );
  }

/*
$.ajax({
	  type: 'POST',
	  url: url,
	  data: data,
	  success: success,
	  dataType: dataType
	});
*/

</script>
<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
<div id="showDiv"></div>
<div>
	<input type="text" name="userName" id="userName">
	<input type="text" name="email" id="email">
	<input type="button" onclick="doAjax()" value="clickMe">
</div>
<jsp:include page="UploadFile.jsp"></jsp:include>