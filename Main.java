import java.util.*;
import java.io.*;

class model{
    String name;
    String color;
    float age;
    long price;
    public model(){}
    public model(String name, String color, float age, long price) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.price = price;
    }
}
class car{
    protected String u_name;
    protected String address;
    void get_details(){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter your name");
        u_name=in.nextLine();
        System.out.println("Enter you location");
        address=in.nextLine();
    }
}
class customer extends car{
    static List<String> getRecordFromLine(String line) {
          List<String> values = new ArrayList<String>();
          try (Scanner rowScanner = new Scanner(line)) {
              rowScanner.useDelimiter(",");
              while (rowScanner.hasNext()) {
                  values.add(rowScanner.next());
              }
          }
          return values;
        }   
    void Bill(model m){
        System.out.println("======================================");
        System.out.println("Name - "+u_name);
        System.out.println("Location - "+address);
        System.out.println("Car name - "+ m.name);
        System.out.println("Color of car - "  + m.color);
        System.out.println("Current Age of car - "  + m.age + "yrs");
        System.out.println("Final prize bought - " + m.price);

    }
    void c_panel(){
        try {
            super.get_details();
          LinkedList<model> c=new LinkedList<>();
          File myObj = new File("sample.csv");
          Scanner myReader = new Scanner(myObj);
          int iq=0;
          while (myReader.hasNextLine()) {
            List<String> res=getRecordFromLine(myReader.next());
            // System.out.println(res);
            if (iq==0) {iq++;continue;}
            String u=res.get(0);
            float w=Float.parseFloat(res.get(1));
            String v=res.get(2);
            long x=Long.parseLong(res.get(3));
            c.add(new model(u,v,w,x));
          }
          for(model inc:c){
            System.out.println("Op"+(c.indexOf(inc)+1)+" "+inc.name+" in "+inc.color+" "+inc.age+"yrs at "+inc.price);
          }
          myReader.close();
          
          System.out.println("Which car would you like to purchase : ");
          Scanner scan_=new Scanner(System.in);
          int op=scan_.nextInt() - 1;
          FileWriter myWriter = new FileWriter("sample.csv");
          myWriter.write("Model,Age,Color,Price");
          for (model inc:c){
            if (c.indexOf(inc)==0 || c.indexOf(inc)==op) continue;
            myWriter.write("\n"+inc.name + ","  + inc.age + ","  + inc.color + "," + inc.price);
          }
          myWriter.close();
          Bill(c.get(op));
        } catch (Exception e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
}
class dealer extends car{
    
    model m=new model();
    void get_details(){
        super.get_details();
        Scanner in=new Scanner(System.in);
        System.out.println("Enter your car model");
        m.name=in.nextLine();
        System.out.println("Enter your car color");
        m.color=in.nextLine();
        System.out.println("Enter your car age");
        m.age=in.nextFloat();
        System.out.println("Enter the price");
        m.price=in.nextLong();
        try {
            FileWriter myWriter = new FileWriter("sample.csv",true);
            myWriter.write("\n"+m.name + ","  + m.age + ","  + m.color + "," + m.price);
            myWriter.close();
        } catch (Exception e) {        }
    }
    void Bill(){
        System.out.println("======================================");
        System.out.println("Name - "+u_name);
        System.out.println("Location - "+address);
        System.out.println("Car name - "+ m.name);
        System.out.println("Color of car - "  + m.color);
        System.out.println("Current Age of car - "  + m.age + "yrs");
        System.out.println("Final prize sold - " + m.price);
    }
    void d_panel(){
        get_details();
        Bill();
    }
}
public class Main{
    public static void main(String[] args) {
        int option=2;
        do {
            System.out.print("Enter user type 1:Dealer/2:Customer - ");
            Scanner scan_=new Scanner(System.in);
            int user_type=scan_.nextInt();
            switch (user_type) {
                case 1:
                    dealer D=new dealer();
                    D.d_panel();
                    break;
                case 2:
                    customer C=new customer();
                    C.c_panel();
                default:
                    break;
            }
            
            System.out.print("Do you want to continue 1:Yes/2:No - ");
            option=scan_.nextInt();
        }while(option==1);
    }
}