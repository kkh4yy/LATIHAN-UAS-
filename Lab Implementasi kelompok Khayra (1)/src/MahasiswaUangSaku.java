public class MahasiswaUangSaku extends Mahasiswa {
    private double uangSaku;
    public MahasiswaUangSaku(String nama, String npm, double ukt, double uangSaku) {
        super(nama, npm, ukt);
        this.uangSaku = uangSaku;
    }
    @Override
    public void bayar(double jumlahBayar) {
        if(!statusLunas()){
            if(jumlahBayar <= super.getUkt()){
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
        return "Mahasiswa Full Subsidi Plus Uang Saku\nStatus Lunas: "+statusLunas()+"\nNama : "+super.getNama() + "\nNPM " + super.getNpm() + "\nJumlah Ukt " + super.getUkt() + "\nUang Saku " + this.uangSaku;
    }
    
}
