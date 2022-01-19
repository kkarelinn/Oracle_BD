
import com.bd.service.ServEM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        System.out.println("Hello. THis is Oracle DB service program");
        Date date = java.util.Date.from(LocalDate.now().atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());

        while (true) {
            showMainMenu();
            int choose = ServEM.getNFromLine(5);
            switch (choose) {
                case (1):
                    ServEM.showAll();
                    break;

                case (2):
                    ServEM.showEmployee();
                    break;

                case (3):
                    ServEM.createEmployeeFromLine();
                    break;

                case (4):
                    ServEM.deleteEmployee();
                    break;

                case (5):
                    try {
                        br.close();
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

            }

        }

    }


    private static void showMainMenu() {
        System.out.println("\nMENU");

        System.out.println("1. See ALL employees");
        System.out.println("2. Get INFO about some employee");
        System.out.println("3. Enter NEW employee");
        System.out.println("4. Delete some employee");
        System.out.println("5. exit");

    }

    private static int getIdFromLine() {

        System.out.print("Enter employee's ID: ");
        while (true) {
            try {
                String str = br.readLine();
                if (str.matches("\\d")) {
                    return Integer.parseInt(str);
                }
                System.out.print("This isn't a number. Enter again: ");
            } catch (IOException e) {
                System.out.print("This isn't a number. Enter again: ");
            }
        }
    }
}
