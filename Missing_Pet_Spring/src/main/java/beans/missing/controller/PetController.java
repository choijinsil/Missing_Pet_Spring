package beans.missing.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import beans.missing.domain.PetVO;
import beans.missing.service.PetService;


@Controller
@RequestMapping("/pet")
public class PetController {
	
	@Autowired
	PetService service;
	
	//실종동물등록폼
	@GetMapping("/register")
	public String registerForm() {
		return "/user/register_pet";
	}
	
	//실종동물등록
	@PostMapping("/register")
	public String register(HttpServletRequest request) {
		System.out.println(request.getParameter("type"));
		return "";
		
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
		
		String images = "/images/1.jpg,/images/2.jpg,/images/3.jpg";
		
		PetVO vo = new PetVO(0,id,images,null,place,to,type,comment,tip,null,null);

		if (service.register(vo)) {
			return "redirect:/main";
		}
		
		return "/user/register_pet";
	}

	@GetMapping("/map/{no}")
	public String map(@PathVariable("no") int no, Model m, HttpServletRequest request) {
		
		List<String> latLng = service.wit_markerList(no);
		System.out.println(latLng);
		for(int i=0;i<latLng.size();i++) { //List에 다음페이지에서 key값 "positions"와 "number"를 이용해서 value값을 찾게끔 저장
			latLng.set(i, "{\"positions\":new kakao.maps.LatLng("+latLng.get(i)+"),\"number\":"+(i+1)+"}");
		}
	
		m.addAttribute("vo", service.select_pet(no));
		request.getSession().setAttribute("latLng", latLng);
		
		return "/user/map";
	}
	
}
