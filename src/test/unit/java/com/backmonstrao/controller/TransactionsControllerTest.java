package com.backmonstrao.controller;

import com.backmonstrao.domain.Transacao;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionsControllerTest {

    @Test
    void mustReturnTransactions() {
        TransactionsController controller = new TransactionsController();

        ResponseEntity<List<Transacao>> expected = ResponseEntity.ok(List.of(getTransacao()));

        ResponseEntity<List<Transacao>> transacoes = controller.getTransacoes();

        assertNotNull(transacoes);
        assertEquals(expected, transacoes);
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