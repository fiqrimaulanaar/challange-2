import java.io.*;
import java.util.*;

public class ProgramUtama {
    public static void main(String[] args) {
        int pilihan, pilihan1;
        while (true) {
            String file = "D:/file_challange_2/data_sekolah.csv";

            Scanner scan = new Scanner(System.in);

            menuAtas();
            System.out.print("Letakkan file csv dengan nama file data_sekolah di direktori\nberikut: D:/file_challange_2" +
                    "\n\nPilih menu :" +
                    "\n1. Generate txt untuk menampilkan modus" +
                    "\n2. Generate txt untuk menampilkan nilai rata-rata, median" +
                    "\n3. Generate kedua file" +
                    "\n0. Exit" +
                    "\nPilihan anda : ");
            pilihan = scan.nextInt();


            if (pilihan == 1) {
                List<Integer> nilai = getValue(file);
                if (nilai.size() > 0) {
                    HashMap<Integer, Integer> freq = findFreq(nilai);
                    menuAtas();
                    writeModus(freq);
                    System.out.println("File telah di generate di D:/file_challange_2" +
                            "\nSilahkan cek");

                    System.out.print("0. Exit\n1. Kembali ke menu utama\nPilihan anda : ");
                    pilihan1 = scan.nextInt();

                    if (pilihan1 == 0) {
                        System.exit(1);
                    } else if (pilihan1 == 1) {
                        continue;
                    }

                }
            } else if (pilihan == 2) {
                List<Integer> nilai = getValue(file);
                if (nilai.size() > 0) {
                    Collections.sort(nilai);

                    double average = findAverage(nilai);
                    int median = findMedian(nilai);
                    int modus = findModus(nilai);
                    menuAtas();

                    writeMeanMedian(average, median, modus);
                    System.out.println("File telah di generate di D:/file_challange_2" +
                            "\nSilahkan cek");

                    System.out.print("0. Exit\n1. Kembali ke menu utama\nPilihan anda : ");
                    pilihan1 = scan.nextInt();

                    if (pilihan1 == 0) {
                        System.exit(1);
                    } else if (pilihan1 == 1) {
                        continue;
                    }
                }

            } else if (pilihan == 3) {
                List<Integer> nilai = getValue(file);
                if (nilai.size() > 0) {
                    HashMap<Integer, Integer> freq = findFreq(nilai);
                    writeModus(freq);

                    double average = findAverage(nilai);
                    Collections.sort(nilai);

                    int median = findMedian(nilai);
                    int modus = findModus(nilai);
                    writeMeanMedian(average, median, modus);
                    menuAtas();

                    System.out.println("File telah di generate di D:/file_challange_2" +
                            "\nSilahkan cek");

                    System.out.print("0. Exit\n1. Kembali ke menu utama\nPilihan anda : ");
                    pilihan1 = scan.nextInt();

                    if (pilihan1 == 0) {
                        System.exit(1);
                    } else if (pilihan1 == 1) {
                        continue;
                    }
                }
            } else if (pilihan == 0) {
                System.exit(1);
            }
        }
    }


    public static void menuAtas() {
        System.out.println("------------------------------\nAplikasi Pengolah Nilai Siswa\n------------------------------");
    }

    public static List<Integer> getValue(String file) {
        String line = "";
        List<Integer> nilai = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(";");
//                System.out.println(data);
                for (String data : datas) {
                    if (data.equals("KelasA") || data.equals("KelasB") || data.equals("KelasC") || data.equals("KelasD") || data.equals("KelasE") || data.equals("KelasF") || data.equals("KelasG") || data.equals("KelasH")) {
                    } else {
                        nilai.add(Integer.valueOf(data));
                    }
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nilai;
    }

    public static double findAverage(List<Integer> nilaiArr) {
        int hasil = 0;
        for (int n : nilaiArr
        ) {
            hasil += n;
        }
//        return (double) hasil / nilaiArr.size();
        return Double.parseDouble(String.format("%.2f", (double) hasil / nilaiArr.size()));
    }

    public static HashMap<Integer, Integer> findFreq(List<Integer> arr) {

        HashMap<Integer, Integer> hm = new HashMap<>();

        for (Integer nilai : arr) {
            if (hm.get(nilai) != null) {
                hm.put(nilai, hm.get(nilai) + 1);

            } else {
                hm.put(nilai, 1);
            }
        }

        return hm;
    }

    public static int findModus(List<Integer> arr) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int max = 1;
        int max_value = 0;

        for (Integer nilai : arr) {
            if (hm.get(nilai) != null) {
                int jumlah = hm.get(nilai);
                jumlah++;
                hm.put(nilai, hm.get(nilai) + 1);

                if (jumlah > max) {
                    max = jumlah;
                    max_value = nilai;
                }
            } else {
                hm.put(nilai, 1);
            }
        }
        return max_value;
    }

    public static int findMedian(List<Integer> arr) {
        double median;
        if (arr.size() % 2 == 0)
            median = ((double) arr.get(arr.size() / 2) + (double) arr.get(arr.size() / 2 - 1)) / 2;
        else
            median = (double) arr.get(arr.size() / 2);
        return (int) median;
    }

    public static void writeMeanMedian(double avg, int median, int modus) {
        try {
            File file = new File("D:/file_challange_2/data_sekolah_mean_median.txt");

            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(file);
                BufferedWriter bwr = new BufferedWriter(writer);
                bwr.write("Berikut hasil Pengolahan Nilai:" +
                        "\n\nBerikut hasil sebaran data nilai" +
                        "\nMean : " + avg +
                        "\nMedian : " + median +
                        "\nModus : " + modus);
                bwr.newLine();
                bwr.flush();
                bwr.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeModus(HashMap<Integer, Integer> freq) {
        try {
            File file = new File("D:/file_challange_2/data_sekolah_modus.txt");

            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(file);
                BufferedWriter bwr = new BufferedWriter(writer);
                bwr.write("Berikut Hasil Pengolahan Nilai :" +
                        "\n\nNilai\t\t|\tFrekuensi" +
                        "\n6\t\t|\t" + freq.get(6) +
                        "\n7\t\t|\t" + freq.get(7) +
                        "\n8\t\t|\t" + freq.get(8) +
                        "\n9\t\t|\t" + freq.get(9) +
                        "\n10\t\t|\t" + freq.get(10));
                bwr.newLine();
                bwr.flush();
                bwr.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
