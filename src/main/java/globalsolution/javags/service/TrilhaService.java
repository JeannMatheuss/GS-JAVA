package com.globalsolution.service;

import globalsolution.javags.dto.TrilhaDTO;
import globalsolution.javags.entity.Trilha;
import globalsolution.javags.exception.TrilhaNaoEncontradaException;
import globalsolution.javags.repository.TrilhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrilhaService {

    @Autowired
    private TrilhaRepository repository;

    public List<TrilhaDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TrilhaDTO buscarPorId(Long id) {
        Trilha trilha = repository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException("Trilha com ID " + id + " não encontrada"));
        return toDTO(trilha);
    }

    public TrilhaDTO criar(TrilhaDTO dto) {
        Trilha trilha = toEntity(dto);
        trilha = repository.save(trilha);
        return toDTO(trilha);
    }

    public TrilhaDTO atualizar(Long id, TrilhaDTO dto) {
        Trilha trilha = repository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException("Trilha com ID " + id + " não encontrada"));
        trilha.setNome(dto.getNome());
        trilha.setDescricao(dto.getDescricao());
        trilha.setNivel(dto.getNivel());
        trilha.setCargaHoraria(dto.getCargaHoraria());
        trilha.setFocoPrincipal(dto.getFocoPrincipal());
        trilha = repository.save(trilha);
        return toDTO(trilha);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new TrilhaNaoEncontradaException("Trilha com ID " + id + " não encontrada");
        }
        repository.deleteById(id);
    }

    private TrilhaDTO toDTO(Trilha trilha) {
        TrilhaDTO dto = new TrilhaDTO();
        dto.setId(trilha.getId());
        dto.setNome(trilha.getNome());
        dto.setDescricao(trilha.getDescricao());
        dto.setNivel(trilha.getNivel());
        dto.setCargaHoraria(trilha.getCargaHoraria());
        dto.setFocoPrincipal(trilha.getFocoPrincipal());
        return dto;
    }

    private Trilha toEntity(TrilhaDTO dto) {
        Trilha trilha = new Trilha();
        trilha.setNome(dto.getNome());
        trilha.setDescricao(dto.getDescricao());
        trilha.setNivel(dto.getNivel());
        trilha.setCargaHoraria(dto.getCargaHoraria());
        trilha.setFocoPrincipal(dto.getFocoPrincipal());
        return trilha;
    }
}