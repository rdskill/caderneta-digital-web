/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "nota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nota.buscarTudo", query = "SELECT n FROM Nota n"),
    @NamedQuery(name = "Nota.buscarPorIdNota", query = "SELECT n FROM Nota n WHERE n.idNota = :idNota"),
    @NamedQuery(name = "Nota.buscarPorNota", query = "SELECT n FROM Nota n WHERE n.nota = :nota")})
public class Nota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNota")
    private Integer idNota;
    @Basic(optional = false)
    @Column(name = "nota")
    private float nota;
    @JoinColumn(name = "idMatricula", referencedColumnName = "idMatricula")
    @ManyToOne(optional = false)
    private Matricula idMatricula;
    @JoinColumn(name = "idTipoNota", referencedColumnName = "idTipoNota")
    @ManyToOne(optional = false)
    private TipoNota idTipoNota;

    public Nota() {
    }

    public Nota(Integer idNota) {
        this.idNota = idNota;
    }

    public Nota(Integer idNota, float nota) {
        this.idNota = idNota;
        this.nota = nota;
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Matricula getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Matricula idMatricula) {
        this.idMatricula = idMatricula;
    }

    public TipoNota getIdTipoNota() {
        return idTipoNota;
    }

    public void setIdTipoNota(TipoNota idTipoNota) {
        this.idTipoNota = idTipoNota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNota != null ? idNota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nota)) {
            return false;
        }
        Nota other = (Nota) object;
        if ((this.idNota == null && other.idNota != null) || (this.idNota != null && !this.idNota.equals(other.idNota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "percistencia.Nota[ idNota=" + idNota + " ]";
    }
    
}
