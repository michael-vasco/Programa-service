package co.edu.uceva.programaservice.domain.exception;

public class PaginaSinProgramaException extends RuntimeException {
    public PaginaSinProgramaException(int page) {
        super("No hay programas en la página solicitada: " + page);
    }
}