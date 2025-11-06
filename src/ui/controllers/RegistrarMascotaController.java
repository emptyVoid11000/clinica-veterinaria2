package ui.controllers;

import ui.views.RegistrarMascotaView;
import util.SessionManager;
import java.util.UUID;
import model.Mascota;
import services.MascotaService;

public class RegistrarMascotaController {
    private final RegistrarMascotaView registrarMascotaView;
    private final MascotaService mascotaService;
    
    public RegistrarMascotaController(RegistrarMascotaView registrarMascotaView, MascotaService mascotaService) {
        this.registrarMascotaView = registrarMascotaView;
        this.mascotaService = mascotaService;

        this.registrarMascotaView.addListenerAddBtn(e -> registrarMascota());
        this.registrarMascotaView.addListenerYesVacBtn(e -> habilitarCampoTextoVacunas());
        this.registrarMascotaView.addListenerYesAleBtn(e -> habilitarCampoTextoAlergias());
    }


    public void registrarMascota() {
        String nombre = registrarMascotaView.getNombre();
        String especie = registrarMascotaView.getEspecie();
        String raza = registrarMascotaView.getRaza();
        String sexo = registrarMascotaView.getSexo();
        String edadStr = registrarMascotaView.getEdad();
        String pesoStr = registrarMascotaView.getPeso();
        String vacunas = registrarMascotaView.getVacunas();
        String alergias = registrarMascotaView.getAlergias();

        if (nombre.isEmpty() || especie.isEmpty() || raza.isEmpty() || sexo.isEmpty() ||
            edadStr.isEmpty() || pesoStr.isEmpty()) {
            registrarMascotaView.mostrarMensaje("Complete todos los campos obligatorios");
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            float peso = Float.parseFloat(pesoStr);

            mascotaService.crearMascota(
                SessionManager.getUsuarioActual().getId(),
                nombre, especie, raza, sexo, edad, peso, vacunas, alergias
            );
            registrarMascotaView.mostrarMensaje("Mascota registrada con éxito");

        } catch (NumberFormatException e) {
            registrarMascotaView.mostrarMensaje("Edad o peso inválidos. Use solo números.");
        }
    }

    public void habilitarCampoTextoVacunas() {
        registrarMascotaView.habilitarCampoTextoVacunas();
    }

    public void habilitarCampoTextoAlergias() {
        registrarMascotaView.habilitarCampoTextoAlergias();
    }

    public void iniciar() {
        registrarMascotaView.setVisible(true);
    }
}
