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
@Table(name = "ciclo_disciplina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CicloDisciplina.buscarTudo", query = "SELECT c FROM CicloDisciplina c"),
    @NamedQuery(name = "CicloDisciplina.buscarPorIdCicloDisciplina", query = "SELECT c FROM CicloDisciplina c WHERE c.idCicloDisciplina = :idCicloDisciplina")})
public class CicloDisciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCicloDisciplina")
    private Integer idCicloDisciplina;
    @JoinColumn(name = "idCiclo", referencedColumnName = "idCiclo")
    @ManyToOne(optional = false)
    private Ciclo idCiclo;
    @JoinColumn(name = "idDisciplina", referencedColumnName = "idDisciplina")
    @ManyToOne(optional = false)
    private Disciplina idDisciplina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDisciplina")
    private List<Leciona> lecionaList;

    public CicloDisciplina() {
    }

    public CicloDisciplina(Integer idCicloDisciplina) {
        this.idCicloDisciplina = idCicloDisciplina;
    }

    public Integer getIdCicloDisciplina() {
        return idCicloDisciplina;
    }

    public void setIdCicloDisciplina(Integer idCicloDisciplina) {
        this.idCicloDisciplina = idCicloDisciplina;
    }

    public Ciclo getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Ciclo idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Disciplina getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Disciplina idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    @XmlTransient
    public List<Leciona> getLecionaList() {
        return lecionaList;
    }

    public void setLecionaList(List<Leciona> lecionaList) {
        this.lecionaList = lecionaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCicloDisciplina != null ? idCicloDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CicloDisciplina)) {
            return false;
        }
        CicloDisciplina other = (CicloDisciplina) object;
        if ((this.idCicloDisciplina == null && other.idCicloDisciplina != null) || (this.idCicloDisciplina != null && !this.idCicloDisciplina.equals(other.idCicloDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "percistencia.CicloDisciplina[ idCicloDisciplina=" + idCicloDisciplina + " ]";
    }
    
}
