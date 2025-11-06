package ui.controllers;

import javax.swing.JOptionPane;
import model.Usuario;
import services.UsuarioService;
import ui.views.FormularioUserView;
import ui.views.GestionUsuariosView;
import ui.views.MainView;
import util.SessionManager;
import ui.views.RegistrarMascotaView;
import services.MascotaService;
import repositories.JsonMascotaRepository;
import ui.controllers.RegistrarMascotaController;
import model.Mascota;

public class MainController {
    private final MainView mainView;
    private final UsuarioService usuarioService;
    private final FormularioUserView addUserView;
    private final RegistrarMascotaView registrarMascotaView;
    
    public MainController(MainView mainView, UsuarioService usuarioService, FormularioUserView addUserView, RegistrarMascotaView registrarMascotaView ) {
        this.mainView = mainView;
        this.usuarioService = usuarioService;
        this.addUserView = addUserView;
        this.registrarMascotaView = registrarMascotaView;
        
        this.mainView.addGestionUsuariosListener(e -> abrirGestionUsuarios());
        this.mainView.addCerrarSesionListener(e -> cerrarSesion());
        this.mainView.addReestablecerContrasenaListener(e -> reestablecerContrasena());
        this.mainView.addMenuItemRegistrarMascota(e-> registrarMascota());
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

    private void registrarMascota(){
        System.out.println("Tilina");
        MascotaService mascotaService = new MascotaService(new JsonMascotaRepository());
        RegistrarMascotaController controller = new RegistrarMascotaController(new RegistrarMascotaView(), mascotaService);
        controller.iniciar();
    }

    private void cerrarSesion() {
        
        mainView.dispose();
        System.exit(0); 
    }
}