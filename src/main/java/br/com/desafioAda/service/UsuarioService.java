package br.com.desafioAda.service;

import br.com.desafioAda.model.Usuario;
import br.com.desafioAda.dto.request.UsuarioRequest;
import br.com.desafioAda.dto.response.UsuarioResponse;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest);

    Optional<UsuarioResponse> buscarPorId(Long id);

    Optional<UsuarioResponse> buscarPorUuid(String uuid);

    Optional<UsuarioResponse> buscarPorEmail(String email);

    Optional<UsuarioResponse> buscarPorCpfCnpj(String cpfCnpj);

    List<UsuarioResponse> listarTodos();

    UsuarioResponse atualizarUsuario(String uuid, UsuarioRequest usuarioRequest);

    void desativarUsuario(String uuid);

    void ativarUsuario(String uuid);

    boolean existePorEmail(String email);

    boolean existePorCpfCnpj(String cpfCnpj);

    // Método interno para outros serviços
    Optional<Usuario> encontrarPorUuid(String uuid);
}