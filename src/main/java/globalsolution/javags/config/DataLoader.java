package globalsolution.javags.config;

import globalsolution.javags.entity.Competencia;
import globalsolution.javags.entity.Trilha;
import globalsolution.javags.entity.Usuario;
import globalsolution.javags.repository.CompetenciaRepository;
import globalsolution.javags.repository.TrilhaRepository;
import globalsolution.javags.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TrilhaRepository trilhaRepository;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Carregar competências
        Competencia ia = new Competencia();
        ia.setNome("Inteligência Artificial");
        ia.setCategoria("Tecnológica");
        ia.setDescricao("Habilidades em IA para automação e análise de dados.");
        competenciaRepository.save(ia);

        Competencia empatia = new Competencia();
        empatia.setNome("Empatia e Colaboração");
        empatia.setCategoria("Humana");
        empatia.setDescricao("Competências interpessoais para trabalho híbrido.");
        competenciaRepository.save(empatia);

        // Carregar trilhas
        Trilha trilhaIA = new Trilha();
        trilhaIA.setNome("Trilha de IA para Profissionais");
        trilhaIA.setDescricao("Aprenda IA para requalificação em 2030.");
        trilhaIA.setNivel("INICIANTE");
        trilhaIA.setCargaHoraria(40);
        trilhaIA.setFocoPrincipal("Tecnologia");
        trilhaIA.setCompetencias(Set.of(ia));
        trilhaRepository.save(trilhaIA);

        // Carregar usuários
        Usuario usuario = new Usuario();
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setAreaAtuacao("TI");
        usuario.setNivelCarreira("Júnior");
        usuario.setDataCadastro(LocalDate.of(2023, 10, 1));
        usuarioRepository.save(usuario);
    }
}