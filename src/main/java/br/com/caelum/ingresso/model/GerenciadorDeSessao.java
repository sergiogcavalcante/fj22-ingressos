package br.com.caelum.ingresso.model;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class GerenciadorDeSessao {
	
	private List<Sessao> sessoesDaSala;

	public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
		this.sessoesDaSala = sessoesDaSala;
	}
	
	public boolean cabe(Sessao sessaoAtual) {
		/*
		for (Sessao sessaoGravada : sessoesDaSala) {
			if (!horarioIsValido(sessaoGravada, sessaoAtual)) {
				return false;
			}
		};
		return true;
		*/
		
		Optional<Boolean> optionalCabe = sessoesDaSala.stream().map(sessaoExistente ->
				horarioIsValido(sessaoExistente, sessaoAtual)).reduce(Boolean::logicalAnd);
		return optionalCabe.orElse(true);
	}
	
	private boolean horarioIsValido(Sessao sessaoExistente, Sessao sessaoAtual) {
		LocalTime horarioSessao = sessaoExistente.getHorario();
		LocalTime horarioAtual = sessaoAtual.getHorario();
		
		boolean ehAntes = horarioAtual.isBefore(horarioSessao);
		
		if (ehAntes) {
			return sessaoAtual.getHorarioTermino().isBefore(horarioSessao);
		}
		
		return sessaoExistente.getHorarioTermino().isBefore(horarioAtual);
	}
	
}
