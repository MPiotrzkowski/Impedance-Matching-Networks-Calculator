package Impedance_Matching_Networks_Calculator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Okno dostosowywania obliczonych parametrów układu dopasowującego.
 * Klasa dziedzicząca po klasie JFrame.
 * Klasa implementująca interfejs ActionListener.
 * @author Mikołaj Piotrzkowski
 */
public class OknoDostosuj extends JFrame implements ActionListener {

    JPanel panel;

    private JLabel cLabel = new JLabel("C = ");
    private static JTextField cField = new JTextField(5);
    private static String[] cJednostki = {"F", "mF", "μF", "nF", "pF", "fF", "aF", "zF", "yF"};
    private static JComboBox cJednostka = new JComboBox(cJednostki);
    private static Double c = 0.0;

    private JLabel csLabel = new JLabel("CS = ");
    private static JTextField csField = new JTextField(5);
    private static JComboBox csJednostka = new JComboBox(cJednostki);
    private static Double cs = 0.0;

    private JLabel clLabel = new JLabel("CL = ");
    private static JTextField clField = new JTextField(5);
    private static JComboBox clJednostka = new JComboBox(cJednostki);
    private static Double cl = 0.0;

    private JLabel lLabel = new JLabel("L = ");
    private static JTextField lField = new JTextField(5);
    private static String[] lJednostki = {"H", "mH", "μH", "nH", "pH", "fH", "aH", "zH", "yH"};
    private static JComboBox lJednostka = new JComboBox(lJednostki);
    private static Double l = 0.0;

    private JLabel lsLabel = new JLabel("LS = ");
    private static JTextField lsField = new JTextField(5);
    private static JComboBox lsJednostka = new JComboBox(lJednostki);
    private static Double ls = 0.0;

    private JLabel llLabel = new JLabel("LL = ");
    private static JTextField llField = new JTextField(5);
    private static JComboBox llJednostka = new JComboBox(lJednostki);
    private static Double ll = 0.0;

    private static JButton anulujButton = new JButton("Cancel");
    private static JButton przeliczButton = new JButton("Recalculate");

    private static boolean poprawnoscWartosci = true;

    /**
     * Główny konstruktor klasy. Utworzenie i dodanie panelu do okna oraz ustawienie parametrów.
     * @param typUkladu obecnie wybrany układ (0 - L, 1 - Pi, 2 - T)
     */
    public OknoDostosuj(int typUkladu) {

        App.getOknoProgram().setEnabled(false);

        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);

        panel.add(anulujButton);
        anulujButton.setVisible(true);
        anulujButton.addActionListener(this);

        panel.add(przeliczButton);
        przeliczButton.setVisible(true);
        przeliczButton.addActionListener(this);

        switch (typUkladu) {
            case 0:
                panel.add(cLabel);
                cLabel.setBounds(5, 3, cLabel.getPreferredSize().width, cLabel.getPreferredSize().height);
                panel.add(cField);
                cField.setBounds(35, 3, cField.getPreferredSize().width, cField.getPreferredSize().height);
                cField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladL.getC()).re()));
                panel.add(cJednostka);
                cJednostka.setBounds(100, 0, cJednostka.getPreferredSize().width, cJednostka.getPreferredSize().height);
                cJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladL.getC()).im());
                panel.add(lLabel);
                lLabel.setBounds(5, 33, lLabel.getPreferredSize().width, lLabel.getPreferredSize().height);
                panel.add(lField);
                lField.setBounds(35, 33, lField.getPreferredSize().width, lField.getPreferredSize().height);
                lField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladL.getL()).re()));
                panel.add(lJednostka);
                lJednostka.setBounds(100, 30, lJednostka.getPreferredSize().width, lJednostka.getPreferredSize().height);
                lJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladL.getL()).im());

                anulujButton.setBounds(203, 0, anulujButton.getPreferredSize().width, anulujButton.getPreferredSize().height);
                przeliczButton.setBounds(190, 30, przeliczButton.getPreferredSize().width, przeliczButton.getPreferredSize().height);
                break;
            case 1:
                if (Parametry.getDolnoGornoPrzepustowy() == 0){
                    panel.add(lLabel);
                    lLabel.setBounds(5, 3, lLabel.getPreferredSize().width, lLabel.getPreferredSize().height);
                    panel.add(lField);
                    lField.setBounds(35, 3, lField.getPreferredSize().width, lField.getPreferredSize().height);
                    lField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladPi.getL()).re()));
                    panel.add(lJednostka);
                    lJednostka.setBounds(100, 0, lJednostka.getPreferredSize().width, lJednostka.getPreferredSize().height);
                    lJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladPi.getL()).im());
                    panel.add(csLabel);
                    csLabel.setBounds(5, 33, csLabel.getPreferredSize().width, csLabel.getPreferredSize().height);
                    panel.add(csField);
                    csField.setBounds(35, 33, csField.getPreferredSize().width, csField.getPreferredSize().height);
                    csField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladPi.getCs()).re()));
                    panel.add(csJednostka);
                    csJednostka.setBounds(100, 30, csJednostka.getPreferredSize().width, csJednostka.getPreferredSize().height);
                    csJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladPi.getCs()).im());
                    panel.add(clLabel);
                    clLabel.setBounds(5, 63, clLabel.getPreferredSize().width, clLabel.getPreferredSize().height);
                    panel.add(clField);
                    clField.setBounds(35, 63, clField.getPreferredSize().width, clField.getPreferredSize().height);
                    clField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladPi.getCl()).re()));
                    panel.add(clJednostka);
                    clJednostka.setBounds(100, 60, clJednostka.getPreferredSize().width, clJednostka.getPreferredSize().height);
                    clJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladPi.getCl()).im());

                    anulujButton.setBounds(203, 15, anulujButton.getPreferredSize().width, anulujButton.getPreferredSize().height);
                    przeliczButton.setBounds(190, 45, przeliczButton.getPreferredSize().width, przeliczButton.getPreferredSize().height);
                }
                if (Parametry.getDolnoGornoPrzepustowy() == 1){
                    panel.add(cLabel);
                    cLabel.setBounds(5, 3, cLabel.getPreferredSize().width, cLabel.getPreferredSize().height);
                    panel.add(cField);
                    cField.setBounds(35, 3, cField.getPreferredSize().width, cField.getPreferredSize().height);
                    cField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladPi.getC()).re()));
                    panel.add(cJednostka);
                    cJednostka.setBounds(100, 0, cJednostka.getPreferredSize().width, cJednostka.getPreferredSize().height);
                    cJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladPi.getC()).im());
                    panel.add(lsLabel);
                    lsLabel.setBounds(5, 33, lsLabel.getPreferredSize().width, lsLabel.getPreferredSize().height);
                    panel.add(lsField);
                    lsField.setBounds(35, 33, lsField.getPreferredSize().width, lsField.getPreferredSize().height);
                    lsField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladPi.getLs()).re()));
                    panel.add(lsJednostka);
                    lsJednostka.setBounds(100, 30, lsJednostka.getPreferredSize().width, lsJednostka.getPreferredSize().height);
                    lsJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladPi.getLs()).im());
                    panel.add(llLabel);
                    llLabel.setBounds(5, 63, llLabel.getPreferredSize().width, llLabel.getPreferredSize().height);
                    panel.add(llField);
                    llField.setBounds(35, 63, llField.getPreferredSize().width, llField.getPreferredSize().height);
                    llField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladPi.getLl()).re()));
                    panel.add(llJednostka);
                    llJednostka.setBounds(100, 60, llJednostka.getPreferredSize().width, llJednostka.getPreferredSize().height);
                    llJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladPi.getLl()).im());

                    anulujButton.setBounds(203, 15, anulujButton.getPreferredSize().width, anulujButton.getPreferredSize().height);
                    przeliczButton.setBounds(190, 45, przeliczButton.getPreferredSize().width, przeliczButton.getPreferredSize().height);
                }
                break;

            case 2:
                if (Parametry.getDolnoGornoPrzepustowy() == 0){
                    panel.add(cLabel);
                    cLabel.setBounds(5, 3, cLabel.getPreferredSize().width, cLabel.getPreferredSize().height);
                    panel.add(cField);
                    cField.setBounds(35, 3, cField.getPreferredSize().width, cField.getPreferredSize().height);
                    cField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladT.getC()).re()));
                    panel.add(cJednostka);
                    cJednostka.setBounds(100, 0, cJednostka.getPreferredSize().width, cJednostka.getPreferredSize().height);
                    cJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladT.getC()).im());
                    panel.add(lsLabel);
                    lsLabel.setBounds(5, 33, lsLabel.getPreferredSize().width, lsLabel.getPreferredSize().height);
                    panel.add(lsField);
                    lsField.setBounds(35, 33, lsField.getPreferredSize().width, lsField.getPreferredSize().height);
                    lsField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladT.getLs()).re()));
                    panel.add(lsJednostka);
                    lsJednostka.setBounds(100, 30, lsJednostka.getPreferredSize().width, lsJednostka.getPreferredSize().height);
                    lsJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladT.getLs()).im());
                    panel.add(llLabel);
                    llLabel.setBounds(5, 63, llLabel.getPreferredSize().width, llLabel.getPreferredSize().height);
                    panel.add(llField);
                    llField.setBounds(35, 63, llField.getPreferredSize().width, llField.getPreferredSize().height);
                    llField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladT.getLl()).re()));
                    panel.add(llJednostka);
                    llJednostka.setBounds(100, 60, llJednostka.getPreferredSize().width, llJednostka.getPreferredSize().height);
                    llJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladT.getLl()).im());

                    anulujButton.setBounds(203, 15, anulujButton.getPreferredSize().width, anulujButton.getPreferredSize().height);
                    przeliczButton.setBounds(190, 45, przeliczButton.getPreferredSize().width, przeliczButton.getPreferredSize().height);

                }
                if (Parametry.getDolnoGornoPrzepustowy() == 1){
                    panel.add(lLabel);
                    lLabel.setBounds(5, 3, lLabel.getPreferredSize().width, lLabel.getPreferredSize().height);
                    panel.add(lField);
                    lField.setBounds(35, 3, lField.getPreferredSize().width, lField.getPreferredSize().height);
                    lField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladT.getL()).re()));
                    panel.add(lJednostka);
                    lJednostka.setBounds(100, 0, lJednostka.getPreferredSize().width, lJednostka.getPreferredSize().height);
                    lJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladT.getL()).im());
                    panel.add(csLabel);
                    csLabel.setBounds(5, 33, csLabel.getPreferredSize().width, csLabel.getPreferredSize().height);
                    panel.add(csField);
                    csField.setBounds(35, 33, csField.getPreferredSize().width, csField.getPreferredSize().height);
                    csField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladT.getCs()).re()));
                    panel.add(csJednostka);
                    csJednostka.setBounds(100, 30, csJednostka.getPreferredSize().width, csJednostka.getPreferredSize().height);
                    csJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladT.getCs()).im());
                    panel.add(clLabel);
                    clLabel.setBounds(5, 63, clLabel.getPreferredSize().width, clLabel.getPreferredSize().height);
                    panel.add(clField);
                    clField.setBounds(35, 63, clField.getPreferredSize().width, clField.getPreferredSize().height);
                    clField.setText(String.valueOf(Parametry.jednostkaRzadWielkosci(UkladT.getCl()).re()));
                    panel.add(clJednostka);
                    clJednostka.setBounds(100, 60, clJednostka.getPreferredSize().width, clJednostka.getPreferredSize().height);
                    clJednostka.setSelectedIndex((int) Parametry.jednostkaRzadWielkosci(UkladT.getCl()).im());

                    anulujButton.setBounds(203, 15, anulujButton.getPreferredSize().width, anulujButton.getPreferredSize().height);
                    przeliczButton.setBounds(190, 45, przeliczButton.getPreferredSize().width, przeliczButton.getPreferredSize().height);
                }
                break;
        }

        add(panel);

        setAlwaysOnTop(true);
        setSize(317, 100);
        setResizable(false);
        setUndecorated(true);
        setLocation((int) (App.getOknoProgram().getLocation().getX() + 320), (int) (App.getOknoProgram().getLocation().getY() + 210));
        setVisible(true);
    }


    /**
     * Nadpisz metodę odpowiedzialną za reakcję
     * programu na przyciśnięcie przycisku.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        poprawnoscWartosci = true;
        if (zrodlo == anulujButton){
            this.setVisible(false);
            dispose();
            App.getOknoProgram().setEnabled(true);
            OknoProgram.getPanelUklady().repaint();
            App.getOknoProgram().toFront();
        }
        if (zrodlo == przeliczButton){
            switch (Parametry.getWybranyUklad()){
                case 0:
                    try {
                        c = Double.valueOf(cField.getText().replace(',', '.'));
                    } catch (NumberFormatException arg){
                        poprawnoscWartosci = false;
                    }
                    try {
                        l = Double.valueOf(lField.getText().replace(',', '.'));
                    } catch (NumberFormatException arg){
                        poprawnoscWartosci = false;
                    }
                    if (poprawnoscWartosci){
                        UkladL.setC(Parametry.jednostkaRazyRzadWielkosci(c, cJednostka.getSelectedIndex()));
                        UkladL.setL(Parametry.jednostkaRazyRzadWielkosci(l, lJednostka.getSelectedIndex()));
                    }
                    break;
                case 1:
                    if (Parametry.getDolnoGornoPrzepustowy() == 0){
                        try {
                            l = Double.valueOf(lField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        try {
                            cs = Double.valueOf(csField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        try {
                            ls = Double.valueOf(lsField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        if (poprawnoscWartosci){
                            UkladPi.setL(Parametry.jednostkaRazyRzadWielkosci(l, lJednostka.getSelectedIndex()));
                            UkladPi.setCs(Parametry.jednostkaRazyRzadWielkosci(cs, csJednostka.getSelectedIndex()));
                            UkladPi.setCl(Parametry.jednostkaRazyRzadWielkosci(ls, lsJednostka.getSelectedIndex()));
                        }
                    }
                    if (Parametry.getDolnoGornoPrzepustowy() == 1){
                        try {
                            c = Double.valueOf(cField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        try {
                            ls = Double.valueOf(lField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        try {
                            ll = Double.valueOf(lField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        if (poprawnoscWartosci){
                            UkladPi.setC(Parametry.jednostkaRazyRzadWielkosci(c, cJednostka.getSelectedIndex()));
                            UkladPi.setLs(Parametry.jednostkaRazyRzadWielkosci(ls, lsJednostka.getSelectedIndex()));
                            UkladPi.setLl(Parametry.jednostkaRazyRzadWielkosci(ll, llJednostka.getSelectedIndex()));
                        }
                    }
                    break;
                case 2:
                    if (Parametry.getDolnoGornoPrzepustowy() == 0){
                        try {
                            c = Double.valueOf(cField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        try {
                            ls = Double.valueOf(lField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        try {
                            ll = Double.valueOf(lField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        if (poprawnoscWartosci){
                            UkladT.setC(Parametry.jednostkaRazyRzadWielkosci(c, cJednostka.getSelectedIndex()));
                            UkladT.setLs(Parametry.jednostkaRazyRzadWielkosci(ls, lsJednostka.getSelectedIndex()));
                            UkladT.setLl(Parametry.jednostkaRazyRzadWielkosci(ll, llJednostka.getSelectedIndex()));
                        }
                    }
                    if (Parametry.getDolnoGornoPrzepustowy() == 1){
                        try {
                            l = Double.valueOf(lField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        try {
                            cs = Double.valueOf(cField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        try {
                            cl = Double.valueOf(lField.getText().replace(',', '.'));
                        } catch (NumberFormatException arg){
                            poprawnoscWartosci = false;
                        }
                        if (poprawnoscWartosci){
                            UkladT.setL(Parametry.jednostkaRazyRzadWielkosci(l, lJednostka.getSelectedIndex()));
                            UkladT.setCs(Parametry.jednostkaRazyRzadWielkosci(cs, csJednostka.getSelectedIndex()));
                            UkladT.setCl(Parametry.jednostkaRazyRzadWielkosci(cl, clJednostka.getSelectedIndex()));
                        }
                    }
                    break;
            }
            this.setVisible(false);
            dispose();
            App.getOknoProgram().setEnabled(true);
            OknoProgram.getPanelUklady().repaint();
            App.getOknoProgram().toFront();
        }
    }
}