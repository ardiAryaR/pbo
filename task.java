import java.io.*;
import java.util.Scanner;

public class task {
    private String nama_tugas;
    private String deskripsi;
    private String tanggal;
    private String hari;

    public task(String nama_tugas, String tanggal, String deskripsi, String hari) {
        this.nama_tugas = nama_tugas;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.hari = hari;
    }

    public String getNama_tugas() { return nama_tugas; }
    public String getTanggal() { return tanggal; }
    public String getDeskripsi() { return deskripsi; }
    public String getHari() { return hari; }

    public void setNama_tugas(String nama_tugas) { this.nama_tugas = nama_tugas; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    public void setHari(String hari) { this.hari = hari; }

    public void inputData() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Nama Tugas: ");
        this.nama_tugas = input.nextLine();
        System.out.print("Masukkan Deskripsi: ");
        this.deskripsi = input.nextLine();
        System.out.print("Masukkan Hari: ");
        this.hari = input.nextLine();
        System.out.print("Masukkan Tanggal: ");
        this.tanggal = input.nextLine();
    }

    public void outputData() {
        System.out.println("Nama Tugas: " + this.nama_tugas);
        System.out.println("Deskripsi: " + this.deskripsi);
        System.out.println("Hari: " + this.hari);
        System.out.println("Tanggal: " + this.tanggal);
    }

    public void simpanKeFile() {
        try (FileWriter writer = new FileWriter("data_tugas.txt", true)) {
            writer.write(this.nama_tugas + ";" + this.deskripsi + ";" + this.hari + ";" + this.tanggal + "\n");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

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
                System.out.println("=====================");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca data: " + e.getMessage());
        }
    }
}
