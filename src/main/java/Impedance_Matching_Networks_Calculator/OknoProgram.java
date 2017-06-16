package Impedance_Matching_Networks_Calculator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * Okno główne programu.
 * Klasa dziedzicząca po klasie JFrame.
 * @author Mikołaj Piotrzkowski
 */
public class OknoProgram extends JFrame{

    private static JPanel panelMenu = new PanelMenu();
    private static JPanel panelUklady = new PanelUklad();

    /**
     * Główny konstruktor klasy. Dodanie panelu menu i panelu układy do okna oraz ustawienie parametrów.
     */
    public OknoProgram() {
        super("Impedance Matching Networks Calculator");
        Parametry.wczytajObrazy();
        setLayout(new GridLayout(1, 2));
        add(panelMenu);
        add(panelUklady);
        setIconImage(Parametry.getIkona());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Parametry.getSzerokoscOkna(), Parametry.getWysokoscOkna());
        setResizable(false);
        setLocation(Parametry.getWspolrzednaXOkna(), Parametry.getWspolrzednaYOkna());
        setVisible(true);

        Parametry.zerujParametryWejsciowe();
    }

    public static JPanel getPanelUklady() {
        return panelUklady;
    }

}
