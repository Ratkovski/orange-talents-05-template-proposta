package br.com.zupacademy.ratkovski.proposta.healthcheck;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Component
public class HealthCheck implements HealthIndicator {

    @Value("${health.proposta.url}")
    private String urlSaude;

    @Value("${health.proposta.porta}")
    private int porta;

    @Override
    public Health health() {
        Map<String, Object> details = new HashMap<String, Object>();
        details.put("version", "v1.0");
        details.put("descrição", "Health Check");
        details.put("hospedado", "localhost:9999/");
        details.put("URL", urlSaude + "/api/solicitacao");

        try (Socket socket = new Socket(new java.net.URL(urlSaude + "/api/solicitacao").getHost(), porta)) {
            /*precisa melhorar*/
            // return Health.status(Status.UP.getCode()).withDetails(details).build();
        } catch (Exception e) {
            return Health.down().withDetail("error", e.getMessage()).build();

            // return Health.down().withDetails(details).build();
            // return Health.status(Status.DOWN).withDetails(details).build();
        }
        return Health.up().withDetails(details).build();
    }

    }







