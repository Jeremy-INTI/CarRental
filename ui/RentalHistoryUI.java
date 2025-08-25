/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.ui;

import carrental.model.*;
import carrental.dao.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Janice
 */
public class RentalHistoryUI extends JPanel {
    private JTable tblRentals;
    private DefaultTableModel tableModel;
    private CarRentalDAO carRentalDAO;    
    private CarDAO carDAO;
    private Customer customer;    

    public RentalHistoryUI(Customer customer){
        this.carRentalDAO = new CarRentalDAO();        
        this.carDAO = new CarDAO();
        this.customer = customer;

        setLayout(new BorderLayout());

        // --- Title ---
        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("Rental History for: " + this.customer.getCustomerName(), SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        northPanel.add(lblTitle, BorderLayout.NORTH);
        add(northPanel, BorderLayout.NORTH);

        // --- Table ---
        String[] columnNames = {"ID", "Car Model", "License Plate", "Start Date", "End Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblRentals = new JTable(tableModel);
        add(new JScrollPane(tblRentals), BorderLayout.CENTER);
        
        // --- Close Button ---
        JPanel btnPanel = new JPanel();
        JButton btnClose = new JButton("Close");
        btnPanel.add(btnClose);
        add(btnPanel, BorderLayout.SOUTH);

        btnClose.addActionListener(e -> {
            // Find parent frame and dispose it
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.dispose();
            }
        });

        // --- Load data ---
        loadRentalByCustomer();
    }

    private void loadRentalByCustomer() {
        tableModel.setRowCount(0); // clear existing rows
        List<CarRental> rentals = carRentalDAO.getRentalsByCustomer(this.customer.getCustomerID());

        for (CarRental rental : rentals) {
            Car car = carDAO.getCarById(rental.getCarID());
            if (car != null) {
                tableModel.addRow(new Object[]{
                        rental.getRentalID(),
                        car.getMake() + " " + car.getModel(),
                        car.getLicensePlate(),
                        rental.getStartDate(),
                        rental.getEndDate()
                });
            }
        }
    }
}