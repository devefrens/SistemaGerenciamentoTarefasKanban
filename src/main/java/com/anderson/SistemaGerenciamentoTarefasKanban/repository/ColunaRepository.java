package com.anderson.SistemaGerenciamentoTarefasKanban.repository;

import com.anderson.SistemaGerenciamentoTarefasKanban.model.Coluna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColunaRepository extends JpaRepository<Coluna, Long> {
    Coluna findByNome(String nome); // Busca uma coluna pelo nome
}
