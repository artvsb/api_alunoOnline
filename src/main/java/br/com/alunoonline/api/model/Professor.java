package br.com.alunoonline.api.model;

import br.com.alunoonline.api.dto.DadosCadastroAluno;
import br.com.alunoonline.api.dto.DadosCadastroProfessor;
import br.com.alunoonline.api.enums.DisciplinaEnum;
import br.com.alunoonline.api.enums.MatriculaStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "professor")
@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome, email, cpf;

	@Enumerated(EnumType.STRING)
	private DisciplinaEnum disciplina;

	public Professor(DadosCadastroProfessor dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.cpf = dados.cpf();
	}
}
