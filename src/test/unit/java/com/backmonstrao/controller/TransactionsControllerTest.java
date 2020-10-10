package com.backmonstrao.controller;

import com.backmonstrao.domain.Transacao;
import com.backmonstrao.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionsControllerTest {

    @Mock
    TransactionService service;

    @Test
    void mustReturnTransactions() {
        TransactionsController controller = new TransactionsController(service);
        int id = 1000;

        ResponseEntity<List<Transacao>> expected = new ResponseEntity<>(
                List.of(getTransacao()),
                HttpStatus.OK
        );

        when(service.getTransacoes(eq(id))).thenReturn(List.of(getTransacao()));

        ResponseEntity<List<Transacao>> transacoes = controller.getTransacoes(id);

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