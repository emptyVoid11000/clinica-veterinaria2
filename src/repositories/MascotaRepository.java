package repositories;
import java.util.List;
import java.util.UUID;
import model.Mascota;


public interface MascotaRepository {
    void agregarMascota(Mascota mascota);
    void actualizarMascota(Mascota mascota);
    List <Mascota> buscarMascotaPorNombre(String nombre);
    List<Mascota> listarMascotas(UUID duenoId);
    Mascota buscarMascotaPorId(UUID id);
    }

