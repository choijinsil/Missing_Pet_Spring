package beans.missing.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.missing.domain.PetVO;
import beans.missing.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<PetVO> pet_list() {
		return sqlSession.selectList("user.pet_list");
	}

	@Override
	public boolean select_user(Map<String, String> map) {

		int t = sqlSession.selectOne("user.select_user", map);

		if (t == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insert_user(UserVO vo) {
		if (sqlSession.insert("user.insert_user", vo) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String select_black_user(String login_id) {
		return sqlSession.selectOne("user.select_black_user", login_id);
	}

	@Override
	public UserVO select_myinfo(String login_id) {
		return sqlSession.selectOne("user.select_myinfo", login_id);
	}

	@Override
	public List<PetVO> select_mymissing(String id) {
		return sqlSession.selectList("pet.select_mymissing", id);
	}

	@Override
	public boolean update_myinfo(UserVO user) {
		if (sqlSession.update("user.update_myinfo", user) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update_mymissing(int missing_no) {
		if (sqlSession.update("pet.update_mymissing", missing_no) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<PetVO> pet_list(int page) {
		return null;
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
	public Integer total_page() {
		
		return null;
	}

	@Override
	public boolean withdraw_user(String id) {
		return false;
	}

}
