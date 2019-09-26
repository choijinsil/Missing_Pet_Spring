package beans.missing.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.missing.domain.PetVO;
import beans.missing.domain.UserVO;
import beans.missing.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO dao;

	@Override
	public UserVO select_update_info(String id) {
		return dao.select_update_info(id);
	}

	@Override
	public List<UserVO> select_user_info(int page) {
		return dao.select_user_info(page);
	}

	@Override
	public Integer select_user_total_page() {
		return dao.select_user_total_page();
	}

	@Override
	public Integer select_wit_total_Page() {
		return dao.select_wit_total_Page();
	}

	@Override
	public boolean update_black_info(Map<String, String> map) {
		return dao.update_black_info(map);
	}

	@Override
	public boolean delete(String id) {
		return dao.delete(id);
	}

	@Override
	public List<PetVO> select_pet_list(int page) {
		return dao.select_pet_list(page);
	}

	@Override
	public List<PetVO> select_wit_list(int page) {
		return dao.select_wit_list(page);
	}

	@Override
	public List<UserVO> search_user(String id) {
		return dao.search_user(id);
	}

}
