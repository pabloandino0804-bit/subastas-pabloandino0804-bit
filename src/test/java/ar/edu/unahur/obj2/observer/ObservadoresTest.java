package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.excepciones.NegocioNoChequeadoException;
import ar.edu.unahur.obj2.observer.observadores.ISubastador;
import ar.edu.unahur.obj2.observer.observadores.Subastador;
import ar.edu.unahur.obj2.observer.ofertas.Oferta;

public class ObservadoresTest {
    private ProductoSubastado producto = new ProductoSubastado();
    private Subastador gozanger = new Subastador("gonzanger");
    private Subastador diazdan = new Subastador("diazdan");
    private Subastador martomau = new Subastador("martomau");

    @BeforeEach
    void setup() {
        producto.reiniciarProducto();
        gozanger.reiniciar();
        diazdan.reiniciar();
        martomau.reiniciar();
        producto.registrarSubastador(gozanger);
        producto.registrarSubastador(martomau);
    }

    @Test
    void dadoElEscenario1_ambosSubastadoresDebenRecibirLaUltimaOfertaRealizada() {
        // Dado
        Oferta oferta1 = new Oferta(martomau, 20.0);
        Oferta oferta2 = new Oferta(gozanger, 35.0);
        Oferta oferta3 = new Oferta(martomau, 30.0);

        // When
        producto.registrarUnaOferta(oferta1);
        producto.registrarUnaOferta(oferta2);
        producto.registrarUnaOferta(oferta3);

        // Debe devolver
        ISubastador subDado1 = gozanger.getUltimaOferta().getSubastador();
        ISubastador subDado2 = martomau.getUltimaOferta().getSubastador();
        assertEquals(subDado1, oferta3.getSubastador());
        assertEquals(subDado2, oferta3.getSubastador());
        assertTrue(producto.getSubastadores().contains(martomau));
    }

    @Test
    void dadoElEscenario1_laUltimaOfertaEnAmbosSubastadores_debeSerLAQuePerteneceAMartomau() {
        // Dado
        Oferta oferta1 = new Oferta(martomau, 20.0);
        Oferta oferta2 = new Oferta(gozanger, 35.0);
        Oferta oferta3 = new Oferta(martomau, 30.0);

        // When
        producto.registrarUnaOferta(oferta1);
        producto.registrarUnaOferta(oferta2);
        producto.registrarUnaOferta(oferta3);

        // Debe devolver
        ISubastador subDado1 = gozanger.getUltimaOferta().getSubastador();
        ISubastador subDado2 = martomau.getUltimaOferta().getSubastador();
        assertEquals(subDado1.getNombre(), martomau.getNombre());
        assertEquals(subDado2.getNombre(), martomau.getNombre());
    }

    @Test
    void dadoElEscenario1_elValorDelaUltimaOfertaEnAmbosSubastadoresDebeSer30() {
        // Dado
        Oferta oferta1 = new Oferta(martomau, 20.0);
        Oferta oferta2 = new Oferta(gozanger, 35.0);
        Oferta oferta3 = new Oferta(martomau, 30.0);

        // When
        producto.registrarUnaOferta(oferta1);
        producto.registrarUnaOferta(oferta2);
        producto.registrarUnaOferta(oferta3);

        // Debe devolver
        assertEquals(gozanger.getUltimaOferta(), oferta3);
        assertEquals(gozanger.getUltimaOferta().getValorOfertado(), 30.0);
        assertEquals(martomau.getUltimaOferta().getValorOfertado(), 30.0);
    }

    @Test
    void dadoElEscenario1_elProductoSubestimadoDebeTenerRegistrado_las3Ofertas() {
        // Dado
        Oferta oferta1 = new Oferta(martomau, 20.0);
        Oferta oferta2 = new Oferta(gozanger, 35.0);
        Oferta oferta3 = new Oferta(martomau, 30.0);

        // When
        producto.registrarUnaOferta(oferta1);
        producto.registrarUnaOferta(oferta2);
        producto.registrarUnaOferta(oferta3);

        // Debe devolver
        assertEquals(producto.getOfertasRecibidas().size(), 3);
    }

    @Test
    void dadoElEscenario1_SiElProductoIntentaRegistrarUnaNuevaOfertDeDizdan_TiraUnaExcepcion() {
        // Dado
        Oferta oferta1 = new Oferta(martomau, 20.0);
        Oferta oferta2 = new Oferta(gozanger, 35.0);
        Oferta oferta3 = new Oferta(martomau, 30.0);
        Oferta nuevaOferta = new Oferta(diazdan, 24.5);

        // When
        producto.registrarUnaOferta(oferta1);
        producto.registrarUnaOferta(oferta2);
        producto.registrarUnaOferta(oferta3);

        // Debe tirar una Excepcion
        assertThrows(NegocioNoChequeadoException.class, () -> producto.registrarUnaOferta(nuevaOferta));
    }
}