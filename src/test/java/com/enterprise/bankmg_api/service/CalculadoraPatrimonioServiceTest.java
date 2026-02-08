package com.enterprise.bankmg_api.service;

import com.enterprise.bankmg_api.domain.BemImovel;
import com.enterprise.bankmg_api.domain.Participante;
import com.enterprise.bankmg_api.domain.PessoaFisica;
import com.enterprise.bankmg_api.domain.PessoaJuridica;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraPatrimonioServiceTest {

    private final CalculadoraPatrimonioService service = new CalculadoraPatrimonioService();

    @Test
    void deveCalcularPatrimonioSimples() {
        BemImovel bem1 = new BemImovel("B1", new BigDecimal("100000.00"));
        PessoaFisica pf = new PessoaFisica("12345678909", List.of(bem1));

        BigDecimal total = service.calcularTotal(pf);

        assertEquals(new BigDecimal("100000.00"), total);
    }

    @Test
    void deveCalcularPatrimonioComSocios() {
        BemImovel bemPf = new BemImovel("B1", new BigDecimal("50000.00"));
        PessoaFisica pf = new PessoaFisica("12345678909", List.of(bemPf));

        BemImovel bemPj = new BemImovel("B2", new BigDecimal("200000.00"));
        PessoaJuridica pj = new PessoaJuridica("12345678000195", List.of(bemPj), List.of(pf));

        BigDecimal total = service.calcularTotal(pj);

        assertEquals(new BigDecimal("250000.00"), total);
    }

    @Test
    void deveEvitarDuplicidadeDeBens() {
        BemImovel bemCompartilhado = new BemImovel("B1", new BigDecimal("100000.00"));
        
        PessoaFisica pf1 = new PessoaFisica("12345678909", List.of(bemCompartilhado));
        PessoaFisica pf2 = new PessoaFisica("98765432100", List.of(bemCompartilhado));

        PessoaJuridica pj = new PessoaJuridica("12345678000195", List.of(), List.of(pf1, pf2));

        BigDecimal total = service.calcularTotal(pj);

        assertEquals(new BigDecimal("100000.00"), total);
    }

    @Test
    void deveEvitarCicloSocietario() {
        BemImovel bemPj1 = new BemImovel("B1", new BigDecimal("100.00"));
        BemImovel bemPj2 = new BemImovel("B2", new BigDecimal("200.00"));

        final Participante[] p2_holder = new Participante[1];

        Participante p1 = new Participante() {
            @Override public String getDocumento() { return "12345678000195"; }
            @Override public List<BemImovel> getBens() { return List.of(bemPj1); }
            @Override public List<Participante> getSocios() { return List.of(p2_holder[0]); }
        };

        Participante p2 = new Participante() {
            @Override public String getDocumento() { return "98765432000100"; }
            @Override public List<BemImovel> getBens() { return List.of(bemPj2); }
            @Override public List<Participante> getSocios() { return List.of(p1); }
        };

        p2_holder[0] = p2;

        BigDecimal total = service.calcularTotal(p1);

        assertEquals(new BigDecimal("300.00"), total);
    }
}
