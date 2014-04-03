package br.pereira.testes;

import java.awt.image.BufferedImage;

public class Aposta implements Comparable<Aposta>{
	int maior;
	int angulo;
	int numeroElementos;
	BufferedImage _img;

	public int compareTo(Aposta o) {
		if( this.maior < o.maior )
			return -1;
		if( this.maior > o.maior )
			return 1;
		return 0;
	}
}
