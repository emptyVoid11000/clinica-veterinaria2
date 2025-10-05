package ui.controllers;

import java.util.List;
import java.util.UUID;
import javax.swing.*;
import model.Rol;
import model.Usuario;
import services.UsuarioService;
import ui.views.FormularioUserView;
import ui.views.GestionUsuariosView;

public class GestionUsuariosController {
    private final GestionUsuariosView view;
    private final UsuarioService usuarioService;

    public GestionUsuariosController(GestionUsuariosView view, UsuarioService usuarioService, FormularioUserView addUserView) {
        this.view = view;
        this.usuarioService = usuarioService;

        
        this.view.addCrearListener(e -> crearUsuario());
        this.view.addEditarListener(e -> editarUsuario());
        this.view.addDesactivarListener(e -> desactivarUsuario());
        this.view.addCerrarListener(e -> view.dispose());

        
        cargarUsuariosEnTabla();
    }

    public void iniciar() {
        view.setVisible(true);
    }

    private void crearUsuario(){
        FormularioUserView addUserView = new FormularioUserView();
        FormularioUserConntroller addUserConntroller = new FormularioUserConntroller(addUserView, usuarioService, this, null);
        addUserConntroller.iniciar();  
    }
    
    public void cargarUsuariosEnTabla() {
        view.getTableModel().setRowCount(0); 
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        for (Usuario u : usuarios) {
            Object[] rowData = {
                u.getId(),
                u.getNombre(),
                u.getCorreo(),
                u.getRol().toString(),
                u.isActivo() ? "Activo" : "Inactivo"
            };
            view.getTableModel().addRow(rowData);
        }
    }

    private void editarUsuario() {
        int filaSeleccionada = view.getTablaUsuarios().getSelectedRow();
        if (filaSeleccionada == -1) {
            view.mostrarMensaje("Por favor, seleccione un usuario para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UUID idUsuario = (UUID) view.getTableModel().getValueAt(filaSeleccionada, 0);
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        if (usuario.getRol() == Rol.ADMINISTRADOR) {
             view.mostrarMensaje("El usuario Administrador no puede ser editado.", "Acción no permitida", JOptionPane.ERROR_MESSAGE);
             return;
        }

        FormularioUserView addUserView = new FormularioUserView();
        FormularioUserConntroller addUserConntroller = new FormularioUserConntroller(addUserView, usuarioService, this, usuario);
        addUserView.editarUsuario();
        addUserConntroller.iniciar();
    }

    private void desactivarUsuario() {
        int filaSeleccionada = view.getTablaUsuarios().getSelectedRow();
        if (filaSeleccionada == -1) {
            view.mostrarMensaje("Por favor, seleccione un usuario.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UUID idUsuario = (UUID) view.getTableModel().getValueAt(filaSeleccionada, 0);
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        if (usuario.getRol() == Rol.ADMINISTRADOR) {
             view.mostrarMensaje("El usuario Administrador no puede ser desactivado.", "Acción no permitida", JOptionPane.ERROR_MESSAGE);
             return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(view, "¿Desea cambiar el estado de este usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            usuarioService.cambiarEstadoUsuario(idUsuario);
            cargarUsuariosEnTabla();
        }
    }
}