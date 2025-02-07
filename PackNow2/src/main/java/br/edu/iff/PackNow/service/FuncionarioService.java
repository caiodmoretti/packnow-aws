package br.edu.iff.PackNow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.PackNow.model.Funcionario;
import br.edu.iff.PackNow.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository FuncionarioRep;
	
	public String addFuncionario(Funcionario funcionario) {
		if(FuncionarioRep.buscarPeloCPF(funcionario.getCpf())!=null) {
			return "Funcionário já foi cadastrado.";
		}else {
			Funcionario f = FuncionarioRep.save(funcionario);
			return "Funcionário registrado no id " + f.getId();
		}
	}
	public Funcionario getFuncionarioById(Long id) {
		Funcionario f = FuncionarioRep.buscarPeloId(id);
		if(f==null){
			throw new IllegalArgumentException("Funcionário com o id "+ id + " não foi encontrado.");
		}
		return FuncionarioRep.buscarPeloId(id);
	}
	public Funcionario getFuncionarioByCPF(String cpf) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f==null){
			throw new IllegalArgumentException("Funcionário com o cpf "+ cpf + " não foi encontrado.");
		}
		return FuncionarioRep.buscarPeloCPF(cpf);
	}

	public String deletarFuncionarioCPF(String cpf) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f!=null) {	
			FuncionarioRep.delete(f);
			return "Funcionario com o id "+f.getId()+ " foi deletado.";
		}else {
			return "Funcionario não encontrado.";
		}
	}

	public List<Funcionario> listarFuncionarios() {
		return FuncionarioRep.findAll();
	}
	@Transactional
	public String atualizarFuncionario(Long id, String nome, String telefone, String cpf, String cargo) {
		Funcionario f = FuncionarioRep.buscarPeloId(id);
		if(f==null) {
			return "Funcionario não encontrado.";
		}else {
			if(nome!=null) {
				f.setNome(nome);
			}
			if(telefone!=null) {
				f.setTelefone(telefone);
			}
			if(cpf!=null) {
				f.setCpf(cpf);
			}
			if(cargo!=null) {				
				f.setCargo(cargo);
			}
			FuncionarioRep.flush();
			return "Funcionário com o id "+f.getId()+ " foi atualizado.";
		}
	}


}
