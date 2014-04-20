/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.yhteenveto;

import superlaskuttaja.kayttoliittyma.yhteenveto.muokkaa.MuokkaaLaskuttajanTietojaIkkuna;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaKuuntelija implements ActionListener {

    private final Lataaja lataaja;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel;

    public LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaKuuntelija(Lataaja lataaja, NappulaLukko lukko, LaskuttajaOsioJPanel panel) {
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            MuokkaaLaskuttajanTietojaIkkuna muokkaaTietoja = new MuokkaaLaskuttajanTietojaIkkuna(lataaja, lukko, panel);
            SwingUtilities.invokeLater(muokkaaTietoja);
        }
    }

}