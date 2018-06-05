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
package org.quelea.jpptviewlib;

import com.sun.jna.WString;
import java.io.File;

/**
 * Responsible for opening, controlling and closing presentations.
 *
 * @author Michael Berry
 */
public class Presentation {

    private static final boolean installed;

    static {
        installed = PPTLibJna.INSTANCE.CheckInstalled();
    }

    private final int x, y, width, height;
    private final File pptFile;
    private final String previewPrefix;
    private int jnaID;

    /**
     * Create a new presentation. (Will not run until the start() method is
     * called.)
     *
     * @param pptFile the PPT or PPS file to open in the viewer.
     * @param x the x position of the top left of the window.
     * @param y the y position of the top left of the window.
     * @param width the width of the window.
     * @param height the height of the window.
     */
    public Presentation(File pptFile, int x, int y, int width, int height) {
        this(pptFile, x, y, width, height, null);
    }

    /**
     * Create a new presentation. (Will not run until the start() method is
     * called.)
     *
     * @param pptFile the PPT or PPS file to open in the viewer.
     * @param x the x position of the top left of the window.
     * @param y the y position of the top left of the window.
     * @param width the width of the window.
     * @param height the height of the window.
     * @param previewPrefix the preview prefix to use for saving slide previews.
     * On calling the start method, this prefix will be used to fill the
     * specified directory with preview images. The format should be the folder
     * (which must exist) with the file name prefix appended, eg.
     * "C:\preview\img" will cause a bunch of images to be saved in "C:\preview"
     * called, img0.bmp, img1.bmp, img2.bmp etc. for each slide in the
     * presentation.
     */
    public Presentation(File pptFile, int x, int y, int width, int height, String previewPrefix) {
        if (!installed) {
            throw new IllegalStateException("Can't initialise a presentation - could not find the powerpoint viewer.");
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.pptFile = pptFile;
        this.previewPrefix = previewPrefix;
    }

    /**
     * Start the presentation.
     */
    public void start() {
        RectByValue rect = new RectByValue();
        rect.left = x;
        rect.top = y;
        rect.right = x + width;
        rect.bottom = y + height;
        jnaID = PPTLibJna.INSTANCE.OpenPPT(new WString(pptFile.getAbsolutePath()), null, rect, new WString(previewPrefix));
    }

    /**
     * Stop the presentation.
     */
    public void stop() {
        PPTLibJna.INSTANCE.ClosePPT(jnaID);
    }

    /**
     * Resume the presentation.
     */
    public void resume() {
        PPTLibJna.INSTANCE.Resume(jnaID);
    }

    /**
     * Get the current slide of the presentation.
     *
     * @return the presentation's current slide.
     */
    public int getCurrentSlide() {
        return PPTLibJna.INSTANCE.GetCurrentSlide(jnaID);
    }

    /**
     * Get the total number of slides in the presentation.
     *
     * @return the total number of slides in the presentation.
     */
    public int getSlideCount() {
        return PPTLibJna.INSTANCE.GetSlideCount(jnaID);
    }

    /**
     * Move forward in the presentation (equivalent to hitting, space, right,
     * pgdown etc. in powerpoint.)
     */
    public void stepForward() {
        PPTLibJna.INSTANCE.NextStep(jnaID);
    }

    /**
     * Move backwards in the presentation (equivalent to hitting, left, pgup
     * etc. in powerpoint.)
     */
    public void stepBack() {
        PPTLibJna.INSTANCE.PrevStep(jnaID);
    }

    /**
     * Move the presentation to a particular slide.
     *
     * @param slideNum the number of the slide the presentation should be moved
     * to.
     */
    public void goToSlide(int slideNum) {
        PPTLibJna.INSTANCE.GotoSlide(jnaID, slideNum);
    }

    /**
     * Restart the specified presentation from the beginning.
     */
    public void restart() {
        PPTLibJna.INSTANCE.RestartShow(jnaID);
    }

    /**
     * Set whether the screen should be blacked out.
     *
     * @param blacked true if the screen should be black, false if the slides
     * should be displayed.
     */
    public void setBlacked(boolean blacked) {
        if (blacked) {
            PPTLibJna.INSTANCE.Blank(jnaID);
        } else {
            PPTLibJna.INSTANCE.Unblank(jnaID);
        }
    }

}
