



import java.util.*;
import java.io.*;

class User {
    String name;
    String password;
    String phoneNo;

    User(String name, String password, String phoneNo) {
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
    }
}

class Admin {
    String name;
    String password;

    Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

class Train {
    String TrainName;
    String startPoint;
    String endPoint;
    int noOfSeat;
    int noOfSeatAlotted;
    int noOfTickets;
    int[] seatAlloted;

    Train(String TrainName, String startPoint, String endPoint, int noOfSeat, int noOfSeatAlotted, int noOfTickets,
            int[] seatAlloted) {
        this.TrainName = TrainName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.noOfSeat = noOfSeat;
        this.noOfSeatAlotted = noOfSeatAlotted;
        this.noOfTickets = noOfTickets;
        this.seatAlloted = seatAlloted;
    }
}

class RailwaySys {
    static Scanner sc = new Scanner(System.in); // used to take input from console
    static ArrayList<Train> trains = new ArrayList<>(); // ArrayList to store Train objects
    static ArrayList<User> user = new ArrayList<>(); // ArrayList to store User objects
    static ArrayList<Admin> admin = new ArrayList<>(); // ArrayList to store Admin objects

    static void getAdmins() {
        File myFile = new File("admins.txt");
        try {
            Scanner fileinput = new Scanner(myFile);
            while (fileinput.hasNextLine()) {
                String line = fileinput.nextLine();
                String[] arrOfStr = line.split("-");

                Admin ad = new Admin(arrOfStr[0], arrOfStr[1]);
                admin.add(ad);
            }
            fileinput.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    static void storeAdmins() {
        try {
            FileWriter fileWriter = new FileWriter("admins.txt");
            for (Admin a : admin) {
                fileWriter.write(a.name + "-" + a.password + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void getUsers() {
        File myFile = new File("users.txt");
        try {
            Scanner fileinput = new Scanner(myFile);
            while (fileinput.hasNextLine()) {
                String line = fileinput.nextLine();
                String[] arrOfStr = line.split("-");

                User us = new User(arrOfStr[0], arrOfStr[1], arrOfStr[2]);
                user.add(us);
            }
            fileinput.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    static void storeUsers() {
        try {
            FileWriter fileWriter = new FileWriter("users.txt");
            for (User u : user) {
                fileWriter.write(u.name + "-" + u.password + "-" + u.phoneNo + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void getTrains() {
        File myFile = new File("trains.txt");
        try {
            Scanner fileinput = new Scanner(myFile);
            while (fileinput.hasNextLine()) {
                String line = fileinput.nextLine();
                String[] arrOfStr = line.split("-");

                String name = arrOfStr[0];
                String st = arrOfStr[1];
                String en = arrOfStr[2];
                int nS = Integer.parseInt(arrOfStr[3]);
                int nSA = Integer.parseInt(arrOfStr[4]);
                int nT = Integer.parseInt(arrOfStr[5]);
                int[] SA = new int[nS];

                String[] seats = arrOfStr[6].split(" ");

                for (int i = 0; i < seats.length; i++) {
                    SA[i] = Integer.parseInt(seats[i]);
                }

                Train tr = new Train(name, st, en, nS, nSA, nT, SA);
                trains.add(tr);
            }
            fileinput.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    static void storeTrains() {
        try {
            FileWriter fileWriter = new FileWriter("trains.txt");
            for (Train t : trains) {
                fileWriter.write(t.TrainName + "-" + t.startPoint + "-" + t.endPoint + "-" + t.noOfSeat + "-"
                        + t.noOfSeatAlotted + "-" + t.noOfTickets + "-");
                for (int i : t.seatAlloted) {
                    fileWriter.write(i + " ");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void adminLogin() {
        int ch = 0;
        while (true) {
            System.out.println("\033[H\033[2J");
            System.out.println("----- Welcome to Admin Login -----");
            System.out.println("1. Sign Up.");
            System.out.println("2. Sign In.");
            System.out.println("3. Exit.");
            System.out.println("Enter Choice : ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    final String authToken = "anish";
                    System.out.println("\nEnter Authorisation Token : ");
                    sc.nextLine();
                    String enteredAuthToken = sc.nextLine();

                    if (enteredAuthToken.compareTo(authToken) == 0)
                        adminSignUp();
                    else
                        System.out.println("\nInvalid Authorisation Token.");
                    System.out.println("\nPress enter to continue.");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    adminSignIn();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Input");
                    System.out.println("\nPress enter to continue.");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
            }
        }
    }

    static void adminSignUp() {
        System.out.println("\033[H\033[2J");
        System.out.println("----- Welcome to Admin Sign Up -----");
        System.out.print("Enter Your Username : ");
        String aName = sc.nextLine();
        boolean flag = true;

        for (Admin a : admin) {
            if (aName == a.name) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.print("Enter Your Password : ");
            String aPassword = sc.nextLine();

            Admin newAdmin = new Admin(aName, aPassword);
            admin.add(newAdmin);

            System.out.println("Account Created Successfully!");
            storeAdmins();
        } else
            System.out.println("Admin username is taken!");

        System.out.println("\nPress enter to continue.");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void adminSignIn() {
        System.out.println("\033[H\033[2J");
        System.out.println("----- Welcome to Admin Sign In -----");
        System.out.println("Enter Your Name : ");
        String aName = sc.next();
        System.out.println("Enter Your Password : ");
        String aPassword = sc.next();
        int pro = 0;
        for (Admin a : admin) {
            if (a.name.compareTo(aName) == 0 && a.password.compareTo(aPassword) == 0) {
                adminFunction();
                pro++;
                break;
            }
        }
        if (pro == 0) {
            System.out.println("Admin ID and Password Mismatch! \nRetry!");

            System.out.println("\nPress enter to continue.");
            try {
                System.in.read();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    static void adminFunction() {
        int adCh = 0;
        while (true) {
            System.out.println("\033[H\033[2J");
            System.out.println("----- Welcome to Admin Main Menu -----");
            System.out.println("1. View Users");
            System.out.println("2. Add Train");
            System.out.println("3. Remove Train");
            System.out.println("4. Edit Train Details");
            System.out.println("5. View Train Details");
            System.out.println("6. Exit");
            System.out.println("Enter Choice : ");
            adCh = sc.nextInt();

            switch (adCh) {
                case 1:
                    viewUsers();
                    break;
                case 2:
                    addTrain();
                    break;
                case 3:
                    remTrain();
                case 4:
                    editTrain();
                    break;
                case 5:
                    trainDetails();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid Input");
                    System.out.println("\nPress enter to continue.");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
            }
        }
    }

    static void viewUsers() {
        int i = 1;
        System.out.println("\033[H\033[2J");
        System.out.println("----- User List -----");
        System.out.println("Sno.\tUsername\tPhone Number");
        for (User us : user) {
            System.out.println(i + "\t" + us.name + "\t\t" + us.phoneNo);
        }
        System.out.println("Enter 0 to return or any number to remove user : ");
        int ch = sc.nextInt();

        if (ch == 0)
            return;
        else {
            if (0 < ch && ch <= user.size()) {
                user.remove(ch - 1);
                System.out.println("User removed from System.");
                storeUsers();
            } else {
                System.out.println("Invalid Input!");
            }
            System.out.println("\nPress enter to continue.");
            try {
                System.in.read();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    static void addTrain() {
        System.out.println("\033[H\033[2J");
        System.out.println("----- Welcome to Add Train Page -----");
        System.out.println("Enter Train Name : ");
        String tName = sc.next();
        System.out.println("Enter Train Boarding Station Name : ");
        String bName = sc.next();
        System.out.println("Enter Train Destination Station Name : ");
        String dName = sc.next();
        System.out.println("Enter No Of Seat Available : ");
        int noOfSeat = sc.nextInt();

        int[] seatAlloted = new int[noOfSeat];

        Train newTrain = new Train(tName, bName, dName, noOfSeat, 0, 0, seatAlloted);
        trains.add(newTrain);

        System.out.println("Train Added.\nPress enter to continue.");
        storeTrains();
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void remTrain() {
        System.out.println("\033[H\033[2J");
        System.out.println("----- Welcome to Remove Train Page -----");
        viewTrains();
        System.out.println("Enter Train to remove : ");
        int ch = sc.nextInt();

        if (0 < ch && ch < trains.size()) {
            trains.remove(ch - 1);
            System.out.println("Train Removed.");
            storeTrains();
        } else {
            System.out.println("Invalid Input!");
        }
        System.out.println("\nPress enter to continue.");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void viewTrains() {
        System.out.println("\033[H\033[2J");
        int i = 1;
        System.out.println("SNo\tTrain Name\tBoarding Station\tDestination Station\tAvailable Seats");
        for (Train t : trains) {
            System.out.println(i + "\t" + t.TrainName + "\t" + t.startPoint + "\t\t\t" + t.endPoint
                    + "\t\t\t" + (t.noOfSeat - t.noOfSeatAlotted));
            i++;
        }
    }

    static void editTrain() {
        System.out.println("\033[H\033[2J");
        System.out.println("----- Welcome to Edit Train Page -----");
        viewTrains();

        System.out.println("Enter Choice : ");
        int n = sc.nextInt();

        if (0 < n && n <= trains.size()) {

            System.out.println("1. Edit Train Name");
            System.out.println("2. Edit Train Boarding Station");
            System.out.println("3. Edit Train Destination Station");
            System.out.println("4. Edit Number of Seats");
            System.out.println("5. Exit");
            System.out.println("Enter Choice : ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Enter New Train Name : ");
                    String name = sc.nextLine();
                    trains.get(n - 1).TrainName = name;
                    System.out.println("Train Name Changed to " + name);
                    break;
                case 2:
                    System.out.println("Enter New Boarding Station : ");
                    String bdst = sc.nextLine();
                    trains.get(n - 1).startPoint = bdst;
                    System.out.println("Train Boarding Station Changed to " + bdst);
                    break;
                case 3:
                    System.out.println("Enter New Destination Station : ");
                    String dsst = sc.nextLine();
                    trains.get(n - 1).endPoint = dsst;
                    System.out.println("Train Destination Station Changed to " + dsst);
                    break;
                case 4:
                    System.out.println("Enter No Of Total Seats : ");
                    int noOfS = sc.nextInt();

                    int oldNoOfSeats = trains.get(n - 1).noOfSeat;

                    int[] temp = new int[oldNoOfSeats];

                    for (int i = 0; i < oldNoOfSeats; i++) {
                        temp[i] = trains.get(n - 1).seatAlloted[i];
                    }

                    trains.get(n - 1).noOfSeat = noOfS;
                    trains.get(n - 1).seatAlloted = new int[noOfS];

                    for (int i = 0; i < oldNoOfSeats; i++) {
                        trains.get(n - 1).seatAlloted[i] = temp[i];
                    }
                    System.out.println("Train Seats Changed to " + noOfS);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Input");
            }

            if (ch == 1 || ch == 2 || ch == 3 || ch == 4)
                storeTrains();

        } else
            System.out.println("Invalid Input!");

        System.out.println("\nPress enter to continue.");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void trainDetails() {
        System.out.println("\033[H\033[2J");
        System.out.println("----- Welcome to Train Details Page -----");
        viewTrains();
        System.out.println("Enter Choice : ");
        int ch = sc.nextInt();

        Train train = trains.get(ch - 1);

        System.out.println(String.format(
                "Train Name : %s\nTrain Boarding Station Name : %s" +
                        "\nTrain Destination Station Name : %s\nNo Of Seat : %s" +
                        "\nNo Of Seat Allotted : %s\nNo Of Tickets : %s\n",
                train.TrainName, train.startPoint, train.endPoint,
                train.noOfSeat, train.noOfSeatAlotted, train.noOfTickets));
        System.out.print("Seats Alloted: ");
        for (int i : train.seatAlloted) {
            System.out.print(i + " ");
        }
        System.out.println("\nPress enter to continue.");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void userLogin() {
        int usOp = 0;
        while (true) {
            System.out.println("\033[H\033[2J");
            System.out.println("----- Welcome to User Login -----");
            System.out.println("1.User Sign Up");
            System.out.println("2.User Sign In");
            System.out.println("3.Exit");
            System.out.println("Enter Choice : ");
            usOp = sc.nextInt();
            if (usOp == 1) {
                userSignUp();
            } else if (usOp == 2) {
                userSignIn();
            } else if (usOp == 3) {
                return;
            } else {
                System.out.println("Invalid Input!");
                System.out.println("\nPress enter to continue.");
                try {
                    System.in.read();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    static void userSignUp() {
        System.out.println("\033[H\033[2J");
        System.out.println("----- Welcome to User Sign Up -----");
        System.out.println("Enter Your Mobile No : ");
        sc.nextLine();
        String uPhoneNo = sc.nextLine();
        boolean flag = true;

        for (User u : user) {
            if (uPhoneNo.equals(u.phoneNo)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("Enter Your Name : ");
            String uName = sc.nextLine();
            System.out.println("Enter Your Password : ");
            String uPassword = sc.nextLine();

            User us = new User(uName, uPassword, uPhoneNo);
            user.add(us);

            System.out.println("Account Created Successfully!");
            storeUsers();
        } else
            System.out.println("This mobile number already has an account!");

        System.out.println("\nPress enter to continue.");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void userSignIn() {
        System.out.println("\033[H\033[2J");
        System.out.println("----- Welcome to User Sign In -----");
        System.out.println("Enter Your Phone Number : ");
        sc.nextLine();
        String uPhoneNo = sc.nextLine();
        System.out.println("Enter Your Password : ");
        String uPassword = sc.nextLine();
        int pro = 0;
        for (User u : user) {
            if (u.phoneNo.equals(uPhoneNo) && u.password.equals(uPassword)) {
                UserFunction();
                pro++;
                break;
            }
        }
        if (pro == 0) {
            System.out.println("User Phone Number and Password Mismatch! \nRetry!");
            System.out.println("\nPress enter to continue.");
            try {
                System.in.read();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    static void UserFunction() {
        int usCh = 0;
        while (true) {
            System.out.println("\033[H\033[2J");
            System.out.println("----- Welcome to User Main Menu -----");
            System.out.println("1.View Trains and Availability");
            System.out.println("2.Book Tickets");
            System.out.println("3.Ticket Cancellation");
            System.out.println("4.Exit");
            System.out.println("Enter Choice : ");
            usCh = sc.nextInt();
            if (usCh == 1) {
                viewTrains();
            } else if (usCh == 2) {
                viewTrains();
                bookTicket();
            } else if (usCh == 3) {
                viewTrains();
                ticketCancel();
            } else if (usCh == 4) {
                return;
            } else {
                System.out.println("Invalid Input");
                System.out.println("\nPress enter to continue.");
                try {
                    System.in.read();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    static void bookTicket() {
        System.out.println("----- Ticket Booking -----");
        System.out.println("Enter Train Number : ");
        int n = sc.nextInt();

        if (0 < n && n <= trains.size()) {

            if (trains.get(n - 1).noOfSeatAlotted == trains.get(n - 1).noOfSeat) {
                System.out.println("Sorry!! No tickets Available.");
                System.out.println("\nPress enter to continue.");
                try {
                    System.in.read();
                } catch (IOException e) {
                    System.out.println(e);
                }
                return;
            }

            System.out.println("Enter Number of Passengers: ");
            int noOfPassengers = sc.nextInt();

            int noOfSeatsAvailable = trains.get(n - 1).noOfSeat - trains.get(n - 1).noOfSeatAlotted;

            if (noOfPassengers <= noOfSeatsAvailable) {
                System.out.println(noOfPassengers + " tickets available.\n1. Continue 2. Exit\nEnter our Choice: ");
                int ch = sc.nextInt();

                if (ch == 1) {
                    allotTicket(n - 1, noOfPassengers);
                } else
                    return;
            } else {
                System.out.println("Only " + noOfSeatsAvailable
                        + " tickets available.\n 1. Continue 2. Exit\n Enter our Choice: ");
                int ch = sc.nextInt();

                if (ch == 1) {
                    allotTicket(n - 1, noOfSeatsAvailable);
                } else
                    return;
            }
        }
    }

    static void allotTicket(int trainNo, int noOfSeats) {
        ArrayList<Integer> seats = new ArrayList<>();
        int noOfSeatsBooked = 0;
        int ticketNo = ++trains.get(trainNo).noOfTickets;

        for (int i = 0; i < trains.get(trainNo).noOfSeat; i++) {
            if (trains.get(trainNo).seatAlloted[i] == 0) {
                trains.get(trainNo).seatAlloted[i] = ticketNo;
                seats.add(i + 1);
                trains.get(trainNo).noOfSeatAlotted++;
                noOfSeatsBooked++;
            }
            if (noOfSeatsBooked == noOfSeats)
                break;
        }

        System.out.println("Booking Complete.\n");
        System.out.println("Your Ticket Number is: " + ticketNo);
        System.out.println("Your seats are: " + seats);
        System.out.println("\nPress enter to continue.");
        storeTrains();
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void ticketCancel() {
        System.out.println("----- Ticket Cancelling -----");
        System.out.println("Enter Train Number : ");
        int n = sc.nextInt();
        int trainNo = n - 1;

        if (0 < n && n <= trains.size()) {
            ArrayList<Integer> seats = new ArrayList<>();
            System.out.println("Enter Ticket Number: ");
            int ticketNo = sc.nextInt();

            for (int i = 0; i < trains.get(trainNo).noOfSeat; i++) {
                if (trains.get(trainNo).seatAlloted[i] == ticketNo) {
                    seats.add(i + 1);
                }
            }
            if (seats.size() > 0) {
                System.out.println("Choose seat to cancel: " + seats);
                int seat = sc.nextInt();
                if (seats.indexOf(seat) == -1) {
                    System.out.println("Not a valid seat to cancel.");
                } else {
                    trains.get(trainNo).seatAlloted[seat - 1] = 0;
                    trains.get(trainNo).noOfSeatAlotted--;
                    System.out.println("Seat Cancelled.");
                    storeTrains();
                }
            } else {
                System.out.println("No tickets with this Number.");
            }

            System.out.println("\nPress enter to continue.");
            try {
                System.in.read();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        getAdmins();
        getUsers();
        getTrains();

        int ch = 0;
        while (ch != 3) {
            System.out.println("\033[H\033[2J");
            System.out.println("----- Welcome To Railway Reservation System -----");
            System.out.println("1.Admin Login");
            System.out.println("2.User Login");
            System.out.println("3.Exit");
            System.out.println("Enter Choice : ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    System.out.println("Thanks for Using!");
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.println("\nPress enter to continue.");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
            }
        }

        storeAdmins();
        storeUsers();
        storeTrains();
    }
}
