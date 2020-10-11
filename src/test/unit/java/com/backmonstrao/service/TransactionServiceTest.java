package com.backmonstrao.service;

import com.backmonstrao.domain.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    public static final int VALID_ID = 1000;
    public static final int VALID_MONTH = 10;
    public static final int VALID_YEAR = 2020;

    private TransactionService service;

    @Mock
    GeneratorService generator;

    @BeforeEach
    void setUp() {
        service = new TransactionService(generator);
    }

    @Test
    void mustReturnTransactionsWhenValidIdDataIsPassed() {
        List<Transacao> expected = List.of(getTransacao());

        when(generator.getTransacoes(anyInt(), anyInt(), anyInt(), anyInt(), anyBoolean())).thenReturn(List.of(getTransacao()));

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

    @ParameterizedTest
    @MethodSource("provideMonthAndQuant")
    void mustReturnMultipleTransactionsAccordingToIdAndMonth(int month, int quant) {
        List<Transacao> transacoes = service.getTransacoes(VALID_ID, VALID_YEAR, month);

        verify(generator, times(1))
                .getTransacoes(eq(VALID_ID), eq(VALID_YEAR), eq(month), eq(quant), anyBoolean());
    }

    private static Stream<Arguments> provideMonthAndQuant() {
        return Stream.of(
                Arguments.of(1, 2),
                Arguments.of(2, 3),
                Arguments.of(9, 10),
                Arguments.of(10, 1),
                Arguments.of(11, 2),
                Arguments.of(12, 3)
        );
    }

    private Transacao getTransacao() {
        Transacao transacao = new Transacao();
        transacao.setDescricao("paçoquita");
        transacao.setData(999L);
        transacao.setValor(9999);
        transacao.setDuplicated(false);
        return transacao;
    }

}
