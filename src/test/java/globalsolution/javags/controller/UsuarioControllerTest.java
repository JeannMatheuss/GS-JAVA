package globalsolution.javags.controller;

import globalsolution.javags.dto.UsuarioDTO;
import globalsolution.javags.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    public void testListarTodos() throws Exception {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        List<UsuarioDTO> usuarios = Arrays.asList(usuario);

        when(usuarioService.listarTodos()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João Silva"));
    }

    @Test
    public void testBuscarPorId() throws Exception {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        usuario.setNome("João Silva");

        when(usuarioService.buscarPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"));
    }

    @Test
    public void testBuscarPorIdNaoEncontrado() throws Exception {
        when(usuarioService.buscarPorId(1L)).thenThrow(new RuntimeException("Usuário com ID 1 não encontrado"));

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Usuário com ID 1 não encontrado"));
    }

    @Test
    public void testCriar() throws Exception {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        usuario.setNome("Novo Usuário");

        when(usuarioService.criar(any(UsuarioDTO.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Novo Usuário\",\"email\":\"novo@email.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Novo Usuário"));
    }

    @Test
    public void testCriarComValidacaoFalha() throws Exception {
        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"\",\"email\":\"invalid-email\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").value("Nome é obrigatório"))
                .andExpect(jsonPath("$.email").value("Email deve ser válido"));
    }

    @Test
    public void testAtualizar() throws Exception {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        usuario.setNome("Atualizado");

        when(usuarioService.atualizar(eq(1L), any(UsuarioDTO.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Atualizado\",\"email\":\"atualizado@email.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Atualizado"));
    }

    @Test
    public void testAtualizarNaoEncontrado() throws Exception {
        when(usuarioService.atualizar(eq(1L), any(UsuarioDTO.class))).thenThrow(new RuntimeException("Usuário com ID 1 não encontrado"));

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Usuário\",\"email\":\"user@email.com\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Usuário com ID 1 não encontrado"));
    }

    @Test
    public void testDeletar() throws Exception {
        doNothing().when(usuarioService).deletar(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeletarNaoEncontrado() throws Exception {
        doThrow(new RuntimeException("Usuário com ID 1 não encontrado")).when(usuarioService).deletar(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Usuário com ID 1 não encontrado"));
    }
}