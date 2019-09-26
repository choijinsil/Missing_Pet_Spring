package beans.missing.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import beans.missing.domain.WitnessVO;
import beans.missing.persistence.WitnessDAO;

@Service
public class WitServiceImpl implements WitService {

	@Autowired
	WitnessDAO dao;
	
	
	@Override
	public int witInfor_insert(WitnessVO wVO) {
		System.out.println("service insert시작!!!");
		return dao.witInfor_insert(wVO);
	}


	@Override
	public WitnessVO printData() {
		
		return dao.printData();
	}

	

}
