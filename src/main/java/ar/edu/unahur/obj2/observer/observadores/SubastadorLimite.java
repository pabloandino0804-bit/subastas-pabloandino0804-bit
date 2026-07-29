package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.ofertas.Oferta;

public class SubastadorLimite extends Subastador {
    private Double limitUmbral;

    public SubastadorLimite(String nombre, Double umbral) {
        super(nombre);
        this.limitUmbral = umbral;
    }

    @Override
    protected boolean sePuedeAgregar(Oferta unaOferta) {
        return unaOferta.getValorOfertado() < limitUmbral;
    }
}
