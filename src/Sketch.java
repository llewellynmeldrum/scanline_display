import processing.core.PApplet;

public class Sketch extends PApplet{

        int bigPixels[][];
        int upscaleFactor;
        int pWidth; int pHeight;
        int pSize; int pCount;
        final int WHITE = color(255);
        final int BLACK = color(0);
        public void settings(){ // setup()
		size(500, 500);
                if (this.width!=this.height){
                        System.err.println("Error!!\nSketch only works with square canvas size.");
                        exit();
                }
                upscaleFactor = 25;
                if (width%upscaleFactor!=0){
                        System.err.println("Error!!\nWidth (" + width + ") must be divisible by" + 
                        "Upscale factor (" + upscaleFactor + ")");
                        exit();
                }
                pWidth = width/upscaleFactor;
                pHeight = height/upscaleFactor;
                pSize = upscaleFactor;
                pCount = (pWidth*pHeight);
                bigPixels = new int[width*pSize][height*pSize];

                println("Exiting settings");
	}
	
	public void draw(){     // draw()
                loadPixels();
                for (int i = 0; i<=frameCount%pCount; i++){
                        int x = i%pWidth;
                        int y = i/pWidth;
                        setBigPixel(x,y,color(0));
                }  

                updatePixels();
              
	}

        // place any functions here
        public void setBigPixel(int px, int py, int color){
               // println("[b]Setting "+ px +"," +  py+".");
                int x = min(px, pWidth)*pSize;
                int y = min(py, pHeight)*pSize;
                
                for (int dx = 0; dx<pSize; dx++)
                for (int dy = 0; dy<pSize; dy++){
                        setPixel(x+dx,y+dy,color); 
                }
        }
        
        public void setPixel(int px, int py, int color){
                
                pixels[px + (py*width)] = color;
        }
	public static void main(String[] args){
		String[] processingArgs = {"MySketch"};
		Sketch mySketch = new Sketch();
		PApplet.runSketch(processingArgs, mySketch);
	}
}