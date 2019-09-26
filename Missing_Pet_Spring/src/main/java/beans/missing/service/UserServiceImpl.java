package beans.missing.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.missing.domain.PetVO;
import beans.missing.domain.UserVO;
import beans.missing.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;

	@Override
	public List<PetVO> pet_list() {
		return dao.pet_list();
	}

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
}
