package application.Curso;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepo;

    public Iterable<CursoDTO> getAll() {
        return cursoRepo.findAll().stream().map(CursoDTO::new).toList();
    }

    public CursoDTO insert(CursoInsertDTO data) {
        return new CursoDTO(cursoRepo.save(new Curso(data)));
    }

    public CursoDTO getOne(@PathVariable long id) {
        Optional<Curso> result = cursoRepo.findById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        return new CursoDTO(result.get());
    }

    @SuppressWarnings("null")
    public CursoDTO update(long id, CursoInsertDTO dados) {
        Optional<Curso> result = cursoRepo.findById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        Curso currentCurso = result.get();

        if (dados.nome() != null) {
            currentCurso.setNome(dados.nome());
        }
        if (dados.descricao() != null) {
            currentCurso.setDescricao(dados.descricao());
        }
        if (dados.cargaHoraria() != null) {
            currentCurso.setCargaHoraria(dados.cargaHoraria());
        }
        if (dados.status() != null) {
            currentCurso.setStatus(dados.status());
        }
        if (dados.dataCriacao() != null) {
            currentCurso.setDataCriacao(dados.dataCriacao());
        }

        return new CursoDTO(cursoRepo.save(currentCurso));
    }

    public void delete(long id) {
        if (!cursoRepo.existsById(id)){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        cursoRepo.deleteById(id);
    }
}
