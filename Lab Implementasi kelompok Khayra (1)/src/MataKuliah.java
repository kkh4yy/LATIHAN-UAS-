import java.util.ArrayList;
import java.util.List;

public class MataKuliah {
    ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    public String nama;

    MataKuliah(String nama){
        this.nama = nama;
    }

    public void prosesPendaftaran(Mahasiswa mahasiswa){
        daftarMahasiswa.add(mahasiswa);
    }

    public List<Mahasiswa> getMahasiswaByStatus(boolean status){
        return daftarMahasiswa.stream().
                filter(m -> m.statusLunas() == status)
                .map(m -> m)
                .toList();
    }

    public List<Mahasiswa> getMahasiswa(){
        return this.daftarMahasiswa;
    }
}
