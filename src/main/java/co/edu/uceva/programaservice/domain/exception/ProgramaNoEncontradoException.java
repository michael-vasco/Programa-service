package co.edu.uceva.programaservice.domain.exception;

public class ProgramaNoEncontradoException extends RuntimeException {
    public ProgramaNoEncontradoException(Long id) {
        super("el programa con ID " + id + " no fue encontrada.");
    }
}