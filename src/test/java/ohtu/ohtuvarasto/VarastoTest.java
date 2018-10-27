package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiSalliNegatiivistaTilavuutta() {
        assertEquals(0, new Varasto(-1).getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriEiSalliNegatiivisaArvoja() {
        Varasto v = new Varasto(-1, -1);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriAsettaaArvot() {
        Varasto v = new Varasto(10, 10);
        assertEquals(10, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriEiAsetaTilavuuttaSuurempaaSaldoa() {
        Varasto v = new Varasto(10, 20);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(-10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldonYlittaessaTilavuudenTaytetaanSaldo() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(1);
        double saatuMaara = varasto.otaVarastosta(-1);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void otettuMaaraEiVoiYlittaaSaldoa() {
        int currentSaldo = 10;
        varasto.lisaaVarastoon(currentSaldo);
        double saatuMaara = varasto.otaVarastosta(20);
        assertEquals(currentSaldo, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tuottaaMerkkijonoEsityksen() {
        String expected = "saldo = 0.0, vielä tilaa 10.0 asd";
        assertEquals(expected, varasto.toString());
    }
}