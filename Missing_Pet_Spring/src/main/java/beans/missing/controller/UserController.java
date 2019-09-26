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
import beans.missing.service.WitnessService;

@Controller
@RequestMapping("/main")
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	WitnessService wit_service;

	String login_id;

	// 메인페이지, 페이징
	@GetMapping("")
	public String main(Model m, HttpServletRequest request) {
		m.addAttribute("list", service.pet_list());

//		String pageNo = request.getParameter("page");
//		int page;
//
//		if (pageNo == null) {
//			page = 1;
//		} else {
//			page = Integer.parseInt(pageNo);
//		}
//
//		request.getSession().setAttribute("list", dao.pet_list(page));
//		request.getSession().setAttribute("page", page);// 현재 페이지 수
//
//		// 총 페이지 구하기
//		int totalPage = dao.total_page();
//		request.getSession().setAttribute("totalPage", totalPage);// 전체 페이지 수

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

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect: /main";
	}

	@GetMapping("/user_mypage")
	public String user_mypage(Model m) {
		m.addAttribute("userlist", service.select_myinfo(login_id));
		m.addAttribute("missinglist", service.select_mymissing(login_id));

		return "/user/mypage";
	}

	@PostMapping("/update_myinfo") // 나의 정보 수정
	public String update_myinfo(UserVO user, HttpSession session) {

		service.update_myinfo(user);
		session.setAttribute("userlist", service.select_myinfo(login_id));
		session.setAttribute("missinglist", service.select_mymissing(login_id));

		return "redirect: /main/user_mypage";
	}

	@GetMapping("/update_mymissing") // 회원 실종신고 정보 수정
	public String update_mymissing(int missing_no, HttpSession session) {

		if (service.update_mymissing(missing_no)) {
			System.out.println("인계정보 수정 완료");
		} else {
			System.out.println("인계정보 수정 실패");
		}

		session.setAttribute("userlist", service.select_myinfo(login_id));
		session.setAttribute("missinglist", service.select_mymissing(login_id));
		return "redirect: /main/user_mypage";
	}
	
	@GetMapping("/user_mypost") // 게시글 보기
	public String user_mypost(Model m) {
		// 내게시글 목록
		m.addAttribute("missinglist", service.select_mymissing(login_id));

		// 내 목격 글 목록 조회
		m.addAttribute("witlist",  wit_service.select_mywit(login_id));

		// FORWARD이동
		return "/user/mypost";

	}

}
