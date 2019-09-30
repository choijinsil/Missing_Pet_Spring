package beans.missing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import beans.missing.domain.Criteria;
import beans.missing.domain.PageDTO;
import beans.missing.domain.PetVO;
import beans.missing.domain.UserVO;
import beans.missing.service.AdminService;
import beans.missing.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@Autowired
	AdminService adminService;

	@GetMapping("") // main, 회원리스트
	public String admin(HttpServletRequest request, Criteria cri) {

		request.setAttribute("list", adminService.select_user_info(cri));
		int total = adminService.select_user_total_page(cri);
		request.setAttribute("pageMaker", new PageDTO(cri, total));

		return "/admin/user_list";
	}

	@GetMapping("/edit") // 수정폼 회원정보 조회
	public String user_info(String id, HttpSession session) {
		session.setAttribute("user", adminService.select_update_info(id));
		return "/admin/user_detail";
	}

	@PostMapping("/update") // DB수정기능
	public String udpate(String id, String black) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("black", black);

		if (adminService.update_black_info(map)) {
			return "redirect: /admin";
		}
		return "";
	}

	@GetMapping("/delete") // 회원 삭제
	public String delete_user(String id) {
		if (adminService.delete(id)) {
			return "redirect: /admin";
		}
		return "";
	}

	@GetMapping("/pet") // 분실강아지 조회
	public String pet_list(Model m, Criteria cri) {

		int totalPage = adminService.select_pet_total_Page(cri);
		m.addAttribute("list", adminService.select_pet_list(cri));
		m.addAttribute("pageMaker", new PageDTO(cri, totalPage));

		return "/admin/missing_list";
	}

	@GetMapping("/wit")
	public String wit(HttpServletRequest request, Criteria cri) {

		int totalPage = adminService.select_wit_total_Page(cri);
		request.setAttribute("list", adminService.select_wit_list(cri));
		request.setAttribute("pageMaker", new PageDTO(cri, totalPage));// 현재 페이지 (1페이지,2페이지)

		return "/admin/wit_list";
	}

	@GetMapping("/search_user")
	public String search_user(HttpServletRequest request) {
		List<UserVO> list = adminService.search_user(request.getParameter("search_id"));// 리턴이 List
		request.setAttribute("list", list);

		return "/admin/user_list";
	}
}
