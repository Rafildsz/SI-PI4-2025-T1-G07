/**
 * ========================================
 * SEMEAR - Serviço de Propriedade
 * ========================================
 *
 * Descrição: classe de serviço que implementa lógica de negócio de propriedades.
 * Gerencia operações CRUD de propriedades associadas a usuários produtores.
 *
 * Responsabilidades:
 * - Listar todas as propriedades
 * - Buscar propriedade por ID
 * - Criar propriedade vinculada a um usuário
 * - Atualizar dados da propriedade
 * - Deletar propriedade
 * - Buscar propriedades de um usuário específico
 *
 * Dependências: PropriedadeRepository, UsuarioRepository, Spring Framework
 * ========================================
 */
package com.example.backend_spring.service;

import com.example.backend_spring.entity.Propriedade;
import com.example.backend_spring.entity.Usuario;
import com.example.backend_spring.repository.PropriedadeRepository;
import com.example.backend_spring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropriedadeService {

    private final PropriedadeRepository repository;
    private final UsuarioRepository usuarioRepository;

    public PropriedadeService(PropriedadeRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Propriedade> listar() {
        return repository.findAll();
    }

    public Propriedade buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Propriedade criar(Propriedade propriedade, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) return null;

        propriedade.setUsuario(usuario);
        return repository.save(propriedade);
    }

    public Propriedade atualizar(Long id, Propriedade dados) {
        Propriedade p = buscar(id);
        if (p == null) return null;

        p.setNome_propriedade(dados.getNome_propriedade());
        p.setDescricao(dados.getDescricao());
        p.setEndereco(dados.getEndereco());
        p.setCep(dados.getCep());
        p.setCpf_cnpj(dados.getCpf_cnpj());
        p.setSelo_certificacao(dados.getSelo_certificacao());

        return repository.save(p);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Propriedade> buscarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}
