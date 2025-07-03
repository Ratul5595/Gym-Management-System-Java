import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class GymGUI extends JFrame {
    private ArrayList<GymMember> membersList = new ArrayList<>();

    // Text fields
    private JTextField idField, nameField, locationField, phoneField, emailField, 
    referralSourceField, paidAmountField, removalReasonField, trainerNameField;

    // Combo boxes
    private JComboBox<String> planComboBox;
    private JComboBox<String> dayComboBox, monthComboBox, yearComboBox;
    private JComboBox<String> startDayComboBox, startMonthComboBox, startYearComboBox;

    // Radio buttons
    private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    private ButtonGroup genderGroup;

    // Non-editable fields
    private JTextField regularPlanPriceField, premiumPlanPriceField, discountAmountField;

    // Buttons
    private JButton addRegularButton, addPremiumButton, activateButton, deactivateButton,
    markAttendanceButton, upgradePlanButton, calculateDiscountButton,
    revertRegularButton, revertPremiumButton, payDueButton, displayButton,
    clearButton, saveToFileButton, readFromFileButton;

    public GymGUI() {
        // Set up frame
        setTitle("Gym Management System");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10));

        // Initialize components
        initializeComponents();

        // Add components to frame
        addComponentsToFrame();
    }

    private void initializeComponents() {
        // Text Fields
        idField = new JTextField();
        nameField = new JTextField();
        locationField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        referralSourceField = new JTextField();
        paidAmountField = new JTextField();
        removalReasonField = new JTextField();
        trainerNameField = new JTextField();

        // Non-editable price fields
        regularPlanPriceField = new JTextField("6500");
        premiumPlanPriceField = new JTextField("50000");
        discountAmountField = new JTextField("0");
        regularPlanPriceField.setEditable(false);
        premiumPlanPriceField.setEditable(false);
        discountAmountField.setEditable(false);

        // Date combo boxes
        String[] days = new String[31];
        String[] months = {"January", "February", "March", "April", "May", "June", 
                "July", "August", "September", "October", "November", "December"};
        String[] years = new String[100];

        for (int i = 0; i < 31; i++) days[i] = String.valueOf(i+1);
        for (int i = 0; i < 100; i++) years[i] = String.valueOf(2023 - i);

        dayComboBox = new JComboBox<>(days);
        monthComboBox = new JComboBox<>(months);
        yearComboBox = new JComboBox<>(years);

        startDayComboBox = new JComboBox<>(days);
        startMonthComboBox = new JComboBox<>(months);
        startYearComboBox = new JComboBox<>(years);

        // Gender radio buttons
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        otherRadioButton = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);
        maleRadioButton.setSelected(true);

        // Plan ComboBox for Regular Members
        planComboBox = new JComboBox<>(new String[]{"Basic", "Standard", "Deluxe"});

        // Buttons
        addRegularButton = new JButton("Add Regular Member");
        addPremiumButton = new JButton("Add Premium Member");
        activateButton = new JButton("Activate Membership");
        deactivateButton = new JButton("Deactivate Membership");
        markAttendanceButton = new JButton("Mark Attendance");
        upgradePlanButton = new JButton("Upgrade Plan");
        calculateDiscountButton = new JButton("Calculate Discount");
        revertRegularButton = new JButton("Revert Regular Member");
        revertPremiumButton = new JButton("Revert Premium Member");
        payDueButton = new JButton("Pay Due Amount");
        displayButton = new JButton("Display Members");
        clearButton = new JButton("Clear Fields");
        saveToFileButton = new JButton("Save to File");
        readFromFileButton = new JButton("Read from File");

        // Add action listeners
        addRegularButton.addActionListener(new AddRegularListener());
        addPremiumButton.addActionListener(new AddPremiumListener());
        activateButton.addActionListener(new ActivateListener());
        deactivateButton.addActionListener(new DeactivateListener());
        markAttendanceButton.addActionListener(new MarkAttendanceListener());
        upgradePlanButton.addActionListener(new UpgradePlanListener());
        calculateDiscountButton.addActionListener(new CalculateDiscountListener());
        revertRegularButton.addActionListener(new RevertRegularListener());
        revertPremiumButton.addActionListener(new RevertPremiumListener());
        payDueButton.addActionListener(new PayDueListener());
        displayButton.addActionListener(new DisplayListener());
        clearButton.addActionListener(new ClearListener());
        saveToFileButton.addActionListener(new SaveListener());
        readFromFileButton.addActionListener(new ReadListener());
    }

    private void addComponentsToFrame() {
        // Add components to frame
        add(new JLabel("Member ID:"));
        add(idField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Location:"));
        add(locationField);
        add(new JLabel("Phone:"));
        add(phoneField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.add(otherRadioButton);
        add(genderPanel);

        add(new JLabel("Date of Birth:"));
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.add(dayComboBox);
        dobPanel.add(monthComboBox);
        dobPanel.add(yearComboBox);
        add(dobPanel);

        add(new JLabel("Membership Start Date:"));
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startDatePanel.add(startDayComboBox);
        startDatePanel.add(startMonthComboBox);
        startDatePanel.add(startYearComboBox);
        add(startDatePanel);

        add(new JLabel("Referral Source:"));
        add(referralSourceField);
        add(new JLabel("Paid Amount:"));
        add(paidAmountField);
        add(new JLabel("Removal Reason:"));
        add(removalReasonField);
        add(new JLabel("Trainer's Name:"));
        add(trainerNameField);
        add(new JLabel("Plan:"));
        add(planComboBox);
        add(new JLabel("Regular Plan Price:"));
        add(regularPlanPriceField);
        add(new JLabel("Premium Charge:"));
        add(premiumPlanPriceField);
        add(new JLabel("Discount Amount:"));
        add(discountAmountField);

        // Add buttons
        add(addRegularButton);
        add(addPremiumButton);
        add(activateButton);
        add(deactivateButton);
        add(markAttendanceButton);
        add(upgradePlanButton);
        add(calculateDiscountButton);
        add(revertRegularButton);
        add(revertPremiumButton);
        add(payDueButton);
        add(displayButton);
        add(clearButton);
        add(saveToFileButton);
        add(readFromFileButton);
    }

    private GymMember findMemberById(int id) {
        for (GymMember member : membersList) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    class AddRegularListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String location = locationField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                if(id<0)
                {
                    JOptionPane.showMessageDialog(GymGUI.this,"This Id is not applicable","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String gender = "";
                if (maleRadioButton.isSelected()) gender = "Male";
                else if (femaleRadioButton.isSelected()) gender = "Female";
                else if (otherRadioButton.isSelected()) gender = "Other";

                // Get dates from combo boxes
                String day = (String) dayComboBox.getSelectedItem();
                String month = (String) monthComboBox.getSelectedItem();
                String year = (String) yearComboBox.getSelectedItem();
                String dob = year + "-" + (monthComboBox.getSelectedIndex()+1) + "-" + day;

                String startDay = (String) startDayComboBox.getSelectedItem();
                String startMonth = (String) startMonthComboBox.getSelectedItem();
                String startYear = (String) startYearComboBox.getSelectedItem();
                String startDate = startYear + "-" + (startMonthComboBox.getSelectedIndex()+1) + "-" + startDay;

                String referral = referralSourceField.getText();

                // Check for duplicate ID
                if (findMemberById(id) != null) {
                    JOptionPane.showMessageDialog(GymGUI.this, "Member ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                RegularMember newMember = new RegularMember(id, name, location, phone, email, 
                        gender, dob, startDate, referral);
                membersList.add(newMember);
                JOptionPane.showMessageDialog(GymGUI.this, "Regular member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Invalid ID format! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class AddPremiumListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String location = locationField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                String gender = "";
                if (maleRadioButton.isSelected()) gender = "Male";
                else if (femaleRadioButton.isSelected()) gender = "Female";
                else if (otherRadioButton.isSelected()) gender = "Other";

                // Get dates from combo boxes
                String day = (String) dayComboBox.getSelectedItem();
                String month = (String) monthComboBox.getSelectedItem();
                String year = (String) yearComboBox.getSelectedItem();
                String dob = year + "-" + (monthComboBox.getSelectedIndex()+1) + "-" + day;

                String startDay = (String) startDayComboBox.getSelectedItem();
                String startMonth = (String) startMonthComboBox.getSelectedItem();
                String startYear = (String) startYearComboBox.getSelectedItem();
                String startDate = startYear + "-" + (startMonthComboBox.getSelectedIndex()+1) + "-" + startDay;

                String trainer = trainerNameField.getText();

                // Check for duplicate ID
                if (findMemberById(id) != null) {
                    JOptionPane.showMessageDialog(GymGUI.this, "Member ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                PremiumMember newMember = new PremiumMember(id, name, location, phone, email, 
                        gender, dob, startDate, trainer);
                membersList.add(newMember);
                JOptionPane.showMessageDialog(GymGUI.this, "Premium member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Invalid ID format! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class ActivateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                GymMember member = findMemberById(id);

                if (member != null) {
                    member.activateMembership();
                    JOptionPane.showMessageDialog(GymGUI.this, "Membership activated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(GymGUI.this, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Invalid ID format! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class DeactivateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                GymMember member = findMemberById(id);

                if (member != null) {
                    member.deactivateMembership();
                    JOptionPane.showMessageDialog(GymGUI.this, "Membership deactivated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(GymGUI.this, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Invalid ID format! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class MarkAttendanceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                GymMember member = findMemberById(id);

                if (member != null) {
                    if (member.isActiveStatus()) {
                        member.markAttendance();
                        JOptionPane.showMessageDialog(GymGUI.this, "Attendance marked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(GymGUI.this, "Cannot mark attendance - membership is inactive!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(GymGUI.this, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Invalid ID format! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class UpgradePlanListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                GymMember member = findMemberById(id);

                if (member != null && member instanceof RegularMember) {
                    RegularMember regularMember = (RegularMember) member;
                    if (regularMember.isActiveStatus()) {
                        String newPlan = (String) planComboBox.getSelectedItem();
                        String result = regularMember.upgradePlan(newPlan);
                        JOptionPane.showMessageDialog(GymGUI.this, result, "Plan Upgrade", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(GymGUI.this, "Cannot upgrade plan - membership is inactive!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (member == null) {
                    JOptionPane.showMessageDialog(GymGUI.this, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(GymGUI.this, "Only regular members can upgrade plans!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Invalid ID format! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class CalculateDiscountListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                GymMember member = findMemberById(id);

                if (member != null && member instanceof PremiumMember) {
                    PremiumMember premiumMember = (PremiumMember) member;
                    premiumMember.calculateDiscount();
                    discountAmountField.setText(String.valueOf(premiumMember.getDiscountAmount()));
                    JOptionPane.showMessageDialog(GymGUI.this, "Discount calculated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (member == null) {
                    JOptionPane.showMessageDialog(GymGUI.this, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(GymGUI.this, "Only premium members can calculate discounts!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Invalid ID format! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class RevertRegularListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                GymMember member = findMemberById(id);
                String reason = removalReasonField.getText();

                if (member != null && member instanceof RegularMember) {
                    RegularMember regularMember = (RegularMember) member;
                    regularMember.revertRegularMember(reason);
                    membersList.remove(member);
                    JOptionPane.showMessageDialog(GymGUI.this, "Regular member reverted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (member == null) {
                    JOptionPane.showMessageDialog(GymGUI.this, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(GymGUI.this, "Only regular members can be reverted with this button!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Invalid ID format! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class RevertPremiumListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                GymMember member = findMemberById(id);

                if (member != null && member instanceof PremiumMember) {
                    PremiumMember premiumMember = (PremiumMember) member;
                    premiumMember.revertPremiumMember();
                    membersList.remove(member);
                    JOptionPane.showMessageDialog(GymGUI.this, "Premium member reverted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (member == null) {
                    JOptionPane.showMessageDialog(GymGUI.this, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(GymGUI.this, "Only premium members can be reverted with this button!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Invalid ID format! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class PayDueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                double amount = Double.parseDouble(paidAmountField.getText());
                GymMember member = findMemberById(id);

                if (member != null && member instanceof PremiumMember) {
                    PremiumMember premiumMember = (PremiumMember) member;
                    String result = premiumMember.payDueAmount(amount);
                    JOptionPane.showMessageDialog(GymGUI.this, result, "Payment", JOptionPane.INFORMATION_MESSAGE);
                } else if (member == null) {
                    JOptionPane.showMessageDialog(GymGUI.this, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(GymGUI.this, "Only premium members can make payments!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Invalid amount format! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class DisplayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (membersList.isEmpty()) {
                JOptionPane.showMessageDialog(GymGUI.this, "No members to display!", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JFrame displayFrame = new JFrame("Member Details");
            displayFrame.setSize(1000, 600);
            displayFrame.setLocationRelativeTo(GymGUI.this);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-5s %-20s %-15s %-10s %-10s %-15s %-15s %-10s %-10s\n",
                    "ID", "Name", "Type", "Plan", "Status", "Attendance", "Loyalty Pts", "Paid", "Trainer"));
            sb.append("------------------------------------------------------------------------------------------------\n");

            for (GymMember member : membersList) {
                if (member instanceof RegularMember) {
                    RegularMember rm = (RegularMember) member;
                    sb.append(String.format("%-5d %-20s %-15s %-10s %-10s %-15d %-15.1f %-10s %-10s\n",
                            rm.getId(), rm.getName(), "Regular", rm.getPlan(), 
                            rm.isActiveStatus() ? "Active" : "Inactive",
                            rm.getAttendance(), rm.getLoyaltyPoints(), "N/A", "N/A"));
                } else if (member instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) member;
                    sb.append(String.format("%-5d %-20s %-15s %-10s %-10s %-15d %-15.1f %-10.2f %-10s\n",
                            pm.getId(), pm.getName(), "Premium", "Premium", 
                            pm.isActiveStatus() ? "Active" : "Inactive",
                            pm.getAttendance(), pm.getLoyaltyPoints(), 
                            pm.getPaidAmount(), pm.getPersonalTrainer()));
                }
            }

            textArea.setText(sb.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            displayFrame.add(scrollPane);
            displayFrame.setVisible(true);
        }
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            idField.setText("");
            nameField.setText("");
            locationField.setText("");
            phoneField.setText("");
            emailField.setText("");
            referralSourceField.setText("");
            paidAmountField.setText("");
            removalReasonField.setText("");
            trainerNameField.setText("");
            maleRadioButton.setSelected(true);
            planComboBox.setSelectedIndex(0);
            discountAmountField.setText("0");

            // Reset date combo boxes
            dayComboBox.setSelectedIndex(0);
            monthComboBox.setSelectedIndex(0);
            yearComboBox.setSelectedIndex(0);
            startDayComboBox.setSelectedIndex(0);
            startMonthComboBox.setSelectedIndex(0);
            startYearComboBox.setSelectedIndex(0);
        }
    }

    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("MemberDetails.txt"))) {
                writer.println(String.format("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %-15s %-10s %-15s %-15s %-15s", 
                        "ID", "Name", "Location", "Phone", "Email", "Membership Start", "Plan", "Price", 
                        "Attendance", "Loyalty Points", "Active", "Full Payment", "Discount", "Net Paid"));

                for (GymMember member : membersList) {
                    if (member instanceof RegularMember) {
                        RegularMember rm = (RegularMember) member;
                        writer.println(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %-15.2f %-10s %-15s %-15s %-15s", 
                                rm.getId(), rm.getName(), rm.getLocation(), rm.getPhone(), rm.getEmail(), 
                                rm.getMembershipStartDate(), rm.getPlan(), rm.getPrice(), rm.getAttendance(), 
                                rm.getLoyaltyPoints(), rm.isActiveStatus() ? "Yes" : "No", "N/A", "N/A", "N/A"));
                    } else if (member instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) member;
                        writer.println(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %-15.2f %-10s %-10s %-15.2f %-15.2f", 
                                pm.getId(), pm.getName(), pm.getLocation(), pm.getPhone(), pm.getEmail(), 
                                pm.getMembershipStartDate(), "Premium", pm.getPremiumCharge(), pm.getAttendance(), 
                                pm.getLoyaltyPoints(), pm.isActiveStatus() ? "Yes" : "No", 
                                pm.isFullPayment() ? "Yes" : "No", pm.getDiscountAmount(), 
                                pm.getPaidAmount()));
                    }
                }
                JOptionPane.showMessageDialog(GymGUI.this, "Member details saved to file successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Error saving to file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class ReadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try (BufferedReader reader = new BufferedReader(new FileReader("MemberDetails.txt"))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                JTextArea textArea = new JTextArea(content.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(800, 600));

                JFrame readFrame = new JFrame("File Contents");
                readFrame.setSize(900, 700);
                readFrame.setLocationRelativeTo(GymGUI.this);
                readFrame.add(scrollPane);
                readFrame.setVisible(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(GymGUI.this, "Error reading from file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                    GymGUI gui = new GymGUI();
                    gui.setVisible(true);
            });
    }
}