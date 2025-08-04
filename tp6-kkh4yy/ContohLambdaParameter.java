import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
interface MapLingkaran {
    Lingkaran proses(Lingkaran lingkaran);    
}

@FunctionalInterface
interface FilterLingkaran {
    boolean proses(Lingkaran lingkaran);
}


public class ContohLambdaParameter {
    List<Lingkaran> lingkarans;
    ContohLambdaParameter(){
        lingkarans = List.of(
            new Lingkaran(1),
            new Lingkaran(2),
            new Lingkaran(3),
            new Lingkaran(4),
            new Lingkaran(5)
        );
    }
    public static void main(String[] args) {
        ContohLambdaParameter contoh = new ContohLambdaParameter();

        System.out.println("Lingkaran yang radius lebih besar dari 1");
        contoh.filterLingkaran(lingkaran -> lingkaran.getRadius() > 1);
        System.out.println(contoh);

        System.out.println("Radius dikuadratkan");
        contoh.mapLingkaran(lingkaran -> new Lingkaran(lingkaran.getRadius() * lingkaran.getRadius()));
        System.out.println(contoh);

        System.out.println("Lingkaran dengan radius ganjil");
        contoh.filterLingkaran(lingkaran -> lingkaran.getRadius() % 2 != 0);
        System.out.println(contoh);

        System.out.println("Radius ditambah 100");
        contoh.mapLingkaran(lingkaran -> new Lingkaran(lingkaran.getRadius() + 100));
        System.out.println(contoh);

    }

    // Method untuk memfilter lingkaran
    public void filterLingkaran(FilterLingkaran filter) {
        lingkarans = lingkarans.stream()
                               .filter(filter::proses) 
                               .collect(Collectors.toList());
    }
    
    // Method untuk memetakan lingkaran 
    public void mapLingkaran(MapLingkaran fungsi) {
        lingkarans = lingkarans.stream()
                               .map(x -> fungsi.proses(x))
                               .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Isi dari List lingkarans: " + lingkarans + "\n";
    }
}

class Lingkaran {
    private double radius;

    public Lingkaran(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    @Override
    public String toString() {
        return "radius=" + radius;
    }
}