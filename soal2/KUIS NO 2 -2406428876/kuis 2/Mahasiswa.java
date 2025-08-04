public class Mahasiswa{
    String nama;
    int angkatan;
    double ipk;  

    public Mahasiswa(String nama, int angkatan, double ipk){
        this.nama=nama;
        this.angkatan=angkatan;
        this.ipk=ipk;
    }

    public String getNama(){
        return nama;
    }

    public int getAngkatan(){
        return angkatan;
    }

    public double getIPK(){
        return ipk;
    }

    public String toString(){
        return "nama: "+nama+"\tangkatan: "+angkatan+"\tipk: "+ipk;
    }
}
