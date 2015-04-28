/*
 *  This file is part of JPPTLib.
 *
 *  JPPTLib is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  JPPTLib is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JPPTLib.  If not, see <http://www.gnu.org/licenses/>.
 */
package jpptviewlib;

import java.io.File;

/**
 * (Very) simple demo application!
 * @author Michael Berry
 */
public class PresentationDemo {
    
    public static void main(String[] args) {
        String pptPath = "C:\\My Presentation.ppt";
        Presentation pres = new Presentation(new File(pptPath), 0, 0, 1920, 1080); //Load presentation assuming a 1080p screen (in real life you'll need to match this to a monitor!)
        pres.start();
        for(int i=0 ; i<10 ; i++) { //Loop through the first 10 steps of the presentation, with a second's pause between each one.
            sleep(1000);
            pres.stepForward();
        }
        pres.setBlacked(true); //Black out the presentation
        sleep(1000);
        pres.stop(); //Exit
    }
    
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        }
        catch(InterruptedException ex) {}
    }
    
}
