package com.example.api_estoque.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_movimentacaoEstoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_move;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Integer quant;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_prod", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private String obs;

    // Construtor vazio
    public MovimentacaoEstoque() {

    }

    public MovimentacaoEstoque(Integer id_move, String tipo, Integer quant, LocalDate data, Produto produto, String obs) {
        this.id_move = id_move;
        this.tipo = tipo;
        this.quant = quant;
        this.data = data;
        this.produto = produto;
        this.obs = obs;
    }

    // Getters e Setters
    public Integer getId_move() { return id_move; }
    public void setId_move(Integer id_move) { this.id_move = id_move; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Integer getQuant() { return quant; }
    public void setQuant(Integer quant) { this.quant = quant; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public String getObs() { return obs; }
    public void setObs(String obs) { this.obs = obs; }
}
