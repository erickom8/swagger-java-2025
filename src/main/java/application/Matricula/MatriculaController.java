package application.Matricula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    @Operation(summary="Cria uma nova matrícula",
            description = "Cria uma nova matrícula com as informações fornecidas no corpo da requisição.")
    @ApiResponse(responseCode = "200", description = "Matrícula criada com sucesso!")
    public MatriculaDTO insert(@RequestBody MatriculaInsertDTO dados){
        return matriculaService.insert(dados);
    }

    @GetMapping
    @Operation(summary = "Lista todas as matrículas",
            description = "Retorna uma lista de todas as matrículas cadastradas no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de matrículas retornada com sucesso")
    public Iterable<MatriculaDTO> getAll() {
        return matriculaService.getAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma matrícula existente",
            description = "Atualiza as informações de uma matrícula específica com base no ID fornecido e nos dados fornecidos no corpo da requisição.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public MatriculaDTO update(@Parameter(description = "ID da matrícula a ser atualizada") @PathVariable long id, @RequestBody MatriculaInsertDTO data) {
        return matriculaService.update(id, data);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma matrícula",
            description = "Remove uma matrícula específica com base no ID fornecido.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public void delete(@Parameter(description = "ID da matrícula a ser removida") @PathVariable long id) {
        matriculaService.delete(id);
    }
}