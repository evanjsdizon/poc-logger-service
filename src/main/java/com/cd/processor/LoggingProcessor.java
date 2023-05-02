package com.cd.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cd.dto.LogDto;
import com.cd.service.LogService;

@Component
public class LoggingProcessor implements Processor {

	@Autowired
	private LogService logService;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		logService.addLog(exchange.getIn().getBody(LogDto.class));
	}

}
