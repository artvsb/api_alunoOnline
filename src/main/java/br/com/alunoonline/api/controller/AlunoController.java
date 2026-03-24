package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // controller que opera em arquitetura Rest
@RequestMapping("/alunos") /* mapeamento de requisição -> toda requisição HTTP que chegar em /alunos vai bater primeiro no AlunoController
"/alunos" é um endpoint */
public class AlunoController {

    @Autowired
    AlunoService alunoService; // injeção do service no controller

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // o status da resposta que deve ser dada à request do POST
    public void criarAluno(@RequestBody Aluno aluno) {
        // a anotação @RequestBody converte o corpo do request de Json para Java
        alunoService.criarAluno(aluno);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> buscarTodosAlunos() {
        return alunoService.buscarTodosAlunos();
    }

    @GetMapping("/{id}") // está entre {} porque o número id da URL deve ser exatamente igual à váriável id do parâmetro
    @ResponseStatus(HttpStatus.OK)
    public Optional<Aluno> buscarAlunoPorId(@PathVariable Long id) { // @PathVariable é a variável que está no caminho
        return alunoService.buscarAlunoPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAlunoPorId(@PathVariable Long id) {
        alunoService.deletarAlunoPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAlunoPorId(@PathVariable Long id,@RequestBody Aluno alunoAtualizado) {
        // @RequestBody é preciso para converter o alunoAtualizado de JSON -> Java
        alunoService.atualizarAlunoPorId(id, alunoAtualizado);
    }
}
