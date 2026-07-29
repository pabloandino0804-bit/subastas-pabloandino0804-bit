package ar.edu.unahur.obj2.observer;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.observer.excepciones.NegocioNoChequeadoException;
import ar.edu.unahur.obj2.observer.observadores.ISubastador;
import ar.edu.unahur.obj2.observer.ofertas.Oferta;

public class ProductoSubastado {
    private List<Oferta> ofertasRecibidas = new ArrayList<>();
    private List<ISubastador> subastadores = new ArrayList<>();

    public List<Oferta> getOfertasRecibidas() {
        return ofertasRecibidas;
    }

    public List<ISubastador> getSubastadores() {
        return subastadores;
    }

    public void registrarUnaOferta(Oferta unaOferta) {
        if (!subastadores.contains(unaOferta.getSubastador())) {
            throw new NegocioNoChequeadoException("El subastador no participa en la subasta");
        }
        this.ofertasRecibidas.add(unaOferta);
        notificarSubastadores(unaOferta);
    }

    public void reiniciarProducto() {
        this.subastadores.stream().forEach(sub -> this.eliminarSubastador(sub));
        this.ofertasRecibidas.clear();
    }

    // Metodos accion de Observador
    public void eliminarSubastador(ISubastador unSubastador) {
        this.subastadores.remove(unSubastador);
    }

    public void registrarSubastador(ISubastador unSubastador) {
        this.subastadores.add(unSubastador);
    }

    public void notificarSubastadores(Oferta oferta) {
        for (ISubastador subastador : this.subastadores) {
            subastador.agregarOferta(oferta);
        }
    }
}