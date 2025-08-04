import java.util.Arrays;

public class PraUAS02 {
    public static void main(String[] args){
        Mahasiswa[] arr={
            new Mahasiswa("Budi", 2024, 3.3),
            new Mahasiswa("Anto", 2023, 3.5),
            new Mahasiswa("Citra", 2024, 2.8),
            new Mahasiswa("Eko", 2024, 4.0),
            new Mahasiswa("Deni", 2025, 3.0)
        };

        System.out.println("Sebelum sorting");
        for(Mahasiswa m: arr){
            System.out.println(m);
        }
        Arrays.sort(arr);

        System.out.println("Setelah sorting");
        for(Mahasiswa m: arr){
            System.out.println(m);
        }
    }
}
