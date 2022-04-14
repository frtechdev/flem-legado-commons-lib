/*
 * UsuarioExterno.java
 *
 * Created on 27 de Novembro de 2006, 17:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
 
package br.org.flem.fwe.persistencia.dto;
 
import br.org.flem.fwe.service.IUsuarioExterno;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

/**
 *
 * @author mjpereira
 */
@DataTransferObject
public class UsuarioExterno implements IUsuarioExterno, Serializable {
    
    @RemoteProperty
    private Integer id;
    
    @RemoteProperty
    private String nome;
    
    private String username;
    /**
     * matricula = numero do prontuario ConsisteHR
     * Apenas Funcionarios possuem numero de matricula
     * */
    @RemoteProperty
    private Integer matriculaHR;
    
    @RemoteProperty
    private Integer codigoDominio;

    private String lotacao;
    private Integer lotacaoDominio;
    private Boolean ativo;
    
    private Collection<String> permissoes = new HashSet<String>();

    @Override
    public Collection<String> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Collection<String> permissoes) {
        this.permissoes = permissoes;
    }
    
    /** Creates a new instance of UsuarioExterno */
    public UsuarioExterno() {
    }
    @Override
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public Integer getMatriculaHR() {
        return matriculaHR;
    }
    
    public void setMatriculaHR(Integer matriculaHR) {
        this.matriculaHR = matriculaHR;
    }

    @Override
    public Integer getCodigoDominio() {
        return codigoDominio;
    }

    public void setCodigoDominio(Integer codigoDominio) {
        this.codigoDominio = codigoDominio;
    }

    public int compareTo(Object o) {
        return this.getNome().compareToIgnoreCase(((UsuarioExterno)o).getNome());
    }
    @Override
    public String getLotacao() {
        return lotacao;
    }
    
    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public Integer getLotacaoDominio() {
        return lotacaoDominio;
    }

    public void setLotacaoDominio(Integer lotacaoDominio) {
        this.lotacaoDominio = lotacaoDominio;
    }

    @Override
    public Boolean getAtivo() {
        return ativo;
    }
    
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
}
