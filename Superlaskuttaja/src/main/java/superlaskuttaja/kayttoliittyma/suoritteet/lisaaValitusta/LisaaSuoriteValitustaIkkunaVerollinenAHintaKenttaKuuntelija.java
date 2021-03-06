/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.lisaaValitusta;

import superlaskuttaja.kayttoliittyma.suoritteet.lisaa.*;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import superlaskuttaja.logiikka.MerkkiJaMerkkijonoTarkistin;

/**
 *
 * @author Augustus58
 */
public class LisaaSuoriteValitustaIkkunaVerollinenAHintaKenttaKuuntelija implements DocumentListener {

    JTextField aHintaVerollinenKentta;
    JTextField aHintaVerotonKentta;
    JTextField alvProsKentta;
    JRadioButton jrb3;
    JRadioButton jrb4;
    MerkkiJaMerkkijonoTarkistin tarkistin;

    public LisaaSuoriteValitustaIkkunaVerollinenAHintaKenttaKuuntelija() {
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        paivita(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        paivita(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        paivita(e);
    }

    private void paivita(DocumentEvent e) {
        if (jrb3.isSelected()) {
            double verollinenHinta;
            try {
                verollinenHinta = Double.parseDouble(aHintaVerollinenKentta.getText());
                int alvPros = Integer.parseInt(alvProsKentta.getText());
                if (0 <= alvPros && alvPros <= 100) {
                    double alvProsD = alvPros / 100.0;
                    double aHintaVeroton = verollinenHinta / (1.0 + alvProsD);
                    aHintaVerotonKentta.setText(Double.toString(aHintaVeroton));
                }
            } catch (NumberFormatException | NullPointerException ex) {
            }
        }
    }

    public void setaHintaVerollinenKentta(JTextField aHintaVerollinenKentta) {
        this.aHintaVerollinenKentta = aHintaVerollinenKentta;
    }

    public void setaHintaVerotonKentta(JTextField aHintaVerotonKentta) {
        this.aHintaVerotonKentta = aHintaVerotonKentta;
    }

    public void setAlvProsKentta(JTextField alvProsKentta) {
        this.alvProsKentta = alvProsKentta;
    }

    public void setJrb3(JRadioButton jrb3) {
        this.jrb3 = jrb3;
    }

    public void setJrb4(JRadioButton jrb4) {
        this.jrb4 = jrb4;
    }
    
}
