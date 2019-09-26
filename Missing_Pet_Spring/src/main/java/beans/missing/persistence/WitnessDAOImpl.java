package beans.missing.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.missing.domain.WitnessVO;

@Repository
public class WitnessDAOImpl implements WitnessDAO {

	@Autowired
	SqlSession session;
	
	@Override
	public int witInfor_insert(WitnessVO wVO) {
		
		return session.insert("wit.witInfor_insert",wVO);
		
	}

	@Override
	public WitnessVO printData() {
		return session.selectOne("wit.printData");
	}

}
