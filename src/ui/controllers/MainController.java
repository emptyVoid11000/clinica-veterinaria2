package ui.controllers;

import javax.swing.JOptionPane;
import model.Usuario;
import services.UsuarioService;
import ui.views.FormularioUserView;
import ui.views.GestionUsuariosView;
import ui.views.MainView;
import util.SessionManager;

public class MainController {
    private final MainView mainView;
    private final UsuarioService usuarioService;
    private final FormularioUserView addUserView;
    

    public MainController(MainView mainView, UsuarioService usuarioService, FormularioUserView addUserView) {
        this.mainView = mainView;
        this.usuarioService = usuarioService;
        this.addUserView = addUserView;

        
        this.mainView.addGestionUsuariosListener(e -> abrirGestionUsuarios());
        this.mainView.addCerrarSesionListener(e -> cerrarSesion());
        this.mainView.addReestablecerContrasenaListener(e -> reestablecerContrasena());
    }

    public void iniciar() {
        mainView.setVisible(true);
    }

    private void abrirGestionUsuarios() {
        GestionUsuariosView gestionView = new GestionUsuariosView();
        GestionUsuariosController gestionController = new GestionUsuariosController(gestionView, usuarioService, addUserView);
        gestionController.iniciar();
    }

    private void reestablecerContrasena(){
        Usuario usuario = SessionManager.getUsuarioActual();
        MainView view = new MainView();
        String nuevaContrasena = JOptionPane.showInputDialog(view, "Nueva contrasena:", "Reestablecer Contraseña", JOptionPane.PLAIN_MESSAGE);

        usuarioService.restablecerContrasena(usuario.getCorreo(), nuevaContrasena);
    }

    private void cerrarSesion() {
        
        mainView.dispose();
        System.exit(0); 
    }
}