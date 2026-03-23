package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.alunoonline.api.repository.AlunoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired // injeta o repository no service
    AlunoRepository alunoRepository; // -> tem acesso ao BD

    public void criarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    public List<Aluno> buscarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }
    // Optional<objeto> sinaliza que o métod0 pode retornar algo ou nada

    public void deletarAlunoPorId(Long id) {
        alunoRepository.deleteById(id);
    }
}
