import java.util.Scanner;

class BillingRecordd {
    String billId;
    String customer;
    double amount;
    String date;
    BillingRecordd next;

    BillingRecordd(String billId, String customer, double amount, String date) {
        this.billId = billId;
        this.customer = customer;
        this.amount = amount;
        this.date = date;
        this.next = null;
    }
}

class BillingHistory {
    BillingRecordd head;
 
    public void addRecord(String billId, String customer, double amount, String date) {
        BillingRecordd newRecord = new BillingRecordd(billId, customer, amount, date);
        if (head == null) {
            head = newRecord;
        } else {
            BillingRecordd temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newRecord;
        }
        System.out.println("✔ Record added successfully.");
    }

    public void viewHistory() {
        if (head == null) {
            System.out.println("❗ No billing records found.");
            return;
        }
        BillingRecordd temp = head;
        System.out.println("\n--- Billing History ---");
        while (temp != null) {
            System.out.println("ID: " + temp.billId + ", Customer: " + temp.customer + 
                               ", Amount: " + temp.amount + ", Date: " + temp.date);
            temp = temp.next;
        }
    }

    public void deleteRecord(String billId) {
        BillingRecordd temp = head, prev = null;
        while (temp != null && !temp.billId.equals(billId)) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("❌ Record not found.");
            return;
        }

        if (prev == null)
            head = head.next;
        else
            prev.next = temp.next;

        System.out.println("✔ Record deleted successfully.");
    }
}

public class BillingApp {
    public static void main(String[] args) {
        BillingHistory history = new BillingHistory();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Billing History Menu ---");
            System.out.println("1. Add Billing Record");
            System.out.println("2. View Billing History");
            System.out.println("3. Delete Billing Record");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Bill ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    history.addRecord(id, name, amount, date);
                    break;
                case 2:
                    history.viewHistory();
                    break;
                case 3:
                    System.out.print("Enter Bill ID to delete: ");
                    String delId = scanner.nextLine();
                    history.deleteRecord(delId);
                    break;
                case 4:
                    System.out.println("Exiting... ✅");
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
 
    

