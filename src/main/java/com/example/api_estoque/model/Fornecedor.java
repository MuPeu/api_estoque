package com.example.api_estoque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_forn;

    @Column(nullable = false)
    private String nome;

    // Construtor vazio
    public Fornecedor() {

    }

    public Fornecedor(Integer id_forn, String nome) {
        this.id_forn=id_forn;
        this.nome=nome;
    }

    // Getters e Setters
    public Integer getId_forn() { return id_forn; }
    public void setId_forn(Integer id_forn) { this.id_forn = id_forn; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
