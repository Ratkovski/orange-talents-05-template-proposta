package br.com.zupacademy.ratkovski.proposta.repository;

import br.com.zupacademy.ratkovski.proposta.modelo.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao,Long> {
}
