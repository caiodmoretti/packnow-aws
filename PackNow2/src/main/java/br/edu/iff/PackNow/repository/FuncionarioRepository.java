package br.edu.iff.PackNow.repository;

import org.springframework.stereotype.Repository;

import br.edu.iff.PackNow.model.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	@Query("SELECT f FROM Funcionario f WHERE f.cpf = :cpf")
	Funcionario buscarPeloCPF(@Param("cpf") String cpf);
	@Query("SELECT f FROM Funcionario f WHERE f.id = :id")
	Funcionario buscarPeloId(@Param("id") Long id);

	/*
	@Query(value="SELECT * FROM FUNCIONARIO WHERE CPF = ?1", nativeQuery = true)
	Funcionario buscarPeloCPF(String CPF);
	
	@Query(value="SELECT * FROM FUNCIONARIO WHERE ID = ?1", nativeQuery = true)
	Funcionario BuscarPeloId(Long id);*/

}
