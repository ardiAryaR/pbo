import java.io.*;
import java.util.Scanner;

public class lama_waktu extends task {
    private String waktu_mulai;
    private String waktu_selesai;

    public lama_waktu(String nama_tugas, String tanggal, String deskripsi, String hari, String waktu_mulai, String waktu_selesai) {
        super(nama_tugas, tanggal, deskripsi, hari);
        this.waktu_mulai = waktu_mulai;
        this.waktu_selesai = waktu_selesai;
    }

    public String getWaktu_mulai() {return waktu_mulai;}
    public String getWaktu_selesai() {return waktu_selesai;}

    public void setWaktu_mulai(String waktu_mulai) {this.waktu_mulai = waktu_mulai;}
    public void setWaktu_selesai(String waktu_selesai) {this.waktu_selesai = waktu_selesai;}

    @Override
    public void inputData() {
        super.inputData();
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Waktu Mulai : ");
        this.waktu_mulai = input.nextLine();
        System.out.print("Masukkan Waktu Selesai : ");
        this.waktu_selesai = input.nextLine();
    }

    @Override
    public void outputData() {
        super.outputData();
        System.out.println("Waktu: " + this.waktu_mulai + " - " + this.waktu_selesai);
    }

    @Override
    public void simpanKeFile() {
        try (FileWriter writer = new FileWriter("data_tugas.txt", true)) {
            writer.write(getNama_tugas() + ";" + getDeskripsi() + ";" + getHari() + ";" + getTanggal() + ";" + this.waktu_mulai + ";" + this.waktu_selesai + "\n");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

    @Override
    public void bacaDariFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data_tugas.txt"))) {
            String line;
            System.out.println("=== Data dalam File ===");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                System.out.println("Nama Tugas: " + data[0]);
                System.out.println("Deskripsi: " + data[1]);
                System.out.println("Hari: " + data[2]);
                System.out.println("Tanggal: " + data[3]);
                if (data.length > 4) {
                    System.out.println("Waktu: " + data[4] + " - " + data[5]);
                }
                System.out.println("=====================");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca data: " + e.getMessage());
        }
    }

    public void hapusTugas() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data_tugas.txt"))) {
            Scanner input = new Scanner(System.in);
            String[] lines = new String[100]; // Maksimal 100 tugas
            int count = 0;
            String line; // Deklarasi variabel 'line'

            // Menampilkan semua tugas
            System.out.println("=== Daftar Tugas ===");
            while ((line = reader.readLine()) != null) {
                lines[count] = line;
                String[] data = line.split(";");
                System.out.println((count + 1) + ". Nama Tugas: " + data[0]);
                System.out.println("   Deskripsi: " + data[1]);
                System.out.println("   Hari: " + data[2]);
                System.out.println("   Tanggal: " + data[3]);
                if (data.length > 4) {
                    System.out.println("   Waktu: " + data[4] + " - " + data[5]);
                }
                System.out.println("===========================");
                count++;
            }

            if (count == 0) {
                System.out.println("Tidak ada tugas untuk dihapus.");
                return;
            }

            // Meminta nomor tugas untuk dihapus
            System.out.print("Masukkan nomor tugas yang ingin dihapus: ");
            int nomor = input.nextInt();

            if (nomor > 0 && nomor <= count) {
                try (FileWriter writer = new FileWriter("data_tugas.txt", false)) {
                    for (int i = 0; i < count; i++) {
                        if (i != nomor - 1) {
                            writer.write(lines[i] + "\n");
                        }
                    }
                }
                System.out.println("Tugas berhasil dihapus!");
            } else {
                System.out.println("Nomor tugas tidak valid.");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca atau menghapus data: " + e.getMessage());
        }
    }
}
