
import java.util.ArrayList;
import java.util.Collections;

public class FruitDemo{
	public static void main(String[] argv){
		Fruit fruit1 = new Apple();
		Fruit fruit2 = new Orange(); 
		Fruit frunt4 = new Apple();
		// Fruit fruit3 = new Fruit(); // Cannot be compile. Why?

		ArrayList<Fruit> koleksiBuah = new ArrayList<Fruit>();
		koleksiBuah.add(frunt4);
		koleksiBuah.add(fruit1);
		koleksiBuah.add(fruit2);

		Collections.sort(koleksiBuah);

		ArrayList<Apple> koleksiApple = new ArrayList<Apple>();
		koleksiApple.add((Apple) fruit1);
		koleksiApple.add(new Apple());

		System.out.println("Cetakan gabungan buah2an:");
		gabung(koleksiApple, koleksiBuah);

		Apple apple;
		apple = (Apple) fruit1; 
		//apple = (Apple) fruit2;
		//apple = (Apple) fruit3;
        // apple = (Apple) fruit2; // Compile ok, runtime error!
		if (fruit2 instanceof Apple) 
			apple = (Apple) fruit2; // Compile ok, runtime ok!
		
		eat(fruit1);
		eat(fruit2);

	}
	public static void eat(Fruit fruit){
		System.out.println("Hallo, saya " + fruit.toString());
		System.out.println("ya, saya bisa dimakan. " + fruit.howToEat());
	}

	public static <E> ArrayList<? super E> gabung(ArrayList<E> koleksi1, ArrayList<? super E> koleksi2){

		for (E apple : koleksi1){
			koleksi2.add(apple);
		}
		for (Object fruit : koleksi2){
			System.out.println(fruit.toString());
		}
		return koleksi2;
	}
}

class Apple implements Fruit{

	@Override
	public String howToEat(){
		return "Cuci dulu, langsung makan dengan kulitnya.";
	}

	public String toString(){
		return "apple";
	}
	@Override
	public int compareTo(Fruit x){
		if (x instanceof Apple) 
			return 1;
		else 
			return -1;
	}

}

class Orange implements Fruit{
	public String toString(){
		return "orange";
	}
	@Override
	public String howToEat(){
		return "Kupas dulu ya. Jangan makan sama kulitnya.";
	}
	@Override
	public int compareTo(Fruit x){
		if (x instanceof Orange) 
			return 1;
		else 
			return -1;
	}
}

interface Fruit extends Comparable<Fruit>{
	public String howToEat();

}