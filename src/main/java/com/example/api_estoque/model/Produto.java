package com.example.api_estoque.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_produtos")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_prod;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descr;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer quant;

    @ManyToOne
    @JoinColumn(name = "id_cat", nullable = false)
    private Categoria id_cat;

    @ManyToOne
    @JoinColumn(name = "id_forn", nullable = false)
    private Fornecedor id_forn;

    public Produto(){

    }

    public Produto(Integer id_prod, String nome, String descr, Double preco, Integer quant, Categoria id_cat, Fornecedor id_forn) {
        this.id_prod=id_prod;
        this.nome=nome;
        this.descr=descr;
        this.preco=preco;
        this.quant=quant;
        this.id_cat=id_cat;
        this.id_forn=id_forn;
    }

    @JsonProperty("id_prod") public Integer getId() { return id_prod; }
    @JsonProperty("id_prod") public void setId(Integer id_prod) { this.id_prod=id_prod; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome=nome; }

    public String getDescr() { return descr; }
    public void setDescr(String descr) { this.descr=descr; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco=preco; }

    public Integer getQuant() { return quant; }
    public void setQuant(Integer quant) { this.quant = quant; }

    public Categoria getCat() { return id_cat; }
    public void setCat(Categoria id_cat) { this.id_cat = id_cat; }

    public Fornecedor getForn() { return id_forn; }
    public void setForn(Fornecedor id_forn) { this.id_forn = id_forn; }
}
