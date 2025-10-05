package ui.views;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;

    public LoginView() {
        setTitle("Iniciar Sesión - Clínica Veterinaria");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(4, 2, 10, 10));

        
        add(new JLabel("  Usuario:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("  Contraseña:"));
        txtContrasena = new JPasswordField();
        add(txtContrasena);

        add(new JLabel()); 
        btnIniciarSesion = new JButton("Iniciar Sesión");
        add(btnIniciarSesion);

        add(new JLabel()); 
        btnRegistrarse = new JButton("Registrarse");
        add(btnRegistrarse);
    }

    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getContrasena() {
        return new String(txtContrasena.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        btnIniciarSesion.addActionListener(listener);
    }

    public void addRegistrarseListener(ActionListener listener) {
        btnRegistrarse.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}