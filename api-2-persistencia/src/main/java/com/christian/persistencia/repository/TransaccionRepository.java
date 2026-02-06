package com.christian.persistencia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.christian.persistencia.entity.Transaccion;
import com.christian.persistencia.enums.EstatusTransaccion;


@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
	
	Optional<Transaccion> findByIdAndReferencia(Long id, String referencia);
	
	Optional<Transaccion> findByReferencia(String referencia);
	
	@Modifying
	@Query("""
	    UPDATE Transaccion t
	    SET t.estatus = :estatus
	    WHERE t.id = :id AND t.referencia = :referencia
	""")
	int actualizarEstatus(@Param("id") Long id, @Param("referencia") String referencia,
			@Param("estatus") EstatusTransaccion estatus);
}
