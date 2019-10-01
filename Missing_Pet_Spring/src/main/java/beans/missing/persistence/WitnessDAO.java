package beans.missing.persistence;

import java.util.List;

import beans.missing.domain.WitnessVO;

public interface WitnessDAO {

	public List<WitnessVO> select_mywit(String id);

	public int delete_mywit(int no);

	public int witInfor_insert(WitnessVO wVO);

	public WitnessVO printData();

	public String latLng(String missing_no);
}
