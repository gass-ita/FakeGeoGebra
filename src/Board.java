import javax.swing.JPanel;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;

class Board extends JPanel implements MouseWheelListener, MouseMotionListener {
    /* -------------------------------------------------------------------------- */
    /*                               DEFAULT VALUES                               */
    /* -------------------------------------------------------------------------- */
    private final int DEFAULT_XSCALE = 20;
    private final int DEFAULT_YSCALE = 20;
    private final Color BACKGROUND_COLOR = Color.BLACK;
    private final Color AXIS_COLOR = Color.WHITE;
    private final Color GRID_COLOR = Color.GRAY;
    private final Color FUNCTION_COLOR = Color.RED;
    private final int DEFAULT_MIN_SCALE = 1;
    private final int DEFAULT_MAX_SCALE = 100;
    private final int DEFAULT_SCALE_STEP = 1;
    
    

    /* -------------------------------------------------------------------------- */
    /*                                PUBLIC VALUES                               */
    /* -------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------- */
    /*                               PRIVATE VALUES                               */
    /* -------------------------------------------------------------------------- */
    private Color backgroundColor;
    private Color axisColor;
    private Color gridColor;
    private Color functionColor;
    private int Xscale;
    private int Yscale;
    private int minScale;
    private int maxScale;
    private int scaleStep;
    private int centerX;
    private int centerY;
    

    

    
    
    

    
    

    public Board(int x, int y, int w, int h){
        super();
        setBounds(x, y, w, h);
        Xscale = DEFAULT_XSCALE;
        Yscale = DEFAULT_YSCALE;; 
        backgroundColor = BACKGROUND_COLOR;
        axisColor = AXIS_COLOR;
        gridColor = GRID_COLOR;
        functionColor = FUNCTION_COLOR;
        minScale = DEFAULT_MIN_SCALE;
        maxScale = DEFAULT_MAX_SCALE;
        scaleStep = DEFAULT_SCALE_STEP;
        centerX = w/2;
        centerY = h/2;
        addMouseWheelListener(this);
        addMouseMotionListener(this);
    }

    

    @Override
    public void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        super.paint(g);
        drawBackground(g, w, h);
        drawGrid(g, w, h);
        drawAxis(g, w, h);
        drawFunction(g, w, h);
        
        

        
    }
    
    private void drawBackground(Graphics g, int w, int h){
        Color c = g.getColor();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, w, h);
        g.setColor(c);
    }
    
    private void drawAxis(Graphics g, int w, int h){
        Color c = g.getColor();
        g.setColor(axisColor);
        g.drawLine(centerX, 0, centerX, h);
        g.drawLine(0, centerY, w, centerY);
        g.setColor(c);
    }

    private void drawGrid(Graphics g, int w, int h){
        Color c = g.getColor();
        g.setColor(gridColor);
        for(int i = centerX; i < w; i += Xscale){
            g.drawLine(i, 0, i, h);
        }
        for(int i = centerY; i < h; i += Yscale){
            g.drawLine(0, i, w, i);
        }
        for(int i = centerX; i > 0; i -= Xscale){
            g.drawLine(i, 0, i, h);
        }
        for(int i = centerY; i > 0; i -= Yscale){
            g.drawLine(0, i, w, i);
        }
        g.setColor(c);
    }

    private void drawFunction(Graphics g, int w, int h){
        Color c = g.getColor();
        g.setColor(functionColor);
        double valuePerPixelX = calculateValuePerPixelInX(w);
        double valuePerPixelY = calculateValuePerPixelInY(h);

        Point[] points = new Point[w];
        for(int i = 0; i < w; i++){
            double x = 0 + (valuePerPixelX * (i - centerX));
            //*this is the function*//
            double y = Math.sin(x);
            int j = convertToPixelValue(y, valuePerPixelY, h);
            points[i] = new Point(i, j);
        }

        for(int i = 0; i < points.length - 1; i++){
            g.drawLine(points[i].x, points[i].y, points[i+1].x, points[i+1].y);
        }
        g.setColor(c);
    }

    private double calculateValuePerPixelInX(int w){
        double offset = (centerX) % Xscale;
        double offsetInUnit = offset / (double)Xscale;
        int integerUnit = (centerX) / Xscale;
        double totalUnit = (double)integerUnit + offsetInUnit;
        double valuePerPixel = totalUnit/(centerX);
        return valuePerPixel;
    }

    private double calculateValuePerPixelInY(int h){
        double offset = (centerY) % Yscale;
        double offsetInUnit = offset / (double)Yscale;
        int integerUnit = (centerY) / Yscale;
        double totalUnit = (double)integerUnit + offsetInUnit;
        double valuePerPixel = totalUnit/(centerY);
        return valuePerPixel;
    }

    private int convertToPixelValue(double value, double valuePerPixel, int h){
        
        return (int)((centerY)-(value/valuePerPixel));
        

    }  

    /* -------------------------------------------------------------------------- */
    /*                             SETTERS AND GETTERS                            */
    /* -------------------------------------------------------------------------- */

    /**
    *<h3>getBackground color</h3>
    *<p>Returns the background color of the board</p>
    *@return background color
    */  
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    public Color getAxisColor() {
        return this.axisColor;
    }

    public void setAxisColor(Color axisColor) {
        this.axisColor = axisColor;
        repaint();
    }

    public Color getGridColor() {
        return this.gridColor;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
        repaint();
    }

    public Color getFunctionColor() {
        return this.functionColor;
    }

    public void setFunctionColor(Color functionColor) {
        this.functionColor = functionColor;
        repaint();
    }

    public int getXscale() {
        return this.Xscale;
    }

    public void setXscale(int Xscale) {
        if(Xscale < minScale){
            this.Xscale = minScale;
        }
        else if(Xscale > maxScale){
            this.Xscale = maxScale;
        }
        else{
            this.Xscale = Xscale;
        }
        repaint();
    }

    public int getYscale() {
        return this.Yscale;
    }

    public void setYscale(int Yscale) {
        if(Yscale < minScale){
            this.Yscale = minScale;
        }else if(Yscale > maxScale){
            this.Yscale = maxScale;
        }else{  
            this.Yscale = Yscale;
        }
        repaint();
    }

    public int getCenterX(){
        return this.centerX;
    }

    public void setCenterX(int centerX){
        this.centerX = centerX;
        repaint();
    }

    public int getCenterY(){
        return this.centerY;
    }

    public void setCenterY(int centerY){
        this.centerY = centerY;
        repaint();
    }



    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // TODO Auto-generated method stub
        if(e.isShiftDown())
            setXscale(getXscale()+e.getWheelRotation() * scaleStep);
        else{
            if(e.isControlDown())
                setYscale(getYscale()+e.getWheelRotation() * scaleStep);
            else{
                setXscale(getXscale()+e.getWheelRotation() * scaleStep);
                setYscale(getYscale()+e.getWheelRotation() * scaleStep);
            }                
        }
        
    }



    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        //move the center of the board
        System.out.println("dragged");
        int lastX = e.getX();
        int lastY = e.getY();
        if(e.isShiftDown()){
            setCenterX(getCenterX() + (lastX - getCenterX()));
        } else if(e.isControlDown()){
            setCenterY(getCenterY() + (lastY - getCenterY()));
        } else{
            setCenterX(getCenterX() + (lastX - getCenterX()));
            setCenterY(getCenterY() + (lastY - getCenterY()));
        }

        
    }



    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }



	

}
