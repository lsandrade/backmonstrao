package com.backmonstrao.service;

import com.backmonstrao.domain.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {

    public static final int VALID_ID = 1000;
    public static final int VALID_MONTH = 10;
    public static final int VALID_YEAR = 2020;

    private TransactionService service;

    @BeforeEach
    void setUp() {
        service = new TransactionService();
    }

    @Test
    void mustReturnTransactionsWhenValidIdDataIsPassed() {
        List<Transacao> expected = List.of(getTransacao());

        List<Transacao> transacoes = service.getTransacoes(VALID_ID, VALID_YEAR, VALID_MONTH);

        assertNotNull(transacoes);
        assertEquals(1, transacoes.size());
        assertEquals(expected, transacoes);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 100000001})
    void mustReturnEmptyListWhenInvalidIdIsPassed(int id) {
        List<Transacao> transacoes = service.getTransacoes(id, VALID_YEAR, VALID_MONTH);

        assertEquals(0, transacoes.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {1969, 2021})
    void mustReturnEmptyListWhenInvalidYearIsPassed(int year) {
        List<Transacao> transacoes = service.getTransacoes(VALID_ID, year, VALID_MONTH);

        assertEquals(0, transacoes.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 13})
    void mustReturnEmptyListWhenInvalidMonthIsPassed(int month) {
        List<Transacao> transacoes = service.getTransacoes(VALID_ID, VALID_YEAR, month);

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