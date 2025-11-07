package ui.views;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Rol;

public class MainView extends JFrame {
    private JMenuItem menuItemGestionUsuarios;
    private JMenuItem menuItemCerrarSesion;
    private JMenuItem menuItemReestablecerContrasena;
    //Dueño
    private JMenuItem menuItemRegistrarMascota;
    private JMenuItem menuItemBusquedaMascota;
    private JLabel saludo;

    public MainView() {
        setTitle("Sistema de Gestión - Clínica Veterinaria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        saludo = new JLabel("", SwingConstants.CENTER);
        saludo.setFont(new Font("Arial", Font.BOLD, 24)); 
        add(saludo, BorderLayout.CENTER);

        
        JMenuBar menuBar = new JMenuBar();

        
        JMenu menuSistema = new JMenu("Sistema");
        menuItemCerrarSesion = new JMenuItem("Cerrar Sesión");
        menuSistema.add(menuItemCerrarSesion);
        
        JMenu menuAdmin = new JMenu("Administración");
        menuItemGestionUsuarios = new JMenuItem("Gestionar Usuarios");
        menuAdmin.add(menuItemGestionUsuarios);

        menuItemReestablecerContrasena = new JMenuItem("Reestablecer Contraseña");
        menuSistema.add(menuItemReestablecerContrasena);

        menuItemRegistrarMascota = new JMenuItem("Registrar Mascota");
        menuSistema.add(menuItemRegistrarMascota);

        menuItemBusquedaMascota = new JMenuItem("Buscar/actualizar Mascota");
        menuSistema.add(menuItemBusquedaMascota);

        menuBar.add(menuSistema);
        menuBar.add(menuAdmin);
        //Dueño
        //btnRegistrarMascota.add(new JButton());

        setJMenuBar(menuBar);
    }

    
    public void configurarParaRol(Rol rol) {
        switch (rol) {
            case ADMINISTRADOR:
                menuItemRegistrarMascota.setVisible(false);
                menuItemBusquedaMascota.setVisible(false);
                break;

            case MEDICO:
                menuItemRegistrarMascota.setVisible(false);
                menuItemBusquedaMascota.setVisible(true);
                getJMenuBar().getMenu(1).setVisible(false);
                break;

            case AUXILIAR:

                getJMenuBar().getMenu(1).setVisible(false);
                break;

            default:
                JOptionPane.showMessageDialog(this, "Error: Rol no reconocido");
                break;
        }
    }
    public void cambiarNombre(String nombre) {
        saludo.setText("¡Hola, " + nombre + "!");
    }

    public void addCerrarSesionListener(ActionListener listener) {
        menuItemCerrarSesion.addActionListener(listener);
    }

    public void addGestionUsuariosListener(ActionListener listener) {
        menuItemGestionUsuarios.addActionListener(listener);
    }

    public void addReestablecerContrasenaListener(ActionListener listener) {
        menuItemReestablecerContrasena.addActionListener(listener);
    }

    public void addMenuItemRegistrarMascota(ActionListener listener) {
        menuItemRegistrarMascota.addActionListener(listener);
    }

    public void addMenuItemBusquedaMascota(ActionListener listener) {
        menuItemBusquedaMascota.addActionListener(listener);
    }
}