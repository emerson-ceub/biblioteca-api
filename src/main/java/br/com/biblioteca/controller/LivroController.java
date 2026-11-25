package br.com.biblioteca.controller;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Livros")
@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todos os livros")
    @GetMapping
    public List<Livro> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca um livro pelo id")
    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @Operation(summary = "Cadastra um livro", description = "Informe autor e editora pelo id: {\"autor\": {\"id\": 1}, \"editora\": {\"id\": 1}}")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro criar(@Valid @RequestBody Livro livro) {
        return service.criar(livro);
    }

    @Operation(summary = "Atualiza um livro")
    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro) {
        return service.atualizar(id, livro);
    }

    @Operation(summary = "Remove um livro")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
