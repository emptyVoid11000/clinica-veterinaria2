package repositories;
import model.Mascota;
import model.Usuario;
import java.util.List;


public interface MascotaRepository {
    void agregarMascota(Mascota mascota);
    void actualizarMascota(Mascota mascota);
    List <Mascota> buscarMascotaPorNombre(String nombre);
    List<Mascota> listarMascotas(Usuario dueno);
    }

