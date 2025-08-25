package carrental.ui;

import carrental.model.*;
import carrental.dao.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Dashboard extends JFrame {
    private JLabel lblUsername;
    private JLabel lblRole;
    private JPanel menuPanel;      // Left side menu
    private JPanel contentPanel;   // Right side dynamic content area
    private CardLayout cardLayout; // For swapping screens

    public Dashboard(User user) {
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
    String role = user.getRole();

    // Admin can see ALL modules
    if ("Admin".equalsIgnoreCase(role)) {
        addModuleButton("Customers", new CustomerUI());
        addModuleButton("Users", new JPanel(new BorderLayout()) {{
            add(new JLabel("Users Management Screen", SwingConstants.CENTER));
        }});
        addModuleButton("Cars", new JPanel(new BorderLayout()) {{
            add(new JLabel("Cars Management Screen", SwingConstants.CENTER));
        }});
        addModuleButton("Rentals", new JPanel(new BorderLayout()) {{
            add(new JLabel("Rentals Management Screen", SwingConstants.CENTER));
        }});
        addModuleButton("Reports", new JPanel(new BorderLayout()) {{
            add(new JLabel("Reports Screen", SwingConstants.CENTER));
        }});
        addModuleButton("SystemLogs", new JPanel(new BorderLayout()) {{
            add(new JLabel("System Logs Screen", SwingConstants.CENTER));
        }});

    // Staff can see only limited modules
    } else if ("Staff".equalsIgnoreCase(role)) {
        addModuleButton("Customers", new CustomerUI());
        addModuleButton("Cars", new JPanel(new BorderLayout()) {{
            add(new JLabel("Cars Management Screen", SwingConstants.CENTER));
        }});
        addModuleButton("Rentals", new JPanel(new BorderLayout()) {{
            add(new JLabel("Rentals Management Screen", SwingConstants.CENTER));
        }});
    }
}

    // Switch to the selected module
    private void showModule(String moduleName) {
        cardLayout.show(contentPanel, moduleName);
    }

    // Run for testing
    private void addModuleButton(String moduleName, JPanel panel) {
    JButton btn = new JButton(moduleName);
    btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    btn.setFocusPainted(false);
    btn.setBackground(new Color(178, 190, 195));

    btn.addActionListener(e -> showModule(moduleName));
    menuPanel.add(btn);

    contentPanel.add(panel, moduleName);
}
}