package ui.controllers;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Usuario;
import services.UsuarioService;
import ui.views.MenuBusquedaView;
import services.MascotaService;
import ui.views.GestionMascotasView;

public class MenuBusquedaController {
    private final UsuarioService usuarioService;
    private final MenuBusquedaView mainView;
    private final MascotaService mascotaService;
    private final RegistrarMascotaController registrarMascotaController;

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
        if (correo.isEmpty()) {
            mainView.mostrarMensaje("Pon un correo primero");
            return;
        }

        Usuario usuario = usuarioService.buscarUsuarioPorCorreo(correo);
        DefaultListModel<String> modelo = mainView.getModeloLista();
        modelo.clear();

        if (usuario == null) {
            mainView.mostrarMensaje("Usuario no encontrado");
        } else {
            modelo.addElement(usuario.getCorreo());
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


        GestionMascotasView view = new GestionMascotasView();
        GestionMascotasController gestionMascotasController =
                new GestionMascotasController(view, mascotaService, registrarMascotaController, usuario.getId());

        gestionMascotasController.iniciar();
    }

    public void iniciar() {
        mainView.setVisible(true);
    }
}

