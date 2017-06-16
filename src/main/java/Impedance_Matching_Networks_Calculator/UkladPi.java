package Impedance_Matching_Networks_Calculator;

/**
 * Klasa obliczająca parametry układu Pi.
 * @author Mikołaj Piotrzkowski
 */
public class UkladPi {

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
     * Metoda obliczająca parametry układu dolnoprzepustowego Pi.
     */
    public static void ukladPiDolnoprzepustowy(){
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

        Zespolone impedancjaZrodlaSzeregowo = new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo());
        Zespolone impedancjaObciazeniaSzeregowo = new Zespolone(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo());

        Zespolone impedancjaZrodlaRownolegle = Parametry.impedancjaSzeregowaNaRownolegla(impedancjaZrodlaSzeregowo);
        Zespolone impedancjaObciazeniaRownolegle = Parametry.impedancjaSzeregowaNaRownolegla(impedancjaObciazeniaSzeregowo);

        if (impedancjaZrodlaRownolegle.re() > impedancjaObciazeniaRownolegle.re()){
            if (Math.sqrt((impedancjaZrodlaRownolegle.re()/impedancjaObciazeniaRownolegle.re()) - 1) > q){
                Parametry.setPoprawnoscDanych(false);
                return;
            }

            wirtualnaRezystancja = impedancjaZrodlaRownolegle.re()/(Math.pow(q, 2) + 1);

            xp1 = -(impedancjaZrodlaRownolegle.re()/q);

            xs1 = q * wirtualnaRezystancja;

            q2 = Math.sqrt((impedancjaObciazeniaRownolegle.re()/wirtualnaRezystancja) - 1);

            xp2 = -(impedancjaObciazeniaRownolegle.re()/q2);

            xs2 = q2 * wirtualnaRezystancja;

            l = Parametry.induktancjaNaHenry(xs1+xs2, Parametry.getF());

            if (Parametry.getReaktancjaObciazeniaSzeregowo() == 0){
                cl = Parametry.kapacytancjaNaFarady(xp2, Parametry.getF());
            }
            else {
                double xpPomocnicze1 = (xp2*impedancjaObciazeniaRownolegle.im())/(impedancjaObciazeniaRownolegle.im()-xp2);
                cl = Parametry.kapacytancjaNaFarady(xpPomocnicze1, Parametry.getF());
            }

            if (Parametry.getReaktancjaZrodlaSzeregowo() == 0){
                cs = Parametry.kapacytancjaNaFarady(xp1, Parametry.getF());
            }
            else {
                double xpPomocnicze2 = (xp1*impedancjaZrodlaRownolegle.im())/(impedancjaZrodlaRownolegle.im()-xp1);
                cs = Parametry.kapacytancjaNaFarady(xpPomocnicze2, Parametry.getF());
            }
        }
        if (impedancjaZrodlaRownolegle.re() < impedancjaObciazeniaRownolegle.re()){
            if (Math.sqrt((impedancjaObciazeniaRownolegle.re()/impedancjaZrodlaRownolegle.re()) - 1) > q){
                Parametry.setPoprawnoscDanych(false);
                return;
            }
            wirtualnaRezystancja = impedancjaObciazeniaRownolegle.re()/(Math.pow(q, 2) + 1);


            xp2 = -(impedancjaObciazeniaRownolegle.re()/q);

            xs2 = q * wirtualnaRezystancja;

            q2 = Math.sqrt((impedancjaZrodlaRownolegle.re()/wirtualnaRezystancja) - 1);

            xp1 = -(impedancjaZrodlaRownolegle.re()/q2);

            xs1 = q2 * wirtualnaRezystancja;

            l = Parametry.induktancjaNaHenry(xs1+xs2, Parametry.getF());

            if (Parametry.getReaktancjaObciazeniaSzeregowo() == 0){
                cl = Parametry.kapacytancjaNaFarady(xp2, Parametry.getF());
            }
            else {
                double xpPomocnicze1 = (xp2*impedancjaObciazeniaRownolegle.im())/(impedancjaObciazeniaRownolegle.im()-xp2);
                cl = Parametry.kapacytancjaNaFarady(xpPomocnicze1, Parametry.getF());
            }

            if (Parametry.getReaktancjaZrodlaSzeregowo() == 0){
                cs = Parametry.kapacytancjaNaFarady(xp1, Parametry.getF());
            }
            else {
                double xpPomocnicze2 = (xp1*impedancjaZrodlaRownolegle.im())/(impedancjaZrodlaRownolegle.im()-xp1);
                cs = Parametry.kapacytancjaNaFarady(xpPomocnicze2, Parametry.getF());
            }
        }
    }

    /**
     * Metoda obliczająca parametry układu górnoprzepustowego Pi.
     */
    public static void ukladPiGornoprzepustowy(){
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

        Zespolone impedancjaZrodlaSzeregowo = new Zespolone(Parametry.getRezystancjaZrodla(), Parametry.getReaktancjaZrodlaSzeregowo());
        Zespolone impedancjaObciazeniaSzeregowo = new Zespolone(Parametry.getRezystancjaObciazenia(), Parametry.getReaktancjaObciazeniaSzeregowo());

        Zespolone impedancjaZrodlaRownolegle = Parametry.impedancjaSzeregowaNaRownolegla(impedancjaZrodlaSzeregowo);
        Zespolone impedancjaObciazeniaRownolegle = Parametry.impedancjaSzeregowaNaRownolegla(impedancjaObciazeniaSzeregowo);

        if (impedancjaZrodlaRownolegle.re() > impedancjaObciazeniaRownolegle.re()){
            if (Math.sqrt((impedancjaZrodlaRownolegle.re()/impedancjaObciazeniaRownolegle.re()) - 1) > q){
                Parametry.setPoprawnoscDanych(false);
                return;
            }
            wirtualnaRezystancja = impedancjaZrodlaRownolegle.re() / (Math.pow(q, 2) + 1);

            xp1 = impedancjaZrodlaRownolegle.re() / q;

            xs1 = -(q * wirtualnaRezystancja);

            q2 = Math.sqrt((impedancjaObciazeniaRownolegle.re() / wirtualnaRezystancja) - 1);

            xp2 = impedancjaObciazeniaRownolegle.re() / q2;

            xs2 = -(q2 * wirtualnaRezystancja);

            c = Parametry.kapacytancjaNaFarady(xs1 + xs2, Parametry.getF());

            if (Parametry.getReaktancjaObciazeniaSzeregowo() == 0) {
                ll = Parametry.induktancjaNaHenry(xp2, Parametry.getF());
            } else {
                double xpPomocnicze1 = (xp2 * impedancjaObciazeniaRownolegle.im()) / (impedancjaObciazeniaRownolegle.im() - xp2);
                ll = Parametry.induktancjaNaHenry(xpPomocnicze1, Parametry.getF());
            }

            if (Parametry.getReaktancjaZrodlaSzeregowo() == 0) {
                ls = Parametry.induktancjaNaHenry(xp1, Parametry.getF());
            } else {
                double xpPomocnicze2 = (xp1 * impedancjaZrodlaRownolegle.im()) / (impedancjaZrodlaRownolegle.im() - xp1);
                ls = Parametry.induktancjaNaHenry(xpPomocnicze2, Parametry.getF());
            }
        }
        if (impedancjaZrodlaRownolegle.re() < impedancjaObciazeniaRownolegle.re()) {
            if (Math.sqrt((impedancjaObciazeniaRownolegle.re()/impedancjaZrodlaRownolegle.re()) - 1) > q){
                Parametry.setPoprawnoscDanych(false);
                return;
            }
            wirtualnaRezystancja = impedancjaObciazeniaRownolegle.re() / (Math.pow(q, 2) + 1);

            xp2 = impedancjaObciazeniaRownolegle.re() / q;

            xs2 = -(q * wirtualnaRezystancja);

            q2 = Math.sqrt((impedancjaZrodlaRownolegle.re() / wirtualnaRezystancja) - 1);

            xp1 = impedancjaZrodlaRownolegle.re() / q2;

            xs1 = -(q2 * wirtualnaRezystancja);

            c = Parametry.kapacytancjaNaFarady(xs1 + xs2, Parametry.getF());

            if (Parametry.getReaktancjaObciazeniaSzeregowo() == 0) {
                ll = Parametry.induktancjaNaHenry(xp2, Parametry.getF());
            } else {
                double xpPomocnicze1 = (xp2 * impedancjaObciazeniaRownolegle.im()) / (impedancjaObciazeniaRownolegle.im() - xp2);
                ll = Parametry.induktancjaNaHenry(xpPomocnicze1, Parametry.getF());
            }

            if (Parametry.getReaktancjaZrodlaSzeregowo() == 0) {
                ls = Parametry.induktancjaNaHenry(xp1, Parametry.getF());
            } else {
                double xpPomocnicze2 = (xp1 * impedancjaZrodlaRownolegle.im()) / (impedancjaZrodlaRownolegle.im() - xp1);
                ls = Parametry.induktancjaNaHenry(xpPomocnicze2, Parametry.getF());
            }
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
     * @param l indukcyjność cewki układu dopasowującego
     * @param cs pojemność kondensatora bliżej źródła układu dopasowującego
     * @param cl pojemność kondensatora bliżej obciążenia układu dopasowującego
     * @param f częstotliwość
     */
    public static Zespolone obliczImpedancjeWejsciowaDolnoprzepustowy(double rezystancjaObciazenia, double reaktancjaObciazenia, double l, double cs, double cl, double f){
        Zespolone z = new Zespolone(0, 0);
        Zespolone ll = new Zespolone(0 , Parametry.henryNainduktancje(l, f));
        Zespolone csc = new Zespolone(0, Parametry.faradyNaKapacytancje(cs, f));
        Zespolone clc = new Zespolone(0, Parametry.faradyNaKapacytancje(cl, f));
        Zespolone zasdasd = Parametry.impedancjaZastepczaRownoleglego(new Zespolone(rezystancjaObciazenia, reaktancjaObciazenia), clc);
        Zespolone z1 = Zespolone.suma(zasdasd, ll);
        return z = Parametry.impedancjaZastepczaRownoleglego(z1, csc);
    }

    /**
     * Metoda obliczająca impedancję wejsciową układu dolnoprzepustowego na
     * podstawie impedancji obciążenia oraz parametrów układu dopasowującego.
     * @param rezystancjaObciazenia rezystancja obciążenia
     * @param reaktancjaObciazenia reaktancja obciążenia
     * @param ll indukcyjność cewki bliżej obciążenia układu dopasowującego
     * @param ls indukcyjność cewki bliżej źródła układu dopasowującego
     * @param c pojemność kondensatora układu dopasowującego
     * @param f częstotliwość
     */
    public static Zespolone obliczImpedancjeWejsciowaGornoprzepustowy(double rezystancjaObciazenia, double reaktancjaObciazenia, double ll, double ls, double c, double f){
        Zespolone z = new Zespolone(0, 0);
        Zespolone lll = new Zespolone(0 , Parametry.henryNainduktancje(ll, f));
        Zespolone lsl = new Zespolone(0, Parametry.henryNainduktancje(ls, f));
        Zespolone cc = new Zespolone(0, Parametry.faradyNaKapacytancje(c, f));
        Zespolone zasdasd = Parametry.impedancjaZastepczaRownoleglego(new Zespolone(rezystancjaObciazenia, reaktancjaObciazenia), lll);
        Zespolone z1 = Zespolone.suma(zasdasd, cc);
        return z = Parametry.impedancjaZastepczaRownoleglego(z1, lsl);
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
        UkladPi.l = l;
    }

    public static void setC(double c) {
        UkladPi.c = c;
    }

    public static void setLs(double ls) {
        UkladPi.ls = ls;
    }

    public static void setCs(double cs) {
        UkladPi.cs = cs;
    }

    public static void setLl(double ll) {
        UkladPi.ll = ll;
    }

    public static void setCl(double cl) {
        UkladPi.cl = cl;
    }
}
