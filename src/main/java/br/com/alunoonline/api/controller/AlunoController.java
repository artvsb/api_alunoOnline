package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.DadosCadastroAluno;
import br.com.alunoonline.api.dto.DadosDetalhamentoAluno;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.service.AlunoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // controller que opera em arquitetura Rest
@RequestMapping("/alunos") /* mapeamento de requisição -> toda requisição HTTP que chegar em /alunos vai bater primeiro no AlunoController "/alunos" é um endpoint */
@SecurityRequirement(name = "bearer-key")
public class AlunoController {

    @Autowired
    AlunoService alunoService; // injeção do service no controller

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // o status da resposta que deve ser dada à request do POST
    public void criarAluno(@RequestBody @Valid DadosCadastroAluno dados) {
        // a anotação @RequestBody converte o corpo do request de Json para Java
        alunoService.criarAluno(dados);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DadosDetalhamentoAluno> buscarTodosAlunos() {
        return alunoService.buscarTodosAlunos();
    }

    @GetMapping("/{id}") // está entre {} porque o número id da URL deve ser exatamente igual à váriável id do parâmetro
    @ResponseStatus(HttpStatus.OK)
    public DadosDetalhamentoAluno buscarAlunoPorId(@PathVariable Long id) { // @PathVariable é a variável que está no caminho
        return alunoService.buscarAlunoPorId(id);
    } // Optional<> é usado quando não sabemos se haverá uma resposta a ser retornada

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAlunoPorId(@PathVariable Long id) {
        alunoService.deletarAlunoPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public DadosDetalhamentoAluno atualizarAlunoPorId(@PathVariable Long id,@RequestBody @Valid DadosCadastroAluno dto) {
        // @RequestBody é preciso para converter o alunoAtualizado de JSON -> Java
        return alunoService.atualizarAlunoPorId(id, dto);
    }
}
