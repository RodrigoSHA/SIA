package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class IngresarPro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IngresarPro frame = new IngresarPro();
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
    public IngresarPro() {
        setBackground(new Color(71, 143, 197));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 557, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(71, 143, 197));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Ingresar Producto");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(140, 50, 254, 31);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nombre del Producto");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(85, 142, 202, 25);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(285, 142, 175, 25);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Descripción");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(85, 194, 103, 25);
        contentPane.add(lblNewLabel_1_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(198, 194, 262, 25);
        contentPane.add(textField_1);

        String[] unidades = {"Ninguna", "Docena", "Centena", "Unidad", "Kilogramo", "Litro"};
        JComboBox comboBoxUnidad = new JComboBox(unidades);
        comboBoxUnidad.setBounds(198, 246, 262, 25);
        contentPane.add(comboBoxUnidad);

        JLabel lblNewLabel_1_1_1 = new JLabel("Unidad");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(85, 246, 103, 25);
        contentPane.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_2 = new JLabel("Precio Unitario");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_2.setBounds(85, 298, 130, 25);
        contentPane.add(lblNewLabel_1_1_2);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(231, 298, 229, 25);
        contentPane.add(textField_2);

        JButton btnNewButton = new JButton("Ingresar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = textField.getText();
                String descripcion = textField_1.getText();
                String unidad = (String) comboBoxUnidad.getSelectedItem();
                String precioUnitario = textField_2.getText();

                if (nombre.isEmpty() || descripcion.isEmpty() || unidad.isEmpty() || precioUnitario.isEmpty()) {
                    JOptionPane.showMessageDialog(IngresarPro.this, "Por favor, ingrese todos los campos");
                    return;
                }

                try {
                    double costoUnitario = Double.parseDouble(precioUnitario);

                    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIATeoria", "postgres", "0000");
                    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO MAE_PRODUCTOS (nombre, descripcion, unidad, costo_unitario) VALUES (?, ?, ?, ?)");
                    pstmt.setString(1, nombre);
                    pstmt.setString(2, descripcion);
                    pstmt.setString(3, unidad);
                    pstmt.setDouble(4, costoUnitario);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(IngresarPro.this, "Producto ingresado correctamente");
                    Menu frame = new Menu();
                    frame.setVisible(true);
                    IngresarPro.this.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(IngresarPro.this, "El precio unitario debe ser un número");
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
                IngresarPro.this.dispose();
            }
        });

        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1.setBackground(new Color(71, 143, 197));
        btnNewButton_1.setBounds(10, 10, 49, 21);
        contentPane.add(btnNewButton_1);
    }
}