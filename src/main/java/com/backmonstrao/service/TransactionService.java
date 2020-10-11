package com.backmonstrao.service;

import com.backmonstrao.domain.Transacao;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.util.Arrays.asList;

@Service
public class TransactionService {

    public static final int MIN_VALUE_ID = 1000;
    public static final int MAX_VALUE_ID = 100000000;
    public static final int NEXT_YEAR = Calendar.getInstance().get(Calendar.YEAR) + 1;
    public static final int MIN_YEAR = 1970;
    public static final int[] VALID_MONTHS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    public List<Transacao> getTransacoes(int id, int year, int month) {

        if (isIdValid(id) && isYearValid(year) && isMonthValid(month)) {
            return List.of(getTransacao());
        }

        return Collections.emptyList();
    }

    private boolean isMonthValid(int month) {
        return asList(VALID_MONTHS).contains(month);
    }

    private boolean isYearValid(int year) {
        return year > MIN_YEAR && year < NEXT_YEAR;
    }

    private boolean isIdValid(int id) {
        return id >= MIN_VALUE_ID && id <= MAX_VALUE_ID;
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