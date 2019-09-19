package beans.missing.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.missing.domain.PetVO;
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

}
