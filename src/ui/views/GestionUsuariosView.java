package ui.views;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestionUsuariosView extends JFrame {
    private JTable tablaUsuarios;
    private DefaultTableModel tableModel;
    private JButton btnCrear, btnEditar, btnDesactivar, btnCerrar;

    public GestionUsuariosView() {
        setTitle("Gestión de Usuarios");
        setSize(700, 400);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        
        String[] columnNames = {"ID", "Nombre", "Correo", "Rol", "Estado"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tablaUsuarios = new JTable(tableModel);

        
        JPanel panelBotones = new JPanel();
        btnCrear = new JButton("Crear Usuario");
        btnEditar = new JButton("Editar Usuario");
        btnDesactivar = new JButton("Activar/Desactivar");
        btnCerrar = new JButton("Cerrar");
        panelBotones.add(btnCrear);
        panelBotones.add(btnEditar);
        panelBotones.add(btnDesactivar);
        panelBotones.add(btnCerrar);

        
        setLayout(new BorderLayout());
        add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTablaUsuarios() {
        return tablaUsuarios;
    }

    public void addCrearListener(ActionListener listener) {
        btnCrear.addActionListener(listener);
    }

    public void addEditarListener(ActionListener listener) {
        btnEditar.addActionListener(listener);
    }

    public void addDesactivarListener(ActionListener listener) {
        btnDesactivar.addActionListener(listener);
    }

    public void addCerrarListener(ActionListener listener) {
        btnCerrar.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
}