package com.maxtrain.capstone.request;

import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer>{
	Iterable<Request> findByStatusAndUserId(String status, int userId);
}
