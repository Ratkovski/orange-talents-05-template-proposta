package br.com.zupacademy.ratkovski.proposta.repository;

import br.com.zupacademy.ratkovski.proposta.modelo.Carteira;
import br.com.zupacademy.ratkovski.proposta.modelo.TipoCarteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    Optional<Carteira> findByCarteiraAndCartaoId(TipoCarteira carteira, String id );
}
