/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javainventario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static JavaInventario.Login.My_User_Connected;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.util.ArrayList;

/**
 *
 * @author Juan Naranjo
 */
public class Orden_de_Compra extends javax.swing.JFrame {

    /**
     * Creates new form Orden_de_Compra
     */
    public boolean editar_my_table = false;
    public ArrayList <String []> total_table = new ArrayList<String []>();
    
    public Orden_de_Compra() {
        initComponents();
        metOrden();
        metProducto();
        get_ID_Order();
    }
    ArrayList my_clientes = new ArrayList<String>();
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    
    public void metOrden(){
        
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemainventario", "root", "");
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT ID, Nombre, Telefono FROM cliente");
            while (rs.next()) my_clientes.add(rs.getString(("Nombre")));
            rs = statement.executeQuery("SELECT ID, Nombre, Telefono FROM cliente");
            Tabla_Clientes_Orden.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(SQLException ex){
            
        }
    }
    
    public void metProducto(){
        
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemainventario", "root", "");
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM producto");
            Tabla_Productos_Orden.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(SQLException ex){
            
        }
    }
    
    private boolean Es_Repetido(String Nombre_Producto_Actual){
        
        for (int f = 0; f < total_table.size(); f++)
            
            if (Nombre_Producto_Actual.equals((total_table.get(f))[1])){
                
                return true;
            }
        return false;
    }
    
    private void get_ID_Order(){
        
        int contador = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemainventario", "root", "");
            statement = connection.createStatement();
            rs = statement.executeQuery("Select * FROM Orden_Compras");
            while(rs.next()){
                contador += 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de conexion");
        }
        Numero_Orden.setText("Orden num: " + (contador + 1));
        
    }
    
    
    private void obtener_producto(){
        
        String[] My_Product = new String[4];
        int MyValue = -1;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemainventario", "root", "");
            statement = connection.createStatement();
            rs = statement.executeQuery("Select * FROM PRODUCTO WHERE Nombre = '" + Nombre_Producto_Orden.getText() + "'");
            while(rs.next()){
                
                My_Product[0] = rs.getString("ID");
                My_Product[1] = rs.getString("Nombre");
                My_Product[2] = ID_Cantidad_Orden.getText();
                My_Product[3] = rs.getString("Precio");
                MyValue = rs.getInt("Cantidad_Inventario");
            }
            
        }catch(SQLException ex){
            
        }
        
        if (My_Product[0] != null){
            
            boolean first_condition = Es_Repetido(My_Product[1]);
            if ((first_condition == false) && (Integer.valueOf(ID_Cantidad_Orden.getText()) <= MyValue) && (Integer.valueOf(ID_Cantidad_Orden.getText()) > 0)){
         
                total_table.add(My_Product);
                editar_my_table = true;
                
            }else if(first_condition == true) JOptionPane.showMessageDialog(this, "El producto ya se añadió");
            else if((Integer.valueOf(ID_Cantidad_Orden.getText()) > MyValue) || (Integer.valueOf(ID_Cantidad_Orden.getText()) <= 0)){
                
                JOptionPane.showMessageDialog(this, "Introduzca una cantidad \nque exista en el inventario \nmayor a 0");
            }
            
        }else JOptionPane.showMessageDialog(this, "El producto que quiere \nañadir no existe");
    }
    
    private String[][] obtener_matriz(){
        
        String[][] parcial = new String[total_table.size()][4];
        
        for (int f = 0; f<= total_table.size()-1; f++){
            
            for (int c = 0; c<= 3; c++){
                
                parcial[f][c] = (total_table.get(f))[c];
                
            }
            
        }
        return parcial;
    }
    
    private boolean condition_integer(){
        
        try {
            Integer.valueOf(ID_Cantidad_Orden.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private void imprimir_total(){
        
        int My_Total = 0;
        for (int f = 0; f < total_table.size(); f++){
            
            My_Total += Integer.valueOf((total_table.get(f))[2]) * Integer.valueOf((total_table.get(f))[3]);
        }
        Total_My_Order.setText("Total: " + My_Total);
    }
    
    public void vista_compra(){
        
        boolean MyInt_condition = condition_integer();
        
        if (Nombre_Producto_Orden.getText().isEmpty() || ID_Cantidad_Orden.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Inserte producto y Cantidad");
            
        }else if(MyInt_condition == true){
            
            obtener_producto();
            
            if(editar_my_table == true){

                String[][] prueba = new String[total_table.size()][4];

                prueba = obtener_matriz();

                Tabla_Mostrar.setModel(new javax.swing.table.DefaultTableModel(

                        prueba, new String [] {
                        "ID", "Producto", "Cantidad", "Precio"
                    }
                ));
                imprimir_total();
                editar_my_table = false;
            }
        }else JOptionPane.showMessageDialog(this,"La cantidad debe ser un número entero");
    }
    
    public void vista_post_editar(){
        
        String[][] prueba = new String[total_table.size()][4];

        prueba = obtener_matriz();

        Tabla_Mostrar.setModel(new javax.swing.table.DefaultTableModel(

                prueba, new String [] {
                "ID", "Producto", "Cantidad", "Precio"
            }
        ));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        OrdenCompra = new java.awt.Button();
        label5 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Clientes_Orden = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_Productos_Orden = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        Nombre_Cliente_Orden = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Nombre_Producto_Orden = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ID_Cantidad_Orden = new javax.swing.JTextField();
        Eliminar_Orden = new javax.swing.JButton();
        Aniadir_Producto = new javax.swing.JButton();
        Nueva_Orden = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla_Mostrar = new javax.swing.JTable();
        Numero_Orden = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        Eliminar_Producto = new javax.swing.JButton();
        Total_My_Order = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Inicio = new java.awt.Button();
        jLabel9 = new javax.swing.JLabel();
        ProductoBoton = new java.awt.Button();
        java.awt.Button CategoriaBoton = new java.awt.Button();
        jLabel10 = new javax.swing.JLabel();
        Cliente = new java.awt.Button();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Usuario_Boton = new java.awt.Button();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Historial_Boton = new java.awt.Button();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MyStock");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(979, 476));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        OrdenCompra.setActionCommand("Orden de compra");
        OrdenCompra.setBackground(new java.awt.Color(0, 51, 204));
        OrdenCompra.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        OrdenCompra.setForeground(new java.awt.Color(255, 255, 255));
        OrdenCompra.setLabel("Orden de compra");
        OrdenCompra.setMinimumSize(new java.awt.Dimension(112, 27));
        jPanel1.add(OrdenCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 120, 40));

        label5.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        label5.setForeground(new java.awt.Color(51, 102, 255));
        label5.setText("Orden de Compra");
        jPanel1.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel2.setText("Lista de Clientes:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, -1, -1));

        Tabla_Clientes_Orden = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Tabla_Clientes_Orden.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        Tabla_Clientes_Orden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Teléfono"
            }
        ));
        Tabla_Clientes_Orden.getTableHeader().setReorderingAllowed(false);
        Tabla_Clientes_Orden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_Clientes_OrdenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_Clientes_Orden);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 238, 105));

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel3.setText("Resumen Orden");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 260, -1, -1));

        Tabla_Productos_Orden = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Tabla_Productos_Orden.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        Tabla_Productos_Orden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Descripción", "Preio", "Categoría", "Cantidad"
            }
        ));
        Tabla_Productos_Orden.getTableHeader().setReorderingAllowed(false);
        Tabla_Productos_Orden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_Productos_OrdenMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla_Productos_Orden);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 491, 105));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel4.setText("    Cliente");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, -1, -1));

        Nombre_Cliente_Orden.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jPanel1.add(Nombre_Cliente_Orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 120, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel5.setText("     Producto");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, -1, 20));

        Nombre_Producto_Orden.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jPanel1.add(Nombre_Producto_Orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, 120, -1));

        jLabel6.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel6.setText("Cantidad");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, -1, -1));

        ID_Cantidad_Orden.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jPanel1.add(ID_Cantidad_Orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 120, -1));

        Eliminar_Orden.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Eliminar_Orden.setText("Eliminar Orden");
        Eliminar_Orden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Eliminar_OrdenMouseClicked(evt);
            }
        });
        jPanel1.add(Eliminar_Orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, -1, -1));

        Aniadir_Producto.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Aniadir_Producto.setText("Añadir Prod");
        Aniadir_Producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Aniadir_ProductoMouseClicked(evt);
            }
        });
        jPanel1.add(Aniadir_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, 120, -1));

        Nueva_Orden.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Nueva_Orden.setText("Nueva Orden");
        Nueva_Orden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Nueva_OrdenMouseClicked(evt);
            }
        });
        jPanel1.add(Nueva_Orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, 118, -1));

        jLabel7.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel7.setText("Lista de Productos:");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, -1, -1));

        Tabla_Mostrar = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Tabla_Mostrar.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        Tabla_Mostrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Cantidad", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Tabla_Mostrar.getTableHeader().setReorderingAllowed(false);
        Tabla_Mostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_MostrarMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Tabla_Mostrar);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 290, 283, 101));

        Numero_Orden.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Numero_Orden.setText("Orden num:");
        jPanel1.add(Numero_Orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 400, 92, -1));

        jButton4.setBackground(new java.awt.Color(102, 102, 255));
        jButton4.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        jButton4.setText("?");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 530, -1, -1));

        Eliminar_Producto.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Eliminar_Producto.setText("Elim. Prod");
        Eliminar_Producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Eliminar_ProductoMouseClicked(evt);
            }
        });
        jPanel1.add(Eliminar_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 420, 118, -1));

        Total_My_Order.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Total_My_Order.setText("Total: 0");
        jPanel1.add(Total_My_Order, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 192, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Captura de pantalla 2022-05-12 141529.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 60, 120));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/home_icon.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        Inicio.setActionCommand("Inicio");
        Inicio.setBackground(new java.awt.Color(0, 51, 204));
        Inicio.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Inicio.setForeground(new java.awt.Color(255, 255, 255));
        Inicio.setLabel("Inicio");
        Inicio.setMinimumSize(new java.awt.Dimension(112, 27));
        Inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InicioMouseClicked(evt);
            }
        });
        jPanel1.add(Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 120, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/caja_icono.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        ProductoBoton.setActionCommand("Producto");
        ProductoBoton.setBackground(new java.awt.Color(0, 51, 204));
        ProductoBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProductoBoton.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        ProductoBoton.setForeground(new java.awt.Color(255, 255, 255));
        ProductoBoton.setLabel("Producto");
        ProductoBoton.setMinimumSize(new java.awt.Dimension(112, 27));
        ProductoBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductoBotonMouseClicked(evt);
            }
        });
        jPanel1.add(ProductoBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 120, 40));

        CategoriaBoton.setActionCommand("Categoría");
        CategoriaBoton.setBackground(new java.awt.Color(0, 51, 204));
        CategoriaBoton.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        CategoriaBoton.setForeground(new java.awt.Color(255, 255, 255));
        CategoriaBoton.setLabel("Categoría");
        CategoriaBoton.setMinimumSize(new java.awt.Dimension(112, 27));
        CategoriaBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CategoriaBotonMouseClicked(evt);
            }
        });
        jPanel1.add(CategoriaBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 120, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/category_icon.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        Cliente.setActionCommand("Cliente");
        Cliente.setBackground(new java.awt.Color(0, 51, 204));
        Cliente.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Cliente.setForeground(new java.awt.Color(255, 255, 255));
        Cliente.setLabel("Cliente");
        Cliente.setMinimumSize(new java.awt.Dimension(112, 27));
        Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClienteActionPerformed(evt);
            }
        });
        jPanel1.add(Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 120, 40));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/custome_icon.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/check_list_icon.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 20, 30));

        Usuario_Boton.setActionCommand("Usuario");
        Usuario_Boton.setBackground(new java.awt.Color(0, 51, 204));
        Usuario_Boton.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Usuario_Boton.setForeground(new java.awt.Color(255, 255, 255));
        Usuario_Boton.setLabel("Usuario");
        Usuario_Boton.setMinimumSize(new java.awt.Dimension(112, 27));
        Usuario_Boton.setName(""); // NOI18N
        Usuario_Boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Usuario_BotonMouseClicked(evt);
            }
        });
        jPanel1.add(Usuario_Boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 120, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user_icon.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 20, 30));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/historial_icon.png"))); // NOI18N
        jLabel15.setToolTipText("");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

        Historial_Boton.setActionCommand("Historial");
        Historial_Boton.setBackground(new java.awt.Color(0, 51, 204));
        Historial_Boton.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Historial_Boton.setForeground(new java.awt.Color(255, 255, 255));
        Historial_Boton.setLabel("Historial");
        Historial_Boton.setMinimumSize(new java.awt.Dimension(112, 27));
        Historial_Boton.setName(""); // NOI18N
        Historial_Boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Historial_BotonMouseClicked(evt);
            }
        });
        jPanel1.add(Historial_Boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 120, 40));

        jTextField1.setBackground(new java.awt.Color(0, 51, 204));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 270, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Tabla_Productos_OrdenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_Productos_OrdenMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)Tabla_Productos_Orden.getModel();
        int MyIndex = Tabla_Productos_Orden.getSelectedRow();
        Nombre_Producto_Orden.setText(model.getValueAt(MyIndex, 1).toString());
        ID_Cantidad_Orden.setText(model.getValueAt(MyIndex, 5).toString());
    }//GEN-LAST:event_Tabla_Productos_OrdenMouseClicked

    private void Tabla_Clientes_OrdenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_Clientes_OrdenMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)Tabla_Clientes_Orden.getModel();
        int MyIndex = Tabla_Clientes_Orden.getSelectedRow();
        Nombre_Cliente_Orden.setText(model.getValueAt(MyIndex, 1).toString());
    }//GEN-LAST:event_Tabla_Clientes_OrdenMouseClicked

    private void Tabla_MostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_MostrarMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)Tabla_Mostrar.getModel();
        int MyIndex = Tabla_Mostrar.getSelectedRow();
        Nombre_Producto_Orden.setText(model.getValueAt(MyIndex, 1).toString());
    }//GEN-LAST:event_Tabla_MostrarMouseClicked

    private void Aniadir_ProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Aniadir_ProductoMouseClicked
        // TODO add your handling code here:
        vista_compra();
    }//GEN-LAST:event_Aniadir_ProductoMouseClicked

    private void Eliminar_OrdenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Eliminar_OrdenMouseClicked
        // TODO add your handling code here:
        total_table = new ArrayList<String []>();
        Nombre_Producto_Orden.setText("");
        ID_Cantidad_Orden.setText("");
        vista_post_editar();
        Total_My_Order.setText("Total: 0");
    }//GEN-LAST:event_Eliminar_OrdenMouseClicked

    private void Eliminar_ProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Eliminar_ProductoMouseClicked
        // TODO add your handling code here:
        String Producto_Eliminar = Nombre_Producto_Orden.getText();
        for (int f = 0; f < total_table.size(); f++){
            
            if ((total_table.get(f))[1].contains(Producto_Eliminar)) total_table.remove(f);
        }
        vista_post_editar();
        imprimir_total();
    }//GEN-LAST:event_Eliminar_ProductoMouseClicked

    private String[] obtener_cantidad_productos_originales(String [] lista_productos_editar){
        
        String[] cantidades = new String[lista_productos_editar.length];
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemainventario", "root", "");
            statement = connection.createStatement();
            for (int f = 0; f < total_table.size(); f++){
                rs = statement.executeQuery("SELECT * FROM producto WHERE Nombre = '" + lista_productos_editar[f] + "'");
                while(rs.next()){
                
                    String Product_quantity_old = rs.getString("Cantidad_Inventario");
                    cantidades[f] = Product_quantity_old;
                }
            }
            
        } catch (Exception e) {
        }
        return cantidades;
    }
    
    private void editar_producto(String [] lista_productos_editar){
        
        String[] Cantidad_original = obtener_cantidad_productos_originales(lista_productos_editar);
        
        try {
        
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemainventario", "root", "");
            for (int i = 0; i < total_table.size(); i++){
                PreparedStatement statement = connection.prepareStatement("UPDATE producto SET Cantidad_Inventario = ? WHERE Nombre = ?");
                statement.setInt(1, Integer.valueOf(Cantidad_original[i]) - Integer.valueOf((total_table.get(i))[2]));
                statement.setString(2, lista_productos_editar[i]);

                int row = statement.executeUpdate();
            }
            //metProducto();

        } catch (Exception e) {
        }
    }
    private void Nueva_OrdenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Nueva_OrdenMouseClicked
        // TODO add your handling code here:
        if(total_table.size() == 0) JOptionPane.showMessageDialog(this,"Una orden require que se \nle asignen productos");
        else if (Nombre_Cliente_Orden.getText().isEmpty() || my_clientes.contains(Nombre_Cliente_Orden.getText()) == false) JOptionPane.showMessageDialog(this,"Inserte el cliente al que se le va \na asigar la orden que este en \nla base de datos");
        else{
            String My_Products_Order = "";
            String My_Quantity_Products = "";
            String My_Products_Price = "";
            
            String[] lista_cantidad_editar = new String[total_table.size()];
            String[] lista_productos_editar = new String[total_table.size()];
            
            for (int f = 0; f < total_table.size(); f++){
            
                lista_cantidad_editar[f] = (total_table.get(f))[2];
                lista_productos_editar[f] = (total_table.get(f))[1];
                
                if (f < total_table.size() - 1 ){
                    My_Products_Order += (total_table.get(f))[1] + ", ";
                    My_Quantity_Products += (total_table.get(f))[2] + ", ";
                    My_Products_Price += (total_table.get(f))[3] + ", ";
                }else{
                    My_Products_Order += (total_table.get(f))[1];
                    My_Quantity_Products += (total_table.get(f))[2];
                    My_Products_Price += (total_table.get(f))[3];
                    
                }
            }
            
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemainventario", "root", "");
                
                PreparedStatement add = connection.prepareStatement("INSERT INTO orden_compras VALUES(?,?,?,?,?,?)");
                int s = Integer.valueOf(Numero_Orden.getText().substring(11));
                add.setInt(1, s);
                add.setString(2, Nombre_Cliente_Orden.getText());
                add.setString(3,  My_Products_Order.toString());
                add.setString(4, My_Quantity_Products.toString());
                add.setString(5, My_Products_Price.toString());
                add.setString(6, My_User_Connected);
                int row = add.executeUpdate();
                
                editar_producto(lista_productos_editar);
                total_table = new ArrayList<String []>();
                vista_post_editar();
                metProducto();
                get_ID_Order();
                Total_My_Order.setText("Total: 0");
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error de conexion");
            }
            }
    }//GEN-LAST:event_Nueva_OrdenMouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "En esta sección podrá generar las ordenes de compra \ny ajustar los niveles de inventario de la mercancía.");
    }//GEN-LAST:event_jButton4MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void InicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InicioMouseClicked
        // TODO add your handling code here:
        Inicio jfInicio =new Inicio();
        jfInicio.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_InicioMouseClicked

    private void ProductoBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductoBotonMouseClicked
        // TODO add your handling code here:
        ProductoJframe jfprod =new ProductoJframe();
        jfprod.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ProductoBotonMouseClicked

    private void CategoriaBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CategoriaBotonMouseClicked
        // TODO add your handling code here:
        CategoriaJframe jfcat =new CategoriaJframe();
        jfcat.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_CategoriaBotonMouseClicked

    private void ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteActionPerformed
        // TODO add your handling code here:
        Clientes jfCliente =new Clientes();
        jfCliente.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ClienteActionPerformed

    private void Usuario_BotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Usuario_BotonMouseClicked
        // TODO add your handling code here:
        if (My_User_Connected.equals("Administrador")){
            Usuarios My_User = new Usuarios();
            My_User.setVisible(true);
            this.setVisible(false);
        }else JOptionPane.showMessageDialog(this,"Usted no tiene acceso a esta sección");
    }//GEN-LAST:event_Usuario_BotonMouseClicked

    private void Historial_BotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Historial_BotonMouseClicked
        // TODO add your handling code here:
        Historial jfHistorial =new Historial();
        jfHistorial.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Historial_BotonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Orden_de_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Orden_de_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Orden_de_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Orden_de_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Orden_de_Compra().setVisible(true);
            }
        });
    } //finish

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aniadir_Producto;
    private java.awt.Button Cliente;
    private javax.swing.JButton Eliminar_Orden;
    private javax.swing.JButton Eliminar_Producto;
    private java.awt.Button Historial_Boton;
    private javax.swing.JTextField ID_Cantidad_Orden;
    private java.awt.Button Inicio;
    private javax.swing.JTextField Nombre_Cliente_Orden;
    private javax.swing.JTextField Nombre_Producto_Orden;
    private javax.swing.JButton Nueva_Orden;
    private javax.swing.JLabel Numero_Orden;
    private java.awt.Button OrdenCompra;
    private java.awt.Button ProductoBoton;
    private javax.swing.JTable Tabla_Clientes_Orden;
    private javax.swing.JTable Tabla_Mostrar;
    private javax.swing.JTable Tabla_Productos_Orden;
    private javax.swing.JLabel Total_My_Order;
    private java.awt.Button Usuario_Boton;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label5;
    // End of variables declaration//GEN-END:variables
}
