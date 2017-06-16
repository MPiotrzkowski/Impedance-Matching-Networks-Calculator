package Impedance_Matching_Networks_Calculator;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 * Kontener parametrów.
 * Klasa przechowuje wszystkie parametry programu oraz
 * pozwala na dostęp do nich i ich modyfikację.
 * Klasa zawiera metody pomocnicze używane przez inne klasy.
 * @author Mikołaj Piotrzkowski
 */
public class Parametry {


    private static final int SZEROKOSC_OKNA = 640;
    private static final int WYSOKOSC_OKNA = 428;
    private static int wspolrzednaXOkna = (Toolkit.getDefaultToolkit().getScreenSize().width-SZEROKOSC_OKNA)/2;
    private static int wspolrzednaYOkna = (Toolkit.getDefaultToolkit().getScreenSize().height-WYSOKOSC_OKNA)/2;

    private static Image ikona;
    private static Image ukladLA;
    private static Image ukladLB;
    private static Image ukladLC;
    private static Image ukladLD;
    private static Image ukladPiLP;
    private static Image ukladPiHP;
    private static Image ukladTLP;
    private static Image ukladTHP;
    private static Image tlo;

    private static Font wynikiCzcionka = new Font("Dialog", Font.BOLD, 12);
    private static Font komunikatCzcionka = new Font("Dialog", Font.BOLD, 12);
    private static Font podpisCzcionka = new Font("Verdana", Font.PLAIN, 9);

    private static double f = 0;
    private static int mnoznikCzestotliwosci = 1;
    private static double szerokoscPasma = 0;
    private static double rezystancjaZrodla = 0;
    private static double reaktancjaZrodlaSzeregowo = 0;
    private static double reaktancjaZrodlaRownolegle = 0;
    private static double rezystancjaObciazenia= 0;
    private static double reaktancjaObciazeniaSzeregowo = 0;
    private static double reaktancjaObciazeniaRownolegle = 0;
    private static int wybranyUklad = 0;
    private static int dolnoGornoPrzepustowy = 0;
    private static double q;
    private static boolean poprawnoscDanych = true;
    private static boolean poprawnaCzestotliwosc = true;
    private static boolean poprawnaSzerokoscPasma = true;
    private static boolean poprawnaRezystancjaZrodla = true;
    private static boolean poprawnaReaktancjaZrodla = true;
    private static boolean poprawnaRezystancjaObciazenia = true;
    private static boolean poprawnaReaktancjaObciazenia = true;
    private static boolean poprawneQ = true;

    /**
     * Metoda ładowania obrazków z plików graficznych.
     */
    public static void wczytajObrazy() {
        ikona = new ImageIcon("images/ikona.png").getImage();
        ukladLA = new ImageIcon("images/LA.png").getImage();
        ukladLB = new ImageIcon("images/LB.png").getImage();
        ukladLC = new ImageIcon("images/LC.png").getImage();
        ukladLD = new ImageIcon("images/LD.png").getImage();
        ukladPiLP = new ImageIcon("images/PiLP.png").getImage();
        ukladPiHP = new ImageIcon("images/PiHP.png").getImage();
        ukladTLP = new ImageIcon("images/TLP.png").getImage();
        ukladTHP = new ImageIcon("images/THP.png").getImage();
        tlo = new ImageIcon("images/tlo.png").getImage();
    }

    /**
     * Metoda licząca impedancję zastępczą dwóch równolegle połączonych impedancji.
     * @param z1 impedancja pierwsza
     * @param z2 impedancja druga
     */
    public static Zespolone impedancjaZastepczaRownoleglego(Zespolone z1, Zespolone z2){
        return Zespolone.iloraz(Zespolone.iloczyn(z1, z2), Zespolone.suma(z1, z2));
    }

    /**
     * Metoda zamieniająca impedancję z postaci szeregowej
     * na równolegle połączoną rezystancję i reaktancję.
     * @param z impedancja zastępcza
     */
    public static Zespolone impedancjaSzeregowaNaRownolegla(Zespolone z){
        double re = (Math.pow(z.im(), 2)/z.re())+z.re();
        double im = 0;
        if (z.im() != 0){
            im = (Math.pow(z.re(), 2)/z.im())+z.im();
        }
        return new Zespolone (re, im);
    }

    /**
     * Metoda licząca pojemność kondensatora na podstawie reaktancji i częstotliwości.
     * @param xc kapacytancja
     * @param f częstotliwość
     */
    public static double kapacytancjaNaFarady(double xc, double f){
        if (xc < 0){
            return (1/(Math.PI*2*f*(-xc)));
        }
        else{
            return 0;
        }
    }

    /**
     * Metoda licząca reaktancję na podstawie pojemności kondensatora i częstotliwości.
     * @param c pojemność kondensatora
     * @param f częstotliwość
     */
    public static double faradyNaKapacytancje(double c, double f){
        if (c > 0){
            return -(1/(Math.PI*2*f*c));
        }
        else{
            return 0;
        }
    }

    /**
     * Metoda licząca indukcyjność cewki na podstawie reaktancji i częstotliwości.
     * @param xl induktancja
     * @param f częstotliwość
     */
    public static double induktancjaNaHenry(double xl, double f){
        return (xl/(Math.PI*2*f));
    }

    /**
     * Metoda licząca reaktancję na podstawie indukcyjności cewki i częstotliwości.
     * @param l indukcyjność cewki
     * @param f częstotliwość
     */
    public static double henryNainduktancje(double l, double f){
        return (l*Math.PI*2*f);
    }

    /**
     * Metoda zamieniająca pojemność na ciąg znaków zawierający liczbę wraz z jednostką.
     * @param farady pojemność kondensatora
     */
    public static String faradyJednostka(double farady){
        int rzadWielkosci = 0;
        double ilosc = farady;
        String wynik = "";
        while (ilosc < 1) {
            ilosc = ilosc*1000;
            rzadWielkosci++;
        }
        switch (rzadWielkosci){
            case 0:
                wynik = (String.valueOf(round(ilosc, 3)) + " F");
                break;
            case 1:
                wynik = (String.valueOf(round(ilosc, 3)) + " mF");
                break;
            case 2:
                wynik = (String.valueOf(round(ilosc, 3)) + " μF");
                break;
            case 3:
                wynik = (String.valueOf(round(ilosc, 3)) + " nF");
                break;
            case 4:
                wynik = (String.valueOf(round(ilosc, 3)) + " pF");
                break;
            case 5:
                wynik = (String.valueOf(round(ilosc, 3)) + " fF");
                break;
            case 6:
                wynik = (String.valueOf(round(ilosc, 3)) + " aF");
                break;
            case 7:
                wynik = (String.valueOf(round(ilosc, 3)) + " zF");
                break;
            case 8:
                wynik = (String.valueOf(round(ilosc, 3)) + " yF");
                break;
            default:
                wynik = "0";
        }
        return wynik;
    }

    /**
     * Metoda zamieniająca indukcyjność na ciąg znaków zawierający liczbę wraz z jednostką.
     * @param henry indukcyjność cewki
     */
    public static String henryJednostka(double henry){
        int rzadWielkosci = 0;
        double ilosc = henry;
        String wynik = "";
        while (ilosc < 1) {
            ilosc = ilosc*1000;
            rzadWielkosci++;
        }
        switch (rzadWielkosci){
            case 0:
                wynik = (String.valueOf(round(ilosc, 3)) + " H");
                break;
            case 1:
                wynik = (String.valueOf(round(ilosc, 3)) + " mH");
                break;
            case 2:
                wynik = (String.valueOf(round(ilosc, 3)) + " μH");
                break;
            case 3:
                wynik = (String.valueOf(round(ilosc, 3)) + " nH");
                break;
            case 4:
                wynik = (String.valueOf(round(ilosc, 3)) + " pH");
                break;
            case 5:
                wynik = (String.valueOf(round(ilosc, 3)) + " fH");
                break;
            case 6:
                wynik = (String.valueOf(round(ilosc, 3)) + " aH");
                break;
            case 7:
                wynik = (String.valueOf(round(ilosc, 3)) + " zH");
                break;
            case 8:
                wynik = (String.valueOf(round(ilosc, 3)) + " yH");
                break;
            default:
                wynik = "0";
        }
        return wynik;
    }

    /**
     * Metoda zwracająca wartość w zakresie <1 ; 1000) wraz z rzędem wielkości.
     * @param wartosc wartość bez wiadomego rzędu wielkości
     */
    public static Zespolone jednostkaRzadWielkosci(double wartosc){
        int rzadWielkosci = 0;
        double ilosc = wartosc;
        while (ilosc < 1) {
            ilosc = ilosc*1000;
            rzadWielkosci++;
        }
        return new Zespolone(round(ilosc, 3), rzadWielkosci);
    }

    /**
     * Metoda zamieniająca wartość wraz z podanym rzędem wielkości na wartość bez danego rzędu wielkości.
     * @param wartosc dana wartość
     * @param rzadWielkosci rząd wielkości
     */
    public static double jednostkaRazyRzadWielkosci (double wartosc, int rzadWielkosci){
        switch (rzadWielkosci){
            case 0:
                return wartosc / 1.0;
            case 1:
                return wartosc / 1000.0;
            case 2:
                return wartosc / 1000000.0;
            case 3:
                return wartosc / 1000000000.0;
            case 4:
                return wartosc / 1000000000000.0;
            case 5:
                return wartosc / 1000000000000000.0;
            case 6:
                return wartosc / 1000000000000000000.0;
            case 7:
                return wartosc / 1000000000000000000000.0;
            case 8:
                return wartosc / 1000000000000000000000000.0;
            default:
                return 0.0;
        }
    }

    /**
     * Metoda obliczająca współczynnik odbicia.
     * @param impedancjaZrodla impedancja źródła
     * @param impedancjaObciazenia impedancja obciążenia
     */
    public static Zespolone wspolczynnikOdbicia(Zespolone impedancjaZrodla, Zespolone impedancjaObciazenia){
        return Zespolone.iloraz(Zespolone.roznica(impedancjaObciazenia, Zespolone.sprzezenie(impedancjaZrodla)), Zespolone.suma(impedancjaObciazenia, impedancjaZrodla));
    }

    /**
     * Metoda obliczająca współczynnik fali stojącej.
     * @param modulWspolczynnikaOdbicia moduł współczynnika odbicia
     */
    public static double wfs(double modulWspolczynnikaOdbicia){
        return (1+modulWspolczynnikaOdbicia)/(1-modulWspolczynnikaOdbicia);
    }

    /**
     * Metoda obliczająca straty odbiciowe.
     * @param modulWspolczynnikaOdbicia moduł współczynnika odbicia
     */
    public static double stratyOdbiciowe(double modulWspolczynnikaOdbicia){
        return -20*Math.log10(modulWspolczynnikaOdbicia);
    }

    /**
     * Metoda obliczająca straty niedopasowania.
     * @param modulWspolczynnikaOdbicia moduł współczynnika odbicia
     */
    public static double stratyNiedopasowania(double modulWspolczynnikaOdbicia){
        return -10*Math.log10(1-Math.pow(modulWspolczynnikaOdbicia, 2));
    }

    /**
     * Metoda zerująca parametry wejściowe.
     */
    public static void zerujParametryWejsciowe() {
        Parametry.setF(0);
        Parametry.setSzerokoscPasma(0);
        Parametry.setMnoznikCzestotliwosci(1);
        Parametry.setRezystancjaZrodla(0);
        Parametry.setReaktancjaZrodlaSzeregowo(0);
        Parametry.setReaktancjaZrodlaRownolegle(0);
        Parametry.setRezystancjaObciazenia(0);
        Parametry.setReaktancjaObciazeniaSzeregowo(0);
        Parametry.setReaktancjaObciazeniaRownolegle(0);
        Parametry.setQ(0);
    }

    /**
     * Metoda ustawiająca parametry wejściowe.
     */
    public static void ustawParametryWejsciowe(double czestotliwosc, double rezystancjaZrodla, double reaktancjaZrodla, double rezystancjaObciazenia, double reaktancjaObciazenia, int czestotliwoscJednostka, double szerokoscPasma, int pasmoJednostka, int wybranyUklad, int dolnoGorno, double q) {
        switch (czestotliwoscJednostka){
            case 0:
                Parametry.setMnoznikCzestotliwosci(1);
                break;
            case 1:
                Parametry.setMnoznikCzestotliwosci(1000);
                break;
            case 2:
                Parametry.setMnoznikCzestotliwosci(1000000);
                break;
            case 3:
                Parametry.setMnoznikCzestotliwosci(1000000000);
                break;
        }
        Parametry.setF(mnoznikCzestotliwosci*czestotliwosc);

        switch (pasmoJednostka){
            case 0:
                Parametry.setMnoznikCzestotliwosci(1);
                break;
            case 1:
                Parametry.setMnoznikCzestotliwosci(1000);
                break;
            case 2:
                Parametry.setMnoznikCzestotliwosci(1000000);
                break;
            case 3:
                Parametry.setMnoznikCzestotliwosci(1000000000);
                break;
        }
        Parametry.setSzerokoscPasma(mnoznikCzestotliwosci*szerokoscPasma);

        Parametry.setRezystancjaZrodla(rezystancjaZrodla);
        Parametry.setReaktancjaZrodlaSzeregowo(reaktancjaZrodla);
        Parametry.setReaktancjaZrodlaRownolegle(0);
        Parametry.setRezystancjaObciazenia(rezystancjaObciazenia);
        Parametry.setReaktancjaObciazeniaSzeregowo(reaktancjaObciazenia);
        Parametry.setReaktancjaObciazeniaRownolegle(0);
        Parametry.setWybranyUklad(wybranyUklad);
        Parametry.setDolnoGornoPrzepustowy(dolnoGorno);
        Parametry.setQ(q);
    }

    /**
     * Metoda zaokrąglająca liczbę do danej liczby cyfr po przecinku.
     * @param d niezaorkąglona liczba
     * @param ic liczba cyfr po przecinku
     */
    static public double round(double d, int ic) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setMaximumFractionDigits(ic);
        nf.setMinimumFractionDigits(ic);
        return Double.parseDouble((nf.format(d)).replaceAll(",", ".").replaceAll(" ", "") );
    }

    public static int getSzerokoscOkna() {
        return SZEROKOSC_OKNA;
    }

    public static int getWysokoscOkna() {
        return WYSOKOSC_OKNA;
    }

    public static int getWspolrzednaXOkna() {
        return wspolrzednaXOkna;
    }

    public static int getWspolrzednaYOkna() {
        return wspolrzednaYOkna;
    }

    public static Image getUkladLA() {
        return ukladLA;
    }

    public static Image getUkladLB() {
        return ukladLB;
    }

    public static Image getUkladLC() {
        return ukladLC;
    }

    public static Image getUkladLD() {
        return ukladLD;
    }

    public static Image getUkladPiLP() {
        return ukladPiLP;
    }

    public static Image getUkladPiHP() {
        return ukladPiHP;
    }

    public static Image getUkladTLP() {
        return ukladTLP;
    }

    public static Image getUkladTHP() {
        return ukladTHP;
    }

    public static Image getIkona() {
        return ikona;
    }

    public static double getF() {
        return f;
    }

    public static double getSzerokoscPasma() {
        return szerokoscPasma;
    }

    public static double getRezystancjaZrodla() {
        return rezystancjaZrodla;
    }

    public static double getReaktancjaZrodlaSzeregowo() {
        return reaktancjaZrodlaSzeregowo;
    }

    public static double getRezystancjaObciazenia() {
        return rezystancjaObciazenia;
    }

    public static double getReaktancjaObciazeniaSzeregowo() {
        return reaktancjaObciazeniaSzeregowo;
    }

    public static int getWybranyUklad() {
        return wybranyUklad;
    }

    public static int getDolnoGornoPrzepustowy() {
        return dolnoGornoPrzepustowy;
    }

    public static double getQ() {
        return q;
    }

    public static boolean isPoprawnoscDanych() {
        return poprawnoscDanych;
    }

    public static boolean isPoprawnaCzestotliwosc() {
        return poprawnaCzestotliwosc;
    }

    public static boolean isPoprawnaSzerokoscPasma() {
        return poprawnaSzerokoscPasma;
    }

    public static boolean isPoprawnaRezystancjaZrodla() {
        return poprawnaRezystancjaZrodla;
    }

    public static boolean isPoprawnaReaktancjaZrodla() {
        return poprawnaReaktancjaZrodla;
    }

    public static boolean isPoprawnaRezystancjaObciazenia() {
        return poprawnaRezystancjaObciazenia;
    }

    public static boolean isPoprawnaReaktancjaObciazenia() {
        return poprawnaReaktancjaObciazenia;
    }

    public static boolean isPoprawneQ() {
        return poprawneQ;
    }

    public static Image getTlo() {
        return tlo;
    }

    public static Font getWynikiCzcionka() {
        return wynikiCzcionka;
    }

    public static Font getPodpisCzcionka() {
        return podpisCzcionka;
    }

    public static Font getKomunikatCzcionka() {
        return komunikatCzcionka;
    }

    public static void setF(double f) {
        Parametry.f = f;
    }

    public static void setSzerokoscPasma(double szerokoscPasma) {
        Parametry.szerokoscPasma = szerokoscPasma;
    }

    public static void setRezystancjaZrodla(double rezystancjaZrodla) {
        Parametry.rezystancjaZrodla = rezystancjaZrodla;
    }

    public static void setReaktancjaZrodlaSzeregowo(double reaktancjaZrodlaSzeregowo) {
        Parametry.reaktancjaZrodlaSzeregowo = reaktancjaZrodlaSzeregowo;
    }

    public static void setReaktancjaZrodlaRownolegle(double reaktancjaZrodlaRownolegle) {
        Parametry.reaktancjaZrodlaRownolegle = reaktancjaZrodlaRownolegle;
    }

    public static void setRezystancjaObciazenia(double rezystancjaObciazenia) {
        Parametry.rezystancjaObciazenia = rezystancjaObciazenia;
    }

    public static void setReaktancjaObciazeniaSzeregowo(double reaktancjaObciazeniaSzeregowo) {
        Parametry.reaktancjaObciazeniaSzeregowo = reaktancjaObciazeniaSzeregowo;
    }

    public static void setReaktancjaObciazeniaRownolegle(double reaktancjaObciazeniaRownolegle) {
        Parametry.reaktancjaObciazeniaRownolegle = reaktancjaObciazeniaRownolegle;
    }

    public static void setMnoznikCzestotliwosci(int mnoznikCzestotliwosci) {
        Parametry.mnoznikCzestotliwosci = mnoznikCzestotliwosci;
    }

    public static void setWybranyUklad(int wybranyUklad) {
        Parametry.wybranyUklad = wybranyUklad;
    }

    public static void setDolnoGornoPrzepustowy(int dolnoGornoPrzepustowy) {
        Parametry.dolnoGornoPrzepustowy = dolnoGornoPrzepustowy;
    }

    public static void setQ(double q) {
        Parametry.q = q;
    }

    public static void setPoprawnoscDanych(boolean poprawnoscDanych) {
        Parametry.poprawnoscDanych = poprawnoscDanych;
    }

    public static void setPoprawnaCzestotliwosc(boolean poprawnaCzestotliwosc) {
        Parametry.poprawnaCzestotliwosc = poprawnaCzestotliwosc;
    }

    public static void setPoprawnaSzerokoscPasma(boolean poprawnaSzerokoscPasma) {
        Parametry.poprawnaSzerokoscPasma = poprawnaSzerokoscPasma;
    }

    public static void setPoprawnaRezystancjaZrodla(boolean poprawnaRezystancjaZrodla) {
        Parametry.poprawnaRezystancjaZrodla = poprawnaRezystancjaZrodla;
    }

    public static void setPoprawnaReaktancjaZrodla(boolean poprawnaReaktancjaZrodla) {
        Parametry.poprawnaReaktancjaZrodla = poprawnaReaktancjaZrodla;
    }

    public static void setPoprawnaRezystancjaObciazenia(boolean poprawnaRezystancjaObciazenia) {
        Parametry.poprawnaRezystancjaObciazenia = poprawnaRezystancjaObciazenia;
    }

    public static void setPoprawnaReaktancjaObciazenia(boolean poprawnaReaktancjaObciazenia) {
        Parametry.poprawnaReaktancjaObciazenia = poprawnaReaktancjaObciazenia;
    }

    public static void setPoprawneQ(boolean poprawneQ) {
        Parametry.poprawneQ = poprawneQ;
    }
}
