package br.com.biblioteca.service;

import br.com.biblioteca.exception.RecursoNaoEncontradoException;
import br.com.biblioteca.model.Editora;
import br.com.biblioteca.repository.EditoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService {

    private final EditoraRepository repository;

    public EditoraService(EditoraRepository repository) {
        this.repository = repository;
    }

    public List<Editora> listar() {
        return repository.findAll();
    }

    public Editora buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Editora", id));
    }

    public Editora criar(Editora editora) {
        editora.setId(null);
        return repository.save(editora);
    }

    public Editora atualizar(Long id, Editora dados) {
        Editora editora = buscar(id);
        editora.setNome(dados.getNome());
        editora.setPaisOrigem(dados.getPaisOrigem());
        editora.setContato(dados.getContato());
        return repository.save(editora);
    }

    public void remover(Long id) {
        repository.delete(buscar(id));
    }
}
