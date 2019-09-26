package beans.missing.persistence;

import java.util.List;
import java.util.Map;

import beans.missing.domain.PetVO;
import beans.missing.domain.UserVO;

public interface UserDAO {

	public List<PetVO> pet_list();

	public boolean select_user(Map<String, String> map);

	public boolean insert_user(UserVO vo);

	public String select_black_user(String login_id);

	public UserVO select_myinfo(String login_id);// 마이페이지

	// <!--MYPAGE.JSP 회원MISSING정보조회 -->
	public List<PetVO> select_mymissing(String id);

	// <!--MYPAGE.JSP 회원정보수정 -->
	public boolean update_myinfo(UserVO user);

	// <!--MYPAGE.JSP 회원MISSING귀가처리 -->
	public boolean update_mymissing(int missing_no);

	// main.jsp에 실종 동물 리스트 select
	public List<PetVO> pet_list(int page);

	public List<UserVO> search_user(String id);

	// main페이지 페이징
	public List<PetVO> select_page(Map<String, Integer> map);

	// main페이지 페이징
	public Integer total_page();

	public boolean withdraw_user(String id);
}
