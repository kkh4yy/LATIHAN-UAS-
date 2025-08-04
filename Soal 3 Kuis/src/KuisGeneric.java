public class KuisGeneric {
  /** Return the maximum between two objects */
  // Mengapa Berkas FruitDemo tidak error
  // Sementara disini error. Setelah diketahui, perbaiki. 
  // Perbaiki program berikut ini:
  public static <E> E max(Comparable<E> o1, Comparable<E> o2) {
    if (o1.compareTo(o2) > 0)
      return o1;
    else
      return o2;
  }

    public static void main(String[] args) {
      int x = 23;
      String text = "Welcome";
      
      Human h1 = new Human("Ade");
      Human h2 = new Human("Budi");
      System.out.println(max(text,"Ahlan")); 
      System.out.println(max(43,x));
      System.out.println(max(h1,h2));
      //System.out.println((h1,x)); // Mengapa compile error?
    }
}

class Human implements Comparable<Human>{
  String nama;
  Human(String nama){
    this.nama = nama;
  }

  Human(){
    nama = "Cecep";
  }
  public String getNama(){
    return nama;
  }

  @Override
  public int compareTo(Human x){
    return nama.compareTo(x.getNama());
  }

  @Override
  public String toString(){
    return "Human bernama "+ nama;
  }
}