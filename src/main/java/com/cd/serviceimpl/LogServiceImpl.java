package com.cd.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.dto.LogDto;
import com.cd.entity.Log;
import com.cd.repository.LogRepository;
import com.cd.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;
	
	@Override
	public void addLog(LogDto dto) {
		logRepository.save(new Log(dto.getDatetime(), dto.getEvent(), dto.getMessage()));
	}

}
