package services;

import java.util.List;
import java.util.UUID;
import model.Mascota;
import model.Usuario;
import repositories.MascotaRepository;

public class MascotaService {
    private final MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRespository){
        this.mascotaRepository = mascotaRespository;
    }

    public void crearMascota( UUID duenoId,
    String nombre,
    String especie,
    String raza,
    String sexo,
    int edad,
    float peso,
    String vacunas,
    String alergias){
       Mascota nuevaMascota = new Mascota(
        nombre,
        UUID.randomUUID(),
        duenoId,
        especie,
        raza,
        sexo,
        edad,
        peso,
        vacunas,
        alergias);
        mascotaRepository.agregarMascota(nuevaMascota);
    }

    public void actualizarMascota(Mascota mascota){
        mascotaRepository.actualizarMascota(mascota);
    }

    public List<Mascota> buscarMascotaPorNombre(String nombre){
        return mascotaRepository.buscarMascotaPorNombre(nombre);
    }

    public List<Mascota> listarMascotasDeUnDueno(UUID duenoId){
        return mascotaRepository.listarMascotas(duenoId);
    }

    public Mascota buscarMascotaPorId(UUID id){
        return mascotaRepository.buscarMascotaPorId(id);
    }
}
