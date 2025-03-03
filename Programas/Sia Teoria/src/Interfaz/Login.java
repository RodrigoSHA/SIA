package Interfaz;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexionDB.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    public Login() {
        setResizable(false);
        setBackground(new Color(71, 143, 197));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 604);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(71, 143, 197));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(181, 98, 88, 47);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Usuario");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(98, 247, 60, 25);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Contraseña");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(70, 293, 101, 24);
        contentPane.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(168, 247, 183, 28);
        contentPane.add(textField);

        JButton btnNewButton = new JButton("Ingresar");
        btnNewButton.setBounds(168, 389, 133, 21);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Crear Cuenta");
        btnNewButton_1.setBounds(168, 431, 133, 21);
        contentPane.add(btnNewButton_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(168, 289, 183, 28);
        contentPane.add(passwordField);

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Register frame = new Register();
                frame.setVisible(true);
                Login.this.dispose();
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = textField.getText();
                String contra = passwordField.getText();

                Conexion c = new Conexion();
                String url = "jdbc:postgresql://localhost:5432/SIATeoria";
                String user = "postgres";
                String password = "0000";
                Connection cn = c.getConnection(url, user, password);

                try {
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery("Select * From MAE_USUARIO WHERE nombre_usuario = '" + usuario + "' and contrasena = '" + contra + "'");
                    if (rs.next()) {
                        Menu frame = new Menu();
                        frame.setVisible(true);
                        Login.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(Login.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}