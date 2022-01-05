package com.produits.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	enum Mode {
		normal, add, update
	}

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
	Mode choix = Mode.normal;

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
		setResizable(false);
		setTitle("Facture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 293);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Feuille Produit");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(24, 11, 483, 39);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Code Produit :");
		lblNewLabel_1.setBorder(new LineBorder(Color.ORANGE, 2, true));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(24, 73, 136, 23);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Nom Produit :");
		lblNewLabel_1_1.setBorder(new LineBorder(Color.ORANGE, 2, true));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(24, 107, 136, 23);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Prix Achat :");
		lblNewLabel_1_2.setBorder(new LineBorder(Color.ORANGE, 2, true));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(24, 142, 136, 23);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Prix Vente :");
		lblNewLabel_1_3.setBorder(new LineBorder(Color.ORANGE, 2, true));
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(24, 176, 136, 23);
		contentPane.add(lblNewLabel_1_3);

		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setBounds(163, 73, 96, 20);
		contentPane.add(txtCode);
		txtCode.setColumns(10);

		txtNom = new JTextField();
		txtNom.setEditable(false);
		txtNom.setColumns(10);
		txtNom.setBounds(163, 110, 189, 20);
		contentPane.add(txtNom);

		txtPrixAchat = new JTextField();
		txtPrixAchat.setEditable(false);
		txtPrixAchat.setColumns(10);
		txtPrixAchat.setBounds(163, 145, 96, 20);
		contentPane.add(txtPrixAchat);

		txtPrixVente = new JTextField();
		txtPrixVente.setEditable(false);
		txtPrixVente.setColumns(10);
		txtPrixVente.setBounds(163, 179, 96, 20);
		contentPane.add(txtPrixVente);

		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (choix) {
				case normal:
					activeDesactiveControls(true);
					txtNom.setText("");
					txtPrixAchat.setText("");
					txtPrixVente.setText("");
					txtNom.requestFocus();
					btnAjouter.setText("Enregistrer");
					btnModifier.setText("Annuler");
					choix = Mode.add;
					break;
				case add:
					Produit p = new Produit();
					p.setNom(txtNom.getText());
					p.setPrixAchat(Double.parseDouble(txtPrixAchat.getText()));
					p.setPrixVente(Double.parseDouble(txtPrixVente.getText()));

					if (metier.save(p))
						JOptionPane.showMessageDialog(null, "Produit ajoute avec succes");
					else
						JOptionPane.showMessageDialog(null, "Echec d'ajout de Produit");

					produits = metier.getAll();
					position = produits.size() - 1;
					afficheProduit();
					choix = Mode.normal;
					btnAjouter.setText("Ajouter");
					btnModifier.setText("Modifier");
					txtNom.setEditable(false);
					txtPrixAchat.setEditable(false);
					txtPrixVente.setEditable(false);
					btnSupprimer.setEnabled(true);
					btnFermer.setEnabled(true);
					break;
				case update:
					p = new Produit();
					p.setId(Integer.parseInt(txtCode.getText()));
					p.setNom(txtNom.getText());
					p.setPrixAchat(Double.parseDouble(txtPrixAchat.getText()));
					p.setPrixVente(Double.parseDouble(txtPrixVente.getText()));

					if (metier.update(p))
						JOptionPane.showMessageDialog(null, "Produit modifie avec succes");
					else
						JOptionPane.showMessageDialog(null, "Echec de modification de Produit");

					produits = metier.getAll();
					afficheProduit();
					choix = Mode.normal;
					btnAjouter.setText("Ajouter");
					btnModifier.setText("Modifier");
					txtNom.setEditable(false);
					txtPrixAchat.setEditable(false);
					txtPrixVente.setEditable(false);
					btnSupprimer.setEnabled(true);
					btnFermer.setEnabled(true);

					break;
				}
			}
		});
		btnAjouter.setName("ajouter");
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAjouter.setBounds(377, 73, 128, 32);
		contentPane.add(btnAjouter);

		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (choix == Mode.normal) {
					activeDesactiveControls(true);
					txtNom.requestFocus();
					btnAjouter.setText("Enregistrer");
					btnModifier.setText("Annuler");
					choix = Mode.update;
				} else {
					produits = metier.getAll();
					afficheProduit();
					choix = Mode.normal;
					btnAjouter.setText("Ajouter");
					btnModifier.setText("Modifier");
					txtNom.setEditable(false);
					txtPrixAchat.setEditable(false);
					txtPrixVente.setEditable(false);
					btnSupprimer.setEnabled(true);
					btnFermer.setEnabled(true);
				}
			}
		});
		btnModifier.setName("modifier");
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModifier.setBounds(377, 116, 128, 32);
		contentPane.add(btnModifier);

		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int ch = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce produit ?",
						"Suppression produit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (ch == JOptionPane.YES_OPTION) {
					Produit p = new Produit();
					p.setId(Integer.parseInt(txtCode.getText()));
					p.setNom(txtNom.getText());
					p.setPrixAchat(Double.parseDouble(txtPrixAchat.getText()));
					p.setPrixVente(Double.parseDouble(txtPrixVente.getText()));

					if (metier.delete(p))
						JOptionPane.showMessageDialog(null, "Produit supprime avec succes");
					else
						JOptionPane.showMessageDialog(null, "Echec de suppression de Produit");

					produits = metier.getAll();
					position = 0;
					afficheProduit();
				}
			}
		});
		btnSupprimer.setName("supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSupprimer.setBounds(377, 159, 128, 32);
		contentPane.add(btnSupprimer);

		btnFermer = new JButton("Fermer");
		btnFermer.setName("fermer");
		btnFermer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFermer.setBounds(377, 202, 128, 32);
		btnFermer.addActionListener((e) -> {
			this.dispose();
		});
		contentPane.add(btnFermer);

		btnFirst = new JButton("<<");
		btnFirst.setName("first");
		btnFirst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFirst.setBounds(10, 210, 70, 32);
		contentPane.add(btnFirst);

		btnPrevious = new JButton("<");
		btnPrevious.setName("previous");
		btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPrevious.setBounds(90, 210, 70, 32);
		contentPane.add(btnPrevious);

		btnNext = new JButton(">");
		btnNext.setName("next");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNext.setBounds(172, 210, 70, 32);
		contentPane.add(btnNext);

		btnLast = new JButton(">>");
		btnLast.setName("last");
		btnLast.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLast.setBounds(252, 210, 70, 32);
		contentPane.add(btnLast);

		produits = metier.getAll();
		if (produits.size() == 0) {
			btnLast.setEnabled(false);
			btnNext.setEnabled(false);
			btnFirst.setEnabled(false);
			btnPrevious.setEnabled(false);
		} else {
			position = 0;
			afficheProduit();
		}

		ActionListener e = (event) -> {
			JButton b = (JButton) event.getSource();

			switch (b.getText()) {
			case "<":
				position--;
				break;
			case "<<":
				position = 0;
				break;
			case ">":
				position++;
				break;
			case ">>":
				position = produits.size() - 1;
				break;
			}

			afficheProduit();
		};

		btnFirst.addActionListener(e);
		btnLast.addActionListener(e);
		btnNext.addActionListener(e);
		btnPrevious.addActionListener(e);
	}

	void afficheProduit() {
		if (position == 0) {
			btnFirst.setEnabled(false);
			btnPrevious.setEnabled(false);
		} else {
			btnFirst.setEnabled(true);
			btnPrevious.setEnabled(true);
		}

		if (position == produits.size() - 1) {
			btnLast.setEnabled(false);
			btnNext.setEnabled(false);
		} else {
			btnLast.setEnabled(true);
			btnNext.setEnabled(true);
		}
		txtCode.setText(String.valueOf(produits.get(position).getId()));
		txtNom.setText(produits.get(position).getNom());
		txtPrixAchat.setText(String.valueOf(produits.get(position).getPrixAchat()));
		txtPrixVente.setText(String.valueOf(produits.get(position).getPrixVente()));
	}

	public void activeDesactiveControls(boolean etat) {
		txtNom.setEditable(etat);
		txtPrixAchat.setEditable(etat);
		txtPrixVente.setEditable(etat);
		btnFirst.setEnabled(!etat);
		btnLast.setEnabled(!etat);
		btnPrevious.setEnabled(!etat);
		btnNext.setEnabled(!etat);
		btnSupprimer.setEnabled(!etat);
		btnFermer.setEnabled(!etat);
	}

}
