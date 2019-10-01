<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>목격리스트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
     body{text-align: center}
     table{margin: auto;}
     td,th{padding: 5px}
     .max-small {
 	 width: 300px;
 	 height: 300px;
	 }
</style>
</head>
<body>
<h3>[신고 정보]&nbsp;<a href="/admin">[관리자 페이지]</a>로 돌아가기</h3>
<hr>
<table border="1">
	<tr style="background-color: skyblue">
		<th>게시번호</th>
		<th>사진</th>
		<th>발견일자</th>
		<th>발견장소</th>
		<th>설명</th>
	</tr>
		<c:forEach items="${list}" var="wit">
		<c:set var="pic" value="${wit.missing_pic}"></c:set>
  		<c:set var="array" value="${fn:split(pic,',')}"></c:set>
			<tr>
				<td>${wit.wit_no }</td>
				<td><img src="${array[0] }" class='max-small'></img></td>
				<td>
				<fmt:formatDate value="${wit.find_date }" pattern="yyyy-MM-dd HH:MM"/>
				</td>
				<td>${wit.wit_place }</td>
				<td>${wit.wit_comment }</td>
			</tr>
		</c:forEach>
      
     
</table>
<br>
	<div class='row'>
	
	<ul class="pagination">
	<c:if test="${pageMaker.prev}">
		<li class="paginate_button previous">
			<a href="${pageMaker.startPage -1}">Previous</a>
		</li>
	</c:if>

	<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
		<li class="paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} ">
			<a href="${num}">${num}</a>
		</li>
	</c:forEach>

		<c:if test="${pageMaker.next}">
		<li class="paginate_button next"><a
			href="${pageMaker.endPage +1 }">Next</a></li>
		</c:if>

	</ul>
	
	</div>
	
	<form id='actionForm' action="/admin/wit" method='get'>
		<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
		<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>

		<input type='hidden' name='type'
			value='<c:out value="${ pageMaker.cri.type }"/>'> <input
			type='hidden' name='keyword'
			value='<c:out value="${ pageMaker.cri.keyword }"/>'>

	</form>
</body>
<script type="text/javascript">
	$(document).ready(
		function() {
						
			var result = '<c:out value="${result}"/>';

			checkModal(result);
			history.replaceState({}, null, null);

		function checkModal(result) {
				if (result === '' || history.state) {
					return;
				}

				if (parseInt(result) > 0) {
					$(".modal-body").html(
							"게시글 " + parseInt(result)
									+ " 번이 등록되었습니다.");
				}
					$("#myModal").modal("show");
			}
				$("#regBtn").on("click", function() {
				self.location = "/admin";
				});

			var actionForm = $("#actionForm");
			$(".paginate_button a").on("click",function(e) {
				e.preventDefault();
				console.log('click');
					actionForm.find("input[name='pageNum']")
						.val($(this).attr("href"));
					console.log('여기다여기::'+$('input[name="pageNum"]'));
							actionForm.submit();
						});

			$(".move").on("click",function(e) {

				e.preventDefault();
				actionForm.append(
				"<input type='hidden' name='bno' value='"+ $(this).attr("href")+ "'>");
								actionForm.attr("action","/board/get");
								actionForm.submit();});

			var searchForm = $("#searchForm");

			$("#searchForm button").on(
					"click",
					function(e) {

						if (!searchForm.find("option:selected")
								.val()) {
							alert("검색종류를 선택하세요");
							return false;
						}

						if (!searchForm.find(
								"input[name='keyword']").val()) {
							alert("키워드를 입력하세요");
							return false;
						}

						searchForm.find("input[name='pageNum']")
								.val("1");
						e.preventDefault();
						searchForm.submit();
					});
		});
</script>
</html>