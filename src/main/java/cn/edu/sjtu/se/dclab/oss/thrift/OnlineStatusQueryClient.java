package cn.edu.sjtu.se.dclab.oss.thrift;

import cn.edu.sjtu.se.dclab.service_management.ServiceManager;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;

import java.util.List;
import java.util.Random;

/**
 * Created by francis on 6/9/15.
 */
public class OnlineStatusQueryClient {

    private String ip;
    private int port;
    private String nodeName = "/online-status-server";

    public OnlineStatusQueryClient() throws Exception {
        ServiceManager manager = ServiceManager.getInstance();
        List<String> contents = manager.retrieve(nodeName);
        if (contents.size() == 0) {
            throw new Exception("Cannot start client. no server found.");
        }

        String content = contents.get(new Random().nextInt(contents.size()));
        String[] data = content.split(":");
        if (data.length < 2) {
            throw new Exception("Content format error: " + content);
        }

        ip = data[0];
        port = Integer.parseInt(data[1]);
    }

    public String checkOnline(String userId) {
        TSocket socket = null;
        String result = "[]";
        try {
            socket = new TSocket(ip, port);
            socket.open();
            TProtocol protocol = new TBinaryProtocol(socket);
            OnlineStatusQueryService.Client client = new OnlineStatusQueryService.Client(protocol);

            result = client.checkOnline(userId);
            System.out.println("Return from server: " + result);
            return result;
        } catch (TException ex) {
            ex.printStackTrace();
        } finally {
            socket.close();
            socket = null;
        }
        return result;
    }
}
