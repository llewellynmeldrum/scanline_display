import processing.core.PApplet;

public class Sketch extends PApplet{

        BigPixelArray screen;
        final int WHITE = color(255);
        final int BLACK = color(0);
        int ScanLineProgress;
        float ScanLineSpeed; // 1 = 1big pixel per frame
        boolean isScanDirectionHorizontal = true;
        
        public void settings(){ // setup()
		size(500, 500);
                
                if (this.width!=this.height){
                        System.err.println("Error!!\nSketch only works with square canvas size.");
                        exit();
                }
                int upscaleFactor = 25;
                if (width%upscaleFactor!=0){
                        System.err.println("Error!!\nWidth (" + width + ") must be divisible by" + 
                        "Upscale factor (" + upscaleFactor + ")");
                        exit();
                }
                int pWidth = width/upscaleFactor;
                int pHeight = height/upscaleFactor;
                ScanLineProgress = 0;
                ScanLineSpeed = 2f;
                screen = new BigPixelArray(this, pWidth, pHeight, upscaleFactor);
                //bigPixels = new int[width*pSize][height*pSize];

                println("Exiting settings");
	}
	
	public void draw(){     // draw()
                println(ScanLineProgress);
                loadPixels();
                boolean isBlack = false;
                for (int x = 0; x<screen.getpWidth(); x++){
                        for (int y = 0; y<screen.getpHeight(); y++){
                                int color = color(255);
                                if (isBlack)
                                        color = color(0);
                                isBlack=!isBlack;
                                screen.setBigPixel(x,y,color);
                        }
                isBlack=!isBlack;
                }
               
               
                
                screen.drawBigPixels(ScanLineProgress);
                if (frameCount==1){
                        background(255);
                }
                noFill();
                //circle(width/2,height/2,400);
                advanceScanLine();
                
                // ScanLineProgress %=screen.getPixelCount();
                
	}
        public void resetScanLine(){
                screen.clearBigPixels();
                ScanLineProgress = 0;
                background(255);
                
        }
        public void advanceScanLine(){
                int roundedScanLineSpeed = round(ScanLineSpeed);
                if (ScanLineSpeed < 1){
                        int framesPerScanAdvance = round(1/ScanLineSpeed);
                        if (frameCount%framesPerScanAdvance==0){
                                ScanLineProgress++;
                        }
                } else {
                ScanLineProgress+=roundedScanLineSpeed;
                }
                if (ScanLineProgress>=screen.getPixelCount()){
                        resetScanLine();
                } 

        }
        public void keyPressed(){
                if (key==' '){
                        resetScanLine();
                        isScanDirectionHorizontal=!isScanDirectionHorizontal; 
                }
        }
        
	public static void main(String[] args){
		String[] processingArgs = {"MySketch"};
		Sketch mySketch = new Sketch();
		PApplet.runSketch(processingArgs, mySketch);
	}
}