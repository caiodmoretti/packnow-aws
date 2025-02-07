package br.edu.iff.PackNow.controller.view;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.PackNow.model.Endereco;
import br.edu.iff.PackNow.model.Morador;
import br.edu.iff.PackNow.service.EnderecoService;
import br.edu.iff.PackNow.service.MoradorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("morador")
public class MoradorController {
	@Autowired
	public MoradorService moradorServ;
	@Autowired
	public EnderecoService enderecoServ;
	

	    public MoradorController(MoradorService moradorService, EnderecoService enderecoService) {
	        this.moradorServ = moradorService;
	        this.enderecoServ = enderecoService;
	    }
	   @GetMapping("/adicionar")
	    public String mostrarFormulario(Model model) {
	        model.addAttribute("morador", new Morador());
	        model.addAttribute("enderecos", enderecoServ.listarEnderecos()); // Carrega os endereços existentes
	        return "morador/adicionar-morador";
	    }
	    @PostMapping("/registrar")
	    public String salvarMorador(@Valid @ModelAttribute("morador") Morador morador, @RequestParam("enderecoId") Long enderecoId, BindingResult result, Model model) {
	        Endereco endereco = enderecoServ.getEnderecoById(enderecoId);
	        morador.setEndereco(endereco); // Associando o endereço existente ao morador
	        moradorServ.addMorador(morador);
	        model.addAttribute("message", "Morador registrado com sucesso.");
	        return "success";
	    }
	@GetMapping("/listar")
	public String listarMoradores(Model model) {
		model.addAttribute("morador_lista", moradorServ.listarMoradores());
		return "morador/visualizar-moradores";
	}
	@GetMapping("/delete/{cpf}")
	public String deleteMorador(@PathVariable("cpf") String cpf, Model model) {
		try {	    
			moradorServ.deletarMoradorCPF(cpf);
		    model.addAttribute("message", "Morador deletado com sucesso.");
		    return "success";
				
		} catch (Exception e) {
		    model.addAttribute("message", "Não foi possível deletar o morador.");
		    return "error";
		}

	}
	@GetMapping("/edit/{id}")
	public String showUpdateMoradorForm(@PathVariable("id") Long id, Model model) {
	    Morador morador = moradorServ.getMoradorById(id);
	    model.addAttribute("morador", morador);
        model.addAttribute("enderecos", enderecoServ.listarEnderecos());
	    return "morador/editar-morador";
	}
	@PostMapping("/update/{id}")
	public String updateMorador(@Valid @PathVariable("id") Long id, Morador morador, Endereco endereco, Model model) {
		String cpf = morador.getCpf();
		String nome = morador.getNome();
		String telefone = morador.getTelefone();
		moradorServ.atualizarDadosMorador(id, nome,  telefone, cpf, endereco);
		model.addAttribute("message", "Morador atualizado com sucesso.<br> nome: " + morador.getNome() +"<br>CPF: " + morador.getCpf());
		return "success";
	}
}
