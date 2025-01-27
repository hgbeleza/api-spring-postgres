package com.beleza.api_postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beleza.api_postgres.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
