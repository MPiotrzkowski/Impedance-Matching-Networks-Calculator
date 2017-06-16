package Impedance_Matching_Networks_Calculator;

/**
 * Klasa obliczająca parametry układu L.
 * @author Mikołaj Piotrzkowski
 */
public class UkladL {

    private static double l = 0;
    private static double c = 0;
    private static double q = 0;

    /**
     * Metoda obliczająca parametry układu dolnoprzepustowego L.
     */
    public static void ukladLDolnoprzepustowy(){
        if (Parametry.getF() <= 0){
            Parametry.setPoprawnaCzestotliwosc(false);
            Parametry.setPoprawnoscDanych(false);
        }
        if (Parametry.getSzerokoscPasma() <= 0){
            Parametry.setPoprawnaSzerokoscPasma(false);
            Parametry.setPoprawnoscDanych(false);
        }
        if (Parametry.getRezystancjaZrodla() < 0){
            Parametry.setPoprawnaRezystancjaZrodla(false);
            Parametry.setPoprawnoscDanych(false);
        }
        if (Parametry.getRezystancjaObciazenia() < 0){
            Parametry.setPoprawnaRezystancjaObciazenia(false);
            Parametry.setPoprawnoscDanych(false);
        }
        if (!Parametry.isPoprawnoscDanych()){
            return;
        }
        if (Parametry.getRezystancjaZrodla() > Parametry.getRezystancjaObciazenia()){
            Zespolone impedancjaZrodlaSzeregowo = new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo());
            Zespolone impedancjaZrodlaRownolegle = Parametry.impedancjaSzeregowaNaRownolegla(impedancjaZrodlaSzeregowo);

            q = Math.sqrt((impedancjaZrodlaRownolegle.re()/Parametry.getRezystancjaObciazenia())-1);

            double reaktancjaSzeregowo = q * Parametry.getRezystancjaObciazenia() - Parametry.getReaktancjaObciazeniaSzeregowo();
            l = Parametry.induktancjaNaHenry(reaktancjaSzeregowo, Parametry.getF());

            double reaktancjaRownolegle = -(impedancjaZrodlaRownolegle.re() / q);
            if (Parametry.getReaktancjaZrodlaSzeregowo() == 0){
                c = Parametry.kapacytancjaNaFarady(reaktancjaRownolegle, Parametry.getF());
            }
            else {
                double xp1 = (reaktancjaRownolegle*impedancjaZrodlaRownolegle.im())/(impedancjaZrodlaRownolegle.im()-reaktancjaRownolegle);
                c = Parametry.kapacytancjaNaFarady(xp1, Parametry.getF());
            }
        }
        if (Parametry.getRezystancjaZrodla() < Parametry.getRezystancjaObciazenia()) {
            Zespolone impedancjaObciazeniaSzeregowo = new Zespolone(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo());
            Zespolone impedancjaObciazeniaRownolegle = Parametry.impedancjaSzeregowaNaRownolegla(impedancjaObciazeniaSzeregowo);

            q = Math.sqrt((impedancjaObciazeniaRownolegle.re()/Parametry.getRezystancjaZrodla())-1);

            double reaktancjaSzeregowo = q * Parametry.getRezystancjaZrodla() - Parametry.getReaktancjaZrodlaSzeregowo();
            l = Parametry.induktancjaNaHenry(reaktancjaSzeregowo, Parametry.getF());

            double reaktancjaRownolegle = -(impedancjaObciazeniaRownolegle.re() / q);
            if (Parametry.getReaktancjaObciazeniaSzeregowo() == 0){
                c = Parametry.kapacytancjaNaFarady(reaktancjaRownolegle, Parametry.getF());
            }
            else {
                double xp1 = (reaktancjaRownolegle*impedancjaObciazeniaRownolegle.im())/(impedancjaObciazeniaRownolegle.im()-reaktancjaRownolegle);
                c = Parametry.kapacytancjaNaFarady(xp1, Parametry.getF());
            }
        }
    }

    /**
     * Metoda obliczająca parametry układu górnoprzepustowego L.
     */
    public static void ukladLGornoprzepustowy(){
        if (Parametry.getF() <= 0){
            Parametry.setPoprawnaCzestotliwosc(false);
            Parametry.setPoprawnoscDanych(false);
        }
        if (Parametry.getSzerokoscPasma() <= 0){
            Parametry.setPoprawnaSzerokoscPasma(false);
            Parametry.setPoprawnoscDanych(false);
        }
        if (Parametry.getRezystancjaZrodla() < 0){
            Parametry.setPoprawnaRezystancjaZrodla(false);
            Parametry.setPoprawnoscDanych(false);
        }
        if (Parametry.getRezystancjaObciazenia() < 0){
            Parametry.setPoprawnaRezystancjaObciazenia(false);
            Parametry.setPoprawnoscDanych(false);
        }
        if (!Parametry.isPoprawnoscDanych()){
            return;
        }
        if (Parametry.getRezystancjaZrodla() > Parametry.getRezystancjaObciazenia()){
            Zespolone impedancjaZrodlaSzeregowo = new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo());
            Zespolone impedancjaZrodlaRownolegle = Parametry.impedancjaSzeregowaNaRownolegla(impedancjaZrodlaSzeregowo);

            q = Math.sqrt((impedancjaZrodlaRownolegle.re()/Parametry.getRezystancjaObciazenia())-1);

            double reaktancjaSzeregowo = -q * Parametry.getRezystancjaObciazenia() - Parametry.getReaktancjaObciazeniaSzeregowo();
            c = Parametry.kapacytancjaNaFarady(reaktancjaSzeregowo, Parametry.getF());

            double reaktancjaRownolegle = impedancjaZrodlaRownolegle.re() / q;
            if (Parametry.getReaktancjaZrodlaSzeregowo() == 0){
                l = Parametry.induktancjaNaHenry(reaktancjaRownolegle, Parametry.getF());
            }
            else {
                double xp1 = (reaktancjaRownolegle*impedancjaZrodlaRownolegle.im())/(impedancjaZrodlaRownolegle.im()-reaktancjaRownolegle);
                l = Parametry.induktancjaNaHenry(xp1, Parametry.getF());
            }
        }
        if (Parametry.getRezystancjaZrodla() < Parametry.getRezystancjaObciazenia()) {
            Zespolone impedancjaObciazeniaSzeregowo = new Zespolone(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo());
            Zespolone impedancjaObciazeniaRownolegle = Parametry.impedancjaSzeregowaNaRownolegla(impedancjaObciazeniaSzeregowo);

            q = Math.sqrt((impedancjaObciazeniaRownolegle.re()/Parametry.getRezystancjaZrodla())-1);

            double reaktancjaSzeregowo = -q * Parametry.getRezystancjaZrodla() - Parametry.getReaktancjaZrodlaSzeregowo();
            c = Parametry.kapacytancjaNaFarady(reaktancjaSzeregowo, Parametry.getF());

            double reaktancjaRownolegle = impedancjaObciazeniaRownolegle.re() / q;
            if (Parametry.getReaktancjaObciazeniaSzeregowo() == 0){
                l = Parametry.induktancjaNaHenry(reaktancjaRownolegle, Parametry.getF());
            }
            else {
                double xp1 = (reaktancjaRownolegle*impedancjaObciazeniaRownolegle.im())/(impedancjaObciazeniaRownolegle.im()-reaktancjaRownolegle);
                l = Parametry.induktancjaNaHenry(xp1, Parametry.getF());
            }
        }
    }

    /**
     * Metoda zerująca wyniki.
     */
    public static void zerujWyniki(){
        l = 0;
        c = 0;
        q = 0;
    }

    /**
     * Metoda obliczająca impedancję wejsciową układu dolnoprzepustowego na
     * podstawie impedancji obciążenia oraz parametrów układu dopasowującego.
     * @param rezystancjaObciazenia rezystancja obciążenia
     * @param reaktancjaObciazenia reaktancja obciążenia
     * @param l indukcyjność cewki układu dopasowującego
     * @param c pojemność kondensatora układu dopasowującego
     * @param f częstotliwość
     */
    public static Zespolone obliczImpedancjeWejsciowaDolnoprzepustowy(double rezystancjaObciazenia, double reaktancjaObciazenia, double l, double c, double f){
        Zespolone z = new Zespolone(0, 0);
        if (Parametry.getRezystancjaZrodla() > Parametry.getRezystancjaObciazenia()){
            Zespolone ll = new Zespolone(0 , Parametry.henryNainduktancje(l, f));
            Zespolone cc = new Zespolone(0, Parametry.faradyNaKapacytancje(c, f));
            Zespolone z1 = Zespolone.suma(new Zespolone(rezystancjaObciazenia, reaktancjaObciazenia), ll);
            z = Parametry.impedancjaZastepczaRownoleglego(z1, cc);
        }
        if (Parametry.getRezystancjaZrodla() < Parametry.getRezystancjaObciazenia()){
            Zespolone ll = new Zespolone(0 , Parametry.henryNainduktancje(l, f));
            Zespolone cc = new Zespolone(0, Parametry.faradyNaKapacytancje(c, f));
            Zespolone z1 = Parametry.impedancjaZastepczaRownoleglego(new Zespolone(rezystancjaObciazenia, reaktancjaObciazenia), cc);
            z = Zespolone.suma(z1, ll);
        }
        return z;
    }

    /**
     * Metoda obliczająca impedancję wejsciową układu górnoprzepustowego na
     * podstawie impedancji obciążenia oraz parametrów układu dopasowującego.
     * @param rezystancjaObciazenia rezystancja obciążenia
     * @param reaktancjaObciazenia reaktancja obciążenia
     * @param l indukcyjność cewki układu dopasowującego
     * @param c pojemność kondensatora układu dopasowującego
     * @param f częstotliwość
     */
    public static Zespolone obliczImpedancjeWejsciowaGornoprzepustowy(double rezystancjaObciazenia, double reaktancjaObciazenia, double l, double c, double f){
        Zespolone z = new Zespolone(0, 0);
        if (Parametry.getRezystancjaZrodla() > Parametry.getRezystancjaObciazenia()){
            Zespolone ll = new Zespolone(0 , Parametry.henryNainduktancje(l, f));
            Zespolone cc = new Zespolone(0, Parametry.faradyNaKapacytancje(c, f));
            Zespolone z1 = Zespolone.suma(new Zespolone(rezystancjaObciazenia, reaktancjaObciazenia), cc);
            z = Parametry.impedancjaZastepczaRownoleglego(z1, ll);
        }
        if (Parametry.getRezystancjaZrodla() < Parametry.getRezystancjaObciazenia()){
            Zespolone ll = new Zespolone(0 , Parametry.henryNainduktancje(l, f));
            Zespolone cc = new Zespolone(0, Parametry.faradyNaKapacytancje(c, f));
            Zespolone z1 = Parametry.impedancjaZastepczaRownoleglego(new Zespolone(rezystancjaObciazenia, reaktancjaObciazenia), ll);
            z = Zespolone.suma(z1, cc);
        }
        return z;
    }

    public static double getL() {
        return l;
    }

    public static double getC() {
        return c;
    }

    public static double getQ() {
        return q;
    }

    public static void setL(double l) {
        UkladL.l = l;
    }

    public static void setC(double c) {
        UkladL.c = c;
    }
}