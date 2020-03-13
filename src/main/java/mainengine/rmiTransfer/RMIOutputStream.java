/*
* The entire code is copied (with minor modifications) from a posting at
*   https://www.censhare.com/uk/blog/article/file-streaming-using-java-rmi
* by Walter Bauer.
* Thank you Walter!!
 */
   package mainengine.rmiTransfer;

import java.io.IOException;
import java.io.OutputStream;
//import java.io.InputStream;
import java.io.Serializable;

public class RMIOutputStream extends OutputStream implements Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 2L;
	private IRMIOutputStream out;

	public RMIOutputStream(RMIOutputStreamImpl out) {
		this.out = (IRMIOutputStream) out;
	}

	public void write(int b) throws IOException {
		out.write(b);
	}

	public void write(byte[] b, int off, int len) throws 
	IOException {
		out.write(b, off, len);
	}

	public void close() throws IOException {
		out.close();
	}   

//	private int pipeKey;
//	public void transfer(InputStream in) throws IOException {
//		RMIPipe pipe = new RMIPipe(pipeKey, in);
//		out.transfer(pipe);
//	}

}//end class