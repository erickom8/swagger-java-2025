package application.Curso;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cursos")
@Getter
@Setter
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private Integer cargaHoraria;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    public Curso(CursoDTO dados){
        this.setId(dados.id());
        this.setNome(dados.nome());
        this.setDescricao(dados.descricao());
        this.setCargaHoraria(dados.cargaHoraria());
        this.setStatus(dados.status());
        this.setDataCriacao(dados.dataCriacao());
    }

    public Curso(CursoInsertDTO dados){
        this.setNome(dados.nome());
        this.setDescricao(dados.descricao());
        this.setCargaHoraria(dados.cargaHoraria());
        this.setStatus(dados.status());
        this.setDataCriacao(dados.dataCriacao());
    }

}
