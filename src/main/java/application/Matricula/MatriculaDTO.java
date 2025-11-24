package application.Matricula;

import java.time.LocalDate;

import application.Aluno.AlunoDTO;
import application.Curso.CursoDTO;

public record MatriculaDTO (long id, LocalDate dataMatricula, String status, AlunoDTO aluno, CursoDTO curso) {
    public MatriculaDTO(Matricula matricula){
        this(
            matricula.getId(),
            matricula.getDataMatricula(),
            matricula.getStatus(),
            new AlunoDTO(matricula.getAluno()),
            new CursoDTO(matricula.getCurso())
        );
    }
}
