public abstract class Mahasiswa implements Pembayaran {
    private String nama;
    private String npm;
    private double ukt;
    private boolean terdaftar;

    public Mahasiswa(String nama, String npm, double ukt){
        this.nama = nama;
        this.npm = npm;
        this.ukt = ukt;
        this.terdaftar = false;
    }

    public String getNama(){
        return this.nama;
    }

    public String getNpm(){
        return this.npm;
    }

    public double getUkt(){
        return this.ukt;
    }

    public boolean getTerdaftar(){
        return this.terdaftar;
    }

    public void setNama(String namaNew){
        this.nama = namaNew;
    }

    public void setNpm(String npmNew){
        this.npm = npmNew;
    }

    public void setUkt(double uktNew){
        this.ukt = uktNew;
    }

    public void setTerdaftar(boolean terdaftar){
        this.terdaftar = terdaftar;
    }
}