package beans.missing.service;

import java.util.List;

import beans.missing.domain.PetVO;

public interface PetService {
	
	public PetVO select_pet(int no);
	
	public List<String> wit_markerList(int no);
	
	public boolean register(PetVO vo);
}
