package com.dj.system.model.monitor;

/**
 * @author wxl
 * @date 2020-03-18 下午4:12
 * @company www.dfdk.com.cn
 */
public class PowerEntity {

    private String name;
    private String deviceName;
    private double remainingCapacityPercent;
    private boolean powerOnLine;
    private double temperature;
    private double powerUsageRate;

    public PowerEntity() {
    }

    public PowerEntity(String name, String deviceName, double remainingCapacityPercent, boolean powerOnLine, double temperature, double powerUsageRate) {
        this.name = name;
        this.deviceName = deviceName;
        this.remainingCapacityPercent = remainingCapacityPercent;
        this.powerOnLine = powerOnLine;
        this.temperature = temperature;
        this.powerUsageRate = powerUsageRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public double getRemainingCapacityPercent() {
        return remainingCapacityPercent;
    }

    public void setRemainingCapacityPercent(double remainingCapacityPercent) {
        this.remainingCapacityPercent = remainingCapacityPercent;
    }

    public boolean isPowerOnLine() {
        return powerOnLine;
    }

    public void setPowerOnLine(boolean powerOnLine) {
        this.powerOnLine = powerOnLine;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPowerUsageRate() {
        return powerUsageRate;
    }

    public void setPowerUsageRate(double powerUsageRate) {
        this.powerUsageRate = powerUsageRate;
    }

    @Override
    public String toString() {
        return "PowerEntity{" +
                "name='" + name + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", remainingCapacityPercent=" + remainingCapacityPercent +
                ", powerOnLine=" + powerOnLine +
                ", temperature=" + temperature +
                ", powerUsageRate=" + powerUsageRate +
                '}';
    }
}
