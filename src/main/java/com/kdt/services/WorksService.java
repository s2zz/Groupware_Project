package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.WorksDAO;
import com.kdt.dto.WorkTimesDTO;
import com.kdt.dto.WorksDTO;

@Service
public class WorksService {

	@Autowired
	private WorksDAO wdao;
	
	public List<WorksDTO> select(String id)throws Exception{
		return wdao.select(id);
	}
	public List<WorkTimesDTO> selectby(String id)throws Exception{
		return wdao.selectby(id);
	}
	public int insert(WorkTimesDTO dto)throws Exception{
		return wdao.insert(dto);
	}
	
	public List<WorkTimesDTO> work_inout(String id)throws Exception{
		return wdao.work_inout(id);
	}
}