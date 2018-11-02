package ohtu.ohtuvarasto;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private double tilavuus;  // paljonko varastoon mahtuu,  > 0
    private double saldo;     // paljonko varastossa on nyt, >= 0

    // --- konstruktorit: ---
    public Varasto(double tilavuus) {
        if (tilavuus > 0.0) {
            this.tilavuus = tilavuus;
        } else {
            this.tilavuus = 0.0;
        }
        saldo = 0;
    }

    public Varasto(double tilavuus, double alkuSaldo) {
        this(tilavuus);

        if ( alkuSaldo < 0) {
            this.saldo = 0;
        } else if (alkuSaldo <= tilavuus) {
            this.saldo = alkuSaldo;
        } else {
            this.saldo = tilavuus;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() {
        return tilavuus - saldo;
    }

    public void lisaaVarastoon(double maara) {
        if (maara < 0) {
            return;
        }
        if (maara <= paljonkoMahtuu()) {
            saldo = saldo + maara;
        } else {
            saldo = tilavuus;
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < 0) {
            return 0.0;
        }
        if (maara > saldo) {
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0;
            return kaikkiMitaVoidaan;
        }
        saldo = saldo - maara;
        return maara;
    }

    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}