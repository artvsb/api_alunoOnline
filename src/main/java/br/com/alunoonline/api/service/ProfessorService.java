package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.DadosCadastroProfessor;
import br.com.alunoonline.api.dto.DadosDetalhamentoProfessor;
import br.com.alunoonline.api.enums.DisciplinaEnum;
import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public DadosDetalhamentoProfessor criarProfessor(DadosCadastroProfessor dados) {

		DisciplinaEnum disciplina = buscarDisciplinaOuFalhar(dados.disciplina());

		Professor professor = new Professor(dados);
		professor.setDisciplina(disciplina);

		professorRepository.save(professor);
		return new DadosDetalhamentoProfessor(professor);
	}

    public List<DadosDetalhamentoProfessor> buscarTodosProfessores() {
		List<Professor> professores =  professorRepository.findAll();
		return professores.stream()
				.map(professor -> paraResposta(professor))
				.toList();
	}

    public DadosDetalhamentoProfessor buscarProfessorPorId(Long id) {
		return paraResposta(buscarOuFalhar(id));
	}

    public void deletarProfessorPorId(Long id) {
		if (!professorRepository.existsById(id)) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Professor não encontrado!"
			);
		}
		professorRepository.deleteById(id);
	}

    public DadosDetalhamentoProfessor atualizarProfessorPorId(Long id, DadosCadastroProfessor dto) {
		Professor professor = buscarOuFalhar(id);
		professor.setNome(dto.nome());
		professor.setEmail(dto.email());
		professor.setCpf(dto.cpf());
		professor.setDisciplina(buscarDisciplinaOuFalhar(dto.disciplina()));

		return paraResposta(professor);
    }

	private DisciplinaEnum buscarDisciplinaOuFalhar(String disciplina) {
		try {
			return DisciplinaEnum.valueOf(disciplina.trim().toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,
					"Disciplina inválida: " + disciplina
			);
		}
	}

	private Professor buscarOuFalhar(Long id) {
		return professorRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Professor não encontrado!"
				));
	}

	private DadosDetalhamentoProfessor paraResposta(Professor professor) {
		return new DadosDetalhamentoProfessor(
				professor.getNome(), professor.getEmail(), professor.getEmail(), professor.getDisciplina()
		);
	}
}
