package com.example.backend_spring.service;

import com.example.backend_spring.entity.Usuario;
import com.example.backend_spring.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario criarUsuario(Usuario u) {
        // hash de senha
        u.setSenha(passwordEncoder.encode(u.getSenha()));
        return usuarioRepository.save(u);
    }

    @Transactional
    public Usuario atualizarUsuario(Long id, Usuario dados) {
        Usuario u = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        u.setNome_completo(dados.getNome_completo());
        u.setTelefone(dados.getTelefone());
        u.setEndereco(dados.getEndereco());
        // NÃO sobrescrever senha aqui a menos que passe nova senha
        return usuarioRepository.save(u);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}

