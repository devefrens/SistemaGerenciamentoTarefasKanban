package com.anderson.SistemaGerenciamentoTarefasKanban.controller;

import com.anderson.SistemaGerenciamentoTarefasKanban.model.Tarefa;
import com.anderson.SistemaGerenciamentoTarefasKanban.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> getAllTarefas() {
        return tarefaService.getAllTarefas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefasById(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.getTarefaById(id);
        return tarefa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tarefa createTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.createTarefa(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaModel) {
        Optional<Tarefa> tarefaAtualizada = tarefaService.updateTarefa(id, tarefaModel);
        return tarefaAtualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<Tarefa> moveTarefa(@PathVariable Long id, @RequestParam String coluna) {
        Tarefa tarefaAtualizada = tarefaService.moveTarefa(id, coluna);
        if (tarefaAtualizada.getStatus().equals("Concluído")) {
            return ResponseEntity.badRequest().body(tarefaAtualizada); // Caso a tarefa já esteja concluída, não pode ser movida
        }
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        if (tarefaService.deleteTarefa(id)) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        }
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found
    }
}

