package com.enterprise.bankmg_api.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DomainTest {

    @Test
    void bemImovelDeveValidarValorPositivo() {
        assertDoesNotThrow(() -> new BemImovel("1", new BigDecimal("100.00")));
        assertThrows(IllegalArgumentException.class, () -> new BemImovel("2", new BigDecimal("0")));
        assertThrows(IllegalArgumentException.class, () -> new BemImovel("3", new BigDecimal("-10")));
    }

    @Test
    void pessoaFisicaDeveValidarCpf() {
        assertDoesNotThrow(() -> new PessoaFisica("12345678909", List.of()));
        assertThrows(IllegalArgumentException.class, () -> new PessoaFisica("123", List.of()));
    }

    @Test
    void pessoaJuridicaDeveValidarCnpj() {
        assertDoesNotThrow(() -> new PessoaJuridica("12345678000195", List.of(), List.of()));
        assertThrows(IllegalArgumentException.class, () -> new PessoaJuridica("123", List.of(), List.of()));
    }
}
