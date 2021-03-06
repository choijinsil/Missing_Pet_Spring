package beans.missing.service;

import java.util.List;
import java.util.Map;

import beans.missing.domain.Criteria;
import beans.missing.domain.PetVO;
import beans.missing.domain.UserVO;

public interface AdminService {

	public List<UserVO> select_user_info(Criteria cri);

	public Integer select_user_total_page(Criteria cri);// 실종동물 토탈페이지

	public Integer select_wit_total_Page(Criteria cri);

	public Integer select_pet_total_Page(Criteria cri);

	// 업데이트 회원 정보 조회
	public UserVO select_update_info(String id);

	// 회원정보 수정
	public boolean update_black_info(Map<String, String> map);

	// 회원 삭제
	public boolean delete(String id);

	// 분실 강아지 정보 조회
	public List<PetVO> select_pet_list(Criteria cri);

	// 신고 강아지 정보 조회
	public List<PetVO> select_wit_list(Criteria cri);

	public List<UserVO> search_user(String id);

}
