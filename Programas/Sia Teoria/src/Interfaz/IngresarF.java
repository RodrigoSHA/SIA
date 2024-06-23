package Interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngresarF extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox comboBoxCliente;
    private JComboBox comboBoxVendedor;
    private JComboBox comboBoxPuntoVenta;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IngresarF frame = new IngresarF();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public IngresarF() {
        setBackground(new Color(71, 143, 197));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 557, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(71, 143, 197));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Ingresar Factura");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(148, 47, 233, 31);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Cliente");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(85, 142, 103, 25);
        contentPane.add(lblNewLabel_1);

        comboBoxCliente = new JComboBox();
        comboBoxCliente.setBounds(198, 142, 262, 25);
        contentPane.add(comboBoxCliente);

        JLabel lblNewLabel_1_1 = new JLabel("Vendedor");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(85, 194, 103, 25);
        contentPane.add(lblNewLabel_1_1);

        comboBoxVendedor = new JComboBox();
        comboBoxVendedor.setBounds(198, 194, 262, 25);
        contentPane.add(comboBoxVendedor);

        JLabel lblNewLabel_1_1_1 = new JLabel("Punto de Venta");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(85, 247, 142, 25);
        contentPane.add(lblNewLabel_1_1_1);

        comboBoxPuntoVenta = new JComboBox();
        comboBoxPuntoVenta.setBounds(237, 247, 223, 25);
        contentPane.add(comboBoxPuntoVenta);

        JButton btnNewButton = new JButton("Ingresar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cliente = (String) comboBoxCliente.getSelectedItem();
                String vendedor = (String) comboBoxVendedor.getSelectedItem();
                String puntoVenta = (String) comboBoxPuntoVenta.getSelectedItem();

                long idCliente = Long.parseLong(cliente.split(" - ")[0]);
                long idVendedor = Long.parseLong(vendedor.split(" - ")[0]);
                long idPuntoVenta = Long.parseLong(puntoVenta.split(" - ")[0]);

                try {
                    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIATeoria", "postgres", "0000");
                    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO MAE_FACTURA (fecha, id_cliente, id_vendedor, id_pto_venta) VALUES (CURRENT_DATE,?,?,?)");
                    pstmt.setLong(1, idCliente);
                    pstmt.setLong(2, idVendedor);
                    pstmt.setLong(3, idPuntoVenta);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(IngresarF.this, "Factura ingresada correctamente");
                    Menu frame = new Menu();
                    frame.setVisible(true);
                    IngresarF.this.dispose();
                } catch (SQLException ex) {
                    System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
                }
            }
        });
        btnNewButton.setBounds(221, 348, 85, 21);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("<-");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu frame = new Menu();
                frame.setVisible(true);
                IngresarF.this.dispose();
            }
        });

        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1.setBackground(new Color(71, 143, 197));
        btnNewButton_1.setBounds(10, 10, 49, 21);
        contentPane.add(btnNewButton_1);

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIATeoria", "postgres", "0000");
            PreparedStatement pstmt = conn.prepareStatement("SELECT ID_Cliente, nombre_cliente, id_documento FROM MAE_CLIENTE");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                comboBoxCliente.addItem(rs.getLong("ID_Cliente") + " - " + rs.getString("nombre_cliente") + " - " + rs.getLong("id_documento"));
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIATeoria", "postgres", "0000");
            PreparedStatement pstmt = conn.prepareStatement("SELECT ID_vendedor, nombre, ID_documento FROM MAE_VENDEDOR");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                comboBoxVendedor.addItem(rs.getLong("ID_vendedor") + " - " + rs.getString("nombre") + " - " + rs.getLong("ID_documento"));
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIATeoria", "postgres", "0000");
            PreparedStatement pstmt = conn.prepareStatement("SELECT Id_punto_venta, nombre FROM MAE_PUNTO_VENTA");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                comboBoxPuntoVenta.addItem(rs.getLong("Id_punto_venta") + " - " + rs.getString("nombre"));
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }
}