package com.dado.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dado.api.dto.DiceDTO;
import com.dado.api.service.DadoService;

@RestController
public class DadoController {
	
	
	@Autowired
	private DadoService service;
	
	@PostMapping("/throw")
	public ResponseEntity<DiceDTO> getTest() {
		return new ResponseEntity<>(service.throwDice(),HttpStatus.OK);
	}
	
	@GetMapping("/dices")
	public ResponseEntity<DiceDTO> getDiceValue(){
		return new ResponseEntity<>(service.getDiceValue(),HttpStatus.OK);
	}
	
	

}
