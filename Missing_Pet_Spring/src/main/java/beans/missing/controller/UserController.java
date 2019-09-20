package beans.missing.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import beans.missing.domain.UserVO;
import beans.missing.service.UserService;

@Controller
@RequestMapping("/main")
public class UserController {

	@Autowired
	UserService service;

	String login_id;

	// 메인페이지
	@GetMapping("")
	public String main(Model m) {
		m.addAttribute("list", service.pet_list());
		return "/common/main";
	}

	// 회원가입폼
	@GetMapping("/join")
	public String joinForm() {
		return "/common/join";
	}

	// 회원가입
	@PostMapping("/join")
	public String join(UserVO vo) {
		if (service.insert_user(vo)) {
			return "redirect: /main/login";
		} else {
			System.out.println("회원가입 실패");
		}
		return "/common/join";
	}

	// 로그인폼
	@GetMapping("/login")
	public String loginForm() {
		return "/common/login";
	}

	// 로그인
	@PostMapping("/login")
	public String login(HttpServletRequest request, Model m) {
		login_id = request.getParameter("id");
		String pass = request.getParameter("pass");

		Map<String, String> map = new HashMap<String, String>(); // sql문에 전달할 값
		map.put("id", login_id);
		map.put("pass", pass);

		if (service.select_user(map) && !service.select_black_user(login_id)) {
			request.getSession().setAttribute("loginId", login_id);
			return "redirect: /main";
		} else {

			// 로그인 실패시 블랙리스트다. 모달창으로 변경 예정
			if (service.select_black_user(login_id)) {
				System.out.println("블랙리스트 입니다.");
			} else { // 아디나 비번이 틀렸다.
				System.out.println("아디, 비번을 틀렸어요.");
			}
			return "redirect: /main/login";
		}
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect: /main";
	}

}
