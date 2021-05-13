// Description: Draw function

// Copyright (c) 2017 - 2018
// Tomas Bayer
// Charles University in Prague, Faculty of Science
// bayertom@natur.cuni.cz

// This library is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this library. If not, see <http://www.gnu.org/licenses/>.

package adaptivesampling;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Tomáš
 */
public class Draw extends JPanel{
        private double a;
        private double b;
        private List<Point2D.Double> points;
        private List<List<Point2D.Double>> fragments;
        
        public Draw (double a, double b, List<List<Point2D.Double>> fragments, List<Point2D.Double> points)
        {
                this.a = a;
                this.b = b;
                this.points = points;
                this.fragments = fragments;
        }
        
        void setA(final double a){this.a = a;}
        void setB(final double b){this.b = b;}
        
        public void createAxes(Graphics2D g2, final int scale, final int sx, final int sy)
        {
                //Draw X,Y axes
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(1));
                
                //Get intervals
                final int xmin = scale * (int)Math.floor(a) + sx;
                final int xmax = scale * (int)Math.ceil(b) + sx;
                final int ymin = scale * (int)Math.floor(a) + sy;
                final int ymax = scale * (int)Math.ceil(b) + sy;
                
                //Draw X and Y axes
                g2.draw( new Line2D.Double(xmin, sy, xmax, sy));
                g2.draw( new Line2D.Double(sx, ymin, sx, ymax));
                
                //Create marks on x axis
                for (int xi = xmin; xi <= xmax; xi+= scale)
                {
                        g2.draw(new Line2D.Double(xi, sy, xi,sy+10));
                }
                
                //Create marks on y axis
                for (int yi = ymin; yi <= ymax; yi+= scale)
                {
                        g2.draw(new Line2D.Double(sx, yi, sx-10, yi));
                }
        }
            
        
        public void paintComponent(Graphics g)
        {
                //Repaint
                super.paintComponent(g);
                
                //Scale and shifts
                int sx = (this.getWidth()-1)/2;
                int sy = (this.getHeight()-1)/2;
                int scale = (int)(this.getWidth() / Math.abs(b-a));

                //Set background
                setBackground(Color.WHITE);
                Graphics2D g2=(Graphics2D)g;
                
                //Create axes
                createAxes(g2, scale, sx, sy);
            
                //Draw function
                try
                {
                        //Store results
                        /*
                        File file = new File("E:\\Tomas\\Latex\\xxx.txt");
                        file.createNewFile();
                        FileWriter fw = new FileWriter(file.getAbsoluteFile());
                        */
                        
                        //Process fragments one by one
                        g2.setColor(Color.red);
                        g2.setStroke(new BasicStroke(3));
                        for (final List <Point2D.Double> frag : fragments)
                        {
                                //Get list of points and its size
                                final int n = frag.size();

                                //Create array of [x,y] coordinates
                                int [] x = new int[n];
                                int [] y = new int[n];

                                //Convert list of points to polyline and rescale
                                for (int i = 0; i < n; i++)
                                {
                                        x[i] = (int)(scale * frag.get(i).getX()) + sx;
                                        y[i] = (int)(scale * frag.get(i).getY()) + sy;

                                        //System.out.println(pointsa.get(i).getX() + " " + pointsa.get(i).getY());
                                        //fw.write(pointsa.get(i).getX() + " " + pointsa.get(i).getY() + "\n");
                                }

                                //Draw polyline
                                g2.drawPolyline(x, y, n);

                                //System.out.println(" ");
                                //fw.write("\n");
                        }

                        //fw.flush();
                        //fw.close();
                }
                
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
}
