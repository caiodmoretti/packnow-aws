package br.edu.iff.PackNow.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Endereco implements  Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Não pode ser nulo ou ficar em branco.")
	private String numero;
	

	private String bloco;
	
//    @OneToMany(mappedBy="enderecoEntrega", cascade = CascadeType.PERSIST)
//    private List<Encomenda> encomendas;
	
	public Endereco(String numero, String bloco) {
		this.setNumero(numero);
		this.setBloco(bloco);
	};
	public Endereco() {
	}
	
	/**
	 * @param the numero to set
	 */
	public  void setNumero(String numero) {
		if(numero.trim().isEmpty() || numero == null) {
			throw new IllegalArgumentException("Numero inválido.");
		}
		this.numero = numero;
	};
	/**
	 * @param the bloco to set
	 */
	public  void setBloco(String bloco) {
		if(bloco.trim().isEmpty() || bloco == null) {
			throw new IllegalArgumentException("Numero inválido.");
		}
		this.bloco = bloco;
	};

	public String getNumero() {
		return numero;
	}
	public String getBloco() {
		return bloco;
	}
	public Long getId() {
		return id;
	}



}
