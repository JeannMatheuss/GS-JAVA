package globalsolution.javags.controller;

import globalsolution.javags.dto.TrilhaDTO;
import globalsolution.javags.service.TrilhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrilhaController.class)
public class TrilhaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrilhaService trilhaService;

    @Test
    public void testListarTodos() throws Exception {
        TrilhaDTO trilha = new TrilhaDTO();
        trilha.setId(1L);
        trilha.setNome("Trilha de IA");
        trilha.setNivel("INICIANTE");
        trilha.setCargaHoraria(40);
        List<TrilhaDTO> trilhas = Arrays.asList(trilha);

        when(trilhaService.listarTodos()).thenReturn(trilhas);

        mockMvc.perform(get("/api/trilhas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Trilha de IA"));
    }

    @Test
    public void testBuscarPorId() throws Exception {
        TrilhaDTO trilha = new TrilhaDTO();
        trilha.setId(1L);
        trilha.setNome("Trilha de IA");

        when(trilhaService.buscarPorId(1L)).thenReturn(trilha);

        mockMvc.perform(get("/api/trilhas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Trilha de IA"));
    }

    @Test
    public void testBuscarPorIdNaoEncontrado() throws Exception {
        when(trilhaService.buscarPorId(1L)).thenThrow(new RuntimeException("Trilha com ID 1 não encontrada"));

        mockMvc.perform(get("/api/trilhas/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Trilha com ID 1 não encontrada"));
    }

    @Test
    public void testCriar() throws Exception {
        TrilhaDTO trilha = new TrilhaDTO();
        trilha.setId(1L);
        trilha.setNome("Nova Trilha");
        trilha.setNivel("INICIANTE");
        trilha.setCargaHoraria(30);

        when(trilhaService.criar(any(TrilhaDTO.class))).thenReturn(trilha);

        mockMvc.perform(post("/api/trilhas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Nova Trilha\",\"nivel\":\"INICIANTE\",\"cargaHoraria\":30}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Nova Trilha"));
    }

    @Test
    public void testCriarComValidacaoFalha() throws Exception {
        mockMvc.perform(post("/api/trilhas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"\",\"nivel\":\"\",\"cargaHoraria\":-1}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").value("Nome é obrigatório"))
                .andExpect(jsonPath("$.nivel").value("Nível é obrigatório"))
                .andExpect(jsonPath("$.cargaHoraria").value("Carga horária deve ser positiva"));
    }

    @Test
    public void testAtualizar() throws Exception {
        TrilhaDTO trilha = new TrilhaDTO();
        trilha.setId(1L);
        trilha.setNome("Trilha Atualizada");

        when(trilhaService.atualizar(eq(1L), any(TrilhaDTO.class))).thenReturn(trilha);

        mockMvc.perform(put("/api/trilhas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Trilha Atualizada\",\"nivel\":\"INICIANTE\",\"cargaHoraria\":40}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Trilha Atualizada"));
    }

    @Test
    public void testAtualizarNaoEncontrado() throws Exception {
        when(trilhaService.atualizar(eq(1L), any(TrilhaDTO.class))).thenThrow(new RuntimeException("Trilha com ID 1 não encontrada"));

        mockMvc.perform(put("/api/trilhas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Trilha\",\"nivel\":\"INICIANTE\",\"cargaHoraria\":40}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Trilha com ID 1 não encontrada"));
    }

    @Test
    public void testDeletar() throws Exception {
        doNothing().when(trilhaService).deletar(1L);

        mockMvc.perform(delete("/api/trilhas/1"))
                .andExpect(status().isNoContent());

        verify(trilhaService, times(1)).deletar(1L);
    }

    @Test
    public void testDeletarNaoEncontrado() throws Exception {
        doThrow(new RuntimeException("Trilha com ID 1 não encontrada")).when(trilhaService).deletar(1L);

        mockMvc.perform(delete("/api/trilhas/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Trilha com ID 1 não encontrada"));
    }
}