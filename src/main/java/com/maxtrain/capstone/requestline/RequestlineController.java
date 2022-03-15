package com.maxtrain.capstone.requestline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;




@CrossOrigin
@RestController
@RequestMapping("/api/requestlines")
public class RequestlineController {

	@Autowired
	private RequestlineRepository reqlRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Requestline>> getRequestlines() {
		var requestlines = reqlRepo.findAll();
		return new ResponseEntity<Iterable<Requestline>>(requestlines, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Requestline> getRequestline(@PathVariable int id) {
		var reqln = reqlRepo.findById(id);
		if(reqln.isEmpty()) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Requestline>(reqln.get(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Requestline> postRequestline(@RequestBody Requestline requestline) throws Exception {
		if(requestline == null || requestline.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var reql = reqlRepo.save(requestline);
		return new ResponseEntity<Requestline>(reql, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putRequestline(@PathVariable int id, @RequestBody Requestline requestline) throws Exception {
		if(requestline == null || requestline.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var reqlOpt = reqlRepo.findById(requestline.getId());
		if(reqlOpt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		reqlRepo.save(requestline);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteRequestline(@PathVariable int id) throws Exception {
		var requestOpt = reqlRepo.findById(id);
		if(requestOpt.isEmpty()) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		var request = requestOpt.get();
		reqlRepo.delete(request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

// Notes:
// CrossOrigin is an annotation that is like to useCords in Startup.cs in prs-server-C35 (the C# prs backend)
// & CrossOrigin makes sure that the frontend can talk to the backend

// RestController signifies that sends & receives JSON data
