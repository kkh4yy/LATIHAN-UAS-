public class BayarFull extends Mahasiswa {

    public BayarFull(String nama, String npm, double ukt) {
        super(nama, npm, ukt);
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
        return "Mahasiswa Non Subsidi\nStatus Lunas: "+statusLunas()+"\nNama : "+super.getNama() + "\nNPM " + super.getNpm() + "\nJumlah Ukt " + super.getUkt();
    }
    
}
