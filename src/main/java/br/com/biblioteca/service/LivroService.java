package br.com.biblioteca.service;

import br.com.biblioteca.exception.RecursoNaoEncontradoException;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;
    private final AutorService autorService;
    private final EditoraService editoraService;

    public LivroService(LivroRepository repository, AutorService autorService, EditoraService editoraService) {
        this.repository = repository;
        this.autorService = autorService;
        this.editoraService = editoraService;
    }

    public List<Livro> listar() {
        return repository.findAll();
    }

    public Livro buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro", id));
    }

    public Livro criar(Livro livro) {
        livro.setId(null);
        copiarRelacionamentos(livro, livro);
        return repository.save(livro);
    }

    public Livro atualizar(Long id, Livro dados) {
        Livro livro = buscar(id);
        livro.setTitulo(dados.getTitulo());
        livro.setIsbn(dados.getIsbn());
        livro.setAnoPublicacao(dados.getAnoPublicacao());
        livro.setGenero(dados.getGenero());
        livro.setIdioma(dados.getIdioma());
        copiarRelacionamentos(dados, livro);
        return repository.save(livro);
    }

    public void remover(Long id) {
        repository.delete(buscar(id));
    }

    private void copiarRelacionamentos(Livro origem, Livro destino) {
        destino.setAutor(autorService.buscar(origem.getAutor().getId()));
        destino.setEditora(editoraService.buscar(origem.getEditora().getId()));
    }
}
