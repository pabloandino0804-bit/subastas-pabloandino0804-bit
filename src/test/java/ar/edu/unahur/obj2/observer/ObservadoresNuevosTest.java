package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.excepciones.NegocioNoChequeadoException;
import ar.edu.unahur.obj2.observer.observadores.ISubastador;
import ar.edu.unahur.obj2.observer.observadores.SubastadorArriesgado;
import ar.edu.unahur.obj2.observer.observadores.SubastadorLimite;
import ar.edu.unahur.obj2.observer.observadores.SubastadorUnico;
import ar.edu.unahur.obj2.observer.ofertas.Oferta;

public class ObservadoresNuevosTest {
    private ProductoSubastado prod = new ProductoSubastado();
    private SubastadorArriesgado gozanger = new SubastadorArriesgado("gonzanger");
    private SubastadorUnico diazdan = new SubastadorUnico("diazdan");
    private SubastadorLimite martomau = new SubastadorLimite("martomau", 47.0);

    @BeforeEach
    void setup() {
        prod.reiniciarProducto();
        gozanger.reiniciar();
        diazdan.reiniciar();
        martomau.reiniciar();
        prod.registrarSubastador(gozanger);
        prod.registrarSubastador(martomau);
    }

    @Test
    void dadoElEscenario1_ambosSubastadoresDebenRecibirLaUltimaOfertaRealizada() {
        // Dado
        Oferta oferta1 = new Oferta(martomau, 10.0);
        Oferta oferta2 = new Oferta(gozanger, 15.0);
        Oferta oferta3 = new Oferta(martomau, 20.0);

        // When
        prod.registrarUnaOferta(oferta1);
        prod.registrarUnaOferta(oferta2);
        prod.registrarUnaOferta(oferta3);

        // Debe devolver
        ISubastador subDado1 = gozanger.getUltimaOferta().getSubastador();
        ISubastador subDado2 = martomau.getUltimaOferta().getSubastador();
        assertEquals(subDado1.getNombre(), oferta3.getSubastador().getNombre());
        assertEquals(subDado2.getNombre(), oferta3.getSubastador().getNombre());
        assertTrue(prod.getSubastadores().contains(martomau));
    }

    @Test
    void dadoElEscenario1_laUltimaOfertaEnAmbosSubastadores_debeSerLAQuePerteneceAMartomau() {
        // Dado
        Oferta oferta1 = new Oferta(martomau, 10.0);
        Oferta oferta2 = new Oferta(gozanger, 15.0);
        Oferta oferta3 = new Oferta(martomau, 20.0);

        // When
        prod.registrarUnaOferta(oferta1);
        prod.registrarUnaOferta(oferta2);
        prod.registrarUnaOferta(oferta3);

        // Debe devolver
        ISubastador subDado1 = gozanger.getUltimaOferta().getSubastador();
        ISubastador subDado2 = martomau.getUltimaOferta().getSubastador();
        assertEquals(subDado1.getNombre(), martomau.getNombre());
        assertEquals(subDado2.getNombre(), martomau.getNombre());
    }

    @Test
    void dadoElEscenario1_elValorDelaUltimaOfertaEnAmbosSubastadoresDebeSer30() {
        // Dado
        Oferta oferta1 = new Oferta(martomau, 10.0);
        Oferta oferta2 = new Oferta(gozanger, 15.0);
        Oferta oferta3 = new Oferta(martomau, 20.0);

        // When
        prod.registrarUnaOferta(oferta1);
        prod.registrarUnaOferta(oferta2);
        prod.registrarUnaOferta(oferta3);

        // Debe devolver;
        assertEquals(gozanger.getUltimaOferta().getValorOfertado(), 30.0);
        assertEquals(martomau.getUltimaOferta().getValorOfertado(), 30.0);
    }

    @Test
    void dadoElEscenario1_elProductoSubestimadoDebeTenerRegistrado_las3Ofertas() {
        // Dado
        Oferta of1 = new Oferta(martomau, 39.0);
        Oferta of2 = new Oferta(gozanger, 5.0);
        Oferta of3 = new Oferta(martomau, 19.0);

        // When
        prod.registrarUnaOferta(of1);
        prod.registrarUnaOferta(of2);
        prod.registrarUnaOferta(of3);

        // Debe devolver
        assertEquals(prod.getOfertasRecibidas().size(), 3);
    }

    @Test
    void dadoElEscenario1_SiElProductoIntentaRegistrarUnaNuevaOfertDeDizdan_TiraUnaExcepcion() {
        // Dado
        Oferta oferta1 = new Oferta(martomau, 10.0);
        Oferta oferta2 = new Oferta(gozanger, 25.0);
        Oferta oferta3 = new Oferta(martomau, 20.0);
        Oferta nuevaOferta = new Oferta(diazdan, 24.5);

        // When
        prod.registrarUnaOferta(oferta1);
        prod.registrarUnaOferta(oferta2);
        prod.registrarUnaOferta(oferta3);

        // Debe tirar una Excepcion
        assertThrows(NegocioNoChequeadoException.class, () -> prod.registrarUnaOferta(nuevaOferta));
    }

    @Test
    void mismoPrimerTestPeroAñadiendoADiazdan() {
        // Dado
        prod.registrarSubastador(diazdan);
        Oferta oferta1 = new Oferta(martomau, 10.0);
        Oferta oferta2 = new Oferta(gozanger, 25.0);
        Oferta oferta3 = new Oferta(martomau, 20.0);

        // When
        prod.registrarUnaOferta(oferta1);
        prod.registrarUnaOferta(oferta2);
        prod.registrarUnaOferta(oferta3);
        prod.eliminarSubastador(diazdan);

        assertEquals(diazdan.getUltimaOferta().getValorOfertado(), oferta1.getValorOfertado());
        assertEquals(diazdan.getNombre(), "diazdan");
    }

}
