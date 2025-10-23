package com.example.api_estoque.controller;

import com.example.api_estoque.model.MovimentacaoEstoque;
import com.example.api_estoque.model.Produto;
import com.example.api_estoque.service.ServiceEstoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerEstoque {

    @Autowired
    private ServiceEstoque estoqueService;

    // Produtos
    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = estoqueService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Integer id) {
        return estoqueService.buscarProduto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        Produto novo = estoqueService.adicionarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
        Produto atualizado = estoqueService.atualizarProduto(id, produtoAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        boolean deletado = estoqueService.deletarProduto(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Movimentações
    @PostMapping("/movimentacoes")
    public ResponseEntity<MovimentacaoEstoque> registrarMovimentacao(@RequestBody MovimentacaoEstoque mov) {
        MovimentacaoEstoque registrada = estoqueService.registrarMovimentacao(mov);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrada);
    }

    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoEstoque>> listarMovimentacoesPorProduto(@RequestParam Integer produtoId) {
        List<MovimentacaoEstoque> lista = estoqueService.listarMovimentacoesPorProduto(produtoId);
        return ResponseEntity.ok(lista);
    }
}