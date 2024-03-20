package br.edu.ifpb.gugawag.so.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        System.out.println("-------------- Servidor NFS --------------");

        ServerSocket serverSocket = new ServerSocket(7001);
        Socket socket = serverSocket.accept();

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        FileSystemMediator FLmediator = new FileSystemMediator();

        while (true) {
            System.out.println("Aguardando ação do cliente...");

            String mensagem = dis.readUTF();

            String userAction = FLmediator.mediator(mensagem);

            dos.writeUTF(userAction);
        }
    }
}
