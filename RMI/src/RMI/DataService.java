package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** Remote Interface dùng cho bài 1 và bài 2. */
public interface DataService extends Remote {
    Object requestData(String studentCode, String qCode) throws RemoteException;

    void submitData(String studentCode, String qCode, Object data)
            throws RemoteException;
}
