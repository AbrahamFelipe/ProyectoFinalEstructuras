/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javainventario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javainventario.CategoriaJframe;
import javainventario.Clientes;
import javainventario.Inicio;
import static JavaInventario.Login.My_User_Connected;
import javainventario.Orden_de_Compra;
import javainventario.ProductoJframe;
import javainventario.Usuarios;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jessi
 */
public class Historial extends javax.swing.JFrame {

    /**
     * Creates new form Orden_de_Compra
     */

//
    public Historial() {
        initComponents();
        metOrden_Compra();

    }

    ArrayList my_ID = new ArrayList<String>();
    ArrayList my_clientes = new ArrayList<String>();
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;    
    
    public void metOrden_Compra(){
        
        try{
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemainventario", "root", "");
            statement = connection.createStatement();
            DefaultTableModel model = new DefaultTableModel();
            Tabla_Orden_de_Compra_total.setModel(model);
           
            String sql = "SELECT ID, Cliente, Producto, Cantidad, Precios, Usuario FROM orden_compras ";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery("SELECT * FROM orden_compras");
            
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            model.addColumn("ID Orden");
            model.addColumn("Cliente");    
            model.addColumn("Productos");
            model.addColumn("Cantidades");
            model.addColumn("Precios");
            model.addColumn("Usuario");
            

            while(rs.next()){
                
                
                if (my_clientes.contains(rs.getString("Cliente")) == false) my_clientes.add(rs.getString("Cliente"));
                
                my_ID.add(rs.getString("ID"));
                
                Object[] filas = new Object[cantidadColumnas];
              
                for(int i = 0; i < cantidadColumnas; i++){
                    
                    filas[i] = rs.getObject(i + 1);
                    
                }
                
                model.addRow(filas);
      
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "No se cargó");
        }
    }

    
//Mostrando contenido en la tabla de resumen
    public void vista_compra(){
        
        String[] My_order_print = new String[3];
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemainventario", "root", "");
            statement = connection.createStatement();
            rs = statement.executeQuery("Select * FROM orden_compras WHERE ID = '" + ID_Consulta_por_Orden.getText() + "'");
            
            while(rs.next()){
                
                My_order_print[0] = rs.getString("Productos");
                My_order_print[1] = rs.getString("Cantidad");
                My_order_print[2] = rs.getString("Precios");
            }
            
            String[] productos_imprimir = My_order_print[0].split(", ");
            String[] cantidades_imprimir = My_order_print[1].split(", ");
            String[] precios_imprimir = My_order_print[2].split(", ");
            
            String[][] for_table = new String[(productos_imprimir.length)][4];
            
            int total_total = 0;
            
            for (int i = 0; i < productos_imprimir.length; i++){
                
                for_table[i][0] = productos_imprimir[i];
                for_table[i][1] = cantidades_imprimir[i];
                for_table[i][2] = precios_imprimir[i];
                int my_precio = Integer.parseInt(precios_imprimir[i]);
                int my_cantidad = Integer.parseInt(cantidades_imprimir[i]);
                for_table[i][3] = (my_cantidad * my_precio) + "";
                total_total += my_cantidad * my_precio;
            }
            Tabla_Mostrar.setModel(new javax.swing.table.DefaultTableModel(
          
                for_table,
                new String [] {
                "Numero", "Producto", "Cantidad", "Precio"
            }
            ));
            numero_orden.setText("Total: " + ID_Consulta_por_Orden.getText());
            total_orden.setText("Total: " + total_total);
        }catch(SQLException ex){

        }
    } 

    public void consultar_nombre_cliente(){
        
        int total = 0;

        if (ID_Consulta_por_Cliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Inserte el nombre del cliente");
        }else {       

        String busqueda = ID_Consulta_por_Cliente.getText();
        String where = "";
        
        if(!"".equals(busqueda)){
            where = "WHERE Cliente = '" + busqueda + "'";
        } 
        try{
            DefaultTableModel model = new DefaultTableModel();
            Tabla_OC.setModel(model);
           
            String sql = "SELECT ID, Cliente, Producto, Cantidad, Precios, Usuario FROM orden_compras " + where;
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery("SELECT * FROM orden_compras WHERE Cliente = '" + busqueda + "'");
            
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            model.addColumn("ID Orden");
            model.addColumn("Cliente");    
            model.addColumn("Productos");
            model.addColumn("Cantidades");
            model.addColumn("Precios");
            model.addColumn("Usuario");
            

            while(rs.next()){
                
                Object[] filas = new Object[cantidadColumnas];
              
                for(int i = 0; i < cantidadColumnas; i++){
                    
                    filas[i] = rs.getObject(i + 1);
                
            total++;

    
                }
                model.addRow(filas);
            
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "No se cargó");
        }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_Orden_de_Compra = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        label5 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ID_Consulta_por_Cliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ID_Consulta_por_Orden = new javax.swing.JTextField();
        Consultar_Orden = new javax.swing.JButton();
        Consultar_Cliente = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla_Mostrar = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        Historial_Boton = new java.awt.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tabla_Orden_de_Compra_total = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        Tabla_OC = new javax.swing.JTable();
        total_orden = new javax.swing.JLabel();
        numero_orden = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ProductoBoton = new java.awt.Button();
        Inicio = new java.awt.Button();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        java.awt.Button CategoriaBoton = new java.awt.Button();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Cliente = new java.awt.Button();
        OrdenCompra = new java.awt.Button();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Usuario_Boton = new java.awt.Button();
        jLabel14 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        Tabla_Orden_de_Compra = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Tabla_Orden_de_Compra.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        Tabla_Orden_de_Compra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Orden", "Cliente", "Productos", "Precios", "Cantidades", "Usuario"
            }
        ));
        Tabla_Orden_de_Compra.getTableHeader().setReorderingAllowed(false);
        Tabla_Orden_de_Compra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_Orden_de_CompraMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla_Orden_de_Compra);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MyStock");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(979, 476));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label5.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        label5.setForeground(new java.awt.Color(51, 102, 255));
        label5.setText("Historial de Compras");
        jPanel1.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel2.setText("Registros Orden de Compra");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel3.setText("Detalle Orden de Compra");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel4.setText("Cliente");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, -1));

        ID_Consulta_por_Cliente.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jPanel1.add(ID_Consulta_por_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 120, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel5.setText("ID Orden");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, -1, -1));

        ID_Consulta_por_Orden.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jPanel1.add(ID_Consulta_por_Orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 120, -1));

        Consultar_Orden.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Consultar_Orden.setText("Consultar Orden");
        Consultar_Orden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Consultar_OrdenMouseClicked(evt);
            }
        });
        jPanel1.add(Consultar_Orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, -1, -1));

        Consultar_Cliente.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Consultar_Cliente.setText("Consultar Cliente");
        Consultar_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Consultar_ClienteMouseClicked(evt);
            }
        });
        jPanel1.add(Consultar_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel7.setText("Búsqueda Orden de Compra");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, -1, -1));
        jLabel7.getAccessibleContext().setAccessibleName("Órdenes de Compra");

        Tabla_Mostrar = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Tabla_Mostrar.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        Tabla_Mostrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio", "Total"
            }
        ));
        Tabla_Mostrar.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(Tabla_Mostrar);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, 283, 101));

        jButton4.setBackground(new java.awt.Color(102, 102, 255));
        jButton4.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        jButton4.setText("?");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 580, -1, -1));

        Historial_Boton.setActionCommand("Usuario");
        Historial_Boton.setBackground(new java.awt.Color(0, 51, 204));
        Historial_Boton.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Historial_Boton.setForeground(new java.awt.Color(255, 255, 255));
        Historial_Boton.setLabel("Historial");
        Historial_Boton.setMinimumSize(new java.awt.Dimension(112, 27));
        Historial_Boton.setName(""); // NOI18N
        jPanel1.add(Historial_Boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 120, 40));

        Tabla_Orden_de_Compra_total = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Tabla_Orden_de_Compra_total.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        Tabla_Orden_de_Compra_total.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Cliente", "Productos", "Cantidades", "Precios"
            }
        ));
        Tabla_Orden_de_Compra_total.getTableHeader().setReorderingAllowed(false);
        Tabla_Orden_de_Compra_total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_Orden_de_Compra_totalMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Tabla_Orden_de_Compra_total);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, 123));

        Tabla_OC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Tabla_OC.setFont(new java.awt.Font("Maiandra GD", 1, 12)); // NOI18N
        Tabla_OC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Productos", "Cantidades", "Precios"
            }
        ));
        Tabla_OC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_OCMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(Tabla_OC);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, -1, 117));

        total_orden.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        total_orden.setText("Total: 0");
        jPanel1.add(total_orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 420, -1, -1));

        numero_orden.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        numero_orden.setText("Orden num: ");
        jPanel1.add(numero_orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 420, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/caja_icono.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

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

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/home_icon.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Captura de pantalla 2022-05-12 141529.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 60, 120));

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

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/category_icon.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/custome_icon.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

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

        OrdenCompra.setActionCommand("Orden de compra");
        OrdenCompra.setBackground(new java.awt.Color(0, 51, 204));
        OrdenCompra.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        OrdenCompra.setForeground(new java.awt.Color(255, 255, 255));
        OrdenCompra.setLabel("Orden de compra");
        OrdenCompra.setMinimumSize(new java.awt.Dimension(112, 27));
        OrdenCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OrdenCompraMouseClicked(evt);
            }
        });
        jPanel1.add(OrdenCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 120, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/check_list_icon.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 20, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user_icon.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 20, 30));

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

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/historial_icon.png"))); // NOI18N
        jLabel14.setToolTipText("");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1061, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void Consultar_OrdenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Consultar_OrdenMouseClicked
        // TODO add your handling code here:
        if ((ID_Consulta_por_Orden.getText().isEmpty()) == false && my_ID.contains(ID_Consulta_por_Orden.getText()) == true){
            try{
                int a = Integer.parseInt(ID_Consulta_por_Orden.getText());
                vista_compra();

            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Tipo de dato Inválido");
            }
        }else JOptionPane.showMessageDialog(this, "Inserte un ID válido");
    
    }//GEN-LAST:event_Consultar_OrdenMouseClicked

    private void Tabla_Orden_de_CompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_Orden_de_CompraMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_Tabla_Orden_de_CompraMouseClicked

    private void Consultar_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Consultar_ClienteMouseClicked
        // TODO add your handling code here:
        
        if (my_clientes.contains(ID_Consulta_por_Cliente.getText()) == true){
            try{
                consultar_nombre_cliente();

            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Tipo de dato Inválido");
            }
        }else JOptionPane.showMessageDialog(this, "Inserte un nombre existente");

    }//GEN-LAST:event_Consultar_ClienteMouseClicked

    private void Tabla_OCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_OCMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)Tabla_OC.getModel();
        int MyIndex = Tabla_OC.getSelectedRow();
        ID_Consulta_por_Orden.setText(model.getValueAt(MyIndex, 0).toString());
        ID_Consulta_por_Cliente.setText(model.getValueAt(MyIndex, 1).toString());
    }//GEN-LAST:event_Tabla_OCMouseClicked

    private void Tabla_Orden_de_Compra_totalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_Orden_de_Compra_totalMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)Tabla_Orden_de_Compra_total.getModel();
        int MyIndex = Tabla_Orden_de_Compra_total.getSelectedRow();
        ID_Consulta_por_Orden.setText(model.getValueAt(MyIndex, 0).toString());
        ID_Consulta_por_Cliente.setText(model.getValueAt(MyIndex, 1).toString());
    }//GEN-LAST:event_Tabla_Orden_de_Compra_totalMouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "En esta pestaña puede consultar la información sobre \nlos registros almacenados en orden de compra");
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ProductoBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductoBotonMouseClicked
        // TODO add your handling code here:
        ProductoJframe jfprod =new ProductoJframe();
        jfprod.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ProductoBotonMouseClicked

    private void InicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InicioMouseClicked
        // TODO add your handling code here:
        Inicio jfInicio =new Inicio();
        jfInicio.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_InicioMouseClicked

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

    private void OrdenCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrdenCompraMouseClicked
        // TODO add your handling code here:
        Orden_de_Compra odp = new Orden_de_Compra();
        odp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_OrdenCompraMouseClicked

    private void Usuario_BotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Usuario_BotonMouseClicked
        // TODO add your handling code here:
        if (My_User_Connected.equals("Administrador")){
            Usuarios My_User = new Usuarios();
            My_User.setVisible(true);
            this.setVisible(false);
        }else JOptionPane.showMessageDialog(this,"Usted no tiene acceso a esta sección");
    }//GEN-LAST:event_Usuario_BotonMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
                new Historial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button Cliente;
    private javax.swing.JButton Consultar_Cliente;
    private javax.swing.JButton Consultar_Orden;
    private java.awt.Button Historial_Boton;
    private javax.swing.JTextField ID_Consulta_por_Cliente;
    private javax.swing.JTextField ID_Consulta_por_Orden;
    private java.awt.Button Inicio;
    private java.awt.Button OrdenCompra;
    private java.awt.Button ProductoBoton;
    private javax.swing.JTable Tabla_Mostrar;
    private javax.swing.JTable Tabla_OC;
    private javax.swing.JTable Tabla_Orden_de_Compra;
    private javax.swing.JTable Tabla_Orden_de_Compra_total;
    private java.awt.Button Usuario_Boton;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label5;
    private javax.swing.JLabel numero_orden;
    private javax.swing.JLabel total_orden;
    // End of variables declaration//GEN-END:variables
}
