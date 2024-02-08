/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package live.egg.estancia.web.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

/**
 *
 * @author pc
 */
@Entity
public class Familias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_familia", nullable = false)
    private Integer idFamilia;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "edad_minima", nullable = false)
    private int edadMinima;
    @Basic(optional = false)
    @Column(name = "edad_maxima", nullable = false)
    private int edadMaxima;
    @Basic(optional = false)
    @Column(name = "num_hijos", nullable = false)
    private int numHijos;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String email;
    @JoinColumn(name = "id_casa_familia", referencedColumnName = "id_casa", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Casas idCasaFamilia;
    private Boolean active;

    public Familias() {
    }

    public Familias(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public Familias(Integer idFamilia, String nombre, int edadMinima, int edadMaxima, int numHijos, String email) {
        this.idFamilia = idFamilia;
        this.nombre = nombre;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
        this.numHijos = numHijos;
        this.email = email;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public int getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(int edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public int getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Casas getIdCasaFamilia() {
        return idCasaFamilia;
    }

    public void setIdCasaFamilia(Casas idCasaFamilia) {
        this.idCasaFamilia = idCasaFamilia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamilia != null ? idFamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familias)) {
            return false;
        }
        Familias other = (Familias) object;
        if ((this.idFamilia == null && other.idFamilia != null) || (this.idFamilia != null && !this.idFamilia.equals(other.idFamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "live.egg.estancia.web.entidades.Familias[ idFamilia=" + idFamilia + " ]";
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
