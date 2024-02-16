package galli.challenge.models;


import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String newsletter;
    private String tipoSubscripcion;
    private String estadoSubscripcion;

    // Constructors, Getters, and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    public String getTipoSubscripcion() {
        return tipoSubscripcion;
    }

    public void setTipoSubscripcion(String tipoSubscripcion) {
        this.tipoSubscripcion = tipoSubscripcion;
    }

    public String getEstadoSubscripcion() {
        return estadoSubscripcion;
    }

    public void setEstadoSubscripcion(String estadoSubscripcion) {
        this.estadoSubscripcion = estadoSubscripcion;
    }
}