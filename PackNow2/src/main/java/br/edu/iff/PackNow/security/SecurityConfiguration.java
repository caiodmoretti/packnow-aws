package br.edu.iff.PackNow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

/*
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/**","/morador/listar","/encomenda/listar","/endereco/listar","/error","/success").permitAll()
				.requestMatchers("/cargo/**", "/h2-console/**", "/swagger-ui/**")
				.hasRole("")
				.requestMatchers("/css/**","/img/**","/js/**").permitAll()
				.requestMatchers("/usuario/cadastro","/usuario/registrar").permitAll()// Permitir acesso ao formulário de cadastro
				.requestMatchers("/error.html","success.html").permitAll()// Permitir acesso ao formulário de cadastro
				.anyRequest().authenticated())
				.formLogin((form) -> form.permitAll())
				.logout((logout) -> logout.logoutSuccessUrl("/").permitAll());
		return http.build();/*
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/register");
    }

    }
}
*/