package com.enterprise.bankmg_api.domain;

import java.util.List;

public interface Participante {

    String getDocumento();

    List<BemImovel> getBens();

    List<Participante> getSocios();
}
