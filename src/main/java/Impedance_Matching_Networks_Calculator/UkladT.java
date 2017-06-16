package Impedance_Matching_Networks_Calculator;

/**
 * Klasa obliczająca parametry układu T.
 * @author Mikołaj Piotrzkowski
 */
public class UkladT {

    private static double l = 0;
    private static double c = 0;
    private static double ls = 0;
    private static double cs = 0;
    private static double ll = 0;
    private static double cl = 0;
    private static double q = 0;
    private static double q2 = 0;
    private static double wirtualnaRezystancja = 0;
    private static double xp1 = 0;
    private static double xp2 = 0;
    private static double xs1 = 0;
    private static double xs2 = 0;

    /**
     * Metoda obliczająca parametry układu dolnoprzepustowego T.
     */
    public static void ukladTDolnoprzepustowy(){
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
        if (Parametry.getQ() <=0){
            Parametry.setPoprawneQ(false);
            Parametry.setPoprawnoscDanych(false);
        }
        if (!Parametry.isPoprawnoscDanych()){
            return;
        }
        q = Parametry.getQ();
        if (Parametry.getRezystancjaZrodla() > Parametry.getRezystancjaObciazenia()){
            if (Math.sqrt((Parametry.getRezystancjaZrodla()/Parametry.getRezystancjaObciazenia()) - 1) > q){
                Parametry.setPoprawnoscDanych(false);
                return;
            }
            wirtualnaRezystancja = Parametry.getRezystancjaObciazenia()*(Math.pow(q, 2) + 1);

            xs2 = q*Parametry.getRezystancjaObciazenia();
            xp2 = wirtualnaRezystancja/ q;

            q2 = Math.sqrt((wirtualnaRezystancja/Parametry.getRezystancjaZrodla())-1);

            xp1 = wirtualnaRezystancja/q2;
            xs1 = q2*Parametry.getRezystancjaZrodla();

            c = Parametry.kapacytancjaNaFarady(Parametry.impedancjaZastepczaRownoleglego(new Zespolone(0, -xp1), new Zespolone(0, -xp2)).im(), Parametry.getF());
            ls = Parametry.induktancjaNaHenry((xs1-Parametry.getReaktancjaZrodlaSzeregowo()), Parametry.getF());
            ll = Parametry.induktancjaNaHenry((xs2-Parametry.getReaktancjaObciazeniaSzeregowo()), Parametry.getF());
        }
        if (Parametry.getRezystancjaZrodla() < Parametry.getRezystancjaObciazenia()){
            if (Math.sqrt((Parametry.getRezystancjaObciazenia()/Parametry.getRezystancjaZrodla()) - 1) > q){
                Parametry.setPoprawnoscDanych(false);
                return;
            }
            wirtualnaRezystancja = Parametry.getRezystancjaZrodla()*(Math.pow(q, 2) + 1);

            xs1 = q*Parametry.getRezystancjaZrodla();
            xp1 = wirtualnaRezystancja/ q;

            q2 = Math.sqrt((wirtualnaRezystancja/Parametry.getRezystancjaObciazenia())-1);

            xp2 = wirtualnaRezystancja/q2;
            xs2 = q2*Parametry.getRezystancjaObciazenia();

            c = Parametry.kapacytancjaNaFarady(Parametry.impedancjaZastepczaRownoleglego(new Zespolone(0, -xp1), new Zespolone(0, -xp2)).im(), Parametry.getF());
            ls = Parametry.induktancjaNaHenry((xs1-Parametry.getReaktancjaZrodlaSzeregowo()), Parametry.getF());
            ll = Parametry.induktancjaNaHenry((xs2-Parametry.getReaktancjaObciazeniaSzeregowo()), Parametry.getF());
        }
    }

    /**
     * Metoda obliczająca parametry układu górnoprzepustowego T.
     */
    public static void ukladTGornoprzepustowy(){
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
        if (Parametry.getQ() <=0){
            Parametry.setPoprawneQ(false);
            Parametry.setPoprawnoscDanych(false);
        }
        if (!Parametry.isPoprawnoscDanych()){
            return;
        }
        q = Parametry.getQ();
        if (Parametry.getRezystancjaZrodla() > Parametry.getRezystancjaObciazenia()){
            if (Math.sqrt((Parametry.getRezystancjaZrodla()/Parametry.getRezystancjaObciazenia()) - 1) > q){
                Parametry.setPoprawnoscDanych(false);
                return;
            }
            wirtualnaRezystancja = Parametry.getRezystancjaObciazenia()*(Math.pow(q, 2) + 1);

            xs2 = q*Parametry.getRezystancjaObciazenia();
            xp2 = wirtualnaRezystancja/ q;

            q2 = Math.sqrt((wirtualnaRezystancja/Parametry.getRezystancjaZrodla())-1);

            xp1 = wirtualnaRezystancja/q2;
            xs1 = q2*Parametry.getRezystancjaZrodla();

            l = Parametry.induktancjaNaHenry(Parametry.impedancjaZastepczaRownoleglego(new Zespolone(0, xp1), new Zespolone(0, xp2)).im(), Parametry.getF());
            cs = Parametry.kapacytancjaNaFarady(-(xs1+Parametry.getReaktancjaZrodlaSzeregowo()), Parametry.getF());
            cl = Parametry.kapacytancjaNaFarady(-(xs2+Parametry.getReaktancjaObciazeniaSzeregowo()), Parametry.getF());
        }
        if (Parametry.getRezystancjaZrodla() < Parametry.getRezystancjaObciazenia()){
            if (Math.sqrt((Parametry.getRezystancjaObciazenia()/Parametry.getRezystancjaZrodla()) - 1) > q){
                Parametry.setPoprawnoscDanych(false);
                return;
            }
            wirtualnaRezystancja = Parametry.getRezystancjaZrodla()*(Math.pow(q, 2) + 1);

            xs1 = q*Parametry.getRezystancjaZrodla();
            xp1 = wirtualnaRezystancja/ q;

            q2 = Math.sqrt((wirtualnaRezystancja/Parametry.getRezystancjaObciazenia())-1);

            xp2 = wirtualnaRezystancja/q2;
            xs2 = q2*Parametry.getRezystancjaObciazenia();

            l = Parametry.induktancjaNaHenry(Parametry.impedancjaZastepczaRownoleglego(new Zespolone(0, xp1), new Zespolone(0, xp2)).im(), Parametry.getF());
            cs = Parametry.kapacytancjaNaFarady(-(xs1+Parametry.getReaktancjaZrodlaSzeregowo()), Parametry.getF());
            cl = Parametry.kapacytancjaNaFarady(-(xs2+Parametry.getReaktancjaObciazeniaSzeregowo()), Parametry.getF());
        }
    }

    /**
     * Metoda zerująca wyniki.
     */
    public static void zerujWyniki(){
        l = 0;
        c = 0;
        ls = 0;
        cs = 0;
        ll = 0;
        cl = 0;
        q = 0;
        q2 = 0;
        wirtualnaRezystancja = 0;
        xp1 = 0;
        xp2 = 0;
        xs1 = 0;
        xs2 = 0;
    }

    /**
     * Metoda obliczająca impedancję wejsciową układu dolnoprzepustowego na
     * podstawie impedancji obciążenia oraz parametrów układu dopasowującego.
     * @param rezystancjaObciazenia rezystancja obciążenia
     * @param reaktancjaObciazenia reaktancja obciążenia
     * @param c pojemnośc kondensatora układu dopasowującego
     * @param ls indukcyjność cewki bliżej źródła układu dopasowującego
     * @param ll indukcyjność cewki bliżej obciążenia układu dopasowującego
     * @param f częstotliwość
     */
    public static Zespolone obliczImpedancjeWejsciowaDolnoprzepustowy(double rezystancjaObciazenia, double reaktancjaObciazenia, double c, double ls, double ll, double f){
        Zespolone z = new Zespolone(0, 0);
        Zespolone lll = new Zespolone(0 , Parametry.henryNainduktancje(ll, f));
        Zespolone lsl = new Zespolone(0, Parametry.henryNainduktancje(ls, f));
        Zespolone cc = new Zespolone(0, Parametry.faradyNaKapacytancje(c, f));
        Zespolone z1 = Zespolone.suma(new Zespolone(rezystancjaObciazenia, reaktancjaObciazenia), lll);
        Zespolone zasdasd = Parametry.impedancjaZastepczaRownoleglego(z1, cc);
        return z = Zespolone.suma(zasdasd, lsl);
    }

    /**
     * Metoda obliczająca impedancję wejsciową układu dolnoprzepustowego na
     * podstawie impedancji obciążenia oraz parametrów układu dopasowującego.
     * @param rezystancjaObciazenia rezystancja obciążenia
     * @param reaktancjaObciazenia reaktancja obciążenia
     * @param l indukcyjność cewki układu dopasowującego
     * @param cs pojemność kondensatora bliżej źródła układu dopasowującego
     * @param cl pojemność kondensatora bliżej obciążenia układu dopasowującego
     * @param f częstotliwość
     */
    public static Zespolone obliczImpedancjeWejsciowaGornoprzepustowy(double rezystancjaObciazenia, double reaktancjaObciazenia, double l, double cs, double cl, double f){
        Zespolone z = new Zespolone(0, 0);
        Zespolone ll = new Zespolone(0 , Parametry.henryNainduktancje(l, f));
        Zespolone clc = new Zespolone(0, Parametry.faradyNaKapacytancje(cl, f));
        Zespolone csc = new Zespolone(0, Parametry.faradyNaKapacytancje(cs, f));
        Zespolone z1 = Zespolone.suma(new Zespolone(rezystancjaObciazenia, reaktancjaObciazenia), clc);
        Zespolone zasdasd = Parametry.impedancjaZastepczaRownoleglego(z1, ll);
        return z = Zespolone.suma(zasdasd, csc);
    }

    public static double getL() {
        return l;
    }

    public static double getC() {
        return c;
    }

    public static double getLs() {
        return ls;
    }

    public static double getCs() {
        return cs;
    }

    public static double getLl() {
        return ll;
    }

    public static double getCl() {
        return cl;
    }

    public static void setL(double l) {
        UkladT.l = l;
    }

    public static void setC(double c) {
        UkladT.c = c;
    }

    public static void setLs(double ls) {
        UkladT.ls = ls;
    }

    public static void setCs(double cs) {
        UkladT.cs = cs;
    }

    public static void setLl(double ll) {
        UkladT.ll = ll;
    }

    public static void setCl(double cl) {
        UkladT.cl = cl;
    }
}
