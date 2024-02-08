/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package live.egg.estancia.web.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author pc
 */

@Entity
public class Reserva {

    @Id
    private Long id;
    private List<Estancias> alquiler;
    private Date fechaLlegada;
    private Date fechaSalida;
    private Boolean active;

    public Reserva() {
    }

    public Reserva(Long id, List<Estancias> alquiler, Date fechaLlegada, Date fechaSalida, Boolean active) {
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

    public void setAlquiler(List<Estancias> alquiler) {
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

    public List<Estancias> getAlquiler() {
        return alquiler;
    }

}
