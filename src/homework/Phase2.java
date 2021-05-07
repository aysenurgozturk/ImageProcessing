package homework;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class Phase2 extends JFrame{
	

	private DrawingPanel dp;
    short [][] pixels;
	short [][] pixelsR;
	short [][] pixelsG;
	short [][] pixelsB;
	int[] pixelByte;
	int fileType,width, height;
    private static Color clr[];
	Phase2(){
		
			Scanner inp = new Scanner(System.in);
			System.out.println("Please enter type of image : ");
			int a = inp.nextInt();
			switch (a) {
			case 1 : 
				Type1 t1 = new Type1();
				t1.readFile();
				break;
			case 2 : 
				Type2 t2 = new Type2();
				t2.readFile();
				break;
			case 3 : 
				Type3 t3 = new Type3();
				t3.readFile();
				break;
			case 5 : 
				Type5 t5 = new Type5();
				t5.readFile();
				break;
			case 6 : 
				Type6 t6 = new Type6();
				t6.readFile();
				break;
			default:
				System.out.println("Please enter correct number!");

			}
			
			dp = new DrawingPanel();
			this.setContentPane(dp);		
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(820,600);
			this.setVisible(true);
	}
	class DrawingPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			if (fileType==1) {
				for(int row = 0; row < height; row++)
					for(int col = 0; col < width; col++){
						if(pixels[row][col]==0) {
							g.setColor(new Color(1,1,1));
						}
						else g.setColor(new Color (255,255,255));
						g.fillRect(col, row, 1, 1);
						
					}
			}
			else if (fileType==2) {
				for(int row=0;row<height;row++){
					for(int col=0;col<width;col++){
						g.setColor(new Color(pixels[row][col],pixels[row][col],pixels[row][col]));
					   g.fillRect(col, row, 1, 1);
					}
				}
			}
			else if (fileType==3) {
				for(int row = 0; row < height; row++)
					for(int col = 0; col < width; col++){
					
						g.setColor(new Color(pixelsR[row][col],pixelsG[row][col],pixelsB[row][col]));
						g.fillRect(col, row, 1, 1);
						
					}
			}
			else if (fileType==5) {
					for(int row=0;row<width;row++) {
						for(int col=0;col<height;col++) {
							g.setColor(new Color(pixelByte[col*width+row],pixelByte[col*width+row],pixelByte[col*width+row]));
							g.fillRect(row, col, 1, 1);

						}
					}
			}
			else if (fileType==6) {
				for(int row=0;row<width;row++) {
					for(int col=0;col<height;col++) {
						g.setColor(clr[col*width+row]);
						g.fillRect(row, col, 1, 1);
					}
				}
			}
			else{
				System.out.println("Error!");
			}
		}
	}
	public static void main(String [] args) throws FileNotFoundException , IOException{
		new Phase2();
	}

interface noname{
	void readFile();
}

class Type1 implements noname{
	public void readFile(){
			Scanner inFile;
			try {
				inFile = new Scanner(new File("C://Users/DELL/Desktop/Eclipse Workspace/Homework3/bitText .txt"));
				fileType = inFile.nextInt();
				width = inFile.nextInt();
				height = inFile.nextInt();
				System.out.printf("type: %d, width: %d, height:%d\n", 
					fileType, width, height);
				pixels = new short[3601][2225]; 
			

				for(int row = 0; row < height; row++) {
					for(int col = 0; col < width; col++)
						{
						pixels [row][col] = inFile.nextShort();
						}
					}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
class Type2 implements noname{
	public void readFile(){
			try {
            Scanner inFile=new Scanner(new File("C://Users/DELL/Desktop/Eclipse Workspace/Homework3/baboonascii.txt"));
            fileType=inFile.nextInt();
            width=inFile.nextInt();
             height=inFile.nextInt();
            //System.out.printf("type:%d\n width:%d\n height:%d",fileType,width,height);
            
            
            pixels=new short[512][512];
            
            for(int row=0;row<height;row++){
                for(int col=0;col<width;col++){
                    pixels [row][col]=inFile.nextShort();
                }
            }
            
        } catch (FileNotFoundException ex) {
           
        }
           
	}
}

class Type3 implements noname{
	public void readFile(){
		Scanner inFile;
		try {
			inFile = new Scanner(new File("C://Users/DELL/Desktop/Eclipse Workspace/Homework3/color.advProg"));
			fileType = inFile.nextInt();
			width = inFile.nextInt();
			height = inFile.nextInt();
			System.out.printf("type: %d, width: %d, height:%d\n", 
				fileType, width, height);
			pixelsR = new short[200][800];
			pixelsG = new short[200][800];
			pixelsB = new short[200][800];
			for(int row = 0; row < height; row++) {
				for(int col = 0; col < width; col++)
					{
					pixelsR [row][col] = inFile.nextShort();
					pixelsG [row][col] = inFile.nextShort();
					pixelsB [row][col] = inFile.nextShort();
					}
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}
class Type5 implements noname{
	public void readFile(){
			try {
			FileInputStream fis = new FileInputStream(new File("type5.txt"));
            fileType = fis.read();
            fileType=fileType-48;
			System.out.println(fileType);
            int aCharacter = fis.read();
            for (int i = 0; i < 3;i++){
                String readValue = "";
                while(Character.isWhitespace(aCharacter)) {
                    aCharacter = fis.read();
                }
                while(!Character.isWhitespace(aCharacter)) {
                    readValue = readValue + (aCharacter-'0');
                    aCharacter = fis.read();
                }
                if(i==0) {
                    width = Integer.parseInt(readValue);
                }
                else if(i==1){
                    height = Integer.parseInt(readValue);
                }
            }

            pixelByte = new int[width*height];
            int b = fis.read();
            for(int row=0;row<height;row++) {
                for(int col=0;col<width;col++) {
                    pixelByte[row*width+col]=fis.read();
        
                }
            }
        } 
		catch (IOException e) {
            e.printStackTrace();
        }       
	}
}
class Type6 implements noname{
	public void readFile(){
		
		try {
			FileInputStream fis = new FileInputStream(new File("type6.txt"));
            fileType = fis.read();
            fileType=fileType-48;
			System.out.println(fileType);
            int aCharacter = fis.read();
            for (int i = 0; i < 3;i++){

                String readValue = "";
                while(Character.isWhitespace(aCharacter)) {
                    aCharacter = fis.read();
                }
                while(!Character.isWhitespace(aCharacter)) {
                    readValue = readValue + (aCharacter-'0');
                    aCharacter = fis.read();
                }
                if(i==0) {
                    width = Integer.parseInt(readValue);
                }
                else if(i==1){
                    height = Integer.parseInt(readValue);
                }
            }
            System.out.println("W:"+width+" H:"+height);
            pixelByte = new int[width*height*3];
           
            clr = new Color[width * height];
            for(int row=0;row<width;row++) {
                for(int col=0;col<height;col++) {
                    int r = fis.read();
                    int g = fis.read();
                    int b = fis.read();
                    clr[row*height+col]= new Color(r, g, b);
                  
                }
            }        
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
}