package Parking_Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestPanel extends JPanel {
    private JComboBox<String> vehicleTypeComboBox;
    private JTextField timeTextField;
    private JTextField cashTextField;
    private JLabel resultLabel;

    public GuestPanel() {
        setLayout(new GridLayout(6, 2, 10, 10));

        // Membuat komponen GUI
        JLabel vehicleTypeLabel = new JLabel("What type of vehicle are you driving? (Car / Truck/ Motorcycle)");
        vehicleTypeComboBox = new JComboBox<>(new String[]{"Car", "Truck", "Motorcycle"});

        JLabel timeLabel = new JLabel("Enter the amount of time of your parked vehicle.. (hours)");
        timeTextField = new JTextField();

        JLabel transactionLabel = new JLabel("Please pay using cash (Type in 'Cash')");
        cashTextField = new JTextField();

        JButton calculateButton = new JButton("Calculate Fee");
        resultLabel = new JLabel();

        // Menambahkan komponen ke panel
        add(vehicleTypeLabel);
        add(vehicleTypeComboBox);
        add(timeLabel);
        add(timeTextField);
        add(transactionLabel);
        add(cashTextField);
        add(new JLabel()); // Spacer
        add(calculateButton);
        add(new JLabel()); // Spacer
        add(resultLabel);

        // Menambahkan action listener untuk tombol
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCalculateFee();
            }
        });
    }

    private void handleCalculateFee() {
        try {
            String vehicle = (String) vehicleTypeComboBox.getSelectedItem();
            double time = Double.parseDouble(timeTextField.getText());
            double rate = 0;

            switch (vehicle.toLowerCase()) {
                case "car":
                    rate = 3.0;
                    break;
                case "truck":
                    rate = 4.0;
                    break;
                case "motorcycle":
                    rate = 2.0;
                    break;
                default:
                    resultLabel.setText("Invalid vehicle type");
                    return;
            }

            double parkingFee = rate * time;
            double givenCash = Double.parseDouble(cashTextField.getText());

            if (givenCash < parkingFee) {
                resultLabel.setText("Insufficient cash, please input an enough amount of cash");
            } else if (givenCash == parkingFee) {
                resultLabel.setText("The payment was a success! (no change needed)");
            } else {
                double change = givenCash - parkingFee;
                resultLabel.setText("The payment was a success!, here is your change: $" + change);
            }

        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers for time and cash.");
        }
    }
}
