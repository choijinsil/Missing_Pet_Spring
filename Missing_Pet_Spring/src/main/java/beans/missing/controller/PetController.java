package beans.missing.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import beans.missing.domain.PetVO;
import beans.missing.service.PetService;


@Controller
@RequestMapping("/pet")
public class PetController {
	

	@Autowired
	PetService service;
	
	// 실종동물등록폼
	@GetMapping("/register")
	public String registerForm() {
		return "/user/register_pet";
	}

	// 실종동물등록
	@PostMapping("/register")
	public String register(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest mtfRequest) {
		
		String id = (String) request.getSession().getAttribute("loginId");
		String place = request.getParameter("missing_place");
		String date = request.getParameter("missing_date");
		String time = request.getParameter("missing_time");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date to = null;
			try {
				to = fm.parse(date +" " +time);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		
		String comment = request.getParameter("comment");
		String tip = request.getParameter("tip");
		String type = request.getParameter("type");
		
		Iterator<String> itr=mtfRequest.getFileNames(); //파일들을 iterator에 넣음
		
		List list = new ArrayList();
		while(itr.hasNext()) {//파일을 하나씩 불러온다
			MultipartFile mpf = mtfRequest.getFile(itr.next());
				list.add(mpf.getOriginalFilename());
		}
		
		String images = "";
		for(int i=0; i<list.size();i++) {
			if(i == list.size()-1) {
				images += list.get(i);
			}else {
				images += list.get(i) +",";
			}
		}
		
		PetVO vo = new PetVO(0,id,images,null,place,to,type,comment,tip,null,null);

		if (service.register(vo)) {
			return "redirect:/main";
		}
		
		return "/user/register_pet";
	}

	@GetMapping("/map/{no}")
	public String map(@PathVariable("no") int no, Model m, HttpServletRequest request) {
		

		List<String> latLng = service.wit_markerList(no);

		for(int i=0;i<latLng.size();i++) { //List에 다음페이지에서 key값 "positions"와 "number"를 이용해서 value값을 찾게끔 저장
			latLng.set(i, "{\"positions\":new kakao.maps.LatLng("+latLng.get(i)+"),\"number\":"+(i+1)+"}");
		}
		
		m.addAttribute("vo", service.select_pet(no));
		request.getSession().setAttribute("latLng", latLng);
		

		return "/user/map";
	}
	

	@GetMapping("/register_upform")
	public String register_upform(int missing_no, HttpSession session) {
		session.setAttribute("vo", service.select_pet(missing_no));
		return "/user/register_upform";
	}

	@PostMapping("/register_update")
	public String register_update(int missing_no, HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest mtfRequest) {
		
		String place = request.getParameter("missing_place");
		String date = request.getParameter("missing_date");
		String time = request.getParameter("missing_time");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date to = null;
			try {
				to = fm.parse(date +" " +time);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		
		String comment = request.getParameter("comment");
		String tip = request.getParameter("tip");
		String type = request.getParameter("type");
		
		Iterator<String> itr=mtfRequest.getFileNames(); //파일들을 iterator에 넣음
		
		List list = new ArrayList();
		while(itr.hasNext()) {//파일을 하나씩 불러온다
			MultipartFile mpf = mtfRequest.getFile(itr.next());
				list.add(mpf.getOriginalFilename());
		}
		
		
		String saveFileName1 = list.get(0).toString();
		String saveFileName2 = list.get(1).toString();
		String saveFileName3 = list.get(2).toString();

		String hidden_pic1 = request.getParameter("hidden_pic1");
		String hidden_pic2 = request.getParameter("hidden_pic2");
		String hidden_pic3 = request.getParameter("hidden_pic3");
		
		if (saveFileName1 == "" && hidden_pic1.length() > 0)
			saveFileName1 = hidden_pic1; 
		if (saveFileName2 == "" && hidden_pic2.length() > 0)
			saveFileName2 = hidden_pic2;
		if (saveFileName3 == "" && hidden_pic3.length() > 0)
			saveFileName3 = hidden_pic3;


		String nameList = null;

		if (saveFileName2 == null && saveFileName3 == null) {
			nameList = saveFileName1;

		} else if (saveFileName3 == null) {
			nameList = saveFileName1 + "," +saveFileName2;

		} else {
			nameList = saveFileName1 + "," +saveFileName2 + "," +saveFileName3;
		}		

		
		PetVO vo = new PetVO(missing_no,null,nameList,null,place,to,type,comment,tip,null,null);
		
		if(service.update_pet_info(vo)) {
			return "redirect: /main/user_mypost";
		}
		return "/pet/register_upform";
	}

}
