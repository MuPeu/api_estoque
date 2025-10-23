package com.example.api_estoque.service;

import com.example.api_estoque.model.MovimentacaoEstoque;
import com.example.api_estoque.model.Produto;
import com.example.api_estoque.repository.MovimentacaoRepository;
import com.example.api_estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEstoque {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarProduto(Integer id_prod) {
        return produtoRepository.findById(id_prod);
    }

    public Produto adicionarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // ATUALIZAR PRODUTO
    public Produto atualizarProduto(Integer id_prod, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id_prod)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescr(produtoAtualizado.getDescr());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuant(produtoAtualizado.getQuant());
        produtoExistente.setCat(produtoAtualizado.getCat());
        produtoExistente.setForn(produtoAtualizado.getForn());

        return produtoRepository.save(produtoExistente);
    }

    public boolean deletarProduto(Integer id_prod) {
        if (produtoRepository.existsById(id_prod)) {
            produtoRepository.deleteById(id_prod);
            return true;
        }
        return false;
    }

    public MovimentacaoEstoque registrarMovimentacao(MovimentacaoEstoque mov) {
        Produto produto = produtoRepository.findById(mov.getProduto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (mov.getTipo().equalsIgnoreCase("ENTRADA")) {
            produto.setQuant(produto.getQuant() + mov.getQuant());
        } else if (mov.getTipo().equalsIgnoreCase("SAIDA")) {
            int quantidadeAtual = produto.getQuant();
            int quantidadeMov = mov.getQuant();

            if (quantidadeAtual < quantidadeMov) {
                throw new IllegalArgumentException("Estoque insuficiente para realizar a saída.");
            }
            produto.setQuant(quantidadeAtual - quantidadeMov);
        } else {
            throw new IllegalArgumentException("Tipo de movimentação inválido (use ENTRADA ou SAIDA).");
        }

        produtoRepository.save(produto);

        mov.setData(LocalDate.now());
        mov.setProduto(produto);
        return movimentacaoRepository.save(mov);
    }

    public List<MovimentacaoEstoque> listarMovimentacoesPorProduto(Integer id_prod) {
        return movimentacaoRepository.findByProduto_Id(id_prod);
    }
}
