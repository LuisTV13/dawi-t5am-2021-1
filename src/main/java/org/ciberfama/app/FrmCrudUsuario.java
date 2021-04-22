package org.ciberfama.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class FrmCrudUsuario extends JFrame { 
	
	JPATest01 JPA = new JPATest01(); 
	Usuario u = new	Usuario();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField fechanac;
	private JTextField txtTipo;
	private JTextField txtEstado;
	private JPasswordField txtClave;
	private JTextField txtCodigo;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
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
	public FrmCrudUsuario() { 
		setTitle("Ingreso de Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 708);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(159, 44, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(159, 89, 86, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(159, 138, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		fechanac = new JTextField();
		fechanac.setBounds(159, 231, 86, 20);
		contentPane.add(fechanac);
		fechanac.setColumns(10);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(159, 281, 86, 20);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(159, 325, 86, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(28, 47, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(28, 92, 46, 14);
		contentPane.add(lblApellido);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(28, 141, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(28, 184, 46, 14);
		contentPane.add(lblClave);
		
		JLabel lblFechanacimiento = new JLabel("FechaNacimiento");
		lblFechanacimiento.setBounds(28, 234, 81, 14);
		contentPane.add(lblFechanacimiento);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(28, 284, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(28, 328, 46, 14);
		contentPane.add(lblEstado); 
		
		
		
		
		JButton btnIngresar = new JButton("INGRESAR");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				Insertar();
				
				
			}
		});
		btnIngresar.setBounds(429, 43, 129, 23);
		contentPane.add(btnIngresar);
		
		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
			Actualizar();
			}
		});
		btnActualizar.setBounds(429, 88, 129, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				Eliminar();
			}
		});
		btnEliminar.setBounds(429, 147, 129, 23);
		contentPane.add(btnEliminar); 
		
		txtClave = new JPasswordField();
		txtClave.setBounds(159, 181, 86, 20);
		contentPane.add(txtClave);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(159, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(28, 14, 46, 14);
		contentPane.add(lblCodigo);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Buscar();
			}
		});
		btnBuscar.setBounds(429, 197, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 364, 627, 294);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		JButton btnListado = new JButton("LISTADO");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				txtS.setText("");
				Listar();
			}
		});
		btnListado.setBounds(429, 249, 89, 23);
		contentPane.add(btnListado);
		
		
		
		
	}

	
	protected void Listar() {    
		// Obtener un listado de los usuarios 
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager(); 
		//TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.FindAll", Usuario.class);
		//List<Usuario> lstUsuarios =  consulta.getResultList(); 
		
		TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.FindAll", Usuario.class); 
		//consulta.setParameter("xtipo", 1);
		List<Usuario> lstUsuarios =  consulta.getResultList();
		
		em.close();

		for(Usuario u : lstUsuarios) {
			txtS.append(u.getCodigo() + "\t"+ u.getNombre() + "\t"+ u.getApellido() + "\t"+ u.getUsuario() + "\t"+ u.getClave()+ "\t" + u.getFnacim() + "\t" +u.getTipo() + "\t" + u.getEstado() + "\n" );
		}
	}

	protected void Buscar() {
		int codigo = Integer.parseInt(txtCodigo.getText());
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager(); 
		Usuario u =  em.find(Usuario.class, codigo); 
		em.close();
		//Si existe lo muestra en los campos, sino avisa
		if(u==null) {
			aviso("Usuario "+ codigo + " no existe pana");
		}else {
			txtNombre.setText(u.getNombre()); 
			txtApellido.setText(u.getApellido()); 
			txtUsuario.setText(u.getUsuario()); 
			txtClave.setText(u.getClave()); 
			fechanac.setText(u.getFnacim()); 
			//txtTipo.setText(Integer.parseUnsignedInt(u.getTipo())); 
			//txtEstado.setText(u.getEstado());
			
			
			
		}
		
	}

	private void aviso(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Aviso del sistema", JOptionPane.WARNING_MESSAGE);
		
	}

	protected void Eliminar() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		// 2. crear el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		em.getTransaction().begin();
		Usuario u;
		u=em.getReference(Usuario.class,Integer.parseInt(txtCodigo.getText()));
		if(u==null) {
			System.out.println("No se encontro Producto");
		}else {
			u.getCodigo();
			em.remove(u);
			em.getTransaction().commit();
			System.out.println("Delete Completado"); 
		}
	}

	protected void Actualizar() { 
		
		
		
		Usuario u = new Usuario(); 
		  u.setCodigo(Integer.parseInt(txtCodigo.getText()));
	      u.setNombre(txtNombre.getText()); 
	      u.setApellido(txtApellido.getText()); 
	      u.setUsuario(txtUsuario.getText()); 
	      u.setClave(leerClave());    
	      u.setFnacim(fechanac.getText()); 
	      u.setTipo(Integer.parseInt(txtTipo.getText())); 
	      u.setEstado(Integer.parseInt(txtEstado.getText()));
		
		// grabar el objeto
		// 1. fabricar el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		// 2. crear el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(u); 
			em.getTransaction().commit();
			
			
		} catch (Exception e) {
			System.out.println("error al actualizar : " + e.getMessage());
		}
				
		
	}

	protected void Insertar() { 
		
      Usuario u = new Usuario(); 
      u.setNombre(txtNombre.getText()); 
      u.setApellido(txtApellido.getText()); 
      u.setUsuario(txtUsuario.getText()); 
      u.setClave(leerClave());    
      u.setFnacim(fechanac.getText()); 
      u.setTipo(Integer.parseInt(txtTipo.getText())); 
      u.setEstado(Integer.parseInt(txtEstado.getText())); 
      
   // grabar el objeto
   		// 1. fabricar el acceso a los datos
   		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
   		// 2. crear el manejador de entidades
   		EntityManager em = fabrica.createEntityManager();
   		
   		// 3. empezar mi transacci�n
   		em.getTransaction().begin();
   		// proceso a realizar (persistencia)
   		em.persist(u); 
   		//em.merge(u); // Es para actualizar datos
   		// 4. confirmar la transacción
   		em.getTransaction().commit();	
   		System.out.println("Registro agregado");
   		em.close();
      
      
   		 
	}

	private String leerClave() {
        
		
		return new String (txtClave.getPassword());
	} 
}
