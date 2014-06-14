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
@Table(name = "matricula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matricula.buscarTudo", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.buscarPorIdMatricula", query = "SELECT m FROM Matricula m WHERE m.idMatricula = :idMatricula")})
public class Matricula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMatricula")
    private Integer idMatricula;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMatricula")
    private List<Nota> notaList;
    @JoinColumn(name = "idAluno", referencedColumnName = "idAluno")
    @ManyToOne(optional = false)
    private Aluno idAluno;
    @JoinColumn(name = "idLeciona", referencedColumnName = "idLeciona")
    @ManyToOne(optional = false)
    private Leciona idLeciona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMatricula")
    private List<Frequencia> frequenciaList;

    public Matricula() {
    }

    public Matricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    @XmlTransient
    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    public Aluno getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Aluno idAluno) {
        this.idAluno = idAluno;
    }

    public Leciona getIdLeciona() {
        return idLeciona;
    }

    public void setIdLeciona(Leciona idLeciona) {
        this.idLeciona = idLeciona;
    }

    @XmlTransient
    public List<Frequencia> getFrequenciaList() {
        return frequenciaList;
    }

    public void setFrequenciaList(List<Frequencia> frequenciaList) {
        this.frequenciaList = frequenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatricula != null ? idMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.idMatricula == null && other.idMatricula != null) || (this.idMatricula != null && !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "percistencia.Matricula[ idMatricula=" + idMatricula + " ]";
    }
    
}
