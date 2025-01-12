import processing.core.PApplet;

public class BigPixelArray {
        PApplet s;
        private int bigPixels[][]; 
        private int pCount;
        private int pHeight;
        private int pSize;

        public BigPixelArray(PApplet sketch, int pWidth, int pHeight, int pSize){
                bigPixels = new int[pWidth][pHeight];
                this.s = sketch;
                this.pWidth = pWidth; 
                this.pHeight = pHeight;
                this.pSize = pSize;
                this.pCount = (pWidth*pHeight);
        }
        public void clearBigPixels(){
                bigPixels = new int[pWidth][pHeight];
        }
        public void setBigPixel(int px, int py, int color){
                bigPixels[px][py] = color;
        }
        void drawBigPixels(int ScanLineProgress){
                for (int i = 1; i<=ScanLineProgress; i++){
                        int x = i%getpWidth();
                        int y = i/getpWidth();
                        drawBigPixel(x,y,bigPixels[x][y]);
                }
                // for (int px = 0; px<pWidth;  px++)
                // for (int py = 0; py<pHeight; py++){
                //         
                // }
                s.updatePixels();
        }
        public void drawBigPixel(int px, int py, int color){
                // println("[b]Setting "+ px +"," +  py+".");
                 int x = Math.min(px, pWidth)*pSize;
                 int y = Math.min(py, pHeight)*pSize;
                 
                 for (int dx = 0; dx<pSize; dx++)
                 for (int dy = 0; dy<pSize; dy++){
                        setPixel(x+dx,y+dy,color); 
                }
        }
        public void setPixel(int px, int py, int color){
                s.pixels[px + (py*s.width)] = color;
        }


        //------- getters -------
        public int getpSize() {
                return pSize;
        }
        private int pWidth; public int getpWidth() {
                return pWidth;
        }
        public int getpHeight() {
                return pHeight;
        }
        public int getPixelCount() {
                return pCount;
        }
}
