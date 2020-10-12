package com.backmonstrao.service;

import com.backmonstrao.domain.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
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
    public static final String INVALID_ID_ERROR_MESSAGE = "ID da transação deve ser um valor entre %s e %s";
    public static final String INVALID_YEAR_ERROR_MESSAGE = "Ei, viajante do tempo. O ano deve ser um inteiro válido entre 1970 e o ano atual";
    public static final String INVALID_MONTH_ERROR_MESSAGE = "O mês deve ser um valor inteiro entre 1 e 12";

    private GeneratorService generator;

    @Autowired
    public TransactionService(GeneratorService generator) {
        this.generator = generator;
    }

    public List<Transacao> getTransacoes(int id, int year, int month) throws Exception {

        if (!isIdValid(id)) {
            throw new Exception(String.format(INVALID_ID_ERROR_MESSAGE, MIN_VALUE_ID, MAX_VALUE_ID));
        }

        if (!isYearValid(year)) {
            throw new Exception(INVALID_YEAR_ERROR_MESSAGE);
        }

        if (!isMonthValid(month)) {
            throw new Exception(INVALID_MONTH_ERROR_MESSAGE);
        }

        int quant = (id + month) % 10 + 1;
        boolean duplicated = month % 4 == 0;
        return generator.getTransacoes(id, year, month, quant, duplicated);


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

}
