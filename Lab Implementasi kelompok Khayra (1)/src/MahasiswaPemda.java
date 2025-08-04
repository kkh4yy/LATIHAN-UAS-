public class MahasiswaPemda extends Mahasiswa{
    private String institusi;
    public MahasiswaPemda(String nama, String npm, double ukt, String institusi) {
        super(nama, npm, ukt);
        this.institusi = institusi;
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
        return "Mahasiswa Full Subsidi Pemda\nStatus Lunas: "+statusLunas()+"\nNama : "+super.getNama() + "\nNPM " + super.getNpm() + "\nJumlah Ukt " + super.getUkt() + "\nInstitusi " + this.institusi;
    }
}