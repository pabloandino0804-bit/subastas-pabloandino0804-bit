package ar.edu.unahur.obj2.observer;

import java.util.List;

import ar.edu.unahur.obj2.observer.excepciones.NegocioNoChequeadoException;
import ar.edu.unahur.obj2.observer.observadores.ISubastador;
import ar.edu.unahur.obj2.observer.ofertas.Oferta;

public class ProductoSubastado {
    private List<Oferta> ofertasRecibidas;
    private List<ISubastador> subastadoresRecibidos;

    public List<Oferta> getOfertasRecibidas() {
        return ofertasRecibidas;
    }

    public List<ISubastador> getSubastadores() {
        return subastadoresRecibidos;
    }

    public void registrarUnaOferta(Oferta unaOferta) {
        if (!subastadoresRecibidos.contains(unaOferta.getSubastador())) {
            throw new NegocioNoChequeadoException("El subastador no participa en la subasta");
        }
        this.ofertasRecibidas.add(unaOferta);

    }

    public void reiniciarProducto() {
        this.ofertasRecibidas.clear();
        this.subastadoresRecibidos.clear();
    }

    // Observadores
    public void registrarSubastador(ISubastador subastador) {
        this.subastadoresRecibidos.add(subastador);
    }

    public void reaccionar(Oferta unaOferta) {
        for (ISubastador subastador : subastadoresRecibidos) {
            subastador.agregarOferta(unaOferta);
        }
    }
}