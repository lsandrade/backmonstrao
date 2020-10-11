package com.backmonstrao.service;

import com.backmonstrao.domain.Transacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class GeneratorService {

    public static final int MIN_VALUE = -9999999;
    public static final int MAX_VALUE = 9999999;

    public static final String[] SUJEITOS = {"dinheiro", "camisa", "bermuda", "sunga", "televisao", "colchao", "comida"};
    public static final String[] VERBOS = {"sumiu", "pegou fogo", "me ameaçou", "ganhou", "correu", "desenvolveu", "comprou"};
    public static final String[] PREDICADOS = {"ontem", "com fome", "terça feira", "depois de amanhã", "na minha mochila", "pra praia"};

    public long generateData(int year, int month, int seed) {
        long start = getDateInMiliseconds(year, month, 1, 0, 0, 0, 0);
        long end = getDateInMiliseconds(year, month, 28, 23, 59, 59, 999);

        return getRandomLong(seed, start, end);
    }

    public Integer generateValor(int seed) {
        return Math.toIntExact(getRandomLong(seed, MIN_VALUE, MAX_VALUE));
    }

    public String generateDescricao(int seed) {
        String sujeito = SUJEITOS[seed % SUJEITOS.length];
        String verbo = VERBOS[seed % VERBOS.length];
        String predicado = PREDICADOS[seed % PREDICADOS.length];
        return String.format("%s %s %s", sujeito, verbo, predicado);
    }

    public List<Transacao> getTransacoes(int id, int year, int month, int quant, boolean duplicated) {

        List<Transacao> transacaos = IntStream.range(0, quant)
                .mapToObj(i -> getTransacao(id, year, month, i + id + year + month))
                .collect(Collectors.toList());

        if (quant > 1 && duplicated) {
            transacaos.set(transacaos.size() - 1,
                    getDuplicatedTransacao(getTransacao(id, year, month, id + year + month)));
        }

        return transacaos;
    }

    private Transacao getDuplicatedTransacao(Transacao transacao) {
        transacao.setDuplicated(true);
        return transacao;
    }

    private long getRandomLong(int seed, long start, long end) {
        Random random = new Random(seed);
        return start + Math.abs(random.nextLong()) % (end - start);
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

    private Transacao getTransacao(int id, int year, int month, int seed) {

        Transacao transacao = new Transacao();
        transacao.setDescricao(generateDescricao(seed));
        transacao.setData(generateData(year, month, seed));
        transacao.setValor(generateValor(seed));
        transacao.setDuplicated(false);
        return transacao;
    }


}
