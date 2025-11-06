package ui.controllers;

import java.util.List;
import java.util.UUID;
import javax.swing.*;

import model.Mascota;
import services.MascotaService;
import ui.views.RegistrarMascotaView;
import ui.views.GestionMascotasView;

public class GestionMascotasController {
    private final GestionMascotasView view;
    private final MascotaService mascotaService;
    private final UUID duenoId;

    public GestionMascotasController(GestionMascotasView view, MascotaService mascotaService, UUID duenoId) {
        this.view = view;
        this.mascotaService = mascotaService;
        this.duenoId = duenoId;

        this.view.addEditarListener(e -> editarMascota());
        this.view.addCerrarListener(e -> view.dispose());

        cargarMascotasEnTabla();
    }

    public void iniciar() {
        view.setVisible(true);
    }
    
    public void cargarMascotasEnTabla() {
        view.getTableModel().setRowCount(0); 
        List<Mascota> mascotas = mascotaService.listarMascotasDeUnDueno(duenoId);
        for (Mascota u : mascotas) {
            Object[] rowData = {
                u.getId(),
                u.getNombre(),
                u.getEspecie(),
                u.getRaza(),
                u.getSexo().toString(),
                u.getEdad(),
                u.getPeso(),
                u.getVacunas(),
                u.getAlergias()
            };
            view.getTableModel().addRow(rowData);
        }
    }

    private void editarMascota() {
        int filaSeleccionada = view.getTablaMascotas().getSelectedRow();
        if (filaSeleccionada == -1) {
            view.mostrarMensaje("Por favor, seleccione una mascota para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UUID idMascota = (UUID) view.getTableModel().getValueAt(filaSeleccionada, 0);
        Mascota mascota = mascotaService.buscarMascotaPorId(idMascota);
        if (mascota == null) {
            view.mostrarMensaje("No se encontró la mascota.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        GestionMascotasController gestionMascotasController = new GestionMascotasController(view,mascotaService, duenoId);
        RegistrarMascotaView registrarMascotaView = new RegistrarMascotaView();
        RegistrarMascotaController controller = new RegistrarMascotaController(registrarMascotaView, mascotaService, mascota, gestionMascotasController);

        controller.iniciar();
    }
}
