package com.samuca;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    public static void main(String[] args) throws Exception{

        //levanta o server na porta 9876
        DatagramSocket serverSocket = new DatagramSocket(9876);

        while(true){
            byte[] recBuffer = new byte[1024];
            DatagramPacket recPkt = new DatagramPacket(recBuffer, recBuffer.length);

            System.out.println("esperando alguma mensagem");
            serverSocket.receive(recPkt);//blocking //questionamento: será necessário criar threads para ouvir outros clientes?? isso ajudar no projeto

            byte[] sendBuf = new byte[1024];
            sendBuf = "sou um servidor".getBytes();

            //como o servidor sabe o ip e porta do cliente? uso os métodos abaixo:
            InetAddress IPAddress = recPkt.getAddress();
            int port = recPkt.getPort();

            DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, IPAddress, port);
            serverSocket.send(sendPacket);
            System.out.println("mensagem enviada par ao client");
        }
    }

}
