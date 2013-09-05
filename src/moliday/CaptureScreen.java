package moliday;

/*Powered by Moliday
 * 
 * 
 * */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class CaptureScreen extends JFrame implements ClipboardOwner {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage subimage;
	private ScreenShot ss;
	CLIPBOARD clipboard = new CLIPBOARD();

	public CaptureScreen() {
	}

	public CaptureScreen(ScreenShot ss) {
		this.ss = ss;
	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {

	}

	public void screenShot(IFrame iFrame) {
		try {
			Robot ro = new Robot();
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension di = tk.getScreenSize();
			int width = iFrame.getWidth();
			int height = iFrame.getHeight();
			if (width == 0) {
				width = di.width;
			}
			if (height == 0) {
				height = di.height;
			}
			Rectangle rec = new Rectangle(0, 0, di.width, di.height);
			BufferedImage bi = ro.createScreenCapture(rec);
			subimage = bi.getSubimage(iFrame.getX(), iFrame.getY(), width-18,
					height);
			pressImage(subimage);
			JFrame jf = new JFrame();
			jf.setUndecorated(true);
			jf.setSize(di);
			jf.setVisible(false);
			jf.setDefaultCloseOperation(HIDE_ON_CLOSE);
		} catch (Exception e) {
			System.out.println("screenShot:" + e.toString());
		}
	}

	private void pressImage(BufferedImage subimage) throws IOException,
			URISyntaxException {
		Graphics g = subimage.createGraphics();
		g.drawImage(subimage, 0, 0, subimage.getWidth(), subimage.getHeight(),
				null);
		InputStream is = this.getClass().getResourceAsStream(
				"moliday_xs.png");
		Image src_biao = ImageIO.read(is);
		int wideth_biao = src_biao.getWidth(null);
		int height_biao = src_biao.getHeight(null);
		g.drawImage(src_biao, (subimage.getWidth() - wideth_biao-8), (subimage
				.getHeight() - height_biao-7), wideth_biao, height_biao, null);
		g.dispose();
		
	}

	public void doSave(String filePath) throws NullPointerException {
		try {
			File file = new File(filePath);
			String about = "jpg";
			String ext = file.toString().toLowerCase();
			ImageIO.write(subimage, about, file);
			CLIPBOARD.setImageClipboard(subimage);
			ss.screenEnd(ext);
		} catch (Exception e) {
			System.out.println("doSave:" + e.toString());
		}
	}
	
	public void saveImage() {
		FileDialog dialog = new FileDialog(new Shell(),SWT.SAVE);
		dialog.setFilterExtensions(new String[]{"*.jpg","*.*"});
		String filePath = dialog.open();
		if(null == filePath){
			return;
		}
		System.out.println(filePath);
		doSave(filePath);
	}

	public static void main(String[] args) {
		new CaptureScreen().saveImage();
	}
}
