package guidatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBQueryTool extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField urlField, userField;
    JPasswordField passField;
    JTextArea queryArea, resultArea;
    JButton connectBtn, executeBtn;
    Connection conn;

    public DBQueryTool() {
        setTitle("Universal DB Query Tool");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel - Connection Fields
        JPanel connPanel = new JPanel(new GridLayout(3, 2));
        connPanel.setBorder(BorderFactory.createTitledBorder("Database Connection"));

        connPanel.add(new JLabel("JDBC URL:"));
        urlField = new JTextField("jdbc:mysql://localhost:3306/rohit");
        connPanel.add(urlField);

        connPanel.add(new JLabel("Username:"));
        userField = new JTextField("root");
        connPanel.add(userField);

        connPanel.add(new JLabel("Password:"));
        passField = new JPasswordField();
        connPanel.add(passField);

        add(connPanel, BorderLayout.NORTH);

        // Center Panel - Query and Results
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        queryArea = new JTextArea("SELECT * FROM your_table;");
        queryArea.setBorder(BorderFactory.createTitledBorder("SQL Query"));
        centerPanel.add(new JScrollPane(queryArea));

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createTitledBorder("Results"));
        centerPanel.add(new JScrollPane(resultArea));

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel - Buttons
        JPanel btnPanel = new JPanel();
        connectBtn = new JButton("Connect");
        executeBtn = new JButton("Execute Query");
        executeBtn.setEnabled(false);

        btnPanel.add(connectBtn);
        btnPanel.add(executeBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // Button Actions
        connectBtn.addActionListener(e -> connectToDatabase());
        executeBtn.addActionListener(e -> executeQuery());

        setVisible(true);
    }

    void connectToDatabase() {
        try {
            String url = urlField.getText();
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            conn = DriverManager.getConnection(url, user, pass);
            resultArea.setText("✅ Connected successfully!");
            executeBtn.setEnabled(true);
        } catch (Exception e) {
            resultArea.setText("❌ Connection failed:\n" + e.getMessage());
            executeBtn.setEnabled(false);
        }
    }

    void executeQuery() {
        try (Statement stmt = conn.createStatement()) {
            String sql = queryArea.getText();
            boolean hasResultSet = stmt.execute(sql);

            if (hasResultSet) {
                ResultSet rs = stmt.getResultSet();
                ResultSetMetaData rsmd = rs.getMetaData();
                int columns = rsmd.getColumnCount();

                StringBuilder output = new StringBuilder();

                // Column headers
                for (int i = 1; i <= columns; i++) {
                    output.append(rsmd.getColumnName(i)).append("\t");
                }
                output.append("\n");

                // Data rows
                while (rs.next()) {
                    for (int i = 1; i <= columns; i++) {
                        output.append(rs.getString(i)).append("\t");
                    }
                    output.append("\n");
                }
                resultArea.setText(output.toString());
            } else {
                int count = stmt.getUpdateCount();
                resultArea.setText("✅ Query OK, " + count + " rows affected.");
            }
        } catch (Exception e) {
            resultArea.setText("❌ Query failed:\n" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Optional: Load MySQL driver (not always required)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("MySQL Driver not found. Make sure it's added to classpath.");
        }
        SwingUtilities.invokeLater(DBQueryTool::new);
    }
}

