package br.edu.iff.PackNow.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.PackNow.model.Encomenda;
import br.edu.iff.PackNow.model.Endereco;
import br.edu.iff.PackNow.model.Funcionario;
import br.edu.iff.PackNow.model.Morador;
import br.edu.iff.PackNow.service.EncomendaService;
import br.edu.iff.PackNow.service.EnderecoService;
import br.edu.iff.PackNow.service.FuncionarioService;
import br.edu.iff.PackNow.service.MoradorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("encomenda")
public class EncomendaController {
	@Autowired
	public EncomendaService encomendaServ;
	@Autowired
	public FuncionarioService funcionarioServ;
	@Autowired
	public EnderecoService enderecoServ;
	@Autowired
	public MoradorService moradorServ;
	
	@GetMapping("/listar")
	public String listarEncomendas(Model model) {
		model.addAttribute("encomenda_lista", encomendaServ.listarEncomendas());
		return "encomenda/visualizar-encomendas";
	}
	   @GetMapping("/adicionar")
	    public String mostrarFormulario(Model model) {
	        model.addAttribute("encomenda", new Encomenda());
	        model.addAttribute("funcionarios", funcionarioServ.listarFuncionarios());
	        model.addAttribute("enderecos", enderecoServ.listarEnderecos());

	        return "encomenda/adicionar-encomenda";
	    }
	   @PostMapping("/registrar")
	   public String salvarEncomenda(@Valid @ModelAttribute("encomenda") Encomenda encomenda,@RequestParam("funcionarioId") Long funcionarioId, 
			   @RequestParam("enderecoId") Long enderecoId, @RequestParam("nomeEntregador") String nomeEntregador, 
			   @RequestParam("telefoneEntregador") String telefoneEntregador , Model model ) {
		   encomendaServ.addEncomenda(funcionarioId, enderecoId, nomeEntregador, telefoneEntregador);
		   model.addAttribute("message", "Encomenda registrada com sucesso.");
		   return "success";
	   }
	   @GetMapping("/registrarSaidaForm/{id}")
	   public String showEncomendaSaidaForm(@PathVariable("id") Long id, Model model){
		    Encomenda encomenda = encomendaServ.getEncomendaById(id);
		    model.addAttribute("encomenda", encomenda);
	        model.addAttribute("funcionarios", funcionarioServ.listarFuncionarios());
	        model.addAttribute("moradores", moradorServ.listarMoradores());
		   return "encomenda/registrar-saida";
	   }
	   @PostMapping("/registrarSaida/{id}")
	   public String registraSaida(@PathVariable("id") Long id, @RequestParam("funcionarioId") Long funcionarioId,@RequestParam("moradorId") Long moradorId, Model model) {
		   encomendaServ.registrarSaida(id, funcionarioId, moradorId);
		   model.addAttribute("message", "A saída da encomenda foi registrada com sucesso.");
		   return "success";
	   }
	   
		@GetMapping("/delete/{id}")
		public String deleteEncomenda(@PathVariable("id") Long id, Model model) {
			try {	    
				encomendaServ.deletarEncomendaPorId(id);
			    model.addAttribute("message", "Encomenda deletada com sucesso.");
			    return "success";
					
			} catch (Exception e) {
			    model.addAttribute("message", "Não foi possível deletar o morador.");
			    return "error";
			}

		}
		@GetMapping("/edit/{id}")
		   public String showEncomendaUpdateForm(@PathVariable("id") Long id, Model model){
		    Encomenda encomenda = encomendaServ.getEncomendaById(id);
		    model.addAttribute("encomenda", encomenda);
	        model.addAttribute("funcionarios", funcionarioServ.listarFuncionarios());
	        model.addAttribute("moradores", moradorServ.listarMoradores());
	        model.addAttribute("enderecos", enderecoServ.listarEnderecos());
		   return "encomenda/editar-encomenda";
	   }	
		@PostMapping("/update/{id}")
		public String updateEncomenda(@Valid @PathVariable("id") Long id,
		                              @RequestParam("funcionarioId") Long funcionarioId, 
		                              @RequestParam("enderecoId") Long enderecoId, 
		                              @RequestParam("nomeEntregador") String nomeEntregador, 
		                              @RequestParam("telefoneEntregador") String telefoneEntregador, 
		                              @RequestParam("funcionarioSaidaId") Long funcionarioSaidaId,
		                              @RequestParam("moradorId") Long moradorId,
		                              Model model) {
		    Funcionario funcionarioEntrada = funcionarioServ.getFuncionarioById(funcionarioId);
		    Funcionario funcionarioSaida = funcionarioServ.getFuncionarioById(funcionarioSaidaId);
		    Endereco endereco = enderecoServ.getEnderecoById(enderecoId);
		    Morador moradorRetirada = moradorServ.getMoradorById(moradorId);
		    encomendaServ.atualizarEncomenda(id, funcionarioEntrada, endereco, nomeEntregador,
		            telefoneEntregador, funcionarioSaida, moradorRetirada);
		    model.addAttribute("message", "Encomenda atualizada com sucesso.");
		    return "success";
		}

}
