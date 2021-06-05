package br.com.zupacademy.ratkovski.proposta.repository;

import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}