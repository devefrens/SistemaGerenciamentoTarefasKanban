package com.anderson.SistemaGerenciamentoTarefasKanban.repository;

import com.anderson.SistemaGerenciamentoTarefasKanban.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
