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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pc
 */
@Entity
public class Estancias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estancia", nullable = false)
    private Integer idEstancia;
    @Basic(optional = false)
    @Column(name = "nombre_huesped", nullable = false, length = 70)
    private String nombreHuesped;
    @Basic(optional = false)
    @Column(name = "fecha_desde", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Basic(optional = false)
    @Column(name = "fecha_hasta", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @JoinColumn(name = "id_casa", referencedColumnName = "id_casa", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Casas idCasa;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clientes idCliente;
    private Boolean active;

    public Estancias() {
    }

    public Estancias(Integer idEstancia) {
        this.idEstancia = idEstancia;
    }

    public Estancias(Integer idEstancia, String nombreHuesped, Date fechaDesde, Date fechaHasta) {
        this.idEstancia = idEstancia;
        this.nombreHuesped = nombreHuesped;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public Integer getIdEstancia() {
        return idEstancia;
    }

    public void setIdEstancia(Integer idEstancia) {
        this.idEstancia = idEstancia;
    }

    public String getNombreHuesped() {
        return nombreHuesped;
    }

    public void setNombreHuesped(String nombreHuesped) {
        this.nombreHuesped = nombreHuesped;
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

    public Casas getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Casas idCasa) {
        this.idCasa = idCasa;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstancia != null ? idEstancia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estancias)) {
            return false;
        }
        Estancias other = (Estancias) object;
        if ((this.idEstancia == null && other.idEstancia != null) || (this.idEstancia != null && !this.idEstancia.equals(other.idEstancia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "live.egg.estancia.web.entidades.Estancias[ idEstancia=" + idEstancia + " ]";
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }

}
