/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package live.egg.estancia.web.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author pc
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Casas.findAll", query = "SELECT c FROM Casas c"),
    @NamedQuery(name = "Casas.findByIdCasa", query = "SELECT c FROM Casas c WHERE c.idCasa = :idCasa"),
    @NamedQuery(name = "Casas.findByCalle", query = "SELECT c FROM Casas c WHERE c.calle = :calle"),
    @NamedQuery(name = "Casas.findByNumero", query = "SELECT c FROM Casas c WHERE c.numero = :numero"),
    @NamedQuery(name = "Casas.findByCodigoPostal", query = "SELECT c FROM Casas c WHERE c.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "Casas.findByCiudad", query = "SELECT c FROM Casas c WHERE c.ciudad = :ciudad"),
    @NamedQuery(name = "Casas.findByPais", query = "SELECT c FROM Casas c WHERE c.pais = :pais"),
    @NamedQuery(name = "Casas.findByFechaDesde", query = "SELECT c FROM Casas c WHERE c.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "Casas.findByFechaHasta", query = "SELECT c FROM Casas c WHERE c.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "Casas.findByTiempoMinimo", query = "SELECT c FROM Casas c WHERE c.tiempoMinimo = :tiempoMinimo"),
    @NamedQuery(name = "Casas.findByTiempoMaximo", query = "SELECT c FROM Casas c WHERE c.tiempoMaximo = :tiempoMaximo"),
    @NamedQuery(name = "Casas.findByPrecioHabitacion", query = "SELECT c FROM Casas c WHERE c.precioHabitacion = :precioHabitacion"),
    @NamedQuery(name = "Casas.findByTipoVivienda", query = "SELECT c FROM Casas c WHERE c.tipoVivienda = :tipoVivienda")})
public class Casas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_casa")
    private Long idCasa;
    private String calle;
    @Basic(optional = false)
    private int numero;
    @Column(name = "codigo_postal")
    private String codigoPostal;
    @Basic(optional = false)
    private String ciudad;
    @Basic(optional = false)
    private String pais;
    @Basic(optional = false)
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    private Date fechaDesde;
    @Basic(optional = false)
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    private Date fechaHasta;
    @Basic(optional = false)
    @Column(name = "tiempo_minimo")
    private int tiempoMinimo;
    @Basic(optional = false)
    @Column(name = "tiempo_maximo")
    private int tiempoMaximo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio_habitacion")
    private BigDecimal precioHabitacion;
    @Basic(optional = false)
    @Column(name = "tipo_vivienda")
    private String tipoVivienda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCasa", fetch = FetchType.LAZY)
    private List<Estancias> estanciasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCasaFamilia", fetch = FetchType.LAZY)
    private List<Familias> familiasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCasa", fetch = FetchType.LAZY)
    private List<Comentarios> comentariosList;
    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Casas() {
    }

    public Casas(Long idCasa) {
        this.idCasa = idCasa;
    }

    public Casas(Long idCasa, String calle, int numero, String codigoPostal, String ciudad, String pais, Date fechaDesde, Date fechaHasta, int tiempoMinimo, int tiempoMaximo, BigDecimal precioHabitacion, String tipoVivienda, Boolean active) {
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

}
