package com.enterprise.bankmg_api.domain;


import com.enterprise.bankmg_api.util.DocumentoValidator;

import java.util.List;

public class PessoaFisica implements Participante {

    private final String cpf;
    private final List<BemImovel> bens;

    public PessoaFisica(String cpf, List<BemImovel> bens) {
        DocumentoValidator.validarCpf(cpf);
        this.cpf = DocumentoValidator.somenteDigitos(cpf);
        this.bens = bens;
    }

    @Override
    public String getDocumento() {
        return cpf;
    }

    @Override
    public List<BemImovel> getBens() {
        return bens;
    }

    @Override
    public List<Participante> getSocios() {
        return List.of();
    }
}
