<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

    <!-- Bootstrap Core CSS -->
<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


<style type="text/css">
	
	table{
    border-collapse: separate;
    border-spacing: 1px;
    line-height: 1.5;
    margin: auto;
	}
	table.type11 th {
    width: 155px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #fff;
    background:#ff6375 ;
	}
	table.type11 td {
    width: 155px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #eee;
	}
	
	.paginate_button{
		align:center;
	}
	body{text-align: center}
    td,th{padding: 5px}
    
</style>


<meta charset="UTF-8">
<title>회원리스트</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script>
  	function search_user(){
  		var search_id = document.getElementById('search_id').value;
  		location.href = '/admin/search_user?search_id='+search_id;
  	}
  </script>
</head>
<body>
<h3><a href="/main">[메인페이지]</a>&nbsp;<a href="/admin/pet">[실종정보 목록]</a>&nbsp;<a href="/admin/wit">[목격정보 목록]</a>&nbsp;<a href="/admin">[회원 목록]</a></h3>
<hr>
<input type="text" placeholder="검색할 아이디나 이름을 입력해주세요" size="50px" id="search_id"> 
<input type="button" value="검색" onclick="search_user()"> 
<input type="button" value="전체 보기" onclick="location.href='/admin'" style="margin-bottom: 30px;">
<table border="1" class="type11" >
	<tr >
		<th>아이디</th>
		<th>이름</th>
		<th>이메일</th>
		<th>연락처</th>
		<th>주소</th>
		<th>블랙리스트 여부</th>
	</tr>
		<c:forEach items="${list}" var="user">
			<tr>
				<td><a href="admin/edit?id=${user.id}">${user.id }</a></td>
				<td>${user.name }</td>
				<td>${user.email }</td>
				<td>${user.tel }</td>
				<td>${user.address }</td>
				<c:if test="${user.black eq 'N'}">
					<td>${user.black }</td>
				</c:if>
				<c:if test="${user.black eq 'Y'}">
					<td><font color="red">${user.black }</font></td>
				</c:if>
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
	
	<form id='actionForm' action="/admin" method='get'>
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