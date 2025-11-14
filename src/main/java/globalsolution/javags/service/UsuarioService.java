package globalsolution.javags.service;

import globalsolution.javags.dto.UsuarioDTO;
import globalsolution.javags.entity.Usuario;
import globalsolution.javags.exception.UsuarioNaoEncontradoException;
import globalsolution.javags.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado"));
        return toDTO(usuario);
    }

    public UsuarioDTO criar(UsuarioDTO dto) {
        Usuario usuario = toEntity(dto);
        usuario = repository.save(usuario);
        return toDTO(usuario);
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado"));
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setAreaAtuacao(dto.getAreaAtuacao());
        usuario.setNivelCarreira(dto.getNivelCarreira());
        usuario.setDataCadastro(dto.getDataCadastro());
        usuario = repository.save(usuario);
        return toDTO(usuario);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado");
        }
        repository.deleteById(id);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setAreaAtuacao(usuario.getAreaAtuacao());
        dto.setNivelCarreira(usuario.getNivelCarreira());
        dto.setDataCadastro(usuario.getDataCadastro());
        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setAreaAtuacao(dto.getAreaAtuacao());
        usuario.setNivelCarreira(dto.getNivelCarreira());
        usuario.setDataCadastro(dto.getDataCadastro());
        return usuario;
    }
}