package beans.missing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
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
		String pageNo = request.getParameter("page");

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
	public String pet_list(HttpServletRequest request) {
		String pageNo = request.getParameter("page");
		// 페이지 요청 파라미터 얻어오기
		int page;
		if (pageNo == null) {
			page = 1; // 페이지 요청이 없었을 시 기본페이지를 1페이지로 하겠음.
		} else {
			page = Integer.parseInt(pageNo);
		}

		List<PetVO> list = adminService.select_pet_list(page);

		int totalPage = adminService.select_wit_total_Page();

		request.setAttribute("list", list);
		request.setAttribute("page", page);// 현재 페이지 (1페이지,2페이지)
//		request.setAttribute("totalPage", totalPage);// 전체페이지 수

		return "/admin/missing_list";
	}

	@GetMapping("/wit")
	public String wit(HttpServletRequest request) {
		String pageNo = request.getParameter("page");

		// 페이지 요청 파라미터 얻어오기
		int page;
		if (pageNo == null) {
			page = 1; // 페이지 요청이 없었을 시 기본페이지를 1페이지로 하겠음.
		} else {
			page = Integer.parseInt(pageNo);
		}

		List<PetVO> list = adminService.select_wit_list(page);

		int totalPage = adminService.select_wit_total_Page();

		request.setAttribute("list", list);
		request.setAttribute("page", page);// 현재 페이지 (1페이지,2페이지)
//		request.setAttribute("totalPage", totalPage);// 전체페이지 수

		return "/admin/wit_list";
	}

	@GetMapping("/search_user")
	public String search_user(HttpServletRequest request) {
		List<UserVO> list = adminService.search_user(request.getParameter("search_id"));// 리턴이 List
		request.setAttribute("list", list);

		return "/admin/user_list";
	}
}
