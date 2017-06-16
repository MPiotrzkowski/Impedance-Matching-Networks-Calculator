package Impedance_Matching_Networks_Calculator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel menu programu. W panelu znajdują się pola do
 * wprowadzenia danych wejściowych oraz przyciski.
 * Klasa dziedzicząca po klasie JPanel.
 * Klasa implementująca interfejs ActionListener.
 * @author Mikołaj Piotrzkowski
 */
public class PanelMenu extends JPanel implements ActionListener {

    private static Double rezystancjaZrodla = 0.0;
    private static Double reaktancjaZrodla = 0.0;
    private static Double rezystancjaObciazenia = 0.0;
    private static Double reaktancjaObciazenia = 0.0;
    private static Double czestotliwosc = 0.0;
    private static Double szerokoscPasma = 0.0;
    private static int typUkladuWartosc = 0;
    private static int gornoDolnoPrzepustowyWartosc = 0;
    private static int czestotliwoscJednostkaWartosc = 0;
    private static int szerokoscPasmaJednostkaWartosc = 0;
    private static Double q;

    private JLabel impedancjaZrodlaLabel = new JLabel("Source Impedance:");
    private JLabel rezystancjaZrodlaLabel = new JLabel("RS = ");
    private static JTextField rezystancjaZrodlaField = new JTextField(10);
    private JLabel ohmZrodlaLabel = new JLabel("Ω");
    private JLabel reaktancjaZrodlaLabel = new JLabel("XS = ");
    private static JTextField reaktancjaZrodlaField = new JTextField(10);
    private JLabel jOhmZrodlaLabel = new JLabel("j Ω");

    private JLabel impedancjaObciazeniaLabel = new JLabel("Load Impedance:");
    private JLabel rezystancjaObciazeniaLabel = new JLabel("RL = ");
    private static JTextField rezystancjaObciazeniaField = new JTextField(10);
    private JLabel ohmObciazeniaLabel = new JLabel("Ω");
    private JLabel reaktancjaObciazeniaLabel = new JLabel("XL = ");
    private static JTextField reaktancjaObciazeniaField = new JTextField(10);
    private JLabel jOhmObciazeniaLabel = new JLabel("j Ω");

    private JLabel czestotliwoscLabel = new JLabel("Frequency:");
    private JLabel fLabel = new JLabel("f = ");
    private static JTextField czestotliwoscField = new JTextField(11);
    private static String[] czestotliwoscJednostki = {"Hz", "kHz", "MHz", "GHz"};
    private static JComboBox czestotliwoscJednostka = new JComboBox(czestotliwoscJednostki);
    private JLabel szerokoscPasmaLabel = new JLabel("Bandwidth = ");
    private static JTextField szerokoscPasmaField = new JTextField(6);
    private static JComboBox szerokoscPasmaJednostka = new JComboBox<String>(czestotliwoscJednostki);
    private JLabel ukladLabel = new JLabel("Matching Network:");
    private JLabel typUkladuLabel = new JLabel("Type:");
    private static String[] typyUkladow = {"L", "π", "T"};
    private static JComboBox typUkladu = new JComboBox(typyUkladow);
    private static String[] gornoDolnoPrzepustowyString = {"Low-pass", "High-pass"};
    private static JComboBox gornoDolnoPrzepustowy = new JComboBox(gornoDolnoPrzepustowyString);
    private JLabel qLabel = new JLabel("Q = ");
    private static JTextField qField = new JTextField(5);

    private JButton obliczButton = new JButton("Calculate");
    private JButton wyczyscButton = new JButton("Clear Input Values");


    /**
     * Główny konstruktor klasy. Dodanie i ustawienie wszystkich
     * elementów oraz dodanie obsługi zdarzeń.
     */
    public PanelMenu(){
        setLayout(null);

        this.add(impedancjaZrodlaLabel);
        impedancjaZrodlaLabel.setBounds((((Parametry.getSzerokoscOkna()/2)-impedancjaZrodlaLabel.getPreferredSize().width)/2), 5, impedancjaZrodlaLabel.getPreferredSize().width, impedancjaZrodlaLabel.getPreferredSize().height);
        this.add(rezystancjaZrodlaLabel);
        rezystancjaZrodlaLabel.setBounds(5, 35, rezystancjaZrodlaLabel.getPreferredSize().width, rezystancjaZrodlaLabel.getPreferredSize().height);
        this.add(rezystancjaZrodlaField);
        rezystancjaZrodlaField.setBounds(35, 35, rezystancjaZrodlaField.getPreferredSize().width, rezystancjaZrodlaField.getPreferredSize().height);
        this.add(ohmZrodlaLabel);
        ohmZrodlaLabel.setBounds(155, 35, ohmZrodlaLabel.getPreferredSize().width, ohmZrodlaLabel.getPreferredSize().height);
        this.add(reaktancjaZrodlaLabel);
        reaktancjaZrodlaLabel.setBounds(5, 65, reaktancjaZrodlaLabel.getPreferredSize().width, reaktancjaZrodlaLabel.getPreferredSize().height);
        this.add(reaktancjaZrodlaField);
        reaktancjaZrodlaField.setBounds(35, 65, reaktancjaZrodlaField.getPreferredSize().width, reaktancjaZrodlaField.getPreferredSize().height);
        this.add(jOhmZrodlaLabel);
        jOhmZrodlaLabel.setBounds(155, 65, jOhmZrodlaLabel.getPreferredSize().width, jOhmZrodlaLabel.getPreferredSize().height);

        this.add(impedancjaObciazeniaLabel);
        impedancjaObciazeniaLabel.setBounds((((Parametry.getSzerokoscOkna()/2)-impedancjaObciazeniaLabel.getPreferredSize().width)/2), 95, impedancjaObciazeniaLabel.getPreferredSize().width, impedancjaObciazeniaLabel.getPreferredSize().height);
        this.add(rezystancjaObciazeniaLabel);
        rezystancjaObciazeniaLabel.setBounds(5, 125, rezystancjaObciazeniaLabel.getPreferredSize().width, rezystancjaObciazeniaLabel.getPreferredSize().height);
        this.add(rezystancjaObciazeniaField);
        rezystancjaObciazeniaField.setBounds(35, 125, rezystancjaObciazeniaField.getPreferredSize().width, rezystancjaObciazeniaField.getPreferredSize().height);
        this.add(ohmObciazeniaLabel);
        ohmObciazeniaLabel.setBounds(155, 125, ohmObciazeniaLabel.getPreferredSize().width, ohmObciazeniaLabel.getPreferredSize().height);
        this.add(reaktancjaObciazeniaLabel);
        reaktancjaObciazeniaLabel.setBounds(5, 155, reaktancjaObciazeniaLabel.getPreferredSize().width, reaktancjaObciazeniaLabel.getPreferredSize().height);
        this.add(reaktancjaObciazeniaField);
        reaktancjaObciazeniaField.setBounds(35, 155, reaktancjaObciazeniaField.getPreferredSize().width, reaktancjaObciazeniaField.getPreferredSize().height);
        this.add(jOhmObciazeniaLabel);
        jOhmObciazeniaLabel.setBounds(155, 155, jOhmObciazeniaLabel.getPreferredSize().width, jOhmObciazeniaLabel.getPreferredSize().height);

        this.add(czestotliwoscLabel);
        czestotliwoscLabel.setBounds((((Parametry.getSzerokoscOkna()/2)-czestotliwoscLabel.getPreferredSize().width)/2), 185, czestotliwoscLabel.getPreferredSize().width, czestotliwoscLabel.getPreferredSize().height);
        this.add(fLabel);
        fLabel.setBounds(5, 215, fLabel.getPreferredSize().width, fLabel.getPreferredSize().height);
        this.add(czestotliwoscField);
        czestotliwoscField.setBounds(30, 215, czestotliwoscField.getPreferredSize().width, czestotliwoscField.getPreferredSize().height);
        this.add(czestotliwoscJednostka);
        czestotliwoscJednostka.setBounds(160, 212, czestotliwoscJednostka.getPreferredSize().width, czestotliwoscJednostka.getPreferredSize().height);
        this.add(this.szerokoscPasmaLabel);
        this.szerokoscPasmaLabel.setBounds(5, 245, this.szerokoscPasmaLabel.getPreferredSize().width, this.szerokoscPasmaLabel.getPreferredSize().height);
        this.add(szerokoscPasmaField);
        this.szerokoscPasmaField.setBounds(85, 245, this.szerokoscPasmaField.getPreferredSize().width, this.szerokoscPasmaField.getPreferredSize().height);
        this.add(szerokoscPasmaJednostka);
        this.szerokoscPasmaJednostka.setBounds(160, 242, this.szerokoscPasmaJednostka.getPreferredSize().width, this.szerokoscPasmaJednostka.getPreferredSize().height);
        this.add(ukladLabel);
        ukladLabel.setBounds((((Parametry.getSzerokoscOkna()/2)-ukladLabel.getPreferredSize().width)/2), 275, ukladLabel.getPreferredSize().width, ukladLabel.getPreferredSize().height);
        this.add(typUkladuLabel);
        typUkladuLabel.setBounds(5, 305, typUkladuLabel.getPreferredSize().width, typUkladuLabel.getPreferredSize().height);
        this.add(typUkladu);
        typUkladu.setBounds(45, 302, typUkladu.getPreferredSize().width, typUkladu.getPreferredSize().height);
        typUkladu.addActionListener(this);
        this.add(gornoDolnoPrzepustowy);
        gornoDolnoPrzepustowy.setBounds(100, 302, gornoDolnoPrzepustowy.getPreferredSize().width, gornoDolnoPrzepustowy.getPreferredSize().height);
        this.add(qLabel);
        qLabel.setBounds(200, 305, qLabel.getPreferredSize().width, qLabel.getPreferredSize().height);
        this.add(qField);
        qField.setBounds(225, 305, qField.getPreferredSize().width, qField.getPreferredSize().height);
        qField.setText("0");
        qField.setEnabled(false);

        this.add(wyczyscButton);
        wyczyscButton.setBounds(35, 350, wyczyscButton.getPreferredSize().width, wyczyscButton.getPreferredSize().height);
        wyczyscButton.addActionListener(this);

        this.add(obliczButton);
        obliczButton.setBounds(195, 350, obliczButton.getPreferredSize().width, obliczButton.getPreferredSize().height);
        obliczButton.addActionListener(this);

        czestotliwoscField.setText(String.valueOf(Parametry.getF()));
        szerokoscPasmaField.setText(String.valueOf(Parametry.getSzerokoscPasma()));
        rezystancjaZrodlaField.setText(String.valueOf(Parametry.getRezystancjaZrodla()));
        reaktancjaZrodlaField.setText(String.valueOf(Parametry.getReaktancjaZrodlaSzeregowo()));
        rezystancjaObciazeniaField.setText(String.valueOf(Parametry.getRezystancjaObciazenia()));
        reaktancjaObciazeniaField.setText(String.valueOf(Parametry.getReaktancjaObciazeniaSzeregowo()));
        qField.setText(String.valueOf(Parametry.getQ()));
    }

    /**
     * Nadpisz metodę odpowiedzialną za reakcję
     * programu na przyciśnięcie przycisku.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        UkladL.zerujWyniki();
        UkladPi.zerujWyniki();
        UkladT.zerujWyniki();
        if (zrodlo == typUkladu){
            Parametry.setWybranyUklad(typUkladu.getSelectedIndex());
            if (Parametry.getWybranyUklad() == 0){
                qField.setEnabled(false);
            }
            else {
                qField.setEnabled(true);
            }
        }
        if (zrodlo == wyczyscButton){
            Parametry.zerujParametryWejsciowe();
            Parametry.setPoprawnoscDanych(true);
            Parametry.setPoprawnaRezystancjaZrodla(true);
            Parametry.setPoprawnaReaktancjaZrodla(true);
            Parametry.setPoprawnaRezystancjaObciazenia(true);
            Parametry.setPoprawnaReaktancjaObciazenia(true);
            Parametry.setPoprawnaCzestotliwosc(true);
            Parametry.setPoprawnaSzerokoscPasma(true);
            Parametry.setPoprawneQ(true);
            czestotliwoscField.setText(String.valueOf(Parametry.getF()));
            szerokoscPasmaField.setText(String.valueOf(Parametry.getSzerokoscPasma()));
            rezystancjaZrodlaField.setText(String.valueOf(Parametry.getRezystancjaZrodla()));
            reaktancjaZrodlaField.setText(String.valueOf(Parametry.getReaktancjaZrodlaSzeregowo()));
            rezystancjaObciazeniaField.setText(String.valueOf(Parametry.getRezystancjaObciazenia()));
            reaktancjaObciazeniaField.setText(String.valueOf(Parametry.getReaktancjaObciazeniaSzeregowo()));
            qField.setText(String.valueOf(Parametry.getQ()));
            PanelUklad.getDostosujWynikiButton().setVisible(false);
            PanelUklad.getWykresImpedancjiButton().setVisible(false);
            PanelUklad.getWykresWspolczynnikaOdbiciaButton().setVisible(false);
            PanelUklad.getWykresWFSButton().setVisible(false);
            PanelUklad.getWykresReturnLossButton().setVisible(false);
            PanelUklad.getWykresMismatchLossButton().setVisible(false);
            OknoProgram.getPanelUklady().repaint();
        }
        if (zrodlo == obliczButton){
            Parametry.setPoprawnoscDanych(true);
            Parametry.setPoprawnaRezystancjaZrodla(true);
            Parametry.setPoprawnaReaktancjaZrodla(true);
            Parametry.setPoprawnaRezystancjaObciazenia(true);
            Parametry.setPoprawnaReaktancjaObciazenia(true);
            Parametry.setPoprawnaCzestotliwosc(true);
            Parametry.setPoprawnaSzerokoscPasma(true);
            Parametry.setPoprawneQ(true);

            try {
                czestotliwosc = Double.valueOf(czestotliwoscField.getText().replace(',', '.'));
            } catch (NumberFormatException arg){
                Parametry.setPoprawnaCzestotliwosc(false);
            }
            try {
                rezystancjaZrodla = Double.valueOf(rezystancjaZrodlaField.getText().replace(',', '.'));
            } catch (NumberFormatException arg){
                Parametry.setPoprawnaRezystancjaZrodla(false);
            }
            try {
                reaktancjaZrodla = Double.valueOf(reaktancjaZrodlaField.getText().replace(',', '.'));
            } catch (NumberFormatException arg){
                Parametry.setPoprawnaReaktancjaZrodla(false);
            }
            try {
                rezystancjaObciazenia = Double.valueOf(rezystancjaObciazeniaField.getText().replace(',', '.'));
            } catch (NumberFormatException arg){
                Parametry.setPoprawnaRezystancjaObciazenia(false);
            }
            try {
                reaktancjaObciazenia = Double.valueOf(reaktancjaObciazeniaField.getText().replace(',', '.'));
            } catch (NumberFormatException arg){
                Parametry.setPoprawnaReaktancjaObciazenia(false);
            }
            try {
                szerokoscPasma = Double.valueOf(szerokoscPasmaField.getText().replace(',', '.'));
            } catch (NumberFormatException arg){
                Parametry.setPoprawnaSzerokoscPasma(false);
            }
            try {
                q = Double.valueOf(qField.getText().replace(',', '.'));
            } catch (NumberFormatException arg){
                Parametry.setPoprawneQ(false);
            }
            if (!(Parametry.isPoprawnaCzestotliwosc() && Parametry.isPoprawnaSzerokoscPasma() && Parametry.isPoprawnaRezystancjaZrodla() && Parametry.isPoprawnaReaktancjaZrodla() && Parametry.isPoprawnaRezystancjaObciazenia() && Parametry.isPoprawnaReaktancjaObciazenia() && Parametry.isPoprawneQ())){
                Parametry.setPoprawnoscDanych(false);
            }
            czestotliwoscJednostkaWartosc = czestotliwoscJednostka.getSelectedIndex();
            szerokoscPasmaJednostkaWartosc = szerokoscPasmaJednostka.getSelectedIndex();
            typUkladuWartosc = typUkladu.getSelectedIndex();
            gornoDolnoPrzepustowyWartosc = gornoDolnoPrzepustowy.getSelectedIndex();

            Parametry.ustawParametryWejsciowe(czestotliwosc, rezystancjaZrodla, reaktancjaZrodla, rezystancjaObciazenia, reaktancjaObciazenia, czestotliwoscJednostkaWartosc,  szerokoscPasma, szerokoscPasmaJednostkaWartosc, typUkladuWartosc, gornoDolnoPrzepustowyWartosc, q);

            switch (Parametry.getWybranyUklad()){
                case 0:
                    if (Parametry.getDolnoGornoPrzepustowy() == 0){
                        UkladL.ukladLDolnoprzepustowy();
                        qField.setText(String.valueOf(Parametry.round(UkladL.getQ(),2)));
                    }
                    if (Parametry.getDolnoGornoPrzepustowy() == 1){
                        UkladL.ukladLGornoprzepustowy();
                        qField.setText(String.valueOf(Parametry.round(UkladL.getQ(),2)));
                    }
                    break;
                case 1:
                    if (Parametry.getDolnoGornoPrzepustowy() == 0){
                        UkladPi.ukladPiDolnoprzepustowy();
                    }
                    if (Parametry.getDolnoGornoPrzepustowy() == 1){
                        UkladPi.ukladPiGornoprzepustowy();
                    }
                    break;
                case 2:
                    if (Parametry.getDolnoGornoPrzepustowy() == 0){
                        UkladT.ukladTDolnoprzepustowy();
                    }
                    if (Parametry.getDolnoGornoPrzepustowy() == 1){
                        UkladT.ukladTGornoprzepustowy();
                    }
                    break;
            }
            if (Parametry.isPoprawnoscDanych() && (Parametry.getRezystancjaZrodla() != Parametry.getRezystancjaObciazenia())){
                PanelUklad.getDostosujWynikiButton().setVisible(true);
                PanelUklad.getWykresImpedancjiButton().setVisible(true);
                PanelUklad.getWykresImpedancjiButton().setVisible(true);
                PanelUklad.getWykresWspolczynnikaOdbiciaButton().setVisible(true);
                PanelUklad.getWykresWFSButton().setVisible(true);
                PanelUklad.getWykresReturnLossButton().setVisible(true);
                PanelUklad.getWykresMismatchLossButton().setVisible(true);
                PanelUklad.ustawDostosujButton();
            }
            else{
                PanelUklad.getDostosujWynikiButton().setVisible(false);
                PanelUklad.getWykresImpedancjiButton().setVisible(false);
                PanelUklad.getWykresImpedancjiButton().setVisible(false);
                PanelUklad.getWykresWspolczynnikaOdbiciaButton().setVisible(false);
                PanelUklad.getWykresWFSButton().setVisible(false);
                PanelUklad.getWykresReturnLossButton().setVisible(false);
                PanelUklad.getWykresMismatchLossButton().setVisible(false);
            }

            OknoProgram.getPanelUklady().repaint();
        }
    }
}
