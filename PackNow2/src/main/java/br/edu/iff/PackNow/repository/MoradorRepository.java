package br.edu.iff.PackNow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.PackNow.model.Endereco;
import br.edu.iff.PackNow.model.Morador;
@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long> {
	@Query("SELECT m FROM Morador m WHERE m.cpf = :cpf")
	Morador buscarPeloCPF(@Param("cpf") String cpf);

	@Query("SELECT m FROM Morador m WHERE m.id = :id")
	Morador buscarPeloId(@Param("id") Long id);	
	
	
	/*@Query(value="SELECT * FROM MORADOR WHERE ID = ?1", nativeQuery = true)
	Morador buscarPeloId(Long id);*/
}
