package br.com.desafioAda.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LoginRequest {

    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;

}