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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author pc
 */
@Entity
public class Casas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_casa", nullable = false)
    private Long idCasa;
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
    @Basic(optional = false)
    @Column(name = "fecha_desde", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Basic(optional = false)
    @Column(name = "fecha_hasta", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @Basic(optional = false)
    @Column(name = "tiempo_minimo", nullable = false)
    private int tiempoMinimo;
    @Basic(optional = false)
    @Column(name = "tiempo_maximo", nullable = false)
    private int tiempoMaximo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio_habitacion", nullable = false, precision = 15, scale = 2)
    private BigDecimal precioHabitacion;
    @Basic(optional = false)
    @Column(name = "tipo_vivienda", nullable = false, length = 30)
    private String tipoVivienda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCasa", fetch = FetchType.LAZY)
    private Collection<Estancias> estanciasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCasaFamilia", fetch = FetchType.LAZY)
    private Collection<Familias> familiasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCasa", fetch = FetchType.LAZY)
    private Collection<Comentarios> comentariosCollection;
    private Boolean active;

    public Casas() {
    }

    public Casas(Long idCasa, String calle, int numero, String codigoPostal, String ciudad, String pais, Date fechaDesde, Date fechaHasta, int tiempoMinimo, int tiempoMaximo, BigDecimal precioHabitacion, String tipoVivienda, Collection<Estancias> estanciasCollection, Collection<Familias> familiasCollection, Collection<Comentarios> comentariosCollection, Boolean active) {
        this.idCasa = idCasa;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.pais = pais;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.tiempoMinimo = tiempoMinimo;
        this.tiempoMaximo = tiempoMaximo;
        this.precioHabitacion = precioHabitacion;
        this.tipoVivienda = tipoVivienda;
        this.estanciasCollection = estanciasCollection;
        this.familiasCollection = familiasCollection;
        this.comentariosCollection = comentariosCollection;
        this.active = active;
    }

    

    public Long getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Long idCasa) {
        this.idCasa = idCasa;
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

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getTiempoMinimo() {
        return tiempoMinimo;
    }

    public void setTiempoMinimo(int tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }

    public int getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(int tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public BigDecimal getPrecioHabitacion() {
        return precioHabitacion;
    }

    public void setPrecioHabitacion(BigDecimal precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public Collection<Estancias> getEstanciasCollection() {
        return estanciasCollection;
    }

    public void setEstanciasCollection(Collection<Estancias> estanciasCollection) {
        this.estanciasCollection = estanciasCollection;
    }

    public Collection<Familias> getFamiliasCollection() {
        return familiasCollection;
    }

    public void setFamiliasCollection(Collection<Familias> familiasCollection) {
        this.familiasCollection = familiasCollection;
    }

    public Collection<Comentarios> getComentariosCollection() {
        return comentariosCollection;
    }

    public void setComentariosCollection(Collection<Comentarios> comentariosCollection) {
        this.comentariosCollection = comentariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCasa != null ? idCasa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Casas)) {
            return false;
        }
        Casas other = (Casas) object;
        if ((this.idCasa == null && other.idCasa != null) || (this.idCasa != null && !this.idCasa.equals(other.idCasa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "live.egg.estancia.web.entidades.Casas[ idCasa=" + idCasa + " ]";
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }

}
