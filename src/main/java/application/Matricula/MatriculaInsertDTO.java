package application.Matricula;

import java.time.LocalDate;

public record MatriculaInsertDTO(LocalDate dataMatricula, String status, long alunoId, long cursoId) {
    public MatriculaInsertDTO(Matricula matricula){
        this(matricula.getDataMatricula(),
            matricula.getStatus(),
            matricula.getAluno().getId(),
            matricula.getCurso().getId()
        );
    }
}
