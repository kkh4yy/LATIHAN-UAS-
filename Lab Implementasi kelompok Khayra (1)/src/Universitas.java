import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Universitas {

    static List<MataKuliah> matkul = new ArrayList<>();
    public static void main(String[] args) {
        Mahasiswa mhsCicil = new MahasiswaCicil("a", "12345678", 5000000.0, 2);
        Mahasiswa nonSubsidi = new BayarFull("b", "11111111", 10000000.0);
        Mahasiswa subsidiPemda = new MahasiswaPemda("c", "22222222", 4000000.0, "Tonato");
        Mahasiswa uktPotongan = new MahasiswaPotongan("d", "33333333", 10_000_000.0, 50);
        Mahasiswa subsidiFull = new MahasiswaUangSaku("e", "44444444", 4000000.0, 4000000.0);

        mhsCicil.bayar(1000000);
        subsidiPemda.bayar(4000000);
        MataKuliah kuliah = new MataKuliah("Manbis");
        kuliah.prosesPendaftaran(subsidiFull);
        kuliah.prosesPendaftaran(mhsCicil);
        kuliah.prosesPendaftaran(nonSubsidi);
        kuliah.prosesPendaftaran(subsidiPemda);
        kuliah.prosesPendaftaran(uktPotongan);

        MataKuliah kuliah2 = new MataKuliah("DDP");
        kuliah2.prosesPendaftaran(subsidiFull);
        kuliah2.prosesPendaftaran(mhsCicil);

        matkul.add(kuliah2);
        matkul.add(kuliah);

        while (true) {
            System.out.println("1. Total mahasiswa yang telah lunas: ");
            System.out.println("2. Total mahasiswa yang belum lunas: ");
            System.out.println("3. Jumlah peserta per kelas yang sudah lunas: ");
            System.out.println("4. Jumlah peserta per kelas: ");


            Scanner scanner = new Scanner(System.in);
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            if(pilihan ==1){
                int total = 0;
                for(MataKuliah m : matkul){
                    total += m.getMahasiswaByStatus(true).size();
                }
                System.out.println("Total mahasiswa yang telah lulus : "+total);
            }else if(pilihan ==2){
                int total = 0;
                for(MataKuliah m : matkul){
                    total += m.getMahasiswaByStatus(false).size();
                }
                System.out.println("Total mahasiswa yang belum lulus : "+total);
            } else if(pilihan ==3){
                for(MataKuliah m : matkul){
                    System.out.println("Total mahasiswa yang telah lulus pada matkul "+m.nama+" : "+m.getMahasiswaByStatus(true).size());
                }
            } else if(pilihan ==4){
                for(MataKuliah m : matkul){
                    System.out.println("Total mahasiswa pada matkul "+m.nama+" : "+m.daftarMahasiswa.size());
                }
            }
        }
        
    }
}
