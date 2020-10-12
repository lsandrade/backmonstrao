package com.backmonstrao.controller;


import com.backmonstrao.domain.Transacao;
import com.backmonstrao.service.TransactionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TransactionsControllerIntegrationTest {

    @Autowired
    private TransactionsController controller;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransactionService service;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void mustReturnTransaction() throws Exception {

        Transacao transacao = getTransacao();

        when(service.getTransacoes(anyInt(), anyInt(), anyInt()))
                .thenReturn(List.of(transacao));

        int port = 8080;
        String baseUrl = "http://localhost:" + port + "/";
        
        int id = 1000;
        int year = 2020;
        int month = 9;
        String url = baseUrl + id + "/transacoes/" + year + "/" + month;

        this.mockMvc
                .perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(1)))
                .andExpect(jsonPath("$[0].descricao", Matchers.is("colchao desenvolveu pra praia")))
                .andExpect(jsonPath("$[0].data", Matchers.is(1602220133067L)))
                .andExpect(jsonPath("$[0].valor", Matchers.is(530101)))
                .andExpect(jsonPath("$[0].duplicated", Matchers.is(false)));

    }

    private Transacao getTransacao() {
        Transacao transacao = new Transacao();
        transacao.setDescricao("colchao desenvolveu pra praia");
        transacao.setData(1602220133067L);
        transacao.setValor(530101);
        transacao.setDuplicated(false);
        return transacao;
    }
}
