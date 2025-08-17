package latihanJava;

import java.util.Scanner;
import java.util.Random;

public class slotMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        /*
         *  🍒🍒 (exactly two cherries, any order) → 0.5× your bet

            🍒🍒🍒 → 1.5× your bet

            🍋🍋🍋 → 1× your bet (break-even)

            🍉🍉🍉 → 2× your bet

            🔔🔔🔔 → 10× your bet

            7️⃣7️⃣7️⃣ → 100× your bet

            Everything else → 0× (lose)
         */

        /*
         * Deklarasi variabel
         */
        double balance = 0, bet = 0;
        char opsi;
        boolean ulang = true;
        int countCherry = 0, countLemon = 0, countWatermelon = 0, countBell = 0, countSeven = 0;
        // array simbol
        String[] symbol = {"🍒", "🍋", "🍉", "🔔", "7️⃣"};
        // simpan hasil roll
        String[] result = new String[3];

        /*
         * Tampilan
         */
        do{
            do{
                // menampilkan saldo tersisa
                System.out.println("Saldo: $" + balance);
                if(balance <= 0){ // cek jika saldo kurang dari atau sama dengan 0
                    System.out.println("Anda tidak memiliki saldo tersisa.");
                    // user memilih untuk menambah saldo atau tidak
                    do{ // memastikan bahwa inputannya valid
                        System.out.print("Ingin menambah saldo? (y/n) ");
                        opsi = scanner.next().charAt(0);
                        if(opsi != 'y' && opsi != 'n') System.out.println("Opsi tidak valid!");
                    }while(opsi != 'y' && opsi != 'n');
                    // jika user memilih 'y' atau 'n'
                    switch(opsi){
                        case 'y' -> { // jika user memilih 'y'
                            do {
                                System.out.print("Masukkan nominal: $");

                                if (scanner.hasNextDouble()) {
                                    balance = scanner.nextDouble();
                                    if (balance <= 0) {
                                        System.out.println("Nominal harus lebih besar dari 0.");
                                    }
                                } else {
                                    System.out.println("Masukkan hanya berupa angka.");
                                    scanner.next(); // buang input yang salah (misalnya huruf)
                                }

                            } while (balance <= 0);
                        }
                        case 'n' -> { // jika user memilih tidak, maka akan keluar dari loop
                            break;
                        }
                        default -> {
                            System.out.println("input invalid!");
                        }
                    }
                    System.out.println("Saldomu $" + balance);
                }
                
                if(balance <= 0) { // jika balance <= 0, keluar dari loop
                    break;
                }else{ // jika tidak, user dapat memasukkan taruhan
                    do {
                        System.out.print("Masukkan taruhan: $");

                        if (scanner.hasNextDouble()) {
                            bet = scanner.nextDouble();
                            if (bet <= 0) {
                                System.out.println("Nominal harus lebih besar dari 0.");
                            }
                        } else {
                            System.out.println("Masukkan hanya berupa angka.");
                            scanner.next(); // buang input yang salah (misalnya huruf)
                        }
                    } while (balance <= 0);
                }
            }while(bet > balance);

            if(balance <= 0) { // jika balance <= 0, keluar dari program
                opsi = 'n';
            }else{ // jika tidak, mesin akan mulai rolling
                // mengurangi balance sebanyak bet
                balance -= bet;
                System.out.println("Rolling...");
                // pause console selama 3 detik
                try {
                    Thread.sleep(2000);  // 2000 ms = 2 detik
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Roll
                System.out.println("***************"); 
                for(int i = 0; i < 3; i++){
                    // mengambil elemen dari symbol[] secara acak dan memasukkannya ke result[]
                    result[i] = symbol[random.nextInt(symbol.length)];
                    System.out.print(' ' + result[i]);
                    if(i != 2) System.out.print(" |");
                }
                System.out.println();
                System.out.println("***************");
                // memeriksa kombinasi rolling
                for(String hasil : result){
                    if(hasil.equals("🍒")) countCherry++;
                    if(hasil.equals("🍋")) countLemon++;
                    if(hasil.equals("🍉")) countWatermelon++;
                    if(hasil.equals("🔔")) countBell++;
                    if(hasil.equals("7️⃣")) countSeven++;
                }
                if(countCherry == 2) bet *= 0.5;
                else if(countCherry == 3) bet *= 1.5;
                else if(countLemon == 3) bet *= 1;
                else if(countWatermelon == 3) bet *= 2;
                else if(countBell == 3) bet *= 10;
                else if(countSeven == 3) bet *= 100;
                else bet *= 0;
    
                // menamplkan hasil
                System.out.println("Anda mendapatkan $" + bet);
                // hasil bet ditambahkan ke balance
                balance += bet;
    
                // ulang
                do{ // memastikan inputtan valid
                    System.out.print("Apakah anda ingin mengulang? (y/n) ");
                    opsi = scanner.next().charAt(0);
                    if(Character.toLowerCase(opsi) == 'n') { // user akan keluar dari program
                        System.out.println("Terima kasih sudah bermain!");
                        System.out.println("Saldo akhirmu adalah $" + balance);
                    }
                    else if(Character.toLowerCase(opsi) == 'y'){ // user lanjut judi
                        // reset semua variabel
                        countCherry = 0;
                        countLemon = 0;
                        countWatermelon = 0;
                        countBell = 0;
                        countSeven = 0;
                        bet = 0;
                        break;
                    }
                    else System.out.println("Opsi tidak valid!");;
                }while(Character.toLowerCase(opsi) != 'n');
            }
            if(Character.toLowerCase(opsi) == 'n') ulang = false;
            System.out.println();
        }while(ulang);
        scanner.close();
    }
}
