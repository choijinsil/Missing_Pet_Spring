package beans.missing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.missing.domain.WitnessVO;
import beans.missing.persistence.WitnessDAO;

@Service
public class WitnessServiceImpl implements WitnessService {

	@Autowired
	WitnessDAO dao;

	@Override
	public String witInfor_insert(WitnessVO vo) {
		return dao.witInfor_insert(vo);
	}

	@Override
	public WitnessVO printData() {
		return dao.printData();
	}

	@Override
	public List<WitnessVO> select_mywit(String id) {
		return dao.select_mywit(id);
	}

	@Override
	public int delete_mywit(int no) {
		return dao.delete_mywit(no);
	}

}
