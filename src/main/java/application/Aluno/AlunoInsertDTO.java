package application.Aluno;

import java.time.LocalDate;

public record AlunoInsertDTO(String nome, String email, String telefone, LocalDate dataMatricula) {
    public AlunoInsertDTO(Aluno aluno) {
        this(
            aluno.getNome(),
            aluno.getEmail(),
            aluno.getTelefone(),
            aluno.getDataMatricula()
        );
    }    
}