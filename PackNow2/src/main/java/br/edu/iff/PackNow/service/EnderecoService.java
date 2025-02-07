package br.edu.iff.PackNow.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.PackNow.model.Endereco;

import br.edu.iff.PackNow.repository.EnderecoRepository;
import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository EnderecoRep;
	
	public String addEndereco(Endereco endereco) {
		if(EnderecoRep.buscarPeloNumeroEBloco(endereco.getNumero(), endereco.getBloco()) != null) {
			return "Endereço já foi cadastrado.";
		}else {
			Endereco e = EnderecoRep.save(endereco);
			EnderecoRep.flush();
			

			
			return "Endereço registrado no id " + e.getId();
		}
	}
	@Transactional
	public String atualizarEndereco(Long id, String numero, String bloco) {
		Endereco e = EnderecoRep.buscarPeloId(id);
		if(e == null) {
			return("Endereço não encontrado");
		}else {
			if(numero == null || bloco == null) {
				throw new IllegalArgumentException("Entrada de dados não pode ser nula.");
			}
			e.setNumero(numero);
			e.setBloco(bloco);
			
			EnderecoRep.flush();
			return "Informações do endereço com id "+e.getId()+ " foram atualizadas";
		}
	}

	public String deletarEndereco(Long id) {
		Endereco e = EnderecoRep.buscarPeloId(id);
		if(e!=null) {	
			EnderecoRep.delete(e);
			return "Endereço com id "+ e.getId() + " foi deletado.";
		}else {
			return "Endereço não encontrado";
		}
	}
	public Endereco getEnderecoById(Long id) {
		if(EnderecoRep.buscarPeloId(id) == null){
			throw new IllegalArgumentException("Endereço com o id "+ id + " não foi encontrado.");
		}
		return EnderecoRep.buscarPeloId(id);
	}
	
	public Endereco getEnderecoByNumeroEBloco(String numero, String bloco) {
		if(EnderecoRep.buscarPeloNumeroEBloco(numero, bloco)== null) {
			throw new IllegalArgumentException("Endereço com o número "+ numero +" e bloco " + bloco +" não foi encontrado.");
		}
		return EnderecoRep.buscarPeloNumeroEBloco(numero, bloco);
	}
	public List<Endereco> listarEnderecos() {
		return EnderecoRep.findAll();
	}
	
	public Set<String> listarBlocos(){
		return EnderecoRep.listarBlocos();
	}
	public Set<String> listarNumerosDoBloco(String bloco){
		return EnderecoRep.listarNumerosDoBloco(bloco);
	}

	public void atualizarEndereco(Endereco endereco) {/*		
	Endereco e = EnderecoRep.buscarPeloNumeroEBloco(numero, bloco);
	    if (e != null) {
	        if (bloco != null) {
	            e.setBloco(bloco);
	        }
	        if (numero != null) {
	            e.setNumero(numero);
	        }
	        EnderecoRep.save(e);
	        EnderecoRep.flush();
	        return "Endereço com Id " + e.getId() + " foi atualizado.";
	    } 
	    else {
	        return "Endereço não encontrado para os parâmetros fornecidos.";
	    }*/
		EnderecoRep.save(endereco);

	}
}
