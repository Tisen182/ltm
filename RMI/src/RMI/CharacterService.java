package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** Remote Interface dùng cho bài 3 và bài 4. */
public interface CharacterService extends Remote {
    String requestCharacter(String studentCode, String qCode)
            throws RemoteException;

    void submitCharacter(String studentCode, String qCode, String strSubmit)
            throws RemoteException;
}
