<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	
	
	table.type11 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: center;
    
    
    line-height: 1.5;
    margin: 20px 10px;
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
	.max-small {
 	width: 300px;
 	height: 300px;
	}
</style>

<meta charset="UTF-8">
<title>실종리스트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
     body{text-align: center}
     table{margin: auto;}
     td,th{padding: 5px}
  </style>
</head>
<body>
<h3>분실 정보&nbsp;<a href="/admin">[관리자 페이지]</a>로 돌아가기</h3>
<hr>
<table border="1" class="type11">
	<tr style="background-color: skyblue">
		<th>공고번호</th>
		<th>아이디</th>
		<th>사진</th>
		<th>작성일</th>
		<th>장소</th>
		<th>분실날짜</th>
		<th>사례금</th>
		<th>인계날짜</th>
	</tr>
		<c:forEach items="${list}" var="pet">
		<c:set var="pic" value="${pet.missing_pic}"></c:set>
  		<c:set var="array" value="${fn:split(pic,',')}"></c:set>
			<tr>
				<td>${pet.missing_no }</td>
				<td>${pet.id }</td>
				<td><img src="/images/missingImage/${array[0]}" class='max-small'></img></td>
				<td>
				<fmt:formatDate value="${pet.write_date }" pattern="yyyy-MM-dd HH:MM"/>
				</td>
				<td>${pet.missing_place }</td>
				<td>
				<fmt:formatDate value="${pet.missing_date }" pattern="yyyy-MM-dd HH:MM"/>
				</td>
				<td>${pet.tip }</td>
				<td>
				<fmt:formatDate value="${pet.complete_date }" pattern="yyyy-MM-dd HH:MM"/>
				</td>
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
	
	<form id='actionForm' action="/admin/pet" method='get'>
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