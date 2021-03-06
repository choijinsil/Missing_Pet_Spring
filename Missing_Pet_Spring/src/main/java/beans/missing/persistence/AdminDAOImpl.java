package beans.missing.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.missing.domain.Criteria;
import beans.missing.domain.PetVO;
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
	public List<UserVO> select_user_info(Criteria cri) {
		RowBounds bounds = new RowBounds((cri.getPageNum() - 1) * 10, 10);
		return sqlSession.selectList("admin.select_user_info", cri, bounds);
	}

	@Override
	public List<UserVO> search_user(String id) {
		return sqlSession.selectList("admin.search_user", id);
	}

	@Override
	public Integer select_user_total_page(Criteria cri) {
		return sqlSession.selectOne("admin.select_user_total_page");
	}

	@Override
	public Integer select_wit_total_Page(Criteria cri) {
		return sqlSession.selectOne("admin.select_wit_total_Page");
	}

	@Override
	public boolean update_black_info(Map<String, String> map) {
		if (sqlSession.update("admin.update_black_info", map) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if (sqlSession.delete("admin.delete_user_info", id) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<PetVO> select_pet_list(Criteria cri) {
		RowBounds bounds = new RowBounds((cri.getPageNum() - 1) * 10, 10);
		return sqlSession.selectList("admin.select_pet_list",  cri, bounds);
	}

	@Override
	public List<PetVO> select_wit_list(Criteria cri) {
		RowBounds bounds = new RowBounds((cri.getPageNum() - 1) * 10, 10);
		return sqlSession.selectList("admin.select_wit_list",  cri, bounds);
	}

	@Override
	public Integer select_pet_total_Page(Criteria cri) {
		return sqlSession.selectOne("admin.select_pet_total_Page");
	}

}
