package moliday;

/*Powered by Moliday
 * 
 * 
 * */

import java.applet.Applet;
import netscape.javascript.JSObject;

public class ScreenShot extends Applet {
	private static final long serialVersionUID = 1L;
	IFrame iFrame = new IFrame();
	private CaptureScreen cs;
	public static ScreenShot ss;
	public static JSObject window = null;

	public void cancel() {
		System.exit(0);
	}

	public void StartScreenShot(int x, int y, int width, int height) {
		iFrame.setX(x);
		iFrame.setY(y);
		iFrame.setWidth(width);
		iFrame.setHeight(height);
		takePhoto();
	}
	
	public boolean setPath(String filePath) {
		return false;
	}

	public void takePhoto() {
		System.out.println("init");
		ss = new ScreenShot();
		window = JSObject.getWindow(this);
		cs = new CaptureScreen(ss);
		exeScreen();
	}
	
	public void exeScreen() {
		if (window == null) {
			window = JSObject.getWindow(this);
			
		} else {
			screenShot();
		}
	}

	private void screenShot() {
		if (cs == null) {
			cs = new CaptureScreen(ss);
		}
		cs.screenShot(iFrame);
		if (window == null) {
			window = JSObject.getWindow(this);
		}
		cs.saveImage();
	}
	
	public void screenEnd(String path) {
		if (window == null) {
			window = JSObject.getWindow(this);
		}
		window.call("response", new Object[] { path });
	}

}
