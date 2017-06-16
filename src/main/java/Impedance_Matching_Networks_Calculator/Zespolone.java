package Impedance_Matching_Networks_Calculator;

/**
 * Klasa wykonująca operacje na liczbach zespolonych.
 * @author Mikołaj Piotrzkowski
 */
public class Zespolone {

    private double re;
    private double im;

    /**
     * Konstruktor klasy.
     * @param re część rzeczywista
     * @param im część urojona
     */
    public Zespolone (double re, double im){
        this.re = re;
        this.im = im;
    }

    /**
     * Konstruktor klasy.
     * @param re część rzeczywista
     */
    public Zespolone (double re){
        this.re = re;
        im = 0;
    }

    /**
     * Konstruktor klasy.
     */
    public Zespolone (){
        re = 0;
        im = 0;
    }

    /**
     * Metoda obliczająca sumę dwóch liczb zespolonych.
     * @param a liczba zespolona pierwsza
     * @param b liczba zespolona druga
     */
    public static Zespolone suma(Zespolone a, Zespolone b){
        double re = a.re + b.re;
        double im = a.im + b.im;
        return new Zespolone(re, im);
    }

    /**
     * Metoda obliczająca różnicę dwóch liczb zespolonych.
     * @param a liczba zespolona pierwsza
     * @param b liczba zespolona druga
     */
    public static Zespolone roznica(Zespolone a, Zespolone b){
        double re = a.re - b.re;
        double im = a.im - b.im;
        return new Zespolone(re, im);
    }

    /**
     * Metoda obliczająca iloczyn dwóch liczb zespolonych.
     * @param a liczba zespolona pierwsza
     * @param b liczba zespolona druga
     */
    public static Zespolone iloczyn(Zespolone a, Zespolone b){
        double re = a.re * b.re - a.im * b.im;
        double im = a.re * b.im + a.im * b.re;
        return new Zespolone(re, im);
    }

    /**
     * Metoda obliczająca iloraz dwóch liczb zespolonych.
     * @param a liczba zespolona pierwsza
     * @param b liczba zespolona druga
     */
    public static Zespolone iloraz(Zespolone a, Zespolone b){
        return iloczyn(a, odwrotnosc(b));
    }

    /**
     * Metoda zwracająca odwrotność liczby zespolonej.
     * @param a liczba zespolona
     */
    public static Zespolone odwrotnosc(Zespolone a){
        double x = a.re*a.re + a.im*a.im;
        return new Zespolone(a.re / x, -a.im / x);
    }

    /**
     * Metoda obliczająca moduł liczby zespolonej.
     * @param a liczba zespolona
     */
    public static double modul(Zespolone a){
        return (Math.sqrt(Math.pow(a.re(), 2)+ Math.pow(a.im(), 2)));
    }

    /**
     * Metoda zwracająca sprzężoną liczbę zespoloną.
     * @param a liczba zespolona
     */
    public static Zespolone sprzezenie(Zespolone a){
        return new Zespolone(a.re(), -a.im());
    }

    /**
     * Metoda zwracająca część rzeczywistą liczby zespolonej.
     */
    public double re(){
        return re;
    }

    /**
     * Metoda zwracająca część urojoną liczby zespolonej.
     */
    public double im(){
        return im;
    }

    @Override
    public String toString() {
        return "(" + re + ", " + im + ")";
    }
}
