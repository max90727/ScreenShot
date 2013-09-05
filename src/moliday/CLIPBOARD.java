package moliday;

/*Powered by Moliday
 * 
 * 
 * */

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


class CLIPBOARD {

	public CLIPBOARD() {
		// TODO Auto-generated constructor stub
	}

	public static String getStringClipboard()
			throws UnsupportedFlavorException, IOException {
		Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
				.getContents(null);
		if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			String text = (String) t.getTransferData(DataFlavor.stringFlavor);
			return text;
		}
		return null;
	}

	public static void setStringClipboard(String str) {
		StringSelection ss = new StringSelection(str);
		Toolkit.getDefaultToolkit().getSystemClipboard().getContents(ss);
	}

	public static void setImageClipboard(Image image) {
		ImageSelection imgSel = new ImageSelection(image);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgSel,
				null);
		//Toolkit.getDefaultToolkit().getSystemClipboard().getContents(imgSel);
	}
}
