package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {

	@Override
	public BigDecimal aplicaDesconto(BigDecimal precoOriginal) {
		return precoOriginal;
	}

	@Override
	public String getDescricão() {
		return "Normal";
	}

}
