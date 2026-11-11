package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.model.Aluno;

public record DadosDetalhamentoAluno(
		String nome,
		String email,
		String cpf,
		Boolean ativo
) {
	public DadosDetalhamentoAluno(Aluno aluno) {
		this(aluno.getNome(), aluno.getEmail(), aluno.getCpf(), aluno.getAtivo());
	}
}
