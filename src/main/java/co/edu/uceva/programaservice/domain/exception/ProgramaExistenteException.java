package co.edu.uceva.programaservice.domain.exception;

public class ProgramaExistenteException extends RuntimeException {
    public ProgramaExistenteException(String nombre) {
        super("el programa con nombre '" + nombre + "' ya existe.");
    }
}

