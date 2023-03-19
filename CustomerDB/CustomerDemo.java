import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDemo {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int op=0;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CustomerDB","root","tiger");
            do{
                switch (op){
                    case 1:delete(con);
                            break;
                    case 2:deleteMultiple(con);
                            break;
                    case 3:display(con);
                            break;
                    case 4:displayAll(con);
                            break;
                    default:System.out.println("Enter Valid Input");
                }
                System.out.println("Enter your choice");
                System.out.println("1.Delete\n2.DeleteMultiple\n3.Display\n4.DisplayAll\n ");
                op= sc.nextInt();
            }while(op<=4);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private static void delete(Connection con) throws SQLException {
        System.out.println("Enter Employee ID for deletion");
        int customerId = sc.nextInt();

        PreparedStatement pstmt = con.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstmt.setInt(1,customerId);

        int rowsAffected = pstmt.executeUpdate();
        if(rowsAffected>0) {
            System.out.println("Deleted Successfully!");
        }
        else{
            System.out.println("No matching found with id"+customerId);
        }
    }
    private static void deleteMultiple(Connection con) throws SQLException {
        System.out.println("How many employees do you want to delete");
        int numCustomers = sc.nextInt();
        sc.nextLine();

        List<Integer> CustomerIds = new ArrayList<>();
        for (int i = 0; i < numCustomers; i++) {
            System.out.print("Enter the ID: ");
            int customerId = sc.nextInt();
            CustomerIds.add(customerId);
        }

        PreparedStatement pstmt = con.prepareStatement("DELETE FROM Customer WHERE id = ?");
        for (int customerId : CustomerIds) {
            pstmt.setInt(1, customerId);
            pstmt.addBatch();
        }

        int[] rowsAffected = pstmt.executeBatch();
        int totalRowsAffected = Arrays.stream(rowsAffected).sum();

        if (totalRowsAffected == numCustomers) {
            System.out.println(numCustomers + " customers deleted.");
        } else {
            System.out.println("Failed to delete");
        }
    }
    private static void display(Connection con) throws SQLException {
        System.out.println("Enter Employee ID to retrieve");
        int customerId= sc.nextInt();
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstmt.setInt(1,customerId);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            System.out.println(rs.getInt(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getString(4) + "|" + rs.getInt(5));
        }else{
            System.out.println("Customer not found");
        }
    }

    private static void displayAll(Connection con) throws SQLException {
        ArrayList<Customer> customerList = new ArrayList<>();
        PreparedStatement psmt = con.prepareStatement( "SELECT * FROM Customer");
        ResultSet rs = psmt.executeQuery();
        while (rs.next()) {
            int cid = rs.getInt("id");
            String name = rs.getString("cname");
            String cphn = rs.getString("mobile");
            String cmail=rs.getString("email");
            int citems=rs.getInt("itemspurchased");
            customerList.add(new Customer(cid,name,cphn,cmail,citems));
        }
        if (customerList.isEmpty()) {
            System.out.println("No employees found.");
        }else {
            for (Customer customer: customerList) {
                System.out.println(customer.getId() + " | " + customer.getName() + " | " + customer.getPhn() + " | " + customer.getMail() + " | " + customer.getItems());
            }
        }
    }
}