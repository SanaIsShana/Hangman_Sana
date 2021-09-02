import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Hangman_Sana {
        public static Scanner scan = new Scanner(System.in);
        //Skaffar olika datatyper eller Arraylist i klassen.
        public static int chans = 10, antalRatt = 0, antalFel = 0;
        static String ord, bokstav;
        public static char a = '0';

        //Jag använde några ArrayList för att kontrollera gissningen.
        public static ArrayList<String> felList = new ArrayList<>();
        public static ArrayList<String> rattList = new ArrayList<>();
        public static ArrayList<String> gissadeBokstav = new ArrayList<>();
        public static ArrayList<String> ordList = new ArrayList<>();
        public static ArrayList<String> spel = new ArrayList<>();

        //Metod för att läsa in det hemliga ordet.
        public static void hemligOrd() {
            System.out.println("Mata in ditt hemligt ord (mata bara in små bokstäver) : ");
            ord = scan.nextLine();
            felSokningOrd();
            for (int i = 0; i < ord.length(); i++) {
                String b = Character.toString(ord.charAt(i));
                ordList.add(b);
                gissadeBokstav.add(b);
                System.out.println(gissadeBokstav);
            }
        }


        //metoden kontrollerar ordet om det är rätt eller inte.
        public static void felSokningOrd() {
            for (int i = 0; i < ord.length(); i++) {
                if (!Character.isLetter(ord.charAt(i)) || ord.length() <= 1 || Character.isUpperCase(ord.charAt(i))) {
                    System.out.println("Det fick fel!");
                    hemligOrd();
                }
            }
        }


        public static void gommaOrdet() {
            for (int i = 0; i < 90; i++) {
                System.out.println("\b");
            }
        }

        // metod för att beräkna hur många fel gissningar som spelaren har gjort.
        public static void gissaFel() {
            //Söker bokstaven i ordet, om bokstaven inte finns i ordet
            //ökar integer antalFel och lägger bokstaven till Arraylisten "felList".
            if (!ord.contains(bokstav) && !felList.contains(bokstav)) {
                felList.add(bokstav);
                chans--;
                antalFel++;
                System.out.println("Bokstäverna du har gissat fel är " + felList);
                System.out.println("Du har gissat fel " + antalFel + " bokstäver.");
                System.out.println("Bokstäverna du har gissat rätt är " + rattList);
                System.out.println("Du har gissat rätt " + antalRatt + " bokstäver.");
                if (antalFel == 10) {
                    System.out.println("Tyvärr! Du har ingen chans kvar!");
                    System.exit(0);
                }
            }
        }

        //metod för att beräkna hur många ratt gissningar som spelaren har gjort.
        public static void gissaRatt() {
            //Söker bokstaven i ordet, om bokstaven finns i ordet
            //ökar integer antalRatt och lägger bokstaven till Arraylisten "rattList".

            if (gissadeBokstav.contains(bokstav)) {
                rattList.add(bokstav);
                antalRatt++;
                gissadeBokstav.remove(bokstav);
                System.out.println("Bokstäverna du har gissat fel är " + felList);
                System.out.println("Du har gissat fel " + antalFel + " bokstäver.");
                System.out.println("Bokstäverna du har gissat rätt är " + rattList);
                System.out.println("Du har gissat rätt " + antalRatt + " bokstäver.");
            }
        }

        //Metod för att skriva utt gissningen av ordet.
        public static void line () {
            for (int i = 0; i < ord.length(); i++) {
                spel.add("_");
            }
        }



        //Den här metoden skriver ut bokstäverna som gissade rätt av spelaren.
        public static void print () {

            //Om spelaren gissade rätt, kommer det att visas på den rätta platsen.
            for (int i = 0; i < ord.length(); i++) {
                if (ord.charAt(i) == bokstav.charAt(0) && !Objects.equals(spel.get(i), ordList.get(i))) {
                    spel.set(i,bokstav);
                    System.out.println(spel);
                    break;
                }
            }
        }

        //Metod för att kontrollera inmatningen, Om det är nummer, tecken eller stor bokstäver,
        //Skrivs programmet ut ett meddelande för spelaren.
        public static boolean felSokningBoktav ( boolean stor){
            if (Character.isUpperCase(bokstav.charAt(0))) {
                stor = false;
            }
            if (!Character.isLetter(bokstav.charAt(0))) {
                stor = false;
            }
            return stor;
        }


        public static void game () {
            gommaOrdet();
            System.out.println("Det hemliga ordet har " + ord.length() + " bokstäver. ");
            while (ord.length() != 0) {
                System.out.println("Mata in en bokstav: ");
                bokstav = scan.nextLine();

                gissaFel();
                gissaRatt();
                print();
                if (felList.contains(bokstav)) {
                    System.out.println("Du har redan gissat bokstaven! Gissa igen!");
                    continue;
                }
                else if (rattList.contains(bokstav) && !gissadeBokstav.contains(bokstav)){
                    System.out.println("Du har redan gissat bokstaven! Gissa igen!");
                    continue;
                }
                //Om spelaren fuskar eller matar in mer än en bokstav, stor bokstav eller nummer istället for bokstäver,
                // visar programmet ett meddelande.
                if (bokstav.length() > 1 || !felSokningBoktav(true)) {
                    System.out.println("Det gick fel!\nFörsök igen!");
                    continue;
                }

                if (antalRatt == ord.length()) {
                    System.out.println("Grattis! Du har gissat ordet!");
                    System.exit(0);}
                //Skriver ut hur många chans finns det i spelet.
                System.out.println("\nDu har " + chans + " chanser.");
            }
        }

        public static void main (String[]args){
            System.out.println("Välkommen till spelet 'Hänga gubbe'!");
            //Anropa metoderna i main metoden.
            //Vissa metoder anropades inte här för att jag har anropat dem i andra metoderna.
            hemligOrd();
            felSokningOrd();
            line();
            gommaOrdet();
            game();

        }
    }




