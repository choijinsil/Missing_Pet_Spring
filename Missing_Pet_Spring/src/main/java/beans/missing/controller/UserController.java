package beans.missing.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import beans.missing.service.UserService;

@Controller
@RequestMapping("/main")
public class UserController {
	
	@Autowired
	UserService service;
	
	String loginId;
	
	//메인페이지
	@GetMapping("")
	public String main(Model m) {
		m.addAttribute("list",service.pet_list());
		return "/common/main";
	}
	
	//회원가입폼
	@GetMapping("/join")
	public String joinForm() {
		return "/common/join";
	}
	
	//로그인폼
	@GetMapping("/login")
	public String loginForm() {
		return "/common/login";
	}
	
	//로그인
	@PostMapping("/login")
	public String login(HttpServletRequest request, Model m) {
		loginId = request.getParameter("id");
		String pass = request.getParameter("pass");

		Map<String, String> map = new HashMap<String, String>(); // sql문에 전달할 값
			map.put("id", loginId);
			map.put("pass", pass);

//		if (service.select_user(map) && "N".equals(service.select_black_user(loginId))) {
//			// id, pass가 맞고 블랙리스트값이 N인 경우 --로그인 성공!
//			request.getSession().setAttribute("loginId", loginId);
//			response.sendRedirect("/main?action=main");
//		}
		
		if(service.select_user(map)) {
			request.getSession().setAttribute("loginId", loginId);
			return "redirect:/main";
		}
		
		return "/common/login";
	}
	
	
	
	
}
