package Impedance_Matching_Networks_Calculator;

import java.awt.EventQueue;

/**
 * Program "Impedance Matching Networks Calculator", jako oprogramowanie do
 * wspomagania projektowania układów dopasowujących nadajników krótkofalowych.
 * @author Mikołaj Piotrzkowski
 */
public class App {

    private static OknoProgram oknoProgram;

    /**
     * Metoda uruchamia program. Tworzony jest nowy obiekt klasy OknoProgram.
     * @param args
     */
    public static void main( String[] args ) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                oknoProgram = new OknoProgram();
            }
        });
    }

    public static OknoProgram getOknoProgram() {
        return oknoProgram;
    }
}