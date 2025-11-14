package globalsolution.javags.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "trilhas")
public class Trilha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String nivel;
    private Integer cargaHoraria;
    private String focoPrincipal;

    @ManyToMany
    @JoinTable(
            name = "trilha_competencia",
            joinColumns = @JoinColumn(name = "trilha_id"),
            inverseJoinColumns = @JoinColumn(name = "competencia_id")
    )
    private Set<Competencia> competencias;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public Integer getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(Integer cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public String getFocoPrincipal() { return focoPrincipal; }
    public void setFocoPrincipal(String focoPrincipal) { this.focoPrincipal = focoPrincipal; }
    public Set<Competencia> getCompetencias() { return competencias; }
    public void setCompetencias(Set<Competencia> competencias) { this.competencias = competencias; }
}