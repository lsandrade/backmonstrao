package com.backmonstrao.service;

import com.backmonstrao.domain.Transacao;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TransactionService {

    public static final int MIN_VALUE_ID = 1000;
    public static final int MAX_VALUE_ID = 100000000;

    public List<Transacao> getTransacoes(int id) {

        if (id < MIN_VALUE_ID || id > MAX_VALUE_ID) {
            return Collections.emptyList();
        }

        return List.of(getTransacao());
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
