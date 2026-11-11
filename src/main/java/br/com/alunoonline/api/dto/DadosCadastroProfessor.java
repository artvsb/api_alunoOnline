package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.DisciplinaEnum;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroProfessor(

		@NotBlank(message = "Nome obrigatório!")
		String nome,

		@NotBlank(message = "Email obrigatório!")
		String email,

		@NotBlank(message = "CPF obirgatório!")
		String cpf,

		@NotBlank(message = "Disciplina obrigatória!")
		String disciplina

) {
}
