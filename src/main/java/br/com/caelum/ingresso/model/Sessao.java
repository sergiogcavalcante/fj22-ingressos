package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sessao {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Sala sala;
	
	@ManyToOne
	private Filme filme;
	
	private LocalTime horario;

	private BigDecimal preco;
	
	@OneToMany(fetch =FetchType.EAGER, mappedBy= "sessao" )
	private Set<Ingresso> ingressos= new HashSet<Ingresso>();
	 
	/**
	 * deprecated hibernate only
	 */
	public Sessao() {

	}


	public Sessao(LocalTime horario, Sala sala, Filme filme) {
		this.horario = horario;
		this.sala = sala;
		this.setFilme(filme);
		this.preco = sala.getPreco().add(filme.getPreco());
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Sala getSala() {
		return sala;
	}


	public void setSala(Sala sala) {
		this.sala = sala;
	}


	public Filme getFilme() {
		return filme;
	}


	public void setFilme(Filme filme) {
		this.filme = filme;
	}


	public LocalTime getHorario() {
		return horario;
	}


	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
	public LocalTime getHorarioTermino() {
		return this.horario.plus(filme.getDuracao().toMinutes(), ChronoUnit.MINUTES);
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Map<String, List<Lugar>> getMapaDeLugares() {
		return sala.getMapaDeLugares();
	}
	
	public boolean isDisponivel(Lugar lugar) {
		System.out.println(lugar.getPosicao());
		return ingressos.stream().map(Ingresso::getLugar).noneMatch(l -> l.equals(lugar));
	}


	public Set<Ingresso> getIngressos() {
		return ingressos;
	}


	public void setIngressos(Set<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}



}
