package com.beleza.api_postgres.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beleza.api_postgres.model.Pessoa;
import com.beleza.api_postgres.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaService.listarPessoa();
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> buscarPessoa(@PathVariable Long id) {
        return pessoaService.buscarPessoa(id);
    }

    @PostMapping
    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaService.salvarPessoa(pessoa);
    }

    @DeleteMapping("/{id}")
    public void excluirPessoa(@PathVariable Long id) {
        pessoaService.excluirPessoa(id);
    }
}
