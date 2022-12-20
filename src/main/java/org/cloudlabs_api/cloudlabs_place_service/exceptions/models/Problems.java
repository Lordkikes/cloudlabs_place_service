package org.cloudlabs_api.cloudlabs_place_service.exceptions.models;

public class Problems {

    public static final Problem TRANSLATION_NOT_FOUND =
            new Problem("No se encontró traducción", "Verifique la traducción solicitada.", "La traducción no existe.");
    public static final Problem LANGUAGE_NOT_FOUND =
            new Problem("No se encontró el idioma", "Verifique el idioma indicado.", "El idioma no existe.");
    public static final Problem VERSION_NOT_FOUND =
            new Problem("No se encontró la versión.", "Verifique la versión indicada.", "La versión no existe.");
    public static final Problem LANGUAGE_ALREADY_EXISTS =
            new Problem("El lenguaje ya existe", "Pruebe con otro lenguaje", "Se encontro lenguaje cargado.");
    public static final Problem UNSPECIFIED_LANGUAGE_CODE =
            new Problem("Código de lenguaje vacio", "Verifique que está enviando código de lenguaje.", "Código de lenguaje no enviado.");
    public static final Problem BAD_REQUEST =
            new Problem("La request es erronea", "Verifique los parámetros de su request.", "Los parámetros de la request no son los esperados.");
    public static final Problem COMUNICATION_PROBLEM =
            new Problem("Error de comunicación", "Hubo un error de comunicación con otro microservicio.", "Verifique los parámetros de comunicación.");
    public static final Problem REQUIRED_EXPECTED =
            new Problem("Parámetro requerido", "Uno o más parámetros requeridos no se han enviado.", "Revisar los parámetros requeridos para la llamada realizada.");
    public static final Problem DEFAULT_LANGUAGE_NOT_SUPPORTED =
            new Problem("El lenguaje es el por defecto.", "Pruebe con otro lenguaje.", "No existe traduccion para el lenguaje por defecto.");
    public static final Problem METHOD_ARGUMENT_NOT_VALID =
            new Problem("Error en algun campo.", "Verifique los campos de la petición.", "");
    public static final Problem COUNTRY_NOT_FOUND =
            new Problem("No se encontró el país", "Verifique el país solicitado.", "El país no existe.");
    public static final Problem STATE_NOT_FOUND =
            new Problem("No se encontró el estado/provincia", "Verifique el estado/provincia solicitado.", "El estado/provincia no existe.");
    public static final Problem CITY_NOT_FOUND =
            new Problem("No se encontró la ciudad", "Verifique la ciudad solicitada.", "La ciudad no existe.");
    public static final Problem JSON_ERROR =
            new Problem("Error al leer json", "Ocurrió un error al leer json.", "El dato de tipo json no pudo ser leído.");
}