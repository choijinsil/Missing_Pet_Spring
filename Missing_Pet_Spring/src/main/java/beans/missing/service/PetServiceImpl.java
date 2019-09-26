package beans.missing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.missing.domain.PetVO;
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

}
