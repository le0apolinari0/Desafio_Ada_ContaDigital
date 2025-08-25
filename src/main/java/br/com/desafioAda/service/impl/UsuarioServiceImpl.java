package br.com.desafioAda.service.impl;

import br.com.desafioAda.dto.request.UsuarioRequest;
import br.com.desafioAda.dto.response.UsuarioResponse;
import br.com.desafioAda.model.Usuario;
import br.com.desafioAda.repository.UsuarioRepository;
import org.apache.coyote.BadRequestException;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioRepository {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CpfCnpjValidator cpfCnpjValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Override
    @Transactional
    public UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest) {
        // Validar CPF/CNPJ
        if (!cpfCnpjValidator.isValid(usuarioRequest.getCpfCnpj())) {
            throw new BadRequestException("CPF/CNPJ inválido");
        }

        // Validar email
        if (!emailValidator.isValid(usuarioRequest.getEmail())) {
            throw new BadRequestException("Email inválido");
        }

        // Verificar se email já existe
        if (existePorEmail(usuarioRequest.getEmail())) {
            throw new BadRequestException("Email já cadastrado");
        }

        // Verificar se CPF/CNPJ já existe
        if (existePorCpfCnpj(usuarioRequest.getCpfCnpj())) {
            throw new BadRequestException("CPF/CNPJ já cadastrado");
        }

        // Mapear request para entidade
        Usuario usuario = modelMapper.map(usuarioRequest, Usuario.class);

        // Criptografar senha
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.getSenha()));

        // Salvar usuário
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        // Retornar response
        return modelMapper.map(usuarioSalvo, UsuarioResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioResponse> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> modelMapper.map(usuario, UsuarioResponse.class));
    }

}