package beans.missing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import beans.missing.domain.PetVO;
import beans.missing.domain.UserVO;
import beans.missing.persistence.AdminDAO;
import beans.missing.persistence.UserDAO;
import beans.missing.service.AdminService;
import beans.missing.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@Autowired
	AdminService adminService;

	@GetMapping("")
	public String admin(HttpServletRequest request) {
		String pageNo = request.getParameter("page");

		// 페이지 요청 파라미터 얻어오기
		int page;
		if (pageNo == null) {
			page = 1; // 페이지 요청이 없었을 시 기본페이지를 1페이지로 하겠음.
		} else {
			page = Integer.parseInt(pageNo);
		}

		List<UserVO> list = adminService.select_user_info(page);

//		int totalPage = adminService.select_user_total_page();

		request.setAttribute("list", list);
		request.setAttribute("page", page);// 현재 페이지 (1페이지,2페이지)
//		request.setAttribute("totalPage", totalPage);// 전체페이지 수

		return "/admin/user_list";
	}

	@GetMapping("/edit")
	public String user_info(String id, HttpSession session) {
		session.setAttribute("user", adminService.select_update_info(id));
		return "/admin/user_detail";
	}

	@PostMapping("/wit")
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
		request.setAttribute("totalPage", totalPage);// 전체페이지 수

		return "/admin/wit_list";
	}
}
