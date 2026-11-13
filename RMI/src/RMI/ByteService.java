package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** Remote Interface dùng cho bài 5 và bài 6. */
public interface ByteService extends Remote {
    byte[] requestData(String studentCode, String qCode)
            throws RemoteException;

    void submitData(String studentCode, String qCode, byte[] data)
            throws RemoteException;
}
