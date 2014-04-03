package br.pereira.testes;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class EscalaCinza extends PixelManager {

	BufferedImage _outImg;
	WritableRaster _outRaster;
	
	public EscalaCinza(BufferedImage i) {
		super(i);
		_outImg = new BufferedImage(_img.getWidth(), _img.getHeight(), _img.getType() );
		_outRaster = _outImg.getRaster();
	}

	public BufferedImage getImage() {
		return _outImg;
	}
	
	public void pixelLoop(int x, int y) {

		int media = (_pix[0]+_pix[1]+_pix[2])/3;
		_pix[ 0 ] = _pix[ 1 ] = _pix[ 2 ] = media;
		_outRaster.setPixel( x, y, _pix );
		
	}

}
