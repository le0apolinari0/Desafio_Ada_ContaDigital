package br.com.desafioAda.dto.response;

import lombok.Data;

@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String uuid;
    private String email;
    private String nomeCompleto;

    // Construtores
    public JwtResponse(
            String token,
            Long id,
            String uuid,
            String email,
            String nomeCompleto
    ) {
        this.token = token;
        this.id = id;
        this.uuid = uuid;
        this.email = email;
        this.nomeCompleto = nomeCompleto;
    }

}