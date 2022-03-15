package com.maxtrain.capstone.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/requests")
public class RequestController {
	@Autowired
	private RequestRepository reqRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Request>> getRequests() {
		var requests = reqRepo.findAll();
		return new ResponseEntity<Iterable<Request>>(requests, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Request> getRequest(@PathVariable int id) {
		var req = reqRepo.findById(id);
		if(req.isEmpty()) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Request>(req.get(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Request> postRequest(@RequestBody Request request) {
		if(request == null || request.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var req = reqRepo.save(request);
		return new ResponseEntity<Request>(req, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putRequest(@PathVariable int id, @RequestBody Request request) {
		if(request == null || request.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var req = reqRepo.findById(request.getId());
		if(req.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		reqRepo.save(request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteRequest(@PathVariable int id) {
		var req = reqRepo.findById(id);
		if(req.isEmpty()) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		reqRepo.delete(req.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
// Notes:
// CrossOrigin is an annotation that is like to useCords in Startup.cs in prs-server-C35 (the C# prs backend)
// & CrossOrigin makes sure that the frontend can talk to the backend

// RestController signifies that sends & receives JSON data
