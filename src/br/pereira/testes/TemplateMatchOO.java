package br.pereira.testes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemplateMatchOO extends PixelManager {

	int corPreto[] = {0, 0, 0};
	
	List<MatchItem> listaMatch = new ArrayList<MatchItem>();
	
	public TemplateMatchOO(BufferedImage i) {
		super(i);
		matrizComp = new int[3][3];
	}

	int[][] matrizComp; // matrix de comparação

	public void getMatriz( int x, int y ) {
		int tempPix[] = new int[ _pix.length ];
		int coordX[][] = { 	{ x-1, x  , x+1 },
							{ x-1, x  , x+1 },
							{ x-1, x  , x+1 } };
		int coordY[][] = {	{ y-1, y-1, y-1 },
							{ y  , y  , y   },
							{ y+1, y+1, y+1 } };

		for( int l=0; l<3; l++ ) {
			for( int c=0; c<3; c++ ) {
				tempPix = _raster.getPixel( coordX[c][l], coordY[c][l], tempPix );
				matrizComp[c][l] = tempPix[0];
			}
		}
	}
	
	public void pixelLoop(int x, int y) {
		// os primeiros pixels não processa
		if( x==0 || y==0 || x==_img.getWidth() || y==_img.getHeight() )
			return; 

		if( ! Arrays.equals( corPreto, _pix ) ) 
			return;
		
		getMatriz( x, y ); // aqui pega a matriz de comparacao
		for( int i=0; i<Caracteristicas.osTemplates.length; i++ )
		{
			int[][] oMatch = Caracteristicas.templates[ i ];
			int linhaOk = 0;
			for( int linha=0; linha<3; linha++ ) {
				if( Arrays.equals( matrizComp[linha], oMatch[linha] ) )
					linhaOk++;
			}
			if( linhaOk == 3 ) { // acertou o match
				MatchItem mi = new MatchItem();
				mi._matchId = i;
				listaMatch.add( mi );
				return;
				// aqui o i atual eh o match certo...
				// tem q guardar o i e mais o q??
			}
		}
	}
	
	public String toString() {
		String msg = "";
		for( int i=0; i<listaMatch.size(); i++ ) {
			MatchItem mi = listaMatch.get( i );
			msg += "\nmatch: " + mi._matchId + " = "+ Caracteristicas.osTemplates[ mi._matchId ];
		}
			
		return msg;
	}

	    
}
