package beans.missing.service;

import java.util.List;

import beans.missing.domain.PetVO;
import beans.missing.domain.WitnessVO;

public interface PetService {
	

	public PetVO select_pet(int no);
	

	public List<String> wit_markerList(int no);
	

	public boolean register(PetVO vo);

	
	public boolean update_pet_info (PetVO vo);

	
	public List<WitnessVO> wit_InforList(int no);

	
	public boolean delete_mymissing(int missing_no);

}
