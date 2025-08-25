package br.com.desafioAda.model;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "cpfCnpj"),
        @UniqueConstraint(columnNames = "email")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();

    @Column(nullable = false, length = 14)
    private String cpfCnpj;

    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false)
    private LocalDateTime dataNascimento;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataAtualizacao;

    // Construtores
    public Usuario() {}

    public Usuario(
            String cpfCnpj,
            String nomeCompleto,
            String email,
            String senha,
            String telefone,
            LocalDateTime dataNascimento,
            Endereco endereco
    ) {
        this.cpfCnpj = cpfCnpj;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    // Métodos de negócio
    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void desativar() {
        this.ativo = false;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void ativar() {
        this.ativo = true;
        this.dataAtualizacao = LocalDateTime.now();
    }
}