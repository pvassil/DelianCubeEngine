/*
* The entire code is copied (with minor modifications) from a posting at
*   https://www.censhare.com/uk/blog/article/file-streaming-using-java-rmi
* by Walter Bauer.
* Thank you Walter!!
 */

package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import mainengine.IServer;

public class ClientRMITransferer {
    final public static int BUF_SIZE = 1024 * 64;
    

    
    public static void upload(IServer server, File src, 
            File dest) throws IOException {
        copy (new FileInputStream(src), server.getOutputStream(dest));
    }

    public static void download(IServer server, File src, 
            File dest) throws IOException {
        copy (server.getInputStream(src), new FileOutputStream(dest));
    }

	//without RMIPipe
    public static void copy(InputStream in, OutputStream out) 
            throws IOException {
        System.out.println("using byte[] read/write");
        byte[] b = new byte[BUF_SIZE];
        int len;
        while ((len = in.read(b)) >= 0) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
    }

    
//    //TODO: fix crashes
//    public static void copyWPipes(InputStream in, OutputStream out)
//            throws IOException {
//        
//        if (in instanceof RMIInputStream) {
//            System.out.println("using RMIPipe of RMIInputStream");
//            ((RMIInputStream) in).transfer(out);
//            return;
//        }
//        
//        if (out instanceof RMIOutputStream) {
//            System.out.println("using RMIPipe of RMIOutputStream");
//            ((RMIOutputStream) out).transfer(in);
//            return;
//        }
//
//        System.out.println("using byte[] read/write");
//        byte[] b = new byte[BUF_SIZE];
//        int len;
//        while ((len = in.read(b)) >= 0) {
//            out.write(b, 0, len);
//        }
//        in.close();
//        out.close();
//    }
    
    
    
}
