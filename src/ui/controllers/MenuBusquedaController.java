package ui.controllers;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Usuario;
import services.UsuarioService;
import ui.views.MenuBusquedaView;
import services.MascotaService;
import ui.views.GestionMascotasView;
import java.util.List;
import model.Rol;

public class MenuBusquedaController {
    private final UsuarioService usuarioService;
    private final MenuBusquedaView mainView;
    private final MascotaService mascotaService;
    private final RegistrarMascotaController registrarMascotaController;
    private Usuario usuarioSelec;

    public MenuBusquedaController(
            UsuarioService usuarioService,
            MenuBusquedaView mainView,
            MascotaService mascotaService,
            RegistrarMascotaController registrarMascotaController
    ) {
        this.usuarioService = usuarioService;
        this.mainView = mainView;
        this.mascotaService = mascotaService;
        this.registrarMascotaController = registrarMascotaController;

        // Botón buscar
        this.mainView.btnBuscarListener(e -> cargarUsuarios());
        // Selección de usuario
        seleccionarUsuario();
    }

    public void cargarUsuarios() {
        String correo = mainView.getCorreo().trim();
        if (correo==null) {
            mainView.mostrarMensaje("Pon un nombre primero");
            return;
        }

        List<Usuario> usuarios = usuarioService.buscarPorCorreoVarios(correo);

        DefaultListModel<String> modelo = mainView.getModeloLista();
        modelo.clear();

        if (usuarios.isEmpty()) {
            mainView.mostrarMensaje("Usuario no encontrado");
        } else {
            for(Usuario u:usuarios){
                if(u.getRol()==Rol.AUXILIAR){modelo.addElement(u.getCorreo());}
            }
        }

        mainView.recargar();
    }

    private void seleccionarUsuario() {
        mainView.getLista().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String seleccionado = mainView.getLista().getSelectedValue();
                    if (seleccionado != null) {
                        cargarMascotas(seleccionado);
                    }
                }
            }
        });
    }

    private void cargarMascotas(String correo) {
        mainView.setVisible(false);
        Usuario usuario = usuarioService.buscarUsuarioPorCorreo(correo);
        if (usuario == null) {
            mainView.mostrarMensaje("No se pudo cargar el usuario.");
            mainView.setVisible(true);
            return;
        }
        //usuarioSelec= new Usuario(usuario.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getPasswordHash(), usuario.getSalt(), usuario.getRol());




        GestionMascotasView view = new GestionMascotasView();
        GestionMascotasController gestionMascotasController =
                new GestionMascotasController(view, mascotaService, usuario.getId());

        gestionMascotasController.iniciar();
    }

    public void iniciar() {
        mainView.setVisible(true);
    }
}

