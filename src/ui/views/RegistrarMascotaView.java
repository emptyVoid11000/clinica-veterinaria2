package ui.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegistrarMascotaView extends JFrame {

    private JTextField txtNombre;
    private JTextField txtEspecie;
    private JTextField txtEdad;
    private JTextField txtRaza;
    private JTextField txtPeso;
    private JTextField txtVacuna;
    private JTextField txtAlergias;

    private JButton btnAnadir;
    private JComboBox<String> comboSexo;

    private JRadioButton radioButtonYesVacunas;
    private JRadioButton radioButtonNoVacunas;
    private JRadioButton radioButtonYesAlergias;
    private JRadioButton radioButtonNoAlergias;

    public RegistrarMascotaView() {
        setTitle("Registrar Mascota - Clínica Veterinaria");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2, 10, 10));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Especie:"));
        txtEspecie = new JTextField();
        add(txtEspecie);

        add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        add(txtEdad);

        add(new JLabel("Raza:"));
        txtRaza = new JTextField();
        add(txtRaza);

        add(new JLabel("Sexo:"));
        String[] sexos = {"HEMBRA", "MACHO"};
        comboSexo = new JComboBox<>(sexos);
        add(comboSexo);

        add(new JLabel("Peso (kg):"));
        txtPeso = new JTextField();
        add(txtPeso);

        add(new JLabel("¿Está vacunada?"));
        JPanel panelVacunas = new JPanel(new FlowLayout());
        radioButtonYesVacunas = new JRadioButton("Sí");
        radioButtonNoVacunas = new JRadioButton("No");
        ButtonGroup grupoVacunas = new ButtonGroup();
        grupoVacunas.add(radioButtonYesVacunas);
        grupoVacunas.add(radioButtonNoVacunas);
        panelVacunas.add(radioButtonYesVacunas);
        panelVacunas.add(radioButtonNoVacunas);
        add(panelVacunas);

        add(new JLabel("Vacunas (si aplica):"));
        txtVacuna = new JTextField();
        txtVacuna.setEnabled(false);
        add(txtVacuna);

        add(new JLabel("¿Tiene alergias?"));
        JPanel panelAlergias = new JPanel(new FlowLayout());
        radioButtonYesAlergias = new JRadioButton("Sí");
        radioButtonNoAlergias = new JRadioButton("No");
        ButtonGroup grupoAlergias = new ButtonGroup();
        grupoAlergias.add(radioButtonYesAlergias);
        grupoAlergias.add(radioButtonNoAlergias);
        panelAlergias.add(radioButtonYesAlergias);
        panelAlergias.add(radioButtonNoAlergias);
        add(panelAlergias);

        add(new JLabel("Alergias (si aplica):"));
        txtAlergias = new JTextField();
        txtAlergias.setEnabled(false);
        add(txtAlergias);

        add(new JLabel());
        btnAnadir = new JButton("Añadir Mascota");
        add(btnAnadir);
    }

    public String getNombre() { return txtNombre.getText(); }
    public String getEspecie() { return txtEspecie.getText(); }
    public String getEdad() { return txtEdad.getText(); }
    public String getRaza() { return txtRaza.getText(); }
    public String getPeso() { return txtPeso.getText(); }
    public String getSexo() { return (String) comboSexo.getSelectedItem(); }
    public String getVacunas() { return txtVacuna.getText(); }
    public String getAlergias() { return txtAlergias.getText(); }

    // --- Habilitar campos ---
    public void habilitarCampoTextoVacunas() {
        txtVacuna.setEnabled(true);
    }

    public void habilitarCampoTextoAlergias() {
        txtAlergias.setEnabled(true);
    }

    public void addListenerAddBtn(ActionListener listener) {
        btnAnadir.addActionListener(listener);
    }

    public void addListenerYesVacBtn(ActionListener listener) {
        radioButtonYesVacunas.addActionListener(listener);
    }

    public void addListenerYesAleBtn(ActionListener listener) {
        radioButtonYesAlergias.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
