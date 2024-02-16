package galli.challenge.models;
import jakarta.persistence.*;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
private String status;
private String email;
private String tipoSubscripcion;
private LocalDate fecha;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getTipoSubscripcion() {
    return tipoSubscripcion;
}

public void setTipoSubscripcion(String tipoSubscripcion) {
    this.tipoSubscripcion = tipoSubscripcion;
}

public LocalDate getFecha() {
    return fecha;
}

public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
}




}
