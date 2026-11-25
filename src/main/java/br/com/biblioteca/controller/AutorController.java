package br.com.biblioteca.controller;

import br.com.biblioteca.model.Autor;
import br.com.biblioteca.service.AutorService;
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

@Tag(name = "Autores")
@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todos os autores")
    @GetMapping
    public List<Autor> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca um autor pelo id")
    @GetMapping("/{id}")
    public Autor buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @Operation(summary = "Cadastra um autor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autor criar(@Valid @RequestBody Autor autor) {
        return service.criar(autor);
    }

    @Operation(summary = "Atualiza um autor")
    @PutMapping("/{id}")
    public Autor atualizar(@PathVariable Long id, @Valid @RequestBody Autor autor) {
        return service.atualizar(id, autor);
    }

    @Operation(summary = "Remove um autor")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
