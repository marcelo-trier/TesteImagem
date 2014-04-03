package br.pereira.testes;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Negativo extends PixelManager {

	BufferedImage _outImg;
	WritableRaster _outRaster;
	
	public Negativo(BufferedImage i) {
		super(i);
		_outImg = new BufferedImage( _img.getWidth(), _img.getHeight(), _img.getType() );
		_outRaster = _outImg.getRaster();
	}

	public BufferedImage getImage() {
		return _outImg;
	}
	
	public void pixelLoop(int x, int y) {
		_pix[0] = 0xFF - _pix[0];
		_pix[1] = 0xFF - _pix[1];
		_pix[2] = 0xFF - _pix[2];
		_outRaster.setPixel( x, y, _pix );
	}	
}
