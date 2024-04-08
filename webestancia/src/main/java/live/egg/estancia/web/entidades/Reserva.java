/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package live.egg.estancia.web.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author pc
 */
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "alquiler", referencedColumnName = "id_casa")
    private Casas alquiler;
    @Temporal(TemporalType.DATE)
    private Date fechaLlegada;
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    private Boolean active;

    public Reserva() {
    }

    public Reserva(Long id, Casas alquiler, Date fechaLlegada, Date fechaSalida, Boolean active) {
        this.id = id;
        this.alquiler = alquiler;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reserva other = (Reserva) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + '}';
    }

    public Casas getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Casas alquiler) {
        this.alquiler = alquiler;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public Long getId() {
        return id;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

}
