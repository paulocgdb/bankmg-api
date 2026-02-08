package com.enterprise.bankmg_api.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentoValidatorTest {

    @Test
    void deveValidarCpfValido() {
        assertDoesNotThrow(() -> DocumentoValidator.validarCpf("123.456.789-09"));
        assertDoesNotThrow(() -> DocumentoValidator.validarCpf("12345678909"));
    }

    @Test
    void deveLancarExcecaoParaCpfInvalido() {
        assertThrows(IllegalArgumentException.class, () -> DocumentoValidator.validarCpf("123.456.789-00"));
        assertThrows(IllegalArgumentException.class, () -> DocumentoValidator.validarCpf("11111111111"));
        assertThrows(IllegalArgumentException.class, () -> DocumentoValidator.validarCpf("123"));
    }

    @Test
    void deveValidarCnpjValido() {
        assertDoesNotThrow(() -> DocumentoValidator.validarCnpj("12.345.678/0001-95"));
        assertDoesNotThrow(() -> DocumentoValidator.validarCnpj("12345678000195"));
    }

    @Test
    void deveLancarExcecaoParaCnpjInvalido() {
        assertThrows(IllegalArgumentException.class, () -> DocumentoValidator.validarCnpj("12.345.678/0001-00"));
        assertThrows(IllegalArgumentException.class, () -> DocumentoValidator.validarCnpj("00000000000000"));
        assertThrows(IllegalArgumentException.class, () -> DocumentoValidator.validarCnpj("123"));
    }

    @Test
    void deveRetornarSomenteDigitos() {
        assertEquals("12345678909", DocumentoValidator.somenteDigitos("123.456.789-09"));
    }

    @Test
    void deveLancarExcecaoParaDocumentoNulo() {
        assertThrows(IllegalArgumentException.class, () -> DocumentoValidator.somenteDigitos(null));
    }
}
