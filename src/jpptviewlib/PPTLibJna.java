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

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef;

/**
 * Manages the JNA side of things directly wraps the DLL.
 *
 * @author Michael Berry
 */
public interface PPTLibJna extends Library {

    PPTLibJna INSTANCE = (PPTLibJna) Native.loadLibrary("pptviewlib.dll", PPTLibJna.class);

    boolean CheckInstalled();

    int OpenPPT(WString filename, WinDef.HWND hParentWnd, RectByValue rect, WString pptViewerPath);

    void ClosePPT(int id);

    int GetCurrentSlide(int id);

    int GetSlideCount(int id);

    void NextStep(int id);

    void PrevStep(int id);

    void GotoSlide(int id, int slide_no);

    void RestartShow(int id);

    void Blank(int id);

    void Unblank(int id);

    void Stop(int id);

    void Resume(int id);

    void SetDebug(boolean onOff);
}

/**
 * We need this to be able to pass a native RECT object by value into the DLL -
 * the default is by reference, which doesn't work (we could have modified the
 * DLL which would have been easier, but nice to keep as few modifications as
 * possible on the native side.)
 *
 * @author Michael Berry
 */
class RectByValue extends WinDef.RECT implements Structure.ByValue {
}
