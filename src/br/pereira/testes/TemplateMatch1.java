package br.pereira.testes;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.*;

public class TemplateMatch1 {
	
	public static void main( String[] args ){
						
	       // Cria vetores de características	       
	       int [][] crt1 = { 
	    		   { 255, 255, 255 }, 
	    		   { 255, 000, 000 }, 
	    		   { 255, 000, 255 }, 
	       };
	       
	       int [][] crt2 = { 
	    		   { 255, 255, 255 }, 
	    		   { 255, 000, 255 }, 
	    		   { 255, 000, 000 }, 
	       };
	       
	       int [][] crt3 = { 
	    		   { 255, 255, 255 }, 
	    		   { 000, 000, 255 }, 
	    		   { 255, 000, 255 }, 
	       };
	       
	       int [][] crt4 = { 
	    		   { 255, 255, 255 }, 
	    		   { 255, 000, 255 }, 
	    		   { 000, 000, 255 }, 
	       };
	       
	       int [][] crt5 = { 
	    		   { 255, 000, 255 }, 
	    		   { 255, 000, 000 }, 
	    		   { 255, 000, 255 }, 
	       };
	       
	       int [][] crt6 = { 
	    		   { 255, 000, 255 }, 
	    		   { 000, 000, 255 }, 
	    		   { 255, 000, 255 }, 
	       };
	       
	       int [][] crt7 = { 
	    		   { 255, 000, 000}, 
	    		   { 255, 255, 255 }, 
	    		   { 255, 255, 255 }, 
	       };
	       
	       int [][] crt8 = { 
	    		   { 000, 000, 255 }, 
	    		   { 255, 255, 255 }, 
	    		   { 255, 255, 255 }, 
	       };
	       
	       int tem_crt1 = 0;
	       int tem_crt2 = 0;
	       int tem_crt3 = 0;
	       int tem_crt4 = 0;
	       int tem_crt5 = 0;
	       int tem_crt6 = 0;
	       int tem_crt7 = 0;
	       int tem_crt8 = 0;
	       // Inicia a Leitura do número
	        BufferedImage numeroLido = null;
	        try {
                
	            File imagefileN3 = new File("C:/Users/ecacarva/Documents/GitHub/TesteImagem/numeros-bmp/num-8.bmp");
	            numeroLido = ImageIO.read(imagefileN3);
	            // Confirma se leu os numeros
	            //System.out.println("Success para número");
	            //Mostra informações
	            //System.out.println("Elemento Lido: "+numeroLido);
	            // System.out.println("Matriz de largura "+numeroLido.getWidth() + " e altura "+numeroLido.getHeight());
	            
	            Raster  rIn = numeroLido.getRaster();
	    		int pixel[] = new int[rIn.getNumBands()];

	    		for(int h=0; h < numeroLido.getHeight(); h++) {
	    			for(int w=0; w < numeroLido.getWidth(); w++) {
	    				pixel = rIn.getPixel(w,h,pixel);
 				    				
	    				// caso o pixel <> de preto, faz proxima interacao
	    				if(pixel[0] != 0 || pixel[1] != 0 || pixel[2] != 0 )
	    					continue;

	    				//se for preto, compara com crt1
 	
		            	// Comparando com crt1///////////////////////////////////////////
	    		        //======================================================== Linha 0	
	    		        // Linha 0 Coluna 0
	    				pixel = rIn.getPixel( w-1, h-1, pixel );
	    				if( crt1[0][0] != pixel[0] ) {
	    					tem_crt1=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt1=1;

	    				// Linha 0 Coluna 1
	    				pixel = rIn.getPixel( w, h-1, pixel );
	    				if( crt1[0][1] != pixel[0] ) {
	    					tem_crt1=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt1=1;
	    				
	    				// Linha 0 Coluna 2
	    				pixel = rIn.getPixel( w+1, h-1, pixel );
	    				if( crt1[0][2] != pixel[0] ) {
	    					tem_crt1=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt1=1;
	    				
	    				//======================================================== Linha 1
	    				// Linha 1 Coluna 0
	    				pixel = rIn.getPixel( w-1, h, pixel );
	    				if( crt1[1][0] != pixel[0] ) {
	    					tem_crt1=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt1=1;
	    				
	    				// Linha 1 Coluna 1
	    				pixel = rIn.getPixel( w, h, pixel );
	    				if( crt1[1][1] != pixel[0] ) {
	    					tem_crt1=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt1=1;
	    				
	    				// Linha 1 Coluna 2
	    				pixel = rIn.getPixel( w+1, h, pixel );
	    				if( crt1[1][2] != pixel[0] ) {
	    					tem_crt1=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt1=1;
	    					
	    				//======================================================== Linha 2
	    				// Linha 2 Coluna 0	    				
	    				pixel = rIn.getPixel( w-1, h+1, pixel );
	    				if( crt1[2][0] != pixel[0] ) {
	    					tem_crt1=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt1=1;
	    					
	    				// Linha 2 Coluna 1
	    				pixel = rIn.getPixel( w, h+1, pixel );
	    				if( crt1[2][1] != pixel[0] ) {
	    					tem_crt1=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt1=1;
	    					
	    				// Linha 2 Coluna 2
	    				pixel = rIn.getPixel( w+1, h+1, pixel );
	    				if( crt1[2][2] != pixel[0] ) {
	    					tem_crt1=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt1=1;
	    			}
	    		}
	    		
	     		for(int h=0; h < numeroLido.getHeight(); h++) {
	    			for(int w=0; w < numeroLido.getWidth(); w++) {
	    				pixel = rIn.getPixel(w,h,pixel);
 				    				
	    				// caso o pixel <> de preto, faz proxima interacao
	    				if(pixel[0] != 0 || pixel[1] != 0 || pixel[2] != 0 )
	    					continue;

	    				//se for preto, compara com crt2
	    					
	    				// Comparando com crt2///////////////////////////////////////////
	    		        //======================================================== Linha 0	
	    		        // Linha 0 Coluna 0
	    				pixel = rIn.getPixel( w-1, h-1, pixel );
	    				if( crt2[0][0] != pixel[0] ) {
	    					tem_crt2=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt2=1;

	    				// Linha 0 Coluna 1
	    				pixel = rIn.getPixel( w, h-1, pixel );
	    				if( crt2[0][1] != pixel[0] ) {
	    					tem_crt2=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt2=1;
	    				
	    				// Linha 0 Coluna 2
	    				pixel = rIn.getPixel( w+1, h-1, pixel );
	    				if( crt2[0][2] != pixel[0] ) {
	    					tem_crt2=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt2=1;
	    				//======================================================== Linha 1
	    				// Linha 1 Coluna 0
	    				pixel = rIn.getPixel( w-1, h, pixel );
	    				if( crt2[1][0] != pixel[0] ) {
	    					tem_crt2=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt2=1;
	    				
	    				// Linha 1 Coluna 1
	    				pixel = rIn.getPixel( w, h, pixel );
	    				if( crt2[1][1] != pixel[0] ) {
	    					tem_crt2=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt2=1;
	    				
	    				// Linha 1 Coluna 2
	    				pixel = rIn.getPixel( w+1, h, pixel );
	    				if( crt2[1][2] != pixel[0] ) {
	    					tem_crt2=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt2=1;
	    					
	    				//======================================================== Linha 2
	    				// Linha 2 Coluna 0	    				
	    				pixel = rIn.getPixel( w-1, h+1, pixel );
	    				if( crt2[2][0] != pixel[0] ) {
	    					tem_crt2=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt2=1;
	    					
	    				// Linha 2 Coluna 1
	    				pixel = rIn.getPixel( w, h+1, pixel );
	    				if( crt2[2][1] != pixel[0] ) {
	    					tem_crt2=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt2=1;
	    					
	    				// Linha 2 Coluna 2
	    				pixel = rIn.getPixel( w+1, h+1, pixel );
	    				if( crt2[2][2] != pixel[0] ) {
	    					tem_crt2=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt2=1;
	    			}
	     		}
	     		
	     		for(int h=0; h < numeroLido.getHeight(); h++) {
	    			for(int w=0; w < numeroLido.getWidth(); w++) {
	    				pixel = rIn.getPixel(w,h,pixel);
 				    				
	    				// caso o pixel <> de preto, faz proxima interacao
	    				if(pixel[0] != 0 || pixel[1] != 0 || pixel[2] != 0 )
	    					continue;

	    				//se for preto, compara com crt1
	    				
	    				// Comparando com crt3///////////////////////////////////////////
	    		        //======================================================== Linha 0	
	    		        // Linha 0 Coluna 0
	    				pixel = rIn.getPixel( w-1, h-1, pixel );
	    				if( crt3[0][0] != pixel[0] ) {
	    					tem_crt3=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt3=1;

	    				// Linha 0 Coluna 1
	    				pixel = rIn.getPixel( w, h-1, pixel );
	    				if( crt3[0][1] != pixel[0] ) {
	    					tem_crt3=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt3=1;
	    				
	    				// Linha 0 Coluna 2
	    				pixel = rIn.getPixel( w+1, h-1, pixel );
	    				if( crt3[0][2] != pixel[0] ) {
	    					tem_crt3=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt3=1;
	    				//======================================================== Linha 1
	    				// Linha 1 Coluna 0
	    				pixel = rIn.getPixel( w-1, h, pixel );
	    				if( crt3[1][0] != pixel[0] ) {
	    					tem_crt3=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt3=1;
	    				
	    				// Linha 1 Coluna 1
	    				pixel = rIn.getPixel( w, h, pixel );
	    				if( crt3[1][1] != pixel[0] ) {
	    					tem_crt3=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt3=1;
	    				
	    				// Linha 1 Coluna 2
	    				pixel = rIn.getPixel( w+1, h, pixel );
	    				if( crt3[1][2] != pixel[0] ) {
	    					tem_crt3=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt3=1;
	    					
	    				//======================================================== Linha 2
	    				// Linha 2 Coluna 0	    				
	    				pixel = rIn.getPixel( w-1, h+1, pixel );
	    				if( crt3[2][0] != pixel[0] ) {
	    					tem_crt3=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt3=1;
	    					
	    				// Linha 2 Coluna 1
	    				pixel = rIn.getPixel( w, h+1, pixel );
	    				if( crt3[2][1] != pixel[0] ) {
	    					tem_crt3=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt3=1;
	    					
	    				// Linha 2 Coluna 2
	    				pixel = rIn.getPixel( w+1, h+1, pixel );
	    				if( crt3[2][2] != pixel[0] ) {
	    					tem_crt3=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt3=1;
	    			}
	     		}
	     		
	     		for(int h=0; h < numeroLido.getHeight(); h++) {
	    			for(int w=0; w < numeroLido.getWidth(); w++) {
	    				pixel = rIn.getPixel(w,h,pixel);
 				    				
	    				// caso o pixel <> de preto, faz proxima interacao
	    				if(pixel[0] != 0 || pixel[1] != 0 || pixel[2] != 0 )
	    					continue;

	    				//se for preto, compara com crt4
	    				
	    				// Comparando com crt4///////////////////////////////////////////
	    		        //======================================================== Linha 0	
	    		        // Linha 0 Coluna 0
	    				pixel = rIn.getPixel( w-1, h-1, pixel );
	    				if( crt4[0][0] != pixel[0] ) {
	    					tem_crt4=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt4=1;

	    				// Linha 0 Coluna 1
	    				pixel = rIn.getPixel( w, h-1, pixel );
	    				if( crt4[0][1] != pixel[0] ) {
	    					tem_crt4=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt4=1;
	    				
	    				// Linha 0 Coluna 2
	    				pixel = rIn.getPixel( w+1, h-1, pixel );
	    				if( crt4[0][2] != pixel[0] ) {
	    					tem_crt4=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt4=1;
	    					
	    				//======================================================== Linha 1
	    				// Linha 1 Coluna 0
	    				pixel = rIn.getPixel( w-1, h, pixel );
	    				if( crt4[1][0] != pixel[0] ) {
	    					tem_crt4=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt4=1;
	    				
	    				// Linha 1 Coluna 1
	    				pixel = rIn.getPixel( w, h, pixel );
	    				if( crt4[1][1] != pixel[0] ) {
	    					tem_crt4=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt4=1;
	    				
	    				// Linha 1 Coluna 2
	    				pixel = rIn.getPixel( w+1, h, pixel );
	    				if( crt4[1][2] != pixel[0] ) {
	    					tem_crt4=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt4=1;
	    					
	    				//======================================================== Linha 2
	    				// Linha 2 Coluna 0	    				
	    				pixel = rIn.getPixel( w-1, h+1, pixel );
	    				if( crt4[2][0] != pixel[0] ) {
	    					tem_crt4=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt4=1;
	    					
	    				// Linha 2 Coluna 1
	    				pixel = rIn.getPixel( w, h+1, pixel );
	    				if( crt4[2][1] != pixel[0] ) {
	    					tem_crt4=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt4=1;
	    					
	    				// Linha 2 Coluna 2
	    				pixel = rIn.getPixel( w+1, h+1, pixel );
	    				if( crt4[2][2] != pixel[0] ) {
	    					tem_crt4=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt4=1;
	    			}
	     		}
	     		
	     		for(int h=0; h < numeroLido.getHeight(); h++) {
	    			for(int w=0; w < numeroLido.getWidth(); w++) {
	    				pixel = rIn.getPixel(w,h,pixel);
 				    				
	    				// caso o pixel <> de preto, faz proxima interacao
	    				if(pixel[0] != 0 || pixel[1] != 0 || pixel[2] != 0 )
	    					continue;

	    				//se for preto, compara com crt5
	    				
	    				// Comparando com crt5///////////////////////////////////////////
	    		        //======================================================== Linha 0	
	    		        // Linha 0 Coluna 0
	    				pixel = rIn.getPixel( w-1, h-1, pixel );
	    				if( crt5[0][0] != pixel[0] ) {
	    					tem_crt5=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt5=1;

	    				// Linha 0 Coluna 1
	    				pixel = rIn.getPixel( w, h-1, pixel );
	    				if( crt5[0][1] != pixel[0] ) {
	    					tem_crt5=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt5=1;
	    				
	    				// Linha 0 Coluna 2
	    				pixel = rIn.getPixel( w+1, h-1, pixel );
	    				if( crt5[0][2] != pixel[0] ) {
	    					tem_crt5=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt5=1;
	    					
	    				//======================================================== Linha 1
	    				// Linha 1 Coluna 0
	    				pixel = rIn.getPixel( w-1, h, pixel );
	    				if( crt5[1][0] != pixel[0] ) {
	    					tem_crt5=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt5=1;
	    				
	    				// Linha 1 Coluna 1
	    				pixel = rIn.getPixel( w, h, pixel );
	    				if( crt5[1][1] != pixel[0] ) {
	    					tem_crt5=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt5=1;
	    				
	    				// Linha 1 Coluna 2
	    				pixel = rIn.getPixel( w+1, h, pixel );
	    				if( crt5[1][2] != pixel[0] ) {
	    					tem_crt5=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt5=1;
	    					
	    				//======================================================== Linha 2
	    				// Linha 2 Coluna 0	    				
	    				pixel = rIn.getPixel( w-1, h+1, pixel );
	    				if( crt5[2][0] != pixel[0] ) {
	    					tem_crt5=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt5=1;
	    					
	    				// Linha 2 Coluna 1
	    				pixel = rIn.getPixel( w, h+1, pixel );
	    				if( crt5[2][1] != pixel[0] ) {
	    					tem_crt5=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt5=1;
	    					
	    				// Linha 2 Coluna 2
	    				pixel = rIn.getPixel( w+1, h+1, pixel );
	    				if( crt5[2][2] != pixel[0] ) {
	    					tem_crt5=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt5=1;
	    			}
	     		}
	     		
	     		for(int h=0; h < numeroLido.getHeight(); h++) {
	    			for(int w=0; w < numeroLido.getWidth(); w++) {
	    				pixel = rIn.getPixel(w,h,pixel);
 				    				
	    				// caso o pixel <> de preto, faz proxima interacao
	    				if(pixel[0] != 0 || pixel[1] != 0 || pixel[2] != 0 )
	    					continue;

	    				//se for preto, compara com crt6
	    				
	    				// Comparando com crt6///////////////////////////////////////////
	    		        //======================================================== Linha 0	
	    		        // Linha 0 Coluna 0
	    				pixel = rIn.getPixel( w-1, h-1, pixel );
	    				if( crt6[0][0] != pixel[0] ) {
	    					tem_crt6=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt6=1;

	    				// Linha 0 Coluna 1
	    				pixel = rIn.getPixel( w, h-1, pixel );
	    				if( crt6[0][1] != pixel[0] ) {
	    					tem_crt6=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt6=1;
	    				
	    				// Linha 0 Coluna 2
	    				pixel = rIn.getPixel( w+1, h-1, pixel );
	    				if( crt6[0][2] != pixel[0] ) {
	    					tem_crt6=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt6=1;
	    					
	    				//======================================================== Linha 1
	    				// Linha 1 Coluna 0
	    				pixel = rIn.getPixel( w-1, h, pixel );
	    				if( crt6[1][0] != pixel[0] ) {
	    					tem_crt6=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt6=1;
	    				
	    				// Linha 1 Coluna 1
	    				pixel = rIn.getPixel( w, h, pixel );
	    				if( crt6[1][1] != pixel[0] ) {
	    					tem_crt6=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt6=1;
	    				
	    				// Linha 1 Coluna 2
	    				pixel = rIn.getPixel( w+1, h, pixel );
	    				if( crt6[1][2] != pixel[0] ) {
	    					tem_crt6=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt6=1;
	    					
	    				//======================================================== Linha 2
	    				// Linha 2 Coluna 0	    				
	    				pixel = rIn.getPixel( w-1, h+1, pixel );
	    				if( crt6[2][0] != pixel[0] ) {
	    					tem_crt6=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt6=1;
	    					
	    				// Linha 2 Coluna 1
	    				pixel = rIn.getPixel( w, h+1, pixel );
	    				if( crt6[2][1] != pixel[0] ) {
	    					tem_crt6=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt6=1;
	    					
	    				// Linha 2 Coluna 2
	    				pixel = rIn.getPixel( w+1, h+1, pixel );
	    				if( crt6[2][2] != pixel[0] ) {
	    					tem_crt6=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt6=1;
	    			}
	     		}
	     		
	     		for(int h=0; h < numeroLido.getHeight(); h++) {
	    			for(int w=0; w < numeroLido.getWidth(); w++) {
	    				pixel = rIn.getPixel(w,h,pixel);
 				    				
	    				// caso o pixel <> de preto, faz proxima interacao
	    				if(pixel[0] != 0 || pixel[1] != 0 || pixel[2] != 0 )
	    					continue;

	    				//se for preto, compara com crt7

	    				// Comparando com crt7///////////////////////////////////////////
	    		        //======================================================== Linha 0	
	    		        // Linha 0 Coluna 0
	    				pixel = rIn.getPixel( w-1, h-1, pixel );
	    				if( crt7[0][0] != pixel[0] ) {
	    					tem_crt7=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt7=1;

	    				// Linha 0 Coluna 1
	    				pixel = rIn.getPixel( w, h-1, pixel );
	    				if( crt7[0][1] != pixel[0] ) {
	    					tem_crt7=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt7=1;
	    				
	    				// Linha 0 Coluna 2
	    				pixel = rIn.getPixel( w+1, h-1, pixel );
	    				if( crt7[0][2] != pixel[0] ) {
	    					tem_crt7=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt7=1;
	    					
	    				//======================================================== Linha 1
	    				// Linha 1 Coluna 0
	    				pixel = rIn.getPixel( w-1, h, pixel );
	    				if( crt7[1][0] != pixel[0] ) {
	    					tem_crt7=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt7=1;
	    				
	    				// Linha 1 Coluna 1
	    				pixel = rIn.getPixel( w, h, pixel );
	    				if( crt7[1][1] != pixel[0] ) {
	    					tem_crt7=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt7=1;
	    				
	    				// Linha 1 Coluna 2
	    				pixel = rIn.getPixel( w+1, h, pixel );
	    				if( crt7[1][2] != pixel[0] ) {
	    					tem_crt7=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt7=1;
	    					
	    				//======================================================== Linha 2
	    				// Linha 2 Coluna 0	    				
	    				pixel = rIn.getPixel( w-1, h+1, pixel );
	    				if( crt7[2][0] != pixel[0] ) {
	    					tem_crt7=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt7=1;
	    					
	    				// Linha 2 Coluna 1
	    				pixel = rIn.getPixel( w, h+1, pixel );
	    				if( crt7[2][1] != pixel[0] ) {
	    					tem_crt7=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt7=1;
	    					
	    				// Linha 2 Coluna 2
	    				pixel = rIn.getPixel( w+1, h+1, pixel );
	    				if( crt7[2][2] != pixel[0] ) {
	    					tem_crt7=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt7=1;
	    			}
	     		}
	     		
	     		for(int h=0; h < numeroLido.getHeight(); h++) {
	    			for(int w=0; w < numeroLido.getWidth(); w++) {
	    				pixel = rIn.getPixel(w,h,pixel);
 				    				
	    				// caso o pixel <> de preto, faz proxima interacao
	    				if(pixel[0] != 0 || pixel[1] != 0 || pixel[2] != 0 )
	    					continue;

	    				//se for preto, compara com crt8

	    				// Comparando com crt8///////////////////////////////////////////
	    		        //======================================================== Linha 0	
	    		        // Linha 0 Coluna 0
	    				pixel = rIn.getPixel( w-1, h-1, pixel );
	    				if( crt8[0][0] != pixel[0] ) {
	    					tem_crt8=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt8=1;

	    				// Linha 0 Coluna 1
	    				pixel = rIn.getPixel( w, h-1, pixel );
	    				if( crt8[0][1] != pixel[0] ) {
	    					tem_crt8=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt8=1;
	    				
	    				// Linha 0 Coluna 2
	    				pixel = rIn.getPixel( w+1, h-1, pixel );
	    				if( crt8[0][2] != pixel[0] ) {
	    					tem_crt8=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt8=1;
	    					
	    				//======================================================== Linha 1
	    				// Linha 1 Coluna 0
	    				pixel = rIn.getPixel( w-1, h, pixel );
	    				if( crt8[1][0] != pixel[0] ) {
	    					tem_crt8=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt8=1;
	    				
	    				// Linha 1 Coluna 1
	    				pixel = rIn.getPixel( w, h, pixel );
	    				if( crt8[1][1] != pixel[0] ) {
	    					tem_crt8=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt8=1;
	    				
	    				// Linha 1 Coluna 2
	    				pixel = rIn.getPixel( w+1, h, pixel );
	    				if( crt8[1][2] != pixel[0] ) {
	    					tem_crt8=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt8=1;
	    					
	    				//======================================================== Linha 2
	    				// Linha 2 Coluna 0	    				
	    				pixel = rIn.getPixel( w-1, h+1, pixel );
	    				if( crt8[2][0] != pixel[0] ) {
	    					tem_crt8=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt8=1;
	    					
	    				// Linha 2 Coluna 1
	    				pixel = rIn.getPixel( w, h+1, pixel );
	    				if( crt8[2][1] != pixel[0] ) {
	    					tem_crt8=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt8=1;
	    					
	    				// Linha 2 Coluna 2
	    				pixel = rIn.getPixel( w+1, h+1, pixel );
	    				if( crt8[2][2] != pixel[0] ) {
	    					tem_crt8=0;
	    					continue;
	    				}
	    				else;
	    					tem_crt8=1;		
	    			}    			
	    		} 
	    		
	    		System.out.println("CRT1:"+tem_crt1);
				System.out.println("CRT2:"+tem_crt2);
				System.out.println("CRT3:"+tem_crt3);
				System.out.println("CRT4:"+tem_crt4);
				System.out.println("CRT5:"+tem_crt5);
				System.out.println("CRT6:"+tem_crt6);
				System.out.println("CRT7:"+tem_crt7);
				System.out.println("CRT8:"+tem_crt8);
				
				
	                
	        } catch (IOException e) {
	        	  System.out.println("Not Success");
	              e.printStackTrace();
	        }
	    }
}
