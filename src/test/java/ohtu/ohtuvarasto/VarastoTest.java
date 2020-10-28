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
    public void kayttokelvotonVarastoNollataan() {
        varasto = new Varasto(-1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void ylijaamaSaldoHukkaan() {
        varasto = new Varasto(10,11);
        assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kayttokelvotonVarastoSaldollaNollataan() {
        varasto = new Varasto(-1,11);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }


    @Test
    public void kayttokelvotonSaldoNollataan() {
        varasto = new Varasto(10,-11);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

        @Test
    public void varastoTayttyyAlkusaldolla() {
        varasto = new Varasto(11,10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(11, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiLisaa() {
        varasto.lisaaVarastoon(-2);

        // saldon pitäisi olla nolla
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liianIsoLisaysTayttaa() {
        varasto.lisaaVarastoon(100);

        // saldon pitäisi olla sama kuin tilavuus
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
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
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttoEiOta() {
        varasto.lisaaVarastoon(2);
        varasto.otaVarastosta(-3);

        // saldon pitäisi pysyä samana
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void isoOttoTyhjentaaKokoVaraston() {
        varasto.lisaaVarastoon(5);
        double palautus = varasto.otaVarastosta(7);

        // varaston pitäisi tyhjentyä ja sa
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(5, palautus, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tulostaaOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}