package com.cd.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.cd.entity.Log;

@EnableScan
public interface LogRepository extends CrudRepository<Log, String> {

}
