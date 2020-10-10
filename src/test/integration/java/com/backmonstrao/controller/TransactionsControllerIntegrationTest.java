package com.backmonstrao.controller;


import com.backmonstrao.domain.Transacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionsControllerIntegrationTest {

    @Autowired
    private TransactionsController controller;

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void mustReturnTransaction() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        int id = 1000;

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/" + id + "/transacoes/",
                String.class)).contains(mapper.writeValueAsString(getTransacao()));
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
