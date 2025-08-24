/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import carrental.dao.CustomerDAO;
import carrental.model.Customer;
/**
 *
 * @author Janice
 */
public class CustomerUI extends JPanel {
    private JTable tblCustomers;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private CustomerDAO customerDAO;

    public CustomerUI() {
        this.customerDAO = new CustomerDAO();
        
        setLayout(new BorderLayout());

        // --- North Panel (Title + Search) ---
        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("Customer Management", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        northPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        northPanel.add(searchPanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);

        // --- Table ---
        String[] columnNames = {"ID", "Name", "Contact", "Email", "License"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblCustomers = new JTable(tableModel);
        add(new JScrollPane(tblCustomers), BorderLayout.CENTER);

        // --- Buttons ---
        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        add(btnPanel, BorderLayout.SOUTH);

        // Load customers
        loadCustomers();

        // --- Button Actions ---
        btnSearch.addActionListener(e -> searchCustomers());
        btnAdd.addActionListener(e -> openForm(null)); // null = new record
        btnUpdate.addActionListener(e -> {
            int row = tblCustomers.getSelectedRow();
            if (row >= 0) {
                int customerId = (int) tableModel.getValueAt(row, 0);
                Customer customer = customerDAO.getCustomerById(customerId);
                openForm(customer);
            } else {
                JOptionPane.showMessageDialog(this, "Select a customer to update.");
            }
        });
        btnDelete.addActionListener(e -> {
            int row = tblCustomers.getSelectedRow();
            if (row >= 0) {
                int customerId = (int) tableModel.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure to delete this customer?", "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    customerDAO.deleteCustomer(customerId);
                    loadCustomers();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a customer to delete.");
            }
        });
    }

    // === Load Customers into Table ===
    private void loadCustomers() {
        tableModel.setRowCount(0);
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer c : customers) {
            tableModel.addRow(new Object[]{
                    c.getCustomerID(),
                    c.getCustomerName(),
                    c.getContactNumber(),
                    c.getEmail(),
                    c.getLicenseNumber()
            });
        }
    }

    // === Search Customers by name/email ===
    private void searchCustomers() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        tableModel.setRowCount(0);
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer c : customers) {
            if (c.getCustomerName().toLowerCase().contains(keyword)
                    || c.getEmail().toLowerCase().contains(keyword)) {
                tableModel.addRow(new Object[]{
                        c.getCustomerID(),
                        c.getCustomerName(),
                        c.getContactNumber(),
                        c.getEmail(),
                        c.getLicenseNumber()
                });
            }
        }
    }

    // === Open Add/Update Form ===
    private void openForm(Customer customer) {
        JFrame frame = new JFrame(customer == null ? "Add Customer" : "Update Customer");
        frame.setContentPane(new CustomerForm(customerDAO, this, customer));
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    // Refresh list after Add/Update
    public void refreshTable() {
        loadCustomers();
    }
}