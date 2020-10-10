package com.backmonstrao.service;

import com.backmonstrao.domain.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {

    private TransactionService service;

    @BeforeEach
    void setUp() {
        service = new TransactionService();
    }

    @Test
    void mustReturnTransactionsWhenValidIdIsPassed() {
        int id = 1000;

        List<Transacao> expected = List.of(getTransacao());

        List<Transacao> transacoes = service.getTransacoes(id);

        assertNotNull(transacoes);
        assertEquals(1, transacoes.size());
        assertEquals(expected, transacoes);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 100000001})
    void mustReturnNullWhenInvalidIdIsPassed(int id) {
        List<Transacao> transacoes = service.getTransacoes(id);

        assertEquals(0, transacoes.size());
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
