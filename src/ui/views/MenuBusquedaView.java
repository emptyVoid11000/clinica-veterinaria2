package ui.views;

import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuBusquedaView extends JFrame {
    private JList<String> lista;
    private DefaultListModel<String> modeloLista;
    private JTextField txtBusqueda;
    private JButton buscarBtn;

    public MenuBusquedaView() {
        super("Lista de Usuarios");

        // Configuración básica
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Campos de texto y botón
        add(new JLabel("Usuario a buscar:"));
        txtBusqueda = new JTextField();
        add(txtBusqueda);

        buscarBtn = new JButton("Buscar");
        add(buscarBtn);

        // Modelo y lista
        modeloLista = new DefaultListModel<>();
        lista = new JList<>(modeloLista);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(lista);
        add(scroll);
    }

    public String getCorreo() {
        return txtBusqueda.getText();
    }

    public void recargar() {
        revalidate();
        repaint();
    }

    public void btnBuscarListener(ActionListener listener) {
        buscarBtn.addActionListener(listener);
    }

    public JList<String> getLista() {
        return lista;
    }

    public DefaultListModel<String> getModeloLista() {
        return modeloLista;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
