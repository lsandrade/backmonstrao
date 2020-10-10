package com.backmonstrao.controller;

import com.backmonstrao.domain.Transcao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionsController {

    @GetMapping
    public ResponseEntity<List<Transcao>> getTransacoes() {
        Transcao transacao = getTranscao();

        return ResponseEntity.ok(List.of(transacao));
    }

    private Transcao getTranscao() {
        Transcao transacao = new Transcao();
        transacao.setDescricao("descricao");
        transacao.setData(999L);
        transacao.setValor(9999);
        transacao.setDuplicated(false);
        return transacao;
    }
}
