package model;
import java.util.UUID;

public class Mascota {
    private String nombre;
    private UUID id;
    private UUID duenoId;
    private String especie;
    private String raza;
    private String sexo;
    private int edad;
    private float peso;
    private String vacunas;
    private String alergias;

    public Mascota(String nombre, UUID id,UUID duenoId, String especie, String raza, String sexo, int edad, float peso, String vacunas, String alergias) {
        this.nombre = nombre;
        this.id = id;
        this.duenoId = duenoId;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.edad = edad;
        this.peso = peso;
        this.vacunas = vacunas;
        this.alergias = alergias;
    }

    public String getNombre(){
        return nombre;
    }

    public UUID getId(){
        return id;
    }

    public UUID getDuenoId(){
        return duenoId;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public String getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public float getPeso() {
        return peso;
    }

    public String getVacunas() {
        return vacunas;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public void setDuenoId(UUID duenoId){
        this.duenoId = duenoId;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setVacunas(String vacunas) {
        this.vacunas = vacunas;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }
}

