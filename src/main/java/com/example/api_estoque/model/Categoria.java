package com.example.api_estoque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cat;

    @Column(nullable = false)
    private String nome;

    // Construtor vazio
    public Categoria() {

    }

    public Categoria(Integer id_cat, String nome) {
        this.id_cat=id_cat;
        this.nome=nome;
    }

    // Getters e Setters
    public Integer getId_cat() { return id_cat; }
    public void setId_cat(Integer id_cat) { this.id_cat = id_cat; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
