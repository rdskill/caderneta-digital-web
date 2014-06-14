/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "frequencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frequencia.buscarTudo", query = "SELECT f FROM Frequencia f"),
    @NamedQuery(name = "Frequencia.buscarPorIdFrequencia", query = "SELECT f FROM Frequencia f WHERE f.idFrequencia = :idFrequencia"),
    @NamedQuery(name = "Frequencia.buscarPorSituacao", query = "SELECT f FROM Frequencia f WHERE f.situacao = :situacao"),
    @NamedQuery(name = "Frequencia.buscarPorData", query = "SELECT f FROM Frequencia f WHERE f.data = :data"),
    @NamedQuery(name = "Frequencia.buscarPorIdMatricula", query = "SELECT f FROM Frequencia f WHERE f.idMatricula.idMatricula = :idMatricula"),
    @NamedQuery(name = "Frequencia.buscarPorMatriculaLeciona", query = "SELECT f FROM Frequencia f WHERE f.idMatricula.idLeciona.idLeciona = :idLeciona")})
public class Frequencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFrequencia")
    private Integer idFrequencia;
    @Basic(optional = false)
    @Column(name = "situacao")
    private String situacao;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "idMatricula", referencedColumnName = "idMatricula")
    @ManyToOne(optional = false)
    private Matricula idMatricula;

    public Frequencia() {
    }

    public Frequencia(Integer idFrequencia) {
        this.idFrequencia = idFrequencia;
    }

    public Frequencia(Integer idFrequencia, String situacao, Date data) {
        this.idFrequencia = idFrequencia;
        this.situacao = situacao;
        this.data = data;
    }

    public Integer getIdFrequencia() {
        return idFrequencia;
    }

    public void setIdFrequencia(Integer idFrequencia) {
        this.idFrequencia = idFrequencia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Matricula getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Matricula idMatricula) {
        this.idMatricula = idMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFrequencia != null ? idFrequencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frequencia)) {
            return false;
        }
        Frequencia other = (Frequencia) object;
        if ((this.idFrequencia == null && other.idFrequencia != null) || (this.idFrequencia != null && !this.idFrequencia.equals(other.idFrequencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "percistencia.Frequencia[ idFrequencia=" + idFrequencia + " ]";
    }
    
}
