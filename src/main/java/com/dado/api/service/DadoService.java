package com.dado.api.service;

import com.dado.api.dto.DiceDTO;

public interface DadoService {
	
	DiceDTO throwDice ();

	DiceDTO getDiceValue(); 

}
