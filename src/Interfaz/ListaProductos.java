/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import ConexionSQL.ConexionDefault;
import Estructuras.DoublyLinkedList;
import Estructuras.QueueRef;
import Estructuras.StackArray;
import Estructuras.StackRef;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ABRAHAM
 */
public class ListaProductos extends javax.swing.JFrame {
    
    DefaultTableModel tablaModelo;

    /**
     * Creates new form ListaProductos
     */
    public ListaProductos() {
        initComponents();
        
        //algoritmo para medir tiempo
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        cargarTablaDoublyLinkedList(); // llamamos a la tarea
        time_end = System.currentTimeMillis();
        System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
    }
    
    public void cargarTablaArray(){
        String[] titulos = {"ID","Nombre","Descripcion","Precio","Categoria","cantidad"};
        String[] registros = new String[50];
        
        String sql = "SELECT *From producto";
        tablaModelo = new DefaultTableModel(null,titulos);
        
        ConexionDefault conectar = new ConexionDefault();
        Connection conect = conectar.openConnection();
        
        try {

            Statement  st = ( Statement) conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("ID");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Descripcion");
                registros[3] = rs.getString("Precio");
                registros[4] = rs.getString("ID_Categoria");
                registros[5] = rs.getString("Cantidad_inventario");
                tablaModelo.addRow(registros);
            }
            Productos_tabla.setModel(tablaModelo);
            
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion");
        }
    
    }
    public void cargarTablaDoublyLinkedList() {
        String[] titulos = {"ID", "Nombre", "Descripcion", "Precio", "Categoria", "cantidad"};
        DoublyLinkedList<String> registros = new DoublyLinkedList<>();

        String sql = "SELECT *From producto";
        tablaModelo = new DefaultTableModel(null, titulos);
        
        //esto establece conexion con la base de datos
        ConexionDefault conectar = new ConexionDefault();
        Connection conect = conectar.openConnection();
        String[] arrayRegistros = new String[10];

        try {
            int contador = 0;
            int numPrueba = 10000;
            Statement st = (Statement) conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (contador<numPrueba && rs.next()) {
                registros.addLast(rs.getString("ID"));
                registros.addLast(rs.getString("Nombre"));
                registros.addLast(rs.getString("Descripcion"));
                registros.addLast(rs.getString("Precio"));
                registros.addLast(rs.getString("ID_Categoria"));
                registros.addLast(rs.getString("Cantidad_inventario"));
                arrayRegistros[0] = registros.deleteFirst();
                arrayRegistros[1] = registros.deleteFirst();
                arrayRegistros[2] = registros.deleteFirst();
                arrayRegistros[3] = registros.deleteFirst();
                arrayRegistros[4] = registros.deleteFirst();
                arrayRegistros[5] = registros.deleteFirst();
                contador++;
                tablaModelo.addRow(arrayRegistros);
            }
            Productos_tabla.setModel(tablaModelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion");
        }
        

    }
    public void cargarTablaQueueRef() {
        String[] titulos = {"ID", "Nombre", "Descripcion", "Precio", "Categoria", "cantidad"};
        QueueRef<String> registros = new QueueRef<>();

        String sql = "SELECT *From producto";
        tablaModelo = new DefaultTableModel(null, titulos);

        ConexionDefault conectar = new ConexionDefault();
        Connection conect = conectar.openConnection();
        String[] arrayRegistros = new String[10];

        try {
            int contador = 1;
            int numPrueba = 100;
            Statement st = (Statement) conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (contador<numPrueba && rs.next()) {
                registros.enqueue(rs.getString("ID"));
                registros.enqueue(rs.getString("Nombre"));
                registros.enqueue(rs.getString("Descripcion"));
                registros.enqueue(rs.getString("Precio"));
                registros.enqueue(rs.getString("ID_Categoria"));
                registros.enqueue(rs.getString("Cantidad_inventario"));
                arrayRegistros[0] = registros.dequeue();
                arrayRegistros[1] = registros.dequeue();
                arrayRegistros[2] = registros.dequeue();
                arrayRegistros[3] = registros.dequeue();
                arrayRegistros[4] = registros.dequeue();
                //arrayRegistros[5] = registros.dequeue();
                contador++;
                tablaModelo.addRow(arrayRegistros);
            }
            Productos_tabla.setModel(tablaModelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion");
        }
        

    }
    public void cargarTablaStackRef() {
        String[] titulos = {"ID", "Nombre", "Descripcion", "Precio", "Categoria", "cantidad"};
        StackRef<String> registros = new StackRef<>();

        String sql = "SELECT *From producto";
        tablaModelo = new DefaultTableModel(null, titulos);

        //esto establece conexion con la base de datos
        ConexionDefault conectar = new ConexionDefault();
        Connection conect = conectar.openConnection();
        String[] arrayRegistros = new String[10];

        try {
            int contador = 0;
            int numPrueba = 10000;
            Statement st = (Statement) conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (contador < numPrueba && rs.next()) {
                registros.push(rs.getString("ID"));
                registros.push(rs.getString("Nombre"));
                registros.push(rs.getString("Descripcion"));
                registros.push(rs.getString("Precio"));
                registros.push(rs.getString("ID_Categoria"));
                registros.push(rs.getString("Cantidad_inventario"));
                arrayRegistros[5] = registros.pop();
                arrayRegistros[4] = registros.pop();
                arrayRegistros[3] = registros.pop();
                arrayRegistros[2] = registros.pop();
                arrayRegistros[1] = registros.pop();
                arrayRegistros[0] = registros.pop();
                contador++;
                tablaModelo.addRow(arrayRegistros);
            }
            Productos_tabla.setModel(tablaModelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Categoria_Producto = new javax.swing.JComboBox<>();
        Cantidad_Producto = new javax.swing.JTextField();
        Precio = new javax.swing.JTextField();
        Descripcion = new javax.swing.JTextField();
        NombreProducto = new javax.swing.JTextField();
        IDproducto = new javax.swing.JTextField();
        tabla_panel = new javax.swing.JScrollPane();
        Productos_tabla = new javax.swing.JTable();
        btnaniadir = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btniniciopro = new javax.swing.JButton();
        btnproducpro = new javax.swing.JButton();
        btncategoriapro = new javax.swing.JButton();
        boton_Cliente = new javax.swing.JButton();
        Boton_Orden = new javax.swing.JButton();
        Boton_Usuario = new javax.swing.JButton();
        Historial_Boton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("Producto"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jLabel1.setText("ID Producto");

        jLabel2.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jLabel3.setText("Descripción");

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jLabel4.setText("Precio");

        jLabel5.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jLabel5.setText("Cantidad");

        jLabel6.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jLabel6.setText("Categoria");

        Categoria_Producto.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N

        Cantidad_Producto.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N

        Precio.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N

        Descripcion.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N

        NombreProducto.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N

        IDproducto.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N

        tabla_panel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        Productos_tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Productos_tabla.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Productos_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Descripcion", "Precio", "Categoria", "Cantidad"
            }
        ));
        Productos_tabla.getTableHeader().setReorderingAllowed(false);
        Productos_tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Productos_tablaMouseClicked(evt);
            }
        });
        tabla_panel.setViewportView(Productos_tabla);

        btnaniadir.setText("Añadir");
        btnaniadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnaniadirMouseClicked(evt);
            }
        });

        btneditar.setText("Editar");
        btneditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneditarMouseClicked(evt);
            }
        });

        btneliminar.setText("Eliminar");
        btneliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneliminarMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Maiandra GD", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 102, 255));
        jLabel10.setText("Producto");

        btniniciopro.setBackground(new java.awt.Color(51, 102, 255));
        btniniciopro.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        btniniciopro.setForeground(new java.awt.Color(255, 255, 255));
        btniniciopro.setText("Inicio");
        btniniciopro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btninicioproMouseClicked(evt);
            }
        });

        btnproducpro.setBackground(new java.awt.Color(51, 102, 255));
        btnproducpro.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        btnproducpro.setForeground(new java.awt.Color(255, 255, 255));
        btnproducpro.setText("Producto");
        btnproducpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproducproActionPerformed(evt);
            }
        });

        btncategoriapro.setBackground(new java.awt.Color(51, 102, 255));
        btncategoriapro.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        btncategoriapro.setForeground(new java.awt.Color(255, 255, 255));
        btncategoriapro.setText("Categoría");
        btncategoriapro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncategoriaproActionPerformed(evt);
            }
        });

        boton_Cliente.setBackground(new java.awt.Color(51, 102, 255));
        boton_Cliente.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        boton_Cliente.setForeground(new java.awt.Color(255, 255, 255));
        boton_Cliente.setText("Cliente");
        boton_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boton_ClienteMouseClicked(evt);
            }
        });

        Boton_Orden.setBackground(new java.awt.Color(51, 102, 255));
        Boton_Orden.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        Boton_Orden.setForeground(new java.awt.Color(255, 255, 255));
        Boton_Orden.setText("Orden de compra");
        Boton_Orden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Boton_OrdenMouseClicked(evt);
            }
        });

        Boton_Usuario.setBackground(new java.awt.Color(51, 102, 255));
        Boton_Usuario.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        Boton_Usuario.setForeground(new java.awt.Color(255, 255, 255));
        Boton_Usuario.setText("Usuario");
        Boton_Usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Boton_UsuarioMouseClicked(evt);
            }
        });

        Historial_Boton.setBackground(new java.awt.Color(51, 102, 255));
        Historial_Boton.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        Historial_Boton.setForeground(new java.awt.Color(255, 255, 255));
        Historial_Boton.setText("Historial");
        Historial_Boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Historial_BotonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Boton_Orden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boton_Cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncategoriapro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnproducpro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btniniciopro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Boton_Usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Historial_Boton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Descripcion)
                            .addComponent(NombreProducto)
                            .addComponent(IDproducto)
                            .addComponent(Cantidad_Producto)
                            .addComponent(Precio)
                            .addComponent(Categoria_Producto, 0, 139, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabla_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnaniadir)
                                .addGap(30, 30, 30)
                                .addComponent(btneditar)
                                .addGap(31, 31, 31)
                                .addComponent(btneliminar)
                                .addGap(54, 54, 54)))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(29, 29, 29))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(IDproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btniniciopro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(NombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnproducpro))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(Cantidad_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(Categoria_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(btncategoriapro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boton_Cliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Boton_Orden)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Boton_Usuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Historial_Boton))))
                    .addComponent(tabla_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnaniadir)
                    .addComponent(btneditar)
                    .addComponent(btneliminar))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 894, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Productos_tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Productos_tablaMouseClicked

    }//GEN-LAST:event_Productos_tablaMouseClicked

    private void btnaniadirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaniadirMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnaniadirMouseClicked

    private void btneditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditarMouseClicked

    }//GEN-LAST:event_btneditarMouseClicked

    private void btneliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneliminarMouseClicked

    }//GEN-LAST:event_btneliminarMouseClicked

    private void btninicioproMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btninicioproMouseClicked

    }//GEN-LAST:event_btninicioproMouseClicked

    private void btnproducproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproducproActionPerformed

    }//GEN-LAST:event_btnproducproActionPerformed

    private void btncategoriaproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncategoriaproActionPerformed

    }//GEN-LAST:event_btncategoriaproActionPerformed

    private void boton_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_ClienteMouseClicked

    }//GEN-LAST:event_boton_ClienteMouseClicked

    private void Boton_OrdenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_OrdenMouseClicked

    }//GEN-LAST:event_Boton_OrdenMouseClicked

    private void Boton_UsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_UsuarioMouseClicked


    }//GEN-LAST:event_Boton_UsuarioMouseClicked

    private void Historial_BotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Historial_BotonMouseClicked

    }//GEN-LAST:event_Historial_BotonMouseClicked

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
            java.util.logging.Logger.getLogger(ListaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaProductos().setVisible(true);
            }
        });
        
        
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_Orden;
    private javax.swing.JButton Boton_Usuario;
    private javax.swing.JTextField Cantidad_Producto;
    private javax.swing.JComboBox<String> Categoria_Producto;
    private javax.swing.JTextField Descripcion;
    private javax.swing.JButton Historial_Boton;
    private javax.swing.JTextField IDproducto;
    private javax.swing.JTextField NombreProducto;
    private javax.swing.JTextField Precio;
    private javax.swing.JTable Productos_tabla;
    private javax.swing.JButton boton_Cliente;
    private javax.swing.JButton btnaniadir;
    private javax.swing.JButton btncategoriapro;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btniniciopro;
    private javax.swing.JButton btnproducpro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane tabla_panel;
    // End of variables declaration//GEN-END:variables
}
