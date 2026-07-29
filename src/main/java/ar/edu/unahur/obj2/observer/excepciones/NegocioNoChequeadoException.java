package ar.edu.unahur.obj2.observer.excepciones;

public class NegocioNoChequeadoException extends RuntimeException {

    public NegocioNoChequeadoException(String mensaje) {
        super(mensaje);
    }

}
