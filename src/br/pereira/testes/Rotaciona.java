package br.pereira.testes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Rotaciona extends PixelManager {

	int _angulo = 0;
	BufferedImage _outImg;
	
	public Rotaciona(BufferedImage i) {
		super(i);
	}

	public void pixelLoop(int x, int y) {	}

	public void setAngulo( int a ) {
		_angulo = a;
	}
	
	public void execute() {
		int w = _img.getWidth();
		int h = _img.getHeight();
		double radiano = Math.toRadians( _angulo );
	
		// Rotate about the input image's centre
	    AffineTransform transforma = AffineTransform.getRotateInstance( radiano, w/2, h/2 );
	    Shape retang = new Rectangle( w, h );

	    // Work out how big the rotated image would be..
	    Rectangle redimens = transforma.createTransformedShape( retang ).getBounds();
	    
	    int novoW = redimens.width;
	    int novoH = redimens.height;

	    AffineTransform reposiciona = AffineTransform.getTranslateInstance( (novoW-w)/2, (novoH-h)/2 );
	    transforma.preConcatenate( reposiciona );
	    
		_outImg = new BufferedImage( novoW, novoH, _img.getType() );
		Graphics2D gOut = _outImg.createGraphics();
		gOut.setColor( Color.WHITE );
		gOut.fillRect( 0, 0, novoW, novoH );
		gOut.drawImage( _img,transforma, null );
	}
	
	public BufferedImage getImage() {
		return _outImg;
	}
	
}
