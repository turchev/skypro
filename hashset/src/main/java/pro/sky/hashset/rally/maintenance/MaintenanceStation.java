package pro.sky.hashset.rally.maintenance;

import pro.sky.hashset.rally.transport.Transport;

import java.util.ArrayDeque;
import java.util.Queue;

public class MaintenanceStation {
    private final Queue<Transport> serviceQueue = new ArrayDeque<>();

    public boolean addToInspectionQueue(Transport transport) {
        return serviceQueue.offer(transport);
    }

    public boolean carryDiagnostics() {
        Transport transport = serviceQueue.poll();
        if (transport == null) {
            return false;
        }
        try {
            transport.passDiagnostics();
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public int getQueueSize() {
        return serviceQueue.size();
    }
}
