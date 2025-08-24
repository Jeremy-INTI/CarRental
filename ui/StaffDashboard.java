package carrental.ui;

import carrental.model.*;
import carrental.dao.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StaffDashboard extends JFrame {
    private JLabel lblUsername;
    private JLabel lblRole;
    private JPanel menuPanel;      // Left side menu
    private JPanel contentPanel;   // Right side dynamic content area
    private CardLayout cardLayout; // For swapping screens

    public StaffDashboard(User user) {
        // Frame setup
        setTitle("Car Rental System - Dashboard");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // === Top Panel (User Info Bar) ===
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(45, 52, 54));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        lblUsername = new JLabel("" + user.getUsername());
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUsername.setForeground(Color.WHITE);

        lblRole = new JLabel("Role: " + user.getRole());
        lblRole.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblRole.setForeground(new Color(116, 185, 255));

        JPanel userInfoPanel = new JPanel(new GridLayout(2, 1));
        userInfoPanel.setBackground(new Color(45, 52, 54));
        userInfoPanel.add(lblUsername);
        userInfoPanel.add(lblRole);

        topPanel.add(userInfoPanel, BorderLayout.EAST);

        // === Menu Panel (Modules on Left) ===
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1, 10, 10));
        menuPanel.setBackground(new Color(223, 230, 233));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // === Content Area (Right side) ===
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Default welcome screen
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeMsg = new JLabel("Welcome to Car Rental System!", SwingConstants.CENTER);
        welcomeMsg.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomePanel.add(welcomeMsg, BorderLayout.CENTER);

        contentPanel.add(welcomePanel, "WELCOME");

        // Load modules dynamically
        loadModulesForUser(user);

        // Add to frame
        add(topPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.WEST);   // Menu left
        add(contentPanel, BorderLayout.CENTER); // Content right
    }

    private void loadModulesForUser(User user) {
        RolePermissionDAO dao = new RolePermissionDAO();
        List<RolePermission> permissions = dao.getPermissionsByRole(user.getRole());

        for (RolePermission perm : permissions) {
            if (perm.isCanRead()) {
                JButton btn = new JButton(perm.getModuleName());
                btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                btn.setFocusPainted(false);
                btn.setBackground(new Color(178, 190, 195));

                // Load module panels dynamically
                btn.addActionListener(e -> showModule(perm.getModuleName()));

                menuPanel.add(btn);

                // Preload dummy panels for now (you can replace with real UIs)
                switch (perm.getModuleName()) {
                    case "Customers":
                        contentPanel.add(new CustomerUI(), "Customers");
                        break;
                    case "Users":
                        contentPanel.add(new JPanel(new BorderLayout()) {{
                            add(new JLabel("Users Management Screen", SwingConstants.CENTER));
                        }}, "Users");
                        break;
                    case "Cars":
                        contentPanel.add(new JPanel(new BorderLayout()) {{
                            add(new JLabel("Cars Management Screen", SwingConstants.CENTER));
                        }}, "Cars");
                        break;
                    case "Rentals":
                        contentPanel.add(new JPanel(new BorderLayout()) {{
                            add(new JLabel("Rentals Management Screen", SwingConstants.CENTER));
                        }}, "Rentals");
                        break;
                    case "Reports":
                        contentPanel.add(new JPanel(new BorderLayout()) {{
                            add(new JLabel("Reports Screen", SwingConstants.CENTER));
                        }}, "Reports");
                        break;
                    case "SystemLogs":
                        contentPanel.add(new JPanel(new BorderLayout()) {{
                            add(new JLabel("System Logs Screen", SwingConstants.CENTER));
                        }}, "SystemLogs");
                        break;
                }
            }
        }
    }

    // Switch to the selected module
    private void showModule(String moduleName) {
        cardLayout.show(contentPanel, moduleName);
    }

    // Run for testing
    public static void main(String[] args) {
        User dummyUser = new User();
        dummyUser.setUsername("Janice");
        dummyUser.setRole("Admin");

        SwingUtilities.invokeLater(() -> {
            new StaffDashboard(dummyUser).setVisible(true);
        });
    }
}