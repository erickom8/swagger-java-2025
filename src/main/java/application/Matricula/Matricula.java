package application.Matricula;

import java.time.LocalDate;

import application.Aluno.Aluno;
import application.Curso.Curso;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "matriculas")
@Getter
@Setter
@NoArgsConstructor
public class Matricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @Column(nullable = false, length = 20)
    private String status;


    @ManyToOne 
    @JoinColumn(name = "aluno_id", nullable = false) // Nome da coluna FK no banco
    private Aluno aluno;


    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false) // Nome da coluna FK no banco
    private Curso curso;
    

}