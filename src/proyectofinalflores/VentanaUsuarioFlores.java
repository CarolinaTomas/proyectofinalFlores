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
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaUsuarioFlores extends JFrame implements ActionListener, FocusListener {
    private JTextField nombreField;
    private JTextField contraseñaField; // Cambiado de JtextField a JTextField
    private JButton siguienteBtn;
    private JLabel nombreLbl, marcaLbl, contraseñaLbl;
    private ImageIcon logo;
    
    public VentanaUsuarioFlores(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        // Icono de inicio
        logo = new ImageIcon("C:\\Users\\wcaro\\OneDrive\\Documentos\\NetBeansProjects\\FloristeriaWendy\\src\\main\\java\\Floristeria\\lflor.png");
        Image logoDimension = logo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon adjustedlogo = new ImageIcon(logoDimension);
        JLabel imageLabel = new JLabel(adjustedlogo);
        imageLabel.setBounds(150, 20, 200, 200);
        this.add(imageLabel);
        
        // Icono en la barra de título
        this.setIconImage(logo.getImage());
        
        // Etiqueta nombre
        nombreLbl = new JLabel("Nombre");
        nombreLbl.setFont(new Font("Arial", Font.BOLD, 20));
        nombreLbl.setBounds(75, 230, 350, 30);
        nombreLbl.setForeground(Color.WHITE);
        this.add(nombreLbl);
        
        // Etiqueta contraseña
        contraseñaLbl = new JLabel("Contraseña");
        contraseñaLbl.setFont(new Font("Arial", Font.BOLD, 20));
        contraseñaLbl.setBounds(75, 310, 350, 30); // Corregido el posicionamiento
        contraseñaLbl.setForeground(Color.WHITE);
        this.add(contraseñaLbl);
        
        // Campo para introducir el nombre
        nombreField = new JTextField("Escriba su nombre aquí");
        nombreField.setBounds(120, 270, 260, 25); // Ajustado para que no se solapen
        nombreField.addFocusListener(this);
        this.add(nombreField);
        
        // Campo para introducir la contraseña
        contraseñaField = new JTextField("Escriba su contraseña aquí");
        contraseñaField.setBounds(120, 350, 260, 25); // Ubicación debajo del nombre
        contraseñaField.addFocusListener(this);
        this.add(contraseñaField);
        
        // Botón siguiente
        siguienteBtn = new JButton("Siguiente");
        siguienteBtn.setBounds(170, 400, 160, 40);
        siguienteBtn.setForeground(Color.WHITE);
        siguienteBtn.setBackground(Color.PINK);
        siguienteBtn.setOpaque(true);
        siguienteBtn.setBorderPainted(false);
        siguienteBtn.addActionListener(this);
        this.add(siguienteBtn);
        
        // Etiqueta marca
        marcaLbl = new JLabel("©2024 WENDYTOMAS FLORISTERIA. Wendy Tomas Tubac, Programación 2, sección A");
        marcaLbl.setFont(new Font("Arial", Font.ITALIC, 8));
        marcaLbl.setBounds(50, 460, 400, 30); // Ajustado para alinearse correctamente
        marcaLbl.setForeground(Color.WHITE);
        this.add(marcaLbl);
        
        // Configuración de la ventana
        this.setTitle("Welcome");
        this.setLocationRelativeTo(null);
        this.setSize(500, 520);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(155, 89, 182));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == siguienteBtn) {
            String nombre = nombreField.getText();
            String contraseña = contraseñaField.getText();
            if (nombre.isEmpty() || nombre.equals("Escriba su nombre aquí") || 
                contraseña.isEmpty() || contraseña.equals("Escriba su contraseña aquí")) {
                JOptionPane.showMessageDialog(this, "Ingrese su nombre y contraseña", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                // Código para navegar al perfil de productos
                // mainProyectoFlores.usuario = nombre;  // Asegúrate de definir esta variable en tu clase principal
                new PerfilProductosFlores(); // Llama a la ventana de PerfilProductosFlores
                this.dispose();
            }
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if (fe.getSource() == nombreField && nombreField.getText().equals("Escriba su nombre aquí")) {
            nombreField.setText("");
            nombreField.setForeground(Color.BLACK);
        }
        if (fe.getSource() == contraseñaField && contraseñaField.getText().equals("Escriba su contraseña aquí")) {
            contraseñaField.setText("");
            contraseñaField.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if (fe.getSource() == nombreField && nombreField.getText().isEmpty()) {
            nombreField.setText("Escriba su nombre aquí");
            nombreField.setForeground(Color.GRAY);
        }
        if (fe.getSource() == contraseñaField && contraseñaField.getText().isEmpty()) {
            contraseñaField.setText("Escriba su contraseña aquí");
            contraseñaField.setForeground(Color.GRAY);
        }
    }
}

    