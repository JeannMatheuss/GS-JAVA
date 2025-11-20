package globalsolution.javags.service;

import globalsolution.javags.dto.TrilhaDTO;
import globalsolution.javags.entity.Trilha;
import globalsolution.javags.exception.TrilhaNaoEncontradaException;
import globalsolution.javags.repository.TrilhaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrilhaServiceTest {

    @Mock
    private TrilhaRepository repository;

    @InjectMocks
    private TrilhaService service;

    @Test
    public void testListarTodos() {
        Trilha trilha = new Trilha();
        trilha.setId(1L);
        trilha.setNome("Trilha de IA");
        List<Trilha> trilhas = Arrays.asList(trilha);

        when(repository.findAll()).thenReturn(trilhas);

        List<TrilhaDTO> result = service.listarTodos();
        assertEquals(1, result.size());
        assertEquals("Trilha de IA", result.get(0).getNome());
    }

    @Test
    public void testBuscarPorId() {
        Trilha trilha = new Trilha();
        trilha.setId(1L);
        trilha.setNome("Trilha de IA");

        when(repository.findById(1L)).thenReturn(Optional.of(trilha));

        TrilhaDTO result = service.buscarPorId(1L);
        assertEquals("Trilha de IA", result.getNome());
    }

    @Test
    public void testBuscarPorIdNaoEncontrado() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TrilhaNaoEncontradaException.class, () -> service.buscarPorId(1L));
    }

    @Test
    public void testCriar() {
        TrilhaDTO dto = new TrilhaDTO();
        dto.setNome("Nova Trilha");
        dto.setNivel("INICIANTE");
        dto.setCargaHoraria(30);

        Trilha trilha = new Trilha();
        trilha.setId(1L);
        trilha.setNome("Nova Trilha");

        when(repository.save(any(Trilha.class))).thenReturn(trilha);

        TrilhaDTO result = service.criar(dto);
        assertEquals("Nova Trilha", result.getNome());
        verify(repository, times(1)).save(any(Trilha.class));
    }

    @Test
    public void testAtualizar() {
        TrilhaDTO dto = new TrilhaDTO();
        dto.setNome("Atualizada");

        Trilha trilha = new Trilha();
        trilha.setId(1L);
        trilha.setNome("Original");

        when(repository.findById(1L)).thenReturn(Optional.of(trilha));
        when(repository.save(any(Trilha.class))).thenReturn(trilha);

        TrilhaDTO result = service.atualizar(1L, dto);
        assertEquals("Atualizada", result.getNome());
    }

    @Test
    public void testAtualizarNaoEncontrado() {
        TrilhaDTO dto = new TrilhaDTO();
        dto.setNome("Atualizada");

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TrilhaNaoEncontradaException.class, () -> service.atualizar(1L, dto));
    }

    @Test
    public void testDeletar() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.deletar(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletarNaoEncontrado() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(TrilhaNaoEncontradaException.class, () -> service.deletar(1L));
    }
}