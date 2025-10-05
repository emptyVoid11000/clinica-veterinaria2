package model;
import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nombre;
    private String correo;
    private String passwordHash;
    private String salt;  
    private Rol rol;
    private boolean activo;

    public Usuario(UUID id, String nombre, String correo, String passwordHash, String salt, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.rol = rol;
        this.activo = true; 
    }

    // --- Getters y Setters ---
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    
    public String getCorreo() {
        return correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public String getSalt() {
        return salt;
    }

    public void setPasswordhash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

        public void setSalt(String salt) {
        this.salt = salt;
    }



    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}