/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.ComboBoxKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.Asiakas;
import superlaskuttaja.logiikka.Lataaja;
import superlaskuttaja.logiikka.MerkkiJaMerkkijonoTarkistin;
import superlaskuttaja.logiikka.Suorite;

/**
 *
 * @author Augustus58
 */
public class LisaaSuoriteIkkunaLisaaKuuntelija implements ActionListener {

    private final ComboBoxKuuntelija kuuntelija;
    private final JTextField kuvausKentta;
    private final JTextField pvmKentta;
    private final JTextField maaraKentta;
    private final JTextField maaranYksikotKentta;
    private final JTextField aHintaKentta;
    private final JTextField alvProsKentta;
    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;
    private final JFrame frame;
    private final NappulaLukko lukko;
    private final MerkkiJaMerkkijonoTarkistin tarkistin;

    public LisaaSuoriteIkkunaLisaaKuuntelija(ComboBoxKuuntelija kuuntelija, JTextField kuvausKentta, JTextField pvmKentta, JTextField maaraKentta, JTextField maaranYksikotKentta, JTextField aHintaKentta, JTextField alvProsKentta, Lataaja lataaja, SuoritteetTaulukko taulukko, JFrame frame, NappulaLukko lukko) {
        this.kuuntelija = kuuntelija;
        this.kuvausKentta = kuvausKentta;
        this.pvmKentta = pvmKentta;
        this.maaraKentta = maaraKentta;
        this.maaranYksikotKentta = maaranYksikotKentta;
        this.aHintaKentta = aHintaKentta;
        this.alvProsKentta = alvProsKentta;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
        this.lukko = lukko;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {

        Asiakas suoritteenAsiakas = lataaja.getLadattuTietovarasto().getAsiakkaat().get(kuuntelija.getValinta());

        if (!tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn(pvmKentta.getText())) {
            throw new IllegalArgumentException("Syöte päivämäärä on virheellinen.");
        }

        if (!tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi(pvmKentta.getText())) {
            throw new IllegalArgumentException("Syöte päivämäärä on virheellinen.");
        }
        
        Integer vuosi = Integer.parseInt(pvmKentta.getText().substring(6, 10));
        Integer kuukausi = Integer.parseInt(pvmKentta.getText().substring(3, 5));
        Integer paiva = Integer.parseInt(pvmKentta.getText().substring(0, 2));
        Date date = new Date(vuosi - 1900, kuukausi - 1, paiva);

        Suorite suorite = new Suorite(suoritteenAsiakas,
                kuvausKentta.getText(),
                date,
                Double.parseDouble(maaraKentta.getText()),
                maaranYksikotKentta.getText(),
                Double.parseDouble(aHintaKentta.getText()),
                Integer.parseInt(alvProsKentta.getText()));

        if (!suorite.onkoTiedotOikeanlaisetPaitsiPvm()) {
            throw new IllegalArgumentException("Jokin muu syöte, kuin päivämäärä on virheellinen.");
        }
        lataaja.getLadattuTietovarasto().getSuoritteet().add(suorite);
        taulukko.addSuoritteetTaulukkoRivi(suorite);
        suljeIkkuna();
        } catch (Exception e) {
            LisaaSuoriteIkkunaLisaaSuoritePoikkeusIkkuna poikkeusIkkuna = new LisaaSuoriteIkkunaLisaaSuoritePoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }

    }

    private void suljeIkkuna() {
        frame.dispose();
        lukko.avaa();
    }
}