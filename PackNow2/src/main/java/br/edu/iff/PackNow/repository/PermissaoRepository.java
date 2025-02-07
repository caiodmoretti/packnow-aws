package br.edu.iff.PackNow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.iff.PackNow.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao,Long> {
	@Query(value="SELECT * FROM PERMISSAO WHERE NOME = ?1", nativeQuery = true)
	public Permissao getByNome(String nome);
}
