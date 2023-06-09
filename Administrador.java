
package ClasesOperativas;
import Conexion.RellenarCombo;
import Conexion.Conexion;
import Conexion.ConexionUsuarios;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Administrador extends javax.swing.JFrame {

DefaultTableModel mo= new DefaultTableModel();
DefaultTableModel mo1= new DefaultTableModel();
DefaultTableModel mo2= new DefaultTableModel();
DefaultTableModel mo3= new DefaultTableModel();
DefaultTableModel mo4= new DefaultTableModel();

RellenarCombo trabajos= new RellenarCombo();
RellenarCombo trabajos1= new RellenarCombo();

LocalDate todaysDate = LocalDate.now();
LocalTime hora= LocalTime.now();

private final String ruta=System.getProperties().getProperty("user.dir");

public Administrador() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        String[] Titulo={"NOMBRE DEL PRODUCTO " , " PRECIO "," STOCK "};
        mo.setColumnIdentifiers(Titulo);
        tabla.setModel(mo);
        
        String[] TituloE={"USUARIO", "CONSTRASEÑA", "COMPROBAR CONTRASEÑA", "PUESTO"};
        mo1.setColumnIdentifiers(TituloE);
        tablausuario.setModel(mo1);
        
       trabajos.RellenarComboBox("puesto","nombre", trabajo);
       trabajos1.RellenarComboBox("puesto","nombre", trabajoactualizar);
       
        Escritorio.setEnabledAt(0, false);
        Escritorio.setEnabledAt(1, false);
        Escritorio.setEnabledAt(2, false);
        Escritorio.setEnabledAt(3, false);
        Escritorio.setEnabledAt(4, false);
        this.setTitle("PANADERIA AMANECER");
        setIconImage(getIconImage());
    }
    
     public Image getIconImage ()
    {
        Image retValue=Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Elementos/panaderia.png"));
        return retValue;
    }
    public void Cargar()
    {
        String [] Actualizar= {" ID-PRODUCTO ", " NOMBRE DEL PRODUCTO ", "PRECIO ", " STOCK "};
        String [] Registro= new String[50];
        
        String sql="Select *from producto";
        mo2= new DefaultTableModel(null, Actualizar);
        ConexionUsuarios cu= new ConexionUsuarios(); 
        Connection conect=cu.conectar();
        try
        {
            Statement st=(Statement) conect.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next())
            {
                Registro[0]=rs.getString("idProducto");
                Registro[1]=rs.getString("nombre");
                Registro[2]=rs.getString("precio");
                Registro[3]=rs.getString("stock");
                mo2.addRow(Registro);
            }
            actualizat.setModel(mo2);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error" + e);
        }
    }
    public void CargarUsuarios()
    {
        String [] Actualizar= {" ID-USUARIO ", " NOMBRE DEL USUARIO ", "CONTRASEÑA ", " iD-PUESTO "};
        String [] Registro= new String[50];
        
        String sql="Select *from usuarios";
        mo2= new DefaultTableModel(null, Actualizar);
        ConexionUsuarios cu= new ConexionUsuarios(); 
        Connection conect=cu.conectar();
        try
        {
            Statement st=(Statement) conect.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next())
            {
                Registro[0]=rs.getString("idUsuarios");
                Registro[1]=rs.getString("nombre");
                Registro[2]=rs.getString("contraseña");
                Registro[3]=rs.getString("idPuesto");
                mo2.addRow(Registro);
            }
            tablaactualizar.setModel(mo2);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error" + e);
        }
    }
    
     public void Abrir(String Nombre)
    {
    try
    {
    File path= new File(Nombre + ".pdf");
    Desktop.getDesktop().open(path);
    }catch(Exception ex)
    {
    JOptionPane.showMessageDialog(null,ex, "ATENCION", 2);
    }
    }
     
     
     
    public void CargarVENTAS()
    {
        String [] Actualizar= {" ID-Venta ", " FECHA", "MONTO ", " VENDEDOR"};
        String [] Registro= new String[50];
        
        String sql="Select *from venta";
        mo2= new DefaultTableModel(null, Actualizar);
        ConexionUsuarios cu= new ConexionUsuarios(); 
        Connection conect=cu.conectar();
        try
        {
            Statement st=(Statement) conect.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next())
            {
                Registro[0]=rs.getString("idVenta");
                Registro[1]=rs.getString("fecha");
                Registro[2]=rs.getString("monto");
                Registro[3]=rs.getString("vendedor");
                mo2.addRow(Registro);
            }
            VENTAS.setModel(mo2);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error" + e);
        }
    }
    
    public void CargarClientes()
    {
        String [] Actualizar= {" ID-CLIENTE ", " NOMBRE ", "APELLIDO PATERNO","APELLIDO MATERNO","TELEFONO", "DIRECCIÓN","RFC", "CP","CIUDAD", "CORREO-ELECTRONICO"};
        String [] Registro= new String[50];
        
        String sql="Select *from cliente";
        mo4= new DefaultTableModel(null, Actualizar);
        ConexionUsuarios cu= new ConexionUsuarios(); 
        Connection conect=cu.conectar();
        try
        {
            Statement st=(Statement) conect.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next())
            {
                Registro[0]=rs.getString("idCliente");
                Registro[1]=rs.getString("nombre");
                Registro[2]=rs.getString("apellido_paterno");
                Registro[3]=rs.getString("apellido_materno");
                Registro[4]=rs.getString("telefono");
                Registro[5]=rs.getString("direccion");
                Registro[6]=rs.getString("rfc");
                Registro[7]=rs.getString("cp");
                Registro[8]=rs.getString("ciudad");
                Registro[9]=rs.getString("correoelectronico");
                mo4.addRow(Registro);
            }
            datosclientes.setModel(mo4);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error" + e);
        }
    }
     public void Agregar()
    {
        mo.addRow(new Object []{
        txtproducto.getText(), txtprecio.getText(), txtstcK.getText()
        });
    }
     
    public void ImFactura(String Nombre)throws FileNotFoundException,DocumentException
    {
     String id="" ,FECHA="", MONTO="", VENDEDOR="";
    
        FileOutputStream archivo = new FileOutputStream(Nombre + ".pdf");
        Document documento= new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(1);
        documento.add(parrafo);
        documento.add(new Paragraph ( "                                                              FACTURA                                     "+ "\n"));
        documento.add(new Paragraph ( " NOMBRE DE LA EMPRESA:" + "     PANADERIA AMANECER "+ "\n"));
        documento.add(new Paragraph ( " DIRECCION: "+ "   MELCHOR OCAMPO #115, ZONA CENTRO \n"));
        documento.add(new Paragraph ( " CIUDAD: " + "    SANTA MARIA DEL RIO, SAN LUIS POTOSI"  +"\n"));
        documento.add(new Paragraph ( " RFC: "+ "   OSJO1604010I6 \n"));
        documento.add(new Paragraph ( " TELEFONO: "+ "    4851080403 \n")); 
        documento.add(new Paragraph ( "                                         FECHA: "+ todaysDate +"\n")); 
        documento.add(new Paragraph ( "                                         HORA: "+ hora +"\n"));                                                                                               
        documento.add(new Paragraph ( "                                         TERMINOS: "+ "REPORTE DE VENTAS "+"\n"));  
        documento.add(new Paragraph ("==================================================================== "));
        documento.add(new Paragraph ("                                                "));
        documento.add(new Paragraph ("==================================================================== "));
        documento.add(new Paragraph ("                                 PRODUCTOS COMPRADOS      "+                "" ));
        documento.add(new Paragraph ("   id     |        FECHA             |    \t |   MONTO         \t  |   VENDEDOR \t"));
        documento.add(new Paragraph ("==================================================================== "));
        for(int i=0; i<mo2.getRowCount(); i++)
        {
        id=(VENTAS.getValueAt(i, 0).toString());
        FECHA=(VENTAS.getValueAt(i, 1).toString());
        MONTO=(VENTAS.getValueAt(i, 2).toString());
        VENDEDOR=(VENTAS.getValueAt(i, 3).toString());
        documento.add(new Paragraph( id    +"         " +  FECHA    +   "                          "+   MONTO        +  "                    " +     VENDEDOR  ));     
        }
        documento.add(new Paragraph("========================================================="+ "\n"));
        documento.add(new Paragraph("                                         "+"\n"));
        documento.add(new Paragraph("                                     ¡ GRACIAS POR SU COMPRA !                     "+"\n" ));     
        documento.add(new Paragraph ("      "+ "\n" ));
        documento.add(new Paragraph ("                                                                       "+ "\n" ));
        documento.close();
        JOptionPane.showMessageDialog(null," FACTURA CREADA CORRECTAMENTE");   
    } 
     
     public void Eliminar()
        {
       int fila=tabla.getSelectedRow();
       if(fila>=0)
       {
           mo.removeRow(fila);
       }
       else 
       {
           JOptionPane.showMessageDialog(null, "   NO SELECCIONO NINGUNA FILA O LA TABLA ESTA VACIA " );
       }
       }
     
     public void Agregar1()
    {
        mo1.addRow(new Object []{
        txtnombreempleado.getText(), txtcontraseña.getText(), txtcomprobacion.getText(), trabajo.getSelectedItem()
        });
    }
     
     public void Eliminar1()
        {
       int fila=tablausuario.getSelectedRow();
       if(fila>=0)
       {
           mo1.removeRow(fila);
       }
       else 
       {
           JOptionPane.showMessageDialog(null, "NO SELECCIONO NINGUNA FILA O LA TABLA ESTA VACIA " );
       }
       }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        etqsistema = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnproductos = new LIB.FSButtonMD();
        btnregistro = new LIB.FSButtonMD();
        btncerrarseciones = new LIB.FSButtonMD();
        btnsctuap = new LIB.FSButtonMD();
        btnactusuarios = new LIB.FSButtonMD();
        btnactclientes = new LIB.FSButtonMD();
        btnventa = new LIB.FSButtonMD();
        Escritorio = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        panel = new LIB.FSGradientPanel();
        etqfuncion = new javax.swing.JLabel();
        btnguardar = new LIB.FSButtonMD();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtproducto = new LIB.FSTexFieldMD();
        txtprecio = new LIB.FSTexFieldMD();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtstcK = new LIB.FSTexFieldMD();
        fSButtonMD10 = new LIB.FSButtonMD();
        fSButtonMD11 = new LIB.FSButtonMD();
        fSButtonMD12 = new LIB.FSButtonMD();
        jPanel5 = new javax.swing.JPanel();
        fSGradientPanel2 = new LIB.FSGradientPanel();
        fSTexFieldMD8 = new LIB.FSTexFieldMD();
        btnguardar1 = new LIB.FSButtonMD();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablausuario = new javax.swing.JTable();
        fSButtonMD7 = new LIB.FSButtonMD();
        jPanelTransparente1 = new LIB.JPanelTransparente();
        txtcomprobacion = new LIB.FSTexFieldMD();
        jLabel8 = new javax.swing.JLabel();
        txtcontraseña = new LIB.FSTexFieldMD();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        trabajo = new javax.swing.JComboBox<>();
        txtnombreempleado = new LIB.FSTexFieldMD();
        jLabel7 = new javax.swing.JLabel();
        fSButtonMD8 = new LIB.FSButtonMD();
        jPanel6 = new javax.swing.JPanel();
        fSGradientPanel1 = new LIB.FSGradientPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtidproductoactualizar = new LIB.FSTexFieldMD();
        txtprecioactualizar = new LIB.FSTexFieldMD();
        jLabel26 = new javax.swing.JLabel();
        txtstockactualizar = new LIB.FSTexFieldMD();
        btneliminar1 = new LIB.FSButtonMD();
        btnactualizar1 = new LIB.FSButtonMD();
        jScrollPane2 = new javax.swing.JScrollPane();
        actualizat = new javax.swing.JTable();
        txtnombreproductoactualizar = new LIB.FSTexFieldMD();
        btneliminar2 = new LIB.FSButtonMD();
        fSButtonMD13 = new LIB.FSButtonMD();
        etqfuncion1 = new javax.swing.JLabel();
        fSButtonMD3 = new LIB.FSButtonMD();
        jPanel4 = new javax.swing.JPanel();
        fSGradientPanel3 = new LIB.FSGradientPanel();
        jPanelTransparente2 = new LIB.JPanelTransparente();
        jLabel10 = new javax.swing.JLabel();
        txtcontraseñaactualizar = new LIB.FSTexFieldMD();
        jLabel14 = new javax.swing.JLabel();
        trabajoactualizar = new javax.swing.JComboBox<>();
        txtnombreempleadoactualizar = new LIB.FSTexFieldMD();
        jLabel12 = new javax.swing.JLabel();
        fSTexFieldMD9 = new LIB.FSTexFieldMD();
        fSButtonMD16 = new LIB.FSButtonMD();
        fSButtonMD17 = new LIB.FSButtonMD();
        fSButtonMD19 = new LIB.FSButtonMD();
        jLabel2 = new javax.swing.JLabel();
        txtidusuario = new LIB.FSTexFieldMD();
        fSButtonMD20 = new LIB.FSButtonMD();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaactualizar = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        fSButtonMD2 = new LIB.FSButtonMD();
        jPanel7 = new javax.swing.JPanel();
        Etqid = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        datosclientes = new javax.swing.JTable();
        EtqDireccion1 = new javax.swing.JLabel();
        etqtelefono = new javax.swing.JLabel();
        etqCorreo = new javax.swing.JLabel();
        etqciudad = new javax.swing.JLabel();
        etqrfc1 = new javax.swing.JLabel();
        etqcp = new javax.swing.JLabel();
        txtnombre = new LIB.FSTexFieldMD();
        txtdireccion = new LIB.FSTexFieldMD();
        txttelefono = new LIB.FSTexFieldMD();
        txtcorreoelectronico = new LIB.FSTexFieldMD();
        txtid = new LIB.FSTexFieldMD();
        txtrfc = new LIB.FSTexFieldMD();
        txtcp = new LIB.FSTexFieldMD();
        jLabel1 = new javax.swing.JLabel();
        fSButtonMD14 = new LIB.FSButtonMD();
        fSButtonMD18 = new LIB.FSButtonMD();
        btneliminar = new LIB.FSButtonMD();
        btnactualizar = new LIB.FSButtonMD();
        btnmodificar = new LIB.FSButtonMD();
        EtqDireccion2 = new javax.swing.JLabel();
        txtapellidomaterno = new LIB.FSTexFieldMD();
        EtqDireccion3 = new javax.swing.JLabel();
        txtapellidopaterno = new LIB.FSTexFieldMD();
        txtciudad = new LIB.FSTexFieldMD();
        EtqDireccion4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        etqventas = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        VENTAS = new javax.swing.JTable();
        btncargarventas = new LIB.FSButtonMD();
        btnguardarpdf = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();
        etqventa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 153));

        etqsistema.setFont(new java.awt.Font("Yu Gothic Medium", 1, 36)); // NOI18N
        etqsistema.setText("SISTEMA ADMINISTRADOR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(etqsistema)
                .addContainerGap(195, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(etqsistema, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 1, 820, 70));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnproductos.setBackground(new java.awt.Color(204, 0, 0));
        btnproductos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/productos.png"))); // NOI18N
        btnproductos.setText("PRODUCTOS");
        btnproductos.setColorTextNormal(new java.awt.Color(255, 255, 204));
        btnproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproductosActionPerformed(evt);
            }
        });
        jPanel2.add(btnproductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        btnregistro.setBackground(new java.awt.Color(204, 0, 0));
        btnregistro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnregistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/anadir.png"))); // NOI18N
        btnregistro.setText("REGISTRO ");
        btnregistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistroActionPerformed(evt);
            }
        });
        jPanel2.add(btnregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 200, -1));

        btncerrarseciones.setBackground(new java.awt.Color(204, 0, 0));
        btncerrarseciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btncerrarseciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/cerrar-sesion.png"))); // NOI18N
        btncerrarseciones.setText("CERRAR SESIÓN");
        btncerrarseciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncerrarsecionesActionPerformed(evt);
            }
        });
        jPanel2.add(btncerrarseciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 539, 200, -1));

        btnsctuap.setBackground(new java.awt.Color(204, 0, 0));
        btnsctuap.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnsctuap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/actualizar-base-de-datos.png"))); // NOI18N
        btnsctuap.setText("ACTUALIZAR PRODUCTOS");
        btnsctuap.setFont(new java.awt.Font("Segoe", 1, 12)); // NOI18N
        btnsctuap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsctuapActionPerformed(evt);
            }
        });
        jPanel2.add(btnsctuap, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));

        btnactusuarios.setBackground(new java.awt.Color(204, 0, 0));
        btnactusuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/actualizar-base-de-datos.png"))); // NOI18N
        btnactusuarios.setText("ACTUALIZAR USUARIOS");
        btnactusuarios.setFont(new java.awt.Font("Segoe", 1, 12)); // NOI18N
        btnactusuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactusuariosActionPerformed(evt);
            }
        });
        jPanel2.add(btnactusuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, -1, -1));

        btnactclientes.setBackground(new java.awt.Color(204, 0, 0));
        btnactclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/actualizar-base-de-datos.png"))); // NOI18N
        btnactclientes.setText("ACTUALIZAR CLIENTES");
        btnactclientes.setFont(new java.awt.Font("Segoe", 1, 12)); // NOI18N
        btnactclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactclientesActionPerformed(evt);
            }
        });
        jPanel2.add(btnactclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, -1, -1));

        btnventa.setBackground(new java.awt.Color(204, 0, 0));
        btnventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/comercio.png"))); // NOI18N
        btnventa.setText("VENTAS");
        btnventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnventaActionPerformed(evt);
            }
        });
        jPanel2.add(btnventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 610));

        panel.setFSEndColor(new java.awt.Color(255, 255, 255));
        panel.setFSStartColor(new java.awt.Color(255, 255, 255));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etqfuncion.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        etqfuncion.setForeground(new java.awt.Color(0, 102, 102));
        etqfuncion.setText("REGISTRO PRODUCTOS PANADERIA AMANECER");
        panel.add(etqfuncion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        btnguardar.setBackground(new java.awt.Color(0, 0, 0));
        btnguardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Guardar.png"))); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        panel.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 130, 40));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCTO", "PRECIO", "STOCK"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        panel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 520, 90));

        txtproducto.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtproducto.setPlaceholder("");
        txtproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtproductoKeyTyped(evt);
            }
        });
        panel.add(txtproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 240, 40));

        txtprecio.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtprecio.setPlaceholder("");
        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });
        panel.add(txtprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 200, 40));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("PRODUCTOS");
        panel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("PRECIO");
        panel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, -1, -1));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("STOCK");
        panel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, -1, -1));

        txtstcK.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtstcK.setPlaceholder("");
        txtstcK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstcKKeyTyped(evt);
            }
        });
        panel.add(txtstcK, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 200, 40));

        fSButtonMD10.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/anadir.png"))); // NOI18N
        fSButtonMD10.setText("AGREGAR");
        fSButtonMD10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD10ActionPerformed(evt);
            }
        });
        panel.add(fSButtonMD10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 130, -1));

        fSButtonMD11.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/limpieza-de-datos.png"))); // NOI18N
        fSButtonMD11.setText("LIMPIAR ");
        fSButtonMD11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD11ActionPerformed(evt);
            }
        });
        panel.add(fSButtonMD11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 110, -1));

        fSButtonMD12.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/borrar.png"))); // NOI18N
        fSButtonMD12.setText("ELIMINAR");
        fSButtonMD12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD12ActionPerformed(evt);
            }
        });
        panel.add(fSButtonMD12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 120, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Escritorio.addTab("MENU 1", jPanel3);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fSGradientPanel2.setFSEndColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel2.setFSStartColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fSTexFieldMD8.setForeground(new java.awt.Color(0, 102, 102));
        fSTexFieldMD8.setText("REGISTRO DE USUARIOS");
        fSTexFieldMD8.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        fSTexFieldMD8.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        fSTexFieldMD8.setOpaque(false);
        fSGradientPanel2.add(fSTexFieldMD8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 330, 40));

        btnguardar1.setBackground(new java.awt.Color(0, 0, 0));
        btnguardar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnguardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Guardar.png"))); // NOI18N
        btnguardar1.setText("GUARDAR Y AGREGAR");
        btnguardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar1ActionPerformed(evt);
            }
        });
        fSGradientPanel2.add(btnguardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 270, 40));
        fSGradientPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, -1, -1));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablausuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "USUARIO", "CONTRASEÑA", "CONFIRMAR CONTRASEÑA", "PUESTO"
            }
        ));
        jScrollPane3.setViewportView(tablausuario);

        fSGradientPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 660, 90));

        fSButtonMD7.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/limpieza-de-datos.png"))); // NOI18N
        fSButtonMD7.setText("LIMPIAR");
        fSButtonMD7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD7ActionPerformed(evt);
            }
        });
        fSGradientPanel2.add(fSButtonMD7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 120, -1));

        jPanelTransparente1.setColorPrimario(new java.awt.Color(255, 255, 255));
        jPanelTransparente1.setColorSecundario(new java.awt.Color(204, 255, 255));
        jPanelTransparente1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtcomprobacion.setForeground(new java.awt.Color(0, 0, 0));
        txtcomprobacion.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtcomprobacion.setOpaque(false);
        txtcomprobacion.setPlaceholder("Reconfirma la Contraseña");
        txtcomprobacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcomprobacionKeyTyped(evt);
            }
        });
        jPanelTransparente1.add(txtcomprobacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 220, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CONTRASEÑA");
        jPanelTransparente1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, 20));

        txtcontraseña.setForeground(new java.awt.Color(0, 0, 0));
        txtcontraseña.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtcontraseña.setOpaque(false);
        txtcontraseña.setPlaceholder("Ingresa la Contraseña");
        txtcontraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcontraseñaKeyTyped(evt);
            }
        });
        jPanelTransparente1.add(txtcontraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 220, 40));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CONFIRMAR CONTRASEÑA");
        jPanelTransparente1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PUESTO");
        jPanelTransparente1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 60, -1));

        trabajo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        trabajo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona el Puesto" }));
        trabajo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        trabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trabajoActionPerformed(evt);
            }
        });
        jPanelTransparente1.add(trabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 160, -1));

        txtnombreempleado.setForeground(new java.awt.Color(0, 0, 0));
        txtnombreempleado.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtnombreempleado.setOpaque(false);
        txtnombreempleado.setPlaceholder("Ingresa el Usuario");
        txtnombreempleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreempleadoKeyTyped(evt);
            }
        });
        jPanelTransparente1.add(txtnombreempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 220, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("USUARIO");
        jPanelTransparente1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 60, -1));

        fSGradientPanel2.add(jPanelTransparente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 300, 300));

        fSButtonMD8.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/borrar.png"))); // NOI18N
        fSButtonMD8.setText("ELIMINAR ");
        fSButtonMD8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD8ActionPerformed(evt);
            }
        });
        fSGradientPanel2.add(fSButtonMD8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 160, -1));

        jPanel5.add(fSGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 820, 570));

        Escritorio.addTab("MENU 2", jPanel5);

        fSGradientPanel1.setFSEndColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel1.setFSStartColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("ID-PRODUCTO");
        fSGradientPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("STOCK");
        fSGradientPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, -1, -1));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("PRODUCTOS");
        fSGradientPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        txtidproductoactualizar.setEditable(false);
        txtidproductoactualizar.setBackground(new java.awt.Color(255, 255, 255));
        txtidproductoactualizar.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtidproductoactualizar.setPlaceholder("");
        fSGradientPanel1.add(txtidproductoactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 230, 40));

        txtprecioactualizar.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtprecioactualizar.setPlaceholder("");
        txtprecioactualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioactualizarKeyTyped(evt);
            }
        });
        fSGradientPanel1.add(txtprecioactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 230, 40));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setText("PRECIO");
        fSGradientPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, -1, -1));

        txtstockactualizar.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtstockactualizar.setPlaceholder("");
        txtstockactualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockactualizarKeyTyped(evt);
            }
        });
        fSGradientPanel1.add(txtstockactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 230, 40));

        btneliminar1.setBackground(new java.awt.Color(0, 0, 0));
        btneliminar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btneliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/actualizar-base-de-datos.png"))); // NOI18N
        btneliminar1.setText("ACTUALIZAR");
        btneliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar1ActionPerformed(evt);
            }
        });
        fSGradientPanel1.add(btneliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, 140, 40));

        btnactualizar1.setBackground(new java.awt.Color(0, 0, 0));
        btnactualizar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnactualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/editar.png"))); // NOI18N
        btnactualizar1.setText("SELECCIONAR");
        btnactualizar1.setColorNormal(new java.awt.Color(0, 0, 0));
        btnactualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizar1ActionPerformed(evt);
            }
        });
        fSGradientPanel1.add(btnactualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, 140, 40));

        actualizat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID-PRODUCTO", "NOMBRE", "PRECIO", "STOCK"
            }
        ));
        jScrollPane2.setViewportView(actualizat);

        fSGradientPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 500, 110));

        txtnombreproductoactualizar.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtnombreproductoactualizar.setPlaceholder("");
        txtnombreproductoactualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreproductoactualizarKeyTyped(evt);
            }
        });
        fSGradientPanel1.add(txtnombreproductoactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 230, 40));

        btneliminar2.setBackground(new java.awt.Color(0, 0, 0));
        btneliminar2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btneliminar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/borrar.png"))); // NOI18N
        btneliminar2.setText("ELIMINAR");
        btneliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar2ActionPerformed(evt);
            }
        });
        fSGradientPanel1.add(btneliminar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 140, 40));

        fSButtonMD13.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/limpieza-de-datos.png"))); // NOI18N
        fSButtonMD13.setText("LIMPIAR");
        fSButtonMD13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD13ActionPerformed(evt);
            }
        });
        fSGradientPanel1.add(fSButtonMD13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 110, -1));

        etqfuncion1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        etqfuncion1.setForeground(new java.awt.Color(0, 102, 102));
        etqfuncion1.setText("ACTUALIZAR PRODUCTOS PANADERIA AMANECER");
        fSGradientPanel1.add(etqfuncion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        fSButtonMD3.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD3.setText("CARGAR DATOS");
        fSButtonMD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD3ActionPerformed(evt);
            }
        });
        fSGradientPanel1.add(fSButtonMD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 130, -1));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fSGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fSGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );

        Escritorio.addTab("MENU 3", jPanel6);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fSGradientPanel3.setFSEndColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel3.setFSStartColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTransparente2.setColorPrimario(new java.awt.Color(255, 255, 255));
        jPanelTransparente2.setColorSecundario(new java.awt.Color(204, 255, 255));
        jPanelTransparente2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("CONTRASEÑA");
        jPanelTransparente2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, 20));

        txtcontraseñaactualizar.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtcontraseñaactualizar.setOpaque(false);
        txtcontraseñaactualizar.setPlaceholder("Ingresa la Contraseña");
        txtcontraseñaactualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcontraseñaactualizarKeyTyped(evt);
            }
        });
        jPanelTransparente2.add(txtcontraseñaactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 220, 40));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("PUESTO");
        jPanelTransparente2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 60, -1));

        trabajoactualizar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        trabajoactualizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona el Puesto" }));
        trabajoactualizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        trabajoactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trabajoactualizarActionPerformed(evt);
            }
        });
        jPanelTransparente2.add(trabajoactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 170, 30));

        txtnombreempleadoactualizar.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtnombreempleadoactualizar.setOpaque(false);
        txtnombreempleadoactualizar.setPlaceholder("Ingresa el Usuario");
        txtnombreempleadoactualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreempleadoactualizarKeyTyped(evt);
            }
        });
        jPanelTransparente2.add(txtnombreempleadoactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 220, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("USUARIO");
        jPanelTransparente2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 60, -1));

        fSGradientPanel3.add(jPanelTransparente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 280, 310));

        fSTexFieldMD9.setForeground(new java.awt.Color(0, 102, 102));
        fSTexFieldMD9.setText("    ACTUALIZAR USUARIOS");
        fSTexFieldMD9.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        fSTexFieldMD9.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        fSTexFieldMD9.setOpaque(false);
        fSGradientPanel3.add(fSTexFieldMD9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 330, 40));

        fSButtonMD16.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/editar.png"))); // NOI18N
        fSButtonMD16.setText("SELECCIONAR");
        fSButtonMD16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD16ActionPerformed(evt);
            }
        });
        fSGradientPanel3.add(fSButtonMD16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, 140, -1));

        fSButtonMD17.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/actualizar-base-de-datos.png"))); // NOI18N
        fSButtonMD17.setText("ACTUALIZAR ");
        fSButtonMD17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD17ActionPerformed(evt);
            }
        });
        fSGradientPanel3.add(fSButtonMD17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 140, 40));

        fSButtonMD19.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/borrar.png"))); // NOI18N
        fSButtonMD19.setText("ELIMINAR");
        fSButtonMD19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD19ActionPerformed(evt);
            }
        });
        fSGradientPanel3.add(fSButtonMD19, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 140, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("ID-USUARIO");
        fSGradientPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, -1, -1));

        txtidusuario.setEditable(false);
        txtidusuario.setBackground(new java.awt.Color(255, 255, 255));
        txtidusuario.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtidusuario.setOpaque(false);
        txtidusuario.setPlaceholder("");
        fSGradientPanel3.add(txtidusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 140, -1));

        fSButtonMD20.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/limpieza-de-datos.png"))); // NOI18N
        fSButtonMD20.setText("LIMPIAR");
        fSButtonMD20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD20ActionPerformed(evt);
            }
        });
        fSGradientPanel3.add(fSButtonMD20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 110, -1));

        tablaactualizar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID-Usuario", "nombre", "contraseña", "idPuesto"
            }
        ));
        jScrollPane4.setViewportView(tablaactualizar);

        fSGradientPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 490, 90));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("ACTUALIZAR EMPLEADOS");
        fSGradientPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 240, -1));

        fSButtonMD2.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD2.setText("CARGAR DATOS");
        fSButtonMD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD2ActionPerformed(evt);
            }
        });
        fSGradientPanel3.add(fSButtonMD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 150, -1));

        jPanel4.add(fSGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 610));

        Escritorio.addTab("MENU 4", jPanel4);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Etqid.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Etqid.setText("ID-CLIENTE");
        jPanel7.add(Etqid, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        datosclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido paterno", "Apellido materno", "Telefono", "Direccion", "RFC", "CP", "Ciudad", "idVenta", "correo-electronico"
            }
        ));
        jScrollPane5.setViewportView(datosclientes);

        jPanel7.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 379, 795, 79));

        EtqDireccion1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        EtqDireccion1.setText("DIRECCION");
        jPanel7.add(EtqDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 182, -1, 10));

        etqtelefono.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        etqtelefono.setText("TELEFONO");
        jPanel7.add(etqtelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 106, -1, -1));

        etqCorreo.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        etqCorreo.setText("CORREO ELECTRONICO");
        jPanel7.add(etqCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 249, -1, -1));

        etqciudad.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        etqciudad.setText("CIUDAD");
        jPanel7.add(etqciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 249, -1, -1));

        etqrfc1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        etqrfc1.setText("RFC");
        jPanel7.add(etqrfc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 182, -1, 20));

        etqcp.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        etqcp.setText("C.P");
        jPanel7.add(etqcp, new org.netbeans.lib.awtextra.AbsoluteConstraints(637, 256, -1, -1));

        txtnombre.setForeground(new java.awt.Color(153, 153, 153));
        txtnombre.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtnombre.setOpaque(false);
        txtnombre.setPlaceholder("");
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        jPanel7.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 64, 193, -1));

        txtdireccion.setForeground(new java.awt.Color(153, 153, 153));
        txtdireccion.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtdireccion.setOpaque(false);
        txtdireccion.setPlaceholder("");
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });
        jPanel7.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 198, 203, 40));

        txttelefono.setForeground(new java.awt.Color(153, 153, 153));
        txttelefono.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txttelefono.setOpaque(false);
        txttelefono.setPlaceholder("");
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });
        jPanel7.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 134, 203, -1));

        txtcorreoelectronico.setForeground(new java.awt.Color(153, 153, 153));
        txtcorreoelectronico.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtcorreoelectronico.setOpaque(false);
        txtcorreoelectronico.setPlaceholder("");
        txtcorreoelectronico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreoelectronicoKeyTyped(evt);
            }
        });
        jPanel7.add(txtcorreoelectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 272, 193, 37));

        txtid.setEditable(false);
        txtid.setForeground(new java.awt.Color(153, 153, 153));
        txtid.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtid.setOpaque(false);
        txtid.setPlaceholder("");
        jPanel7.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 110, -1));

        txtrfc.setForeground(new java.awt.Color(153, 153, 153));
        txtrfc.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtrfc.setOpaque(false);
        txtrfc.setPlaceholder("");
        txtrfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrfcKeyTyped(evt);
            }
        });
        jPanel7.add(txtrfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 208, 193, -1));

        txtcp.setForeground(new java.awt.Color(153, 153, 153));
        txtcp.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtcp.setOpaque(false);
        txtcp.setPlaceholder("");
        txtcp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcpKeyTyped(evt);
            }
        });
        jPanel7.add(txtcp, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 279, 203, -1));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("ACTUALIZACION DE DATOS DEL CLIENTE");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 4, -1, -1));

        fSButtonMD14.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/limpieza-de-datos.png"))); // NOI18N
        fSButtonMD14.setText("LIMPIAR");
        fSButtonMD14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD14ActionPerformed(evt);
            }
        });
        jPanel7.add(fSButtonMD14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 110, -1));

        fSButtonMD18.setBackground(new java.awt.Color(0, 0, 0));
        fSButtonMD18.setText("CARGAR DATOS");
        fSButtonMD18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD18ActionPerformed(evt);
            }
        });
        jPanel7.add(fSButtonMD18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 130, -1));

        btneliminar.setBackground(new java.awt.Color(0, 0, 0));
        btneliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/borrar.png"))); // NOI18N
        btneliminar.setText("ELIMINAR");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        jPanel7.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, 120, 29));

        btnactualizar.setBackground(new java.awt.Color(0, 0, 0));
        btnactualizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/actualizar-base-de-datos.png"))); // NOI18N
        btnactualizar.setText("ACTUALIZAR");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });
        jPanel7.add(btnactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, 130, 26));

        btnmodificar.setBackground(new java.awt.Color(0, 0, 0));
        btnmodificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/editar.png"))); // NOI18N
        btnmodificar.setText("SELECCIONAR");
        btnmodificar.setColorNormal(new java.awt.Color(0, 0, 0));
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });
        jPanel7.add(btnmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 470, 150, 29));

        EtqDireccion2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        EtqDireccion2.setText("APELLIDO MATERNO");
        jPanel7.add(EtqDireccion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 106, -1, -1));

        txtapellidomaterno.setForeground(new java.awt.Color(153, 153, 153));
        txtapellidomaterno.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtapellidomaterno.setOpaque(false);
        txtapellidomaterno.setPlaceholder("");
        txtapellidomaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidomaternoKeyTyped(evt);
            }
        });
        jPanel7.add(txtapellidomaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 134, 203, -1));

        EtqDireccion3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        EtqDireccion3.setText("APELLIDO PATERNO");
        jPanel7.add(EtqDireccion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 41, -1, -1));

        txtapellidopaterno.setForeground(new java.awt.Color(153, 153, 153));
        txtapellidopaterno.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtapellidopaterno.setOpaque(false);
        txtapellidopaterno.setPlaceholder("");
        txtapellidopaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidopaternoKeyTyped(evt);
            }
        });
        jPanel7.add(txtapellidopaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 66, 193, 37));

        txtciudad.setForeground(new java.awt.Color(153, 153, 153));
        txtciudad.setBordeColorFocus(new java.awt.Color(0, 0, 0));
        txtciudad.setOpaque(false);
        txtciudad.setPlaceholder("");
        txtciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtciudadKeyTyped(evt);
            }
        });
        jPanel7.add(txtciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 272, 193, -1));

        EtqDireccion4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        EtqDireccion4.setText("NOMBRE ");
        jPanel7.add(EtqDireccion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 41, -1, 20));

        Escritorio.addTab("MENU 5", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        etqventas.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        etqventas.setForeground(new java.awt.Color(0, 102, 102));
        etqventas.setText("VENTAS REALIZADAS EN LA PANADERIA AMANECER");

        VENTAS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(VENTAS);

        btncargarventas.setBackground(new java.awt.Color(0, 0, 0));
        btncargarventas.setText("CARGAR DATOS");
        btncargarventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargarventasActionPerformed(evt);
            }
        });

        btnguardarpdf.setBackground(new java.awt.Color(255, 204, 204));
        btnguardarpdf.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnguardarpdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Factura.png"))); // NOI18N
        btnguardarpdf.setText("GENERAR");
        btnguardarpdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarpdfActionPerformed(evt);
            }
        });

        btnimprimir.setBackground(new java.awt.Color(255, 204, 204));
        btnimprimir.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/computadora.png"))); // NOI18N
        btnimprimir.setText("IMPRIMIR");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        etqventa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        etqventa.setText("VENTAS ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(btnguardarpdf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172)
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(etqventas))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(btncargarventas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(etqventa)
                .addGap(367, 367, 367))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(etqventas)
                .addGap(18, 18, 18)
                .addComponent(etqventa)
                .addGap(5, 5, 5)
                .addComponent(btncargarventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardarpdf, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        Escritorio.addTab("MENU 6", jPanel8);

        getContentPane().add(Escritorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 820, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        String producto=txtproducto.getText();
        String precio=txtprecio.getText();
        String Stock=txtstcK.getText();
        try
         {
        if(txtproducto.getText().equals("") && txtprecio.equals("")&& txtstcK.getText().equals(""))
        {
        JOptionPane.showMessageDialog(null, "DEBES DE LLENAR EL CAMPO VACIO" );
        }
    else 
    {
        if(txtproducto.getText().equals("")|| txtprecio.getText().equals("") || txtstcK.getText().equals(""))
        {
        JOptionPane.showMessageDialog (null, " DEBES DE LLENAR TODOS LOS CAMPOS  ");
        }
        else
        {
        Conexion objdatos= new Conexion();
        objdatos.conectar();
        String sql="insert into producto (nombre, precio, stock) value ('"+ producto + "','"+ precio + "','"+ Stock +"');";
        objdatos.actualizar(sql);
        JOptionPane.showMessageDialog(null,"EL PRODUCTO SE GUARDO EXITOSAMENTE");
        }  
    }
     }catch(SQLException ex)
        {
        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproductosActionPerformed
    Escritorio.setSelectedIndex(0);
    Escritorio.setEnabledAt(0, true);
    Escritorio.setEnabledAt(1, false);
    Escritorio.setEnabledAt(2, false);
    }//GEN-LAST:event_btnproductosActionPerformed

    private void btnregistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistroActionPerformed
    Escritorio.setSelectedIndex(1);
    Escritorio.setEnabledAt(0, false);
    Escritorio.setEnabledAt(1, true);
    Escritorio.setEnabledAt(2, false);
    }//GEN-LAST:event_btnregistroActionPerformed

    private void btncerrarsecionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncerrarsecionesActionPerformed
    Login L = new Login();
    L.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btncerrarsecionesActionPerformed

    private void btnguardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar1ActionPerformed
    try
    {
                String nombre= txtnombreempleado.getText();
                String passwoord= txtcontraseña.getText();
                String comprobacion=txtcomprobacion.getText();
                int trab=trabajo.getSelectedIndex();
    
                 if(nombre.isEmpty() || passwoord.isEmpty() || comprobacion.isEmpty())
                {
                JOptionPane.showMessageDialog(null," LOS CAMPOS ESTAN VACIOS ");
                }
                 else if (nombre.equals(nombre) && passwoord.equals(passwoord) && comprobacion.equals(comprobacion) )
                    {   
                        if(txtcontraseña.getText().equals(txtcomprobacion.getText()))
                        {
                        JOptionPane.showMessageDialog(null,"CONTRASEÑAS CORRECTAS");
                        Conexion objdatos= new Conexion();
                        objdatos.conectar();
                        String sql="insert into usuarios (nombre, contraseña, idPuesto) value ('"+ nombre + "','"+ passwoord+ "','"+ trab +"');";
                        objdatos.actualizar(sql);
                        JOptionPane.showMessageDialog(rootPane, "EL USUARIO "+ nombre +" FUE REGISTRADO EXITOSAMENTE");   
                        Agregar1();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"CONTRASEÑA, INCORRECTA VUELVE A INGRESARLA");
                            txtcomprobacion.setText("");
                        }
                    }
    }catch(SQLException ex)
        {
        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_btnguardar1ActionPerformed

    private void trabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trabajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trabajoActionPerformed

    private void fSButtonMD7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD7ActionPerformed
    txtnombreempleado.setText("");
    txtcontraseña.setText("");
    txtcomprobacion.setText("");
    trabajo.setToolTipText("");
    }//GEN-LAST:event_fSButtonMD7ActionPerformed

    private void fSButtonMD8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD8ActionPerformed
    Eliminar1();    
    }//GEN-LAST:event_fSButtonMD8ActionPerformed

    private void fSButtonMD11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD11ActionPerformed
    txtstcK.setText("");        
    txtproducto.setText("");
    txtprecio.setText("");
    }//GEN-LAST:event_fSButtonMD11ActionPerformed

    private void fSButtonMD10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD10ActionPerformed
    Agregar();
    }//GEN-LAST:event_fSButtonMD10ActionPerformed

    private void fSButtonMD12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD12ActionPerformed
    Eliminar();
    }//GEN-LAST:event_fSButtonMD12ActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        ConexionUsuarios cu= new ConexionUsuarios(); 
        Connection conect=cu.conectar();
    try {
    PreparedStatement pps=conect.prepareStatement("UPDATE producto SET nombre='"+ txtnombreproductoactualizar.getText()+"',precio='"+ 
    txtprecioactualizar.getText()+ "',stock='"+ txtstockactualizar.getText()+"'WHERE idProducto='"+ txtidproductoactualizar.getText()+"';");
    pps.executeUpdate();
    Cargar();
    JOptionPane.showMessageDialog(null,"DATOS ACTUALIZADOS CORRECTAMENTE");
    } catch (SQLException ex) {
    Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void btnsctuapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsctuapActionPerformed
    Escritorio.setSelectedIndex(2);
    Escritorio.setEnabledAt(0, false);
    Escritorio.setEnabledAt(1, false);
    Escritorio.setEnabledAt(2, true);
    }//GEN-LAST:event_btnsctuapActionPerformed

    private void btnactualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizar1ActionPerformed
     int fila=actualizat.getSelectedRow();
     if(fila>=0)
     {  
        txtidproductoactualizar.setText(actualizat.getValueAt(fila, 0).toString());
        txtnombreproductoactualizar.setText(actualizat.getValueAt(fila, 1).toString());
        txtprecioactualizar.setText(actualizat.getValueAt(fila, 2).toString());
        txtstockactualizar.setText(actualizat.getValueAt(fila, 3).toString()); 
     }else
     {
        JOptionPane.showMessageDialog(null,"FILA NO SELECCIONADA");
     }
    }//GEN-LAST:event_btnactualizar1ActionPerformed

    private void btneliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar2ActionPerformed
    ConexionUsuarios cu= new ConexionUsuarios(); 
    Connection conect=cu.conectar();
       try
       {
    int fila=actualizat .getSelectedRow();
    String valor=actualizat.getValueAt(fila, 0).toString();
           if(fila>=0)
           {
            PreparedStatement pps=conect.prepareStatement("DELETE from producto WHERE idProducto='"+ valor+"'");
            pps.executeUpdate();
            Cargar();
            JOptionPane.showMessageDialog(null," DATOS ELIMINADOS");
           }
           else
           {
           JOptionPane.showMessageDialog(null,"SELECCIONA UNA FILA"); 
           }
       }catch(SQLException ex)
       {
       Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex); 
       }
   
    }//GEN-LAST:event_btneliminar2ActionPerformed

    private void fSButtonMD13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD13ActionPerformed
    txtidproductoactualizar.setText("");
    txtnombreproductoactualizar.setText("");
    txtprecioactualizar.setText("");
    txtstockactualizar.setText("");
    }//GEN-LAST:event_fSButtonMD13ActionPerformed

    private void btnactusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactusuariosActionPerformed
    Escritorio.setSelectedIndex(3);
    Escritorio.setEnabledAt(0, false);
    Escritorio.setEnabledAt(1, false);
    Escritorio.setEnabledAt(2, false);
    Escritorio.setEnabledAt(3, true);
    }//GEN-LAST:event_btnactusuariosActionPerformed

    private void trabajoactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trabajoactualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trabajoactualizarActionPerformed

    private void fSButtonMD16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD16ActionPerformed
     int fila=tablaactualizar.getSelectedRow();
     if(fila>=0)
     {
         txtidusuario.setText(tablaactualizar.getValueAt(fila,0).toString());
         txtnombreempleadoactualizar.setText(tablaactualizar.getValueAt(fila, 1).toString());
         txtcontraseñaactualizar.setText(tablaactualizar.getValueAt(fila, 2).toString());
         trabajoactualizar.setToolTipText(tablaactualizar.getValueAt(fila, 3).toString()); 
     }else
     {
         JOptionPane.showMessageDialog(null,"FILA NO SELECCIONADA");
     }
    }//GEN-LAST:event_fSButtonMD16ActionPerformed

    private void fSButtonMD17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD17ActionPerformed
    ConexionUsuarios cu= new ConexionUsuarios(); 
    Connection conect=cu.conectar();
    try {
    PreparedStatement pps=conect.prepareStatement("UPDATE usuarios SET nombre='"+ txtnombreempleadoactualizar.getText()+ "',contraseña='"+ txtcontraseñaactualizar.getText()+ "', idPuesto='"+trabajoactualizar.getSelectedIndex()+"'WHERE idUsuarios='"+ txtidusuario.getText()+"';");
    pps.executeUpdate();
    CargarUsuarios();
    JOptionPane.showMessageDialog(null,"DATOS ACTUALIZADOS CORRECTAMENTE");
    } catch (SQLException ex) {
    Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
    }    
    }//GEN-LAST:event_fSButtonMD17ActionPerformed

    private void fSButtonMD19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD19ActionPerformed
    ConexionUsuarios cu= new ConexionUsuarios(); 
    Connection conect=cu.conectar();
       try
       {
        int fila=tablaactualizar .getSelectedRow();
        String valor=tablaactualizar.getValueAt(fila, 0).toString();
        if(fila>=0)
        {
        PreparedStatement pps=conect.prepareStatement("DELETE from usuarios WHERE idUsuarios='"+ valor+"'");
        pps.executeUpdate();
        Cargar();
       JOptionPane.showMessageDialog(null," DATOS ELIMINADOS");
        }
       else
        {
        JOptionPane.showMessageDialog(null,"SELECCIONA UNA FILA"); 
        }
       }catch(SQLException ex)
       {
       Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex); 
       }    
    }//GEN-LAST:event_fSButtonMD19ActionPerformed

    private void fSButtonMD20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD20ActionPerformed
    txtidusuario.setText("");
    txtnombreempleadoactualizar.setText("");
    txtcontraseñaactualizar.setText("");
    trabajoactualizar.setToolTipText("");
    }//GEN-LAST:event_fSButtonMD20ActionPerformed

    private void fSButtonMD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD2ActionPerformed
    CargarUsuarios();
    }//GEN-LAST:event_fSButtonMD2ActionPerformed

    private void fSButtonMD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD3ActionPerformed
    Cargar();
    }//GEN-LAST:event_fSButtonMD3ActionPerformed

    private void fSButtonMD14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD14ActionPerformed
    txtid.setText("");
    txtnombre.setText("");
    txtapellidomaterno.setText("");
    txtapellidopaterno.setText("");
    txtcorreoelectronico.setText("");
    txtdireccion.setText("");
    txtciudad.setText("");
    txttelefono.setText("");
    txtrfc.setText("");
    txtcp.setText("");
    }//GEN-LAST:event_fSButtonMD14ActionPerformed

    private void fSButtonMD18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD18ActionPerformed
    CargarClientes();
    }//GEN-LAST:event_fSButtonMD18ActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
    ConexionUsuarios cu= new ConexionUsuarios(); 
    Connection conect=cu.conectar();
       try
       {
        int fila=datosclientes.getSelectedRow();
        String valor=datosclientes.getValueAt(fila, 0).toString();
        if(fila>=0)
        {
        PreparedStatement pps=conect.prepareStatement("DELETE from cliente WHERE idCliente='"+ valor+"'");
        pps.executeUpdate();
        Cargar();
       JOptionPane.showMessageDialog(null," DATOS ELIMINADOS");
        }
       else
        {
        JOptionPane.showMessageDialog(null,"SELECCIONA UNA FILA"); 
        }
       }catch(SQLException ex)
       {
       Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex); 
       }                                            
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
    ConexionUsuarios cu= new ConexionUsuarios(); 
    Connection conect=cu.conectar();
    try {
    PreparedStatement pps=conect.prepareStatement("UPDATE cliente SET nombre='"+ txtnombre.getText()+"',apellido_paterno='"+ 
    txtapellidopaterno.getText()+ "',apellido_materno='"+txtapellidomaterno.getText()+ "',telefono='"+ txttelefono.getText()+ "',direccion='"+ txtdireccion.getText()+ "',rfc='"+ txtrfc.getText()+ "',cp='"+ txtcp.getText()+ "',ciudad='"+ txtciudad.getText() + "',correoelectronico='"+ txtcorreoelectronico.getText()+"'WHERE idCliente='"+ txtid.getText()+"';");
    pps.executeUpdate();
    CargarClientes();
    JOptionPane.showMessageDialog(null,"DATOS ACTUALIZADOS CORRECTAMENTE");
    } catch (SQLException ex) {
    Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
     int fila=datosclientes.getSelectedRow();
     if(fila>=0)
     {   
       txtid.setText(datosclientes.getValueAt(fila, 0).toString());
       txtnombre.setText(datosclientes.getValueAt(fila, 1).toString());
       txtapellidopaterno.setText(datosclientes.getValueAt(fila, 2).toString());
       txtapellidomaterno.setText(datosclientes.getValueAt(fila, 3).toString());
       txttelefono.setText(datosclientes.getValueAt(fila, 4).toString());
       txtdireccion.setText(datosclientes.getValueAt(fila, 5).toString());
       txtrfc.setText(datosclientes.getValueAt(fila,6).toString()); 
       txtcp.setText(datosclientes.getValueAt(fila, 7).toString()); 
       txtciudad.setText(datosclientes.getValueAt(fila, 8).toString());
       txtcorreoelectronico.setText(datosclientes.getValueAt(fila, 9).toString());
     }else
     {
        JOptionPane.showMessageDialog(null,"FILA NO SELECCIONADA");
     }           
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnactclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactclientesActionPerformed
    Escritorio.setSelectedIndex(4);
    Escritorio.setEnabledAt(0, false);
    Escritorio.setEnabledAt(1, false);
    Escritorio.setEnabledAt(2, false);
    Escritorio.setEnabledAt(3, false);
    Escritorio.setEnabledAt(4, true );
    }//GEN-LAST:event_btnactclientesActionPerformed

    private void btncargarventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargarventasActionPerformed
CargarVENTAS();    
    }//GEN-LAST:event_btncargarventasActionPerformed

    private void btnguardarpdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarpdfActionPerformed

     try
        {
            ImFactura(etqventa.getText());
        }catch(FileNotFoundException ex)
        {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }catch(DocumentException ex)
        {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnguardarpdfActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
       if(! etqventa.getText().isEmpty())
        {
            Abrir(etqventa.getText());
        }
        else
        {
            JOptionPane.showMessageDialog(null," NO SE ENCONTRO EL ARCHIVO");
        }
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void txtproductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproductoKeyTyped
     Character c= evt.getKeyChar();
    if(!Character.isLetter(c) && c !=KeyEvent.VK_SPACE || (txtproducto.getText().length()>=30))
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtproductoKeyTyped

    private void txtprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyTyped
     char validar=evt.getKeyChar();
        if(Character.isLetter(validar) || txtprecio.getText().length()>=7)
        {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtprecioKeyTyped

    private void txtstcKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstcKKeyTyped
     char validar=evt.getKeyChar();
        if(Character.isLetter(validar) || txtstcK.getText().length()>=7)
        {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtstcKKeyTyped

    private void txtnombreempleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreempleadoKeyTyped
    Character c= evt.getKeyChar();
    if(!Character.isLetter(c) && c !=KeyEvent.VK_SPACE || txtnombreempleado.getText().length()>=20)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtnombreempleadoKeyTyped

    private void txtcontraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraseñaKeyTyped
    char validar=evt.getKeyChar();
        if(Character.isLetter(validar) || txtcontraseña.getText().length()>=6)
        {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtcontraseñaKeyTyped

    private void txtcomprobacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcomprobacionKeyTyped
    char validar=evt.getKeyChar();
        if(Character.isLetter(validar) || txtcomprobacion.getText().length()>=6)
        {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtcomprobacionKeyTyped

    private void txtstockactualizarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockactualizarKeyTyped
    char validar=evt.getKeyChar();
        if(Character.isLetter(validar) || txtstockactualizar.getText().length()>=7)
        {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtstockactualizarKeyTyped

    private void txtprecioactualizarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioactualizarKeyTyped
    char validar=evt.getKeyChar();
        if(Character.isLetter(validar) || txtprecioactualizar.getText().length()>=7)
        {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtprecioactualizarKeyTyped

    private void txtnombreproductoactualizarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreproductoactualizarKeyTyped
    Character c= evt.getKeyChar();
    if(!Character.isLetter(c) && c !=KeyEvent.VK_SPACE || txtnombreproductoactualizar.getText().length()>=30)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtnombreproductoactualizarKeyTyped

    private void txtnombreempleadoactualizarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreempleadoactualizarKeyTyped
    Character c= evt.getKeyChar();
    if(!Character.isLetter(c) && c !=KeyEvent.VK_SPACE || txtnombreempleadoactualizar.getText().length()>=20)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtnombreempleadoactualizarKeyTyped

    private void txtcontraseñaactualizarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraseñaactualizarKeyTyped
    char validar=evt.getKeyChar();
        if(Character.isLetter(validar) || txtcontraseñaactualizar.getText().length()>=6)
        {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtcontraseñaactualizarKeyTyped

    private void btnventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnventaActionPerformed
    Escritorio.setSelectedIndex(5);
    Escritorio.setEnabledAt(0, false);
    Escritorio.setEnabledAt(1, false);
    Escritorio.setEnabledAt(2, false);
    Escritorio.setEnabledAt(3, false);
    Escritorio.setEnabledAt(4, false );
    Escritorio.setEnabledAt(5, true);
    }//GEN-LAST:event_btnventaActionPerformed

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
    Character c= evt.getKeyChar();
    if(!Character.isLetter(c) && c !=KeyEvent.VK_SPACE || txtnombre.getText().length()>=30)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtapellidomaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidomaternoKeyTyped
    Character c= evt.getKeyChar();
    if(!Character.isLetter(c) && c !=KeyEvent.VK_SPACE || txtapellidomaterno.getText().length()>=30)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtapellidomaternoKeyTyped

    private void txtapellidopaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidopaternoKeyTyped
     Character c= evt.getKeyChar();
    if(!Character.isLetter(c) && c !=KeyEvent.VK_SPACE || txtapellidopaterno.getText().length()>=30)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtapellidopaternoKeyTyped

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
    if(txttelefono.getText().length()>=10)
        {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtrfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrfcKeyTyped
    if(txtrfc.getText().length()>=13)
        {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtrfcKeyTyped

    private void txtcpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcpKeyTyped
     char validar=evt.getKeyChar();
        if(Character.isLetter(validar)||txtcp.getText().length()>=5)
        {
            evt.consume();
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "INGRESA SOLO NUMEROS Y SOLO ESCRIBE 5 DIGITOS");
        }
    }//GEN-LAST:event_txtcpKeyTyped

    private void txtciudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciudadKeyTyped
     if( txtciudad.getText().length()>=30)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtciudadKeyTyped

    private void txtcorreoelectronicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoelectronicoKeyTyped
    if(txtcorreoelectronico.getText().length()>=45)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtcorreoelectronicoKeyTyped

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped
     if( txtdireccion.getText().length()>=30)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtdireccionKeyTyped

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
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Escritorio;
    private javax.swing.JLabel EtqDireccion1;
    private javax.swing.JLabel EtqDireccion2;
    private javax.swing.JLabel EtqDireccion3;
    private javax.swing.JLabel EtqDireccion4;
    private javax.swing.JLabel Etqid;
    private javax.swing.JTable VENTAS;
    private javax.swing.JTable actualizat;
    private LIB.FSButtonMD btnactclientes;
    private LIB.FSButtonMD btnactualizar;
    private LIB.FSButtonMD btnactualizar1;
    private LIB.FSButtonMD btnactusuarios;
    private LIB.FSButtonMD btncargarventas;
    private LIB.FSButtonMD btncerrarseciones;
    private LIB.FSButtonMD btneliminar;
    private LIB.FSButtonMD btneliminar1;
    private LIB.FSButtonMD btneliminar2;
    private LIB.FSButtonMD btnguardar;
    private LIB.FSButtonMD btnguardar1;
    private javax.swing.JButton btnguardarpdf;
    private javax.swing.JButton btnimprimir;
    private LIB.FSButtonMD btnmodificar;
    private LIB.FSButtonMD btnproductos;
    private LIB.FSButtonMD btnregistro;
    private LIB.FSButtonMD btnsctuap;
    private LIB.FSButtonMD btnventa;
    private javax.swing.JTable datosclientes;
    private javax.swing.JLabel etqCorreo;
    private javax.swing.JLabel etqciudad;
    private javax.swing.JLabel etqcp;
    private javax.swing.JLabel etqfuncion;
    private javax.swing.JLabel etqfuncion1;
    private javax.swing.JLabel etqrfc1;
    private javax.swing.JLabel etqsistema;
    private javax.swing.JLabel etqtelefono;
    private javax.swing.JLabel etqventa;
    private javax.swing.JLabel etqventas;
    private LIB.FSButtonMD fSButtonMD10;
    private LIB.FSButtonMD fSButtonMD11;
    private LIB.FSButtonMD fSButtonMD12;
    private LIB.FSButtonMD fSButtonMD13;
    private LIB.FSButtonMD fSButtonMD14;
    private LIB.FSButtonMD fSButtonMD16;
    private LIB.FSButtonMD fSButtonMD17;
    private LIB.FSButtonMD fSButtonMD18;
    private LIB.FSButtonMD fSButtonMD19;
    private LIB.FSButtonMD fSButtonMD2;
    private LIB.FSButtonMD fSButtonMD20;
    private LIB.FSButtonMD fSButtonMD3;
    private LIB.FSButtonMD fSButtonMD7;
    private LIB.FSButtonMD fSButtonMD8;
    private LIB.FSGradientPanel fSGradientPanel1;
    private LIB.FSGradientPanel fSGradientPanel2;
    private LIB.FSGradientPanel fSGradientPanel3;
    private LIB.FSTexFieldMD fSTexFieldMD8;
    private LIB.FSTexFieldMD fSTexFieldMD9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private LIB.JPanelTransparente jPanelTransparente1;
    private LIB.JPanelTransparente jPanelTransparente2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private LIB.FSGradientPanel panel;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaactualizar;
    private javax.swing.JTable tablausuario;
    private javax.swing.JComboBox<String> trabajo;
    private javax.swing.JComboBox<String> trabajoactualizar;
    private LIB.FSTexFieldMD txtapellidomaterno;
    private LIB.FSTexFieldMD txtapellidopaterno;
    private LIB.FSTexFieldMD txtciudad;
    private LIB.FSTexFieldMD txtcomprobacion;
    private LIB.FSTexFieldMD txtcontraseña;
    private LIB.FSTexFieldMD txtcontraseñaactualizar;
    private LIB.FSTexFieldMD txtcorreoelectronico;
    private LIB.FSTexFieldMD txtcp;
    private LIB.FSTexFieldMD txtdireccion;
    private LIB.FSTexFieldMD txtid;
    private LIB.FSTexFieldMD txtidproductoactualizar;
    private LIB.FSTexFieldMD txtidusuario;
    private LIB.FSTexFieldMD txtnombre;
    private LIB.FSTexFieldMD txtnombreempleado;
    private LIB.FSTexFieldMD txtnombreempleadoactualizar;
    private LIB.FSTexFieldMD txtnombreproductoactualizar;
    private LIB.FSTexFieldMD txtprecio;
    private LIB.FSTexFieldMD txtprecioactualizar;
    private LIB.FSTexFieldMD txtproducto;
    private LIB.FSTexFieldMD txtrfc;
    private LIB.FSTexFieldMD txtstcK;
    private LIB.FSTexFieldMD txtstockactualizar;
    private LIB.FSTexFieldMD txttelefono;
    // End of variables declaration//GEN-END:variables

}
