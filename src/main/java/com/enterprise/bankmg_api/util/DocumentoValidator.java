package com.enterprise.bankmg_api.util;

public final class DocumentoValidator {

    private static final int[] PESOS_DV1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_DV2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private DocumentoValidator() {
    }

    public static String somenteDigitos(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Documento não pode ser nulo");
        }
        return valor.replaceAll("\\D", "");
    }

    public static void validarCpf(String cpf) {
        String digits = somenteDigitos(cpf);

        if (digits.length() != 11 || todosDigitosIguais(digits)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        int dv1 = calcularDvCpf(digits, 9);
        int dv2 = calcularDvCpf(digits, 10);

        if (dv1 != digits.charAt(9) - '0' || dv2 != digits.charAt(10) - '0') {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    private static int calcularDvCpf(String cpf, int tamanho) {
        int soma = 0;
        int peso = tamanho + 1;

        for (int i = 0; i < tamanho; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }

        int mod = soma % 11;
        return (mod < 2) ? 0 : 11 - mod;
    }

    public static void validarCnpj(String cnpj) {
        String digits = somenteDigitos(cnpj);

        if (digits.length() != 14 || todosDigitosIguais(digits)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        int dv1 = calcularDvCnpj(digits, PESOS_DV1);
        int dv2 = calcularDvCnpj(digits, PESOS_DV2);

        if (dv1 != digits.charAt(12) - '0' || dv2 != digits.charAt(13) - '0') {
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }

    private static int calcularDvCnpj(String cnpj, int[] pesos) {
        int soma = 0;

        for (int i = 0; i < pesos.length; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos[i];
        }

        int mod = soma % 11;
        return (mod < 2) ? 0 : 11 - mod;
    }

    private static boolean todosDigitosIguais(String valor) {
        char primeiro = valor.charAt(0);

        for (int i = 1; i < valor.length(); i++) {
            if (valor.charAt(i) != primeiro) {
                return false;
            }
        }
        return true;
    }
}
