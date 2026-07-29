package ar.edu.unahur.obj2.observer.ofertas;

import ar.edu.unahur.obj2.observer.observadores.ISubastador;

public class Oferta {
    private ISubastador subastador;
    private Double valor;

    public Oferta(ISubastador subastador, Double valorOfertado) {
        this.subastador = subastador;
        this.valor = valorOfertado;
    }

    public ISubastador getSubastador() {
        return subastador;
    }

    public Double getValorOfertado() {
        return valor;
    }

}
