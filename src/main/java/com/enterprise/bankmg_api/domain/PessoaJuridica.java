package com.enterprise.bankmg_api.domain;


import com.enterprise.bankmg_api.util.DocumentoValidator;

import java.util.List;

public class PessoaJuridica implements Participante {

    private final String cnpj;
    private final List<BemImovel> bens;
    private final List<Participante> socios;

    public PessoaJuridica(String cnpj, List<BemImovel> bens, List<Participante> socios) {
        DocumentoValidator.validarCnpj(cnpj);
        this.cnpj = DocumentoValidator.somenteDigitos(cnpj);
        this.bens = bens;
        this.socios = socios;
    }

    @Override
    public String getDocumento() {
        return cnpj;
    }

    @Override
    public List<BemImovel> getBens() {
        return bens;
    }

    @Override
    public List<Participante> getSocios() {
        return socios;
    }
}
