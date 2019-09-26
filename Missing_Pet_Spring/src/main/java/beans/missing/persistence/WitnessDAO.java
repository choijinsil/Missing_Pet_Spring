package beans.missing.persistence;

import java.sql.SQLException;

import beans.missing.domain.WitnessVO;

public interface WitnessDAO {

	public int witInfor_insert(WitnessVO wVO); 
	public WitnessVO printData(); 
}
