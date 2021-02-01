package com.dj.system.model.monitor;

/**
 * @author wxl
 * @date 2020-03-18 下午3:27
 * @company www.dfdk.com.cn
 */
public class InterfaceEntity {

    private String name;
    private String macAddr;
    private long speed;
    private long bytesRecv;
    private long bytesSent;
    private long packetsRecv;
    private long packetsSent;
    private long inErrors;
    private long outErrors;
    private long inDrops;

    public InterfaceEntity() {
    }

    public InterfaceEntity(String name, String macAddr, Long speed, long bytesRecv, long bytesSent, long packetsRecv, long packetsSent, long inErrors, long outErrors, long inDrops) {
        this.name = name;
        this.macAddr = macAddr;
        this.speed = speed;
        this.bytesRecv = bytesRecv;
        this.bytesSent = bytesSent;
        this.packetsRecv = packetsRecv;
        this.packetsSent = packetsSent;
        this.inErrors = inErrors;
        this.outErrors = outErrors;
        this.inDrops = inDrops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public long getBytesRecv() {
        return bytesRecv;
    }

    public void setBytesRecv(long bytesRecv) {
        this.bytesRecv = bytesRecv;
    }

    public long getBytesSent() {
        return bytesSent;
    }

    public void setBytesSent(long bytesSent) {
        this.bytesSent = bytesSent;
    }

    public long getPacketsRecv() {
        return packetsRecv;
    }

    public void setPacketsRecv(long packetsRecv) {
        this.packetsRecv = packetsRecv;
    }

    public long getPacketsSent() {
        return packetsSent;
    }

    public void setPacketsSent(long packetsSent) {
        this.packetsSent = packetsSent;
    }

    public long getInErrors() {
        return inErrors;
    }

    public void setInErrors(long inErrors) {
        this.inErrors = inErrors;
    }

    public long getOutErrors() {
        return outErrors;
    }

    public void setOutErrors(long outErrors) {
        this.outErrors = outErrors;
    }

    public long getInDrops() {
        return inDrops;
    }

    public void setInDrops(long inDrops) {
        this.inDrops = inDrops;
    }

    @Override
    public String toString() {
        return "InterfaceEntity{" +
                "name='" + name + '\'' +
                ", macAddr='" + macAddr + '\'' +
                ", speed=" + speed +
                ", bytesRecv=" + bytesRecv +
                ", bytesSent=" + bytesSent +
                ", packetsRecv=" + packetsRecv +
                ", packetsSent=" + packetsSent +
                ", inErrors=" + inErrors +
                ", outErrors=" + outErrors +
                ", inDrops=" + inDrops +
                '}';
    }
}
