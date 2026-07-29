package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.ofertas.Oferta;

public class SubastadorArriesgado extends Subastador {

    public SubastadorArriesgado(String nombre) {
        super(nombre);
    }

    @Override
    public void agregarOferta(Oferta nuevaOferta) {
        if (ultimaOferta == null) {
            nuevaOferta.aumentar(10.0);
            this.ultimaOferta = nuevaOferta;
            System.out.println("El subastador " + nombre + " ha recibido la oferta ");
        } else if (sePuedeAgregar(nuevaOferta)) {
            nuevaOferta.aumentar(10.0);
            this.ultimaOferta = nuevaOferta;
            System.out.println("El subastador " + nombre + "ha recibido la oferta ");
        }
    }
}
