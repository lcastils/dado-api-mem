package com.dado.api.dto;

public class DiceDTO {

	private String diceValue;

	public DiceDTO() {

	}

	public DiceDTO(String diceValue) {
		this.diceValue = diceValue;
	}

	public String getDiceValue() {
		return diceValue;
	}

	public void setDiceValue(String diceValue) {
		this.diceValue = diceValue;
	}

}
