<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
		/* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
    
        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 50%; /* Could be more or less, depending on screen size */                          
        }
        /* The Close Button */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
		


</style>
<meta charset="UTF-8">
<title>실종동물정보 입력창</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

 $(function() {
	 
	 var saveImageCount=0;

	 
	if( $('img').attr("src")=='#'){
		
		$('img').hide();
		//$('img').hide();
		
	}
	 
	$("#uploadFile1").change(function() {
			
			readURL1(this);
			
		});
		$("#uploadFile2").change(function() {
		
			readURL2(this);
			
		});

		$("#uploadFile3").change(function() {
			
			readURL3(this);
		});
	 
	 
	
		 
	function readURL1(input){
			if(input.files && input.files[0]){
				var reader=new FileReader();
				
				reader.onload=function(e){
					$('#img1').attr('src',e.target.result);
					$('#img1').show();
				}
				reader.readAsDataURL(input.files[0]);
			}
			
		}

		function readURL2(input){
			if(input.files && input.files[0]){
				var reader=new FileReader();
				
				reader.onload=function(e){
					$('#img2').attr('src',e.target.result);
					$('#img2').show();
				}
				reader.readAsDataURL(input.files[0]);
			}
			
		}

		function readURL3(input){
			if(input.files && input.files[0]){
				var reader=new FileReader();
				
				reader.onload=function(e){
					$('#img3').attr('src',e.target.result);
					$('#img3').show();
				}
				reader.readAsDataURL(input.files[0]);
			}
			
		}

		
		$('#fileForm').submit(function() { // form 데이터를 전송할때 유효성검사
			
			 if(!$('#wit_date').val() || !$('#wit_time').val()){
				 alert('필수정보를 모두 입력하시오!!!!!');
				 return false;
			 }else{
				
				 
				form=$('fileForm');
				form.action=form.action+"?comment="+form["comment"];
			
				return true;
			 }
		});						

		
		//파일업로드 선택할때마다 비동기적으로 controller에 데이터 전달
		
		$("input[type=file]").change(function(){
		
			
		 if(saveImageCount>=3){
			 modalDisplay();
			 
			 return;
			 
		 }else
			 
			var fileListView="";
			
			var formData=new FormData(); //ajax로 넘길 data
			
			var fileInput=document.getElementById("uploadFile");
			
			var files=fileInput.files;//업로드한 파일들의 정보를 얻는다
			
			for(var i=0;i<files.length;i++){
				
				formData.append('file-'+i,files[i]);//업로드한 파일데이터 하나하나읽어서 formData에 넣는다
				
			}
			
			
			$.ajax({
				url:"/witRest/fileUp",
				data:formData,
				dataType:"json",
				processData:false,
				contentType:false,
				type:'POST',
				
				success:function(data){
					if(data.code=="OK"){//응답결과
						fileInfoList=data.fileInfoList; //응답결과 데이터
						originalFilename=data.originalFilename;
						
						saveImageCount++;
						
						$.each(fileInfoList,function(index,fileInfo){
							
							
							console.log("Path:"+fileInfo.fileFullPath);
							console.log("FileName"+fileInfo.originalFilename);
							
							fileListView +="<p>"+fileInfo.originalFilename+"</p>"//뷰에 업로드한 파일 목록 표시용
							fullPath=fileInfo.fileFullPath;
							$("#img"+saveImageCount).attr("src","/images/witimage/"+fileInfo.originalFilename);
						});
						
						
						$("#file_view_list").append(fileListView);
					}else{
						
						alert("파일 등록에 실패하였습니다!!!!");
					}
					
				},
				error:function(xhr,textStatus,error){
					
					alert("예상치못한 오류가 발생했습니다!!!!");
				}
				
				
			});
			
		 
		 
		});
		
	//모달창이벤트
	// Get the modal
      var modal = document.getElementById('myModal');

      

      // Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];  
	
  	
      function modalDisplay(){
     	 modal.style.display = "block";
     	 
      }
	
  // When the user clicks on <span> (x), close the modal
     span.onclick = function() {
         modal.style.display = "none";
     }

     // When the user clicks anywhere outside of the modal, close it
     window.onclick = function(event) {
         if (event.target == modal) {
             modal.style.display = "none";
         }
     }
     
     //폼전송시 발생하는 이벤트
    $('#fileForm').submit(function(){
    	
    	saveImageCount=0;
    	
    });
     
 });
	
</script>
</head>
<body>

 <div id="myModal" class="modal">
 	<div class="modal-content">
 		<span class="close">&times;</span>
 		<p>이미지는 총 3개까지 업로드 가능합니다.</p>
 	
 	</div>
 </div>
	
  <img src="#" id="img1">
	 <img src="#" id="img2">
	 <img src="#" id="img3">
	
  <table border="1">
	
	<thead>
	 <tr>
	 	<th style="border:none">목격동물 정보입력</th>
	 </tr>
	 
	</thead>
	<tbody>
	   
	  	<form name="fileForm" id="fileForm" method="post" action="/wit/fileUp" enctype="multipart/form-data" >
	  
		  <tr><td>사진:</td><td><input multiple="multiple" type="file" name="uploadFile" id="uploadFile"> </td></tr>  
		  <tr><td>발견날짜:</td><td><input type="date" name="wit_date" id="wit_date" ></td> </tr>
		  <tr><td>발견시간:</td><td><input type="time" name="wit_time" id="wit_time" ></td> </tr>
		  <tr ><td>위치정보:</td><td colspan="1"><input type="text" name="wit_place" value="${addr }" size="50" readonly></td> </tr>
		  <tr><td>코멘트:</td><td><textarea rows="5" cols="20" name="comment"></textarea></td> </tr>
	      <tr><td style="border:none"><button  name="input" type="submit"  >등록하기</button>   </td></tr>
	  	<input type="hidden" name="missing_no" value="${missing_no}">	
	  	<input type="hidden" name="latLng" value="${latLng}">	
	  	
	  	</form>	   
  	 
  	</tbody>
  </table>		  
  
  	
  	<h3>업로드한 사진명</h3>
	
	<div id="file_view_list"></div>
	
	
</body>
</html>