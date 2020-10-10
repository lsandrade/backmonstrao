package com.backmonstrao.controller;

import com.backmonstrao.domain.Transacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionsController {

    @GetMapping
    public ResponseEntity<List<Transacao>> getTransacoes() {
        Transacao transacao = getTransacao();

        return ResponseEntity.ok(List.of(transacao));
    }

    private Transacao getTransacao() {
        Transacao transacao = new Transacao();
        transacao.setDescricao("descricao");
        transacao.setData(999L);
        transacao.setValor(9999);
        transacao.setDuplicated(false);
        return transacao;
    }
}
