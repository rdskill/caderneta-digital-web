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
@Table(name = "ciclo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciclo.buscarTudo", query = "SELECT c FROM Ciclo c"),
    @NamedQuery(name = "Ciclo.buscarPorIdCiclo", query = "SELECT c FROM Ciclo c WHERE c.idCiclo = :idCiclo"),
    @NamedQuery(name = "Ciclo.buscarPorCiclo", query = "SELECT c FROM Ciclo c WHERE c.ciclo = :ciclo")})
public class Ciclo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCiclo")
    private Integer idCiclo;
    @Column(name = "ciclo")
    private Integer ciclo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCiclo")
    private List<CicloDisciplina> cicloDisciplinaList;
    @JoinColumn(name = "idCurso", referencedColumnName = "idCurso")
    @ManyToOne
    private Curso idCurso;

    public Ciclo() {
    }

    public Ciclo(Integer idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Integer getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Integer idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    @XmlTransient
    public List<CicloDisciplina> getCicloDisciplinaList() {
        return cicloDisciplinaList;
    }

    public void setCicloDisciplinaList(List<CicloDisciplina> cicloDisciplinaList) {
        this.cicloDisciplinaList = cicloDisciplinaList;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCiclo != null ? idCiclo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciclo)) {
            return false;
        }
        Ciclo other = (Ciclo) object;
        if ((this.idCiclo == null && other.idCiclo != null) || (this.idCiclo != null && !this.idCiclo.equals(other.idCiclo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "percistencia.Ciclo[ idCiclo=" + idCiclo + " ]";
    }
    
}
