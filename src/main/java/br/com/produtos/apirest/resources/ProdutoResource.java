package br.com.produtos.apirest.resources;

import br.com.produtos.apirest.models.Produto;
import br.com.produtos.apirest.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest Produtos")
@CrossOrigin(origins = "*")
public class ProdutoResource {

    @Autowired
    private ProdutoRepository produtosRepository;

    @ApiOperation(value = "Retorna uma lista de produtos")
    @GetMapping("/produtos")
    public List<Produto> getlListaProdutos(){
        return produtosRepository.findAll();
    }

    @ApiOperation(value = "Retorna um produto único")
    @GetMapping("/produtos/{id}")
    public Produto getProdutoById(@PathVariable("id") Long id){
        return produtosRepository.findById(id).orElse(null);
    }

    @ApiOperation(value = "Salvar um produto")
    @PostMapping("/produtos")
    public Produto addProduto(@RequestBody Produto produto){
        return produtosRepository.save(produto);
    }

    @ApiOperation(value = "Deletar um produto")
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity deletarProdutoById(@PathVariable("id") Long id){
        if(produtosRepository.existsById(id)){
            produtosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Atualiza um produto")
    @PutMapping("/produtos")
    public ResponseEntity deletarProdutoById(@RequestBody Produto produto){
        if(produtosRepository.existsById(produto.getId())){
            produtosRepository.save(produto);
            return ResponseEntity.ok().body(produto);
        }
        return ResponseEntity.badRequest().build();
    }
}
