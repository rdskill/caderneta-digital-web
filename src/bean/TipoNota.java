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
@Table(name = "tipo_nota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNota.buscarTudo", query = "SELECT t FROM TipoNota t"),
    @NamedQuery(name = "TipoNota.buscarPorIdTipoNota", query = "SELECT t FROM TipoNota t WHERE t.idTipoNota = :idTipoNota"),
    @NamedQuery(name = "TipoNota.buscarPorTipoNota", query = "SELECT t FROM TipoNota t WHERE t.tipoNota = :tipoNota")})
public class TipoNota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoNota")
    private Integer idTipoNota;
    @Basic(optional = false)
    @Column(name = "tipoNota")
    private String tipoNota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoNota")
    private List<Nota> notaList;

    public TipoNota() {
    }

    public TipoNota(Integer idTipoNota) {
        this.idTipoNota = idTipoNota;
    }

    public TipoNota(Integer idTipoNota, String tipoNota) {
        this.idTipoNota = idTipoNota;
        this.tipoNota = tipoNota;
    }

    public Integer getIdTipoNota() {
        return idTipoNota;
    }

    public void setIdTipoNota(Integer idTipoNota) {
        this.idTipoNota = idTipoNota;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }

    @XmlTransient
    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoNota != null ? idTipoNota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNota)) {
            return false;
        }
        TipoNota other = (TipoNota) object;
        if ((this.idTipoNota == null && other.idTipoNota != null) || (this.idTipoNota != null && !this.idTipoNota.equals(other.idTipoNota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "percistencia.TipoNota[ idTipoNota=" + idTipoNota + " ]";
    }
    
}
