package br.com.desafioAda.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.processing.Pattern;

import java.time.LocalDateTime;

@Data
public class UsuarioRequest {

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    private String cpfCnpj;

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 5, max = 100, message = "Nome deve ter entre 5 e 100 caracteres")
    private String nomeCompleto;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotNull
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDateTime dataNascimento;

    @NotNull
    @Past(message = "Enderço e obrigatorio")
    private EnderecoRequest endereco;

}