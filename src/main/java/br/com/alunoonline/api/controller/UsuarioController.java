package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.DadosCadastroUsuario;
import br.com.alunoonline.api.dto.DadosDetalhamentoUsuario;
import br.com.alunoonline.api.model.Usuario;
import br.com.alunoonline.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastros")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping
	@Transactional
	public ResponseEntity cadastro(@RequestBody @Valid
									   DadosCadastroUsuario dados,
								   UriComponentsBuilder uriBuilder) {
		if (usuarioRepository.findByLogin(dados.login()) != null) {
			return ResponseEntity.badRequest().body("Login já cadastrado.");
		}
		// Criptografar a senha
		var senhaCriptografada = passwordEncoder.encode(dados.senha());

		// Criar o usuário com o login e senha criptografada
		// foi criado um construtor en Usuario
		var usuario = new Usuario(dados.login(), senhaCriptografada);

		// Salvar o usuário no banco de dados
		usuarioRepository.save(usuario);

		// Criar o URI para o novo usuário
		var uri = uriBuilder.path("/usuarios/{id}")
				.buildAndExpand(usuario.getId()).toUri();

		return ResponseEntity.created(uri)
				.body(new DadosDetalhamentoUsuario(usuario.getId(), usuario.getLogin()));
	}

}
