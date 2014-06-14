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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "leciona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Leciona.buscarTudo", query = "SELECT l FROM Leciona l"),
    @NamedQuery(name = "Leciona.buscarPorIdLeciona", query = "SELECT l FROM Leciona l WHERE l.idLeciona = :idLeciona"),
    @NamedQuery(name = "Leciona.buscarPorPeriodo", query = "SELECT l FROM Leciona l WHERE l.periodo = :periodo")})
public class Leciona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLeciona")
    private Integer idLeciona;
    @Basic(optional = false)
    @Column(name = "periodo")
    private String periodo;
    @JoinColumn(name = "idProfessor", referencedColumnName = "idProfessor")
    @ManyToOne(optional = false)
    private Professor idProfessor;
    @JoinColumn(name = "idDisciplina", referencedColumnName = "idCicloDisciplina")
    @ManyToOne(optional = false)
    private CicloDisciplina idDisciplina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLeciona")
    private List<Matricula> matriculaList;

    public Leciona() {
    }

    public Leciona(Integer idLeciona) {
        this.idLeciona = idLeciona;
    }

    public Leciona(Integer idLeciona, String periodo) {
        this.idLeciona = idLeciona;
        this.periodo = periodo;
    }

    public Integer getIdLeciona() {
        return idLeciona;
    }

    public void setIdLeciona(Integer idLeciona) {
        this.idLeciona = idLeciona;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Professor getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Professor idProfessor) {
        this.idProfessor = idProfessor;
    }

    public CicloDisciplina getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(CicloDisciplina idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLeciona != null ? idLeciona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leciona)) {
            return false;
        }
        Leciona other = (Leciona) object;
        if ((this.idLeciona == null && other.idLeciona != null) || (this.idLeciona != null && !this.idLeciona.equals(other.idLeciona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "percistencia.Leciona[ idLeciona=" + idLeciona + " ]";
    }
    
}
