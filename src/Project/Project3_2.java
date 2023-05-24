package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Musteri {
    String username;
    String password;
    Hesap musteriHesap;

}

class Hesap {


    String[] hesap;
    int[] paraMiktari;
}

public class Project3_2 {
    static int indx = 0;

    public static void main(String[] args) {

        String[] actions = {"Para_Yatir", "Para_Cek", "Transfer", "Cikis"};


        Scanner okuStr = new Scanner(System.in);
        Scanner okuInt = new Scanner(System.in);

        List<Musteri> users = new ArrayList<>();


        Musteri m1 = new Musteri();
        m1.username = "User1";
        m1.password = "password1";
        m1.musteriHesap = new Hesap();
        m1.musteriHesap.hesap = new String[2];
        m1.musteriHesap.hesap[0] = "1User1";
        m1.musteriHesap.hesap[1] = "2User1";
        m1.musteriHesap.paraMiktari = new int[2];
        m1.musteriHesap.paraMiktari[0] = 1000;
        m1.musteriHesap.paraMiktari[1] = 2000;
        users.add(m1);

        Musteri m2 = new Musteri();
        m2.username = "User2";
        m2.password = "password2";
        m2.musteriHesap = new Hesap();
        m2.musteriHesap.hesap = new String[2];
        m2.musteriHesap.hesap[0] = "1User2";
        m2.musteriHesap.hesap[1] = "2User2";
        m2.musteriHesap.paraMiktari = new int[2];
        m2.musteriHesap.paraMiktari[0] = 1500;
        m2.musteriHesap.paraMiktari[1] = 1800;
        users.add(m2);


        Musteri m3 = new Musteri();
        m3.username = "User3";
        m3.password = "password3";
        m3.musteriHesap = new Hesap();
        m3.musteriHesap.hesap = new String[2];
        m3.musteriHesap.hesap[0] = "1User3";
        m3.musteriHesap.hesap[1] = "2User3";
        m3.musteriHesap.paraMiktari = new int[2];
        m3.musteriHesap.paraMiktari[0] = 500;
        m3.musteriHesap.paraMiktari[1] = 800;
        users.add(m3);

        String username;
        String password;

        Hesap secilenHesap;
        Musteri aktifKullanici;

        while (true) {
            System.out.println("lütfen kullanıcı adını giriniz:");
            username = okuStr.nextLine();
            System.out.println("lütfen şifrenizi giriniz:");
            password = okuStr.nextLine();
            aktifKullanici = confirmUserNameAndPassword(users, username, password);
            if (aktifKullanici != null) {
                System.out.println("Basarili bir sekilde giris yaptiniz");
                break;
            }
            System.out.println("Sistemde kayitli boyle bir kullanici bulunamadi.. Tekrar deneyin");
        }

        while (true) {
            System.out.println("Yapmak istediginiz islemi seciniz...");
            for (int i = 0; i < actions.length; i++) {
                System.out.println(actions[i] + " - " + (i + 1));
            }
            int userChoice = okuInt.nextInt();

            switch (userChoice) {

                case 1: {

                    while (true) {
                        System.out.println(Arrays.toString(aktifKullanici.musteriHesap.hesap));
                        System.out.println("Hangi hesap numarasına para yatırmak istiyorsunuz...");
                        String sHesap = okuStr.nextLine();
                        if (aktifKullanici.musteriHesap == confirmAcountNumber(aktifKullanici, sHesap)) {
                            for (int i = 0; i < aktifKullanici.musteriHesap.hesap.length; i++) {
                                if (aktifKullanici.musteriHesap.hesap[i].equals(sHesap)) {
                                    indx = i;
                                }
                            }
                            break;

                        } else
                            System.out.println("Hatalı hesap numarası girdiniz,tekrar deneyin.");

                    }
                    System.out.println("Ne kadar para yatırmak istiyorsunuz");
                    int miktar = okuInt.nextInt();
                    aktifKullanici.musteriHesap.paraMiktari[indx] += miktar;

                }
                break;

                case 2: {

                    while (true) {

                        System.out.println("Lutfen para cekmek istediginiz hesap numarasini giriniz...: ");
                        System.out.println(Arrays.toString(aktifKullanici.musteriHesap.hesap));

                        String secim = okuStr.nextLine();

                        secilenHesap = confirmAcountNumber(aktifKullanici, secim);
                        if (secilenHesap == null) {
                            System.out.println("Hatali hesap numarasi girdiniz...");
                            continue;
                        }
                        System.out.println("Lutfen cekmek istediginiz para miktarini giriniz...: ");
                        int cekilecekPara = okuInt.nextInt();

                        if (withDraw(secilenHesap, cekilecekPara)) {
                            System.out.println("Hesabınızdaki para miktarı " + aktifKullanici.musteriHesap.paraMiktari[indx]);

                            break;

                        } else
                            System.out.println("Hesabınızda yeterli bakiye yoktur,tekrar deneyin...");
                    }
                    break;
                }

                case 3: {
                    System.out.println("Bu islemi su an gerceklestiremiyoruz...");
                    break;
                }

                case 4:
                    System.exit(1);

                default: {
                    System.out.println("Basarili bir secim yapmadiniz...");
                }
            }
        }
    }

    public static Musteri confirmUserNameAndPassword(List<Musteri> users, String username, String password) {
        Musteri m = null;

        for (Musteri i : users) {

            if (i.username.equals(username) && i.password.equals(password)) {
                m = i;
            }
        }
        return m;
    }

    public static Hesap confirmAcountNumber(Musteri aktifKullanici, String secilenHesap) {
        Hesap h = null;
        for (int i = 0; i < aktifKullanici.musteriHesap.hesap.length; i++) {
            if (aktifKullanici.musteriHesap.hesap[i].equals(secilenHesap)) {
                h = aktifKullanici.musteriHesap;
            }
        }
        return h;

    }

    public static boolean withDraw(Hesap secilenHesap, int cekilecekPara) {
        boolean b = false;
        if (secilenHesap.paraMiktari[indx] > cekilecekPara) {
            secilenHesap.paraMiktari[indx] -= cekilecekPara;
            b = true;


        }
        return b;
    }

}