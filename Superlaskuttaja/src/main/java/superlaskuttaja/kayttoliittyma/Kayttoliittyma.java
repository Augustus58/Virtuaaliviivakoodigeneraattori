/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma;

import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetPanel;
import superlaskuttaja.kayttoliittyma.yhteenveto.YhteenvetoPanel;
import superlaskuttaja.kayttoliittyma.laskut.LaskutPanel;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatPanel;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;

    public Kayttoliittyma() {
    }

    @Override
    public void run() {
        frame = new JFrame("Superlaskuttaja");
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.setLocation(100, 60);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Lataaja lataaja = new Lataaja();
        
        NappulaLukko lukko = new NappulaLukko();

        luoKomponentit(frame.getContentPane(), lataaja, lukko);

        frame.pack();
        frame.setVisible(true);

    }

    private void luoKomponentit(Container container, Lataaja lataaja, NappulaLukko lukko) {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel yhteenvetoPanel = new YhteenvetoPanel(lataaja, lukko);
        tabbedPane.addTab("Yhteenveto", null, yhteenvetoPanel, null);

        JPanel asiakkaatPanel = new AsiakkaatPanel(lataaja, lukko);
        tabbedPane.addTab("Asiakkaat", null, asiakkaatPanel, null);

        JPanel suoritteetPanel = new SuoritteetPanel(lataaja, lukko);
        tabbedPane.addTab("Suoritteet", null, suoritteetPanel, null);

        JPanel laskutPanel = new LaskutPanel(lataaja, lukko);
        tabbedPane.addTab("Laskut", null, laskutPanel, null);

        container.add(tabbedPane);
    }

    public JFrame getFrame() {
        return frame;
    }
}