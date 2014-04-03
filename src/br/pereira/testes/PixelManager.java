package br.pereira.testes;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public abstract class PixelManager {

	protected BufferedImage _img;
	protected Raster _raster;
	protected int[] _pix;

	public PixelManager( BufferedImage i ) {
		_img = i;
	}

	public void execute() {
		_raster = _img.getRaster();
		int numeroBandas = _raster.getNumBands();
		_pix = new int[ numeroBandas ];
		
		int w = _img.getWidth();
		int h = _img.getHeight();

		for( int x=0; x<w; x++ ) {
			for( int y=0; y<h; y++ ) {
				_pix = _raster.getPixel(x, y, _pix );
				pixelLoop( x, y );
			}
		}
		
	}
	
	public abstract void pixelLoop( int x, int y );
}
