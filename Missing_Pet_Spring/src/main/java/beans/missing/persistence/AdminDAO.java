package beans.missing.persistence;

import java.util.List;
import java.util.Map;

import beans.missing.domain.Criteria;
import beans.missing.domain.PetVO;
import beans.missing.domain.UserVO;

public interface AdminDAO {
	public UserVO select_update_info(String id);

	public List<UserVO> select_user_info(Criteria cri);

	public List<UserVO> search_user(String id);

	// 분실 강아지 정보 조회
	public List<PetVO> select_pet_list(Criteria cri);

	// 신고 강아지 정보 조회
	public List<PetVO> select_wit_list(Criteria cri);

	public Integer select_user_total_page(Criteria cri);// 실종동물 토탈페이지

	public Integer select_wit_total_Page(Criteria cri);// 목격동물 토탈

	// 회원정보 수정
	public boolean update_black_info(Map<String, String> map);

	// 회원 삭제
	public boolean delete(String id);

	public Integer select_pet_total_Page(Criteria cri);

}
