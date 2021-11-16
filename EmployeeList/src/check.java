import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class check {
    public static void main(String[] args) throws IOException {
        try {

            ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream("account.txt", false));
            Employee a = new Employee(1, "111");
            Employee b = new Employee(2, "222");
            Employee c = new Employee(3, "333");
            Employee d = new Employee(4, "444");
            Employee e = new Employee(5, "555");
            a.setName("Dương Việt Huy");
            b.setName("Nguyễn Thị B");
            c.setName("Vũ Văn A");
            d.setName("Cao Thị C");

            ArrayList<Employee> employees = new ArrayList<Employee>();
            employees.add(a);
            employees.add(b);
            employees.add(c);
            employees.add(d);
            employees.add(e);

            OOS.writeObject(employees);

            ArrayList<Employee> employees1 = new ArrayList<Employee>();

            ObjectInputStream OIS = new ObjectInputStream(new FileInputStream("account.txt"));
            employees1 = (ArrayList<Employee>) OIS.readObject();

            Employee[] ems = new Employee[employees1.toArray().length];

            Iterator<Employee> iterator = employees1.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                ems[i] = iterator.next();
                i++;
            }

            System.out.println(ems[1].getPassword());

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
