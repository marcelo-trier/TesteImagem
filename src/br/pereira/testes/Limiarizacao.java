package br.pereira.testes;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Limiarizacao extends PixelManager {
	int _limite = 0;
	BufferedImage _outImg;
	WritableRaster _outRaster;
	
	public Limiarizacao(BufferedImage i) {
		super(i);
		_outImg = new BufferedImage( _img.getWidth(), _img.getHeight(), _img.getType() );
		_outRaster = _outImg.getRaster();
	}

	public void setLimite( int l ) {
		_limite = l;
	}
	
	public BufferedImage getImage() {
		return _outImg;
	}

	public void pixelLoop(int x, int y) {

		int media = ( _pix[0]+ _pix[1]+ _pix[2])/3;
		int cor = 0;
		if( media > _limite ) {
			cor = 0xFF;
		}
		_pix[0] = _pix[1] = _pix[2] = cor;
			
		_outRaster.setPixel( x, y, _pix );
		
	}

}
