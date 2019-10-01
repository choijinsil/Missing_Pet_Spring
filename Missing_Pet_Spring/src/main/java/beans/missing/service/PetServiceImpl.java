package beans.missing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.missing.domain.PetVO;
import beans.missing.domain.WitnessVO;
import beans.missing.persistence.PetDAO;

@Service
public class PetServiceImpl implements PetService{
	
	@Autowired
	PetDAO dao;
	
	@Override
	public PetVO select_pet(int no) {
		return dao.select_pet(no);
	}

	@Override
	public List<String> wit_markerList(int no) {
		return dao.wit_markerList(no);
	}

	@Override
	public boolean register(PetVO vo) {
		return dao.register(vo);
	}

	@Override
	public boolean update_pet_info(PetVO vo) {
		return dao.update_pet_info(vo);
	}

	@Override
	public List<WitnessVO> wit_InforList(int no) {
		
		return dao.wit_InforList(no);
	}
	
	public boolean delete_mymissing(int missing_no) {
		return dao.delete_mymissing(missing_no);
	}

}
