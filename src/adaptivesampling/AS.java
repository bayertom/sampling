// Description: Adaptive sampling class

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

import static java.lang.Math.*;
import java.util.List;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Tomáš
 */
public class AS {
        
        /*
        public static void adaptiveSampling(final IFunction fx, final List<Point2D.Double> points, final double u, final double v, final int d, final double fmax, final int nmax, final double eps, final double ff)
        {
                //Compute function values
                final double fu = fx.f(u);
                final double fv = fx.f(v);
                
                //Singular case
                if ( Math.abs(fu) > fmax)
                        throw new MathSingularityException("", "", u); 

                //Singular case
                if ( Math.abs(fv) > fmax)
                        throw new MathSingularityException("", "", v);
                
                //Add first point
                points.add(new Point2D.Double(u, fu));
                        
                //Intermediat points
                adaptiveSamplingInt3(fx, points, u, v, fu, fv, d, fmax, nmax, eps, ff);
                
                //Add last point;
                points.add(new Point2D.Double(v, fv));
                
                //System.out.println(n[0]);
        }
        */
        
        
        public static void adaptiveSamplingInt(final IFunction fx, final List<Point2D.Double> points, final double u, final double v, final double fu, final double fv, final int d, final double fmax, final int dmax, final double eps, final double ff)
        {
                //Stop sampling, recursive implementation
                //Two recursive calls, split to two subintervals
                
                //if (d > dmax)
                //        return;
                
                //Compute mid point
                final double x1 = 0.5 * (u + v);
                
                //Compute function vaules at x1
                final double fx1 = fx.f(x1);
                
                //Singular case at x
                if ( Math.abs(fx1) > fmax)
                {
                        throw new MathSingularityException("", "", x1); 
                }
                
                //Test criterion at x1: Is another recursive step required?
                final double alpha = getCriterion(u, fu, x1, fx1, v, fv);
                //System.out.println(alpha + ":" + u + " " + fu + ", " + x1 + " " + fx1 + ", " + v + " " + fv + "\n");
                
                //Function not flat, recursion, first part
                if (alpha > ff)
                {
                        //Process left sub interval
                        if (alpha > ff)
                                adaptiveSamplingInt(fx, points, u, x1, fu, fx1, d+1, fmax, dmax, eps, ff);
                        
                        //Add new point
                        points.add(new Point2D.Double(x1, fx1));
                        
                        //Process right sub interval
                        if (alpha > ff)
                                adaptiveSamplingInt(fx, points, x1, v, fx1, fv, d+1, fmax, dmax, eps, ff);
                }             
        }


public static void adaptiveSamplingInt2(final IFunction fx, final List<Point2D.Double> points, final double u, final double v, final double fu, final double fv, final int d, final double fmax, final int dmax, final double eps, final double ff)
        {
                //Recursive implementation, method M2
                //Three recursive calls are used, split to 3 subintervals
                //if (d > dmax)
                //        return;
                
                //Take 2 points inside the interval (u,v)
                //final double w = u + (Math.random() * (v - u));
                final double x1 = 1.0 / 4  * v + 3.0 / 4 * u;
                final double x2 = 1.0 / 4  * u + 3.0 / 4 * v;
                
                //Compute function vaules at these points
                double fx1 = fx.f(x1);
                double fx2 = fx.f(x2);
                
                //Singular case at x1
                if ( Math.abs(fx1) > fmax || x1 == 0)
                {
                        throw new MathSingularityException("", "", x1); 
                }
                
                //Singular case at x2
                if ( Math.abs(fx2) > fmax || x2 == 0)
                {
                        throw new MathSingularityException("", "", x2); 
                }
                
                //Compute test criteria at x1, x2: Is another recursive step required?
                final double alpha1 = getCriterion(u, fu, x1, fx1, x2, fx2);
                final double alpha2 = getCriterion(x1, fx1, x2, fx2, v, fv);
                               
                //Function not flat, recursion depth d < dmax
                if (alpha1 > ff || alpha2 > ff)
                {
                        //Process first sub interval
                        if (alpha1 > ff)
                                adaptiveSamplingInt2(fx, points, u, x1, fu, fx1, d+1, fmax, dmax, eps, ff);
                        
                        //Add new point
                        points.add(new Point2D.Double(x1, fx1));
                        
                        //Process middle sub interval
                        if (alpha1 > ff || alpha2 > ff)
                                adaptiveSamplingInt2(fx, points, x1, x2, fx1, fx2, d+1, fmax, dmax, eps, ff);
                        
                        //Add new point
                        points.add(new Point2D.Double(x2, fx2));
                        
                        //Process third sub interval
                        if (alpha2 > ff)
                                adaptiveSamplingInt2(fx, points, x2, v, fx2, fv, d+1, fmax, dmax, eps, ff);
                }    
        }

        
        public static void adaptiveSamplingInt3(final IFunction fx, final List<Point2D.Double> points, final double a, final double b, final double ya, final double yb, final int d, final double fmax, final int dmax, final double eps, final double ff)
        {
                //Adaptive sampling, recursive implementation, method M3
                //3 recursive calls are used, split to 4 subintervals
                
                //Stop sampling
                //if (d >= dmax)
                //        return;
               
                //Stop sampling, interval is too narrow
                if (b-a < eps)
                        return;
                
                final double dmin = 0;
                //System.out.println("Depth= " + d);
                
                
                //Take 3 random points inside the interval (u,v)
                Random rand = new Random();
                final double r1 = rand.nextDouble() * 0.1 + 0.45;
                final double r2 = rand.nextDouble() * 0.1 + 0.45;
                final double r3 = rand.nextDouble() * 0.1 + 0.45;
               
                final double x1 = a + r1 * (b-a) / 2.0;
                final double x2 = a + r2 * (b-a);
                final double x3 = a + r3 * (b-a) * 3.0 /2.0;

                //Compute function vaules at x1, x2, x3
                final double y1 = fx.f(x1);
                final double y2 = fx.f(x2);
                final double y3 = fx.f(x3);

                //Singular case at x1
                if ( discontLR(fx, x1, 0.5 * eps, fmax))
                        throw new MathSingularityException("", "",x1); 
                
                //Singular case at x2
                if ( discontLR(fx, x2, 0.5 * eps, fmax))
                        throw new MathSingularityException("", "",x2); 
                
                //Singular case at x3
                if ( discontLR(fx, x3, 0.5 * eps, fmax))
                        throw new MathSingularityException("", "",x3); 

                //Compute test criteria at x1, x2, x3: Is another recursive step required?
                final double alpha1 = getCriterion(a, ya, x1, y1, x2, y2);
                final double alpha2 = getCriterion(x1, y1, x2, y2, x3, y3);
                final double alpha3 = getCriterion(x2, y2, x3, y3, b, yb);
                        
                //Not flat, recursion depth < dmax
                if (alpha1 > ff || alpha2 > ff || alpha3 > ff || d < dmin)
                {
                        //Process first sub interval
                        if (alpha1 > ff || d < dmin)
                                adaptiveSamplingInt3(fx, points, a, x1, ya, y1, d+1, fmax, dmax, eps, ff);
                        
                        //Add new point
                        points.add(new Point2D.Double(x1, y1));
                        
                        //Process second sub interval
                        if (alpha1 > ff || alpha2 > ff || d < dmin)
                                adaptiveSamplingInt3(fx, points, x1, x2, y1, y2, d+1, fmax, dmax, eps, ff);
                        
                        //Add new point
                        points.add(new Point2D.Double(x2, y2));
                        
                        //Process third sub interval
                        if (alpha2 > ff || alpha3 > ff || d < dmin)
                                adaptiveSamplingInt3(fx, points, x2, x3, y2, y3, d+1, fmax, dmax, eps, ff);
                        
                        //Add new point
                        points.add(new Point2D.Double(x3, y3));
                        
                        //Process fourth sub interval
                        if (alpha3 > ff || d < dmin)
                                adaptiveSamplingInt3(fx, points, x3, b, y3, yb, d+1, fmax, dmax, eps, ff);
                }    
        }

        
        public static double getCriterion(final double x1, final double y1, final double x2, final double y2, final double x3, final double y3)
        {
                //Compute angle between 2 line segments of the sampled line
                final double dx1 = x3 - x2;
                final double dx2 = x1 - x2;
                final double dy1 = y3 - y2;
                final double dy2 = y1 - y2;
    
                //Norms
                final double n1 = sqrt(dx1 * dx1 + dy1 * dy1);
                final double n2 = sqrt(dx2 * dx2 + dy2 * dy2);
                
                return abs(abs(Math.acos((dx1 * dx2 + dy1 * dy2)/(n1 * n2)))* 180 / PI - 180);   
        }
        
        
        public static void asf(final IFunction fx,  List<List<Point2D.Double>> fragments, final double a, final double b, final double fmax, final int smax, final int dmax, final double eps, final double ff)
        {
                //Adaptive sampling with discontinuities: stack-based implementation
                int d = 0, s = 0;
                
                //Create empty stack
                Stack <Interval> S = new Stack<>();
                
                //Create first interval (a,b) and push to S
                Interval i = new Interval(a, b);
                S.push(i);
                
                //Repeat until the stack is empty
                while (!S.empty())
                {
                        //Get active interval
                        i = S.pop();
                        
                        //Adaptive sampling
                        try
                        {
                                //System.out.println(i.a + " " + i.b + "\n");
                                
                                //Create list of points
                                List<Point2D.Double> points = new ArrayList<>();

                                //Compute function values at a, b
                                final double fa = fx.f(i.a);
                                final double fb = fx.f(i.b);

                                //Singular case at a
                                if ( discontLR(fx, i.a, 0.5*eps, fmax))
                                        throw new MathSingularityException("", "", i.a); 

                                //Singular case at b
                                if ( discontLR(fx, i.b, 0.5*eps, fmax))
                                        throw new MathSingularityException("", "", i.b);

                                //Add first point a
                                points.add(new Point2D.Double(i.a, fa));

                                //Adaptive sampling
                                adaptiveSamplingInt3(fx, points, i.a, i.b, fa, fb, d, fmax, dmax, eps, ff);
                                
                                //Add last point b
                                points.add(new Point2D.Double(i.b, fb));
                                
                                //Add to the list
                                fragments.add(points);
                                
                                //System.out.println( "Interval:" +i.a + ", " + i.b);
                        }
                        
                        //Exception
                        catch (MathSingularityException e)
                        {
                                //Get singularity
                                final double c = e.getArg();
                                
                                //Too many splits, suspected function
                                if ( s > smax)
                                {
                                        fragments.clear();
                                        return;
                                }

                                //Empty interval, delete
                                if (Math.abs(i.b - i.a) < 2 * eps)
                                {
                                        continue;
                                }
                                
                                //Incorrect interval, jump over
                                else if (i.a > i.b)
                                {
                                        continue;
                                }

                                //Value c is lower bound: shift lower bound
                                else if ((i.a <= c) && (abs(c - i.a) < eps))
                                {
                                        i.a +=  eps;
                                }

                                //Value c is upper bound: shift upper bound
                                else if ((i.b >= c) && (abs(c - i.b) < eps))
                                {
                                        i.b -= eps;
                                }

                                //Value c inside interval (a, b): split interval into 2 subintervals
                                else if ((i.a < c) && (i.b > c) && (abs(c - i.a) > eps) && (abs(c - i.b) > eps))
                                {
                                        //New boundaries od the interval
                                        double bl = c - eps;
                                        final double ar = c + eps;
                                        
                                        //Create new interval over the right part of the subinterval
                                        Interval i2 = new Interval(ar, i.b);
                                        
                                        if( (i2.a < i2.b) && (Math.abs(i2.b - i2.a) > 2 * eps))
                                                S.push(i2);   
                                        
                                        //Left interval: do not create new one, change its upper bound
                                        i.b = bl;
                                        
                                        //Increment amount of splits
                                        s++;
                                }

                                //Add modified interval, which is sufficiently wide, to the stack S
                                if( (i.a < i.b) && (Math.abs(i.b - i.a) > 2 * eps))
                                        S.push(i);
                        }
                }
                
                //System.out.println("Splits: " + splits);
        }      
        

        public static void asfr(final IFunction fx, List<List<Point2D.Double>> fragments, double a, double b, int s, final double fmax, final int smax, final int dmax, final double eps, final double ff)
        {
                //Adaptive sampling with discontinuities: recursive version
                
                //Too many splits, suspected function
                if (s > smax) 
                {
                        fragments.clear();
                        return;
                }
                
                //Incorrect interval, skip 
                if (a > b) 
                {
                        return;
                }

                //Empty interval, skip
                else if (Math.abs(b - a) < 2 * eps) 
                {
                        return;
                }
                
                //Probably, correct interval
                else
                {
                        try
                        {
                                //Create list
                                List<Point2D.Double> points = new ArrayList<>();

                                //Compute function values at a, b
                                final double fa = fx.f(a);
                                final double fb = fx.f(b);

                                //Singular case at a
                                if ( discontLR(fx, a, 0.5 * eps, fmax))
                                        throw new MathSingularityException("", "",a); 

                                //Singular case at b
                                if ( discontLR(fx, b, 0.5 * eps, fmax))
                                        throw new MathSingularityException("", "", b);

                                //Add first point a
                                points.add(new Point2D.Double(a, fa));

                                //Adaptive sampling: call recursive procedure
                                adaptiveSamplingInt3(fx, points, a, b, fa, fb, 1, fmax, dmax, eps, ff);
                                
                                //Add last point b
                                points.add(new Point2D.Double(b, fb));
                                
                                //Add to the list
                                fragments.add(points);
                                
                                //System.out.println( "interval" + a + ", " + b + "\n");
                        }
                        
                        //Exception has been detected
                        catch (MathSingularityException e)
                        {
                                //Get singularity
                                final double c = e.getArg();

                                //Value c is lower bound: shift lower bound
                                if ((a <= c) && (abs(c - a) < eps))
                                {
                                        a +=  eps;
                                        asfr(fx, fragments, a, b, s + 1, fmax, smax, dmax, eps, ff);
                                }

                                //Value c is upper bound: shift upper bound
                                else if ((b >= c) && (abs(c - b) < eps))
                                {
                                        b -= eps;
                                        asfr(fx, fragments, a, b, s + 1, fmax, smax, dmax, eps, ff);
                                }

                                //Value c inside interval (a, b): split interval into 2 subintervals
                                else if ((a < c) && (b > c) && (abs(c - a) > eps) && (abs(c - b) > eps))
                                {
                                        //New boundaries
                                        final double bl = c - eps;
                                        final double ar = c + eps;
                                                                               
                                        //Call recursive procedure: first subinterval
                                        if( (a < bl) && (abs(bl - a) > 2 * eps))
                                                asfr(fx, fragments, a, bl, s + 1, fmax, smax, dmax, eps, ff);
                                        
                                        //Call recursive procedure: second subinterval
                                        if( (ar < b) && (Math.abs(b - ar) > 2 * eps))
                                                asfr(fx, fragments, ar, b, s + 1, fmax, smax, dmax, eps, ff);
                                }
                        }
                }
        }
        
        
        public static boolean discontLR (final IFunction fx, final double xi, final double h, double fmax)
        {
                //Detect discontinuity using LR criterion
                final double xi1 = xi + h;
                final double xi2 = xi1 + h;
                final double x_i1 = xi - h;
                final double x_i2 = x_i1 - h;

                //Compute f(xi)
                final double fxi = fx.f(xi);

                //Singularity at xi
                if(Double.isNaN(fxi) || Double.isInfinite(fxi) || abs(fxi) > fmax)
                {
                       //System.out.println("E=" + xi);
                       return true;
                }
                
                //Compute f(xi1)
                final double fxi1 = fx.f(xi1);

                //Singularity at xi1
                if(Double.isNaN(fxi1) || Double.isInfinite(fxi1) || abs(fxi1) > fmax)
                {
                        //System.out.println("E=" + xi1);
                        return true;
                }
                
                //Compute f(xi2)
                final double fxi2 = fx.f(xi2);

                //Singularity at xi2
                if(Double.isNaN(fxi2) || Double.isInfinite(fxi2) || abs(fxi2) > fmax)
                {
                        //System.out.println("E=" + xi2);
                        return true;
                }
                
                //Compute f(x_i1)
                final double fx_i1 = fx.f(x_i1);

                //Singularity at x_i1
                if(Double.isNaN(fx_i1) || Double.isInfinite(fx_i1) || abs(fx_i1) > fmax)
                {
                        //System.out.println("E=" + x_i1);
                        return true;
                }
                   
                //Compute f(x_i2)
                final double fx_i2 = fx.f(x_i2);

                //Singularity at x_i1
                if(Double.isNaN(fx_i2) || Double.isInfinite(fx_i2) || abs(fx_i2) > fmax)
                {
                        //System.out.println("E=" + x_i2);
                        return true;
                }

                //Compute LR criterion
                final double fr = 3 * fxi - 4 * fxi1 + fxi2;
                final double fl = 3 * fxi - 4 * fx_i1 + fx_i2;
                final double lr = abs((fr * fr - fl * fl)/(fr * fr + fl * fl + 0.0001));
                
                //System.out.println(lr);

                //Threshold exceeded, possible discontinuity
                if (lr > 0.8)
                {
                        //System.out.println("E=" + xi);
                        return true;
                }
                 
                //No discontinuity has been found
                return false;
        }
        
        
         public static boolean discontWeno (final IFunction fx, final double xi, final double h, double fmax)
        {
                //Detect discontinuity using WENO criterion
                final double xi1 = xi + h;
                final double xi2 = xi1 + h;
                final double x_i1 = xi - h;
                final double x_i2 = x_i1 - h;
                
                //Compute f(xi)
                final double fxi = fx.f(xi);

                //Singularity at xi
                if(Double.isNaN(fxi) || Double.isInfinite(fxi) || abs(fxi) > fmax)
                       return true;

                //Compute f(xi1)
                final double fxi1 = fx.f(xi1);

                //Singularity at xi1
                if(Double.isNaN(fxi1) || Double.isInfinite(fxi1) || abs(fxi1) > fmax)
                       return true;
                
                //Compute f(xi2)
                final double fxi2 = fx.f(xi2);

                //Singularity at xi2
                if(Double.isNaN(fxi2) || Double.isInfinite(fxi2) || abs(fxi2) > fmax)
                       return true;
                
                //Compute f(x_i1)
                final double fx_i1 = fx.f(x_i1);

                //Singularity at x_i1
                if(Double.isNaN(fx_i1) || Double.isInfinite(fx_i1) || abs(fx_i1) > fmax)
                       return true;
                
                //Compute f(x_i2)
                final double fx_i2 = fx.f(x_i2);

                //Singularity at x_i2
                if(Double.isNaN(fx_i2) || Double.isInfinite(fx_i2) || abs(fx_i2) > fmax)
                       return true;

                //Compute WENO criterion
                final double df01 = fx_i2 - 2 * fx_i1 + fxi;
                final double df02 = fx_i2 - 4 * fx_i1 + 3.0*fxi;
                final double is0 = 13.0 / 12 * df01 * df01 + 0.25 * df02 * df02;
                
                final double df11 = fx_i1 - 2 * fxi + fxi1;
                final double df12 = fx_i1 - fxi1;
                final double is1 = 13.0 / 12 * df11 * df11 + 0.25 * df12 * df12;

                final double df21 = fxi2 - 2 * fxi1 + fxi;
                final double df22 = fxi2 - 4 * fxi1 + 3 *fxi;
                final double is2 = 13.0 / 12 * df21 * df21 + 0.25 * df22 * df22;
                
                final double alpha0 = 1.0 / (is0 * is0 + 0.0001);
                final double alpha1 = 1.0 / (is1 * is1 + 0.0001);
                final double alpha2 = 1.0 / (is2 * is2 + 0.0001);
                final double alphas = alpha0 + alpha1 + alpha2;
                final double om0 = alpha0 /alphas;
                final double om1 = alpha1 /alphas;
                final double om2 = alpha2 /alphas;

                //Threshold exceeded?
                final double th = 0.8;
                if (om0 > th || om1 > th || om2 > th)
                        return true;
                else
                        return false;
        }
}
