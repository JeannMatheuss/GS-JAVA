package globalsolution.javags.controller;

import globalsolution.javags.dto.UsuarioDTO;
import globalsolution.javags.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")  // Prefixo /api para evitar conflitos com recursos estáticos
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping  // GET /api/usuarios
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        List<UsuarioDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")  // GET /api/usuarios/{id}
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping  // POST /api/usuarios
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTO usuario = usuarioService.criar(dto);
        return ResponseEntity.status(201).body(usuario);  // 201 Created
    }

    @PutMapping("/{id}")  // PUT /api/usuarios/{id}
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTO usuario = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")  // DELETE /api/usuarios/{id}
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}