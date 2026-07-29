package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.ofertas.Oferta;

public class SubastadorUnico extends Subastador {
    private Integer cont = 0;

    public SubastadorUnico(String nombre) {
        super(nombre);
    }

    @Override
    public void agregarOferta(Oferta nuevaOferta) {
        if (cont < 1) {
            this.ultimaOferta = nuevaOferta;
            cont += 1;
            System.out.println("El subastador " + nombre + " ha recibido la oferta ");
        }
    }
}
