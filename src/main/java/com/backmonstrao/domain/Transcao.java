package com.backmonstrao.domain;

import lombok.Data;

@Data
public class Transcao {
    private String descricao;
    private long data;
    private Integer valor;
    private boolean duplicated;
}
