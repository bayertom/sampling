// Description: Singularity in x

// Copyright (c) 2017-2018
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

//Math error: singularity in the latitude direction
public class MathSingularityException extends MathException{
        
        private final double x;
        
        public MathSingularityException(final String exception_text_, final String math_text_, final double x_)
        {
                super(exception_text_, math_text_);
                x = x_;
        }
        
        @Override
        public void printException() {
                super.printStackTrace();
                System.out.println(x + '\n');
        }
        
        @Override
        public double getArg() {return x;}
}
