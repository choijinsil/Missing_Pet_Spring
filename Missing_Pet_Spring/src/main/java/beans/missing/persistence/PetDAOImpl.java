package beans.missing.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.missing.domain.PetVO;

@Repository
public class PetDAOImpl implements PetDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public PetVO select_pet(int no) {
		return sqlSession.selectOne("pet.select_pet", no);
	}

	@Override
	public List<String> wit_markerList(int no) {
		return sqlSession.selectList("pet.wit_markerList", no);
	}

	@Override
	public boolean register(PetVO vo) {
		if(sqlSession.insert("pet.register", vo) == 1) {
			return true;
		}
		return false;
	}

}