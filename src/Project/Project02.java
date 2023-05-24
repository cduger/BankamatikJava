
package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class client {
    String uName, hNo, pass;
    int mAm;
}

public class Project02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);
        ArrayList<client> cl = new ArrayList<>();
        ArrayList<Integer> funds = new ArrayList<>();
        ArrayList<String> passWord = new ArrayList<>();
        ArrayList<String> users = new ArrayList<>();
        ArrayList<String> accounts = new ArrayList<>();
        ArrayList<String> action = new ArrayList<>(Arrays.asList("Para_Yatir", "Para_Cek", "Transfer", "Cikis"));

        client c1 = new client();
        client c2 = new client();
        client c3 = new client();
        c1.hNo = "12";
        c1.mAm = 1000;
        c1.uName = "cihat";
        c1.pass = "1234";
        c2.hNo = "13";
        c2.mAm = 2000;
        c2.uName = "neval";
        c2.pass = "1002";
        c3.hNo = "14";
        c3.mAm = 3000;
        c3.uName = "onur";
        c3.pass = "123456";
        cl.add(c1);
        cl.add(c2);
        cl.add(c3);

        withdraw(cl, funds, passWord, users, accounts);

        while (true) {
            System.out.print("Kullanıcı adınızı giriniz: ");
            String s = scan.next();
            System.out.print("Şifrenizi giriniz: ");
            String p = scan.next();
            if (confirmusernameAndpassword(users, passWord, s, p)) {
                System.out.println("Başarılı bir şekilde giriş yaptınız!!");
                break;
            } else
                System.out.println("Sisteme kayıtlı böyle bir kullanıcı bulunamadı..Tekrar deneyin!!!");

        }

        while (true) {
            System.out.println("Yapmak istediginiz islemi seciniz...");
            for (int i = 0; i < action.size(); i++) {
                System.out.println(action.get(i) + " - " + (i + 1));
            }
            int choice = scan2.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Bu bankamatik gecici olarak para yatirma islemine kapalidir...Uzgunuz");
                    break;
                case 2:
                    System.out.println("Hangi hesaptan para cekmek istiyorsunuz?");
                    System.out.println(accounts);
                    System.out.print("Hesap Numarasının adını giriniz: ");
                    String str = scan2.next();
                    int in = accounts.indexOf(str);

                    while (true) {
                        System.out.print("Lütfen para miktarını giriniz: ");
                        int money = scan3.nextInt();
                        if (funds.get(in) >= money) {
                            System.out.println("Lütfen paranızı alınız hesabınızda " + (funds.get(in) - money) + " dolar kalmıştır.");
                            break;
                        } else if (funds.get(in) < money) {
                            System.out.println("Hesabınızda yeterli bakiye bulunmamaktadır.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Bu islemi su an gerceklestiremiyoruz...");
                    break;
                case 4:
                    System.exit(1);
                default:
                    System.out.println("Basarili bir secim yapmadiniz...");
            }
        }
    }
    static boolean confirmusernameAndpassword(ArrayList<String> u, ArrayList<String> p, String username, String password) {
        if (u.contains(username) && p.contains(password)) {
            return true;
        } else
            return false;
    }
    static void withdraw(ArrayList<client> cl, ArrayList<Integer> funds, ArrayList<String> passWord, ArrayList<String> users, ArrayList<String> accounts) {
        for (int i = 0; i < cl.size(); i++) {
            funds.add(cl.get(i).mAm);
            passWord.add(cl.get(i).pass);
            users.add(cl.get(i).uName);
            accounts.add(cl.get(i).hNo);
        }
    }
}


