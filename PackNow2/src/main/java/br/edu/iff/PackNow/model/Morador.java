package br.edu.iff.PackNow.model;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Morador extends Pessoa {


	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Endereco endereco;

//    @OneToMany(mappedBy="moradorRetirada")
//    private List<Encomenda> encomendas;
    
    
	public Morador(String nome, String telefone, String cpf) {
		super(nome, telefone, cpf);	
	}
	public Morador() {}
	

	public void adicionarMoradorNoEndereco(Endereco endereco) {
		if(endereco == null) {
			throw new IllegalArgumentException("Endereço não pode ser nulo.");
		}
		this.endereco = endereco;
		
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	

}
