package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import br.com.caelum.ingresso.model.descontos.DescontoEstudante;
import br.com.caelum.ingresso.model.descontos.DescontoTrintaPorCentoParaBancos;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public class DescontoTest {
	
	Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
	
	Filme filme = new Filme("Rogue ONE", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
	
	Sessao sessao = new Sessao(LocalTime.now(), sala, filme);
	
	Ingresso ingresso;
	
	BigDecimal precoEsperado;
	
	Lugar lugar = new Lugar("A", 1);
	
	

	@Test
	public void deveConcederDescontoDe30PorCentoParaIngressoDeClientesDeBancos() {
		
		ingresso = new Ingresso(sessao, TipoDeIngresso.BANCO, lugar);
		
		precoEsperado = new BigDecimal("22.75");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe50PorCentoParaIngressoDeEstudante() {
		
		ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);
		
		precoEsperado = new BigDecimal("16.25");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		
		ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);
		
		precoEsperado = new BigDecimal("32.5");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
}
