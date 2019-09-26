package beans.missing.service;

import java.util.List;

import beans.missing.domain.WitnessVO;

public interface WitnessService {
	
	public String witInfor_insert(WitnessVO vo);// 목격자가 목격창에서 정보입력-> DB저장

	public WitnessVO printData();

	public List<WitnessVO> select_mywit(String id);

	public int delete_mywit(int no);
}
