package br.edu.ifpb.gugawag.so.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        System.out.println("-------------- Cliente NFS --------------");
        System.out.println("\nComandos: \n --> readdir \n --> rename \n --> remove \n --> create\n");

        Socket socket = new Socket("127.0.0.1", 7001);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        while (true) {
            System.out.print(">> ");
            Scanner teclado = new Scanner(System.in);
            dos.writeUTF(teclado.nextLine());
            String mensagem = dis.readUTF();
            System.out.println(mensagem);
        }
    }
}
