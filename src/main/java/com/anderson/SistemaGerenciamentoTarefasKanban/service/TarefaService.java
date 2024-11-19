package com.anderson.SistemaGerenciamentoTarefasKanban.service;

import com.anderson.SistemaGerenciamentoTarefasKanban.model.Coluna;
import com.anderson.SistemaGerenciamentoTarefasKanban.model.Tarefa;
import com.anderson.SistemaGerenciamentoTarefasKanban.repository.ColunaRepository;
import com.anderson.SistemaGerenciamentoTarefasKanban.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TarefaService {

    // Criação do Logger
    private static final Logger log = LoggerFactory.getLogger(TarefaService.class);

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ColunaRepository colunaRepository;

    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> getTarefaById(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa createTarefa(Tarefa tarefa) {
        if (tarefa.getTitulo() == null || tarefa.getTitulo().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título não pode ser vazio");
        }

        try {
            // Buscar a coluna "A Fazer" e associar à tarefa
            Coluna colunaAFazer = colunaRepository.findByNome("A Fazer");
            if (colunaAFazer == null) {
                // Caso a coluna "A Fazer" não exista, criá-la
                colunaAFazer = new Coluna();
                colunaAFazer.setNome("A Fazer");
                colunaAFazer = colunaRepository.save(colunaAFazer);  // Cria a coluna no banco
            }

            tarefa.setColuna(colunaAFazer); // Definir a coluna "A Fazer" como inicial
            return tarefaRepository.save(tarefa); // Salvar a tarefa no banco de dados
        } catch (DataAccessException e) {
            // Logar e lançar exceção mais específica para problemas no banco
            log.error("Erro ao acessar o banco de dados: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados", e);
        } catch (Exception e) {
            // Logar erro genérico, se necessário
            log.error("Erro ao criar tarefa: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar tarefa", e);
        }
    }


    public Optional<Tarefa> updateTarefa(Long id, Tarefa tarefaModel) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
        if (tarefaExistente.isPresent()) {
            Tarefa tarefaAtualizada = tarefaExistente.get();
            tarefaAtualizada.setTitulo(tarefaModel.getTitulo());
            tarefaAtualizada.setDescricao(tarefaModel.getDescricao());
            tarefaAtualizada.setDataCriacao(tarefaModel.getDataCriacao());
            tarefaAtualizada.setStatus(tarefaModel.getStatus());
            tarefaAtualizada.setPrioridade(tarefaModel.getPrioridade());
            tarefaAtualizada.setDataLimite(tarefaModel.getDataLimite());
            return Optional.of(tarefaRepository.save(tarefaAtualizada));
        }
        return Optional.empty();
    }

    public enum StatusColuna {
        A_FAZER, EM_PROGRESO, CONCLUIDO
    }

    // Método para mover tarefa entre as colunas
    public Tarefa moveTarefa(Long tarefaId, String coluna) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

        Coluna colunaDestino = colunaRepository.findByNome(coluna);

        if (colunaDestino == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Coluna de destino inválida");
        }

        // Regras para mover entre as colunas

//        if ("A Fazer".equals(colunaAtual) && !"Em Progresso".equals(colunaDestino.getNome())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A tarefa deve seguir a ordem A Fazer → Em Progresso → Concluído");
//        } else if ("Em Progresso".equals(colunaAtual) && !"Concluído".equals(colunaDestino.getNome())) { // (tarefa.getColuna().getId() == 2 && !novaColuna.equals("Concluído")) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A tarefa deve seguir a ordem Em Progresso → Concluído"); // throw new IllegalStateException("A tarefa deve seguir a ordem Em Progresso → Concluído");
//        }

        String colunaAtual = tarefa.getColuna().getNome(); // (tarefa.getColuna().getId() == 1 && !novaColuna.equals("Em Progresso"))
        if (StatusColuna.A_FAZER.name().equals(colunaAtual) && !StatusColuna.EM_PROGRESO.name().equals(colunaDestino.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A tarefa deve seguir a ordem A Fazer → Em Progresso → Concluído");
        } else if (StatusColuna.EM_PROGRESO.name().equals(colunaAtual) && !StatusColuna.CONCLUIDO.name().equals(colunaDestino.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A tarefa deve seguir a ordem Em Progresso → Concluído");
        }

        // Atualiza a coluna da tarefa e salva
        tarefa.setColuna(colunaDestino);
        return tarefaRepository.save(tarefa);

    }

    public boolean deleteTarefa(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true; // Retorna true se a tarefa foi deletada
        }
        return false; // Retorna false se a tarefa não foi encontrada
    }

    public List<Tarefa> gerarRelatorio() {
        List<Tarefa> todasTarefas = tarefaRepository.findAll();
        return todasTarefas.stream()
                .filter(tarefa -> tarefa.getDataLimite().isBefore(LocalDate.now()) && !"Concluído".equals(tarefa.getStatus()))
                .collect(Collectors.toList());
    }

}
