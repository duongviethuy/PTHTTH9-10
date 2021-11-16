import java.io.*;
import java.net.Socket;

public class Processing extends Thread {
    private Socket socket;

    public Processing(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            System.out.println("Client " + socket.getPort() + " request log in");

            DataOutputStream DataOS = new DataOutputStream(socket.getOutputStream());
            DataInputStream DataIS = new DataInputStream(socket.getInputStream());
            Account acc = new Account();

            int ID = DataIS.readInt();
            String Pass = DataIS.readUTF();
            DataOS.writeBoolean(acc.logIn(ID, Pass));

            int count = 0;

            while (acc.logIn(ID, Pass) == false) {
                if (count == 3) {
                    System.out.println("Client " + socket.getPort() + " login failed more 3 times");
                    System.out.println("Disconnected");
                    socket.close();
                    break;
                } else
                    System.out.println("Client " + socket.getPort() + " login failed");
                ID = DataIS.readInt();
                Pass = DataIS.readUTF();
                DataOS.writeBoolean(acc.logIn(ID, Pass));
                count++;
            }

            if (acc.logIn(ID, Pass) == true) {
                System.out.println("Client " + socket.getPort() + " log in successful ");
                System.out.println("Username: " + acc.getUserName(ID));
                DataOS.writeBoolean(acc.logIn(ID, Pass));
                DataOS.writeUTF(acc.getUserName(ID));
                System.out.println("What is your request ?");
                System.out.println("1. Create account\n2. Delete account\n3. Change password\n0. Exit");

                int choose = 0;

                do {
                    choose = DataIS.readInt();
                    switch (choose) {
                    case 1:
                        System.out.println("Client " + socket.getPort() + " request create account");
                        ID = DataIS.readInt();
                        Pass = DataIS.readUTF();
                        if (acc.createAccount(ID, Pass) == true) {
                            System.out.println("Created account successful");
                            System.out.println("ID: " + ID + " Password: " + Pass);
                        } else {
                            System.out.println("Account already exist !!!");
                        }
                        break;
                    case 2:
                        System.out.println("Client " + socket.getPort() + " request delete an account");
                        ID = DataIS.readInt();
                        if (acc.deleteAccount(ID) == true) {
                            System.out.println("Deleted account !!!");
                        } else {
                            System.out.println("Account doesn't exist !!!");
                        }
                        break;
                    case 3:
                        System.out.println("Client " + socket.getPort() + " request change password this account");
                        String oldPass = DataIS.readUTF();
                        String newPass = DataIS.readUTF();
                        if (acc.changePassword(ID, oldPass, newPass) == true) {
                            System.out.println("Password changed");
                        } else {
                            System.out.println("Old password is not correct !!!");
                        }
                        break;

                    case 0:
                        System.out.println("Client " + socket.getPort() + " was disconnected");
                        socket.close();
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
