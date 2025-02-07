package br.edu.iff.PackNow.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.PackNow.model.Endereco;
import br.edu.iff.PackNow.model.Morador;
import br.edu.iff.PackNow.service.MoradorService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/morador")
public class MoradorRestController {
	@Autowired
	public MoradorService MoradorServ;
	
	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar um morador")
	public String addMorador(String nome, String cpf, String telefone) throws Exception{
		return MoradorServ.addMorador(new Morador(nome, cpf, telefone));
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um morador")
	public String atualizarMorador(@PathVariable("id") Long id, String nome, String telefone, String cpf, Endereco endereco) throws Exception{
		Morador mBusca = MoradorServ.getMoradorById(id);
		if(mBusca == null) {
			return "Morador não encontrado.";
		} else {
			return MoradorServ.atualizarDadosMorador(id, nome, telefone, cpf, endereco);
		}
	}
	@PutMapping("/{id}/adicionar-morador-endereco")
	@ResponseBody
	@Operation(summary = "Adicionar morador em um endereço")
	public String adicionarMoradorNoEndereco(@PathVariable("id") Long id, Long enderecoId) throws Exception{
		Morador mBusca = MoradorServ.getMoradorById(id);
		if(mBusca == null) {
			return "Morador não encontrado.";
		} else {
			return MoradorServ.adicionarMoradorNoEndereco(id, enderecoId);
		}
	}
	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar um morador")
	public String deletarMoradorCPF(@PathVariable("id") Long id) throws Exception {
		Morador mBusca = MoradorServ.getMoradorById(id);
		if(mBusca==null) {			
			return "Morador não encontrado";
		}else {
			return MoradorServ.deletarMoradorCPF(mBusca.getCpf());
		}
	}
	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar todos os moradores")
	public List<Morador> listarMoradores() throws Exception {
		return MoradorServ.listarMoradores();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um morador")
	public Morador buscarMoradorId(@PathVariable("id") Long id) throws Exception {
		return MoradorServ.getMoradorById(id);
	}
	
}
