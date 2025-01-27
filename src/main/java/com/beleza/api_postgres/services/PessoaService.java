package com.beleza.api_postgres.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beleza.api_postgres.model.Pessoa;
import com.beleza.api_postgres.repository.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarPessoa() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPessoa(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void excluirPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}
