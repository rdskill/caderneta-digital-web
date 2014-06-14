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
@Table(name = "usuario_secretaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioSecretaria.buscarTudo", query = "SELECT u FROM UsuarioSecretaria u"),
    @NamedQuery(name = "UsuarioSecretaria.buscarPorIdUsuarioSecretaria", query = "SELECT u FROM UsuarioSecretaria u WHERE u.idUsuarioSecretaria = :idUsuarioSecretaria"),
    @NamedQuery(name = "UsuarioSecretaria.buscarPorNomeUsuario", query = "SELECT u FROM UsuarioSecretaria u WHERE u.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "UsuarioSecretaria.buscarPorSenha", query = "SELECT u FROM UsuarioSecretaria u WHERE u.senha = :senha")})
public class UsuarioSecretaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuarioSecretaria")
    private Integer idUsuarioSecretaria;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioSecretaria")
    private List<Notificacao> notificacaoList;

    public UsuarioSecretaria() {
    }

    public UsuarioSecretaria(Integer idUsuarioSecretaria) {
        this.idUsuarioSecretaria = idUsuarioSecretaria;
    }

    public UsuarioSecretaria(Integer idUsuarioSecretaria, String nome, String nomeUsuario, String senha) {
        this.idUsuarioSecretaria = idUsuarioSecretaria;
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public Integer getIdUsuarioSecretaria() {
        return idUsuarioSecretaria;
    }

    public void setIdUsuarioSecretaria(Integer idUsuarioSecretaria) {
        this.idUsuarioSecretaria = idUsuarioSecretaria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @XmlTransient
    public List<Notificacao> getNotificacaoList() {
        return notificacaoList;
    }

    public void setNotificacaoList(List<Notificacao> notificacaoList) {
        this.notificacaoList = notificacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioSecretaria != null ? idUsuarioSecretaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioSecretaria)) {
            return false;
        }
        UsuarioSecretaria other = (UsuarioSecretaria) object;
        if ((this.idUsuarioSecretaria == null && other.idUsuarioSecretaria != null) || (this.idUsuarioSecretaria != null && !this.idUsuarioSecretaria.equals(other.idUsuarioSecretaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "percistencia.UsuarioSecretaria[ idUsuarioSecretaria=" + idUsuarioSecretaria + " ]";
    }
    
}
