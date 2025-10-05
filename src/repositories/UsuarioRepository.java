package repositories;

import java.util.List;
import java.util.UUID;
import model.Usuario;

public interface UsuarioRepository {
    void agregar(Usuario usuario);
    void actualizar(Usuario usuario);
    Usuario buscarPorId(UUID id);
    Usuario buscarPorCorreo(String correo);
    List<Usuario> listarTodos();
}