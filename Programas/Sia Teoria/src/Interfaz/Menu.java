package Interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public Menu() {
		setBackground(new Color(71, 143, 197));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(71, 143, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Ingresar ");
		btnNewButton.setBounds(246, 129, 101, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Mostrar");
		btnNewButton_1.setBounds(357, 129, 101, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(218, 50, 73, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(96, 129, 101, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Vendedor");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(96, 167, 101, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton_2 = new JButton("Ingresar ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	IngresarV frame = null;
				try {
					frame = new IngresarV();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				frame.setVisible(true);
				Menu.this.dispose();
		    }
		});
		btnNewButton_2.setBounds(246, 167, 101, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("Mostrar");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarC frame = new MostrarC();
				frame.setVisible(true);
				Menu.this.dispose();
		    }
		});
		btnNewButton_1_1.setBounds(357, 167, 101, 21);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Producto");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(96, 206, 101, 21);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Factura");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(96, 244, 101, 21);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton_2_1 = new JButton("Ingresar ");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarF frame = new IngresarF();
				frame.setVisible(true);
				Menu.this.dispose();
		    }
		});
		btnNewButton_2_1.setBounds(246, 244, 101, 21);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_3 = new JButton("Ingresar ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	IngresarPro frame = new IngresarPro();
				frame.setVisible(true);
				Menu.this.dispose();
		    }
		});
		btnNewButton_3.setBounds(246, 206, 101, 21);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_1_2 = new JButton("Mostrar");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarC frame = new MostrarC();
				frame.setVisible(true);
				Menu.this.dispose();
		    }
		});
		btnNewButton_1_2.setBounds(357, 206, 101, 21);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_1_1 = new JButton("Mostrar");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarC frame = new MostrarC();
				frame.setVisible(true);
				Menu.this.dispose();
		    }
		});
		btnNewButton_1_1_1.setBounds(357, 244, 101, 21);
		contentPane.add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nota de Cargo");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(96, 285, 133, 21);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JButton btnNewButton_2_1_1 = new JButton("Ingresar ");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarNC frame = new IngresarNC();
				frame.setVisible(true);
				Menu.this.dispose();
		    }
		});
		btnNewButton_2_1_1.setBounds(246, 285, 101, 21);
		contentPane.add(btnNewButton_2_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Mostrar");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarC frame = new MostrarC();
				frame.setVisible(true);
				Menu.this.dispose();
		    }
		});
		btnNewButton_1_1_1_1.setBounds(357, 285, 101, 21);
		contentPane.add(btnNewButton_1_1_1_1);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	IngresarC frame = null;
				try {
					frame = new IngresarC();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				frame.setVisible(true);
				Menu.this.dispose();
		    }
		});
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	MostrarC frame = new MostrarC();
				frame.setVisible(true);
				Menu.this.dispose();
		    }
		});
	}
}
