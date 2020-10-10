package com.backmonstrao.controller;

import com.backmonstrao.domain.Transcao;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionsControllerTest {

    @Test
    void mustReturnTransactions() {
        TransactionsController controller = new TransactionsController();

        ResponseEntity<List<Transcao>> expected = ResponseEntity.ok(List.of(getTranscao()));

        ResponseEntity<List<Transcao>> transacoes = controller.getTransacoes();

        assertNotNull(transacoes);
        assertEquals(expected, transacoes);
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