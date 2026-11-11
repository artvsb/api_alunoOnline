package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.DisciplinaEnum;
import br.com.alunoonline.api.model.Professor;

public record DadosDetalhamentoProfessor(
		String nome,
		String email,
		String cpf,
		DisciplinaEnum disciplina
) {
	public DadosDetalhamentoProfessor(Professor professor) {
		this(professor.getNome(), professor.getEmail(), professor.getCpf(), professor.getDisciplina());
	}
}
