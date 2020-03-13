/*
* The entire code is copied (with minor modifications) from a posting at
*   https://www.censhare.com/uk/blog/article/file-streaming-using-java-rmi
* by Walter Bauer.
* Thank you Walter!!
 */
   package mainengine.rmiTransfer;

import java.io.IOException;
import java.io.InputStream;
//import java.io.OutputStream;
import java.io.Serializable;

public class RMIInputStream extends InputStream implements Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	IRMIInputStream in;

	public RMIInputStream(IRMIInputStream in) {
		this.in = in;
	}

	public int read() throws IOException {
		return in.read();
	}

	public int read(byte[] b, int off, int len) throws IOException {
		byte[] b2 = in.readBytes(len);
		if (b2 == null)
			return -1;
		int i = b2.length;
		System.arraycopy(b2, 0, b, off, i);
		return i;
	}

	public void close() throws IOException {
		super.close();
	}

//	public void transfer(OutputStream out) throws IOException {
//	    RMIPipe pipe = new RMIPipe(out);
//	    in.transfer(pipe.getKey());
//	}
}//end class