package ui.views;

import java.awt.event.ActionListener;
import javax.swing.*;
import model.Rol;

public class MainView extends JFrame {
    private JMenuItem menuItemGestionUsuarios;
    private JMenuItem menuItemCerrarSesion;
    private JMenuItem menuItemReestablecerContrasena;
    //Dueño
    private JMenuItem menuItemRegistrarMascota;

    public MainView() {
        setTitle("Sistema de Gestión - Clínica Veterinaria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
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
        menuSistema.add("Registrar Mascota");

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

                break;

            case MEDICO:
                menuItemRegistrarMascota.setVisible(false);

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
}