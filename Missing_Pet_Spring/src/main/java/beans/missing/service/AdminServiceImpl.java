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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer select_wit_total_Page() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update_black_info(Map<String, String> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PetVO> select_pet_list(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PetVO> select_wit_list(int page) {
		// TODO Auto-generated method stub
		return null;
	}

}
