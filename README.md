[![Build Status](https://travis-ci.org/berry120/jpptviewlib.svg?branch=master)](https://travis-ci.org/berry120/jpptviewlib)

# JPPTViewLib

JPPTViewLib provides an easy way to control Powerpoint viewer from Java. It is essentially a rather light JNA based wrapper of [PPTViewLib](http://bazaar.launchpad.net/~openlp-core/openlp/trunk/view/head:/openlp/plugins/presentations/lib/pptviewlib/), a plugin developed for OpenLP to enable it to display powerpoint presentations.

The released jar contains pptviewlib.dll; it has a minor modification to guard against passing `null` as a parameter to preview path.

## Setup

### Maven
    <dependency>
      <groupId>org.quelea.jpptviewlib</groupId>
      <artifactId>jpptviewlib</artifactId>
      <version>1.0</version>
    </dependency>

### Gradle
    compile 'org.quelea.jpptviewlib:jpptviewlib:1.0'

## Usage

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
