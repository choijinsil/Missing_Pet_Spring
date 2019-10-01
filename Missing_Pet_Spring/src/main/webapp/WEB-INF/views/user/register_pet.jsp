<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실종동물등록</title>
<style>
	h3{text-align: center;}
	hr{width: 380px;}
	table.type11 {
			    border-collapse: separate;
			    border-spacing: 1px;
			    text-align: center;
			    line-height: 1.5;
			    margin: 20px 10px;
			    margin-left: 120px;
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
	
	.header{position: absolute; float: left;}
	.wrap{ display:inline; width: 100%; height: 500px; border-radius: 5px; position: absolute; margin-top: 20px;}
	.list{ display:inline; float:left; width:40%; margin-top: 30px;}
	.image{width: 100px; height: 100px; margin-left: 290px;}
	
	.map_wrap{ display:inline; float:left; width: 60%; height: 700px; margin-top: 30px;}
	.title {font-weight:bold;display:block;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;
    		background:rgba(255,255,255,0.8);z-index:1;padding:5px;margin-top:30px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
    
    body {
			  background: #ff5467; /* fallback for old browsers */
			  background: -webkit-linear-gradient(right, #ff5467, #ff5467);
			  background: -moz-linear-gradient(right, #ff5467, #ff5467);
			  background: -o-linear-gradient(right, #ff5467, #ff5467);
			  background: linear-gradient(to left, #ff6375, #ffc9cf);
			  font-family: "Roboto", sans-serif;
			  -webkit-font-smoothing: antialiased;
			  -moz-osx-font-smoothing: grayscale;      
		}
	
	a{
	  font-family: "Roboto", sans-serif;
	  text-transform: uppercase;
	  outline: 0;
	  background: #ff6375;
	  border: 0;
	  padding: 15px;
	  color: #FFFFFF;
	  font-size: 14px;
	  -webkit-transition: all 0.3 ease;
	  transition: all 0.3 ease;
	  cursor: pointer;
	}
    
	button {
	  font-family: "Roboto", sans-serif;
	  text-transform: uppercase;
	  outline: 0;
	  background: #ff6375;
	  border: 0;
	  padding: 15px;
	  color: #FFFFFF;
	  font-size: 14px;
	  -webkit-transition: all 0.3 ease;
	  transition: all 0.3 ease;
	  cursor: pointer;
	  text-align: center;
	}
	
	#bt1{
		margin-left: 280px;
	}

    
   	/* The Modal (background) */
       .modal {
           display: none; /* Hidden by default */
           position: fixed; /* Stay in place */
           z-index: 10; /* Sit on top */
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
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(function(){
		
		$("input[name=type]:checkbox").change(function() {
	        var cnt = 1;
	        if( cnt == $("input[name=type]:checkbox:checked").length ) {
	            $(":checkbox:not(:checked)").attr("disabled", "disabled");
	        } else {
	            $("input[name=type]:checkbox").removeAttr("disabled");
	        }
	    });
 	
		 var saveImageCount=0;
		 
		 
			if( $('img').attr("src")=='#'){
				
				$('img').hide();
			}
			 
			$("#uploadFile").on('change',function() {
					
					readURL(this);
			});
				
			
			function readURL(input){
				
					if(input.files && input.files[0]){
						var reader=new FileReader();
						
						reader.onload=function(e){
								
							$('#img').attr('src',e.target.result);
							$('#img').show();
							
						}
						reader.readAsDataURL(input.files[0]);
					}
					
				} //이미지 미리보기 

			
				$('#fileForm').submit(function() { // form 데이터를  PetController에 전송할때 유효성검사
					
					
					 var missing_pic="";	
						
					
					for(var i=0;i<saveImageCount;i++){
						
						if(i!=saveImageCount-1){
							missing_pic+=$('#file_view_list').children('#minus').eq(i).text()+',';
						}else
							missing_pic+=$('#file_view_list').children('#minus').eq(i).text();
						
					}//PetController에 missig_pic 문자열을 보내기위한작업 (아래 hidden으로)
					
						
					
						$('input[name=missing_pic]').attr('value',missing_pic);	
					
						
					 if(!$('#missing_date').val() || !$('#missing_time').val()){
						 modal2Display();
						 return false;
					 }else{
						
						 
						form=$('fileForm');
						form.action=form.action+"?comment="+form["comment"];
							
						return true;
					 }
				
					
				
				
				});						

				
				//파일업로드 선택할때마다 비동기적으로 controller에 데이터 전달
				
				$("input[type=file]").change(function(){
					
					var index=$('#file_view_list div').index($('#minus'));
					
					var delName=$('#file_view_list').children('#minus').eq(index).text();
					
					
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
					
					
					$.ajax({ //파일선택할때마다 PetRestController와 비동기 통신 
						url:"/petRest/fileUp",
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
									
									fileListView +="<p>"+"<div id='minus' name='pic'>"+fileInfo.originalFilename+"<img  src='https://iconmonstr.com/wp-content/g/gd/makefg.php?i=../assets/preview/2012/png/iconmonstr-minus-6.png&r=0&g=0&b=0'  width='15px' height='15px'></div></p>"//뷰에 업로드한 파일 목록 표시용
									fullPath=fileInfo.fileFullPath;
									$("#img"+saveImageCount).attr("src","/images/missingImage/"+fileInfo.originalFilename);
								});
								
								console.log($('div[name=pic]').val);
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
				
				//- 이미지 클릭시 파일삭제
				$(document).on('click','#minus',function(){
					var index=$('#file_view_list div').index(this);
						
					var delName=$('#file_view_list').children('#minus').eq(index).text();
					
					
					$.ajax({ // 업로드한 사진명 밑에  파일명과 같이있는 - 이미지를 클릭할때마다 WitnessRestController와 비동기 통신
						url:"/petRest/fileDown",
						data:delName,	//"{\"delName\":"+delName+"}"
						dataType:'text',
						processData:false,
						contentType:'application/json; charset=utf-8',
						type:'POST',
						
						success:function(data){
							if(data=='SUCCESS'){//응답결과
								alert(data);
								$("#file_view_list div").eq(index).detach();
								saveImageCount--;
							}else{
								
								alert("파일 삭제에 실패하였습니다!!!!");
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
			  var modal2=document.getElementById('myModal2');
		      

		      // Get the <span> element that closes the modal
		      var span = document.getElementsByClassName("close")[0];  
		      var span2 = document.getElementsByClassName("close")[1];  
			
		  	
		      function modalDisplay(){
		     	 modal.style.display = "block";
		     	 
		     	 
		      }
		      
		      function modal2Display(){
		      	 
		      	 modal2.style.display = "block";
		      	 
		       }
		      
		  // When the user clicks on <span> (x), close the modal
		     span.onclick = function() {
		         modal.style.display = "none";
		        
		     }

		     span2.onclick = function() {
		         
		         modal2.style.display="none";
		     }

		  
		  
		     // When the user clicks anywhere outside of the modal, close it
		     window.onclick = function(event) {
		         if (event.target == modal || event.target==modal2){
		             modal.style.display = "none";
		             
		         }else if(event.target==modal2){
		        	 modal2.style.display="none";
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
	
		<div id="myModal2" class="modal">
		 	<div class="modal-content">
		 		<span class="close">&times;</span>
		 		<p>필수정보값을 모두 입력하시오!!!</p>
		 	</div>
		</div>
	<a class="header" href="/main" style="text-decoration:none">홈페이지로</a>
	<div class="wrap">
		<div class="map_wrap">
		    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
		    <div class="hAddr">
		        <span class="title">지도중심기준 행정동 주소정보</span>
		        <span id="centerAddr"></span>
		        <span><input type="text" id="searchCenter" style="width:219px; text-align: center" placeholder="지도중심좌표 이동" ></span>
		    </div>
		</div>
		<div class="list">
		<h3>실종동물등록</h3><hr style="background-color: yellow">

  		<img src="#" id="img" class="image">
			
			<form name="fileForm" id="fileForm" action="register" method="post" enctype="multipart/form-data">
				<table class="type11">
					<tbody>
						<tr>
							<th>*사진</th>
							<td><input multiple="multiple" type="file" name="uploadFile" id="uploadFile"></td>
						</tr>
						<tr>
							<th>*실종날짜</th>
							<td><input type="date" id="missing_date" name="missing_date"></td>
						</tr>
						<tr>
							<th>*실종시간</th>
							<td><input type="time" id="missing_time" name="missing_time"></td>
						</tr>
						<tr>
							<th>코멘트</th>
							<td>
								<textarea rows="10" cols="30" name="comment"></textarea>
							</td>
						</tr>
						<tr>
							<th>*보상금</th>
							<td><input name="tip"></td>
						</tr>
						<tr>
							<th>*종류</th>
							<td colspan="3">
								<input type="checkbox" name="type" value="강아지"> 강아지
								<input type="checkbox" name="type" value="고양이"> 고양이
								<input type="checkbox" name="type" value="기타"> 기타
							</td>
						</tr>
					</tbody>
				</table>
			<div>
				<button type="submit" id="bt1">등록</button>
				<button type="reset">reset</button>
				<input type="hidden" name="missing_pic" value="">
			</div>
			<div id="file_view_list"></div>
			<div id="clickLatlng"></div>
			</form>
		</div>
	</div>
	
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=01fbf10c2ff64d32622233895840cd27&libraries=services"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(37.566826, 126.9786567),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
		
		// input창에 client가 주소를 입력시 change이벤트가 발생해서 text박스에 있는 주소값으로 지도중심으로 바꾸기
		function changeCenter(center){
			
			var center=center;
			var geocoder=new kakao.maps.services.Geocoder();
			
			//주소로 좌표를 검색
			geocoder.addressSearch(center,function(result,status){
				var coords=new kakao.maps.LatLng(result[0].y,result[0].x);
			
				map.setCenter(coords);
			});
			
		}
		
		
		// 지도에 클릭 이벤트를 등록합니다
		// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {       
		    
		    // 클릭한 위도, 경도 정보를 가져옵니다 
		    var latlng = mouseEvent.latLng; 
		    alert(mouseEvent.latLng);
		    // 마커 위치를 클릭한 위치로 옮깁니다"C:/Users/Playdata/Desktop/web/2/index4.html"
		    marker.setPosition(latlng);
		    
		    /* var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
		    message += '경도는 ' + latlng.getLng() + ' 입니다'; */
		    
		    var message = latlng.getLat() +',' +latlng.getLng();
		    
		    var resultDiv = document.getElementById('clickLatlng'); 
		    resultDiv.innerHTML = '<input type=hidden name=missing_place value=' +message +'>';
		    
		    
		    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
		        if (status === kakao.maps.services.Status.OK) {
		            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
		            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
		           
		            var content = '<div class="bAddr">' +
		                            '<span class="title">법정동 주소정보</span>' + 
		                            detailAddr + 
		                        '</div>';

		            // 마커를 클릭한 위치에 표시합니다 
		            marker.setPosition(mouseEvent.latLng);
		            marker.setMap(map);

		            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
		            infowindow.setContent(content);
		            infowindow.open(map, marker);
		        }   
		    });
		});
		
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();

		var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
		    infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

		// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
		searchAddrFromCoords(map.getCenter(), displayCenterInfo);

		// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
		kakao.maps.event.addListener(map, 'idle', function() {
		    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
		});

		function searchAddrFromCoords(coords, callback) {
		    // 좌표로 행정동 주소 정보를 요청합니다
		    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
		}

		function searchDetailAddrFromCoords(coords, callback) {
		    // 좌표로 법정동 상세 주소 정보를 요청합니다
		    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
		}

		// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
		function displayCenterInfo(result, status) {
		    if (status === kakao.maps.services.Status.OK) {
		        var infoDiv = document.getElementById('centerAddr');

		        for(var i = 0; i < result.length; i++) {
		            // 행정동의 region_type 값은 'H' 이므로
		            if (result[i].region_type === 'H') {
		                infoDiv.innerHTML = result[i].address_name;
		                break;
		            }
		        }
		    }    
		}
		</script>
</body>
</html>