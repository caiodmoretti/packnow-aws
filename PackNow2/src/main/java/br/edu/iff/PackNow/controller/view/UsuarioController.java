package br.edu.iff.PackNow.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.PackNow.model.Usuario;

/*
@Controller
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioDetailsService usuarioService;

    public UsuarioController(UsuarioDetailsService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro() {
        return "usuario/cadastro"; // Retorna o nome do arquivo HTML do formulário de cadastro
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute("usuario") String login, String senha, String permissao, Model model) {
        usuarioService.salvar(login,senha,permissao);
        model.addAttribute("message", "Usario registrado com sucesso.");
        return "success";
    }
}

*/
