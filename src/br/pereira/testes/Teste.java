package br.pereira.testes;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;

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
		
		
		Color corBranco = new Color( 255, 255, 255 );
		BufferedImage imgOut = new BufferedImage( w, h, imgIn.getType() );
		Graphics2D gOut = imgOut.createGraphics();
		gOut.setBackground( corBranco );
		gOut.rotate( radiano, w/2, h/2 );
		gOut.drawImage( imgIn, null, 0, 0 );
		return imgOut;
	}

/*
int w = img.getWidth();  
int h = img.getHeight();  
BufferedImage newImage = new BufferedImage(width, height, img.getType());
    Graphics2D g2 = newImage.createGraphics();
    g2.rotate(Math.toRadians(rotation), w/2, h/2);  
    g2.drawImage(img,null,0,0);
return newImage;   */
	
	
/*
 *  public BufferedImage doRotate(BufferedImage input, int angle) {
    int width = input.getWidth();
    int height = input.getHeight();


    double radians = Math.toRadians(angle / 10.0);

    // Rotate about the input image's centre
    AffineTransform rotate = AffineTransform.getRotateInstance(radians, width / 2.0, height / 2.0);

    Shape rect = new Rectangle(width, height);

    // Work out how big the rotated image would be..
    Rectangle bounds = rotate.createTransformedShape(rect).getBounds();

    // Shift the rotated image into the centre of the new bounds
    rotate.preConcatenate(
            AffineTransform.getTranslateInstance((bounds.width - width) / 2.0, (bounds.height - height) / 2.0));

    BufferedImage output = new BufferedImage(bounds.width, bounds.height, input.getType());
    Graphics2D g2d = (Graphics2D) output.getGraphics();

    // Fill the background with white
    g2d.setColor(Color.WHITE);
    g2d.fill(new Rectangle(width, height));

    RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.setRenderingHints(hints);
    g2d.drawImage(input, rotate, null);

    return output;
}
 * 	
 */
	
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

	
	public void clickOnLoad() throws Exception {
		JFileChooser fileChooser = new JFileChooser();
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
		contentPane = new JDesktopPane();
		contentPane.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//contentPane.setLayout(null);
	}
}

