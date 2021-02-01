package com.dj.system.model.monitor;

/**
 * @author wxl
 * @date 2020-03-18 下午4:30
 * @company www.dfdk.com.cn
 */
public class ProcesseEntity {

    private int pid;
    private double perCpu;
    private double perMen;
    private String vsz;
    private String rss;
    private String name;

    public ProcesseEntity() {
    }

    public ProcesseEntity(int pid, double perCpu, double perMen, String vsz, String rss, String name) {
        this.pid = pid;
        this.perCpu = perCpu;
        this.perMen = perMen;
        this.vsz = vsz;
        this.rss = rss;
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getPerCpu() {
        return perCpu;
    }

    public void setPerCpu(double perCpu) {
        this.perCpu = perCpu;
    }

    public double getPerMen() {
        return perMen;
    }

    public void setPerMen(double perMen) {
        this.perMen = perMen;
    }

    public String getVsz() {
        return vsz;
    }

    public void setVsz(String vsz) {
        this.vsz = vsz;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProcesseEntity{" +
                "pid=" + pid +
                ", perCpu=" + perCpu +
                ", perMen=" + perMen +
                ", vsz='" + vsz + '\'' +
                ", rss='" + rss + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
