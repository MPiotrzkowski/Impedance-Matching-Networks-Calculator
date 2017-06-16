package Impedance_Matching_Networks_Calculator;

import java.awt.Toolkit;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Klasa tworząca i wyświetlająca wykresy.
 * @author Mikołaj Piotrzkowski
 */
public class Wykres {

    /**
     * Metoda tworząca i wyświetlajaca wykres impedancji wejściowej w funkcji częstotliwości.
     */
    public static void impedancjaWejsciowa()
    {
        /**   Seria danych rezystancja.   */
        XYSeries rezystancja= new XYSeries("Rin");
        /**   Seria danych reaktancja.  */
        XYSeries reaktancja= new XYSeries("Xin");

        if (Parametry.getWybranyUklad() == 0){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    rezystancja.add(i, UkladL.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i).re());
                }

                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    reaktancja.add(i, UkladL.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i).im());
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                        rezystancja.add(i, UkladL.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i).re());
                }

                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    reaktancja.add(i, UkladL.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i).im());
                }
            }
        }

        if (Parametry.getWybranyUklad() == 1){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    rezystancja.add(i, UkladPi.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getL(), UkladPi.getCs(), UkladPi.getCl(), i).re());
                }

                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    reaktancja.add(i, UkladPi.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getL(), UkladPi.getCs(), UkladPi.getCl(), i).im());
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    rezystancja.add(i, UkladPi.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getLl(), UkladPi.getLs(), UkladPi.getC(), i).re());
                }

                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    reaktancja.add(i, UkladPi.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getLl(), UkladPi.getLs(), UkladPi.getC(), i).im());
                }
            }
        }

        if (Parametry.getWybranyUklad() == 2){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    rezystancja.add(i, UkladT.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getC(), UkladT.getLs(), UkladT.getLl(), i).re());
                }

                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    reaktancja.add(i, UkladT.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getC(), UkladT.getLs(), UkladT.getLl(), i).im());
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    rezystancja.add(i, UkladT.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getL(), UkladT.getCs(), UkladT.getCl(), i).re());
                }

                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    reaktancja.add(i, UkladT.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getL(), UkladT.getCs(), UkladT.getCl(), i).im());
                }
            }
        }

        /**   Kolekcja zawierająca serie danych.   */
        XYSeriesCollection kolekcja = new XYSeriesCollection();

        kolekcja.addSeries(rezystancja);
        kolekcja.addSeries(reaktancja);

        /**   XYDataset zawierający kolekcję.   */
        XYDataset xyDataset = kolekcja;

        /**   Wykres.   */
        JFreeChart wykres = ChartFactory.createXYLineChart("Zin (f)", "Frequency [Hz]", "Zin [Ω]", xyDataset, PlotOrientation.VERTICAL, true, true, false);

        /**   Okno zawierające wykres.   */
        ChartFrame okno = new ChartFrame("Input Impedance as a Function of Frequency", wykres);
        okno.setIconImage(Parametry.getIkona());
        okno.setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().width-okno.getPreferredSize().getWidth())/2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().height-okno.getPreferredSize().getHeight())/2));
        okno.pack();
        okno.setVisible(true);
    }//koniec impedancjaWejsciowa()


    /**
     * Metoda tworząca i wyświetlajaca wykres modułu współczynnika odbicia w funkcji częstotliwości.
     */
    public static void wspolczynnikOdbicia() {

        /**   Seria danych współczynnik odbicia.   */
        XYSeries wspOdbiciaModul= new XYSeries("|Γ|");


        if (Parametry.getWybranyUklad() == 0){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladL.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i);
                    wspOdbiciaModul.add(i, Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia)));
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladL.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i);
                    wspOdbiciaModul.add(i, Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia)));
                }
            }
        }

        if (Parametry.getWybranyUklad() == 1){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladPi.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getL(), UkladPi.getCs(), UkladPi.getCl(), i);
                    wspOdbiciaModul.add(i, Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia)));
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladPi.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getLl(), UkladPi.getLs(), UkladPi.getC(), i);
                    wspOdbiciaModul.add(i, Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia)));
                }
            }
        }

        if (Parametry.getWybranyUklad() == 2){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladT.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getC(), UkladT.getLs(), UkladT.getLl(), i);
                    wspOdbiciaModul.add(i, Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia)));
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladT.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getL(), UkladT.getCs(), UkladT.getCl(), i);
                    wspOdbiciaModul.add(i, Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia)));
                }
            }
        }

        /**   Kolekcja zawierająca serie danych.   */
        XYSeriesCollection kolekcja = new XYSeriesCollection();

        kolekcja.addSeries(wspOdbiciaModul);

        /**   XYDataset zawierający kolekcję.   */
        XYDataset xyDataset = kolekcja;

        /**   Wykres.   */
        JFreeChart lineGraph = ChartFactory.createXYLineChart("|Γ| (f)", "Frequency [Hz]", "|Γ|", xyDataset, PlotOrientation.VERTICAL, true, true, false);

        /**   Okno zawierające wykres.   */
        ChartFrame okno = new ChartFrame("Magnitude of Reflection Coefficient as a Function of Frequency", lineGraph);
        okno.setIconImage(Parametry.getIkona());
        okno.setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().width-okno.getPreferredSize().getWidth())/2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().height-okno.getPreferredSize().getHeight())/2));
        okno.pack();
        okno.setVisible(true);
    }

    /**
     * Metoda tworząca i wyświetlajaca wykres współczynnika fali stojącej w funkcji częstotliwości.
     */
    public static void wfs() {

        /**   Seria danych współczynnik fali stojącej.   */
        XYSeries wfs= new XYSeries("VSWR");


        if (Parametry.getWybranyUklad() == 0){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladL.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i);
                    wfs.add(i, Parametry.wfs(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladL.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i);
                    wfs.add(i, Parametry.wfs(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                }
            }
        }

        if (Parametry.getWybranyUklad() == 1){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladPi.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getL(), UkladPi.getCs(), UkladPi.getCl(), i);
                    wfs.add(i, Parametry.wfs(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladPi.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getLl(), UkladPi.getLs(), UkladPi.getC(), i);
                    wfs.add(i, Parametry.wfs(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                }
            }
        }

        if (Parametry.getWybranyUklad() == 2){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladT.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getC(), UkladT.getLs(), UkladT.getLl(), i);
                    wfs.add(i, Parametry.wfs(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladT.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getL(), UkladT.getCs(), UkladT.getCl(), i);
                    wfs.add(i, Parametry.wfs(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                }
            }
        }

        /**   Kolekcja zawierająca serie danych.   */
        XYSeriesCollection kolekcja = new XYSeriesCollection();

        kolekcja.addSeries(wfs);

        /**   XYDataset zawierający kolekcję.   */
        XYDataset xyDataset = kolekcja;

        /**   Wykres.   */
        JFreeChart lineGraph = ChartFactory.createXYLineChart("VSWR (f)", "Frequency [Hz]", "VSWR", xyDataset, PlotOrientation.VERTICAL, true, true, false);
        lineGraph.getXYPlot().getRangeAxis().setLowerBound(1.0);

        /**   Okno zawierające wykres.   */
        ChartFrame okno = new ChartFrame("Voltage Standing Wave Ratio as a Function of Frequency", lineGraph);
        okno.setIconImage(Parametry.getIkona());
        okno.setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().width-okno.getPreferredSize().getWidth())/2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().height-okno.getPreferredSize().getHeight())/2));
        okno.pack();
        okno.setVisible(true);
    }


    /**
     * Metoda tworząca i wyświetlajaca wykres strat odbiciowych w funkcji częstotliwości.
     */
    public static void stratyOdbiciowe() {

        /**   Seria danych straty odbiciowe.   */
        XYSeries returnLoss= new XYSeries("Return Loss");

        if (Parametry.getWybranyUklad() == 0){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladL.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i);
                    if (Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))) < 50){
                        returnLoss.add(i, Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                    }
                    else{
                        returnLoss.add(i, 50);
                    }
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladL.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i);
                    if (Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))) < 50){
                        returnLoss.add(i, Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                    }
                    else{
                        returnLoss.add(i, 50);
                    }
                }
            }
        }

        if (Parametry.getWybranyUklad() == 1){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladPi.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getL(), UkladPi.getCs(), UkladPi.getCl(), i);
                    if (Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))) < 50){
                        returnLoss.add(i, Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                    }
                    else{
                        returnLoss.add(i, 50);
                    }
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladPi.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getLl(), UkladPi.getLs(), UkladPi.getC(), i);
                    if (Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))) < 50){
                        returnLoss.add(i, Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                    }
                    else{
                        returnLoss.add(i, 50);
                    }
                }
            }
        }

        if (Parametry.getWybranyUklad() == 2){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladT.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getC(), UkladT.getLs(), UkladT.getLl(), i);
                    if (Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))) < 50){
                        returnLoss.add(i, Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                    }
                    else{
                        returnLoss.add(i, 50);
                    }
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladT.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getL(), UkladT.getCs(), UkladT.getCl(), i);
                    if (Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))) < 50){
                        returnLoss.add(i, Parametry.stratyOdbiciowe(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                    }
                    else{
                        returnLoss.add(i, 50);
                    }
                }
            }
        }

        /**   Kolekcja zawierająca serie danych.   */
        XYSeriesCollection kolekcja = new XYSeriesCollection();

        kolekcja.addSeries(returnLoss);

        /**   XYDataset zawierający kolekcję.   */
        XYDataset xyDataset = kolekcja;

        /**   Wykres.   */
        JFreeChart lineGraph = ChartFactory.createXYLineChart("Return Loss (f)", "Frequency [Hz]", "Return Loss [dB]", xyDataset, PlotOrientation.VERTICAL, true, true, false);

        /**   Okno zawierające wykres.   */
        ChartFrame okno = new ChartFrame("Return Loss as a Function of Frequency", lineGraph);
        okno.setIconImage(Parametry.getIkona());
        okno.setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().width-okno.getPreferredSize().getWidth())/2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().height-okno.getPreferredSize().getHeight())/2));
        okno.pack();
        okno.setVisible(true);
    }


    /**
     * Metoda tworząca i wyświetlajaca wykres strat niedopasowania w funkcji częstotliwości.
     */
    public static void stratyNiedopasowania() {

        /**   Seria danych straty niedopasowania.   */
        XYSeries mismatchLoss= new XYSeries("Mismatch Loss");


        if (Parametry.getWybranyUklad() == 0){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladL.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i);
                    mismatchLoss.add(i, Parametry.stratyNiedopasowania(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladL.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladL.getL(), UkladL.getC(), i);
                    mismatchLoss.add(i, Parametry.stratyNiedopasowania(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                }
            }
        }

        if (Parametry.getWybranyUklad() == 1){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladPi.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getL(), UkladPi.getCs(), UkladPi.getCl(), i);
                    mismatchLoss.add(i, Parametry.stratyNiedopasowania(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladPi.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladPi.getLl(), UkladPi.getLs(), UkladPi.getC(), i);
                    mismatchLoss.add(i, Parametry.stratyNiedopasowania(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                }
            }
        }

        if (Parametry.getWybranyUklad() == 2){
            if (Parametry.getDolnoGornoPrzepustowy() == 0) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladT.obliczImpedancjeWejsciowaDolnoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getC(), UkladT.getLs(), UkladT.getLl(), i);
                    mismatchLoss.add(i, Parametry.stratyNiedopasowania(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                }
            }
            if (Parametry.getDolnoGornoPrzepustowy() == 1) {
                for (double i = Parametry.getF() - (Parametry.getSzerokoscPasma() / 2); i <= Parametry.getF() + (Parametry.getSzerokoscPasma() / 2); i += Parametry.getSzerokoscPasma() / 1000) {
                    Zespolone impedancjaObciazenia = UkladT.obliczImpedancjeWejsciowaGornoprzepustowy(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo(), UkladT.getL(), UkladT.getCs(), UkladT.getCl(), i);
                    mismatchLoss.add(i, Parametry.stratyNiedopasowania(Zespolone.modul(Parametry.wspolczynnikOdbicia(new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo()), impedancjaObciazenia))));
                }
            }
        }

        /**   Kolekcja zawierająca serie danych.   */
        XYSeriesCollection kolekcja = new XYSeriesCollection();
        // Dodanie kolejnych serii do kolekcji:
        kolekcja.addSeries(mismatchLoss);
        /**   XYDataset zawierający kolekcję.   */
        XYDataset xyDataset = kolekcja;
        /**   Wykres.   */
        JFreeChart lineGraph = ChartFactory.createXYLineChart("Mismatch Loss (f)", "Frequency [Hz]", "Mismatch Loss [dB]", xyDataset, PlotOrientation.VERTICAL, true, true, false);

        /**   Okno zawierające wykres.   */
        ChartFrame okno = new ChartFrame("Mismatch Loss as a Function of Frequency", lineGraph);
        okno.setIconImage(Parametry.getIkona());
        okno.setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().width-okno.getPreferredSize().getWidth())/2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().height-okno.getPreferredSize().getHeight())/2));
        okno.pack();
        okno.setVisible(true);
    }
}