package ui.controllers;

import java.util.UUID;
import model.Rol;
import model.Usuario;
import services.UsuarioService;
import ui.views.FormularioUserView;
import ui.views.MainView;
import ui.views.RegistrarMascotaView;
import util.SessionManager;

public class FormularioUserConntroller{
        private final FormularioUserView addUserView;
        private final UsuarioService usuarioService; 
        private final GestionUsuariosController controller;
        private final Usuario usuario;

        public FormularioUserConntroller(FormularioUserView addUserView, UsuarioService usuarioService, GestionUsuariosController controller, Usuario usuario){
            this.addUserView = addUserView;
            this.usuarioService = usuarioService; 
            this.controller = controller; 
            this.usuario = usuario;
        
            this.addUserView.addListener(e -> capturarUsuario());
        }

        private void guardarUsuario(){
            String nombre = addUserView.getNombre();
            String correo = addUserView.getCorreo();
            String contrasena = addUserView.getContrasena();
            Rol rolSeleccionado = addUserView.getRol();

            if(usuarioService.buscarUsuarioPorCorreo(correo) !=null){ addUserView.mostrarMensaje("Ya existe una cuenta con este correo");return;}

            if (nombre != null && contrasena != null && rolSeleccionado != null) {
                MainView mainView = new MainView();
                if(SessionManager.getUsuarioActual()==null){

                RegistrarMascotaView registrarMascotaView = new RegistrarMascotaView();
                MainController mainController = new MainController(mainView, usuarioService, addUserView, registrarMascotaView);           mainController.iniciar();
                }
                
                usuarioService.crearUsuario(UUID.randomUUID(), nombre, correo, contrasena, rolSeleccionado);

                mainView.configurarParaRol(SessionManager.getUsuarioActual().getRol());

                controller.cargarUsuariosEnTabla();

                addUserView.mostrarMensaje("Usuario creado exitosamente.");
                addUserView.setVisible(false);
            }
        }

        private void editarUsuario(){
            String nombre = addUserView.getNombre();
            String contrasena = addUserView.getContrasena();
            Rol rolSeleccionado = addUserView.getRol();
            usuarioService.actualizarUsuario(new Usuario(usuario.getId(), nombre, usuario.getCorreo(), contrasena, "", rolSeleccionado));
            controller.cargarUsuariosEnTabla();
            addUserView.mostrarMensaje("Usuario editado con exito");
            addUserView.setVisible(false);
        }

        private void capturarUsuario() {
            if(usuario==null){
                guardarUsuario();
            } else editarUsuario();
        }

        public void iniciar() {
            addUserView.setVisible(true);
        }

        
}
