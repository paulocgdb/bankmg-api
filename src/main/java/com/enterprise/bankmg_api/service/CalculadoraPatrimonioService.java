package com.enterprise.bankmg_api.service;

import com.enterprise.bankmg_api.domain.BemImovel;
import com.enterprise.bankmg_api.domain.Participante;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CalculadoraPatrimonioService {

    public BigDecimal calcularTotal(Participante empresa) {

        Set<String> participantesVisitados = new HashSet<>();
        Set<String> bensContabilizados = new HashSet<>();

        BigDecimal total = BigDecimal.ZERO;

        Deque<Participante> fila = new ArrayDeque<>();
        fila.add(empresa);

        while (!fila.isEmpty()) {

            Participante atual = fila.poll();

            if (!participantesVisitados.add(atual.getDocumento())) {
                continue;
            }

            for (BemImovel bem : atual.getBens()) {
                if (bensContabilizados.add(bem.getId())) {
                    total = total.add(bem.getValor());
                }
            }

            fila.addAll(atual.getSocios());
        }

        return total;
    }
}
