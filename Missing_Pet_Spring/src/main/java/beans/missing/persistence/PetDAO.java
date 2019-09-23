package beans.missing.persistence;

import java.util.List;

import beans.missing.domain.PetVO;

public interface PetDAO {
	
	public PetVO select_pet(int no);
	
	public List<String> wit_markerList(int no);
	
	public boolean register(PetVO vo);
}