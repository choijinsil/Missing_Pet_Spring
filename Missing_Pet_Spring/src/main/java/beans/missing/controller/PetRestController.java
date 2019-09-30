package beans.missing.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@RestController
@RequestMapping("/petRest")
public class PetRestController {
	
	@ResponseBody
	@RequestMapping("/fileUp")
	public Object fileUp(HttpServletRequest request, HttpServletResponse response,
						MultipartHttpServletRequest mtfRequest ,Model m ) {
		
		//1. 지정된 Path에 파일 업로드
		HashMap fileHashMap;
		List<HashMap> fileArrayList=new ArrayList<HashMap>();
		
		String path=request.getServletContext().getRealPath("/images/missingImage"); //파일경로
		
		File dir=new File(path); //파일 저장 경로확인
		
		if(!dir.exists()) {
			dir.mkdirs();
			
		}
		
		
		Iterator<String> itr=mtfRequest.getFileNames(); //파일들을 iterator에 넣음
		
		while(itr.hasNext()) {//파일을 하나씩 불러온다
			MultipartFile mpf=mtfRequest.getFile(itr.next());
			
			fileHashMap=new HashMap();
			
			String originalFilename=mpf.getOriginalFilename();

			String fileFullPath=path+"/"+originalFilename;// 파일전체경로
			
		try {	
			//파일 저장
			mpf.transferTo(new File(fileFullPath));
			
			fileHashMap.put("originalFilename",originalFilename);
			fileHashMap.put("fileFullPath",fileFullPath);
			
			
			fileArrayList.add(fileHashMap);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		Map<String,Object> retVal=new HashMap<String, Object>();// 응답값 셋팅
			
		try {
			List<HashMap> list=	(List<HashMap>) retVal.get("fileInfoList");
			retVal.put("fileInfoList",fileArrayList);
			retVal.put("code","OK");
			
		}catch(Exception e) {
			retVal.put("code", "FAIL");
		}
		
				return retVal;
	}

}
