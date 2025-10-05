package services;

import model.Usuario;
import repositories.UsuarioRepository;
import util.PasswordUtil;
import util.SessionManager;

public class AutenticacionService {
    private final UsuarioRepository usuarioRepository;
    //private Usuario usuarioAutenticado;

    public AutenticacionService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        //this.usuarioAutenticado = SessionManager.getUsuarioActual();
    }

    public boolean iniciarSesion(String correo, String contrasena) {
        

        Usuario usuario = usuarioRepository.buscarPorCorreo(correo);
        if(usuario == null ) return false;
        byte[] salt = PasswordUtil.base64ToSalt(usuario.getSalt());
        
        if (usuario.isActivo()&&PasswordUtil.verifyPassword(contrasena, usuario.getPasswordHash(), salt)) {
            SessionManager.setUsuarioActual(usuario); 
            return true;    
        }
        return false;
    }

    public void cerrarSesion() {
        SessionManager.cerrarSesion();
    }
/*
    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    } */
}