package br.com.biblioteca.service;

import br.com.biblioteca.exception.RecursoNaoEncontradoException;
import br.com.biblioteca.model.Autor;
import br.com.biblioteca.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public List<Autor> listar() {
        return repository.findAll();
    }

    public Autor buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Autor", id));
    }

    public Autor criar(Autor autor) {
        autor.setId(null);
        return repository.save(autor);
    }

    public Autor atualizar(Long id, Autor dados) {
        Autor autor = buscar(id);
        autor.setNome(dados.getNome());
        autor.setSobrenome(dados.getSobrenome());
        autor.setNacionalidade(dados.getNacionalidade());
        autor.setDataNascimento(dados.getDataNascimento());
        return repository.save(autor);
    }

    public void remover(Long id) {
        repository.delete(buscar(id));
    }
}
