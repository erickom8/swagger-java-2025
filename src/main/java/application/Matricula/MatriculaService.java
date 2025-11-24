package application.Matricula;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.Aluno.Aluno;
import application.Aluno.AlunoRepository;
import application.Curso.Curso;
import application.Curso.CursoRepository;

@Service
public class MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepo;

    @Autowired
    private AlunoRepository alunoRepo;

    @Autowired
    private CursoRepository cursoRepo;

    public Iterable<MatriculaDTO> getAll(){
        return matriculaRepo.findAll().stream().map(MatriculaDTO::new).toList();
    }

    public MatriculaDTO insert(MatriculaInsertDTO dados){
        Optional<Aluno> alunoResultado = alunoRepo.findById(dados.aluno_id());
        Optional<Curso> cursoResultado = cursoRepo.findById(dados.curso_id());
    
        String errorMessage = "Dados não encontrados para: ";
        boolean hasError = false;

        if (alunoResultado.isEmpty()) {
            errorMessage +=  "alunoID=" + dados.aluno_id() + " ";
            hasError = true;
        }
        if (cursoResultado.isEmpty()) {
            errorMessage += "cursoID=" + dados.curso_id() + " ";
            hasError = true;
        }
        if (hasError) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, errorMessage
            );
        }

        Matricula newMatricula = new Matricula();
        newMatricula.setDataMatricula(dados.dataMatricula());
        newMatricula.setStatus(dados.status());
        newMatricula.setAluno(alunoResultado.get());
        newMatricula.setCurso(cursoResultado.get());

        return new MatriculaDTO(matriculaRepo.save(newMatricula));
    }

    public MatriculaDTO getOne(long id){
        return matriculaRepo.findById(id).map(MatriculaDTO::new).orElseThrow(() ->new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Registration not found for id=" + id
        ));
    }

    public MatriculaDTO update(long id, MatriculaInsertDTO dados){
        Optional<Matricula> matriculaResultado = matriculaRepo.findById(id);
        if (matriculaResultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Matricula not found for id=" + id
            );
        }
        Optional<Aluno> alunoResultado = alunoRepo.findById(dados.aluno_id());
        Optional<Curso> cursoResultado = cursoRepo.findById(dados.curso_id());

        String errorMessage = "Data not found for: ";
        boolean hasError = false;

        if (alunoResultado.isEmpty()) {
            errorMessage += "alunoID=" + dados.aluno_id() + " ";
            hasError = true;
        }
        if (cursoResultado.isEmpty()) {
            errorMessage += "cursoID=" + dados.curso_id() + " ";
            hasError = true;
        }
        if (hasError) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, errorMessage
            );
        }

        Matricula registration = matriculaResultado.get();
        if (dados.dataMatricula() != null) {
            registration.setDataMatricula(dados.dataMatricula());
        }
        if (dados.status() != null) {
            registration.setStatus(dados.status());
        }
        registration.setAluno(alunoResultado.get());
        registration.setCurso(cursoResultado.get());

        return new MatriculaDTO(matriculaRepo.save(registration));
    }

    public void delete(long id) {
        if (!matriculaRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Matricula não encontrada para o id=" + id
            );
        }
        matriculaRepo.deleteById(id);
    }
}
