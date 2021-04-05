package com.dado.api.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.dado.api.dto.DiceDTO;
import com.dado.api.entity.DadoEntity;
import com.dado.api.repository.DadoRepository;
import com.dado.api.service.DadoService;

@Service
public class DadoServiceImpl implements DadoService {

	private SecureRandom rand = new SecureRandom();
	private static final int MAX = 6;
	private static final int MIN = 1;

	@Autowired
	private DadoRepository dadoRepository;

	@Override
	public DiceDTO throwDice() {
		String diceValue = getByteArray(rand.nextInt(MAX - MIN + 1) + MIN);
		saveDice(diceValue);
		return new DiceDTO(diceValue);
	}

	private void saveDice(String diceValue) {
		Optional<DadoEntity> optDado = dadoRepository.findById(1L);
		DadoEntity entity = null;
		if (optDado.isPresent()) {
			entity = optDado.get();
			entity.setDiceValue(diceValue);
			dadoRepository.save(entity);
		} else {
			entity = new DadoEntity();
			entity.setDiceValue(diceValue);
			dadoRepository.save(entity);

		}

	}

	private String getByteArray(Integer number) {
		Resource resource = new ClassPathResource("face" + number + ".jpg");

		InputStream fnew = null;

		BufferedImage originalImage;
		byte[] imageInByte = null;
		try {
			resource.getFile();
			fnew = resource.getInputStream();
			originalImage = ImageIO.read(fnew);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			imageInByte = baos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return Base64.getEncoder().encodeToString(imageInByte);
	}

	@Override
	public DiceDTO getDiceValue() {
		DiceDTO diceReturn = new DiceDTO();
		Optional<DadoEntity> dado = dadoRepository.findById(1L);
		if(dado.isPresent()) {
			diceReturn.setDiceValue(dado.get().getDiceValue());
		}
		return diceReturn;
	}

}
