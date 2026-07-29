package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.ofertas.Oferta;

public interface ISubastador {

    String getNombre();

    Oferta getUltimaOferta();

    void reiniciar();

    void agregarOferta(Oferta nuevaOferta);

}