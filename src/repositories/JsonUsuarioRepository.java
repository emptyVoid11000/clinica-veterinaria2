package repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Mascota;
import model.Rol;
import model.Usuario;
import util.PasswordUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class JsonUsuarioRepository implements UsuarioRepository {

    private final String RUTA_ARCHIVO = "usuarios.json";
    private List<Usuario> usuarios;
    private final Gson gson;

    public JsonUsuarioRepository() {
        
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.usuarios = cargarDesdeArchivo();
    }

    private List<Usuario> cargarDesdeArchivo() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            
            List<Usuario> usuariosPorDefecto = new ArrayList<>();
            byte[] salt = PasswordUtil.generateSalt();
            String hash = PasswordUtil.hashPassword("admin1234", salt);
            String saltS = PasswordUtil.saltToBase64(salt);

            Usuario admin = new Usuario(UUID.randomUUID(), "Administrador", "admin@gmail.com", hash, saltS, Rol.ADMINISTRADOR);
            usuariosPorDefecto.add(admin);
            guardarEnArchivo(usuariosPorDefecto);
            return usuariosPorDefecto;
        }

        try (FileReader reader = new FileReader(archivo)) {
            Type tipoListaUsuarios = new TypeToken<ArrayList<Usuario>>() {}.getType();
            return gson.fromJson(reader, tipoListaUsuarios);
        } catch (IOException e) {
            System.err.println("Error al cargar los usuarios desde el archivo: " + e.getMessage());
            
            return new ArrayList<>();
        }
    }

    private void guardarEnArchivo(List<Usuario> usuariosAGuardar) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(usuariosAGuardar, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar los usuarios en el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregar(Usuario usuario) {
        usuarios.add(usuario);
        guardarEnArchivo(this.usuarios); 
    }

    @Override
    public void actualizar(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarios.stream()
            .filter(u -> u.getId().equals(usuario.getId()))
            .findFirst();

        usuarioExistente.ifPresent(u -> {
            u.setNombre(usuario.getNombre());
            u.setPasswordhash(usuario.getPasswordHash());
            u.setSalt(usuario.getSalt());
            u.setRol(usuario.getRol());
            u.setActivo(usuario.isActivo());
            guardarEnArchivo(this.usuarios); 
        });
    }

    @Override
    public Usuario buscarPorId(UUID id) {
        return usuarios.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    
@Override
public List<Usuario> buscarPorNombre(String nombre) {
    return usuarios.stream()
        .filter(u -> u.getNombre().toLowerCase().contains(nombre))
        .collect(Collectors.toList());
}


    @Override
    public Usuario buscarPorCorreo(String correo) {
        return usuarios.stream()
            .filter(u -> u.getCorreo().equals(correo))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Usuario> buscarPorCorreoVarios(String correo) {
        return usuarios.stream()
        .filter(u -> u.getCorreo().toLowerCase().contains(correo))
        .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }
}