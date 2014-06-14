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
@Table(name = "notificacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacao.buscarTudo", query = "SELECT n FROM Notificacao n"),
    @NamedQuery(name = "Notificacao.buscarPorIdNotificacao", query = "SELECT n FROM Notificacao n WHERE n.idNotificacao = :idNotificacao"),
    @NamedQuery(name = "Notificacao.buscarPorTitulo", query = "SELECT n FROM Notificacao n WHERE n.titulo like :titulo"),
    @NamedQuery(name = "Notificacao.buscarPorData", query = "SELECT n FROM Notificacao n WHERE n.data = :data")})
public class Notificacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNotificacao")
    private Integer idNotificacao;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "conteudo")
    private String conteudo;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "dataEvento")
    @Temporal(TemporalType.DATE)
    private Date dataEvento;
    @JoinColumn(name = "idUsuarioSecretaria", referencedColumnName = "idUsuarioSecretaria")
    @ManyToOne(optional = false)
    private UsuarioSecretaria idUsuarioSecretaria;

    public Notificacao() {
    }

    public Notificacao(Integer idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public Notificacao(Integer idNotificacao, String titulo, String conteudo, Date data, Date dataEvento) {
        this.idNotificacao = idNotificacao;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.data = data;
        this.dataEvento = dataEvento;
    }

    public Integer getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(Integer idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public UsuarioSecretaria getIdUsuarioSecretaria() {
        return idUsuarioSecretaria;
    }

    public void setIdUsuarioSecretaria(UsuarioSecretaria idUsuarioSecretaria) {
        this.idUsuarioSecretaria = idUsuarioSecretaria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacao != null ? idNotificacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Notificacao)) {
            return false;
        }
        Notificacao other = (Notificacao) object;
        if ((this.idNotificacao == null && other.idNotificacao != null) || (this.idNotificacao != null && !this.idNotificacao.equals(other.idNotificacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "percistencia.Notificacao[ idNotificacao=" + idNotificacao + " ]";
    }
    
}
