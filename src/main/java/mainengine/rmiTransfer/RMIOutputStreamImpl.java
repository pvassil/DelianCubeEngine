/*
* The entire code is copied (with minor modifications) from a posting at
*   https://www.censhare.com/uk/blog/article/file-streaming-using-java-rmi
* by Walter Bauer.
* Thank you Walter!!
 */
   package mainengine.rmiTransfer;

import java.io.IOException;
import java.io.OutputStream;
import java.rmi.server.UnicastRemoteObject;

public class RMIOutputStreamImpl implements IRMIOutputStream {
	private RMIPipe pipe;
    private OutputStream out;
    

    
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

    
    
    public RMIOutputStreamImpl(OutputStream out) throws 
            IOException {
        this.out = out;
        this.pipe = new RMIPipe(out);
        UnicastRemoteObject.exportObject(this, 1099);
    }

    public int getPipeKey() {
        return pipe.getKey();
    }
      
    public void transfer(RMIPipe pipe) {
        // nothing more to do here
        // pipe has been serialized and that's all we want
    }
    
}//end class