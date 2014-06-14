/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "disciplina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disciplina.buscarTudo", query = "SELECT d FROM Disciplina d"),
    @NamedQuery(name = "Disciplina.buscarPorIdDisciplina", query = "SELECT d FROM Disciplina d WHERE d.idDisciplina = :idDisciplina"),
    @NamedQuery(name = "Disciplina.buscarPorNome", query = "SELECT d FROM Disciplina d WHERE d.nome = :nome")})
public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDisciplina")
    private Integer idDisciplina;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "cargaHoraria")
    private int cargaHoraria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDisciplina")
    private List<CicloDisciplina> cicloDisciplinaList;

    public Disciplina() {
    }

    public Disciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Disciplina(Integer idDisciplina, String nome, int cargaHoraria) {
        this.idDisciplina = idDisciplina;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @XmlTransient
    public List<CicloDisciplina> getCicloDisciplinaList() {
        return cicloDisciplinaList;
    }

    public void setCicloDisciplinaList(List<CicloDisciplina> cicloDisciplinaList) {
        this.cicloDisciplinaList = cicloDisciplinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDisciplina != null ? idDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplina)) {
            return false;
        }
        Disciplina other = (Disciplina) object;
        if ((this.idDisciplina == null && other.idDisciplina != null) || (this.idDisciplina != null && !this.idDisciplina.equals(other.idDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "percistencia.Disciplina[ idDisciplina=" + idDisciplina + " ]";
    }
    
}
