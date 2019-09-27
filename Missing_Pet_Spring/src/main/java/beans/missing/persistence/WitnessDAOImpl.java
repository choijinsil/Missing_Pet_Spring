package beans.missing.persistence;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.missing.domain.WitnessVO;

@Repository
public class WitnessDAOImpl implements WitnessDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public int witInfor_insert(WitnessVO wVO) {

		return sqlSession.insert("wit.witInfor_insert", wVO);

	}

	@Override
	public WitnessVO printData() {
		return sqlSession.selectOne("wit.printData");
	}

	@Override
	public List<WitnessVO> select_mywit(String id) {
		return sqlSession.selectList("wit.select_mywit", id);
	}

	@Override
	public int delete_mywit(int no) {
		return sqlSession.delete("wit.delete_mywit", no);
	}

}
