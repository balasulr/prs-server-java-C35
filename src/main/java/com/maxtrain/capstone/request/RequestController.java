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
	
	// GetReviews(userId) - Gets requests in "REVIEW" status and not owned by the user with the primary key of id.
	// GET: /api/requests/review/{userId}
	@GetMapping("review/{userId}")
	public ResponseEntity<Iterable<Request>> getRequestsInReview(@PathVariable int userId) {
		var requests = reqRepo.findByStatusAndUserId("REVIEW", userId);
		return new ResponseEntity<Iterable<Request>>(requests, HttpStatus.OK);
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
	
	// Review(request) - Sets the status of the request for the id provided to "REVIEW" unless the total
	// of the request is less than or equal to $50. If so, it sets the status directly to "APPROVED"
	// PUT: /api/requests/5/review
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}/review")
	public ResponseEntity reviewRequest(@PathVariable int id, @RequestBody Request request) {
		var status = (request.getTotal() <= 50) ? "APPROVED" : "REVIEW";
		request.setStatus(status);
		return putRequest(id, request);
	}
	
	// Approve(request) - Sets the status of the request for the id provided to "APPROVED"
	// PUT: /api/requests/5/approve
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}/approve")
	public ResponseEntity approveRequest(@PathVariable int id, @RequestBody Request request) {
		request.setStatus("APPROVED");
		return putRequest(id, request);
	}
	
	// Reject(request) - Sets the status of the request for the id provided to "REJECTED"
	// PUT: /api/requests/5/reject
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}/reject")
	public ResponseEntity rejectRequest(@PathVariable int id, @RequestBody Request request) {
		request.setStatus("REJECTED");
		return putRequest(id, request);
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

// @SuppressWarnings("rawtypes") is there so that can use the ResponseEntity without the <> after the ResponseEntity
