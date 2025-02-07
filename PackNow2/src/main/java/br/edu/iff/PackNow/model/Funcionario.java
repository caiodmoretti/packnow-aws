package br.edu.iff.PackNow.model;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Funcionario extends Pessoa  {
	

	@NotBlank(message = "Cargo não pode ser nulo ou ficar em branco")
    @Size(min = 1, max = 30, message = "O cargo deve ter entre 1 e 30 caracteres.")
    @Column(length = 30)
	private String cargo;
    
//    @OneToMany(mappedBy="funcionarioEntrada", cascade = CascadeType.ALL)
//    private List<Encomenda> encomendas;
		
	public Funcionario(String nome, String telefone, String cpf, String cargo) {
		super(nome, telefone, cpf);

		this.setCargo(cargo);	
	}
	public Funcionario(){
		super();
	};
	/**
	 * @param the cargo to set
	 */
	public void setCargo(String cargo) {
		if(cargo.trim().isEmpty() || cargo == null) {
			throw new IllegalArgumentException("Cargo inválido.");
		}
		this.cargo = cargo;
	}
	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	
}
