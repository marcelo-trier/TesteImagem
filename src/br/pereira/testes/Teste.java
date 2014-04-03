package br.pereira.testes;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teste frame = new Teste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void clickVerifiqueRotacao() {
		BufferedImage imgIn = getImage();
		
		int lateral[] = new int[imgIn.getHeight()];
		int abaixo[] = new int[imgIn.getWidth()];
		Arrays.fill( lateral, 0 );
		Arrays.fill( abaixo,  0 );
		
		Raster  rIn = imgIn.getRaster();
		int pixel[] = new int[ rIn.getNumBands() ];

		for( int h=0; h<imgIn.getHeight(); h++ ) {
			for( int w=0; w<imgIn.getWidth(); w++ ) {
				pixel = rIn.getPixel( w, h, pixel );

				// caso o pixel for qualquer coisa diferente de preto, faz próxima interação
				if( pixel[0] != 0 || pixel[1] != 0 || pixel[2] != 0 )
					continue;

				//se for preto, faz os cálculos...
				lateral[ h ] = lateral[ h ] + 1;
				abaixo[ w ] = abaixo[ w ] + 1;
			}
		}
		int aa;
		aa = 0;
		aa++;
	}
	
	public void clickRotate() {
		String aa = JOptionPane.showInputDialog( "Digite angulo" );
		int angulo = Integer.parseInt( aa );

		BufferedImage imgOut = rotate( this.getImage(), angulo );

		TelaInterna interno = new TelaInterna( imgOut );
		contentPane.add( interno );
		interno.setVisible( true );	
	}

	public BufferedImage rotate( BufferedImage imgIn, int ang ) {
		int w = imgIn.getWidth();
		int h = imgIn.getHeight();
		double radiano = Math.toRadians( ang );
	
		// Rotate about the input image's centre
	    AffineTransform transforma = AffineTransform.getRotateInstance( radiano, w/2, h/2 );
	    Shape retang = new Rectangle( w, h );

	    // Work out how big the rotated image would be..
	    Rectangle redimens = transforma.createTransformedShape( retang ).getBounds();
	    
	    int novoW = redimens.width;
	    int novoH = redimens.height;

	    AffineTransform reposiciona = AffineTransform.getTranslateInstance( (novoW-w)/2, (novoH-h)/2 );
	    transforma.preConcatenate( reposiciona );
	    
	    
		BufferedImage imgOut = new BufferedImage( novoW, novoH, imgIn.getType() );
		Graphics2D gOut = imgOut.createGraphics();
		gOut.setColor( Color.WHITE );
		gOut.fillRect( 0, 0, novoW, novoH );
	    //gOut.rotate( radiano, novoW/2, novoH/2 );
	    //gOut.drawImage( imgIn, null, 0, 0 );
		gOut.drawImage( imgIn,transforma, null );

		return imgOut;
	}


	public void processaPixels(  ) {
		BufferedImage imgIn = getImage();
		BufferedImage imgOut = new BufferedImage(imgIn.getWidth(), imgIn.getHeight(), imgIn.getType() );
		Raster  rIn = imgIn.getRaster();
		WritableRaster rOut = imgOut.getRaster();
		
		int pixel[] = new int[ rIn.getNumBands() ];

		for( int h=0; h<imgIn.getHeight(); h++ ) {
			for( int w=0; w<imgIn.getWidth(); w++ ) {
//				pxm.execute();
/*
				pixel = rIn.getPixel( w, h, pixel );
				int media = (pixel[0]+pixel[1]+pixel[2])/3;
				int cor = 0;
				if( media > limite ) {
					cor = 0xFF;
				}
				pixel[0] = pixel[1] = pixel[2] = cor;
					
				rOut.setPixel( w, h, pixel ); */
			}
		}
		TelaInterna interno = new TelaInterna( imgOut );
		contentPane.add( interno );
		interno.setVisible( true );		
	}
	
	public void clickLimiar() {
		String aa = JOptionPane.showInputDialog( "Digite limiar" );
		int limite = Integer.parseInt( aa );

		BufferedImage imgIn = getImage();
		//BufferedImage imgOut = new BufferedImage(imgIn.getWidth(), imgIn.getHeight(), BufferedImage.TYPE_INT_RGB );
		BufferedImage imgOut = new BufferedImage(imgIn.getWidth(), imgIn.getHeight(), imgIn.getType() );
		Raster  rIn = imgIn.getRaster();
		WritableRaster rOut = imgOut.getRaster();
		
		int pixel[] = new int[ rIn.getNumBands() ];

		for( int h=0; h<imgIn.getHeight(); h++ ) {
			for( int w=0; w<imgIn.getWidth(); w++ ) {

				pixel = rIn.getPixel( w, h, pixel );
				int media = (pixel[0]+pixel[1]+pixel[2])/3;
				int cor = 0;
				if( media > limite ) {
					cor = 0xFF;
				}
				pixel[0] = pixel[1] = pixel[2] = cor;
					
				rOut.setPixel( w, h, pixel );
			}
		}
		TelaInterna interno = new TelaInterna( imgOut );
		contentPane.add( interno );
		interno.setVisible( true );		
	}
	
	
	public void clickGrayScale() {
		BufferedImage imgIn = getImage();
		//BufferedImage imgOut = new BufferedImage(imgIn.getWidth(), imgIn.getHeight(), BufferedImage.TYPE_INT_RGB );
		BufferedImage imgOut = new BufferedImage(imgIn.getWidth(), imgIn.getHeight(), imgIn.getType() );
		Raster  rIn = imgIn.getRaster();
		WritableRaster rOut = imgOut.getRaster();
		
		int pixel[] = new int[ rIn.getNumBands() ];

		for( int h=0; h<imgIn.getHeight(); h++ ) {
			for( int w=0; w<imgIn.getWidth(); w++ ) {

				pixel = rIn.getPixel( w, h, pixel );
				int media = (pixel[0]+pixel[1]+pixel[2])/3;
				pixel[0] = media;
				pixel[1] = media;
				pixel[2] = media;
				rOut.setPixel( w, h, pixel );
			}
		}
		TelaInterna interno = new TelaInterna( imgOut );
		contentPane.add( interno );
		interno.setVisible( true );		
	}
	
	public BufferedImage getImage() {
		// pega a janela ativa...
		TelaInterna ti = ( TelaInterna )contentPane.getSelectedFrame();
		BufferedImage img;
		img = ti.getImage();
		return img;
	}	
	
	public void clickEscurece() {

		BufferedImage imgIn = getImage();
		//BufferedImage imgOut = new BufferedImage(imgIn.getWidth(), imgIn.getHeight(), BufferedImage.TYPE_INT_RGB );
		BufferedImage imgOut = new BufferedImage(imgIn.getWidth(), imgIn.getHeight(), imgIn.getType() );
		Raster  rIn = imgIn.getRaster();
		WritableRaster rOut = imgOut.getRaster();
		
		int pixel[] = new int[ rIn.getNumBands() ];

		for( int h=0; h<imgIn.getHeight(); h++ ) {
			for( int w=0; w<imgIn.getWidth(); w++ ) {

				pixel = rIn.getPixel( w, h, pixel );
				pixel[0] = 0xFF - pixel[0];
				pixel[1] = 0xFF - pixel[1];
				pixel[2] = 0xFF - pixel[2];
				rOut.setPixel( w, h, pixel );
			}
		}
		TelaInterna interno = new TelaInterna( imgOut );
		contentPane.add( interno );
		interno.setVisible( true );		
	}		

	/*
		 *     BufferedImage output = new BufferedImage(bounds.width, bounds.height, input.getType());
    Graphics2D g2d = (Graphics2D) output.getGraphics();

    // Fill the background with white
    g2d.setColor(Color.WHITE);
    g2d.fill(new Rectangle(width, height));

		 */
	
	public void calculeBrancos( BufferedImage aImagem ) {
		Raster raster = aImagem.getRaster();
		int numeroBandas = raster.getNumBands();
		int pix[] = new int[ numeroBandas ];
		
		int w = aImagem.getWidth();
		int h = aImagem.getHeight();
		short r = 0, g=0, b=0;

		int brancos=0, pretos=0, outros=0;
		for( int x=0; x<w; x++ ) {
			for( int y=0; y<h; y++ ) {
				pix = raster.getPixel(x, y, pix );
				if( pix[ 0 ]==255 && pix[ 1 ]==255 && pix[ 2 ]==255 ) {
					brancos++;
				}
				else if( pix[ 0 ]==0 && pix[1]==0 && pix[2]==0 ) {
					pretos++;
				}
				else
					outros++;
			}
		}
		int tot = brancos + pretos + outros;
		String msg = "tot = " + tot;
		msg += "\nbrancos = " + brancos;
		msg += "\npretos = " + pretos;
		msg += "\noutros = " + outros;
		JOptionPane.showMessageDialog( this, msg );
	}
	
	public void testeClick() {
		calculeBrancos( getImage() );
	}
	
	public void clickOnLoad3333() throws Exception {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File file = fileChooser.getSelectedFile();
		BufferedImage aImagem = ImageIO.read( file );
		//Raster raster = aImagem.getData();
		Raster raster = aImagem.getRaster();
		int numeroBandas = raster.getNumBands();
		int pix[] = new int[ numeroBandas ];
		
		int w = aImagem.getWidth();
		int h = aImagem.getHeight();
		short r = 0, g=0, b=0;
		long aa = 0;

		
		for( int x=0; x<w; x++ ) {
			for( int y=0; y<h; y++ ) {
				pix = raster.getPixel(x, y, pix );
				if( pix[ 0 ] != 0xFF )
					aa++;
			}
			aa++;
		}
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

