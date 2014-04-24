///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package superlaskuttaja.kayttoliittyma.laskut;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import superlaskuttaja.kayttoliittyma.GregorianCalendarRenderer;
import superlaskuttaja.kayttoliittyma.TableModelSolujenMuokkaaminenEstetty;
import superlaskuttaja.logiikka.Lasku;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LaskutTaulukko {

    private final LaskutTaulukkoJTable taulukko;
    private final TableModelSolujenMuokkaaminenEstetty model;
    private final ListSelectionModel selectionModel;
    private final TableRowSorter<TableModelSolujenMuokkaaminenEstetty> sorter;
    private final GregorianCalendarRenderer dateRenderer;
    private final Lataaja lataaja;

    public LaskutTaulukko(Lataaja lataaja) {
        this.lataaja = lataaja;
        this.taulukko = new LaskutTaulukkoJTable();
        this.model = new TableModelSolujenMuokkaaminenEstetty(new Object[][]{}, new Object[]{"Asiakas", "Asiakasnumero", "Viite", "Laskun numero", "Summa", "Päiväys", "Eräpäivä", "Maksettu"});           
        this.sorter = new TableRowSorter<>(model);
        this.taulukko.setModel(model);
        this.taulukko.setRowSorter(sorter);
        this.selectionModel = this.taulukko.getSelectionModel();
        this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.dateRenderer = new GregorianCalendarRenderer();
        this.taulukko.getColumnModel().getColumn(5).setCellRenderer(dateRenderer);
        this.taulukko.getColumnModel().getColumn(6).setCellRenderer(dateRenderer);
        muodostaLaskutTaulukko();
    }

    public final void muodostaLaskutTaulukko() {
        if (!lataaja.getLadattuTietovarasto().getLaskut().isEmpty()) {
            for (int i = 0; i < lataaja.getLadattuTietovarasto().getLaskut().size(); i++) {
                model.addRow(lataaja.getLadattuTietovarasto().getLaskut().get(i).laskunTiedotTaulukossa());
            }
        }
    }
    
    public void lisaaLaskutTaulukkoRivi(Lasku lasku) {
        model.addRow(lasku.laskunTiedotTaulukossa());
    }

    public JTable getTaulukko() {
        return taulukko;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public ListSelectionModel getSelectionModel() {
        return selectionModel;
    }

    public TableRowSorter<TableModelSolujenMuokkaaminenEstetty> getSorter() {
        return sorter;
    }
    
    public String getValueString(Integer x, Integer y) {
        return (getModel().getValueAt(x, y).toString());
    }
}
