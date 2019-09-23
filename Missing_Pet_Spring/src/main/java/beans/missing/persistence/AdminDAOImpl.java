package beans.missing.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.missing.domain.UserVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public UserVO select_update_info(String id) {
		return sqlSession.selectOne("admin.select_update_info", id);
	}

	@Override
	public List<UserVO> select_user_info(int page) {
		//sqlSession.selectList("admin.select_user_info", 10 * (page - 1), 10)
		return sqlSession.selectList("admin.select_user_info",page);
	}

}
