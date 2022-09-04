import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean turaBialego = true;
        while (czyZakonczycGre(turaBialego)==false) {
            wyswietl();

            if (turaBialego == true) {
                System.out.println("RUCH GRACZA BIALEGO");
            } else {
                System.out.println("RUCH GRACZA CZARNEGO");
            }

            boolean koniecTury=false;

            while(koniecTury==false){
                int wybranyPionek = wyborPionka(turaBialego);
                int nowaPozycja = -1;
                if(czyJakikolwiekPionekMaBicie(turaBialego)){
                    if(czyPionekMaBicie(wybranyPionek,turaBialego?1:0)){
                        while(czyPionekMaBicie(wybranyPionek,turaBialego?1:0)){
                            nowaPozycja=wyborPozycjiDocelowej(turaBialego,wybranyPionek);
                            wykonajbicie(wybranyPionek,(wybranyPionek+nowaPozycja)/2,nowaPozycja);
                            wybranyPionek=nowaPozycja;
                            if(czyPionekMaBicie(wybranyPionek,turaBialego?1:0)){
                                wyswietl();
                                System.out.println("MOZLIWE KOLEJNE BICIE");
                            }
                        }
                        koniecTury=true;
                    }else{
                        System.out.println("WYBRANY PIONEK NIE MA BICIA");
                    }
                }else{
                    nowaPozycja=wyborPozycjiDocelowej(turaBialego,wybranyPionek);
                    wykonajRuch(turaBialego,wybranyPionek,nowaPozycja);
                    koniecTury=true;
                }
            }
            turaBialego = !turaBialego;
        }
    }
    static long x1_bialy = 0b0000000000101000001101000011101000101101000111101001000101001010L;   //6 bialych
    static long x2_bialy = 0b0000000000101001100101001110101010001101010011101010101101010111L;   //6 bialych

    static long x1_czarny = 0b0000000000100101000100101010100101100100101110100110001100110011L;   //6 czarnych
    static long x2_czarny = 0b0000000000100110101100110111100111000100111010100111100100111110L;   //6 czarnych

    static boolean czyPionekMaBicie(int aktualnaPozycja, int kolorPrzeciwnika) {
        //bicie w lewo do gory
        if (czyNaPoluJestPionekPrzeciwnika(aktualnaPozycja - 9, kolorPrzeciwnika) && czyPoleJestPuste(aktualnaPozycja - 18) && aktualnaPozycja > 18 && czyPoleJestCzarne(aktualnaPozycja - 18)) {
            return true;
        }
        //bicie w prawo do gory
        if (czyNaPoluJestPionekPrzeciwnika(aktualnaPozycja - 7, kolorPrzeciwnika) && czyPoleJestPuste(aktualnaPozycja - 14) && aktualnaPozycja > 14 && czyPoleJestCzarne(aktualnaPozycja - 14)) {
            return true;
        }
        //bicie w prawo w dol
        if (czyNaPoluJestPionekPrzeciwnika(aktualnaPozycja + 9, kolorPrzeciwnika) && czyPoleJestPuste(aktualnaPozycja + 18) && aktualnaPozycja < 46 && czyPoleJestCzarne(aktualnaPozycja + 18)) {
            return true;
        }
        //bicie w dol w lewo
        if (czyNaPoluJestPionekPrzeciwnika(aktualnaPozycja + 7, kolorPrzeciwnika) && czyPoleJestPuste(aktualnaPozycja + 14) && aktualnaPozycja < 47 && czyPoleJestCzarne(aktualnaPozycja + 14)) {
            return true;
        }
        return false;
    }

    static boolean czyNaPoluJestPionekPrzeciwnika(int numerPola, int kolorPrzeciwnika) {
        int maskaPozycji = 0b111111;
        int maskaCzyZyje = 0b100000000;
        for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
            int bit1 = nrPionka * 9;
            if (kolorPrzeciwnika == 1) {
                int pozycjaPionkaCzarnyX1 = (int) (x1_czarny >> bit1) & maskaPozycji;
                if (pozycjaPionkaCzarnyX1 == numerPola) {
                    if (((int) ((x1_czarny >> bit1) & maskaCzyZyje)) > 0) {
                        return true;
                    }
                }
                int pozycjaPionkaCzarnyX2 = (int) (x2_czarny >> bit1) & maskaPozycji;
                if (pozycjaPionkaCzarnyX2 == numerPola) {
                    if (((int) ((x2_czarny >> bit1) & maskaCzyZyje)) > 0) {
                        return true;
                    }
                }
            } else {
                int pozycjaPionkaBialyX1 = (int) (x1_bialy >> bit1) & maskaPozycji;
                if (pozycjaPionkaBialyX1 == numerPola) {
                    if (((int) ((x1_bialy >> bit1) & maskaCzyZyje)) > 0) {
                        return true;
                    }
                }
                int pozycjaPionkaBialyX2 = (int) (x2_bialy >> bit1) & maskaPozycji;
                if (pozycjaPionkaBialyX2 == numerPola) {
                    if (((int) ((x2_bialy >> bit1) & maskaCzyZyje)) > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean czyPoleJestPuste(int numerPola) {

        for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
            int maskaPozycji = 0b111111;
            int maskaCzyZyje = 0b100000000;
            int bit1 = nrPionka * 9;
            int pozycjaPionkaBialyX1 = (int) (x1_bialy >> bit1) & maskaPozycji;
            if (pozycjaPionkaBialyX1 == numerPola) {
                if (((int) ((x1_bialy >> bit1) & maskaCzyZyje)) > 0) {
                    return false;
                }
            }

            int pozycjaPionkaBialyX2 = (int) (x2_bialy >> bit1) & maskaPozycji;
            if (pozycjaPionkaBialyX2 == numerPola) {
                if (((int) ((x2_bialy >> bit1) & maskaCzyZyje)) > 0) {
                    return false;
                }
            }

            int pozycjaPionkaCzarnyyX1 = (int) (x1_czarny >> bit1) & maskaPozycji;
            if (pozycjaPionkaCzarnyyX1 == numerPola) {
                if (((int) ((x1_czarny >> bit1) & maskaCzyZyje)) > 0) {
                    return false;
                }
            }

            int pozycjaPionkaCzarnyX2 = (int) (x2_czarny >> bit1) & maskaPozycji;
            if (pozycjaPionkaCzarnyX2 == numerPola) {
                if (((int) ((x2_czarny >> bit1) & maskaCzyZyje)) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean czyPoleJestCzarne(int numerPola) {
        int kolumna = (numerPola % 8)+1;
        int wiersz = (numerPola / 8)+1;

        if (kolumna % 2 == 0 && wiersz % 2 == 1) {
            return true;
        }
        if (kolumna % 2 == 1 && wiersz % 2 == 0) {
            return true;
        }
        return false;
    }

    static void wykonajbicie(int aktualnaPozycja, int pozycjaZbijanego, int pozycjaPoZbiciu) {

        for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
            int maskaPozycji = 0b111111;
            int bit1 = nrPionka * 9;
            long nowaPozLong = pozycjaPoZbiciu;
            long mask = (0b111111L << bit1);
            long maskaDoZbijania = (0b100000000L <<bit1);
            int pozycjaPionkaBialyX1 = (int) (x1_bialy >> bit1) & maskaPozycji;
            if (pozycjaPionkaBialyX1 == aktualnaPozycja) {
                x1_bialy = ((x1_bialy & (~mask)));
                x1_bialy = (x1_bialy | (nowaPozLong << bit1));
            }

            int pozycjaPionkaBialyX2 = (int) (x2_bialy >> bit1) & maskaPozycji;
            if (pozycjaPionkaBialyX2 == aktualnaPozycja) {
                x2_bialy = ((x2_bialy & (~mask)));
                x2_bialy = (x2_bialy | (nowaPozLong << bit1));
            }

            int pozycjaPionkaCzarnyX1 = (int) (x1_czarny >> bit1) & maskaPozycji;
            if (pozycjaPionkaCzarnyX1 == aktualnaPozycja) {
                x1_czarny = ((x1_czarny & (~mask)));
                x1_czarny = (x1_czarny | (nowaPozLong << bit1));
            }

            int pozycjaPionkaCzarnyX2 = (int) (x2_czarny >> bit1) & maskaPozycji;
            if (pozycjaPionkaCzarnyX2 == aktualnaPozycja) {
                x2_czarny = ((x2_czarny & (~mask)));
                x2_czarny = (x2_czarny | (nowaPozLong << bit1));
            }
            if (pozycjaPionkaBialyX1 == pozycjaZbijanego) {
                x1_bialy = ((x1_bialy & (~maskaDoZbijania)));
            }
            if (pozycjaPionkaBialyX2 == pozycjaZbijanego) {
                x2_bialy = ((x2_bialy & (~maskaDoZbijania)));
            }
            if (pozycjaPionkaCzarnyX1 == pozycjaZbijanego) {
                x1_czarny = ((x1_czarny & (~maskaDoZbijania)));
            }
            if (pozycjaPionkaCzarnyX2 == pozycjaZbijanego) {
                x2_czarny = ((x2_czarny & (~maskaDoZbijania)));
            }
        }
    }

    static int wyborPionka(boolean turaBialego) {
        int x_poz;
        int y_poz;
        Scanner scanner = new Scanner(System.in);
        boolean czyWybranoPoprawny = false;
        int wybranyPionek = -1;
        int maskaPozycji = 0b111111;
        int maskaCzyZyje = 0b100000000;

        while (czyWybranoPoprawny == false) {  //while do wyboru pionka do nowej pozycji
            if (turaBialego == true) {

                System.out.println("Podaj pozycje pionka, ktorym chcesz sie ruszyc");
                System.out.print("X:");
                x_poz = scanner.nextInt();
                System.out.print("Y:");
                y_poz = scanner.nextInt();
                System.out.println();

                wybranyPionek = (x_poz - 1) + (8 * (y_poz - 1));
                for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
                    int bit1 = nrPionka * 9;
                    int pozycjaPionkaBialyX1 = (int) (x1_bialy >> bit1) & maskaPozycji;
                    if (pozycjaPionkaBialyX1 == wybranyPionek) {
                        if (((int) ((x1_bialy >> bit1) & maskaCzyZyje)) > 0) {
                            czyWybranoPoprawny = true;
                        }
                    }
                    int pozycjaPionkaBialyX2 = (int) (x2_bialy >> bit1) & maskaPozycji;
                    if (pozycjaPionkaBialyX2 == wybranyPionek) {
                        if (((int) ((x2_bialy >> bit1) & maskaCzyZyje)) > 0) {
                            czyWybranoPoprawny = true;
                        }
                    }
                }
            } else {
                System.out.println("Podaj pozycje pionka, ktorym chcesz sie ruszyc");
                System.out.print("X:");
                x_poz = scanner.nextInt();
                System.out.print("Y:");
                y_poz = scanner.nextInt();
                System.out.println();

                wybranyPionek = (x_poz - 1) + (8 * (y_poz - 1));
                for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
                    int bit1 = nrPionka * 9;
                    int pozycjaPionkaCzarnyX1 = (int) (x1_czarny >> bit1) & maskaPozycji;
                    if (pozycjaPionkaCzarnyX1 == wybranyPionek) {
                        if (((int) ((x1_czarny >> bit1) & maskaCzyZyje)) > 0) {
                            czyWybranoPoprawny = true;
                        }
                    }
                    int pozycjaPionkaCzarnyX2 = (int) (x2_czarny >> bit1) & maskaPozycji;
                    if (pozycjaPionkaCzarnyX2 == wybranyPionek) {
                        if (((int) ((x2_czarny >> bit1) & maskaCzyZyje)) > 0) {
                            czyWybranoPoprawny = true;
                        }
                    }
                }
            }
            if (czyWybranoPoprawny == false) {
                System.out.println("NIEPOPRAWNY PIONEK");
            }
        }
        return wybranyPionek;
    }

    static boolean czyJakikolwiekPionekMaBicie(boolean turaBialego) {
        int maskaPozycji = 0b111111;
        int maskaCzyZyje = 0b100000000;

        if (turaBialego == true) {
            for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
                int bit1 = nrPionka * 9;
                int pozycjaPionkaBialyX1 = (int) (x1_bialy >> bit1) & maskaPozycji;
                if (((int) ((x1_bialy >> bit1) & maskaCzyZyje)) > 0) {
                    if (czyPionekMaBicie(pozycjaPionkaBialyX1, 1)) {
                        return true;
                    }
                }
                int pozycjaPionkaBialyX2 = (int) (x2_bialy >> bit1) & maskaPozycji;
                if (((int) ((x2_bialy >> bit1) & maskaCzyZyje)) > 0) {
                    if (czyPionekMaBicie(pozycjaPionkaBialyX2, 1)) {
                        return true;
                    }
                }
            }
        } else {
            for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
                int bit1 = nrPionka * 9;
                int pozycjaPionkaCzarnyX1 = (int) (x1_czarny >> bit1) & maskaPozycji;
                if (((int) ((x1_czarny >> bit1) & maskaCzyZyje)) > 0) {
                    if (czyPionekMaBicie(pozycjaPionkaCzarnyX1, 0)) {
                        return true;
                    }
                }

                int pozycjaPionkaCzarnyX2 = (int) (x2_czarny >> bit1) & maskaPozycji;
                if (((int) ((x2_czarny >> bit1) & maskaCzyZyje)) > 0) {
                    if (czyPionekMaBicie(pozycjaPionkaCzarnyX2, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static int wyborPozycjiDocelowej(boolean turaBialego,int wybranyPionek){

        Scanner scanner=new Scanner(System.in);
        boolean czyPoprawnaNowaPozycja = false;
        int nowaPozycja = -1;
        while (czyPoprawnaNowaPozycja == false) {  //while do wyboru nowej pozycji
            int x;
            int y;
            System.out.println("Podaj nowa pozycje pionka");
            System.out.print("X:");
            x = scanner.nextInt();
            System.out.print("Y:");
            y = scanner.nextInt();
            System.out.println();

            nowaPozycja = (x - 1) + (8 * (y - 1));

            if (turaBialego == true) {
                if(czyPionekMaBicie(wybranyPionek,1)){
                    if ((wybranyPionek -18) == nowaPozycja || (wybranyPionek -14) == nowaPozycja || (wybranyPionek +14) == nowaPozycja || (wybranyPionek +18) == nowaPozycja) {
                        czyPoprawnaNowaPozycja=czyPoleJestPuste(nowaPozycja);
                    }
                }else{
                    if ((wybranyPionek + 7) == nowaPozycja || (wybranyPionek + 9) == nowaPozycja) {
                        czyPoprawnaNowaPozycja=czyPoleJestPuste(nowaPozycja);
                    }
                }

            } else {
                if(czyPionekMaBicie(wybranyPionek,0)){
                    if ((wybranyPionek-18) == nowaPozycja || (wybranyPionek-14) == nowaPozycja || (wybranyPionek+14) == nowaPozycja || (wybranyPionek+18) == nowaPozycja) {
                        czyPoprawnaNowaPozycja=czyPoleJestPuste(nowaPozycja);
                    }
                }else{
                    if ((wybranyPionek - 7) == nowaPozycja || (wybranyPionek - 9) == nowaPozycja) {
                        czyPoprawnaNowaPozycja=czyPoleJestPuste(nowaPozycja);
                    }
                }
            }

            if(czyPoprawnaNowaPozycja==false){
                System.out.println("WYBRANA POZYCJA JEST NIEPOPRAWNA");
            }
        }
        return nowaPozycja;
    }

    static void wykonajRuch(boolean turaBialego, int wybranyPionek, int nowaPozycja){
        int maskaPozycji = 0b111111;
        int maskaCzyZyje = 0b100000000;

        if (turaBialego == true) {
            for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
                int bit1 = nrPionka * 9;
                int pozycjaPionkaBialyX1 = (int) (x1_bialy >> bit1) & maskaPozycji;
                if (pozycjaPionkaBialyX1 == wybranyPionek) {
                    if (((int) ((x1_bialy >> bit1) & maskaCzyZyje)) > 0) {
                        int przesuniecieLewo = nrPionka * 9;
                        long mask = (0b111111L << przesuniecieLewo);
                        x1_bialy = ((x1_bialy & (~mask)));
                        long nowaPozLong = nowaPozycja;
                        x1_bialy = (x1_bialy | (nowaPozLong << przesuniecieLewo));
                    }
                }

                int pozycjaPionkaBialyX2 = (int) (x2_bialy >> bit1) & maskaPozycji;
                if (pozycjaPionkaBialyX2 == wybranyPionek) {
                    if (((int) ((x2_bialy >> bit1) & maskaCzyZyje)) > 0) {
                        int przesuniecieLewo = nrPionka * 9;
                        long mask = (0b111111L << przesuniecieLewo);
                        x2_bialy = ((x2_bialy & (~mask)));
                        long nowaPozLong = nowaPozycja;
                        x2_bialy = (x2_bialy | (nowaPozLong << przesuniecieLewo));
                    }
                }
            }
        } else {

            for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
                int bit1 = nrPionka * 9;
                int pozycjaPionkaCzarnyX1 = (int) (x1_czarny >> bit1) & maskaPozycji;
                if (pozycjaPionkaCzarnyX1 == wybranyPionek) {
                    if (((int) ((x1_czarny >> bit1) & maskaCzyZyje)) > 0) {
                        int przesuniecieLewo = nrPionka * 9;
                        long mask = (0b111111L << przesuniecieLewo);
                        x1_czarny = ((x1_czarny & (~mask)));
                        long nowaPozLong = nowaPozycja;
                        x1_czarny = (x1_czarny | (nowaPozLong << przesuniecieLewo));
                    }
                }

                int pozycjaPionkaCzarnyyX2 = (int) (x2_czarny >> bit1) & maskaPozycji;
                if (pozycjaPionkaCzarnyyX2 == wybranyPionek) {
                    if (((int) ((x2_czarny >> bit1) & maskaCzyZyje)) > 0) {
                        int przesuniecieLewo = nrPionka * 9;
                        long mask = (0b111111L << przesuniecieLewo);
                        x2_czarny = ((x2_czarny & (~mask)));
                        long nowaPozLong = nowaPozycja;
                        x2_czarny = (x2_czarny | (nowaPozLong << przesuniecieLewo));
                    }
                }
            }
        }
    }

    static void wyswietl(){
        int n = 0;
        int linia = 0;
        int maskaPozycji = 0b111111;
        int maskaCzyZyje = 0b100000000;
        int maskaCzyDamka = 0b010000000;
        System.out.println("  1  2  3 4  5  6 7  8");
        for (int pole = 0; pole < 64; pole++) {
            if (n % 8 == 0) {
                System.out.print(linia+1+" ");
            }
            if ((pole % 2 == 0 && linia % 2 == 0) || (pole % 2 == 1 && linia % 2 == 1)) {
                System.out.print("\u2B1B ");
            } else {
                boolean wyswietlonoPionka = false;
                for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
                    int bit1 = nrPionka * 9;
                    int pozycjaPionkaBialyX1 = (int) (x1_bialy >> bit1) & maskaPozycji;
                    if (pozycjaPionkaBialyX1 == pole) {
                        if (((int) ((x1_bialy >> bit1) & maskaCzyZyje)) > 0) {
                            wyswietlonoPionka = true;
                            if (((int) ((x1_bialy >> bit1) & maskaCzyDamka)) > 0) {
                                System.out.print("\u2655 ");
                            } else {
                                System.out.print("\u265F ");
                            }
                        }
                    }

                    int pozycjaPionkaBialyX2 = (int) (x2_bialy >> bit1) & maskaPozycji;
                    if (pozycjaPionkaBialyX2 == pole) {

                        if (((int) ((x2_bialy >> bit1) & maskaCzyZyje)) > 0) {
                            wyswietlonoPionka = true;
                            if (((int) ((x2_bialy >> bit1) & maskaCzyDamka)) > 0) {
                                System.out.print("\u2655 ");
                            } else {
                                System.out.print("\u265F ");
                            }
                        }
                    }

                    int pozycjaPionkaCzarnyX1 = (int) (x1_czarny >> bit1) & maskaPozycji;

                    if (pozycjaPionkaCzarnyX1 == pole) {
                        if (((int) ((x1_czarny >> bit1) & maskaCzyZyje)) > 0) {
                            wyswietlonoPionka = true;

                            if (((int) ((x1_czarny >> bit1) & maskaCzyDamka)) > 0) {
                                System.out.print("\u265B ");
                            } else {
                                System.out.print("\u2659 ");
                            }
                        }
                    }

                    int pozycjaPionkaCzarnyX2 = (int) (x2_czarny >> bit1) & maskaPozycji;
                    if (pozycjaPionkaCzarnyX2 == pole) {
                        if (((int) ((x2_czarny >> bit1) & maskaCzyZyje)) > 0) {
                            wyswietlonoPionka = true;
                            if (((int) ((x2_czarny >> bit1) & maskaCzyDamka)) > 0) {
                                System.out.print("\u265B ");
                            } else {
                                System.out.print("\u2659 ");
                            }
                        }
                    }
                }
                if (wyswietlonoPionka == false) {
                    System.out.print("\u2B1C ");
                }
            }
            n++;
            if (n % 8 == 0) {
                System.out.println();
                linia++;
            }
        }
    }

    static boolean czyZakonczycGre(boolean turaBialego){
        return zbitoWszystkiePionkiGracza()||czyGraczZablokowany(turaBialego);
    }

    static boolean zbitoWszystkiePionkiGracza(){
        long maskaCzyZyjaPionki = 0b100000000100000000100000000100000000100000000100000000L;
        if((x1_bialy&maskaCzyZyjaPionki)==0 && (x2_bialy&maskaCzyZyjaPionki)==0){
            System.out.println("CZARNY WYGRYWA- bialy nie ma pionkow");
            return true;
        }
        if((x1_czarny&maskaCzyZyjaPionki)==0 && (x2_czarny&maskaCzyZyjaPionki)==0){
            System.out.println("BIALY WYGRYWA- czarny nie ma pionkow");
            return true;
        }
        return false;
    }

    static boolean czyGraczZablokowany(boolean turaBialego){

        int maskaPozycji = 0b111111;
        int maskaCzyZyje = 0b100000000;

        if(turaBialego==false){
            for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
                int bit1 = nrPionka * 9;
                int pozycjaPionkaCzarnyX1 = (int) (x1_czarny >> bit1) & maskaPozycji;
                if (((int) ((x1_czarny >> bit1) & maskaCzyZyje)) > 0) {
                    if(czyPionekMaBicie(pozycjaPionkaCzarnyX1,1) ||
                            czyMoznaPoruszycPionkaNaPole(pozycjaPionkaCzarnyX1+7) ||
                            czyMoznaPoruszycPionkaNaPole(pozycjaPionkaCzarnyX1+9)){
                    }
                    return false;
                }

                int pozycjaPionkaCzarnyX2 = (int) (x2_czarny >> bit1) & maskaPozycji;
                if (((int) ((x2_czarny >> bit1) & maskaCzyZyje)) > 0) {
                    if(czyPionekMaBicie(pozycjaPionkaCzarnyX2,1) ||
                            czyMoznaPoruszycPionkaNaPole(pozycjaPionkaCzarnyX2+7) ||
                            czyMoznaPoruszycPionkaNaPole(pozycjaPionkaCzarnyX2+9)){
                    }
                    return false;
                }
            }
            System.out.println("WYGRYWA BIALY - gracz czarny zablokowany");

            return true;
        }else{
            for (int nrPionka = 0; nrPionka < 6; nrPionka++) {
                int bit1 = nrPionka * 9;
                int pozycjaPionkaBialyX1 = (int) (x1_czarny >> bit1) & maskaPozycji;
                if (((int) ((x1_czarny >> bit1) & maskaCzyZyje)) > 0) {
                    if(czyPionekMaBicie(pozycjaPionkaBialyX1,1) ||
                            czyMoznaPoruszycPionkaNaPole(pozycjaPionkaBialyX1+7) ||
                            czyMoznaPoruszycPionkaNaPole(pozycjaPionkaBialyX1+9)){
                    }
                    return false;
                }

                int pozycjaPionkaBialyX2 = (int) (x2_czarny >> bit1) & maskaPozycji;
                if (((int) ((x2_czarny >> bit1) & maskaCzyZyje)) > 0) {
                    if(czyPionekMaBicie(pozycjaPionkaBialyX2,1) ||
                            czyMoznaPoruszycPionkaNaPole(pozycjaPionkaBialyX2+7) ||
                            czyMoznaPoruszycPionkaNaPole(pozycjaPionkaBialyX2+9)){
                    }
                    return false;
                }
            }
            System.out.println("WYGRYWA CZARNY - gracz bialy zablokowany");
            return true;

        }
    }

    static boolean czyMoznaPoruszycPionkaNaPole(int docelowaPozycja){
        return czyPoleJestPuste(docelowaPozycja)&&czyPoleJestCzarne(docelowaPozycja);
    }
}