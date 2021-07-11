package com.samuca;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception{

        DatagramSocket clientSocket = new DatagramSocket(); //SO ajusta uma porta para vc

        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");

        //declaração do buffer de recebimento (caso haja)
        byte[] sendData = new byte[1024];
        sendData = "sou um cliente".getBytes();

        //envio
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

        //envio do datagrama ao host remoto
        clientSocket.send(sendPacket);

        System.out.println("mensagem enviada para o servidor");

        byte[] recBuffer = new byte[1024];

        //recebimento
        DatagramPacket recPkt = new DatagramPacket(recBuffer, recBuffer.length);

        //bloqueante
        clientSocket.receive(recPkt);

        String informacao = new String(recPkt.getData(),
                recPkt.getOffset(),//importante -> google: datagrampacket getoffset api
                recPkt.getLength());

        System.out.println("recebido do servidor: " + informacao);

        clientSocket.close();
    }
}
