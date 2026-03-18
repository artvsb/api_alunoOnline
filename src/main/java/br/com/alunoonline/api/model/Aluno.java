package br.com.alunoonline.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // construtor com nenhum argumento
@AllArgsConstructor // construtor com todos os argumentos
@Data // substitui os getters e setters
@Table(name = "aluno") // nome da tabela no banco de dados -> não é necessária porque @Entity já faz a tabela
@Entity // qualifica a linha abaixo como entidade (aluno) e cria uma tabela no BD
public class Aluno {

    // transforma id em chave primária; tem que ser o Id do jakarta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* o tipo 'int' é mais limitado e não comporta
	mais que alguns milhões de ids diferentes */

    private String nome;

    private String email;

    private String cpf;

}
