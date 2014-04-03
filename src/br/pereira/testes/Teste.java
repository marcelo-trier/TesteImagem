package br.pereira.testes;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class Teste extends JFrame {

	private JDesktopPane contentPane;

	public void clickVerifiqueRotacao() {
		VerifiqueRotacao vr = new VerifiqueRotacao( getImage() );
		vr.execute();
		JOptionPane.showMessageDialog( this, vr );
		mostraImagem( vr.getImage() );
	}
	
	public void mostraImagem( BufferedImage imgOut ) {
		TelaInterna interno = new TelaInterna( imgOut );
		contentPane.add( interno );
		interno.setVisible( true );	
	}
	
	public void clickRotate() {
		String aa = JOptionPane.showInputDialog( "Digite angulo" );
		int angulo = Integer.parseInt( aa );

		Rotaciona rotate = new Rotaciona( getImage() );
		rotate.setAngulo( angulo );
		rotate.execute();
		BufferedImage imgOut = rotate.getImage();
		
//		BufferedImage imgOut = rotate( getImage(), angulo );

		mostraImagem( imgOut );
		
	}

	public void clickLimiar() {
		String aa = JOptionPane.showInputDialog( "Digite limiar" );
		int limite = Integer.parseInt( aa );

		Limiarizacao limiar = new Limiarizacao( getImage() );
		limiar.setLimite( limite );
		limiar.execute();
		BufferedImage imgOut = limiar.getImage();
		this.mostraImagem( imgOut );
	}
	
	public void clickGrayScale() {
		EscalaCinza cinza = new EscalaCinza( getImage() );
		cinza.execute();
		mostraImagem( cinza.getImage() );
	}
	
	public BufferedImage getImage() {
		// pega a janela ativa...
		TelaInterna ti = ( TelaInterna )contentPane.getSelectedFrame();
		BufferedImage img;
		img = ti.getImage();
		return img;
	}	
	
	public void clickEscurece() {
		Negativo neg = new Negativo( getImage() );
		neg.execute();
		mostraImagem( neg.getImage() );
	}		

	
	public void calculeBrancos( BufferedImage aImagem ) {
		CalculePontos brancos = new CalculePontos( getImage() );
		brancos.execute();
		JOptionPane.showMessageDialog( this, brancos );
	}
	
	public void testeClick() {
		calculeBrancos( getImage() );
	}
	
	
	public void clickSave() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogType( JFileChooser.SAVE_DIALOG );
		File umDir = new File( System.getProperty( "user.dir" ) );
		fileChooser.setCurrentDirectory( umDir );
		if( fileChooser.showSaveDialog( this ) != JFileChooser.APPROVE_OPTION ) {
			return;
		}
		File salvar = fileChooser.getSelectedFile();
		//ImageIO.w
		BufferedImage img = getImage();
		ImageIO.write( img, "bmp", salvar );
	}

	
	public void clickOnLoad() throws Exception {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogType( JFileChooser.OPEN_DIALOG );
		File umDir = new File( System.getProperty( "user.dir" ) );
		fileChooser.setCurrentDirectory( umDir );
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File file = fileChooser.getSelectedFile();
		BufferedImage imagem = ImageIO.read( file );
		TelaInterna interno = new TelaInterna( imagem );
		contentPane.add( interno );
		interno.setVisible( true );
	}
	
	/**
	 * Create the frame.
	 */
	public Teste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 399);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Load...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickOnLoad();
				} catch( Exception ex ) {
					
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save...");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickSave();
				} catch (Exception ex ) {
					
				}
			}
		});
		mnFile.add(mntmSave);
		
		JMenu mnImagens = new JMenu("Imagens");
		menuBar.add(mnImagens);
		
		JMenuItem mntmTeste = new JMenuItem("Calculo Brco e Preto");
		mntmTeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testeClick();
			}
		});
		mnImagens.add(mntmTeste);
		
		JMenuItem mntmEscurece = new JMenuItem("Escurece");
		mntmEscurece.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickEscurece();
			}
		});
		mnImagens.add(mntmEscurece);
		
		JMenuItem mntmGrayscale = new JMenuItem("GrayScale");
		mntmGrayscale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickGrayScale();
			}
		});
		mnImagens.add(mntmGrayscale);
		
		JMenuItem mntmBinarizao = new JMenuItem("Binarização");
		mntmBinarizao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickLimiar();
			}
		});
		mnImagens.add(mntmBinarizao);
		
		JMenuItem mntmRotate = new JMenuItem("rotate");
		mntmRotate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickRotate();
			}
		});
		mnImagens.add(mntmRotate);
		
		JMenu mnProcessamento = new JMenu("Processamento");
		menuBar.add(mnProcessamento);
		
		JMenuItem mntmVerifiqueRotao = new JMenuItem("Verifique rotação");
		mntmVerifiqueRotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickVerifiqueRotacao();
			}
		});
		mnProcessamento.add(mntmVerifiqueRotao);
		contentPane = new JDesktopPane();
		contentPane.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//contentPane.setLayout(null);
	}
}

