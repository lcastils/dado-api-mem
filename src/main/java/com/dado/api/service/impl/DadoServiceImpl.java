package com.dado.api.service.impl;

import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dado.api.dto.DiceDTO;
import com.dado.api.entity.DadoEntity;
import com.dado.api.entity.DadoValueEntity;
import com.dado.api.repository.DadoRepository;
import com.dado.api.repository.DadoValueRepository;
import com.dado.api.service.DadoService;

@Service
public class DadoServiceImpl implements DadoService {

	private SecureRandom rand = new SecureRandom();
	private static final int MAX = 6;
	private static final int MIN = 1;

	@Autowired
	private DadoRepository dadoRepository;

	@Autowired
	private DadoValueRepository dadoValuerepository;

	@Override
	public DiceDTO throwDice() {
		String diceValue = getByteArray(rand.nextInt(MAX - MIN + 1) + MIN);
		System.out.println(diceValue);
		saveDice(diceValue);
		return new DiceDTO(diceValue);
	}

	private void saveDice(String diceValue) {
		DadoEntity entity = new DadoEntity();
		entity.setId(1L);
		entity.setDiceValue(diceValue);
		dadoRepository.save(entity);
	}

	private String getByteArray(Integer number) {
		String response = "";
		Optional<DadoValueEntity> dadoValue = dadoValuerepository.findById(Long.valueOf(number));
		if (dadoValue.isPresent()) {
			response = dadoValue.get().getValue();
		}

		return response;
	}

	@Override
	public DiceDTO getDiceValue() {
		DiceDTO diceReturn = new DiceDTO();
		Optional<DadoEntity> dado = dadoRepository.findById(1L);
		if (dado.isPresent()) {
			diceReturn.setDiceValue(dado.get().getDiceValue());
		}
		return diceReturn;
	}

}
