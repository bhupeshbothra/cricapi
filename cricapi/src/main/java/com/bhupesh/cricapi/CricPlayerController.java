package com.bhupesh.cricapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhupesh.cricapi.domain.CricPlayer;
import com.bhupesh.cricapi.domain.ReturnMessage;
import com.bhupesh.cricapi.service.ICricPlayerInfoService;

@RestController
@RequestMapping(value="/cricapi/v1")
@CrossOrigin("*")
public class CricPlayerController {
	
	private ResponseEntity responseEntity;
	
	@Autowired
	ICricPlayerInfoService iCricPlayerInfoService;
	
	@GetMapping(path="/getAllPlayer")
	public ResponseEntity<List<CricPlayer>> getCricPlayerList(){
		
		List<CricPlayer> cricplayerls = iCricPlayerInfoService.getCricPlayerList();

		return new ResponseEntity(cricplayerls, HttpStatus.OK);
	}

	@PostMapping(path="/save")
	public ResponseEntity<List<CricPlayer>> saveCricPlayerList(@RequestBody CricPlayer cricPlayer){
		
		int returncode = iCricPlayerInfoService.saveCricPlayer(cricPlayer);

		ReturnMessage rm = new ReturnMessage();
		
		rm.setCode(returncode+"");
		rm.setMessage("success");
		return new ResponseEntity(rm, HttpStatus.CREATED);
	}
	
	@PostMapping(path="/update")
	public ResponseEntity<List<CricPlayer>> updateCricPlayerList(@RequestBody CricPlayer cricPlayer){
		
		int returncode = iCricPlayerInfoService.updateCricPlayer(cricPlayer);

		ReturnMessage rm = new ReturnMessage();
		
		rm.setCode(returncode+"");
		rm.setMessage("success");
		return new ResponseEntity(rm, HttpStatus.CREATED);
	}
	
	@PostMapping(path="/delete")
	public ResponseEntity<List<CricPlayer>> deleteCricPlayerList(@RequestBody CricPlayer cricPlayer){
		
		int returncode = iCricPlayerInfoService.deleteCricPlayer(cricPlayer);

		ReturnMessage rm = new ReturnMessage();
		
		rm.setCode(returncode+"");
		rm.setMessage("success");
		return new ResponseEntity(rm, HttpStatus.CREATED);
	}

}
