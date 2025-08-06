import java.time.LocalDateTime;

public class Menfess {

    private String Pengguna;
    private LocalDateTime TanggalKirim;
    private String IsiMenfess;
  
    public Menfess (String Pengguna, String IsiMenfess, LocalDateTime TanggalKirim) {
    
        this.Pengguna = Pengguna;
        this.IsiMenfess = IsiMenfess;
        this.TanggalKirim = TanggalKirim;
    }

    public String getPengguna() {
        return Pengguna;
    }
    public String getIsiMenfess() {
        return IsiMenfess;
    }
    public LocalDateTime getTanggalKirim() {
        return TanggalKirim;
    }
}
