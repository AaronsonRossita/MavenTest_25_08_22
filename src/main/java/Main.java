import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static String txt = "text";




    public static void main(String[] args) {

        int x = 4;
        int x1 = 6;
        double y = 2.5;
        x = (int)y;
//        System.out.println(x);

        sum(x,x1);
        String[] arr = {"","3"};
        ArrayList<String> list = new ArrayList();
        list.add("6");
        list.add("mashu");
        list.add("true");

        HashMap<String,Integer> hashlist = new HashMap<>();
        hashlist.put("one",1);
        hashlist.put("two",2);
        System.out.println(hashlist.get("one"));

        Person person1 = new Person("Rossita","Aaronson","123",32);
        Person person2 = new Person("Shlomit","Rovner","456",38);
        Person person3 = new Person("Neria","Mines","789",25);
        HashMap<String,Person> personMap = new HashMap<>();
        personMap.put(person1.name,person1);
        personMap.put(person2.name,person2);
        personMap.put(person3.name,person3);
        System.out.println(personMap.get("Rossita"));


    }

    static void sum(int a, int b){

    }



}
