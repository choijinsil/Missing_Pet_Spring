package beans.missing.persistence;

import java.util.List;

import beans.missing.domain.UserVO;

public interface AdminDAO {
	public UserVO select_update_info(String id);
	
	public List<UserVO> select_user_info(int page);

}
