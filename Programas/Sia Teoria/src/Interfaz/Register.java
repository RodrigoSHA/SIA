package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexionDB.Conexion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField_1;
    private JTextField textField_1;

    public Register() {
        setBackground(new Color(71, 143, 197));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 604);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(71, 143, 197));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblRegister = new JLabel("REGISTER");
        lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblRegister.setBounds(155, 122, 146, 47);
        contentPane.add(lblRegister);

        JLabel lblNewLabel_1 = new JLabel("Usuario");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(108, 252, 60, 25);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(178, 252, 183, 28);
        contentPane.add(textField);

        JButton btnNewButton_1 = new JButton("Crear Cuenta");
        btnNewButton_1.setBounds(178, 440, 133, 21);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel_2_1 = new JLabel("Contraseña");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2_1.setBounds(81, 360, 101, 24);
        contentPane.add(lblNewLabel_2_1);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(179, 356, 183, 28);
        contentPane.add(passwordField_1);

        JLabel lblNewLabel_1_1 = new JLabel("Correo");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_1.setBounds(108, 304, 60, 25);
        contentPane.add(lblNewLabel_1_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(178, 304, 183, 28);
        contentPane.add(textField_1);

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = textField.getText();
                String correo = textField_1.getText();
                String contra = passwordField_1.getText();

                if (usuario.isEmpty() || correo.isEmpty() || contra.isEmpty()) {
                    JOptionPane.showMessageDialog(Register.this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Conexion c = new Conexion();
                String url = "jdbc:postgresql://localhost:5432/SIATeoria";
                String user = "postgres";
                String password = "0000";
                Connection cn = c.getConnection(url, user, password);

                try {
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO MAE_USUARIO (nombre_usuario, correo_electronico, contrasena) VALUES (?,?,?)");
                    pst.setString(1, usuario);
                    pst.setString(2, correo);
                    pst.setString(3, contra);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(Register.this, "Cuenta creada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    Login frame = new Login();
                    frame.setVisible(true);
                    Register.this.dispose();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(Register.this, "Error al crear cuenta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}