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
}
