package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ConexionDB.Conexion;

public class IngresarV extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresarV frame = new IngresarV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public IngresarV() throws SQLException {
		setBackground(new Color(71, 143, 197));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(71, 143, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingresar Vendedor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(148, 47, 214, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(85, 142, 103, 25);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(198, 142, 262, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		Conexion c = new Conexion();
        String url = "jdbc:postgresql://localhost:5432/SIATeoria";
        String user = "postgres";
        String password = "0000";
        Connection cn = c.getConnection(url, user, password);
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("Select * From mae_tipo_documento");
        List<String> tiposIDList = new ArrayList<>();
        while(rs.next()) {
            tiposIDList.add(rs.getString(2));
        }
        String[] tiposID = tiposIDList.toArray(new String[0]);

        JComboBox comboBoxUnidad = new JComboBox(tiposID);
        comboBoxUnidad.setBounds(198, 194, 262, 25);
        contentPane.add(comboBoxUnidad);
        
        JLabel lblNewLabel_1_1 = new JLabel("Tipo de ID");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(85, 194, 103, 25);
        contentPane.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("ID");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(85, 247, 103, 25);
        contentPane.add(lblNewLabel_1_1_1);
        
        
        JButton btnNewButton = new JButton("Ingresar");
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = textField.getText();
                String tipoID = (String) comboBoxUnidad.getSelectedItem();
                String id = textField_1.getText();

                if (nombre.isEmpty() || tipoID.isEmpty() || id.isEmpty()) {
                    JOptionPane.showMessageDialog(IngresarV.this, "Por favor, ingrese todos los campos");
                    return;
                }

                Conexion c = new Conexion();
                String url = "jdbc:postgresql://localhost:5432/SIATeoria";
                String user = "postgres";
                String password = "0000";
                Connection cn = c.getConnection(url, user, password);

                try {
                    Statement st = cn.createStatement();
                    String query = "INSERT INTO MAE_VENDEDOR (nombre, tipo_de_documento, id_documento) VALUES('" + nombre + "', (SELECT id_tipo_documento FROM mae_tipo_documento WHERE  nombre= '" + tipoID + "'), " + id + ")";
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(IngresarV.this, "Vendedor ingresado correctamente");
                    Menu frame = new Menu();
                    frame.setVisible(true);
                    IngresarV.this.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(IngresarV.this, "Error al ingresar vendedor: " + ex.getMessage());
                } finally {
                    try {
                        cn.close();
                    } catch (SQLException ex) {
                        ex.getMessage();
                    }
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
				IngresarV.this.dispose();
		    }
        });
        
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1.setBackground(new Color(71, 143, 197));
        btnNewButton_1.setBounds(10, 10, 49, 21);
        contentPane.add(btnNewButton_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(198, 247, 262, 25);
        contentPane.add(textField_1);
	}
	

}
