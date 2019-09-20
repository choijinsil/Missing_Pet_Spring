package beans.missing.controller;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
