package br.edu.iff.PackNow.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import br.edu.iff.PackNow.model.Funcionario;
import br.edu.iff.PackNow.service.FuncionarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("funcionario")
public class FuncionarioController {
	
	@Autowired
	public FuncionarioService funcionarioServ;
	
	@GetMapping("/adicionar")
	public String addFuncionarioForm(Model model) {
		model.addAttribute("funcionario", new Funcionario());
		return "funcionario/adicionar-funcionario";
	}
	@PostMapping("/registrar")
	public String salvarFuncionario(@Valid @ModelAttribute Funcionario newFuncionario, Model model) {
		funcionarioServ.addFuncionario(newFuncionario);
		System.out.println("Adicionado: " + newFuncionario.getId());
		model.addAttribute("funcionario", new Funcionario());
		model.addAttribute("message", "Funcionário adicionado com sucesso.<br>Nome: " + newFuncionario.getNome() +"<br>CPF: " + newFuncionario.getCpf());
		return "success";
	}
	@GetMapping("/listar")
	public String listarFuncionarios(Model model) {
		model.addAttribute("funcionario_lista", funcionarioServ.listarFuncionarios());
		return "funcionario/visualizar-funcionarios";
	}
	@GetMapping("/edit/{id}")
	public String showUpdateFuncionarioForm(@PathVariable("id") Long id, Model model) {
		Funcionario funcionario = funcionarioServ.getFuncionarioById(id);
		model.addAttribute("funcionario", funcionario);
		return "funcionario/editar-funcionario";
	}
	@PostMapping("/update/{id}")
	public String updateFuncionario(@Valid @PathVariable("id") Long id, Funcionario funcionario, Model model) {
		String nome = funcionario.getNome();
		String cpf = funcionario.getCpf();
		String telefone = funcionario.getTelefone();
		String cargo = funcionario.getCargo();
		funcionarioServ.atualizarFuncionario(id, nome, telefone, cpf, cargo);
		model.addAttribute("message", "Funcionário atualizado com sucesso.<br>Nome: " + funcionario.getNome() +"<br>CPF: " + funcionario.getCpf());
		return "success";
	}
	@GetMapping("/delete/{cpf}")
	public String deleteFuncionario(@PathVariable("cpf") String cpf, Model model) {
		try {	    
			funcionarioServ.deletarFuncionarioCPF(cpf);
		    model.addAttribute("message", "Funcionario deletado com sucesso.");
		    return "success";
				
		} catch (Exception e) {
		    model.addAttribute("message", "Não foi possível deletar o funcionário.");
		    return "error";
		}

	}
}
