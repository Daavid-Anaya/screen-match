package com.david.screenmatch.model;

import com.google.genai.types.GenerateContentResponse;

import io.github.cdimascio.dotenv.Dotenv;

import com.google.genai.Client;

public class ConsultaGemini {
    private static final String MODELO = "gemini-2.5-flash";
    private static final Client CLIENTE;

    static {
        String apiKey = Dotenv.load().get("APIGEMINI");
        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("No se encontró la variable de entorno GEMINI_API_KEY");
        }
        CLIENTE = new Client.Builder().apiKey(apiKey).build();
    }

    public static String obtenerTraduccion(String texto) {
        if (texto == null || texto.isBlank()) {
            return "";
        }

        String prompt = "Traduce al español el siguiente texto sin agregar explicaciones: " + texto;

        try {
            GenerateContentResponse respuesta =
                    CLIENTE.models.generateContent(MODELO, prompt, null);

            if (respuesta != null && respuesta.text() != null && !respuesta.text().isBlank()) {
                return respuesta.text();
            }

        } catch (Exception e) {
            System.err.println("Error al llamar a Gemini: " + e.getMessage());
        }

        return "";
    }
}