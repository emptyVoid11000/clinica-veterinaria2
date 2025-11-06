package ui.controllers;

import model.Mascota;
import services.MascotaService;
import ui.views.RegistrarMascotaView;
import util.SessionManager;

public class RegistrarMascotaController {
    private final RegistrarMascotaView registrarMascotaView;
    private final MascotaService mascotaService;
    private final Mascota mascota;
    private final GestionMascotasController gestionMascotasController;
    
    public RegistrarMascotaController(RegistrarMascotaView registrarMascotaView, MascotaService mascotaService, Mascota mascota, GestionMascotasController gestionMascotasController) {
        this.gestionMascotasController = gestionMascotasController;
        this.registrarMascotaView = registrarMascotaView;
        this.mascotaService = mascotaService;
        this.mascota = mascota;

        this.registrarMascotaView.addListenerAddBtn(e -> mainBtnListener());
        this.registrarMascotaView.addListenerYesVacBtn(e -> habilitarCampoTextoVacunas());
        this.registrarMascotaView.addListenerYesAleBtn(e -> habilitarCampoTextoAlergias());
        this.registrarMascotaView.addListenerNoVacBtn(e -> deshabilitarCampoTextoVacunas());
        this.registrarMascotaView.addListenerNoAleBtn(e -> deshabilitarCampoTextoAlergias());
    }


    public void mainBtnListener (){
        if(mascota==null){
            registrarMascota();
        } else{
            actualizarMascota();
        }
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
            registrarMascotaView.setVisible(false);

        } catch (NumberFormatException e) {
            registrarMascotaView.mostrarMensaje("Edad o peso inválidos. Use solo números.");
        }
        
    }

    public void actualizarMascota(){
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

            mascota.setNombre(nombre);
            mascota.setEspecie(especie);
            mascota.setRaza(raza);
            mascota.setSexo(sexo.toString());
            mascota.setEdad(edad);
            mascota.setPeso(peso);
            mascota.setVacunas(vacunas);
            mascota.setAlergias(alergias);

            mascotaService.actualizarMascota(mascota);

            registrarMascotaView.mostrarMensaje("Mascota actualizada con éxito");
            registrarMascotaView.setVisible(false);
            gestionMascotasController.cargarMascotasEnTabla();

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

    public void deshabilitarCampoTextoVacunas() {
        registrarMascotaView.deshabilitarCampoTextoVacunas();
    }

    public void deshabilitarCampoTextoAlergias() {
        registrarMascotaView.deshabilitarCampoTextoAlergias();
    }

    public void iniciar() {
        registrarMascotaView.setVisible(true);
    }
}
