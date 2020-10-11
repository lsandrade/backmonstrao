package com.backmonstrao.controller;

import com.backmonstrao.domain.Transacao;
import com.backmonstrao.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TransactionsController {

    private TransactionService service;

    @Autowired
    public TransactionsController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/{id}/transacoes/{year}/{month}/")
    public ResponseEntity<List<Transacao>> getTransacoes(@PathVariable int id,
                                                         @PathVariable int year,
                                                         @PathVariable int month) {
        return ResponseEntity.ok(service.getTransacoes(id, year, month));
    }

}
