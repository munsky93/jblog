<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div class="center-content">
		
	
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
		
		<form class="join-form" id="join-form" method="get" action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<input type="text" name="userName"  value="" />
			
			<label class="block-label" for="id">아이디</label>
			<input type="text" name="id" id="id" value="" >
			
			<input id="btn-checkid" type="button" value="id 중복체크">
			<p id="checkid-msg" class="form-error">
			&nbsp;
			</p>
			
			
			<label class="block-label" for="password">패스워드</label>
			<input type="password" name="password"  value="" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>

</body>


<script type="text/javascript">
    $("#btn-checkid").on("click", function(){     /* 버튼을 누르면 펑션이 실행 */

    	var id = $("#id").val();  
    	
    
    	$.ajax({
  	      
  	      url : "${pageContext.request.contextPath }/user/idcheck",      /* idcheck 으로 컨트롤로 보내준다 */ 
  	      type : "post",
  	      data : {id: id},
  	      dataType : "json",
  	      success : function(isExists){
  	    	  console.log(isExists);
  	    	  if(isExists==true) {
  	    		  $("#checkid-msg").html("사용중인 아이디 입니다.")
  	    	  } else {
  	    		  $("#checkid-msg").html("사용가능한 아이디 입니다.")
  	    	  }
  	         
  	      },
  	      error : function(XHR, status, error) {
  	         console.error(status + " : " + error);
  	      }
  	   });
    });
    
</script>


</html> 