package application.Aluno;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepo;

    public Iterable<AlunoDTO> getAll(){
        return alunoRepo.findAll().stream().map(AlunoDTO::new).toList();
    }

    public AlunoDTO insert(AlunoInsertDTO aluno) {
        return new AlunoDTO(alunoRepo.save(new Aluno(aluno)));
    }

    public AlunoDTO getOne(long id) {
        Optional<Aluno> resultado = alunoRepo.findById(id);

        if(resultado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado!"
            );
        }

        return new AlunoDTO(resultado.get());
    }

    @SuppressWarnings("null")
    public AlunoDTO update(long id, AlunoInsertDTO dadosAluno){
        Optional<Aluno> resultado = alunoRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado!"
            );
        }

        Aluno alunoExistente = resultado.get();

        if (dadosAluno.nome() != null) {
        alunoExistente.setNome(dadosAluno.nome());
        }

        if (dadosAluno.email() != null) {
            alunoExistente.setEmail(dadosAluno.email());
        }

        if (dadosAluno.telefone() != null) {
            alunoExistente.setTelefone(dadosAluno.telefone());
        }

        Aluno alunoAtualizado = alunoRepo.save(alunoExistente);
        
        return new AlunoDTO(alunoAtualizado);
    }


    public void delete(long id){
        if(!alunoRepo.existsById(id)){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado."
            );
        }
        alunoRepo.deleteById(id);
    }


}
