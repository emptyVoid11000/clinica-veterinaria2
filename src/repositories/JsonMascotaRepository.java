package repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Mascota;
import model.Usuario;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class JsonMascotaRepository implements MascotaRepository {

    private final String RUTA_ARCHIVO = "mascotas.json";
    private List<Mascota> mascotas;
    private final Gson gson;

    public JsonMascotaRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.mascotas = cargarDesdeArchivo();
    }

    private List<Mascota> cargarDesdeArchivo() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(archivo)) {
            Type tipoListaMascotas = new TypeToken<ArrayList<Mascota>>() {}.getType();
            List<Mascota> lista = gson.fromJson(reader, tipoListaMascotas);
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar las mascotas desde el archivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void guardarEnArchivo(List<Mascota> mascotasAGuardar) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(mascotasAGuardar, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar las mascotas en el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
        guardarEnArchivo(mascotas);
    }

    @Override
    public void actualizarMascota(Mascota mascota) {
        Optional<Mascota> mascotaExistente = mascotas.stream()
                .filter(u -> u.getId().equals(mascota.getId()))
                .findFirst();

        mascotaExistente.ifPresent(u -> {
            u.setNombre(mascota.getNombre());
            u.setEspecie(mascota.getEspecie());
            u.setRaza(mascota.getRaza());
            u.setSexo(mascota.getSexo());
            u.setEdad(mascota.getEdad());
            u.setPeso(mascota.getPeso());
            u.setVacunas(mascota.getVacunas());
            u.setAlergias(mascota.getAlergias());
            guardarEnArchivo(this.mascotas);
        });
    }

    @Override
    public List<Mascota> buscarMascotaPorNombre(String nombre) {
        return mascotas.stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Mascota> listarMascotas(UUID duenoId) {
        return mascotas.stream()
                .filter(u -> u.getDuenoId().equals(duenoId))
                .collect(Collectors.toList());
    }

    @Override
    public Mascota buscarMascotaPorId(UUID id) {
        return mascotas.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElse(null);}


}
