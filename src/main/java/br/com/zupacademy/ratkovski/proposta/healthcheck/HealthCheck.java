package br.com.zupacademy.ratkovski.proposta.healthcheck;


import feign.FeignException;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;

import java.util.HashMap;
import java.util.Map;

public class HealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        Map<String, Object> details = new HashMap<String, Object>();
        details.put("version", "v1.0");
        details.put("descrição", "Health Check");
        details.put("hospedado", "localhost:9999/");
        details.put("serviço", "localhost:9999/api/solicitacao");

        try {
/*precisa melhorar*/
            return Health.status(Status.UP.getCode()).withDetails(details).build();
        } catch (FeignException e) {
            return Health.down(e).withDetails(details).build();
           // return Health.status(Status.DOWN).withDetails(details).build();
        }
    }

    }



