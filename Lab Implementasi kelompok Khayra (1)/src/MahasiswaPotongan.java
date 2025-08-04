public class MahasiswaPotongan extends Mahasiswa {

    private double potongan;
    public MahasiswaPotongan(String nama, String npm, double ukt, int potongan) {
        super(nama, npm, ukt);
        this.potongan = potongan;
    }
    @Override
    public void bayar(double jumlahBayar) {
        if(!statusLunas()){
            double yangHarusDibayar = super.getUkt() - (super.getUkt()*potongan) /100.0 ;
            if(jumlahBayar <= yangHarusDibayar){
                super.setTerdaftar(true);
            }
        }
    }
    @Override
    public boolean statusLunas() {
        return super.getTerdaftar();
    }
    @Override
    public String toString(){
        return "Mahasiswa Membayar dengan Potongan\nStatus Lunas: "+statusLunas()+"\nNama : "+super.getNama() + "\nNPM " + super.getNpm() + "\nJumlah Ukt " + super.getUkt() + "\nJumlah potongan " + ((super.getUkt()*potongan) /100.0);
    }
}
