package ui.controllers;

import javax.swing.JOptionPane;
import model.Rol;
import model.Usuario;
import repositories.JsonMascotaRepository;
import repositories.MascotaRepository;
import services.MascotaService;
import services.UsuarioService;
import ui.views.*;
import util.SessionManager;
;

public class MainController {
    private final MainView mainView;
    private final UsuarioService usuarioService;
    private final FormularioUserView addUserView;
    private final RegistrarMascotaView registrarMascotaView;
    
    public MainController(MainView mainView, UsuarioService usuarioService, FormularioUserView addUserView, RegistrarMascotaView registrarMascotaView) {
        this.mainView = mainView;
        this.usuarioService = usuarioService;
        this.addUserView = addUserView;
        this.registrarMascotaView = registrarMascotaView;
        
        this.mainView.addGestionUsuariosListener(e -> abrirGestionUsuarios());
        this.mainView.addCerrarSesionListener(e -> cerrarSesion());
        this.mainView.addReestablecerContrasenaListener(e -> reestablecerContrasena());
        this.mainView.addMenuItemRegistrarMascota(e -> registrarMascota());
        this.mainView.addMenuItemBusquedaMascota(e -> buscarMascota());
        this.saludo();
    }

    public void iniciar() {
        mainView.setVisible(true);
    }

    private void abrirGestionUsuarios() {
        GestionUsuariosView gestionView = new GestionUsuariosView();
        GestionUsuariosController gestionController = new GestionUsuariosController(gestionView, usuarioService, addUserView);
        gestionController.iniciar();
    }

    private void reestablecerContrasena() {
        Usuario usuario = SessionManager.getUsuarioActual();
        String nuevaContrasena = JOptionPane.showInputDialog(mainView, "Nueva contraseña:", "Reestablecer Contraseña", JOptionPane.PLAIN_MESSAGE);

        if (nuevaContrasena != null && !nuevaContrasena.isEmpty()) {
            usuarioService.restablecerContrasena(usuario.getCorreo(), nuevaContrasena);
            JOptionPane.showMessageDialog(mainView, "Contraseña actualizada con éxito.");
        }
    }

    private void registrarMascota() {
        MascotaService mascotaService = new MascotaService(new JsonMascotaRepository());
        RegistrarMascotaController controller = new RegistrarMascotaController(new RegistrarMascotaView(), mascotaService, null, null);
        controller.iniciar();
    }

    private void buscarMascota() {
        MascotaRepository mascotaRepository = new JsonMascotaRepository();
        MascotaService mascotaService = new MascotaService(mascotaRepository);

        if (SessionManager.getUsuarioActual().getRol() != Rol.AUXILIAR) {
            MenuBusquedaView menuBusquedaView = new MenuBusquedaView();
            RegistrarMascotaView registrarMascotaView = new RegistrarMascotaView();

            GestionMascotasView gestionMascotasView = new GestionMascotasView();

            GestionMascotasController gestionMascotasController= new GestionMascotasController(gestionMascotasView, mascotaService, null);

            RegistrarMascotaController registrarMascotaController = new RegistrarMascotaController(registrarMascotaView, mascotaService, null, gestionMascotasController);


            MenuBusquedaController controller = new MenuBusquedaController(usuarioService, menuBusquedaView, mascotaService, registrarMascotaController);
            controller.iniciar();

        } else {
            GestionMascotasView gestionMascotasView = new GestionMascotasView();
            GestionMascotasController controller = new GestionMascotasController(
                gestionMascotasView,
                mascotaService,
                SessionManager.getUsuarioActual().getId()
            );
            controller.iniciar();
        }
    }

    private void saludo(){
        mainView.cambiarNombre(SessionManager.getUsuarioActual().getNombre());
    }

    private void cerrarSesion() {
        mainView.dispose();
        System.exit(0);
    }
}
