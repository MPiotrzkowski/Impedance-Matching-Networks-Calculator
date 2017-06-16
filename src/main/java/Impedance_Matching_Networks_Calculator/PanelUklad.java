package Impedance_Matching_Networks_Calculator;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel wyświetlający wyniki obliczeń. W panelu znajdują się przyciski do
 * wyświetlania wykresów oraz przycisk dostosowywania parametrów układu.
 * Klasa dziedzicząca po klasie JPanel.
 * Klasa implementująca interfejs ActionListener.
 * @author Mikołaj Piotrzkowski
 */
public class PanelUklad extends JPanel implements ActionListener {

    private static JButton dostosujWynikiButton = new JButton("Adjust Calculated Values");

    private static JButton wykresImpedancjiButton = new JButton("Zin Chart");
    private static JButton wykresWspolczynnikaOdbiciaButton = new JButton("|Γ| Chart");
    private static JButton wykresWFSButton = new JButton("VSWR Chart");
    private static JButton wykresReturnLossButton = new JButton("Return Loss Chart");
    private static JButton wykresMismatchLossButton = new JButton("Mismatch Loss Chart");

    /**
     * Główny konstruktor klasy. Dodanie i ustawienie wszystkich
     * elementów oraz dodanie obsługi zdarzeń.
     */
    public PanelUklad(){
        setLayout(null);

        this.add(dostosujWynikiButton);
        ustawDostosujButton();
        dostosujWynikiButton.setVisible(false);
        dostosujWynikiButton.addActionListener(this);

        this.add(wykresImpedancjiButton);
        wykresImpedancjiButton.setBounds(8, 305, wykresImpedancjiButton.getPreferredSize().width, wykresImpedancjiButton.getPreferredSize().height);
        wykresImpedancjiButton.setVisible(false);
        wykresImpedancjiButton.addActionListener(this);

        this.add(wykresWspolczynnikaOdbiciaButton);
        wykresWspolczynnikaOdbiciaButton.setBounds(109, 305, wykresWspolczynnikaOdbiciaButton.getPreferredSize().width, wykresWspolczynnikaOdbiciaButton.getPreferredSize().height);
        wykresWspolczynnikaOdbiciaButton.setVisible(false);
        wykresWspolczynnikaOdbiciaButton.addActionListener(this);

        this.add(wykresWFSButton);
        wykresWFSButton.setBounds(205, 305, wykresWFSButton.getPreferredSize().width, wykresWFSButton.getPreferredSize().height);
        wykresWFSButton.setVisible(false);
        wykresWFSButton.addActionListener(this);

        this.add(wykresReturnLossButton);
        wykresReturnLossButton.setBounds(8, 350, wykresReturnLossButton.getPreferredSize().width, wykresReturnLossButton.getPreferredSize().height);
        wykresReturnLossButton.setVisible(false);
        wykresReturnLossButton.addActionListener(this);

        this.add(wykresMismatchLossButton);
        wykresMismatchLossButton.setBounds(155, 350, wykresMismatchLossButton.getPreferredSize().width, wykresMismatchLossButton.getPreferredSize().height);
        wykresMismatchLossButton.setVisible(false);
        wykresMismatchLossButton.addActionListener(this);
    }

    /**
     * Nadpisz metodę odpowiedzialną za odrysowanie panelu
     * (własne wypełnienie pola graficznego gry).
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(Parametry.getTlo(), 0, 0, this);
        g2d.setColor(Color.BLACK);
        g2d.setFont(Parametry.getWynikiCzcionka());

        if (Parametry.isPoprawnoscDanych()){
            switch (Parametry.getWybranyUklad()){
                case 0:
                    if (Parametry.getRezystancjaZrodla() > Parametry.getRezystancjaObciazenia()){
                        if (Parametry.getDolnoGornoPrzepustowy() == 0){
                            g2d.drawImage(Parametry.getUkladLB(), 20, 10, this);
                            g2d.drawString("C = " +Parametry.faradyJednostka(UkladL.getC()), 5, 200);
                            g2d.drawString("L = " +Parametry.henryJednostka(UkladL.getL()), 5, 230);
                        }
                        if (Parametry.getDolnoGornoPrzepustowy() == 1){
                            g2d.drawImage(Parametry.getUkladLD(), 20, 10, this);
                            g2d.drawString("C = " +Parametry.faradyJednostka(UkladL.getC()), 5, 200);
                            g2d.drawString("L = " +Parametry.henryJednostka(UkladL.getL()), 5, 230);
                        }
                    }
                    if (Parametry.getRezystancjaZrodla() < Parametry.getRezystancjaObciazenia()){
                        if (Parametry.getDolnoGornoPrzepustowy() == 0){
                            g2d.drawImage(Parametry.getUkladLA(), 20, 10, this);
                            g2d.drawString("C = " +Parametry.faradyJednostka(UkladL.getC()), 5, 200);
                            g2d.drawString("L = " +Parametry.henryJednostka(UkladL.getL()), 5, 230);
                        }
                        if (Parametry.getDolnoGornoPrzepustowy() == 1){
                            g2d.drawImage(Parametry.getUkladLC(), 20, 10, this);
                            g2d.drawString("C = " +Parametry.faradyJednostka(UkladL.getC()), 5, 200);
                            g2d.drawString("L = " +Parametry.henryJednostka(UkladL.getL()), 5, 230);
                        }
                    }
                    break;
                case 1:
                    if (Parametry.getRezystancjaZrodla() != Parametry.getRezystancjaObciazenia()){
                        if (Parametry.getDolnoGornoPrzepustowy() == 0){
                            g2d.drawImage(Parametry.getUkladPiLP(), 20, 10, this);
                            g2d.drawString("L = " +Parametry.henryJednostka(UkladPi.getL()), 5, 200);
                            g2d.drawString("CS = " +Parametry.faradyJednostka(UkladPi.getCs()), 5, 230);
                            g2d.drawString("CL = " +Parametry.faradyJednostka(UkladPi.getCl()), 5, 260);
                        }
                        if (Parametry.getDolnoGornoPrzepustowy() == 1){
                            g2d.drawImage(Parametry.getUkladPiHP(), 20, 10, this);
                            g2d.drawString("C = " +Parametry.faradyJednostka(UkladPi.getC()), 5, 200);
                            g2d.drawString("LS = " +Parametry.henryJednostka(UkladPi.getLs()), 5, 230);
                            g2d.drawString("LL = " +Parametry.henryJednostka(UkladPi.getLl()), 5, 260);
                        }
                    }
                    break;
                case 2:
                    if (Parametry.getRezystancjaZrodla() != Parametry.getRezystancjaObciazenia()){
                        if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                            g2d.drawImage(Parametry.getUkladTLP(), 20, 10, this);
                            g2d.drawString("C = " + Parametry.faradyJednostka(UkladT.getC()), 5, 200);
                            g2d.drawString("LS = " + Parametry.henryJednostka(UkladT.getLs()), 5, 230);
                            g2d.drawString("LL = " + Parametry.henryJednostka(UkladT.getLl()), 5, 260);
                        }
                        if (Parametry.getDolnoGornoPrzepustowy() == 1){
                            g2d.drawImage(Parametry.getUkladTHP(), 20, 10, this);
                            g2d.drawString("L = " +Parametry.henryJednostka(UkladT.getL()), 5, 200);
                            g2d.drawString("CS = " +Parametry.faradyJednostka(UkladT.getCs()), 5, 230);
                            g2d.drawString("CL = " +Parametry.faradyJednostka(UkladT.getCl()), 5, 260);
                        }
                    }
                    break;
            }
        }
        else{
            g2d.setColor(Color.RED);
            g2d.setFont(Parametry.getKomunikatCzcionka());
            g2d.drawString("INVALID INPUT DATA", 100, 50);
            StringBuilder parametryNieprawidlowe = new StringBuilder();
            if (!Parametry.isPoprawnaRezystancjaZrodla()){
                parametryNieprawidlowe.append("RS ");
            }
            if (!Parametry.isPoprawnaReaktancjaZrodla()){
                parametryNieprawidlowe.append("XS ");
            }
            if (!Parametry.isPoprawnaRezystancjaObciazenia()){
                parametryNieprawidlowe.append("RL ");
            }
            if (!Parametry.isPoprawnaReaktancjaObciazenia()){
                parametryNieprawidlowe.append("XL ");
            }
            if (!Parametry.isPoprawnaCzestotliwosc()){
                parametryNieprawidlowe.append("f ");
            }
            if (!Parametry.isPoprawnaSzerokoscPasma()){
                parametryNieprawidlowe.append("BW ");
            }
            if (!Parametry.isPoprawneQ()){
                parametryNieprawidlowe.append("Q ");
            }
            g2d.drawString("Invalid parameters: " + parametryNieprawidlowe.toString(), 80, 80);
        }
    }

    /**
     * Nadpisz metodę odpowiedzialną za reakcję
     * programu na przyciśnięcie przycisku.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        if (zrodlo == dostosujWynikiButton){
            new OknoDostosuj(Parametry.getWybranyUklad());
        }
        if (zrodlo == wykresImpedancjiButton){
            Wykres.impedancjaWejsciowa();
        }
        if (zrodlo == wykresWspolczynnikaOdbiciaButton){
            Wykres.wspolczynnikOdbicia();
        }
        if (zrodlo == wykresWFSButton){
            Wykres.wfs();
        }
        if (zrodlo == wykresReturnLossButton){
            Wykres.stratyOdbiciowe();
        }
        if (zrodlo == wykresMismatchLossButton){
            Wykres.stratyNiedopasowania();
        }
    }

    /**
     * Ustaw przycisk dostosowywania wyników
     * w zależności od wybranego układu.
     */
    public static void ustawDostosujButton(){
        switch (Parametry.getWybranyUklad()){
            case 0:
                dostosujWynikiButton.setBounds(130, 200, dostosujWynikiButton.getPreferredSize().width, dostosujWynikiButton.getPreferredSize().height);
                break;
            case 1:
                dostosujWynikiButton.setBounds(130, 210, dostosujWynikiButton.getPreferredSize().width, dostosujWynikiButton.getPreferredSize().height);
                break;
            case 2:
                dostosujWynikiButton.setBounds(130, 210, dostosujWynikiButton.getPreferredSize().width, dostosujWynikiButton.getPreferredSize().height);
                break;
        }
    }

    public static JButton getDostosujWynikiButton() {
        return dostosujWynikiButton;
    }

    public static JButton getWykresImpedancjiButton() {
        return wykresImpedancjiButton;
    }

    public static JButton getWykresWspolczynnikaOdbiciaButton() {
        return wykresWspolczynnikaOdbiciaButton;
    }

    public static JButton getWykresWFSButton() {
        return wykresWFSButton;
    }

    public static JButton getWykresReturnLossButton() {
        return wykresReturnLossButton;
    }

    public static JButton getWykresMismatchLossButton() {
        return wykresMismatchLossButton;
    }
}
