package com.enterprise.bankmg_api.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class BemImovel {

    private final String id;
    private final BigDecimal valor;

    public BemImovel(String id, BigDecimal valor) {
        this.id = Objects.requireNonNull(id);
        this.valor = Objects.requireNonNull(valor);

        if (valor.signum() <= 0) {
            throw new IllegalArgumentException("Valor do bem deve ser positivo");
        }
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
