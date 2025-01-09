import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUIPengelolaanTugas {
    private JFrame frame;
    private JPanel panel;
    private JTextField namaTugasField, deskripsiField, hariField, tanggalField, waktuMulaiField, waktuSelesaiField;
    private JTextArea areaTampilan;
    private JButton tombolTambahTugas, tombolTampilkanTugas, tombolHapusTugas;
    private JScrollPane panelScroll;

    public GUIPengelolaanTugas() {
        frame = new JFrame("Pengelolaan Tugas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        panel = new JPanel();
        panel.setLayout(null);

        JLabel labelNamaTugas = new JLabel("Nama Tugas:");
        labelNamaTugas.setBounds(20, 20, 100, 25);
        panel.add(labelNamaTugas);

        namaTugasField = new JTextField();
        namaTugasField.setBounds(150, 20, 200, 25);
        panel.add(namaTugasField);

        JLabel labelDeskripsi = new JLabel("Deskripsi:");
        labelDeskripsi.setBounds(20, 60, 100, 25);
        panel.add(labelDeskripsi);

        deskripsiField = new JTextField();
        deskripsiField.setBounds(150, 60, 200, 25);
        panel.add(deskripsiField);

        JLabel labelHari = new JLabel("Hari:");
        labelHari.setBounds(20, 100, 100, 25);
        panel.add(labelHari);

        hariField = new JTextField();
        hariField.setBounds(150, 100, 200, 25);
        panel.add(hariField);

        JLabel labelTanggal = new JLabel("Tanggal:");
        labelTanggal.setBounds(20, 140, 100, 25);
        panel.add(labelTanggal);

        tanggalField = new JTextField();
        tanggalField.setBounds(150, 140, 200, 25);
        panel.add(tanggalField);

        JLabel labelWaktuMulai = new JLabel("Waktu Mulai:");
        labelWaktuMulai.setBounds(20, 180, 100, 25);
        panel.add(labelWaktuMulai);

        waktuMulaiField = new JTextField();
        waktuMulaiField.setBounds(150, 180, 200, 25);
        panel.add(waktuMulaiField);

        JLabel labelWaktuSelesai = new JLabel("Waktu Selesai:");
        labelWaktuSelesai.setBounds(20, 220, 100, 25);
        panel.add(labelWaktuSelesai);

        waktuSelesaiField = new JTextField();
        waktuSelesaiField.setBounds(150, 220, 200, 25);
        panel.add(waktuSelesaiField);

        // Area untuk menampilkan data
        areaTampilan = new JTextArea();
        areaTampilan.setEditable(false);
        areaTampilan.setLineWrap(true);
        areaTampilan.setWrapStyleWord(true);

        // menambahkan JScrollPane untuk JTextArea
        panelScroll = new JScrollPane(areaTampilan);
        panelScroll.setBounds(20, 260, 440, 300);
        panelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(panelScroll);

        // membuat tombol tombol
        tombolTambahTugas = new JButton("Tambah Tugas");
        tombolTambahTugas.setBounds(40, 580, 130, 30);
        panel.add(tombolTambahTugas);

        tombolTampilkanTugas = new JButton("Tampilkan Tugas");
        tombolTampilkanTugas.setBounds(180, 580, 130, 30);
        panel.add(tombolTampilkanTugas);

        tombolHapusTugas = new JButton("Hapus Tugas");
        tombolHapusTugas.setBounds(320, 580, 130, 30);
        panel.add(tombolHapusTugas);

        frame.add(panel);
        frame.setVisible(true);

        // Tambahkan aksi tombol
        tombolTambahTugas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahTugas();
            }
        });

        tombolTampilkanTugas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanTugas();
            }
        });

        tombolHapusTugas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bukaHapusTugas();
            }
        });
    }

    private void tambahTugas() {
        String namaTugas = namaTugasField.getText();
        String deskripsi = deskripsiField.getText();
        String hari = hariField.getText();
        String tanggal = tanggalField.getText();
        String waktuMulai = waktuMulaiField.getText();
        String waktuSelesai = waktuSelesaiField.getText();

        String tugas = namaTugas + ";" + deskripsi + ";" + hari + ";" + tanggal + ";" + waktuMulai + ";" + waktuSelesai;
        try (BufferedWriter penulis = new BufferedWriter(new FileWriter("data_tugas.txt", true))) {
            penulis.write(tugas);
            penulis.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Gagal menambah tugas: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(frame, "Tugas berhasil ditambahkan!");
    }

    private void tampilkanTugas() {
        StringBuilder dataTugas = new StringBuilder();

        try (BufferedReader pembaca = new BufferedReader(new FileReader("data_tugas.txt"))) {
            String baris;
            while ((baris = pembaca.readLine()) != null) {
                String[] data = baris.split(";");
                dataTugas.append("Nama Tugas: ").append(data[0]).append("\n");
                dataTugas.append("Deskripsi: ").append(data[1]).append("\n");
                dataTugas.append("Hari: ").append(data[2]).append("\n");
                dataTugas.append("Tanggal: ").append(data[3]).append("\n");
                if (data.length > 4) {
                    dataTugas.append("Waktu: ").append(data[4]).append(" - ").append(data[5]).append("\n");
                }
                dataTugas.append("===========================\n");
            }
        } catch (Exception e) {
            dataTugas.append("Terjadi kesalahan saat membaca file.");
        }

        areaTampilan.setText(dataTugas.toString());
    }

    private void bukaHapusTugas() {
        JDialog dialog = new JDialog(frame, "Hapus Tugas", true);
        dialog.setSize(400, 350);
        dialog.setLayout(null);

        JTextArea daftarTugas = new JTextArea();
        daftarTugas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(daftarTugas);
        scrollPane.setBounds(20, 20, 340, 200);
        dialog.add(scrollPane);

        JLabel labelPilih = new JLabel("Masukkan Nomor Tugas:");
        labelPilih.setBounds(20, 240, 200, 25);
        dialog.add(labelPilih);

        JTextField inputNomor = new JTextField();
        inputNomor.setBounds(180, 240, 100, 25);
        dialog.add(inputNomor);

        JButton tombolHapus = new JButton("Hapus");
        tombolHapus.setBounds(140, 280, 100, 30);
        dialog.add(tombolHapus);

        // Load daftar tugas ke dalam JTextArea
        StringBuilder dataTugas = new StringBuilder();
        String[] barisData = new String[100]; // Maksimal 100 tugas
        int jumlahTugas = 0;

        try (BufferedReader pembaca = new BufferedReader(new FileReader("data_tugas.txt"))) {
            String baris;
            int nomor = 1;
            while ((baris = pembaca.readLine()) != null) {
                barisData[jumlahTugas++] = baris;
                String[] data = baris.split(";");
                dataTugas.append(nomor++).append(". Nama Tugas: ").append(data[0]).append("\n");
                dataTugas.append("   Deskripsi: ").append(data[1]).append("\n");
                dataTugas.append("   Hari: ").append(data[2]).append("\n");
                dataTugas.append("   Tanggal: ").append(data[3]).append("\n");
                if (data.length > 4) {
                    dataTugas.append("   Waktu: ").append(data[4]).append(" - ").append(data[5]).append("\n");
                }
                dataTugas.append("===========================\n");
            }
        } catch (Exception e) {
            dataTugas.append("Tidak ada data tugas.");
        }
        daftarTugas.setText(dataTugas.toString());

        int jumlahTugasAkhir = jumlahTugas;
        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int nomor = Integer.parseInt(inputNomor.getText());
                    if (nomor > 0 && nomor <= jumlahTugasAkhir) {
                        try (FileWriter penulis = new FileWriter("data_tugas.txt", false)) {
                            for (int i = 0; i < jumlahTugasAkhir; i++) {
                                if (i != nomor - 1) {
                                    penulis.write(barisData[i] + "\n");
                                }
                            }
                        }
                        dialog.dispose();
                        tampilkanTugas(); // Perbarui tampilan utama
                        JOptionPane.showMessageDialog(frame, "Tugas berhasil dihapus!");
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Nomor tugas tidak valid!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Masukkan nomor yang valid!");
                }
            }
        });

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new GUIPengelolaanTugas();
    }
}
