package br.com.desafioAda.dto.response;

import br.com.desafioAda.model.Endereco;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UsuarioResponse {

    private Long id;
    private String uuid;
    private String cpfCnpj;
    private String nomeCompleto;
    private String email;
    private String telefone;
    private LocalDateTime dataNascimento;
    private Endereco endereco;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}