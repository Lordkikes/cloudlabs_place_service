package org.cloudlabs_api.cloudlabs_place_service.commons;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class Converter {

    private final static Logger LOGGER = Logger.getLogger(Converter.class.getName());
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Convierte una instancia de clase a un JSON String
     *
     * @param fromObject Objeto a convertir
     * @param <T>        Clase de objeto origen parametrizable
     * @return JSON String que representa al objeto de entrada
     */
    public static <T> String convertValue(T fromObject) {
        try {
            if (fromObject.equals(String.class)) {
                return fromObject.toString();
            }
            mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.writeValueAsString(fromObject);
        } catch (Exception e) {
            LOGGER.severe("Error al intentar convertir un objeto de la clase " + fromObject + "a un JSON String. \nMensaje de error: " + e.getCause().getMessage());
            return null;
        }
    }
}
