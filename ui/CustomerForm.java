/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.ui;

import javax.swing.*;
import java.awt.*;

import carrental.dao.CustomerDAO;
import carrental.model.Customer;
/**
 *
 * @author Janice
 */
public class CustomerForm extends JPanel {
    private JTextField txtName, txtContact, txtEmail, txtLicense;
    private CustomerDAO customerDAO;
    private CustomerUI parentUI;
    private Customer customer; // null = new customer

    public CustomerForm(CustomerDAO customerDAO, CustomerUI parentUI, Customer customer) {
        this.customerDAO = customerDAO;
        this.parentUI = parentUI;
        this.customer = customer;

        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Name:"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Contact:"));
        txtContact = new JTextField();
        add(txtContact);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("License:"));
        txtLicense = new JTextField();
        add(txtLicense);

        JButton btnSave = new JButton(customer == null ? "Add" : "Update");
        JButton btnCancel = new JButton("Cancel");

        add(btnSave);
        add(btnCancel);

        if (customer != null) {
            txtName.setText(customer.getCustomerName());
            txtContact.setText(customer.getContactNumber());
            txtEmail.setText(customer.getEmail());
            txtLicense.setText(customer.getLicenseNumber());
        }

        // Actions
        btnSave.addActionListener(e -> saveCustomer());
        btnCancel.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void saveCustomer() {
        if (txtName.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Email are required!");
            return;
        }

        if (customer == null) { // ADD
            customer = new Customer();
            customer.setCustomerName(txtName.getText());
            customer.setContactNumber(txtContact.getText());
            customer.setEmail(txtEmail.getText());
            customer.setLicenseNumber(txtLicense.getText());
            customer.setCreatedBy(1); // TODO: logged-in user
            customerDAO.addCustomer(customer);
        } else { // UPDATE
            customer.setCustomerName(txtName.getText());
            customer.setContactNumber(txtContact.getText());
            customer.setEmail(txtEmail.getText());
            customer.setLicenseNumber(txtLicense.getText());
            customer.setLastModifiedBy(1); // TODO: logged-in user
            customerDAO.updateCustomer(customer);
        }

        parentUI.refreshTable();
        SwingUtilities.getWindowAncestor(this).dispose();
    }
}
