package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.web.server.ResponseStatusException;

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

    public void atualizarAlunoPorId(Long id, Aluno alunoAtualizado) {
        // primeiro precisamos buscar o aluno para ver se ele existe no BD
        Optional<Aluno> alunoObsoleto = buscarAlunoPorId(id);

        // se não existir aluno com o id citado
        if (alunoObsoleto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Aluno não encontrado!");
        }

        /* se chegou aqui, significa que existe aluno com esse id no BD
        vou puxá-lo e armazená-lo numa variável para editá-lo posteriormente */
        Aluno alunoEditado = alunoObsoleto.get();

        /* com esse aluno para ser editado acima, faço os setters necessários para att os atributos dele;
         é como se buscássemos o aluno em questão no BD, guardamos ele localmente enquanto
         estamos editando, para devolvê-lo editado ao BD com os dados editados pelo front */
        alunoEditado.setNome(alunoAtualizado.getNome());
        alunoEditado.setCpf(alunoAtualizado.getCpf());
        alunoEditado.setEmail(alunoAtualizado.getEmail());

        // agora vou levar o alunoEditado para o BD
        alunoRepository.save(alunoAtualizado);

        // esse métood é um PUT obrigatório, porque vai atualizar todos os dados mesmo que o
        // usuário não mude algum dado
    }
}
