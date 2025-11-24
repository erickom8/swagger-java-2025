package application.Matricula;

import java.time.LocalDate;

public record MatriculaInsertDTO(LocalDate dataMatricula, String status, long aluno_id, long curso_id) {
    public MatriculaInsertDTO(Matricula matricula){
        this(matricula.getDataMatricula(),
            matricula.getStatus(),
            matricula.getAluno().getId(),
            matricula.getCurso().getId()
        );
    }
}
