/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mathslv;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 *
 * @author nikol
 */
public class Graph extends JPanel{
    
boolean drawFirst = false;
    private final String f;
    private double range;
    public Graph(String f, Dimension d, double range) {
        this.f = f.replace(" ", "");
        this.range = range;
        setSize(d);
        setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    private String changeX(String x, String num) {
        while(x.contains("x")) {
            int i = x.indexOf("x");
            if(i != 0 && "0123456789".contains("" + x.charAt(i - 1))) {
                x = x.substring(0, i) + x.substring(i + 1);
                x = x.substring(0, i) + "*" + num + x.substring(i);
            }
            else {
                x = x.substring(0, i) + x.substring(i + 1);
                x = x.substring(0, i) + num + x.substring(i);
            }
        }
        return x;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g2.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        Polygon p = new Polygon();
        g2.setColor(Color.RED);
        double step = range / getWidth();
        for(double i = -(range / 2); i <= (range / 2); i += step) {
            i = Math.round(i * 100) / 100.0;
            String x = Double.toString(i).replace(",", ".");
            String y = changeX(f, x);
            double fnl =Double.parseDouble(Solve.eval(y));
            //if(!drawFirst) System.out.println((int) ((i + (range / 2)) * (1000.0 / range)) + ": " + (1000 - (int)((range / 2) * (1000.0 / range))));
            p.addPoint((int) ((i + (range / 2)) * (getWidth() / range)), getHeight() - (int)((fnl + range / 2) * (getHeight() / range)));
        }

        g2.drawPolyline(p.xpoints, p.ypoints, p.npoints);

        g2.dispose();
        drawFirst = true;
    }

}
