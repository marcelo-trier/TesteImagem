package br.pereira.testes;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VerifiqueRotacao extends PixelManager {

//	BufferedImage _outImg;
//	WritableRaster _outRaster;
	BufferedImage _original;

	int _lateral[]; 
	int _abaixo[];
	
	List<Aposta> maiores = new ArrayList<Aposta>();	

	int _corPreto[] = { 0, 0, 0 };
	
	public VerifiqueRotacao(BufferedImage i) {
		super(i);
		//_outImg = new BufferedImage( _img.getWidth(), _img.getHeight(), _img.getType() );
		//_outRaster = _outImg.getRaster();
		
		_original = i;
	}
	
	/*
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
	*/	

	// pega img base
	// ang rotacao
	// pede para fazer o calculo e adicionar na lista
	
	public void reiniciaVariaveis() {
		_lateral = new int[ _img.getHeight() ];
		_abaixo = new int[ _img.getWidth() ];
		Arrays.fill( _lateral, 0 );
		Arrays.fill( _abaixo,  0 );
	}
	
	public void execute() {
		int[] angulos = { 0, 15, -15, 30, -30, 50, -50 };
		int total = angulos.length;
		for( int i=0; i<3; i++ ) {
			Rotaciona rotate = new Rotaciona( _original );
			rotate.setAngulo( angulos[ i ] );
			rotate.execute();
			_img = rotate.getImage();		

			reiniciaVariaveis();
			super.execute();
			//Arrays.sort( _lateral );
			Arrays.sort( _abaixo );
			Aposta a = new Aposta();
			a.angulo = angulos[ i ];
			a.maior = _abaixo[ _abaixo.length - 1 ];
			a.numeroElementos = getNumeroElementos( _abaixo );
			a._img = _img;
			maiores.add( a );
		}
		Collections.sort( maiores );
//		Aposta ap = maiores.get( maiores.size() -1 );
//		_outImg = ap._img;

	}
	
	public int getNumeroElementos( int[] arr ) {
		int conta = 0;
		for( int i=0; i<arr.length; i++ ) {
			if( arr[ i ] != 0 )
				conta++;
		}
		return conta;
	}
	
	public BufferedImage getImage() {
		Aposta ap = melhorAposta();
		return ap._img;
	}

	public void pixelLoop(int x, int y) {	

		// caso o pixel for qualquer coisa diferente de preto, faz próxima interação
		if( ! Arrays.equals( _pix, _corPreto ) ) 
			return;

		//se for preto, faz os cálculos...
		_lateral[ y ] = _lateral[ y ] + 1;
		_abaixo[ x ] = _abaixo[ x ] + 1;		
	}

	public Aposta melhorAposta() {
		Aposta ap = maiores.get( maiores.size() - 1 );
		return ap;
	}
	
	public String toString() {
		int size = maiores.size();
		int l = _lateral[ _lateral.length - 1 ];
		int a = _abaixo[ _abaixo.length - 1 ];
		String msg = "size = " + size;
		Aposta ap = melhorAposta();
		msg += "\nmaior = " + ap.maior;
		msg += "\n elementos = " + ap.numeroElementos;
		msg += "\n angulo = " + ap.angulo;
		return msg;
	}
	
}
