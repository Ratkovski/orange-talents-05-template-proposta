package br.com.zupacademy.ratkovski.proposta.repository;

import br.com.zupacademy.ratkovski.proposta.modelo.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    /** faz busca para ver se tem documentos iguais **/
    boolean existsByDocumento( String documento);

    @Query(value = "select * from proposta where status = 'ELEGIVEL' and cartao_id is null",nativeQuery = true)
    List<Proposta> findAllElegivel();

    Optional<Proposta> findByUuid(String uuid);

  /*  @Query(value = "select p from Proposta p where p.statusProposta =:status and p.cartao_id is null")
    List<Proposta>findAllElegivel(StatusProposta status);
*/

}
