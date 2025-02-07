package br.edu.iff.PackNow.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.PackNow.model.Funcionario;
import br.edu.iff.PackNow.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/funcionario")
public class FuncionarioRestController {
	@Autowired
	public FuncionarioService FuncionarioServ;
	
	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar um funcionário")
	public String addFuncionario(String nome, String telefone, String cpf,  String funcao) throws Exception{
		return FuncionarioServ.addFuncionario(new Funcionario(nome,telefone, cpf, funcao));
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um funcionário")
	public String atualizarFuncionario(@PathVariable("id") long id, String nome, String telefone, String cpf, String cargo) throws Exception{
		Funcionario fBusca = FuncionarioServ.getFuncionarioById(id);
		if(fBusca == null) {
			return "Funcionário não encontrado";
		} else {
			return FuncionarioServ.atualizarFuncionario(id, nome, telefone, fBusca.getCpf(), cargo);
		}
	}
	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar um funcionário")
	public String deletarFuncionarioCPF(@PathVariable("id") Long id) throws Exception {
		Funcionario fBusca = FuncionarioServ.getFuncionarioById(id);
		if(fBusca==null) {			
			return "Funcionario não encontrado";
		}else {
			return FuncionarioServ.deletarFuncionarioCPF(fBusca.getCpf());
		}
	}
	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar todos os funcionários")
	public List<Funcionario> listarFuncionarios() throws Exception {
		return FuncionarioServ.listarFuncionarios();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um funcionário")
	public Funcionario buscarFuncionarioId(@PathVariable("id") Long id) throws Exception {
		return FuncionarioServ.getFuncionarioById(id);
	}
	
}
