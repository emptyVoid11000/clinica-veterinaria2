package ui.views;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Rol;

public class FormularioUserView extends JFrame{

    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnAnadir;
    private JComboBox comboRol;

    public FormularioUserView() {
        setTitle("Añadir Usuario - Clínica Veterinaria");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(5, 2, 10, 10));

        
        add(new JLabel("  Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("  Correo:"));
        txtCorreo = new JTextField();
        add(txtCorreo);

        add(new JLabel("  Contraseña:"));
        txtContrasena = new JPasswordField();
        add(txtContrasena);

        String[] roles = {"MEDICO", "AUXILIAR"};
        
        add(new JLabel("Elija el rol"));
        comboRol= new JComboBox<>(roles);
        add(comboRol);

        add(new JLabel()); 
        btnAnadir = new JButton("Añadir Usuario");
        add(btnAnadir);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getCorreo() {
        return txtCorreo.getText();
    }

    public String getContrasena() {
        return new String(txtContrasena.getPassword());
    }

    public Rol getRol(){
        String rolStr = (String) comboRol.getSelectedItem();
        return Rol.valueOf(rolStr);
    }

    public void setNombre(String nombre) { txtNombre.setText(nombre); }
    public void setCorreo(String correo) { txtCorreo.setText(correo); }
    public void setContrasena(String contrasena) { txtContrasena.setText(contrasena); }
    public void setRol(Rol rol) { comboRol.setSelectedItem(rol); }


    public void editarUsuario() {
        setTitle("Editar Usuario");
        btnAnadir.setText("Guardar Cambios");
        txtCorreo.setEnabled(false); 
    }

    public void registrarse() {
        setTitle("Registrar Usuario");
        btnAnadir.setText("Registrarse");
    }


    public void addListener(ActionListener listener) {
        btnAnadir.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

}
