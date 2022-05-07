package br.com.registros.Janela;

import java.io.File;
import java.io.IOException;
import jxl.read.biff.BiffException;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.com.registros.Planilha.Planilha;

import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

public class Janela {

	private JFrame frmFinder;
	private JTable table;
	private JTextField txtNome;
	private Planilha planilha;
	
	private Object[][] dados = new Object[5][5];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela window = new Janela();
					window.frmFinder.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Janela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		planilha = new Planilha();
		try {
		planilha.conecta(new File("trabalhos.xls"));
		}catch(IOException e) {
			
		}catch(BiffException e) {
			
		}
		
		frmFinder = new JFrame();
		frmFinder.setResizable(false);
		frmFinder.setTitle("Finder");
		frmFinder.setBounds(100, 100, 538, 300);
		frmFinder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinder.getContentPane().setLayout(null);

		
		table = new JTable();
		
		////////////////////////////////////////////////
		DefaultTableModel dtm = new DefaultTableModel(
				new Object[][] {
			{"", "", "", "", ""},
		},
				new String[] {
			"Nome", "Servico", "Entrega", "Hora", "Status"
		});
		
		table.setModel(dtm);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		
		////////////////////////////////////////////////
		
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBounds(10, 86, 500, 114);
		
		table.setEnabled(false);
		frmFinder.getContentPane().add(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Pesquisa:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 191, 255)));
		panel.setBounds(10, 11, 500, 58);
		frmFinder.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 26, 73, 14);
		panel.add(lblCliente);
		
		txtNome = new JTextField();
		txtNome.setBounds(53, 23, 299, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnPesquisa = new JButton("Pesquisa");
		btnPesquisa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){

				dados = planilha.localiza(txtNome.getText());
				
				dtm.setDataVector(dados, new String[] {
						"Nome", "Servico", "Entrega", "Hora", "Status"
				});;
				
				table.getColumnModel().getColumn(0).setPreferredWidth(150);
				table.getColumnModel().getColumn(1).setPreferredWidth(100);
				table.getColumnModel().getColumn(2).setPreferredWidth(40);
				table.getColumnModel().getColumn(3).setPreferredWidth(40);
			}
		});
		btnPesquisa.setBounds(362, 22, 115, 23);
		panel.add(btnPesquisa);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setBounds(10, 68, 46, 14);
		frmFinder.getContentPane().add(lblNewLabel);
		
		JLabel lblServico = new JLabel("Servi\u00E7o");
		lblServico.setBounds(179, 68, 46, 14);
		frmFinder.getContentPane().add(lblServico);
		
		JLabel lblEntrega = new JLabel("Entrega");
		lblEntrega.setBounds(299, 68, 46, 14);
		frmFinder.getContentPane().add(lblEntrega);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(358, 68, 30, 14);
		frmFinder.getContentPane().add(lblHora);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(416, 68, 46, 14);
		frmFinder.getContentPane().add(lblStatus);
	}
}
