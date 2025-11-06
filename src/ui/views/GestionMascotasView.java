package ui.views;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestionMascotasView extends JFrame{
    private JTable tablaUsuarios;
    private DefaultTableModel tableModel;
    private JButton btnEditar, btnCerrar;

    public GestionMascotasView() {
        setTitle("Lista de Mascotas");
        setSize(700, 400);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        
        String[] columnNames = {"Id","Nombre", "Especie", "Raza", "Sexo", "Edad", "Peso", "Vacunas", "Alergias"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tablaUsuarios = new JTable(tableModel);

        
        JPanel panelBotones = new JPanel();
        btnEditar = new JButton("Editar mascota");
        //btnEliminar = new JButton("Eliminar");
        btnCerrar = new JButton("Cerrar");
        panelBotones.add(btnEditar);
        //panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        
        setLayout(new BorderLayout());
        add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTablaMascotas() {
        return tablaUsuarios;
    }

    public void addEditarListener(ActionListener listener) {
        btnEditar.addActionListener(listener);
    }

    /*public void addEliminarListener(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }*/

    public void addCerrarListener(ActionListener listener) {
        btnCerrar.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
}

