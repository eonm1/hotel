
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Casillas
 */
public class VentanaHabitaciones extends javax.swing.JFrame
{

	/**
	 * Creates new form VentanaHabitacion
	 */
	public VentanaHabitaciones()
	{
		initComponents();
		m = ( DefaultTableModel ) tblHabitaciones.getModel();
		cargarTablaDesdeBD();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings ("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        rdbGrupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rbtnDisponible = new javax.swing.JRadioButton();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        rbtnOcupada = new javax.swing.JRadioButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHabitaciones = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        btnBuscar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnEditar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agregar Habitación");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1400, 800));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 100, 800);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/hotel-3.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(110, 10, 200, 110);

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(1300, 0, 100, 800);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Número: ");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(460, 200, 120, 30);

        txtNumero.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtNumero.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtNumeroActionPerformed(evt);
            }
        });
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtNumeroKeyTyped(evt);
            }
        });
        jPanel3.add(txtNumero);
        txtNumero.setBounds(560, 200, 170, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Tipo :");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(480, 260, 60, 20);

        txtPrecio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtPrecioKeyTyped(evt);
            }
        });
        jPanel3.add(txtPrecio);
        txtPrecio.setBounds(570, 350, 160, 30);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Precio:");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(470, 360, 70, 22);

        rdbGrupo.add(rbtnDisponible);
        rbtnDisponible.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        rbtnDisponible.setText("Disponible");
        jPanel3.add(rbtnDisponible);
        rbtnDisponible.setBounds(540, 300, 130, 27);

        cmbTipo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el tipo de habitación", "Sencilla", "Doble", "Suite" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cmbTipoActionPerformed(evt);
            }
        });
        jPanel3.add(cmbTipo);
        cmbTipo.setBounds(550, 250, 320, 33);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Status:");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(470, 300, 90, 20);

        rdbGrupo.add(rbtnOcupada);
        rbtnOcupada.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        rbtnOcupada.setText("Ocupada");
        jPanel3.add(rbtnOcupada);
        rbtnOcupada.setBounds(690, 300, 110, 27);

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/disco-flexible.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar);
        btnGuardar.setBounds(440, 400, 140, 40);

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/cerrar-2.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelar);
        btnCancelar.setBounds(680, 400, 140, 40);

        tblHabitaciones.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tblHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Número", "Tipo", "Status", "Precio"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tblHabitaciones.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblHabitacionesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHabitaciones);
        if (tblHabitaciones.getColumnModel().getColumnCount() > 0)
        {
            tblHabitaciones.getColumnModel().getColumn(0).setResizable(false);
            tblHabitaciones.getColumnModel().getColumn(1).setResizable(false);
            tblHabitaciones.getColumnModel().getColumn(2).setResizable(false);
            tblHabitaciones.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(240, 480, 710, 240);

        jButton3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/boton-de-inicio.png"))); // NOI18N
        jButton3.setText("Inicio");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);
        jButton3.setBounds(60, 730, 160, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Agregar Habitación");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(510, 60, 260, 50);

        jSeparator1.setBackground(new java.awt.Color(0, 51, 255));
        jPanel3.add(jSeparator1);
        jSeparator1.setBounds(0, 120, 1200, 20);

        jToolBar1.setBackground(new java.awt.Color(0, 51, 153));
        jToolBar1.setRollover(true);

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/lupa-de-busqueda.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBuscarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBuscar);
        jToolBar1.add(jSeparator3);

        btnEditar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/escritor.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEditar);
        jToolBar1.add(jSeparator2);

        btnBorrar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/archivos/boton-eliminar.png"))); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.setEnabled(false);
        btnBorrar.setFocusable(false);
        btnBorrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBorrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBorrar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBorrarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBorrar);

        jPanel3.add(jToolBar1);
        jToolBar1.setBounds(0, 130, 1200, 60);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(100, 0, 1200, 800);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoActionPerformed

	private void validaciones() throws HabitacionException
	{
		if ( txtNumero.getText().equals( "" ) )
		{
			txtNumero.requestFocus();
			throw new HabitacionException( "Casilla Numero sin llenar" );
		}
		if ( txtPrecio.getText().equals( "" ) )
		{
			txtPrecio.requestFocus();
			throw new HabitacionException( "Casilla Precio sin llenar" );
		}

	}
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnGuardarActionPerformed
    {//GEN-HEADEREND:event_btnGuardarActionPerformed
		try
		{
			validaciones();
			insertar();
			borrarCampos();
			m.setRowCount( 0 );
			cargarTablaDesdeBD();
		}
		catch ( HabitacionException e )
		{
			showMessageDialog( rootPane , e.getMessage() );
		}
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblHabitacionesMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblHabitacionesMouseClicked
    {//GEN-HEADEREND:event_tblHabitacionesMouseClicked
		btnEditar.setEnabled( true );
		moverDatos();
		btnGuardar.setEnabled( false );
		btnBorrar.setEnabled( true );

    }//GEN-LAST:event_tblHabitacionesMouseClicked

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBorrarActionPerformed
    {//GEN-HEADEREND:event_btnBorrarActionPerformed
		borrar();
		btnGuardar.setEnabled( true );
		btnEditar.setEnabled( false );
		btnBorrar.setEnabled( false );
		borrarCampos();

    }//GEN-LAST:event_btnBorrarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
		Inicio inicio = new Inicio();
		inicio.setVisible( true );
		this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelarActionPerformed
    {//GEN-HEADEREND:event_btnCancelarActionPerformed
		borrarCampos();
		cargarTablaDesdeBD();
		btnEditar.setEnabled( false );
		btnBorrar.setEnabled( false );
		btnGuardar.setEnabled( true );
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBuscarActionPerformed
    {//GEN-HEADEREND:event_btnBuscarActionPerformed
		String cad = JOptionPane.showInputDialog( "Ingresa el numero de la habitacion" );
		Buscar( cad );
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEditarActionPerformed
    {//GEN-HEADEREND:event_btnEditarActionPerformed
		editar();
		borrarCampos();
		m.setRowCount( 0 );
		cargarTablaDesdeBD();
		btnEditar.setEnabled( false );
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtNumeroActionPerformed
    {//GEN-HEADEREND:event_txtNumeroActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtNumeroKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNumeroKeyTyped
    {//GEN-HEADEREND:event_txtNumeroKeyTyped
		char e = evt.getKeyChar();
		if ( ( ( e < '0' ) || ( e > '9' ) ) )
		{
			evt.consume();
		}
    }//GEN-LAST:event_txtNumeroKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtPrecioKeyTyped
    {//GEN-HEADEREND:event_txtPrecioKeyTyped
		char e = evt.getKeyChar();
		if ( ( ( e < '0' ) || ( e > '9' ) ) )
		{
			evt.consume();
		}
    }//GEN-LAST:event_txtPrecioKeyTyped

	private void borrarCampos()
	{

		txtNumero.setText( "" );
		cmbTipo.setSelectedIndex( 0 );
		rdbGrupo.clearSelection();
		txtPrecio.setText( "" );

	}

	private void moverDatos()
	{
		txtNumero.setText( tblHabitaciones.getValueAt( tblHabitaciones.getSelectedRow() , 0 ).toString() );

		int index = 1;
		if ( ( tblHabitaciones.getValueAt( tblHabitaciones.getSelectedRow() , 1 ).toString() ).equals( "Sencilla" ) )
			index = 1;
		if ( ( tblHabitaciones.getValueAt( tblHabitaciones.getSelectedRow() , 1 ).toString() ).equals( "Doble" ) )
			index = 2;
		if ( ( tblHabitaciones.getValueAt( tblHabitaciones.getSelectedRow() , 1 ).toString() ).equals( "Suite" ) )
			index = 3;
		cmbTipo.setSelectedIndex( index );

		if ( ( tblHabitaciones.getValueAt( tblHabitaciones.getSelectedRow() , 2 ).toString() ).equals( "Disponible" ) )
			rbtnDisponible.setSelected( true );
		if ( ( tblHabitaciones.getValueAt( tblHabitaciones.getSelectedRow() , 2 ).toString() ).equals( "Ocupado" ) )
			rbtnOcupada.setSelected( true );

		txtPrecio.setText( tblHabitaciones.getValueAt( tblHabitaciones.getSelectedRow() , 3 ).toString() );

	}

	private void borrar()
	{
		if ( javax.swing.JOptionPane.showConfirmDialog( this , "¿Desea eliminar este registro" , "Borrar!!!" , JOptionPane.YES_NO_OPTION ) == 0 )
		{
			String cad = "";
			Connection c = conexionBD();
			cad = "delete from Habitaciones where numero =" + tblHabitaciones.getValueAt( tblHabitaciones.getSelectedRow() , 0 ).toString();

			try
			{
				Statement stmt = c.createStatement();
				stmt.executeUpdate( cad );
				m.removeRow( tblHabitaciones.getSelectedRow() );

			}
			catch ( SQLException ex )
			{
				showMessageDialog( this , "Error al borrar" + ex.getMessage() );
			}
		}

	}

	private void editar()
	{
		int numero = Integer.parseInt( txtNumero.getText() );
		String tipo = cmbTipo.getSelectedItem().toString();
		String estado = "Disponible";
		if ( rbtnDisponible.isSelected() == true )
		{
			estado = "Disponible";
		}
		if ( rbtnOcupada.isSelected() == true )
		{
			estado = "Ocupado";
		}
		int precio = Integer.parseInt( txtPrecio.getText() );

		Connection c = conexionBD();
		String insert = "update Habitaciones set numero=?,tipo=?,disponibilidad=?,precio=? where numero=" + tblHabitaciones.getValueAt( tblHabitaciones.getSelectedRow() , 0 ).toString();

		try
		{
			Statement stmt = c.createStatement();
			PreparedStatement ps;

			ps = c.prepareStatement( insert );
			ps.setInt( 1 , numero );
			ps.setString( 2 , tipo );
			ps.setString( 3 , estado );
			ps.setInt( 4 , precio );

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
		Object O[] = new Object[ 4 ];

		O[ 0 ] = s1;
		O[ 1 ] = s2;
		O[ 2 ] = s3;
		O[ 3 ] = s4;
		m.addRow( O );

	}

	private Connection conexionBD()
	{
		String url = "jdbc:mysql://localhost:3306/Hotel";
		System.out.println( "Conectando..." );

		Connection connection = null;

		try
		{
			connection = DriverManager.getConnection( url , "root" , "werick" );

			System.out.println( "Conectado a la base de datos " + "Hotel" + " !!" );

		}
		catch ( SQLException e )
		{
			System.out.println( "No se pudo conectar a la base de datos " + "Hotel" );
			System.out.println( e.getMessage() );
		}

		return connection;
	}

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

	private void cargarTablaDesdeBD()
	{
		String q = "select * from Habitaciones;";

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
			System.out.println( "Error al cargar datos de la tabla Habitaciones" );
			System.out.println( e.getMessage() );
		}

		System.out.println( q + "\n" + prueba );

	}

	private void insertar()
	{

		int numero = Integer.parseInt( txtNumero.getText() );
		String tipo = cmbTipo.getSelectedItem().toString();
		String estado = "Disponible";
		if ( rbtnDisponible.isSelected() == true )
		{
			estado = "Disponible";
		}
		if ( rbtnOcupada.isSelected() == true )
		{
			estado = "Ocupado";
		}
		int precio = Integer.parseInt( txtPrecio.getText() );

		Connection c = conexionBD();
		String insert = "insert into Habitaciones (numero,tipo,disponibilidad,precio) values(?,?,?,?)";

		try
		{
			Statement stmt = c.createStatement();
			PreparedStatement ps;

			ps = c.prepareStatement( insert );
			ps.setInt( 1 , numero );
			ps.setString( 2 , tipo );
			ps.setString( 3 , estado );
			ps.setInt( 4 , precio );

			ps.executeUpdate();

			c.close();
		}
		catch ( SQLException ex )
		{
			Logger.getLogger( VentanaReservacion.class.getName() ).log( Level.SEVERE , null , ex );
			showMessageDialog( this , "Habitacion ya registrada" );
		}

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main( String args[] )
	{
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try
		{
			for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() )
			{
				if ( "Nimbus".equals( info.getName() ) )
				{
					javax.swing.UIManager.setLookAndFeel( info.getClassName() );
					break;
				}
			}
		}
		catch ( ClassNotFoundException ex )
		{
			java.util.logging.Logger.getLogger( VentanaHabitaciones.class.getName() ).log( java.util.logging.Level.SEVERE , null , ex );
		}
		catch ( InstantiationException ex )
		{
			java.util.logging.Logger.getLogger( VentanaHabitaciones.class.getName() ).log( java.util.logging.Level.SEVERE , null , ex );
		}
		catch ( IllegalAccessException ex )
		{
			java.util.logging.Logger.getLogger( VentanaHabitaciones.class.getName() ).log( java.util.logging.Level.SEVERE , null , ex );
		}
		catch ( javax.swing.UnsupportedLookAndFeelException ex )
		{
			java.util.logging.Logger.getLogger( VentanaHabitaciones.class.getName() ).log( java.util.logging.Level.SEVERE , null , ex );
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				new VentanaHabitaciones().setVisible( true );
			}

		} );
	}

	DefaultTableModel m;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton rbtnDisponible;
    private javax.swing.JRadioButton rbtnOcupada;
    private javax.swing.ButtonGroup rdbGrupo;
    private javax.swing.JTable tblHabitaciones;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

}