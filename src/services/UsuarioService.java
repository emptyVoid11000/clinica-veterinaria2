package services;

import java.util.List;
import java.util.UUID;
import model.Rol;
import model.Usuario;
import repositories.UsuarioRepository;
import util.PasswordUtil;
import util.SessionManager;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void crearUsuario(UUID id, String nombre, String correo, String contrasena, Rol rol) {
        if (usuarioRepository.buscarPorId(id) == null) {
            byte[] salt = PasswordUtil.generateSalt();
            String hash = PasswordUtil.hashPassword(contrasena, salt);
            String saltS = PasswordUtil.saltToBase64(salt);

            Usuario nuevoUsuario = new Usuario(id, nombre, correo, hash, saltS , rol);
            usuarioRepository.agregar(nuevoUsuario);

            if(SessionManager.getUsuarioActual()==null) SessionManager.setUsuarioActual(nuevoUsuario);
        } else {
            System.out.println("Error: El usuario ya existe.");
        }
    }
    
    // --- NUEVOS MÉTODOS ---
    public Usuario buscarUsuarioPorId(UUID id) {
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        return usuarioRepository.buscarPorCorreo(correo);
    }
    
    public void actualizarUsuario(Usuario usuario) {
        byte[] salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(usuario.getPasswordHash(), salt);
        String saltS = PasswordUtil.saltToBase64(salt);

        usuario.setPasswordhash(hash);
        usuario.setSalt(saltS);
        usuarioRepository.actualizar(usuario);
    }

    public void cambiarEstadoUsuario(UUID id) {
        Usuario usuario = usuarioRepository.buscarPorId(id);
        if (usuario != null) {
            usuario.setActivo(!usuario.isActivo()); // Invierte el estado actual
            usuarioRepository.actualizar(usuario);
        }
    }

    
    
    // --- MÉTODOS EXISTENTES ---
    public void restablecerContrasena(String correo, String nuevaContrasena) {
        Usuario usuario = usuarioRepository.buscarPorCorreo(correo);
        if (usuario != null) {
            byte[] salt = PasswordUtil.generateSalt();
            String hash = PasswordUtil.hashPassword(nuevaContrasena, salt);
            String saltS = PasswordUtil.saltToBase64(salt);

            usuario.setPasswordhash(hash);
            usuario.setSalt(saltS);
            usuarioRepository.actualizar(usuario);
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarTodos();
    }
}