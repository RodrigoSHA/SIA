package Interfaz;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class MostrarC extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JComboBox comboBoxTipo;
    private JTextArea textArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MostrarC frame = new MostrarC();
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
    public MostrarC() {
        setBackground(new Color(71, 143, 197));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 554, 720);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(71, 143, 197));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Mostrar");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(207, 58, 118, 37);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Tipo");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(94, 101, 66, 25);
        contentPane.add(lblNewLabel_1);

        String[] tipos = {"Cliente", "Vendedor", "Producto", "Factura", "Nota de Cargo"};
        comboBoxTipo = new JComboBox(tipos);
        comboBoxTipo.setBounds(189, 101, 224, 25);
        contentPane.add(comboBoxTipo);

        JLabel lblNewLabel_1_1 = new JLabel("ID");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(94, 136, 66, 25);
        contentPane.add(lblNewLabel_1_1);

        textField = new JTextField();
        textField.setBounds(189, 136, 224, 25);
        contentPane.add(textField);
        textField.setColumns(10);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textArea.setBounds(67, 206, 396, 441);
        contentPane.add(textArea);

        JButton btnNewButton_1_1 = new JButton("<-");
        btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1_1.setBackground(new Color(71, 143, 197));
        btnNewButton_1_1.setBounds(22, 23, 49, 21);
        contentPane.add(btnNewButton_1_1);

        JButton btnNewButton = new JButton("Mostrar");
        btnNewButton.setBounds(221, 171, 85, 25);
        contentPane.add(btnNewButton);

        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu frame = new Menu();
                frame.setVisible(true);
                MostrarC.this.dispose();
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) comboBoxTipo.getSelectedItem();
                String id = textField.getText();

                if (tipo.equals("Cliente")) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIATeoria", "postgres", "0000");
                        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MAE_CLIENTE WHERE ID_Cliente =?");
                        pstmt.setLong(1, Long.parseLong(id)); 
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            String nombreCliente = rs.getString("nombre_cliente");
                            int idTipoDocumento = rs.getInt("id_tipo_documento");
                            long idDocumento = rs.getLong("id_documento");

                            textArea.setText("ID Cliente: " + id + "\nNombre Cliente: " + nombreCliente + "\nID Tipo Documento: " + idTipoDocumento + "\nID Documento: " + idDocumento);
                        } else {
                            textArea.setText("No se encontró el cliente con ID " + id);
                        }

                        conn.close();
                    } catch (SQLException ex) {
                        System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
                    }
                } else if (tipo.equals("Vendedor")) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIATeoria", "postgres", "0000");
                        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MAE_VENDEDOR WHERE ID_Vendedor =?");
                        pstmt.setLong(1, Long.parseLong(id)); 
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            String nombreVendedor = rs.getString("nombre");
                            int idTipoDocumento = rs.getInt("tipo_de_documento");
                            long idDocumento = rs.getLong("id_documento");

                            textArea.setText("ID Vendedor: " + id + "\nNombre Vendedor: " + nombreVendedor + "\nID Tipo Documento: " + idTipoDocumento + "\nID Documento: " + idDocumento);
                        } else {
                            textArea.setText("No se encontró el vendedor con ID " + id);
                        }

                        conn.close();
                    } catch (SQLException ex) {
                        System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
                    }
                } else if (tipo.equals("Producto")) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIATeoria", "postgres", "0000");
                        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MAE_PRODUCTOS WHERE ID_Producto =?");
                        pstmt.setLong(1, Long.parseLong(id)); 
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            String nombreProducto = rs.getString("nombre");
                            String descripcionProducto = rs.getString("descripcion");
                            double precio = rs.getDouble("costo_unitario");

                            textArea.setText("ID Producto: " + id + "\nNombre Producto: " + nombreProducto +"\nDescripcion: "+ descripcionProducto + "\nPrecio Unitario: " + precio);
                        } else {
                            textArea.setText("No se encontró el producto con ID " + id);
                        }

                        conn.close();
                    } catch (SQLException ex) {
                        System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
                    }
                } else if (tipo.equals("Factura")) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIATeoria", "postgres", "0000");
                        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MAE_FACTURA WHERE ID_Factura =?");
                        pstmt.setLong(1, Long.parseLong(id)); 
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            long idCliente = rs.getLong("id_cliente");
                            long idVendedor = rs.getLong("id_vendedor");
                            long idPuntoVenta = rs.getLong("id_pto_venta");
                            Date fecha = rs.getDate("fecha");

                            pstmt = conn.prepareStatement("SELECT * FROM MAE_CLIENTE WHERE ID_Cliente =?");
                            pstmt.setLong(1, idCliente); 
                            ResultSet rsCliente =pstmt.executeQuery();
                            rsCliente.next();
                            String nombreCliente = rsCliente.getString("nombre_cliente");

                            pstmt = conn.prepareStatement("SELECT * FROM MAE_VENDEDOR WHERE ID_Vendedor =?");
                            pstmt.setLong(1, idVendedor); 
                            ResultSet rsVendedor = pstmt.executeQuery();
                            rsVendedor.next();
                            String nombreVendedor = rsVendedor.getString("nombre");

                            pstmt = conn.prepareStatement("SELECT * FROM MAE_PUNTO_VENTA WHERE Id_punto_venta =?");
                            pstmt.setLong(1, idPuntoVenta); 
                            ResultSet rsPuntoVenta = pstmt.executeQuery();
                            rsPuntoVenta.next();
                            String nombrePuntoVenta = rsPuntoVenta.getString("nombre");

                            textArea.setText("Factura N° " + id + "\nFecha: " + fecha + "\nCliente: " + nombreCliente + "\nVendedor: " + nombreVendedor + "\nPunto de Venta: " + nombrePuntoVenta);
                        } else {
                            textArea.setText("No se encontró la factura con ID " + id);
                        }

                        conn.close();
                    } catch (SQLException ex) {
                        System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
                    }
                } else if (tipo.equals("Nota de Cargo")) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIATeoria", "postgres", "0000");
                        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM TRS_NOTA_CARGO WHERE ID_Nota_Cargo =?");
                        pstmt.setLong(1, Long.parseLong(id)); 
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            long idFactura = rs.getLong("ID_Factura");
                            String formaPago = rs.getString("forma_de_pago");
                            Date fecha = rs.getDate("fecha");
                            
                            pstmt = conn.prepareStatement("SELECT * FROM MAE_FACTURA WHERE ID_Factura =?");
                            pstmt.setLong(1, idFactura); 
                            ResultSet rsFactura = pstmt.executeQuery();
                            rsFactura.next();
                            long idCliente = rsFactura.getLong("id_cliente");

                            pstmt = conn.prepareStatement("SELECT * FROM MAE_CLIENTE WHERE ID_Cliente =?");
                            pstmt.setLong(1, idCliente); 
                            ResultSet rsCliente = pstmt.executeQuery();
                            rsCliente.next();
                            String nombreCliente = rsCliente.getString("nombre_cliente");

                            pstmt = conn.prepareStatement("SELECT * FROM TRS_NOTA_CARGO_DETALLE WHERE ID_Nota_Cargo =?");
                            pstmt.setLong(1, Long.parseLong(id)); 
                            ResultSet rsDetalle = pstmt.executeQuery();

                            String detalle = "";
                            while (rsDetalle.next()) {
                                long idProducto = rsDetalle.getLong("ID_Producto");
                                int cantidad = rsDetalle.getInt("cantidad");
                                double total = rsDetalle.getDouble("total");

                                pstmt = conn.prepareStatement("SELECT * FROM MAE_PRODUCTOS WHERE ID_Producto =?");
                                pstmt.setLong(1, idProducto); 
                                ResultSet rsProducto = pstmt.executeQuery();
                                rsProducto.next();
                                String nombreProducto = rsProducto.getString("nombre");

                                detalle += "Producto: " + nombreProducto + "\nCantidad: " + cantidad + "\nTotal: " + total + "\n\n";
                            }

                            textArea.setText("Nota de Cargo N° " + id + "\nFecha: " + fecha + "\nFactura: " + idFactura + "\nCliente: " + nombreCliente + "\nForma de Pago: " + formaPago + "\n\nDetalle:\n" + detalle);
                        } else {
                            textArea.setText("No se encontró la nota de cargo con ID " + id);
                        }

                        conn.close();
                    } catch (SQLException ex) {
                        System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
                    }
                }
            }
        });
    }
}