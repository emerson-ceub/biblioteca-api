package br.com.biblioteca.controller;

import br.com.biblioteca.model.Editora;
import br.com.biblioteca.service.EditoraService;
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

@Tag(name = "Editoras")
@RestController
@RequestMapping("/api/editoras")
public class EditoraController {

    private final EditoraService service;

    public EditoraController(EditoraService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todas as editoras")
    @GetMapping
    public List<Editora> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca uma editora pelo id")
    @GetMapping("/{id}")
    public Editora buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @Operation(summary = "Cadastra uma editora")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Editora criar(@Valid @RequestBody Editora editora) {
        return service.criar(editora);
    }

    @Operation(summary = "Atualiza uma editora")
    @PutMapping("/{id}")
    public Editora atualizar(@PathVariable Long id, @Valid @RequestBody Editora editora) {
        return service.atualizar(id, editora);
    }

    @Operation(summary = "Remove uma editora")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
