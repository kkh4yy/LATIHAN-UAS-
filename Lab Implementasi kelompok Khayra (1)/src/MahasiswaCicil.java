public class MahasiswaCicil extends Mahasiswa {
    private int banyakCicilan;
    private double totalBayar;
    public MahasiswaCicil(String nama, String npm, double ukt, int banyakCicilan) {
        super(nama, npm, ukt);
        this.banyakCicilan = banyakCicilan;
        this.totalBayar = 0;
    }


    @Override
    public void bayar(double jumlahBayar) {
        if(!statusLunas()){
            totalBayar += jumlahBayar;
            if(totalBayar > super.getUkt()){
                totalBayar = super.getUkt();
            }
        }

        if(!super.getTerdaftar()){
            super.setTerdaftar(true);
        }
    }
    @Override
    public boolean statusLunas() {
        if(super.getUkt() == totalBayar) return true;
        return false;
    }

    @Override
    public String toString(){
        return "Mahasiswa mencicil\nStatus Lunas: "+statusLunas()+"\nNama : "+super.getNama() + "\nNPM " + super.getNpm() + "\nJumlah Ukt " + super.getUkt() + "\nJumlah yang dibayar " + this.totalBayar;
    }


    
}
