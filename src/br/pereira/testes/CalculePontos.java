package br.pereira.testes;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.Arrays;

public class CalculePontos extends PixelManager {

	int _brancos=0, _pretos=0, _outros=0;
	int _corBranco[] = { 255, 255, 255 };
	int _corPreto[] = { 0, 0, 0 };
	
	public CalculePontos(BufferedImage i) {
		super(i);
	}

	public String toString() {
		int tot = _brancos + _pretos + _outros;
		String msg = "tot = " + tot;
		msg += "\nbrancos = " + _brancos;
		msg += "\npretos = " + _pretos;
		msg += "\noutros = " + _outros;		

		return msg;
	}

	public void pixelLoop(int x, int y) {
		if( Arrays.equals( _pix, _corBranco ) ) {
			_brancos++;
			return;
		}

		if( Arrays.equals( _pix, _corPreto ) ) {
			_pretos++;
			return;
		}
		
		_outros++;
	}

	
}
