package br.com.produtos.apirest.resources;

import br.com.produtos.apirest.models.Produto;
import br.com.produtos.apirest.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProdutoResource {

    @Autowired
    private ProdutoRepository produtosRepository;

    @GetMapping("/produtos")
    public List<Produto> getlListaProdutos(){
        return produtosRepository.findAll();
    }

    @GetMapping("/produtos/{id}")
    public Produto getProdutoById(@PathVariable("id") Long id){
        return produtosRepository.findById(id).orElse(null);
    }

    @PostMapping("/produtos")
    public Produto addProduto(@RequestBody Produto produto){
        return produtosRepository.save(produto);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity deletarProdutoById(@PathVariable("id") Long id){
        if(produtosRepository.existsById(id)){
            produtosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/produtos")
    public ResponseEntity deletarProdutoById(@RequestBody Produto produto){
        if(produtosRepository.existsById(produto.getId())){
            produtosRepository.save(produto);
            return ResponseEntity.ok().body(produto);
        }
        return ResponseEntity.badRequest().build();
    }
}
