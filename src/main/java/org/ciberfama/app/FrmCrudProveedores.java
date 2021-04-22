package org.ciberfama.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmCrudProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTextField txtIdCategoria;
	private JTextField txtEstado;
	private JTextField txtIdProveedor;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudProducto frame = new FrmCrudProducto();
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
	public FrmCrudProducto() {
		setTitle("Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(26, 27, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción");
		lblNewLabel_1.setBounds(26, 63, 73, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Stock");
		lblNewLabel_2.setBounds(26, 99, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Precio");
		lblNewLabel_3.setBounds(26, 134, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("IdCategoria");
		lblNewLabel_4.setBounds(26, 170, 64, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Estado");
		lblNewLabel_5.setBounds(26, 210, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("IdProveedor");
		lblNewLabel_6.setBounds(26, 245, 64, 14);
		contentPane.add(lblNewLabel_6);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(130, 24, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(130, 60, 86, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(130, 96, 86, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(130, 131, 86, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtIdCategoria = new JTextField();
		txtIdCategoria.setBounds(130, 167, 86, 20);
		contentPane.add(txtIdCategoria);
		txtIdCategoria.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(130, 207, 86, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		txtIdProveedor = new JTextField();
		txtIdProveedor.setBounds(130, 242, 86, 20);
		contentPane.add(txtIdProveedor);
		txtIdProveedor.setColumns(10);
		
		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				Registrar();
			}
		});
		btnRegistrar.setBounds(443, 46, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Actualizar();
			}
		});
		btnActualizar.setBounds(443, 82, 103, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Eliminar();
			}
		});
		btnEliminar.setBounds(443, 189, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Buscar();
			}
		});
		btnBuscar.setBounds(443, 118, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnListar = new JButton("LISTAR");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			textArea.setText(""); 
			Listar();
			
			}
		});
		btnListar.setBounds(443, 153, 89, 23);
		contentPane.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 275, 624, 195);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

	protected void Eliminar() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01"); 
		EntityManager em = fabrica.createEntityManager(); 
		em.getTransaction().begin(); 
		Producto p; 
		p=em.getReference(Producto.class, txtCodigo.getText()); 
		if (p==null) {
			JOptionPane.showMessageDialog(this, "No se encontró producto");
		}else {
			p.getIdprod(); 
			em.remove(p);
			em.getTransaction().commit(); 
			JOptionPane.showMessageDialog(this, "Delete completado");
		}
		
	}

	protected void Listar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01"); 
		EntityManager em = fabrica.createEntityManager(); 
		
		TypedQuery<Producto> consulta = em.createNamedQuery("Producto.FindAll", Producto.class); 
		List<Producto> lstProductos = consulta.getResultList(); 
		em.close(); 
		
		for (Producto p : lstProductos) {
			textArea.append(p.getIdprod() + "\t"+ p.getDescripcion() + "\t" + p.getStock() + "\t" + p.getIdcategoria() + "\t"+p.getEstado()+"\t"+p.getIdproveedor());
			
		}
		
	}

	protected void Buscar() {
		String codigo = txtCodigo.getText(); 
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01"); 
		EntityManager em = fabrica.createEntityManager(); 
		
		Producto p = em.find(Producto.class, codigo); 
		em.close(); 
		
		if(p==null) {
			JOptionPane.showMessageDialog(this,"Producto "+ codigo + " no existe");
		}else {
			txtDescripcion.setText(p.getDescripcion());
			/*txtStock.setText(Integer.parseInt(p.getStock()));
			txtPrecio.setText(Double.parseDouble(p.getPrecio())); 
			txtIdCategoria.setText(Integer.parseInt(p.getIdcategoria()));
		    txtEstado.setText(Integer.parseInt(p.getEstado())); 
		    txtIdProveedor.setText(Integer.parseInt(p.getIdproveedor()));  */
		    
		    
			
		}
		
	}

	protected void Actualizar() {
		
		Producto p = new Producto();  
		p.setDescripcion(txtDescripcion.getText()); 
		p.setStock(Integer.parseInt(txtStock.getText())); 
		p.setPrecio(Double.parseDouble(txtPrecio.getText())); 
		p.setIdcategoria(Integer.parseInt(txtIdCategoria.getText())); 
		p.setEstado(Integer.parseInt(txtEstado.getText())); 
		p.setIdproveedor(Integer.parseInt(txtIdProveedor.getText()));  
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01"); 
		EntityManager em = fabrica.createEntityManager(); 
		em.getTransaction().begin(); 
		em.merge(p); 
		em.getTransaction().commit(); 
		JOptionPane.showMessageDialog(this, "Producto actualizado"); 
		em.close();
		
		
	}

	protected void Registrar() {
		
		Producto p = new Producto(); 
	    p.setIdprod(txtCodigo.getText()); 
	    p.setDescripcion(txtDescripcion.getText());
	    p.setStock(Integer.parseInt(txtStock.getText())); 
	    p.setPrecio(Double.parseDouble(txtPrecio.getText())); 
	    p.setIdcategoria(Integer.parseInt(txtIdCategoria.getText())); 
	    p.setEstado(Integer.parseInt(txtEstado.getText())); 
	    p.setIdproveedor(Integer.parseInt(txtIdProveedor.getText())); 
	    
	    EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");  
	    EntityManager em = fabrica.createEntityManager(); 
	    em.getTransaction().begin(); 
	    em.persist(p);
	    em.getTransaction().commit(); 
	    JOptionPane.showMessageDialog(this, "Producto registrado"); 
	    em.close();
	    
	}
}
