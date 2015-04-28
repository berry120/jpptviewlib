# JPPTViewLib

JPPTViewLib provides an easy way to control Powerpoint viewer from Java. It is essentially a rather light JNA based wrapper of [PPTViewLib](http://bazaar.launchpad.net/~openlp-core/openlp/trunk/view/head:/openlp/plugins/presentations/lib/pptviewlib/), a plugin developed for OpenLP to enable it to display powerpoint presentations.

You *must* have `pptviewlib.dll` present on your system and set `-Djna.library.path=C:\your\path\to\dll`, otherwise you'll find yourself buried in a `UnsatisfiedLinkError`. You may use the DLL here or build from the OpenLP source; the one here (in the lib folder) is recommended since it has a minor modification to guard against passing `null` as a parameter to preview path.

**Usage Example**:

```
String pptPath = "C:\\My Presentation.ppt";
Presentation pres = new Presentation(new File(pptPath), 0, 0, 1920, 1080); //Load presentation assuming a 1080p screen (in real life you'll need to match this to a monitor!)
pres.start();
for(int i=0 ; i<10 ; i++) { //Loop through the first 10 steps of the presentation, with a second's pause between each one.
    Thread.sleep(1000);
    pres.stepForward();
}
pres.setBlacked(true); //Black out the presentation
Thread.sleep(1000);
pres.stop(); //Exit
```
