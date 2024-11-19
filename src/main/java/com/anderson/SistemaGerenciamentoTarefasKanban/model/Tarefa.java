package com.anderson.SistemaGerenciamentoTarefasKanban.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private String status;
    private String prioridade;
    private LocalDate dataLimite;

    @ManyToOne
    @JoinColumn(name = "id_coluna")
    private Coluna coluna; // Coluna onde a tarefa está

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public Coluna getColuna() {
        return coluna;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public void setColuna(Coluna coluna) {
        this.coluna = coluna;
    }
}
