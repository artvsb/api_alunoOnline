package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.DadosCadastroAluno;
import br.com.alunoonline.api.dto.DadosDetalhamentoAluno;
import br.com.alunoonline.api.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlunoService {

    @Autowired // injeta o repository no service
    AlunoRepository alunoRepository; // -> tem acesso ao BD

    public DadosDetalhamentoAluno criarAluno(DadosCadastroAluno dados) {
        Aluno novo = new Aluno(dados);
		alunoRepository.save(novo);
		return new DadosDetalhamentoAluno(novo.getNome(), novo.getEmail(), novo.getCpf(), novo.getAtivo());
    }

    public List<DadosDetalhamentoAluno> buscarTodosAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
		return alunos.stream()
				.map(aluno -> paraResposta(aluno))
				.toList();
    }

    public DadosDetalhamentoAluno buscarAlunoPorId(Long id) {
        return paraResposta(buscarOuFalhar(id));
    }

    public void deletarAlunoPorId(Long id) {
        if (!alunoRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
		}

		alunoRepository.deleteById(id);
    }

    public DadosDetalhamentoAluno atualizarAlunoPorId(Long id, DadosCadastroAluno dto) {
		Aluno aluno = buscarOuFalhar(id);
		aluno.setNome(dto.nome());
		aluno.setEmail(dto.email());
		aluno.setCpf(dto.cpf());

		Aluno salvo = alunoRepository.save(aluno);
		return paraResposta(salvo);
	}
	private Aluno buscarOuFalhar(Long id) {
		return alunoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Aluno não encontrado!"
				));
	}

	private DadosDetalhamentoAluno paraResposta(Aluno aluno) {
		return new DadosDetalhamentoAluno(
				aluno.getNome(), aluno.getEmail(), aluno.getCpf(), aluno.getAtivo()
		);
	}
}
