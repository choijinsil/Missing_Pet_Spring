package beans.missing.persistence;

import java.util.List;
import java.util.Map;

import beans.missing.domain.PetVO;

public interface UserDAO {
	
	public List<PetVO> pet_list();
	
	public boolean select_user(Map<String, String> map);
}
