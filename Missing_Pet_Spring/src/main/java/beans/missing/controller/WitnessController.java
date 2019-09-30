package beans.missing.controller;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import beans.missing.persistence.WitnessDAO;
import beans.missing.service.WitService;

import beans.missing.domain.WitnessVO;

@Controller
@RequestMapping("/wit")
public class WitnessController {

	ArrayList<String> nameList;
	int count=0;
	String pathList="";
	
	
	int count = 0;
	String pathList = "";

	@Autowired
	WitService witService;
	
	@RequestMapping("/wit/{id}/{missing_no}/{missing_place}") //common>map.jsp 에서 목격동물등록버튼 누를때 실행되는 메소드
	public String wit(@PathVariable String id,@PathVariable String missing_no,@PathVariable String missing_place,Model m ) { 
		
		
		System.out.println("id>>>"+id);
		System.out.println("missing_no>>>"+missing_no);
		

	@RequestMapping("/wit/{id}/{missing_no}/{missing_place}") // common>map.jsp 에서 목격동물등록버튼 누를때 실행되는 메소드
	public String wit(@PathVariable String id, @PathVariable String missing_no, @PathVariable String missing_place,
			Model m) {

		System.out.println("id>>>" + id);
		System.out.println("missing_no>>>" + missing_no);

		String missingno = missing_no;
		String place = missing_place;
		String latitude = place.split(",")[0];
		String longitude = place.split(",")[1];
	
		

		m.addAttribute("latitude", latitude);
		m.addAttribute("longitude", longitude);
		m.addAttribute("missing_no", missing_no);
		
		return "/common/map"; 
		}
	

		return "/common/map";
	}

	@GetMapping("/witpet")
	public String witpet() {
		

		return "/common/wit_pet";
	}
	
	
	//common>map.jsp 에서 마커를찍으면 생기는

	// common>map.jsp 에서 마커를찍으면 생기는
	@RequestMapping("/witpet/{detailAddr}/{latLng}/{missing_no}")
	public String witpet(@PathVariable String detailAddr,@PathVariable String latLng, @PathVariable String missing_no,Model m) {
		//System.out.println("latLng>>>>"+latLng);
		
		String lalt=latLng;
		 int idx=lalt.indexOf(",");
		 int idx2=lalt.indexOf(")");
		 
		 
		String la=lalt.substring(1, idx)+",";
		String lt=lalt.substring(idx+2, idx2);
		
		//wit_pet.jsp에서 전달해줄정보들
		
		m.addAttribute("missing_no",missing_no);
	public String witpet(@PathVariable String detailAddr, @PathVariable String latLng, @PathVariable String missing_no,
			Model m) {
		// System.out.println("latLng>>>>"+latLng);

		String lalt = latLng;
		int idx = lalt.indexOf(",");
		int idx2 = lalt.indexOf(")");

		String la = lalt.substring(1, idx) + ",";
		String lt = lalt.substring(idx + 2, idx2);

		// wit_pet.jsp에서 전달해줄정보들

		m.addAttribute("missing_no", missing_no);
		m.addAttribute("addr", detailAddr);
		m.addAttribute("latLng",la+lt);
		
		
		return "/common/wit_pet" ;
	}//common>wit_pet.jsp에서 목격동물 등록버튼 누를시
	
	
	
	
		m.addAttribute("latLng", la + lt);

		return "/common/wit_pet";
	}// common>wit_pet.jsp에서 목격동물 등록버튼 누를시

	@RequestMapping("/fileUp")
<<<<<<< HEAD
	public Object fileUp(HttpServletRequest request, HttpServletResponse response,MultipartHttpServletRequest multipartRequest,Model m ) {
		Date date = null;
		String date_s=multipartRequest.getParameter("wit_date")+" "+multipartRequest.getParameter("wit_time");
		SimpleDateFormat dt=new SimpleDateFormat("yyyy-mm-dd hh:mm");//wit_pet.jsp에서 발견날짜,발견시간을 불러와서 포맷을 지정
		try {
			date=dt.parse(date_s);
		 //System.out.println("parse한 포맷>>"+date);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		System.out.println("missing_pic>>>"+request.getParameter("missing_pic"));
		System.out.println("find_date>>>"+date);
		System.out.println("wit_place>>>"+request.getParameter("latLng"));
		System.out.println("wit_comment>>>"+multipartRequest.getParameter("comment"));
		System.out.println("세션에저장된ID>>>>"+request.getSession().getAttribute("loginId"));
		System.out.println("missing_no>>>"+multipartRequest.getParameter("missing_no"));
		
		String no=multipartRequest.getParameter("missing_no");
		
			//2.vo ---> DB에 저장  (pathList: insertArticle에 파일업로드경로)
			WitnessVO wVO=new WitnessVO(request.getParameter("missing_pic"),date,request.getParameter("latLng"),multipartRequest.getParameter("comment"),(String)request.getSession().getAttribute("loginId"),Integer.parseInt(no)); 
			//목격자 테이블에 들어갈 정보를담은 vo ===>끝에 파라미터인자 2개는 map.jsp에저장되어있는 실종동물테이블에서 정보를 받아와야함
			
			
			System.out.println(wVO.toString());
				
			/*
				try {
					if(witService.witInfor_insert(wVO)==1)
						System.out.println("DB입력성공!!!");
						WitnessVO firstVO=witService.printData();
						System.out.println("발견장소>>>>"+request.getSession().getAttribute("addr"));
						
						
						String missing_pic=firstVO.getMissing_pic();
						
						m.addAttribute("nameList", nameList);
						
						m.addAttribute("witInfor_insert",firstVO);
						return "redirect:/main/main";//저장된 VO위치정보로 마커표시하기, ajax로 wit_info.jsp띄어서 등록한 목격정보 띄우기
																	   // 목격마커 숨기기(실종마커 클릭했을때 missing_no와 일치하는 목격마커 띄우기)
				} catch (Exception e) {
					e.printStackTrace();
				}											
				*/
				return "redirect:/main";
			
		
		
	}//fileUp
	
=======
	public Object fileUp(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest multipartRequest, Model m) {

		/*
		 * Date date = null; String id="kimjuwon"; // ★저장되어있는 loginId값 가져오기 String no =
		 * multipartRequest.getParameter("missing_no");
		 * 
		 * String date_s=multipartRequest.getParameter("wit_date")+" "+multipartRequest.
		 * getParameter("wit_time"); //System.out.println(date_s); SimpleDateFormat
		 * dt=new SimpleDateFormat("yyyy-mm-dd hh:mm");//wit_pet.jsp에서 발견날짜,발견시간을 불러와서
		 * 포맷을 지정
		 * 
		 * try { date=dt.parse(date_s); //System.out.println("parse한 포맷>>"+date); }
		 * catch (ParseException e) {
		 * 
		 * e.printStackTrace(); }
		 * 
		 * 
		 * //2.vo ---> DB에 저장 (pathList: insertArticle에 파일업로드경로) WitnessVO wVO=new
		 * WitnessVO(pathList,date,request.getParameter("latLng"),multipartRequest.
		 * getParameter("comment"),id,Integer.parseInt(no)); //목격자 테이블에 들어갈 정보를담은 vo
		 * ===>끝에 파라미터인자 2개는 map.jsp에저장되어있는 실종동물테이블에서 정보를 받아와야함
		 * 
		 * 
		 * pathList=""; System.out.println(wVO.toString());
		 * 
		 * try { if(witService.witInfor_insert(wVO)==1) System.out.println("DB입력성공!!!");
		 * WitnessVO firstVO=witService.printData();
		 * System.out.println("발견장소>>>>"+request.getSession().getAttribute("addr"));
		 * 
		 * 
		 * String missing_pic=firstVO.getMissing_pic();
		 * 
		 * m.addAttribute("nameList", nameList);
		 * 
		 * m.addAttribute("witInfor_insert",firstVO); return "redirect:/main/main";//저장된
		 * VO위치정보로 마커표시하기, ajax로 wit_info.jsp띄어서 등록한 목격정보 띄우기 // 목격마커 숨기기(실종마커 클릭했을때
		 * missing_no와 일치하는 목격마커 띄우기) } catch (Exception e) { e.printStackTrace(); }
		 */
		return "redirect:/main";

	}// fileUp

}
