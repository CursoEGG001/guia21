/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package live.egg.estancia.web.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author pc
 */
@Entity
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(length = 50)
    private String calle;
    @Basic(optional = false)
    @Column(nullable = false)
    private int numero;
    @Column(name = "codigo_postal", length = 10)
    private String codigoPostal;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String ciudad;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String pais;
    @Column(length = 50)
    private String email;
    private Boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente", fetch = FetchType.LAZY)
    private Collection<Estancias> estanciasCollection;

    public Clientes(Integer idCliente, String nombre, String calle, int numero, String codigoPostal, String ciudad, String pais, String email, Boolean active, Collection<Estancias> estanciasCollection) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.pais = pais;
        this.email = email;
        this.active = active;
        this.estanciasCollection = estanciasCollection;
    }

    public Clientes() {
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Estancias> getEstanciasCollection() {
        return estanciasCollection;
    }

    public void setEstanciasCollection(Collection<Estancias> estanciasCollection) {
        this.estanciasCollection = estanciasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "live.egg.estancia.web.entidades.Clientes[ idCliente=" + idCliente + " ]";
    }

    public void setActive(Boolean active) {
        this.active = active; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean getActive() {
        return active; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
