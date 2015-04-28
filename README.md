# jpptviewlib
JNA Java wrapper around the PPTViewLib library originally developed for OpenLP (https://github.com/Boddlnagg/PowerpointViewerLib/tree/master/pptviewlib)

**Usage Example**:

```
       String pptPath = "C:\\My Presentation.ppt";
        Presentation pres = new Presentation(new File(pptPath), 0, 0, 1920, 1080); //Load presentation assuming a 1080p screen (in real life you'll need to match this to a monitor!)
        pres.start();
        for(int i=0 ; i<10 ; i++) { //Loop through the first 10 steps of the presentation, with a second's pause between each one.
            sleep(1000);
            pres.stepForward();
        }
        pres.setBlacked(true); //Black out the presentation
        sleep(1000);
        pres.stop(); //Exit```
