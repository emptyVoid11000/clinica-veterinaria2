package ui.controllers;

import services.AutenticacionService;
import services.UsuarioService;
import ui.views.FormularioUserView;
import ui.views.GestionUsuariosView;
import ui.views.LoginView;
import ui.views.MainView;
import util.SessionManager;

public class LoginController {
    private final LoginView loginView;
    private final AutenticacionService autenticacionService;
    private final UsuarioService usuarioService; 

    public LoginController(LoginView loginView, AutenticacionService autenticacionService, UsuarioService usuarioService) {
        this.loginView = loginView;
        this.autenticacionService = autenticacionService;
        this.usuarioService = usuarioService; 
        
        this.loginView.addLoginListener(e -> autenticarUsuario());
        this.loginView.addRegistrarseListener(e -> registrarse());
    }

    private void autenticarUsuario() {
        String usuario = loginView.getUsuario();
        String contrasena = loginView.getContrasena();

        if (autenticacionService.iniciarSesion(usuario, contrasena)) {
            loginView.dispose();
            
            MainView mainView = new MainView();
            FormularioUserView addUserView = new FormularioUserView();
            mainView.configurarParaRol(SessionManager.getUsuarioActual().getRol());
            
            MainController mainController = new MainController(mainView, usuarioService, addUserView);
            mainController.iniciar();
            
        } else {
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        }
    }

    private void registrarse(){
        FormularioUserView addUserView = new FormularioUserView();
        GestionUsuariosView gestionUsuariosView = new GestionUsuariosView();
        GestionUsuariosController controller = new GestionUsuariosController(gestionUsuariosView, usuarioService, addUserView);
        FormularioUserConntroller addUserConntroller = new FormularioUserConntroller(addUserView, usuarioService, controller, null);
        addUserView.registrarse();
        addUserConntroller.iniciar();  
    }

    public void iniciar() {
        loginView.setVisible(true);
    }
}