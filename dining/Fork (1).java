package dini;

import java.awt.Color;
import java.awt.Graphics;

import dini.Table;

class Fork {
    private Table t;
    private static final int XSIZE = 10;
    private static final int YSIZE = 10;
    private int orig_x;
    private int orig_y;
    private int x;
    private int y;
    private int value=1;
    public Fork(Table T, int cx, int cy) {
        t = T;
        orig_x = cx;
        orig_y = cy;
        x = cx;
        y = cy;
    }

    public void reset() {
        clear();
        x = orig_x;
        y = orig_y;
        t.repaint();
    }

    public void acquire(int px, int py) {
        clear();
        x = (orig_x + px)/2;
        y = (orig_y + py)/2;
        t.repaint();
    }
public int check()
{
	return value;

}
public void eat()
{
	value=0;
}
public void done()
{
	value=1;
}

    public void release() {
        reset();
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(x-XSIZE/2, y-YSIZE/2, XSIZE, YSIZE);
    }
    private void clear() {
        Graphics g = t.getGraphics();
        g.setColor(t.getBackground());
        g.fillOval(x-XSIZE/2, y-YSIZE/2, XSIZE, YSIZE);
    }
}
