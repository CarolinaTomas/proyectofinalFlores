/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalflores;

/**
 *
 * @author wcaro
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PerfilProductosFlores extends JFrame implements ActionListener {
    private JButton ingresarBtn, consultarBtn, actualizarBtn, eliminarBtn, buscarCodigoBtn, buscarNombreBtn, transaccionBtn, reporteBtn;
    private JTextArea reporteArea;
    private ImageIcon logo;
    
    
    // Datos de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/bdnegocioflores";
    private static final String USER = "root";
    private static final String PASSWORD = "carolina93";

    public PerfilProductosFlores() {
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {
        this.setTitle("Menu Principal - Floristeria");
        this.setSize(800, 650);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.pink);
        
        // Icono de inicio
        logo = new ImageIcon("C:\\Users\\wcaro\\OneDrive\\Documentos\\NetBeansProjects\\FloristeriaWendy\\src\\main\\java\\Floristeria\\lflor.png");
        Image logoDimension = logo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon adjustedlogo = new ImageIcon(logoDimension);
        JLabel imageLabel = new JLabel(adjustedlogo);
        imageLabel.setBounds(325,20,150,150);
        this.add(imageLabel);
        this.setIconImage(logo.getImage());
        
        // Icono en la barra de título
        this.setIconImage(logo.getImage());
        
        // Etiqueta "Menú"
    JLabel menuLabel = new JLabel("Menú");
    menuLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Puedes ajustar el tamaño de la fuente
    menuLabel.setBounds(30, 150, 100, 30);
    menuLabel.setForeground(Color.WHITE);
    this.add(menuLabel);
    
        // Configuración de botones con más separación
    int buttonWidth = 200;
    int buttonHeight = 40;
    int buttonX = 30;
    int initialY = 200;
    int spacing = 50;  // Espaciado entre los botones
    
        
        // Botones para funcionalidades
        ingresarBtn = new JButton("Ingresar Datos");
        ingresarBtn.setBounds(buttonX, initialY, buttonWidth, buttonHeight);
        //ingresarBtn.setBounds(30, 200, 200, 40);
        ingresarBtn.addActionListener(this);
        ingresarBtn.setForeground(Color.WHITE);
        ingresarBtn.setBackground(new Color(155, 89, 182));
        this.add(ingresarBtn);
        
        consultarBtn = new JButton("Consultar Registros");
        consultarBtn.setBounds(buttonX, initialY + spacing, buttonWidth, buttonHeight);

        //consultarBtn.setBounds(30, 250, 200, 40);
        consultarBtn.addActionListener(this);
        consultarBtn.setForeground(Color.WHITE);
        consultarBtn.setBackground(new Color(155, 89, 182));
        this.add(consultarBtn);
        
        actualizarBtn = new JButton("Actualizar Registros");
        actualizarBtn.setBounds(buttonX, initialY + spacing * 2, buttonWidth, buttonHeight);
        //actualizarBtn.setBounds(30, 300, 200, 40);
        actualizarBtn.addActionListener(this);
        actualizarBtn.setForeground(Color.WHITE);
        actualizarBtn.setBackground(new Color(155, 89, 182));
        this.add(actualizarBtn);
        
        eliminarBtn = new JButton("Eliminar Registros");
        eliminarBtn.setBounds(buttonX, initialY + spacing * 3, buttonWidth, buttonHeight);
        //eliminarBtn.setBounds(30, 350, 200, 40);
        eliminarBtn.addActionListener(this);
        eliminarBtn.setForeground(Color.WHITE);
        eliminarBtn.setBackground(new Color(155, 89, 182));
        this.add(eliminarBtn);
        
        buscarCodigoBtn = new JButton("Buscar por Código");
        buscarCodigoBtn.setBounds(buttonX, initialY + spacing * 4, buttonWidth, buttonHeight);
        //buscarCodigoBtn.setBounds(30, 230, 200, 40);
        buscarCodigoBtn.addActionListener(this);
        buscarCodigoBtn.setForeground(Color.WHITE);
        buscarCodigoBtn.setBackground(new Color(155, 89, 182));
        this.add(buscarCodigoBtn);
        
        buscarNombreBtn = new JButton("Buscar por Nombre");
        buscarNombreBtn.setBounds(buttonX, initialY + spacing * 5, buttonWidth, buttonHeight);
        //buscarNombreBtn.setBounds(30, 280, 200, 40);
        buscarNombreBtn.addActionListener(this);
        buscarNombreBtn.setForeground(Color.WHITE);
        buscarNombreBtn.setBackground(new Color(155, 89, 182));
        this.add(buscarNombreBtn);
        
        transaccionBtn = new JButton("Transacción");
        transaccionBtn.setBounds(buttonX, initialY + spacing * 6, buttonWidth, buttonHeight);
        //transaccionBtn.setBounds(30, 330, 200, 40);
        transaccionBtn.addActionListener(this);
        transaccionBtn.setForeground(Color.WHITE);
        transaccionBtn.setBackground(new Color(155, 89, 182));
        this.add(transaccionBtn);
        
        reporteBtn = new JButton("Generar Reporte de Productos");
        reporteBtn.setBounds(buttonX, initialY + spacing * 7, buttonWidth, buttonHeight);
        //reporteBtn.setBounds(30, 380, 200, 40);
        reporteBtn.addActionListener(this);
        reporteBtn.setForeground(Color.WHITE);
        reporteBtn.setBackground(new Color(155, 89, 182));
        this.add(reporteBtn);
        
        // Etiqueta "Inventario Floristería"
    JLabel inventarioLabel = new JLabel("Inventario Floristería");
    inventarioLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Puedes ajustar el tamaño de la fuente
    inventarioLabel.setBounds(260, 170, 250, 30);  // Colocamos esta etiqueta sobre el área de texto
    inventarioLabel.setForeground(Color.WHITE);
    this.add(inventarioLabel);
    
        // Área de texto para mostrar el reporte
        reporteArea = new JTextArea();
        reporteArea.setEditable(false);
        
        // Agregar área de texto en un panel de desplazamiento
        JScrollPane scrollPane = new JScrollPane(reporteArea);
        scrollPane.setBounds(260, 200, 500, 350);
        this.add(scrollPane);

        this.setVisible(true);
    }

    // Método de conexión a la base de datos
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Acción al presionar cada botón
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ingresarBtn) {
        ingresarProducto();
    } else if (e.getSource() == consultarBtn) {
        consultarRegistros();
    } else if (e.getSource() == actualizarBtn) {
        actualizarProducto();
    } else if (e.getSource() == eliminarBtn) {
        eliminarProducto();
    } else if (e.getSource() == buscarCodigoBtn) {
        buscarProductoPorCodigo();
    } else if (e.getSource() == buscarNombreBtn) {
        buscarProductoPorNombre();
    } else if (e.getSource() == transaccionBtn) {
        realizarTransaccion();
    } else if (e.getSource() == reporteBtn) {
        generarReporteProductos();
    }
}
     private void ingresarProducto() {
    // Ejemplo de datos de entrada
    String codigo = JOptionPane.showInputDialog("Ingrese el código del producto:");
    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
    double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio unitario:"));
    int existencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la existencia:"));

    String query = "INSERT INTO productos (codigo, nombre, precio_unitario, existencia) VALUES ('" + codigo + "', '" + nombre + "', " + precio + ", " + existencia + ")";
    
    try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
        stmt.executeUpdate(query);
        JOptionPane.showMessageDialog(this, "Producto ingresado correctamente.");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al ingresar producto.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void consultarRegistros() {
    // Puedes llamar a generarReporteProductos o hacer otra consulta.
    generarReporteProductos();
}

private void actualizarProducto() {
    String codigo = JOptionPane.showInputDialog("Ingrese el código del producto a actualizar:");
    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del producto:");
    double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio unitario:"));
    int nuevaExistencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva existencia:"));

    String query = "UPDATE productos SET nombre = '" + nuevoNombre + "', precio_unitario = " + nuevoPrecio + ", existencia = " + nuevaExistencia + " WHERE codigo = '" + codigo + "'";
    
    try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
        int filasActualizadas = stmt.executeUpdate(query);
        if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el producto con el código especificado.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al actualizar producto.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void eliminarProducto() {
    String codigo = JOptionPane.showInputDialog("Ingrese el código del producto a eliminar:");

    String query = "DELETE FROM productos WHERE codigo = '" + codigo + "'";
    
    try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
        int filasEliminadas = stmt.executeUpdate(query);
        if (filasEliminadas > 0) {
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el producto con el código especificado.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al eliminar producto.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void buscarProductoPorCodigo() {
    String codigo = JOptionPane.showInputDialog("Ingrese el código del producto a buscar:");
    String query = "SELECT * FROM productos WHERE codigo = '" + codigo + "'";

    try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
        if (rs.next()) {
            String resultado = "Código: " + rs.getString("codigo") + "\n" +
                               "Nombre: " + rs.getString("nombre") + "\n" +
                               "Precio: " + rs.getDouble("precio_unitario") + "\n" +
                               "Existencia: " + rs.getInt("existencia");
            JOptionPane.showMessageDialog(this, resultado);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el producto con el código especificado.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al buscar producto.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void buscarProductoPorNombre() {
    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto a buscar:");
    String query = "SELECT * FROM productos WHERE nombre LIKE '%" + nombre + "%'";

    try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
        StringBuilder resultados = new StringBuilder("Resultados de búsqueda:\n");
        while (rs.next()) {
            resultados.append("Código: ").append(rs.getString("codigo")).append(", ")
                      .append("Nombre: ").append(rs.getString("nombre")).append(", ")
                      .append("Precio: ").append(rs.getDouble("precio_unitario")).append(", ")
                      .append("Existencia: ").append(rs.getInt("existencia")).append("\n");
        }
        reporteArea.setText(resultados.toString());
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al buscar producto.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
private void realizarTransaccion() {
    String codigoProducto = JOptionPane.showInputDialog("Ingrese el código del producto a vender:");
    int cantidadVendida = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad vendida:"));

    try (Connection conn = conectar()) {
        conn.setAutoCommit(false);

        // Consulta para verificar si el producto existe y obtener su inventario actual
        String queryVerificar = "SELECT nombre, existencia, precio_unitario FROM productos WHERE codigo = '" + codigoProducto + "'";
        Statement stmtVerificar = conn.createStatement();
        ResultSet rs = stmtVerificar.executeQuery(queryVerificar);

        if (rs.next()) {
            String nombreProducto = rs.getString("nombre");
            int existenciaActual = rs.getInt("existencia");
            double precioUnitario = rs.getDouble("precio_unitario");

            if (existenciaActual >= cantidadVendida) {
                double totalVenta = cantidadVendida * precioUnitario;
                int nuevaExistencia = existenciaActual - cantidadVendida;
                
                // Actualizar el inventario
                String queryActualizar = "UPDATE productos SET existencia = " + nuevaExistencia + " WHERE codigo = '" + codigoProducto + "'";
                Statement stmtActualizar = conn.createStatement();
                stmtActualizar.executeUpdate(queryActualizar);

                conn.commit();

                // Generar el contenido de la factura
                StringBuilder factura = new StringBuilder();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String fecha = sdf.format(new Date());

                factura.append("----- FACTURA DE VENTA -----\n")
                       .append("Fecha: ").append(fecha).append("\n")
                       .append("Código del Producto: ").append(codigoProducto).append("\n")
                       .append("Nombre del Producto: ").append(nombreProducto).append("\n")
                       .append("Cantidad Vendida: ").append(cantidadVendida).append("\n")
                       .append("Precio Unitario: $").append(precioUnitario).append("\n")
                       .append("Total de la Venta: $").append(totalVenta).append("\n")
                       .append("Inventario Actualizado: ").append(nuevaExistencia).append(" unidades\n")
                       .append("----------------------------\n");

                // Mostrar la factura en un cuadro de diálogo
                JOptionPane.showMessageDialog(this, factura.toString(), "Factura de Venta", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this, "Inventario insuficiente para la venta.");
                conn.rollback();
            }
        } else {
            JOptionPane.showMessageDialog(this, "El producto con el código especificado no existe.");
            conn.rollback();
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al realizar la transacción.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}




 
    
// Método para generar reporte de productos desde la base de datos
    private void generarReporteProductos() {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {

            String query = "SELECT codigo, nombre, precio_unitario, existencia FROM productos";
            ResultSet rs = stmt.executeQuery(query);
            
            StringBuilder reporte = new StringBuilder("Código\tNombre\tPrecio_unitario\tExistencia\n");
            reporte.append("----------------------------------------\n");
            
            while (rs.next()) {
                reporte.append(rs.getString("codigo")).append("\t")
                       .append(rs.getString("nombre")).append("\t")
                       .append(rs.getDouble("precio_unitario")).append("\t")
                       .append(rs.getInt("existencia")).append("\n");
            }
            
            reporteArea.setText(reporte.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el reporte de productos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new PerfilProductosFlores();
    }
}

