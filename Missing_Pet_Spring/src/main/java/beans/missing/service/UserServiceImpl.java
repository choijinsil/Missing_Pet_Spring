package beans.missing.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.missing.domain.Criteria;
import beans.missing.domain.PetVO;
import beans.missing.domain.UserVO;
import beans.missing.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;

	@Override
	public boolean select_user(Map<String, String> map) {
		return dao.select_user(map);
	}

	@Override
	public boolean insert_user(UserVO vo) {
		return dao.insert_user(vo);
	}

	@Override
	public boolean select_black_user(String login_id) {
		if ("Y".equals(dao.select_black_user(login_id))) {
			return true;
		}
		return false;
	}

	@Override
	public UserVO select_myinfo(String login_id) {
		return dao.select_myinfo(login_id);
	}

	@Override
	public List<PetVO> select_mymissing(String login_id) {
		return dao.select_mymissing(login_id);
	}

	@Override
	public boolean update_myinfo(UserVO user) {
		return dao.update_myinfo(user);
	}

	@Override
	public boolean update_mymissing(int missing_no) {
		return dao.update_mymissing(missing_no);
	}

	@Override
	public List<PetVO> pet_list(Criteria cri) {
		return dao.pet_list(cri);
	}

	@Override
	public List<UserVO> search_user(String id) {
		return null;
	}

	@Override
	public List<PetVO> select_page(Map<String, Integer> map) {
		return null;
	}

	@Override
	public Integer total_page(Criteria cri) {
		return dao.total_page(cri);
	}

	@Override
	public boolean withdraw_user(String id) {
		return false;
	}
}
