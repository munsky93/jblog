<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/blogheader.jsp"></c:import>


		<div id="wrapper">
			<div id="content" class="full-screen">

				<c:import url="/WEB-INF/views/includes/admiddle.jsp"></c:import>

				<table class="admin-cat">
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id=cateList>
						<%-- <tr>
							<td>3</td>
							<td>영화</td>
							<td>5</td>
							<td>영화에 관한 이야기입니다.</td>
							<td><img
								src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>
						</tr>
						<tr>
							<td>2</td>
							<td>음악</td>
							<td>3</td>
							<td>음악에 관한 이야기입니다.</td>
							<td><img
								src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>
						</tr>
						<tr>
							<td>1</td>
							<td>미분류</td>
							<td>0</td>
							<td>기본으로 만들어지는 카테고리 입니다.</td>
							<td><img
								src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>
						</tr> --%>
					</tbody>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" name="name" value=""></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" name="desc"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input id="btnAddCate" type="submit" value="카테고리 추가"></td>
					</tr>
				</table>

			</div>
		</div>

		<!-- 푸터-->
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2018
			</p>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		fetchList();
	});



	$("tbody").on("click", "img", function() {
		
		var cateNo =  $(this).attr("class"); 
		console.log(cateNo);

		$.ajax({
			url : "${pageContext.request.contextPath }/${blogvo.id}/admin/delete2",
			type : "post",
			data : {
				cateNo : cateNo
			},
			dataType : "json",
			success : function(delno) {
				
				if (delno == 0) {
					alert("삭제안돼요");
				} else {
					$("#" + delno).remove(); //delno는 삭제하는 번호를 말하고 remove를 통해 삭제한다!
					
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})

	});



	$("#btnAddCate").on("click", function() {
		console.log("btnAdd");
		var id = "${blogvo.id}";
		var cateName = $("[name=name]").val();
		var description = $("[name=desc]").val(); /* 위에꺼 name 참고하기 */
		console.log(cateName);
		console.log(description);

		/* 리스트 입력 ajax */
		$.ajax({
			url : "${pageContext.request.contextPath }/${blogvo.id}/admin/addcategory",
			type : "post",
			data : {
				id : id,
				cateName : cateName,
				description : description
			},
			dataType : "json",
			success : function(categoryVo) {
				render(categoryVo, "up");
				$("[name=name]").val("");
				$("[name=desc]").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
	})







	function fetchList() { /* 위에 있는 fetchList와 같은거 위에있는거를 실행(function) */
		/* 리스트 요청 ajax */
		$.ajax({
			url : "${pageContext.request.contextPath }/{blogvo.id}/admin/category/list",
			type : "get",
			dataType : "json",
			success : function(list) {
				/*성공시 처리해야할 코드 작성*/
				console.log(list);
				for (var i = 0; i < list.length; i++) {

					render(list[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})

	}





	function render(categoryVo, updown) {
		var str = "";

		str += "<tr id='"+categoryVo.cateNo+"'>";
		str += "<td>" + categoryVo.cateNo + "</td>";
		str += "<td>" + categoryVo.cateName + "</td>"; 
		str += "<td>5</td>";
		str += "<td>" + categoryVo.description + "</td>";
		str += "<td><img id='delete' class="+categoryVo.cateNo+" src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>";
		str += "</tr>";




		if (updown == "up") {
			$("#cateList").prepend(str);
		} else if (updown == "down") {
			$("#cateList").append(str);
		} else {
			console.log("오류");
		}
	}
</script>
</html>