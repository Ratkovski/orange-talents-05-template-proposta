package br.com.zupacademy.ratkovski.proposta.metricas;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;
/*fazer mais metricas*/
@Component
public class MetricasProposta {

    private final MeterRegistry meterRegistry;

    public MetricasProposta (MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void contaPropostaNaoElegivel(){
        counter("proposta_nao_elegivel");
    }
    private void counter(String nomeDaMetrica){
        Counter counter = this.meterRegistry.counter(nomeDaMetrica);
        counter.increment();
    }

}

