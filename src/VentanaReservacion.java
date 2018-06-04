
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author erick
 */
public class VentanaReservacion extends javax.swing.JFrame
{

	private static String Nombre_dela_Tabla_de_Habitaciones = "Habitaciones";
	private static String Nombre_dela_Tabla_de_Registros = "Registros";

	private static String Usuario_BD = "root";
	private static String Contras_BD = "werick";
	private static String Servidor_BD = "localhost";
	private static String Pueto_BD = "3306";
	private static String Nombre_BD = "Hotel";

	public VentanaReservacion()
	{
		initComponents();
		m = ( DefaultTableModel ) tblListaHabitaciones.getModel();

		cargarTablaDesdeBD();
	}

	private void nuevaReserv()
	{

	}

	private void buscarReserv()
	{

	}

	private void expedirComprobante()
	{

	}

	private Connection conexionBD()
	{
		String url = "jdbc:mysql://" + Servidor_BD + ":" + Pueto_BD + "/" + Nombre_BD;
		System.out.println( "Conectando..." );

		Connection connection = null;

		try
		{
			connection = DriverManager.getConnection( url , Usuario_BD , Contras_BD );

			System.out.println( "Conectado a la base de datos " + Nombre_BD + " !!" );

		}
		catch ( SQLException e )
		{
			System.out.println( "No se pudo conectar a la base de datos " + Nombre_BD );
			System.out.println( e.getMessage() );
		}

		return connection;
	}

	private void filtrarHabitacionesNumero( String cad )
	{
		String q = "select * from " + Nombre_dela_Tabla_de_Habitaciones + " where numero = " + cad + ";";

		Connection c = conexionBD();

		String prueba = "";

		String strNumero;
		String strTipo;
		String strDisponibilidad;
		String strPrecio;

		try
		{

			Statement stmt = c.createStatement();
			stmt.execute( q );
			ResultSet rs = stmt.getResultSet();
			if ( rs != null )
			{
				while ( rs.next() )
				{
					strNumero = rs.getString( "numero" );
					strTipo = rs.getString( "tipo" );
					strDisponibilidad = rs.getString( "disponibilidad" );
					strPrecio = rs.getString( "precio" );

					prueba += "(" + strNumero + "," + strTipo + "," + strDisponibilidad + ","
						+ strPrecio + ")\n";

					agregar_a_tabla( strNumero , strTipo , strDisponibilidad , strPrecio );
				}
			}

			c.close(); // no dejar la conexión abierta

		}
		catch ( SQLException e )
		{
			System.out.println( "Error al cargar datos de la tabla " + Nombre_dela_Tabla_de_Habitaciones );
			System.out.println( e.getMessage() );
		}

		System.out.println( q + "\n" + prueba );

	}

	private void cargarTablaDesdeBD()
	{
		String q = "select * from " + Nombre_dela_Tabla_de_Habitaciones + ";";

		Connection c = conexionBD();

		String prueba = "";

		String strNumero;
		String strTipo;
		String strDisponibilidad;
		String strPrecio;

		try
		{

			Statement stmt = c.createStatement();
			stmt.execute( q );
			ResultSet rs = stmt.getResultSet();
			if ( rs != null )
			{
				while ( rs.next() )
				{
					strNumero = rs.getString( "numero" );
					strTipo = rs.getString( "tipo" );
					strDisponibilidad = rs.getString( "disponibilidad" );
					strPrecio = rs.getString( "precio" );

					prueba += "(" + strNumero + "," + strTipo + "," + strDisponibilidad + ","
						+ strPrecio + ")\n";

					agregar_a_tabla( strNumero , strTipo , strDisponibilidad , strPrecio );
				}
			}

			c.close(); // no dejar la conexión abierta

		}
		catch ( SQLException e )
		{
			System.out.println( "Error al cargar datos de la tabla " + Nombre_dela_Tabla_de_Habitaciones );
			System.out.println( e.getMessage() );
		}

		System.out.println( q + "\n" + prueba );

	}

	private void nuevoRegistro()
	{
		String nombreCliente = txtNombreCliente.getText();
		String edadCliente = txtEdadCliente.getText();

		// sexo.cliente
		String telCliente = txtTelefono.getText();

		String q = "insert into " + Nombre_dela_Tabla_de_Registros + " values ("
			+ nombreCliente + "," + edadCliente + "," + telCliente + ");";

		System.out.println( q );
	}

	public void calendarios()
	{
		fechaInicial.setEnabled( true );
		fechaFinal.setEnabled( true );
	}

	public void dcalendarios()
	{
		fechaInicial.setEnabled( false );
		fechaFinal.setEnabled( false );
	}

	public void habilitarCampos()
	{
		txtNombreCliente.setEditable( true );
		txtEdadCliente.setEditable( true );
		txtTelefono.setEditable( true );
		btnRegistar.setEnabled( true );
		btnCancelarRegistro.setEnabled( true );
		txtNombreCliente.requestFocus();

	}

	public void insertar( String nombre , int edad , String telefono , String fechaEntrada , String fechaSalida , int numeroHabitacion , int costoTotal )
	{
		nombre = txtNombreCliente.getText();
		edad = Integer.parseInt( txtEdadCliente.getText() );
		telefono = txtTelefono.getText();
		numeroHabitacion = Integer.parseInt( txtHabitacion.getText() );
		costoTotal = Integer.parseInt( txtCostoTotal.getText() );

		Connection c = conexionBD();
		String insert = "insert into Reservaciones (nombre,edad,telefono,fechaEntrada,fechaSalida,numeroHabitacion,costoTotal) values(?,?,?,?,?,?,?)";

		try
		{
			Statement stmt = c.createStatement();
			PreparedStatement ps;

			ps = c.prepareStatement( insert );
			ps.setString( 1 , nombre );
			ps.setInt( 2 , edad );
			ps.setString( 3 , telefono );
			ps.setString( 4 , cadFechaInicial );
			ps.setString( 5 , cadFechaFinal );
			ps.setInt( 6 , numeroHabitacion );
			ps.setInt( 7 , costoTotal );

			ps.executeUpdate();

			c.close();
		}
		catch ( SQLException ex )
		{
			Logger.getLogger( VentanaReservacion.class.getName() ).log( Level.SEVERE , null , ex );

		}

	}

	private void agregar_a_tabla( String s1 , String s2 , String s3 , String s4 )
	{

		// aquí agregar las cadenas a la tabla
		Object O[] = new Object[ 4 ];
		O[ 0 ] = s1;
		O[ 1 ] = s2;
		O[ 2 ] = s3;
		O[ 3 ] = s4;
		m.addRow( O );

	}

	@SuppressWarnings ("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtNombreCliente = new javax.swing.JTextField();
        txtEdadCliente = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        btnRegistar = new javax.swing.JButton();
        btnCancelarRegistro = new javax.swing.JButton();
        txtHabitacion = new javax.swing.JTextField();
        txtCostoTotal = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtDias = new javax.swing.JLabel();
        fechaInicial = new com.toedter.calendar.JDateChooser();
        fechaFinal = new com.toedter.calendar.JDateChooser();
        Nombre = new java.awt.Label();
        Nombre3 = new java.awt.Label();
        Nombre4 = new java.awt.Label();
        Nombre5 = new java.awt.Label();
        Nombre2 = new java.awt.Label();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnHacerRegistro = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnBuscarReservacion = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaHabitaciones = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reservación");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1400, 800));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        txtNombreCliente.setEditable(false);
        txtNombreCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtNombreClienteActionPerformed(evt);
            }
        });
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtNombreClienteKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombreCliente);
        txtNombreCliente.setBounds(211, 20, 299, 38);

        txtEdadCliente.setEditable(false);
        txtEdadCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtEdadClienteActionPerformed(evt);
            }
        });
        txtEdadCliente.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtEdadClienteKeyTyped(evt);
            }
        });
        jPanel1.add(txtEdadCliente);
        txtEdadCliente.setBounds(211, 85, 154, 35);

        txtTelefono.setEditable(false);
        txtTelefono.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txtTelefono);
        txtTelefono.setBounds(211, 138, 299, 36);

        btnRegistar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/agregar-usuario (2).png"))); // NOI18N
        btnRegistar.setText("Registrar");
        btnRegistar.setEnabled(false);
        btnRegistar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnRegistarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistar);
        btnRegistar.setBounds(123, 462, 113, 44);

        btnCancelarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/multiplicar.png"))); // NOI18N
        btnCancelarRegistro.setText("Cancelar");
        btnCancelarRegistro.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelarRegistroActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelarRegistro);
        btnCancelarRegistro.setBounds(371, 467, 140, 35);

        txtHabitacion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtHabitacionActionPerformed(evt);
            }
        });
        jPanel1.add(txtHabitacion);
        txtHabitacion.setBounds(211, 367, 213, 34);

        txtCostoTotal.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtCostoTotalKeyTyped(evt);
            }
        });
        jPanel1.add(txtCostoTotal);
        txtCostoTotal.setBounds(211, 421, 213, 35);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/calculadora.png"))); // NOI18N
        jButton2.setText("Calcula");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(362, 313, 108, 31);

        txtDias.setBackground(new java.awt.Color(255, 255, 255));
        txtDias.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDias.setOpaque(true);
        jPanel1.add(txtDias);
        txtDias.setBounds(211, 309, 98, 35);

        fechaInicial.setEnabled(false);
        jPanel1.add(fechaInicial);
        fechaInicial.setBounds(211, 193, 299, 33);

        fechaFinal.setEnabled(false);
        jPanel1.add(fechaFinal);
        fechaFinal.setBounds(211, 246, 299, 33);

        Nombre.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Nombre.setText("Nombre");
        jPanel1.add(Nombre);
        Nombre.setBounds(91, 20, 88, 33);

        Nombre3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Nombre3.setText("Edad");
        jPanel1.add(Nombre3);
        Nombre3.setBounds(123, 76, 56, 33);

        Nombre4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Nombre4.setText("Telefono");
        jPanel1.add(Nombre4);
        Nombre4.setBounds(91, 141, 96, 33);

        Nombre5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Nombre5.setText("Fecha de entrada");
        jPanel1.add(Nombre5);
        Nombre5.setBounds(10, 193, 191, 33);

        Nombre2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Nombre2.setText("Fecha de salida");
        jPanel1.add(Nombre2);
        Nombre2.setBounds(32, 246, 169, 33);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Habitacion");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(80, 360, 159, 34);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Dias de Estancia");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(17, 309, 184, 35);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Costo total");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(80, 420, 114, 29);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(850, 150, 540, 590);

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1410, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 750, 1410, 50);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/hotel-3.png"))); // NOI18N
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Reservaciones");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/boton-de-inicio.png"))); // NOI18N
        jButton1.setText("Inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 427, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(478, 478, 478))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(31, 31, 31))))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 30, 1400, 120);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(0, 51, 204));
        jToolBar1.setForeground(new java.awt.Color(0, 0, 0));
        jToolBar1.setRollover(true);

        btnHacerRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/notas.png"))); // NOI18N
        btnHacerRegistro.setText("Hacer Registro");
        btnHacerRegistro.setEnabled(false);
        btnHacerRegistro.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHacerRegistroActionPerformed(evt);
            }
        });
        jToolBar1.add(btnHacerRegistro);
        jToolBar1.add(jSeparator1);

        btnBuscarReservacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/lupa-de-busqueda_1.png"))); // NOI18N
        btnBuscarReservacion.setText("Buscar habitación");
        btnBuscarReservacion.setFocusable(false);
        btnBuscarReservacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarReservacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscarReservacion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBuscarReservacionActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBuscarReservacion);
        jToolBar1.add(jSeparator2);

        tblListaHabitaciones.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tblListaHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Numero", "Tipo", "Estado", "Precio"
            }
        ));
        tblListaHabitaciones.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblListaHabitacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaHabitaciones);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 160, 840, 590);

        jPanel5.setBackground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1400, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 0, 1400, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

	private void Buscar( String cad )
	{
		String q = "select * from Habitaciones where numero=" + cad + ";";

		Connection c = conexionBD();

		String prueba = "";

		String strNumero;
		String strTipo;
		String strDisponibilidad;
		String strPrecio;

		try
		{

			Statement stmt = c.createStatement();
			stmt.execute( q );
			ResultSet rs = stmt.getResultSet();
			if ( rs != null )
			{
				while ( rs.next() )
				{
					strNumero = rs.getString( "numero" );
					strTipo = rs.getString( "tipo" );
					strDisponibilidad = rs.getString( "disponibilidad" );
					strPrecio = rs.getString( "precio" );

					prueba += "(" + strNumero + "," + strTipo + "," + strDisponibilidad + ","
						+ strPrecio + ")\n";

					m.setRowCount( 0 );
					agregar_a_tabla( strNumero , strTipo , strDisponibilidad , strPrecio );
				}
			}

			c.close(); // no dejar la conexión abierta

		}
		catch ( SQLException e )
		{
			System.out.println( "Error al cargar datos de la tabla Habitaciones" );
			System.out.println( e.getMessage() );
		}

		System.out.println( q + "\n" + prueba );

	}

	private void limpiar()
	{
		txtNombreCliente.setText( "" );
		txtEdadCliente.setText( "" );
		txtTelefono.setText( "" );
		txtHabitacion.setText( "" );
		txtCostoTotal.setText( "" );
		fechaInicial.setCalendar( null );
		fechaFinal.setCalendar( null );
		txtDias.setText( "" );
	}

	private void bloquear()
	{
		txtNombreCliente.setEnabled( false );
		txtEdadCliente.setEnabled( false );
		txtTelefono.setEnabled( false );
		txtHabitacion.setEnabled( false );
		txtCostoTotal.setEnabled( false );
		fechaInicial.setEnabled( false );
		fechaFinal.setEnabled( false );
	}

	private void validaRes() throws ReservacionException
	{
		if ( txtNombreCliente.getText().equals( "" ) )
		{
			txtNombreCliente.requestFocus();
			throw new ReservacionException( "Casilla Nombre sin llenar" );
		}
		if ( txtEdadCliente.getText().equals( "" ) )
		{
			txtEdadCliente.requestFocus();
			throw new ReservacionException( "Casilla Edad sin llenar" );
		}
		if ( txtTelefono.getText().equals( "" ) )
		{
			txtTelefono.requestFocus();
			throw new ReservacionException( "Casilla Telefono sin llenar" );
		}
		if ( fechaInicial.equals( "" ) )
		{
			fechaInicial.requestFocus();
			throw new ReservacionException( "Ingresa Fecha" );
		}
		if ( fechaFinal.equals( "" ) )
		{
			fechaFinal.requestFocus();
			throw new ReservacionException( "Ingresa Fecha" );
		}
	}

    private void btnRegistarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnRegistarActionPerformed
    {//GEN-HEADEREND:event_btnRegistarActionPerformed
		try
		{
			validaRes();

			insertar( "" , 0 , "" , "" , "" , 0 , 0 );
			cambiarDisponibilidad();
			m.setRowCount( 0 );
			cargarTablaDesdeBD();

			limpiar();
			bloquear();
			dcalendarios();
		}
		catch ( ReservacionException e )
		{
			showMessageDialog( rootPane , e.getMessage() );
		}
    }//GEN-LAST:event_btnRegistarActionPerformed

    private void btnHacerRegistroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHacerRegistroActionPerformed
    {//GEN-HEADEREND:event_btnHacerRegistroActionPerformed
		habilitarCampos();
		calendarios();
		txtHabitacion.setText( tblListaHabitaciones.getValueAt( tblListaHabitaciones.getSelectedRow() , 0 ).toString() );
    }//GEN-LAST:event_btnHacerRegistroActionPerformed

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtNombreClienteActionPerformed
    {//GEN-HEADEREND:event_txtNombreClienteActionPerformed
		txtNombreCliente.transferFocus();
    }//GEN-LAST:event_txtNombreClienteActionPerformed

    private void txtEdadClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtEdadClienteActionPerformed
    {//GEN-HEADEREND:event_txtEdadClienteActionPerformed
		txtEdadCliente.transferFocus();
    }//GEN-LAST:event_txtEdadClienteActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtTelefonoActionPerformed
    {//GEN-HEADEREND:event_txtTelefonoActionPerformed
		txtTelefono.transferFocus();
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void btnBuscarReservacionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBuscarReservacionActionPerformed
    {//GEN-HEADEREND:event_btnBuscarReservacionActionPerformed
		String cad = JOptionPane.showInputDialog( "Ingrese el numero de habitacion" );
		Buscar( cad );
    }//GEN-LAST:event_btnBuscarReservacionActionPerformed

    private void tblListaHabitacionesMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblListaHabitacionesMouseClicked
    {//GEN-HEADEREND:event_tblListaHabitacionesMouseClicked
		if ( tblListaHabitaciones.getValueAt( tblListaHabitaciones.getSelectedRow() , 2 ).toString().equals( "Disponible" ) )
		{
			btnHacerRegistro.setEnabled( true );
		}
		else
		{
			btnHacerRegistro.setEnabled( false );
		}
    }//GEN-LAST:event_tblListaHabitacionesMouseClicked

    private void txtEdadClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadClienteKeyTyped
		char e = evt.getKeyChar();
		if ( ( ( e < '0' ) || ( e > '9' ) ) && ( e != '.' ) )
		{
			evt.consume();
		}
    }//GEN-LAST:event_txtEdadClienteKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
		char e = evt.getKeyChar();
		if ( ( ( e < '0' ) || ( e > '9' ) ) && ( e != '.' ) )
		{
			evt.consume();
		}
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
		char e = evt.getKeyChar();
		if ( ( ( e < 'A' ) || ( e > 'Z' ) ) && ( ( e < 'a' ) || ( e > 'z' ) ) && ( e != ' ' ) )
		{
			evt.consume();
		}
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtCostoTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoTotalKeyTyped
		char e = evt.getKeyChar();
		if ( ( ( e < '0' ) || ( e > '9' ) ) && ( e != '.' ) )
		{
			evt.consume();
		}
    }//GEN-LAST:event_txtCostoTotalKeyTyped

    private void btnCancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRegistroActionPerformed
		limpiar();
		//bloquear();
		dcalendarios();
		btnHacerRegistro.setEnabled( false );
		m.setRowCount( 0 );
		cargarTablaDesdeBD();
    }//GEN-LAST:event_btnCancelarRegistroActionPerformed

    private void txtHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHabitacionActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txtHabitacionActionPerformed

	private void cambiarDisponibilidad()
	{

		Connection c = conexionBD();
		String insert = "update Habitaciones set disponibilidad=? where numero=" + tblListaHabitaciones.getValueAt( tblListaHabitaciones.getSelectedRow() , 0 ).toString();

		try
		{
			Statement stmt = c.createStatement();
			PreparedStatement ps;

			ps = c.prepareStatement( insert );
			ps.setString( 1 , "Ocupado" );

			ps.executeUpdate();

			c.close();
		}
		catch ( SQLException ex )
		{
			Logger.getLogger( VentanaReservacion.class.getName() ).log( Level.SEVERE , null , ex );

		}

	}

	private void fechas()
	{
		String finicial, ffinal;

		finicial = new SimpleDateFormat().format( fechaInicial.getDate() );
		String A[] = finicial.split( " " );
		cadFechaInicial = A[ 0 ];
		ffinal = new SimpleDateFormat().format( fechaFinal.getDate() );
		String B[] = ffinal.split( " " );
		cadFechaFinal = B[ 0 ];
	}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		fechas();
		Date fi = fechaInicial.getDate();
		Date ff = fechaFinal.getDate();
		int dias = ( int ) ( ( ff.getTime() - fi.getTime() ) / 86400000 );
		txtDias.setText( dias + "" );
		int costo = Integer.parseInt( ( tblListaHabitaciones.getValueAt( tblListaHabitaciones.getSelectedRow() , 3 ).toString() ) );

		txtCostoTotal.setText( Integer.parseInt( ( tblListaHabitaciones.getValueAt( tblListaHabitaciones.getSelectedRow() , 3 ).toString() ) ) * dias + "" );
    }//GEN-LAST:event_jButton2ActionPerformed

    private void fechaFinalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinalMouseClicked

    }//GEN-LAST:event_fechaFinalMouseClicked

    private void fechaFinalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaFinalPropertyChange

    }//GEN-LAST:event_fechaFinalPropertyChange

    private void fechaFinalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinalMouseExited

    }//GEN-LAST:event_fechaFinalMouseExited

    private void fechaFinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fechaFinalFocusLost
		Date fi = fechaInicial.getDate();
		Date ff = fechaFinal.getDate();
		int dias = ( int ) ( ( ff.getTime() - fi.getTime() ) / 86400000 );
		txtDias.setText( dias + "" );
    }//GEN-LAST:event_fechaFinalFocusLost

    private void fechaFinalInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_fechaFinalInputMethodTextChanged
		// TODO add your handling code here:
    }//GEN-LAST:event_fechaFinalInputMethodTextChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
		Inicio inicio = new Inicio();
		inicio.setVisible( true );
		this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

	public static void main( String args[] )
	{
		java.awt.EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				new VentanaReservacion().setVisible( true );
			}

		} );
	}

	DefaultTableModel m;
	String cadFechaInicial, cadFechaFinal;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label Nombre;
    private java.awt.Label Nombre2;
    private java.awt.Label Nombre3;
    private java.awt.Label Nombre4;
    private java.awt.Label Nombre5;
    private javax.swing.JButton btnBuscarReservacion;
    private javax.swing.JButton btnCancelarRegistro;
    private javax.swing.JButton btnHacerRegistro;
    private javax.swing.JButton btnRegistar;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser fechaFinal;
    private com.toedter.calendar.JDateChooser fechaInicial;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblListaHabitaciones;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JLabel txtDias;
    private javax.swing.JTextField txtEdadCliente;
    private javax.swing.JTextField txtHabitacion;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
