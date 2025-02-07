package br.edu.iff.PackNow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.PackNow.model.Encomenda;
import br.edu.iff.PackNow.model.Morador;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {
	
	@Query("SELECT e FROM Encomenda e WHERE e.id = :id")
	Encomenda buscarPeloId(@Param("id") Long id);
	@Query("SELECT e FROM Encomenda e WHERE e.moradorRetirada IS NULL")
	List<Encomenda> buscarEncomendasNaoRetiradas(@Param("moradorRetirada") Morador moradorRetirada);

/*
	@Query(value="SELECT * FROM ENCOMENDA WHERE ID = ?1", nativeQuery = true)
	Encomenda buscarPeloId(Long id);
	
	@Query(value="SELECT * FROM ENCOMENDA WHERE moradorRetirada = NULL ", nativeQuery = true)
	Encomenda buscarEncomendasNaoRetiradas(Morador moradorRetirada);
*/

}
