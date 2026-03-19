package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    /* 'extends JpaRepository' torna possível o CRUD do repository no Banco de Dados
	 extends JpaRepository <nome do model, tipo da chave primária desse model>
     alunoRepository só aceita a entidade Aluno como entrada */
}
