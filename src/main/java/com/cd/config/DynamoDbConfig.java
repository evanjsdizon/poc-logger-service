package com.cd.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.cd.repository")
public class DynamoDbConfig {

	@Value("${amazon.dynamodb.endpoint}")
	private String endpoint;
	
	@Value("${amazon.aws.accesskey}")
	private String accessKey;
	
	@Value("${amazon.aws.secretkey}")
	private String secretKey;
	
	@Value("${amazon.aws.region}")
	private String region;
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
				.withEndpointConfiguration(new EndpointConfiguration(endpoint, region))
				.build();
	}
	
	@Bean
	public AWSCredentials awsCredentials() {
		return new BasicAWSCredentials(accessKey, secretKey);
	}
}
