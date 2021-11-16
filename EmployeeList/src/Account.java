import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Account {
    public Account() {
    }

    private ArrayList<Employee> employees = new ArrayList<>();

    public boolean logIn(int ID, String Pass) throws FileNotFoundException, IOException {
        try {
            ObjectInputStream ObjIS = new ObjectInputStream(new FileInputStream("./data/account.txt"));
            employees = (ArrayList<Employee>) ObjIS.readObject();
            Iterator<Employee> iter = employees.iterator();
            int arrLength = employees.toArray().length;
            Employee[] account = new Employee[arrLength];
            int count = 0;
            while (iter.hasNext()) {
                account[count] = iter.next();
                count++;
            }
            for (int i = 0; i < arrLength; i++) {
                if (ID == account[i].getId() && Pass.equals(account[i].getPassword())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    public boolean createAccount(int ID, String Pass) throws FileNotFoundException, IOException {
        try {
            ObjectInputStream ObjIS = new ObjectInputStream(new FileInputStream("./data/account.txt"));
            employees = (ArrayList<Employee>) ObjIS.readObject();
            int arrLength = employees.toArray().length;
            Employee[] account = new Employee[arrLength];
            int count = 0;
            Iterator<Employee> iter = employees.iterator();
            while (iter.hasNext()) {
                account[count] = iter.next();
                count++;
            }
            for (int i = 0; i < arrLength; i++) {
                if (ID == account[i].getId()) {
                    return false;
                }
            }

            employees.add(new Employee(ID, Pass));
            ObjectOutputStream ObjOS = new ObjectOutputStream(new FileOutputStream("./data/account.txt"));
            ObjOS.writeObject(employees);
            ObjIS.close();
            ObjOS.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return true;
    }

    public String getUserName(int ID) {
        try {
            ObjectInputStream ObjIS = new ObjectInputStream(new FileInputStream("./data/account.txt"));
            employees = (ArrayList<Employee>) ObjIS.readObject();
            Iterator<Employee> iter = employees.iterator();
            int arrLength = employees.toArray().length;
            Employee[] account = new Employee[arrLength];
            int count = 0;
            while (iter.hasNext()) {
                account[count] = iter.next();
                count++;
            }
            for (int i = 0; i < arrLength; i++) {
                if (ID == account[i].getId()) {
                    return account[i].getName();
                } else
                    return "";
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return "";
    }

    public boolean deleteAccount(int ID) throws FileNotFoundException, IOException {
        try {
            ObjectInputStream ObjIS = new ObjectInputStream(new FileInputStream("./data/account.txt"));
            employees = (ArrayList<Employee>) ObjIS.readObject();
            int arrLength = employees.toArray().length;
            Employee[] account = new Employee[arrLength];
            int count = 0;
            Iterator<Employee> iter = employees.iterator();
            while (iter.hasNext()) {
                account[count] = iter.next();
                count++;
            }
            for (int i = 0; i < arrLength; i++) {
                if (ID == account[i].getId()) {
                    employees.remove(account[i]);
                    ObjectOutputStream ObjOS = new ObjectOutputStream(new FileOutputStream("./data/account.txt"));
                    ObjOS.writeObject(employees);
                    ObjIS.close();
                    ObjOS.close();
                    return true;
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    public boolean changePassword(int ID, String oldPass, String newPass) throws FileNotFoundException, IOException {
        try {
            ObjectInputStream ObjIS = new ObjectInputStream(new FileInputStream("./data/account.txt"));
            employees = (ArrayList<Employee>) ObjIS.readObject();
            int arrLength = employees.toArray().length;
            Employee[] account = new Employee[arrLength];
            int count = 0;
            Iterator<Employee> iter = employees.iterator();
            while (iter.hasNext()) {
                account[count] = iter.next();
                count++;
            }
            for (int i = 0; i < arrLength; i++) {
                if (ID == account[i].getId() && oldPass.equals(account[i].getPassword())) {
                    account[i].setPassword(newPass);
                    ArrayList<Employee> newEmployees = new ArrayList<Employee>(Arrays.asList(account));
                    ObjectOutputStream ObjOS = new ObjectOutputStream(new FileOutputStream("./data/account.txt"));
                    ObjOS.writeObject(newEmployees);
                    ObjIS.close();
                    ObjOS.close();
                    return true;
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

}
