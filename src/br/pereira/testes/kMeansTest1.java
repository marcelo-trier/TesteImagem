package br.pereira.testes;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class kMeansTest1 {
	
	public static void main( String[] args ){
		
	       // Inicia a Leitura da paisagem
	        BufferedImage paisagem1 = null;
	        try {
             
	            File imagefilePaisagem = new File("D:/Users/Master/Documents/GitHub/TesteImagem/numeros-bmp/Paisagem.bmp");
	            paisagem1 = ImageIO.read(imagefilePaisagem);
	            // Confirma se leu
	            //System.out.println("Success para imagem");
	            //System.out.println("Matriz de largura "+paisagem1.getWidth() + " e altura "+paisagem1.getHeight());  
	            //System.out.println("Tamanho da Matriz = "+paisagem1.getWidth() * paisagem1.getHeight());

    			// Passo 1 - Dado n objetos, inicialize k centroides
	            // Definir centróides randomicamente
	            int k=4;
	            int centroideW[] 		= new int [k];
	            int centroideH[] 		= new int [k];
	            double centroideSoma [] = new double [k];

   	            Raster  rIn = paisagem1.getRaster();
	    		int pixel[] = new int[rIn.getNumBands()];

	            Random gerador = new Random(); 
	            for (int i = 0; i < k; i++) {
	            	// Gera posição aleatória para largura e altura            		
	            	centroideW[i]=gerador.nextInt(paisagem1.getWidth());
	            	centroideH[i]=gerador.nextInt(paisagem1.getHeight());
	            	//Pega valores do pixel para essa largura e altura elevados ao quadrado e soma
	            	pixel = rIn.getPixel(centroideW[i],centroideH[i],pixel);						            	
	            	// Ignora valores se pixel = branco
		    		if(pixel[0] == 255 || pixel[1] == 255 || pixel[2] == 255 ) {
		    			i--;
		    			continue;
		    		}
		    		
		    		centroideSoma[i] = (Math.pow(pixel[0],2) + Math.pow(pixel[1],2) + Math.pow(pixel[2],2));
		    		
		    		System.out.println(" Centroide "+i+" Largura-> "+centroideW[i]+" Altura--> "+centroideH[i] +
		    				" = Pixel [0] = "+pixel[0]+" Pixel [1] = "+pixel[1]+" Pixel [2] = "+pixel[2] +
		    				" Soma do centroide = "+centroideSoma[i]
		    				); 		              				
	            	}
	            
	            // Passo 2 - Agrupe cada objeto para seu centróide mais próximo	            
	            double pixelSoma 		= 0;
	            double diferenca[] 		= new double [k];
	            double diferencaAtual 	= 0;
				
				int altura 				= paisagem1.getHeight();
				int matriz [][] 		= new int [altura][6];
				
				double somaCentroideZero 	= 0;
				double somaCentroideHum 	= 0;
				double somaCentroideDois 	= 0;
				double somaCentroideTres 	= 0;
				
				int contaCentroideZero 	= 0;
				int contaCentroideHum 	= 0;
				int contaCentroideDois 	= 0;
				int contaCentroideTres 	= 0;
				
	            //for(int h=0; h < paisagem1.getHeight(); h++) {
	            for(int h=0; h < 1; h++) {
	    			//for(int w=0; w < paisagem1.getWidth(); w++) {
	    			for(int w=0; w < 5; w++) {
	    				// se a coordenada for igual dos centróides ignora
	    				if ((w == centroideW[0] && h == centroideH[0]) || (w == centroideW[1] && h == centroideH[1]) ||
	    					(w == centroideW[2] && h == centroideH[2]) || (w == centroideW[3] && h == centroideH[3])) continue;		
	    				
	    				pixel = rIn.getPixel(w,h,pixel);
	    				// Soma os valores de RGB ao quadrado
	    				pixelSoma = (Math.pow(pixel[0],2) + Math.pow(pixel[1],2) + Math.pow(pixel[2],2));	   
	    				
	    				System.out.println("Soma Pixel = "+pixelSoma);
	    				
	    				// Verifica qual o centroide mais próximo
	    				int centroideCerto 		= 99;	    				
	    				for (int i = 0; i < k; i++) {
	    					diferenca[i] = Math.abs(centroideSoma[i] - pixelSoma);
	    					System.out.println("Diferença entre pixel e centroide "+i+" = "+diferenca[i]);
	    					
	    					// Se for a primeira vez já atribui valor de i e guarda a diferença
	    					if (centroideCerto == 99) {
	    						centroideCerto = i;
	    						diferencaAtual = diferenca[i];
	    					}
	    					// Se a diferença atualfor menor do que a anterior, entao substitui a anterior pela atual
	    					else if (diferenca[i] < diferencaAtual) {
	    						centroideCerto = i;
	    						diferencaAtual = diferenca[i];
	    					}		    		
	    				}
	    				System.out.println("W = "+w+" H = "+h+" Pixel[0] "+pixel[0]+" Pixel[1] "+pixel[1]+" Pixel[2] "+pixel[2]+" Centroide = " + centroideCerto);
	    				// Armazenar pixels em um array com informação do centróide
	    				// w, h, pixel[0], pixel[1],pixel[2],centroide
	    				matriz[w][0]= w;
	    				matriz[w][1]= h;
	    				// De acordo com o centróide determina a cor de cada pixel
	    				// 0 = blue (0 0 255), 1 = aquamarine (127 255 212), 2= Brown (165 42 42), Beige  (245 245 220)   				
	    				if (centroideCerto ==0) {
	    					//pixel[0]
	    					matriz[w][2]= 0;
	    					//pixel[1]
		    				matriz[w][3]= 0;
		    				//pixel[2] 
		    				matriz[w][4]= 255;
		    				// Soma elementos do centróide para mais tarde recalcular os centróides
		    				somaCentroideZero = (Math.pow(matriz[w][2],2) + Math.pow(matriz[w][3],2) + Math.pow(matriz[w][4],2));
		    				// Conta quantos elementos tem neste centróide para usar no recálculo do centróide
		    				contaCentroideZero ++;
	    				}
	    				else if (centroideCerto ==1) {
	    					//pixel[0]
	    					matriz[w][2]= 127;
	    					//pixel[1]
		    				matriz[w][3]= 255;
		    				//pixel[2] 
		    				matriz[w][4]= 212;
		    				// Soma elementos do centróide para mais tarde recalcular os centróides
		    				somaCentroideHum = (Math.pow(matriz[w][2],2) + Math.pow(matriz[w][3],2) + Math.pow(matriz[w][4],2));;
		    				// Conta quantos elementos tem neste centróide para usar no recálculo do centróide
		    				contaCentroideHum ++;
	    				}
	    				else if (centroideCerto ==2) {
	    					//pixel[0]
	    					matriz[w][2]= 165;
	    					//pixel[1]
		    				matriz[w][3]= 42;
		    				//pixel[2] 
		    				matriz[w][4]= 42;
		    				// Soma elementos do centróide para mais tarde recalcular os centróides
		    				somaCentroideDois = (Math.pow(matriz[w][2],2) + Math.pow(matriz[w][3],2) + Math.pow(matriz[w][4],2));
		    				// Conta quantos elementos tem neste centróide para usar no recálculo do centróide
		    				contaCentroideDois ++;
	    				}
	    				else if (centroideCerto ==3) {
	    					//pixel[0]
	    					matriz[w][2]= 245;
	    					//pixel[1]
		    				matriz[w][3]= 245;
		    				//pixel[2] 
		    				matriz[w][4]= 220;
		    				// Soma elementos do centróide para mais tarde recalcular os centróides
		    				somaCentroideTres = (Math.pow(matriz[w][2],2) + Math.pow(matriz[w][3],2) + Math.pow(matriz[w][4],2));
		    				// Conta quantos elementos tem neste centróide para usar no recálculo do centróide
		    				contaCentroideTres ++;
	    				}
	    				// Indica qual centróide este pixel pertence
	    				matriz[w][5]= centroideCerto;	    				
	    			}
	            }     
	            // 3. Atualize o centróide de cada agrupamento
	            double novoCentroideZero = (somaCentroideZero / contaCentroideZero);
	            double novoCentroideHum  = (somaCentroideHum  / contaCentroideHum);
	            double novoCentroideDois = (somaCentroideDois / contaCentroideDois);
	            double novoCentroideTres = (somaCentroideTres / contaCentroideTres);
	            
				System.out.println("novoCentroideZero "+novoCentroideZero);
				System.out.println("novoCentroideHum "+novoCentroideHum);
				System.out.println("novoCentroideDois "+novoCentroideDois);
				System.out.println("novoCentroideTres "+novoCentroideTres);

		            
	            // 4. Repita o passo 2 e 3 até que nenhuma mudança ocorra

	        } catch (IOException e) {
	        	  System.out.println("Not Success");
	              e.printStackTrace();
	        }
	    }
	
	
	
}
