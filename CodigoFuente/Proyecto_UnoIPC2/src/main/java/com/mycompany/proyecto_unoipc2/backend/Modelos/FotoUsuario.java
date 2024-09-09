/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Modelos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 *
 * @author kevin-mushin
 */
public class FotoUsuario {
    
    private Long idFoto;
    private String nombreUsuario;
    private InputStream foto;

    public FotoUsuario() {
    }

    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }
     public String getFotoBase64() {
        if (this.foto == null) {
            return null;
        }

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Leer el InputStream y escribirlo en el OutputStream
            while ((bytesRead = foto.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Convertir a Base64
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // Añadir el prefijo para Data URL (asumiendo que es una imagen PNG)
            return "data:image/png;base64," + base64Image;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para establecer una nueva imagen desde un archivo subido
    public void setFotoFromBytes(byte[] imageBytes) {
        this.foto = new java.io.ByteArrayInputStream(imageBytes);
    }

  
}
