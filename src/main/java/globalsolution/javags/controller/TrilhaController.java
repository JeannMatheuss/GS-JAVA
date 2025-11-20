package globalsolution.javags.controller;

import globalsolution.javags.dto.TrilhaDTO;
import globalsolution.javags.service.TrilhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trilhas")
public class TrilhaController {

    @Autowired
    TrilhaService trilhaService;

    @GetMapping
    public ResponseEntity<List<TrilhaDTO>> listarTodos() {
        List<TrilhaDTO> trilhas = trilhaService.listarTodos();
        return ResponseEntity.ok(trilhas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaDTO> buscarPorId(@PathVariable Long id) {
        TrilhaDTO trilha = trilhaService.buscarPorId(id);
        return ResponseEntity.ok(trilha);
    }

    @PostMapping
    public ResponseEntity<TrilhaDTO> criar(@Valid @RequestBody TrilhaDTO dto) {
        TrilhaDTO trilha = trilhaService.criar(dto);
        return ResponseEntity.status(201).body(trilha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TrilhaDTO dto) {
        TrilhaDTO trilha = trilhaService.atualizar(id, dto);
        return ResponseEntity.ok(trilha);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        trilhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}