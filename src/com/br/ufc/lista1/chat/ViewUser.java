package com.br.ufc.lista1.chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewUser extends JFrame implements Runnable{

	private JPanel contentPane;
	private JTextField textField;
	private static Cliente cliente;
	private JTextArea textArea;
	private JButton btnEnviar;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {	
			public void run() {
				try {
					ViewUser frame = new ViewUser();
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
	public ViewUser() {	
		
		setTitle("Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.enviar(textField.getText().toString());
				textArea.append("EU: "+ textField.getText() + "\n");
				textField.setText("");

			}
		});
		btnEnviar.setBounds(445, 350, 115, 34);
		contentPane.add(btnEnviar);
		
		textField = new JTextField();
		textField.setBounds(12, 350, 427, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setBounds(12, 12, 545, 313);
		contentPane.add(textArea);
		
		Thread t1 = new Thread(this);
		t1.start();
		
	}

	@Override
	public void run() {
		cliente = new Cliente();
		cliente.execulta();
		String txt;
		while(true) {
			txt = cliente.receber();			
			textArea.append(txt+"\n");
		}
	}
}
