package beans.missing.service;

import java.util.List;
import java.util.Map;

import beans.missing.domain.PetVO;
import beans.missing.domain.UserVO;

public interface UserService {
	
	public List<PetVO> pet_list();
	
	public boolean select_user(Map<String, String> map);
	
	public boolean insert_user(UserVO vo);
	
	public boolean select_black_user(String login_id);
}
