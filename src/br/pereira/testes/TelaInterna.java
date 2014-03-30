package br.pereira.testes;

import javax.imageio.ImageIO;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TelaInterna extends JInternalFrame {
	static int contadorJanela = 0;
	public int id = 0;
	private ImagePanel panel;
	public BufferedImage imagem = null;
	
	
	public TelaInterna(BufferedImage img ) {
		super( "Janela: " + contadorJanela, true, true, true, true );
		id = contadorJanela;
		contadorJanela++;
		setBounds(100, 100, 430, 260);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		imagem = img;
		panel = new ImagePanel( img );
		getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	public BufferedImage getImage() {
		return imagem;
	}
	
}
