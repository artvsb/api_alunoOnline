package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.enums.MatriculaStatusEnum;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MatriculaAlunoService {

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

	private static final Double MEDIA_PARA_APROVACAO = 7.0;

    public void matricular(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculaStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }
    /* Nesse caso, a matricula só vai poder nascer com status MATRICULADO
    * e nenhum outro*/

	public void trancarMatricula(Long id) {
		MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Matrícula não encontrada"));

		if (matriculaAluno.getStatus()
				.equals(MatriculaStatusEnum.MATRICULADO)) {
			matriculaAluno.setStatus(MatriculaStatusEnum.TRANCADO);
			matriculaAlunoRepository.save(matriculaAluno);
		} else {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,
					"Só é possível trancar com status MATRICULADO"
			);
		}
	}

	public void atualizarNotas(Long id, AtualizarNotasRequestDTO dto) {
		MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Matrícula não encontrada"));

		if (dto.getNota1() != null)
			matriculaAluno.setNota1(dto.getNota1());
		if (dto.getNota2() != null)
			matriculaAluno.setNota2(dto.getNota2());

		if (matriculaAluno.getNota1() != null
				&& matriculaAluno.getNota2() != null) {
			Double media = (matriculaAluno.getNota1() + matriculaAluno.getNota2()) / 2;
			matriculaAluno.setStatus(
					media >= MEDIA_PARA_APROVACAO
							? MatriculaStatusEnum.APROVADO
							: MatriculaStatusEnum.REPROVADO);
		}
	}
}
