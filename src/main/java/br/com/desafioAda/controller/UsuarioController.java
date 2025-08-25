package br.com.desafioAda.controller;

import br.com.desafioAda.dto.request.UsuarioRequest;
import br.com.desafioAda.dto.response.UsuarioResponse;
import br.com.desafioAda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = usuarioService.criarUsuario(usuarioRequest);
        return ResponseEntity.ok(usuarioResponse);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioResponse> buscarPorUuid(@PathVariable String uuid) {
        return usuarioService.buscarPorUuid(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        List<UsuarioResponse> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(
            @PathVariable String uuid,
            @Valid @RequestBody UsuarioRequest usuarioRequest) {

        UsuarioResponse usuarioResponse = usuarioService.atualizarUsuario(uuid, usuarioRequest);
        return ResponseEntity.ok(usuarioResponse);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> desativarUsuario(@PathVariable String uuid) {
        usuarioService.desativarUsuario(uuid);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{uuid}/ativar")
    public ResponseEntity<Void> ativarUsuario(@PathVariable String uuid) {
        usuarioService.ativarUsuario(uuid);
        return ResponseEntity.noContent().build();
    }
}