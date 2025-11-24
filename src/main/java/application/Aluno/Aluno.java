package application.Aluno; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate; 

@Entity
@Table(name = "alunos") // Nome da tabela no banco de dados
@Getter
@Setter
@NoArgsConstructor
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use Long com 'L' maiúsculo é preferível para IDs

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email; // E-mail deve ser único (UNIQUE)

    private String telefone;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula; 

    
    public Aluno (AlunoDTO dados) {
        this.setId(dados.id());
        this.setNome(dados.nome());
        this.setEmail(dados.email());
        this.setTelefone(dados.telefone());
        this.setDataMatricula(dados.dataMatricula());
    }

    
    public Aluno(AlunoInsertDTO dados) {
        this.setNome(dados.nome());
        this.setEmail(dados.email());
        this.setTelefone(dados.telefone());
        this.setDataMatricula(dados.dataMatricula());
        this.setDataMatricula(LocalDate.now()); 
    }
    
}