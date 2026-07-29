package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.ofertas.Oferta;

public class Subastador implements ISubastador {
    protected String nombre;
    protected Oferta ultimaOferta;

    public Subastador(String nombre) {
        this.nombre = nombre;
        this.ultimaOferta = null;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Oferta getUltimaOferta() {
        return ultimaOferta;
    }

    @Override
    public void reiniciar() {
        this.ultimaOferta = null;
    }

    @Override
    public void agregarOferta(Oferta nuevaOferta) {
        if (ultimaOferta == null) {
            this.ultimaOferta = nuevaOferta;
            System.out.println("El subastador " + nombre + " ha recibido la oferta.");
        } else if (sePuedeAgregar(nuevaOferta)) {
            this.ultimaOferta = nuevaOferta;
            System.out.println("El subastador " + nombre + " ha recibido la oferta.");
        }
    }

    protected boolean sePuedeAgregar(Oferta unaOferta) {
        return unaOferta.getValorOfertado() + 10 > ultimaOferta.getValorOfertado();
    }
}
