package com.backmonstrao.service;

import com.backmonstrao.domain.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GeneratorServiceTest {

    public static final int VALID_YEAR = 2020;
    public static final int MIN_VALUE = -9999999;
    public static final int MAX_VALUE = 9999999;
    public static final int MIN_SIZE_DESC = 10;
    public static final int MAX_SIZE_DESC = 120;

    GeneratorService generatorService;

    @BeforeEach
    void setUp() {
        generatorService = new GeneratorService();
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void mustGenerateDataBetweenFirstAndLastDay(int month) {

        long start = getDateInMiliseconds(VALID_YEAR, month, 1, 0, 0, 0, 0);
        long end = getDateInMiliseconds(VALID_YEAR, month, 28, 23, 59, 59, 999);

        long dateGenerated = generatorService.generateData(VALID_YEAR, month, 1);

        assertTrue(dateGenerated >= start);
        assertTrue(dateGenerated <= end);
    }

    @Test
    void mustGenerateValueBetweenRange() {
        Integer value1 = generatorService.generateValor( VALID_YEAR + 1);
        Integer value2 = generatorService.generateValor( MAX_VALUE);

        assertTrue(value1 >= MIN_VALUE);
        assertTrue(value1 <= MAX_VALUE);

        assertTrue(value2 >= MIN_VALUE);
        assertTrue(value2 <= MAX_VALUE);

        assertNotEquals(value1, value2);
    }

    @Test
    void mustGenerateDescricao() {
        String descricao1 = generatorService.generateDescricao(1);
        String descricao2 = generatorService.generateDescricao(2000);

        assertTrue(descricao1.length() >= MIN_SIZE_DESC);
        assertTrue(descricao1.length() <= MAX_SIZE_DESC);

        assertTrue(descricao2.length() >= MIN_SIZE_DESC);
        assertTrue(descricao2.length() <= MAX_SIZE_DESC);

        assertNotEquals(descricao1, descricao2);
    }

    @Test
    void mustGenerateNonDuplicatedTransactions() {
        int month = 10;
        int id = 1000;
        int quant = 2;

        List<Transacao> transacoes = generatorService.getTransacoes(id, VALID_YEAR, month, quant, false);

        assertEquals(quant, transacoes.size());
        assertFalse(transacoes.get(0).isDuplicated());
        assertNotEquals(transacoes.get(0), transacoes.get(1));
    }

    @Test
    void mustGenerateDuplicatedTransactions() {
        int month = 10;
        int id = 1000;
        int quant = 2;

        List<Transacao> transacoes = generatorService.getTransacoes(id, VALID_YEAR, month, quant, true);

        assertEquals(quant, transacoes.size());

        assertFalse(transacoes.get(0).isDuplicated());
        assertTrue(transacoes.get(1).isDuplicated());

        assertThat(transacoes.get(0))
                .isEqualToIgnoringGivenFields(transacoes.get(1),
                        "duplicated");

        assertNotEquals(transacoes.get(0), transacoes.get(1));
    }

    private long getDateInMiliseconds(int year, int month, int day, int hour, int minute, int second, int mili) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, mili);

        return calendar.getTimeInMillis();
    }


}