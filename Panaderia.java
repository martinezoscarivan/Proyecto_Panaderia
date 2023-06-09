
package ClasesOperativas;
import Conexion.Conexion;
import Conexion.ConexionUsuarios;
import Conexion.RellenarCombo;
import Conexion.Venta;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.time.LocalTime;
import java.util.Vector;
import javax.swing.JFormattedTextField;
public class Panaderia extends javax.swing.JFrame {
    DefaultTableModel dtm= new DefaultTableModel();
    DefaultTableModel dtm1= new DefaultTableModel();
     DefaultTableModel d= new DefaultTableModel();
    int cantidad=0, eliminar=0;
    double precio=0;
    double fer=0;
    double pago=0;
    double total=0;
    Venta Vdao = new Venta();
    LocalTime hora= LocalTime.now();
    LocalDate todaysDate = LocalDate.now();
    
    private final String ruta=System.getProperties().getProperty("user.dir");
    
    RellenarCombo empleadoss= new RellenarCombo();
    ArrayList<Venta> listaVentas= new ArrayList<Venta>();
    //private Object txtusuario;
    
    public Panaderia() {
        
    initComponents();
    this.setLocationRelativeTo(null);
    Escritorio.setEnabledAt(1, false);
    Escritorio.setEnabledAt(2, false);
    Escritorio.setEnabledAt(3, false);
    Escritorio.setEnabledAt(4, false);
    
    empleadoss.RellenarComboBoxS("usuarios","nombre",empleados);
    
    this.setTitle("PANADERIA AMANECER");
    setIconImage(getIconImage());
    
    String[] Titulo={" ID ", "  PRODUCTOS " , " CANTIDAD "," PRECIO "," TOTAL"};
    dtm.setColumnIdentifiers(Titulo);   
   
    String[] FACTURA={"ID-PRODUCTO", "NOMBRE", "CANTIDAD", "PRECIO", "TOTAL"};
    d.setColumnIdentifiers(FACTURA);
    Actualizar();
    }
    //***************************************************************************************************************************
   private void ActualizarStock() {
       
        for (int i = 0; i < TablaP.getRowCount(); i++) {
            int id = Integer.parseInt(TablaP.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(TablaP.getValueAt(i, 2).toString());
            int StockActual =Integer.parseInt(txtstock.getText()) - cant;
            Vdao.ActualizarStock(StockActual, id);

        }
    }
    public Image getIconImage ()
    {
        Image retValue=Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Elementos/panaderia.png"));
        return retValue;
    }
    //***************************************************************************************************************************
    
     //***************************************************************************************************************************
    public void ImFactura(String Nombre)throws FileNotFoundException,DocumentException
    {
     String id="" ,p="", c="", pre="", total="";
    
        FileOutputStream archivo = new FileOutputStream(Nombre + ".pdf");
        Document documento= new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(1);
        documento.add(parrafo);
        documento.add(new Paragraph ( "                                                          FACTURA                                     "+ "\n"));
        documento.add(new Paragraph ( " NOMBRE DE LA EMPRESA:" + "     PANADERIA AMANECER "+ "\n"));
        documento.add(new Paragraph ( " DIRECCION: "+ "   MELCHOR OCAMPO #115, ZONA CENTRO \n"));
        documento.add(new Paragraph ( " CIUDAD: " + "    SANTA MARIA DEL RIO, SAN LUIS POTOSI"  +"\n"));
        documento.add(new Paragraph ( " RFC: "+ "   OSJO1604010I6 \n"));
        documento.add(new Paragraph ( " TELEFONO: "+ "    4851080403 \n")); 
        documento.add(new Paragraph ("============================================================="+ "\n"));
        documento.add(new Paragraph ( "                                      NUMERO DE FACTURA:"+ txtNP.getText()+"\n"));
        documento.add(new Paragraph ( "                                      FECHA: "+ todaysDate +"\n")); 
        documento.add(new Paragraph ( "                                      HORA: "+ hora +"\n"));                                                                                               
        documento.add(new Paragraph ( "                                      TERMINOS: "+ "PAGADERO AL RECIBIRSE "+"\n"));  
        documento.add(new Paragraph ("============================================================="));
        documento.add(new Paragraph ( "                    FACTURA A:              "+ "\n"));
        documento.add(new Paragraph("NOMBRE DE LA EMPRESA o CLIENTE: " + txtNP.getText() +"\n" ));
        documento.add(new Paragraph("RFC: "+ txtrfc.getText() +"\n"));
        documento.add(new Paragraph("DIRECCION: " + txtDireccion.getText() +"\n" ));
        documento.add(new Paragraph("C.P: " + txtcodigopostal.getText() +"\n" ));
        documento.add(new Paragraph("CIUDAD: " + txtciudad.getText() +"\n" ));
        documento.add(new Paragraph("CORREO ELECTRONICO: " + txtcorreo.getText() +"\n" ));              
        documento.add(new Paragraph("TELEFONO: " + txtTelefono.getText() )); 
        documento.add(new Paragraph ("                                                "));
        documento.add(new Paragraph ("================================================================"));
        documento.add(new Paragraph ("                            PRODUCTOS COMPRADOS      "+                "" ));
        documento.add(new Paragraph ("   id     |        PRODUCTO             |    \t |   CANTIDAD  \t  |    PRECIO \t   |     TOTAL"));
        documento.add(new Paragraph ("================================================================"));
        for(int i=0; i<dtm.getRowCount(); i++)
        {
        id=(TablaP.getValueAt(i, 0).toString());
        p=(TablaP.getValueAt(i, 1).toString());
        c=(TablaP.getValueAt(i, 2).toString());
        pre=(TablaP.getValueAt(i, 3).toString());
        total=(TablaP.getValueAt(i, 4).toString());
        documento.add(new Paragraph( id    +"         " +  p    +   "                          "+    pre        +  "                    " +     c    + "             " +    total));     
        }
        documento.add(new Paragraph("=================================================================="+ "\n"));
        documento.add(new Paragraph("                                                          "));
        documento.add(new Paragraph("TOTAL:              " +  txttotalc.getText() +"\n" ));
        documento.add(new Paragraph("                                         "+"\n"));
        documento.add(new Paragraph("                                     ¡ GRACIAS POR SU COMPRA !                     "+"\n" ));     
        documento.add(new Paragraph ("      "+ "\n" ));
        documento.add(new Paragraph ("                                                                       "+ "\n" ));
        documento.close();
        JOptionPane.showMessageDialog(null," FACTURA CREADA CORRECTAMENTE");   
    }
    //***************************************************************************************************************************
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
    
    //***************************************************************************************************************************
    
   // public void Cambio()
    {
     fer=(pago-total);
    }
//***************************************************************************************************************************
  public void MostrarProducto() {
  String cod, sql;
  ConexionUsuarios cu= new ConexionUsuarios(); 
        Connection conect=cu.conectar();
  try {
       
   cod = PRODUCTOS.getValueAt(PRODUCTOS.getSelectedRow(), 0).toString();
   sql = "SELECT * FROM producto WHERE idProducto='" + cod + "'";
   Statement st=(Statement) conect.createStatement();
   ResultSet rs= st.executeQuery(sql);
   rs = st.executeQuery(sql);
   if (rs.next()) {
    etqvendedor.setText(rs.getString(1));
    nombreproducto.setText(rs.getString(2));
    txtprecio.setText(rs.getString(3));
    txtstock.setText(rs.getString(4));
   }
  } catch (Exception a) {
   JOptionPane.showMessageDialog(null,a.getMessage());
  }
  
 }
    //***************************************************************************************************************************
    public void Ticket()
    {
        String id="", p="", c="", pre="", total="";
        
    File Archivo= new File("C:\\Users\\marti\\OneDrive\\Documentos\\NetBeansProjects\\Panaderia_Oscar\\Ticket\\VENTA.txt");
    InputStreamReader isr= new InputStreamReader(System.in);
    BufferedReader Arch= new BufferedReader(isr);
    FileWriter linea_txt; 
    try {
        linea_txt = new FileWriter(Archivo,true);
        linea_txt.write( "                    TICKET                   "+ "\n");
        linea_txt.write("               PANADERIA AMANECER" +"\n" );
        linea_txt.write("         CALLE MELCHOR OCAMPO #115, ZONA CENTRO   " + "\n" );
        linea_txt.write("         SANTA MARIA DEL RIO, S.L.P     " +"\n" );
        linea_txt.write("         RFC:  OSJO160401OI6                          " + "\n");
        linea_txt.write("         TELEFONO: 4851080635            "+"\n");
        linea_txt.write("         FECHA: "+ todaysDate+"\n");
        linea_txt.write("         HORA: "+ hora+"\n");
        linea_txt.write("==================================================================="+ "\n");
        linea_txt.write("                    PRODUCTOS COMPRADOS                         "+ "\n" );
        linea_txt.write("  ID      |      PRODUCTO        |    PRECIO      |    CANTIDAD    |   TOTAL \n");
        linea_txt.write("---------------------------------------------------------------------------------\n");
        for(int i=0; i<dtm.getRowCount(); i++)
        {
        id=(FACTURA.getValueAt(i,0).toString());
        p=(FACTURA.getValueAt(i, 1).toString());
        pre=(FACTURA.getValueAt(i, 2).toString());
        c=(FACTURA.getValueAt(i, 3).toString());
        total=(FACTURA.getValueAt(i, 4).toString());
        
        linea_txt.write(id + "\t   " +  p  + "\t        "+    pre   + " \t            "+    c  +   " \t             " +    total  +"\n");     
        }
        linea_txt.write("                                                            " + "\n");
        linea_txt.write("--------------------------------------------------------------------"+ "\n");
        linea_txt.write("TOTAL:                   " + txttotalc.getText() +"\n" );
        linea_txt.write("===================================================================="+ "\n");
        linea_txt.write("                 ¡GRACIAS POR SU COMPRA!           "+"\n" );  
        linea_txt.write("                                                          "); 
        linea_txt.close();
        JOptionPane.showMessageDialog(null,"TICKET IMPRESO CORRECTAMENTE");
    } catch (IOException ex) {
        Logger.getLogger(Panaderia.class.getName()).log(Level.SEVERE, null, ex);
    }    
    }
    
    //***************************************************************************************************************************
    public void CargarTabla()
    {
        String [] Actualizar= {" ID-PRODUCTO ", " NOMBRE DEL PRODUCTO ", "PRECIO ", " STOCK "};
        String [] Registro= new String[50];
        String sql="Select *from producto";
        dtm1= new DefaultTableModel(null, Actualizar);
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
                dtm1.addRow(Registro);
                etqvendedor.setText(rs.getString(1));
                nombreproducto.setText(rs.getString(2));
                txtprecio.setText(rs.getString(3));
                txtstock.setText(rs.getString(4));
            }
            PRODUCTOS.setModel(dtm1);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error" + e);
        }
    }
    
    
    public void agregarproducto()
    {
     try
     {
         if (etqvendedor.getText().equals("")) 
        {
       JOptionPane.showMessageDialog(null,"SELECCIONE PRODUCTO");
       }
            if (txtcantidad.getText().equals("")) 
            {
            JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD A COMPRAR");
            }
                else
                {
                 int cant,stock;
                 double precio;
                cant=Integer.parseInt(txtcantidad.getText());
                stock=Integer.parseInt(txtstock.getText());
            if(cant<=0)
            {
           JOptionPane.showMessageDialog(null,"INGRESE CANTIDAD MAYOR A 0"); 
           txtcantidad.setText("");
            }
            if(cant>stock){
           JOptionPane.showMessageDialog(null,"NO CUENTA CON EL STOCK NECESARIO");
           txtstock.setText("");
  }
                            String cod = etqvendedor.getText();
                            String cantidad=txtcantidad.getText();
                            String nomproducto=nombreproducto.getText();
                            String precios=txtprecio.getText();
 
  Double totalproducto=Double.parseDouble(precios)*Integer.parseInt(cantidad);
  DefaultTableModel modelo=(DefaultTableModel)TablaP.getModel();
  Vector vRow=new Vector();
  
  vRow.addElement(cod); 
  vRow.addElement(nomproducto);
  vRow.addElement(cantidad);
  vRow.addElement(precios);
  vRow.addElement(totalproducto);
  modelo.addRow(vRow);
  CalcularTotal();   
     } 
  }
  catch(Exception e){
   JOptionPane.showMessageDialog(null, e.getMessage());
  }  
 }
    
 public void registrarVD() throws SQLException
 {
     String id=etqvendedor.getText();
     String nombre_producto=nombreproducto.getText();
     String  cantidad=txtcantidad.getText();
     String precio=txtprecio.getText();
        String ids="1";
         Conexion objdatos= new Conexion();
     try
         {
        if(etqvendedor.getText().equals("") && nombreproducto.getText().equals("")&& txtcantidad.getText().equals("") && txtprecio.getText().equals(""))
        {
        JOptionPane.showMessageDialog(null, "DEBES DE LLENAR EL CAMPO VACIO" );
        }
    else 
    {
        if(etqvendedor.getText().equals("") || nombreproducto.getText().equals("") || txtcantidad.getText().equals("") || txtprecio.getText().equals(""))
        {
        JOptionPane.showMessageDialog (null, " DEBES DE LLENAR TODOS LOS CAMPOS  ");
        }
        else
        {
       
        objdatos.conectar();
        String sql="insert into ventad (nombre_pro, cantidad ,precio, fecha)value ('"+nombre_producto + "','"+ cantidad + "','"+ precio +"','"+todaysDate+"');";
        System.out.println(sql);
        objdatos.actualizar(sql);
     //   registrardetalle();
        JOptionPane.showMessageDialog(null,"LA VENTA SE GUARDO EXITOSAMENTE");
        }  
    }
     }catch(SQLException ex)
        {
        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
     finally
     {
         try
         {
     objdatos.Off();
     }catch(Exception e)
     {
         JOptionPane.showMessageDialog(null,"ERROR");
     }
     }
 }
 
  public void Eliminar()
  {
   int fila;
   try{
   DefaultTableModel tbl = (DefaultTableModel) TablaP.getModel();
   
      fila = TablaP.getSelectedRow();
      tbl.removeRow(fila);
      CalcularTotal();
}
     catch(Exception e){
           JOptionPane.showMessageDialog(null, "SELECCIONE FILA A QUITAR");
             }
  }
    
  public void CalcularTotal() {
 total= 0.00;
 int numerofila=TablaP.getRowCount();
 for (int i=0; i<numerofila; i++)
 {
    double calcular=Double.parseDouble(String.valueOf(TablaP.getModel().getValueAt(i,4)));
    total=total+ calcular;
    txttotalc.setText(String.valueOf(total));
 }
 }
 
 
  public void Actualizar()
        {
        while(dtm.getRowCount()>0){
            dtm.removeRow(0);
        }
        for(Venta v : listaVentas)
        {
          Object X[]= new Object[5];
          X[0]=v.getId();
          X[1]=v.getDescripcion();
          X[2]=v.getCantidad();
          X[3]=v.getPrecio();
          X[4]=v.getImporte(); 
          dtm.addRow(X); 
        }
        FACTURA.setModel(dtm);
    }
  
   public boolean JuntarVenta(Venta nueva)
    {  
        int ncant=0;
        for (Venta v: listaVentas)
        {
          if(v.getId()==nueva.getId())
          {
            int ca=Integer.parseInt(txtcantidad.getText());
            double pre=Double.parseDouble(txtprecio.getText());
            v.setCantidad(ca);
            v.setImporte(v.getPrecio()*ca);
            return true;
          }
    }
         return false;
    }
   
   public void agregar()
   {
       Venta venta= new Venta();
       int id=Integer.parseInt(etqvendedor.getText());
        String pro=nombreproducto.getText();
        int ca=Integer.parseInt(txtcantidad.getText());
        double pre=Double.parseDouble(txtprecio.getText());
        System.out.println(pre);
        System.out.println(ca);
        venta.setId(id);
        venta.setDescripcion(pro);
        venta.setCantidad(ca);
        venta.setPrecio(pre);
        venta.setImporte(pre*ca);
        if(!JuntarVenta(venta))
        {
            listaVentas.add(venta);
        }
        else
        {
            borrarVenta();
            Actualizar();
        }
   }
     public void borrarVenta()
    {
        precio=0;
        cantidad=0;
        CalcularTotal();
    }   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fSButtonMD1 = new LIB.FSButtonMD();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fSGradientPanel3 = new LIB.FSGradientPanel();
        etqrepostero = new javax.swing.JLabel();
        etqpanadero = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PanelMenu = new javax.swing.JPanel();
        fSGradientPanel2 = new LIB.FSGradientPanel();
        btnventa = new javax.swing.JButton();
        btncliente = new javax.swing.JButton();
        btnfactura1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        Escritorio = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        fSGradientPanel1 = new LIB.FSGradientPanel();
        btnactualizar = new javax.swing.JButton();
        etqProductopa = new javax.swing.JLabel();
        etqvendedor = new javax.swing.JTextField();
        etqCantidad2 = new javax.swing.JLabel();
        etqCantidad3 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        txtprecio = new javax.swing.JTextField();
        etqCantidad4 = new javax.swing.JLabel();
        txttotalc = new javax.swing.JTextField();
        etqCantidad5 = new javax.swing.JLabel();
        nombreproducto = new javax.swing.JTextField();
        etqCantidad7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        PRODUCTOS = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        txtcantidad = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaP = new javax.swing.JTable();
        etqCantidad9 = new javax.swing.JLabel();
        etqCantidad10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        empleados = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        fSGradientPanel4 = new LIB.FSGradientPanel();
        txtcorreo = new javax.swing.JTextField();
        etqCorreo = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtciudad = new javax.swing.JTextField();
        etqciudad = new javax.swing.JLabel();
        EtqDireccion1 = new javax.swing.JLabel();
        txtNP = new javax.swing.JTextField();
        EtqDireccion = new javax.swing.JLabel();
        txtcodigopostal = new javax.swing.JTextField();
        etqcp = new javax.swing.JLabel();
        txtrfc = new javax.swing.JTextField();
        etqrfc1 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JFormattedTextField();
        etqtelefono = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtapellidomaterno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtapellidopaterno = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        fSGradientPanel5 = new LIB.FSGradientPanel();
        btnMensajes = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        fSGradientPanel7 = new LIB.FSGradientPanel();
        btnguardar = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        fSGradientPanel6 = new LIB.FSGradientPanel();
        btnTicket = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnMensaje = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLeer = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FACTURA = new javax.swing.JTable();

        fSButtonMD1.setText("fSButtonMD1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fSGradientPanel3.setBackground(new java.awt.Color(255, 255, 255));
        fSGradientPanel3.setFSEndColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel3.setFSStartColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etqrepostero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Repostero.png"))); // NOI18N
        etqrepostero.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fSGradientPanel3.add(etqrepostero, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 110, 110));

        etqpanadero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/pan.png"))); // NOI18N
        etqpanadero.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fSGradientPanel3.add(etqpanadero, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 110, 110));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setText("SISTEMA DE VENTAS");
        fSGradientPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 380, -1));

        jPanel2.add(fSGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 110));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 100));

        PanelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fSGradientPanel2.setBackground(new java.awt.Color(255, 255, 153));
        fSGradientPanel2.setFSEndColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel2.setFSStartColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnventa.setBackground(new java.awt.Color(255, 0, 0));
        btnventa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnventa.setForeground(new java.awt.Color(255, 255, 255));
        btnventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/punto-de-venta.png"))); // NOI18N
        btnventa.setText("     VENTAS");
        btnventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnventaActionPerformed(evt);
            }
        });
        fSGradientPanel2.add(btnventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 180, 48));

        btncliente.setBackground(new java.awt.Color(255, 0, 0));
        btncliente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btncliente.setForeground(new java.awt.Color(255, 255, 255));
        btncliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Factura.png"))); // NOI18N
        btncliente.setText("    FACTURA");
        btncliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btncliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclienteActionPerformed(evt);
            }
        });
        fSGradientPanel2.add(btncliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 180, 50));

        btnfactura1.setBackground(new java.awt.Color(255, 0, 0));
        btnfactura1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnfactura1.setForeground(new java.awt.Color(255, 255, 255));
        btnfactura1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/ticket.png"))); // NOI18N
        btnfactura1.setText("    TICKET");
        btnfactura1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfactura1ActionPerformed(evt);
            }
        });
        fSGradientPanel2.add(btnfactura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 180, 50));

        jButton6.setBackground(new java.awt.Color(204, 0, 0));
        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/cerrar-sesion.png"))); // NOI18N
        jButton6.setText("CERRAR SESIÓN");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        fSGradientPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 180, 50));

        PanelMenu.add(fSGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 540));

        jPanel1.add(PanelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 200, 540));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Chef.png"))); // NOI18N
        jLabel7.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel7)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(63, 63, 63))
        );

        Escritorio.addTab("MENU", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fSGradientPanel1.setFSEndColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel1.setFSStartColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnactualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Agregar.png"))); // NOI18N
        btnactualizar.setBorder(null);
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });
        btnactualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnactualizarKeyReleased(evt);
            }
        });
        fSGradientPanel1.add(btnactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 110, 90));

        etqProductopa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        etqProductopa.setForeground(new java.awt.Color(153, 0, 0));
        etqProductopa.setText("  PRODUCTOS PANADERIA");
        fSGradientPanel1.add(etqProductopa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 250, -1));

        etqvendedor.setEditable(false);
        etqvendedor.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        fSGradientPanel1.add(etqvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 60, -1));

        etqCantidad2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etqCantidad2.setText("TOTAL");
        fSGradientPanel1.add(etqCantidad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 470, -1, 20));

        etqCantidad3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etqCantidad3.setText("ID-PRODUCTO");
        fSGradientPanel1.add(etqCantidad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 20));

        txtstock.setEditable(false);
        txtstock.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        fSGradientPanel1.add(txtstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 80, -1));

        txtprecio.setEditable(false);
        txtprecio.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        fSGradientPanel1.add(txtprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 80, -1));

        etqCantidad4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etqCantidad4.setText("CANTIDAD");
        fSGradientPanel1.add(etqCantidad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        txttotalc.setEditable(false);
        txttotalc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        fSGradientPanel1.add(txttotalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 470, 80, -1));

        etqCantidad5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etqCantidad5.setText("STOCK");
        fSGradientPanel1.add(etqCantidad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, -1));

        nombreproducto.setEditable(false);
        nombreproducto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        fSGradientPanel1.add(nombreproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 250, -1));

        etqCantidad7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etqCantidad7.setText("PRODUCTO");
        fSGradientPanel1.add(etqCantidad7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, -1, -1));

        PRODUCTOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        PRODUCTOS.setOpaque(false);
        PRODUCTOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PRODUCTOSMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(PRODUCTOS);

        fSGradientPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 540, 90));

        jButton7.setText("CARGAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        fSGradientPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtcantidad.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        fSGradientPanel1.add(txtcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 110, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/limpieza-de-datos.png"))); // NOI18N
        jButton3.setText("LIMPIAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        fSGradientPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        TablaP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TablaP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID-PRODUCTO", "NOMBRE", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
        jScrollPane3.setViewportView(TablaP);

        fSGradientPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 630, 110));

        etqCantidad9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etqCantidad9.setText("VENDEDOR");
        fSGradientPanel1.add(etqCantidad9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, -1, -1));

        etqCantidad10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etqCantidad10.setText("PRECIO");
        fSGradientPanel1.add(etqCantidad10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Guardar.png"))); // NOI18N
        jButton2.setText("GUARDAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        fSGradientPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 460, 140, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/borrar.png"))); // NOI18N
        jButton1.setText("ELIMINAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        fSGradientPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 460, 120, 40));

        empleados.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        empleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONA UNA OPCION" }));
        fSGradientPanel1.add(empleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 200, -1));

        jPanel5.add(fSGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 780, 570));

        Escritorio.addTab("MENU 1", jPanel5);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fSGradientPanel4.setFSEndColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel4.setFSStartColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtcorreo.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtcorreo.setToolTipText("Ingresa el Correo Electronico");
        txtcorreo.setOpaque(false);
        txtcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreoKeyTyped(evt);
            }
        });
        fSGradientPanel4.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 200, 30));

        etqCorreo.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        etqCorreo.setText("CORREO ELECTRONICO");
        fSGradientPanel4.add(etqCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        txtDireccion.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtDireccion.setToolTipText("Ingresa la Dirección");
        txtDireccion.setOpaque(false);
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        fSGradientPanel4.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 200, 30));

        txtciudad.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtciudad.setToolTipText("Ingresa la Ciudad");
        txtciudad.setOpaque(false);
        txtciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtciudadKeyTyped(evt);
            }
        });
        fSGradientPanel4.add(txtciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 190, 30));

        etqciudad.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        etqciudad.setText("CIUDAD");
        fSGradientPanel4.add(etqciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, -1, 20));

        EtqDireccion1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        EtqDireccion1.setText("DIRECCION");
        fSGradientPanel4.add(EtqDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, 10));

        txtNP.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtNP.setToolTipText("Ingresa el Nombre del Cliente o Empresa");
        txtNP.setOpaque(false);
        txtNP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNPActionPerformed(evt);
            }
        });
        txtNP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNPKeyTyped(evt);
            }
        });
        fSGradientPanel4.add(txtNP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 200, 30));

        EtqDireccion.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        EtqDireccion.setText("NOMBRE");
        fSGradientPanel4.add(EtqDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        txtcodigopostal.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtcodigopostal.setToolTipText("Ingresa el Codigo Postal");
        txtcodigopostal.setOpaque(false);
        txtcodigopostal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigopostalKeyTyped(evt);
            }
        });
        fSGradientPanel4.add(txtcodigopostal, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 190, 30));

        etqcp.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        etqcp.setText("C.P");
        fSGradientPanel4.add(etqcp, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, -1, -1));

        txtrfc.setFont(new java.awt.Font("Comic Sans MS", 3, 11)); // NOI18N
        txtrfc.setToolTipText("Ingresa el RFC");
        txtrfc.setOpaque(false);
        txtrfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrfcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrfcKeyTyped(evt);
            }
        });
        fSGradientPanel4.add(txtrfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 190, 30));

        etqrfc1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        etqrfc1.setText("RFC");
        fSGradientPanel4.add(etqrfc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, -1, 20));

        try
        {
            MaskFormatter mf= new MaskFormatter("(###) ### - ####");
            mf.setPlaceholderCharacter('_');
            txtTelefono.setFormatterFactory(new DefaultFormatterFactory(mf));
        }catch(ParseException ex)
        {
            JOptionPane.showMessageDialog(null,"INGRESO ERRORES");
        }
        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###) - ### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefono.setToolTipText("Ingresa el Telefono");
        txtTelefono.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtTelefono.setOpaque(false);
        fSGradientPanel4.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 200, 30));

        etqtelefono.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        etqtelefono.setText("TELEFONO");
        fSGradientPanel4.add(etqtelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 0, 0));
        jLabel2.setFont(new java.awt.Font("PMingLiU-ExtB", 3, 14)); // NOI18N
        jLabel2.setText("DATOS DE LA EMPRESA O CLIENTE DE LA COMPRA");
        fSGradientPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("FORMULARIO ");
        fSGradientPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, 20));

        txtapellidomaterno.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtapellidomaterno.setOpaque(false);
        txtapellidomaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidomaternoKeyTyped(evt);
            }
        });
        fSGradientPanel4.add(txtapellidomaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 200, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel1.setText("APELLIDO MATERNO");
        fSGradientPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel5.setText("APELLIDO PATERNO");
        fSGradientPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, -1, -1));

        txtapellidopaterno.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtapellidopaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidopaternoActionPerformed(evt);
            }
        });
        txtapellidopaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidopaternoKeyTyped(evt);
            }
        });
        fSGradientPanel4.add(txtapellidopaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 190, 30));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("FACTURA");
        fSGradientPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, -1, -1));

        fSGradientPanel5.setFSEndColor(new java.awt.Color(0, 0, 0));
        fSGradientPanel5.setFSStartColor(new java.awt.Color(0, 0, 0));

        btnMensajes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnMensajes.setText("MENSAJE");
        btnMensajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMensajesActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Guardar.png"))); // NOI18N
        jButton4.setText("GUARDAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btneliminar.setBackground(new java.awt.Color(255, 204, 204));
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/borrar.png"))); // NOI18N
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fSGradientPanel5Layout = new javax.swing.GroupLayout(fSGradientPanel5);
        fSGradientPanel5.setLayout(fSGradientPanel5Layout);
        fSGradientPanel5Layout.setHorizontalGroup(
            fSGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fSGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fSGradientPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        fSGradientPanel5Layout.setVerticalGroup(
            fSGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fSGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fSGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fSGradientPanel4.add(fSGradientPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 270, 110));

        fSGradientPanel7.setFSEndColor(new java.awt.Color(0, 0, 0));
        fSGradientPanel7.setFSStartColor(new java.awt.Color(0, 0, 0));

        btnguardar.setBackground(new java.awt.Color(255, 204, 204));
        btnguardar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Factura.png"))); // NOI18N
        btnguardar.setText("GENERAR");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 204, 204));
        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/computadora.png"))); // NOI18N
        jButton9.setText("IMPRIMIR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fSGradientPanel7Layout = new javax.swing.GroupLayout(fSGradientPanel7);
        fSGradientPanel7.setLayout(fSGradientPanel7Layout);
        fSGradientPanel7Layout.setHorizontalGroup(
            fSGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fSGradientPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        fSGradientPanel7Layout.setVerticalGroup(
            fSGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fSGradientPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fSGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar)
                    .addComponent(jButton9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fSGradientPanel4.add(fSGradientPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 310, 60));

        jPanel7.add(fSGradientPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 780, 550));

        Escritorio.addTab("MENU 2", jPanel7);

        fSGradientPanel6.setFSEndColor(new java.awt.Color(255, 255, 255));
        fSGradientPanel6.setFSStartColor(new java.awt.Color(255, 255, 255));

        btnTicket.setBackground(new java.awt.Color(255, 204, 204));
        btnTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/ticket.png"))); // NOI18N
        btnTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTicketActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("           TICKET A IMPRIMIR");

        btnMensaje.setBackground(new java.awt.Color(255, 204, 204));
        btnMensaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/persona.png"))); // NOI18N
        btnMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMensajeActionPerformed(evt);
            }
        });

        txtLeer.setColumns(20);
        txtLeer.setRows(5);
        jScrollPane2.setViewportView(txtLeer);

        jButton5.setBackground(new java.awt.Color(255, 204, 204));
        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton5.setText("MENSAJE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton8.setText("LIMPIAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fSGradientPanel6Layout = new javax.swing.GroupLayout(fSGradientPanel6);
        fSGradientPanel6.setLayout(fSGradientPanel6Layout);
        fSGradientPanel6Layout.setHorizontalGroup(
            fSGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fSGradientPanel6Layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(fSGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fSGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnTicket, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addComponent(btnMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton5)
                    .addComponent(jButton8))
                .addGap(63, 63, 63))
            .addGroup(fSGradientPanel6Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fSGradientPanel6Layout.setVerticalGroup(
            fSGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fSGradientPanel6Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGroup(fSGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fSGradientPanel6Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addGroup(fSGradientPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fSGradientPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(fSGradientPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Escritorio.addTab("MENU 3", jPanel3);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        FACTURA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID-PRODUCTO", "NOMBRE", "CANTIDAD", "PRECIO ", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(FACTURA);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );

        Escritorio.addTab("MENU 4", jPanel11);

        jPanel1.add(Escritorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 780, 560));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnventaActionPerformed
        Escritorio.setSelectedIndex(1);
        Escritorio.setEnabledAt(0, false);
        Escritorio.setEnabledAt(1, true);
        Escritorio.setEnabledAt(2, false);
        Escritorio.setEnabledAt(3, false);
        Escritorio.setEnabledAt(4, false);
    }//GEN-LAST:event_btnventaActionPerformed

    private void btnclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclienteActionPerformed
        Escritorio.setSelectedIndex(2);
        Escritorio.setEnabledAt(0, false);
        Escritorio.setEnabledAt(1, false);
        Escritorio.setEnabledAt(2, true);
        Escritorio.setEnabledAt(3, false);
        Escritorio.setEnabledAt(4, false);
    }//GEN-LAST:event_btnclienteActionPerformed

    private void btnfactura1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfactura1ActionPerformed
        Escritorio.setSelectedIndex(3);
        Escritorio.setEnabledAt(0, false);
        Escritorio.setEnabledAt(1, false);
        Escritorio.setEnabledAt(2, false);
        Escritorio.setEnabledAt(3, true);
        Escritorio.setEnabledAt(4, true);
    }//GEN-LAST:event_btnfactura1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Mensaje M= new Mensaje();
        M.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMensajeActionPerformed
        File archivo = new File("C:\\Users\\marti\\OneDrive\\Documentos\\NetBeansProjects\\Panaderia_Oscar\\Ticket\\VENTA.txt");

        try
        {
            BufferedReader leer= new BufferedReader(new FileReader(archivo));
            String linea= leer.readLine();
            while(linea != null)
            {
                txtLeer.append(linea+ "\n");
                linea= leer.readLine();
            }
        }catch(Exception ex)
        {
            Logger.getLogger(Panaderia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMensajeActionPerformed

    private void btnTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTicketActionPerformed
        Ticket();
    }//GEN-LAST:event_btnTicketActionPerformed

    private void btnMensajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMensajesActionPerformed
        Mensaje M= new Mensaje();
        M.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMensajesActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(!txtNP.getText().isEmpty())
        {
            Abrir(txtNP.getText());
        }
        else
        {
            JOptionPane.showMessageDialog(null," NO SE ENCONTRO EL ARCHIVO");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtrfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrfcKeyTyped
        if(txtrfc.getText().length()>=13)
        {
            evt.consume();
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "INGRESA SOLO 13 CARACTERES");
        }
    }//GEN-LAST:event_txtrfcKeyTyped

    private void txtrfcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrfcKeyReleased
        //txtFactura.setText(txtrfc.getText());
    }//GEN-LAST:event_txtrfcKeyReleased

    private void txtcodigopostalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigopostalKeyTyped
        char validar=evt.getKeyChar();
        if(Character.isLetter(validar)||txtcodigopostal.getText().length()>=5)
        {
            evt.consume();
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "INGRESA SOLO NUMEROS Y SOLO ESCRIBE 5 DIGITOS");
        }
    }//GEN-LAST:event_txtcodigopostalKeyTyped

    private void txtNPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNPActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
      //  llenar();
        try
        {
            ImFactura(txtNP.getText());
        }catch(FileNotFoundException ex)
        {
            Logger.getLogger(Panaderia.class.getName()).log(Level.SEVERE, null, ex);
        }catch(DocumentException ex)
        {
            Logger.getLogger(Panaderia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        txtNP.setText("");
        txtrfc.setText("");
        txtDireccion.setText("");
        txtcodigopostal.setText("");
        txtciudad.setText("");
        txtcorreo.setText("");
        txtTelefono.setText("");
        JOptionPane.showMessageDialog(null,"DEBES DE LLENAR LOS CAMPOS");
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
    agregar();
    agregarproducto();
    ActualizarStock();
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     Login L= new Login();
     L.setVisible(true);
     this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnactualizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnactualizarKeyReleased

    }//GEN-LAST:event_btnactualizarKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    CargarTabla();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void PRODUCTOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PRODUCTOSMouseClicked
    MostrarProducto();
    }//GEN-LAST:event_PRODUCTOSMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    Eliminar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    String monto=txttotalc.getText();
    System.out.println(monto);
    String vendedor=(String) empleados.getSelectedItem();
    Conexion objdatos= new Conexion();    
    try
         {
        if(txttotalc.equals("")&& empleados.getSelectedItem().equals(""))
        {
        JOptionPane.showMessageDialog(null, "DEBES DE LLENAR EL CAMPO VACIO" );
        }
    else 
    {
        if(txttotalc.getText().equals("") || empleados.getSelectedItem().equals(""))
        {
        JOptionPane.showMessageDialog (null, " DEBES DE LLENAR TODOS LOS CAMPOS  ");
        }
        else
        {
        objdatos.conectar();
        String sql="insert into venta (fecha, monto, vendedor) value ('"+ todaysDate + "','"+ monto + "','"+ vendedor +"');";
        objdatos.actualizar(sql);
        //registrarVD();
        JOptionPane.showMessageDialog(null,"LA VENTA SE GUARDO EXITOSAMENTE");
        }  
    }
     }catch(SQLException ex)
        {
        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
     {
         try
         {
     objdatos.Off();
     }catch(Exception e)
     {
         JOptionPane.showMessageDialog(null,"ERROR");
     }
     }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtapellidopaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidopaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidopaternoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    etqvendedor.setText("");
  nombreproducto.setText("");
  txtprecio.setText("");
  txtstock.setText("");
  txtcantidad.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    String Np=txtNP.getText();
    String direccon=txtDireccion.getText();
    String Cp=txtcodigopostal.getText();
    String ciudad=txtciudad.getText();
    String correo=txtcorreo.getText();
    String telefono=txtTelefono.getText();
    String apellidomaterno=txtapellidomaterno.getText();
    String apellidopaterno=txtapellidopaterno.getText();
    String rfc=txtrfc.getText();
    try
    {
    if(txtNP.getText().equals("")  && txtrfc.equals("")&& txtDireccion.getText().equals("") && txtcodigopostal.equals("") && txtciudad.equals("") && txtcorreo.equals("") && txtTelefono.equals("")&&txtapellidopaterno.getText().equals("") && txtapellidomaterno.getText().equals("") )
    {
    JOptionPane.showMessageDialog(null, " DEBES DE LLENAR TODOS LOS CAMPOS " );
    }
    else 
    {
        if(txtNP.getText().equals("") || txtrfc.getText().equals("") || txtDireccion.getText().equals("") || txtcodigopostal.getText().equals("") || txtciudad.getText().equals("") || txtcorreo.getText().equals("") || txtTelefono.getText().equals("")|| txtapellidopaterno.getText().equals("") || txtapellidomaterno.getText().equals(""))
        {
        JOptionPane.showMessageDialog (null, "DEBES DE LLENAR EL CAMPO VACIO ");
        }
        else
        {
        Conexion objdatos= new Conexion();
        objdatos.conectar();
            
        String sql="insert into cliente (nombre, apellido_paterno, apellido_materno,telefono, direccion, rfc, cp, ciudad, correoelectronico) value ('"+ Np + "','"+ apellidopaterno + "','"+ apellidomaterno +"','"+telefono+"','"+ direccon+"','"+rfc+"','"+Cp+"','"+ciudad+"','"+correo+"');";
        System.out.println(sql);
        objdatos.actualizar(sql);
        JOptionPane.showMessageDialog(null,"DATOS GUARDADOS PARA CREAR LA FACTURA");
        }  
    }
    }catch(SQLException ex)
        {
        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtNPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNPKeyTyped
    Character c= evt.getKeyChar();
    if(!Character.isLetter(c) && c !=KeyEvent.VK_SPACE || txtNP.getText().length()>=30)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtNPKeyTyped

    private void txtapellidomaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidomaternoKeyTyped
    Character c= evt.getKeyChar();
    if(!Character.isLetter(c) || txtapellidomaterno.getText().length()>=25)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtapellidomaternoKeyTyped

    private void txtapellidopaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidopaternoKeyTyped
    Character c= evt.getKeyChar();
    if(!Character.isLetter(c) || txtapellidopaterno.getText().length()>=25)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtapellidopaternoKeyTyped

    private void txtciudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciudadKeyTyped
    if( txtciudad.getText().length()>=30)
    {
        evt.consume(); // no permite escribir en el campo.
    }
    }//GEN-LAST:event_txtciudadKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        if( txtDireccion.getText().length()>=30)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtcorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoKeyTyped
        if(txtcorreo.getText().length()>=45)
        {
            evt.consume();
        }
        
    }//GEN-LAST:event_txtcorreoKeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    txtLeer.setText("");
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(Panaderia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panaderia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panaderia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panaderia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panaderia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Escritorio;
    private javax.swing.JLabel EtqDireccion;
    private javax.swing.JLabel EtqDireccion1;
    private javax.swing.JTable FACTURA;
    private javax.swing.JTable PRODUCTOS;
    private javax.swing.JPanel PanelMenu;
    private javax.swing.JTable TablaP;
    private javax.swing.JButton btnMensaje;
    private javax.swing.JButton btnMensajes;
    private javax.swing.JButton btnTicket;
    public javax.swing.JButton btnactualizar;
    private javax.swing.JButton btncliente;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnfactura1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnventa;
    private javax.swing.JComboBox<String> empleados;
    private javax.swing.JLabel etqCantidad10;
    private javax.swing.JLabel etqCantidad2;
    private javax.swing.JLabel etqCantidad3;
    private javax.swing.JLabel etqCantidad4;
    private javax.swing.JLabel etqCantidad5;
    private javax.swing.JLabel etqCantidad7;
    private javax.swing.JLabel etqCantidad9;
    private javax.swing.JLabel etqCorreo;
    private javax.swing.JLabel etqProductopa;
    private javax.swing.JLabel etqciudad;
    private javax.swing.JLabel etqcp;
    private javax.swing.JLabel etqpanadero;
    private javax.swing.JLabel etqrepostero;
    private javax.swing.JLabel etqrfc1;
    private javax.swing.JLabel etqtelefono;
    public javax.swing.JTextField etqvendedor;
    private LIB.FSButtonMD fSButtonMD1;
    private LIB.FSGradientPanel fSGradientPanel1;
    private LIB.FSGradientPanel fSGradientPanel2;
    private LIB.FSGradientPanel fSGradientPanel3;
    private LIB.FSGradientPanel fSGradientPanel4;
    private LIB.FSGradientPanel fSGradientPanel5;
    private LIB.FSGradientPanel fSGradientPanel6;
    private LIB.FSGradientPanel fSGradientPanel7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField nombreproducto;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextArea txtLeer;
    private javax.swing.JTextField txtNP;
    private javax.swing.JFormattedTextField txtTelefono;
    private javax.swing.JTextField txtapellidomaterno;
    private javax.swing.JTextField txtapellidopaterno;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtciudad;
    private javax.swing.JTextField txtcodigopostal;
    private javax.swing.JTextField txtcorreo;
    public javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtrfc;
    public javax.swing.JTextField txtstock;
    public javax.swing.JTextField txttotalc;
    // End of variables declaration//GEN-END:variables

    
}
