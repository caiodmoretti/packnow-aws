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
import br.edu.iff.PackNow.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/endereco")
public class EnderecoRestController {
	@Autowired
	public EnderecoService EnderecoServ;
	
	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar um endereco")
	public String addEndereco(String numero, String bloco) throws Exception{
		return EnderecoServ.addEndereco(new Endereco(numero, bloco));
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um endereço")
	public String atualizarEndereco(@PathVariable("id") Long id, String numero, String bloco) throws Exception{
		Endereco eBusca = EnderecoServ.getEnderecoById(id);
		if(eBusca == null) {
			return "Endereco não encontrado";
		} else {
			return EnderecoServ.atualizarEndereco(id, numero, bloco);
		}
	}
	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar um endereço")
	public String deletarEndereco(@PathVariable("id") Long id) throws Exception {
		Endereco eBusca = EnderecoServ.getEnderecoById(id);
		if(eBusca==null) {			
			return "Endereço não encontrado";
		}else {
			return EnderecoServ.deletarEndereco(id);
		}
	}
	
	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar todos os endereços")
	public List<Endereco> listarEnderecos() throws Exception {
		return EnderecoServ.listarEnderecos();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um endereço")
	public Endereco buscarEndereco(@PathVariable("id") Long id) throws Exception {
		return EnderecoServ.getEnderecoById(id);
	}
}
