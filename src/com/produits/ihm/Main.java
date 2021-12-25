package com.produits.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.produits.metier.IMetier;
import com.produits.metier.MetierProduit;
import com.produits.models.Produit;

import antlr.debug.Event;

import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtNom;
	private JTextField txtPrixAchat;
	private JTextField txtPrixVente;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnFermer;
	private JButton btnFirst;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;
	
	IMetier<Produit> metier = new MetierProduit();
	List<Produit> produits;
	int position;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Facture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Feuille Produit");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(99, 11, 396, 39);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Code Produit :");
		lblNewLabel_1.setBorder(new LineBorder(Color.YELLOW));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(59, 91, 136, 23);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Nom Produit :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(59, 125, 136, 23);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Prix Achat :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(59, 160, 136, 23);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Prix Vente :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(59, 194, 136, 23);
		contentPane.add(lblNewLabel_1_3);

		txtCode = new JTextField();
		txtCode.setBounds(216, 91, 96, 20);
		contentPane.add(txtCode);
		txtCode.setColumns(10);

		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(216, 128, 189, 20);
		contentPane.add(txtNom);

		txtPrixAchat = new JTextField();
		txtPrixAchat.setColumns(10);
		txtPrixAchat.setBounds(216, 163, 96, 20);
		contentPane.add(txtPrixAchat);

		txtPrixVente = new JTextField();
		txtPrixVente.setColumns(10);
		txtPrixVente.setBounds(216, 197, 96, 20);
		contentPane.add(txtPrixVente);

		btnAjouter = new JButton("Ajouter");
		btnAjouter.setName("ajouter");
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAjouter.setBounds(439, 88, 128, 32);
		contentPane.add(btnAjouter);

		btnModifier = new JButton("Modifier");
		btnModifier.setName("modifier");
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModifier.setBounds(439, 128, 128, 32);
		contentPane.add(btnModifier);

		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setName("supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSupprimer.setBounds(439, 170, 128, 32);
		contentPane.add(btnSupprimer);

		btnFermer = new JButton("Fermer");
		btnFermer.setName("fermer");
		btnFermer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFermer.setBounds(439, 213, 128, 32);
		contentPane.add(btnFermer);

		btnFirst = new JButton("<<");
		btnFirst.setName("first");
		btnFirst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFirst.setBounds(66, 271, 70, 32);
		contentPane.add(btnFirst);

		btnPrevious = new JButton("<");
		btnPrevious.setName("previous");
		btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPrevious.setBounds(146, 271, 70, 32);
		contentPane.add(btnPrevious);

		btnNext = new JButton(">");
		btnNext.setName("next");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNext.setBounds(228, 271, 70, 32);
		contentPane.add(btnNext);

		btnLast = new JButton(">>");
		btnLast.setName("last");
		btnLast.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLast.setBounds(317, 271, 70, 32);
		contentPane.add(btnLast);
		
		
		produits = metier.getAll();
		if(produits.size()==0) {
			btnLast.setEnabled(false);
			btnNext.setEnabled(false);
			btnFirst.setEnabled(false);
			btnPrevious.setEnabled(false);			
		}else {
			position = 0;
			afficheProduit();
		}
		
		ActionListener e = (event) -> {
			JButton b = (JButton)event.getSource();
			
			switch (b.getText()) {
				case "<": position--; break;
				case "<<": position=0; break;
				case ">": position++; break;
				case ">>": position=produits.size()-1; break;
			}
			
			afficheProduit();
		};
		
		btnFirst.addActionListener(e);
		btnLast.addActionListener(e);
		btnNext.addActionListener(e);
		btnPrevious.addActionListener(e);
	}

	void afficheProduit() {
		if(position==0) {
			btnFirst.setEnabled(false);
			btnPrevious.setEnabled(false);
		}else {
			btnFirst.setEnabled(true);
			btnPrevious.setEnabled(true);
		}
		
		if(position==produits.size()-1) {
			btnLast.setEnabled(false);
			btnNext.setEnabled(false);
		}else {
			btnLast.setEnabled(true);
			btnNext.setEnabled(true);
		}
		txtCode.setText(String.valueOf(produits.get(position).getId()));
		txtNom.setText(produits.get(position).getNom());
		txtPrixAchat.setText(String.valueOf(produits.get(position).getPrixAchat()));
		txtPrixVente.setText(String.valueOf(produits.get(position).getPrixVente()));
	}
	
	
}
