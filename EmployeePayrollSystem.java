
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

class Employee {
    int id;
    String name, department;
    double salary;

    // Default Constructor
    Employee() {
        id = 0;
        name = "";
        salary = 0;
        department = "";
    }

    // Parameterized Constructor
    Employee(int i, String n, double s, String d) {
        id = i;
        name = n;
        salary = s;
        department = d;
    }

    // Copy Constructor
    Employee(Employee e) {
        id = e.id;
        name = e.name;
        salary = e.salary;
        department = e.department;
    }

    // Net Salary
    double netSalary() {
        return salary + salary * 0.20 - salary * 0.05;
    }

    // Display Details
    public String toString() {
        return "ID: " + id
            + "\nName: " + name
            + "\nDepartment: " + department
            + "\nBasic Salary: Rs. " + salary
            + "\nNet Salary: Rs. " + netSalary() + "\n\n";
    }
}

public class EmployeePayrollSystem extends JFrame {

    JTextField id = new JTextField();
    JTextField name = new JTextField();
    JTextField salary = new JTextField();

    JComboBox<String> dept = new JComboBox<>(
        new String[]{"IT", "HR", "Sales", "Finance"}
    );

    JTextArea output = new JTextArea();
    ArrayList<Employee> list = new ArrayList<>();

    EmployeePayrollSystem() {
        setTitle("Employee Payroll System");
        setSize(200, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

panel.add(new JLabel("Employee ID:"));
panel.add(id);

panel.add(new JLabel("Employee Name:"));
panel.add(name);

panel.add(new JLabel("Basic Salary:"));
panel.add(salary);

panel.add(new JLabel("Department:"));
panel.add(dept);

JButton b1 = new JButton("Add Employee");
JButton b2 = new JButton("Save");
JButton b3 = new JButton("Load");
JButton b4 = new JButton("View All");

JPanel buttons = new JPanel(new GridLayout(1, 4, 5, 5));
buttons.add(b1);
buttons.add(b2);
buttons.add(b3);
buttons.add(b4);

panel.add(new JLabel("Actions:"));
panel.add(buttons);

output.setEditable(false);
output.setFont(new Font("Arial", Font.PLAIN, 12));

add(panel, BorderLayout.NORTH);
add(new JScrollPane(output), BorderLayout.CENTER);

b1.addActionListener(e -> addEmployee());
b2.addActionListener(e -> saveData());
b3.addActionListener(e -> loadData());
b4.addActionListener(e -> viewData());

setSize(500, 400);
setLocationRelativeTo(null);
setVisible(true);
	}

    void addEmployee() {
        try {
            int i = Integer.parseInt(id.getText());
            String n = name.getText();
            double s = Double.parseDouble(salary.getText());
            String d = (String) dept.getSelectedItem();

            Employee e = new Employee(i, n, s, d);
            list.add(new Employee(e));

            JOptionPane.showMessageDialog(this, "Employee Added!");

            id.setText("");
            name.setText("");
            salary.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter Valid Details!");
        }
    }

    void saveData() {
        try {
            FileWriter fw = new FileWriter("employees.txt");

            for (Employee e : list) {
                fw.write(e.id + "," + e.name + ","
                    + e.salary + "," + e.department + "\n");
            }

            fw.close();
            JOptionPane.showMessageDialog(this, "Data Saved!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Saving Data!");
        }
    }

    void loadData() {
        try {
            BufferedReader br = new BufferedReader(
                new FileReader("employees.txt"));

            list.clear();
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Employee e = new Employee(
                    Integer.parseInt(data[0]),
                    data[1],
                    Double.parseDouble(data[2]),
                    data[3]
                );

                list.add(e);
            }

            br.close();
            JOptionPane.showMessageDialog(this, "Data Loaded!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Loading Data!");
        }
    }

    void viewData() {
        output.setText("");

        for (Employee e : list) {
            output.append(e.toString());
        }
    }

    public static void main(String[] args) {
        new EmployeePayrollSystem();
    }
}
