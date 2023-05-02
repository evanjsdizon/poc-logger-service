package com.cd.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cd.dto.LogDto;
import com.cd.processor.LoggingProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class LoggingConsumerRoute extends RouteBuilder {

	private static final String ENDPOINT = "kafka:%s?"
			+ "brokers=%s:%s";

	@Value("${app.kafka.topic}")
	private String topic;
	
	@Value("${app.kafka.host}")
	private String host;
	
	@Value("${app.kafka.port}")
	private int port;
	
	@Autowired
	private LoggingProcessor loggingProcessor;
	
	@Override
	public void configure() throws Exception {
		var objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		var dataFormat = new JacksonDataFormat();
		dataFormat.setObjectMapper(objectMapper);
		dataFormat.setUnmarshalType(LogDto.class);
		
		from(String.format(ENDPOINT, topic, host, port))
		.log("Logging Consumer JSON: ${body}")
		.unmarshal(dataFormat)
		.log("Logging Consumer: ${body}")
		.process(loggingProcessor);
	}

}
