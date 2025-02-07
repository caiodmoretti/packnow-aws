package br.edu.iff.PackNow.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class MainControllerView {
	@GetMapping("/")
	public String cruds(){
		return "home";
	}
	@GetMapping("/pagina1")
	public String teste(){
		return "pagina1";
	}
	@GetMapping("visualizar-encomendas")
	public String visualizarEncomendas(){
		return "visualizar-encomendas";
	}
	@GetMapping("visualizar-moradores")
	public String visualizarMoradores(){
		return "visualizar-moradores";
	}
	@GetMapping("visualizar-enderecos")
	public String visualizarEnderecos(){
		return "visualizar-enderecos";
	}
	@GetMapping("adicionar-encomenda")
	public String adicionarEncomenda(){
		return "adicionar-encomenda";
	}
	@GetMapping("registrar-saida")
	public String registraSaida(){
		return "registrar-saida";
	}
	@GetMapping("adicionar-morador")
	public String adicionarMorador(){
		return "adicionar-morador";
	}
	/*@GetMapping("adicionar-endereco")
	public String adicionarEndereco(){
		return "adicionar-endereco";
	}*/
}
