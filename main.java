import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        lama_waktu tugas = new lama_waktu("", "", "", "", "", "");
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Tambah Tugas");
            System.out.println("2. Tampilkan Tugas");
            System.out.println("3. Baca Tugas dari File");
            System.out.println("4. Hapus Tugas");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1:
                    tugas.inputData();
                    tugas.simpanKeFile();
                    System.out.println("=== Data Tersimpan ===");
                    break;
                case 2:
                    tugas.outputData();
                    break;
                case 3:
                    tugas.bacaDariFile();
                    break;
                case 4:
                    tugas.hapusTugas();
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

}
