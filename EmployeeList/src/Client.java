import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 5050);

            DataOutputStream DataOS = new DataOutputStream(socket.getOutputStream());
            DataInputStream DataIS = new DataInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);

            System.out.println("Please log in");
            System.out.print("ID: ");
            DataOS.writeInt(sc.nextInt());
            System.out.println("Password: ");
            DataOS.writeUTF(sc.next());

            while (DataIS.readBoolean() == false) {
                System.out.println("Wrong ID or Password");
                System.out.println("Please log in");
                System.out.print("ID: ");
                DataOS.writeInt(sc.nextInt());
                System.out.print("Password: ");
                DataOS.writeUTF(sc.next());
            } // 1,111 //2,222 //.....

            if (DataIS.readBoolean() == true) {
                System.out.println("Login successful !!!");
                int choose = 0;
                do {
                    System.out.print("Your request: ");
                    choose = sc.nextInt();
                    DataOS.writeInt(choose);
                    switch (choose) {
                    case 1:
                        System.out.println("Create account");
                        System.out.print("ID: ");
                        DataOS.writeInt(sc.nextInt());
                        System.out.println("Password: ");
                        DataOS.writeUTF(sc.next());
                        break;
                    case 2:
                        System.out.println("Delete account");
                        System.out.print("ID: ");
                        DataOS.writeInt(sc.nextInt());
                        break;
                    case 3:
                        System.out.println("Change password");
                        System.out.print("Old password: ");
                        DataOS.writeUTF(sc.next());
                        System.out.print("New password: ");
                        DataOS.writeUTF(sc.next());
                        break;

                    case 0:
                        System.out.println("Good bye !!!!");
                        socket.close();
                        sc.close();
                        break;

                    default:
                        break;
                    }
                } while (choose != 0);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
