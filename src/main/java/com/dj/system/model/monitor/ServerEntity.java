package com.dj.system.model.monitor;

import com.dj.system.utils.Arith;
import com.dj.system.utils.IpUtils;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.hardware.CentralProcessor.TickType;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author wxl
 * @date 2020-03-18 下午2:15
 * @company www.dfdk.com.cn
 */
public class ServerEntity implements Serializable {

    private static final int OSHI_WAIT_SECOND = 1000;
    private static final long serialVersionUID = -7437814692244334067L;


    /**
     * CPU相关信息
     */
    private CpuEntity cpu = new CpuEntity();

    /**
     * 內存相关信息
     */
    private MemEntity mem = new MemEntity();

    /**
     * JVM相关信息
     */
    private JvmEntity jvm = new JvmEntity();

    /**
     * 服务器相关信息
     */
    private SysEntity sys = new SysEntity();

    /**
     * 磁盘相关信息
     */
    private List<SysFileEntity> sysFiles = new LinkedList<SysFileEntity>();

    private List<InterfaceEntity> interfaceEntityList = new ArrayList<>();

    private List<PowerEntity> powerEntityList = new ArrayList<>();

    List<ProcesseEntity> processeEntityList = new ArrayList<>();

    public CpuEntity getCpu() {
        return cpu;
    }

    public void setCpu(CpuEntity cpu) {
        this.cpu = cpu;
    }

    public MemEntity getMem() {
        return mem;
    }

    public void setMem(MemEntity mem) {
        this.mem = mem;
    }

    public JvmEntity getJvm() {
        return jvm;
    }

    public void setJvm(JvmEntity jvm) {
        this.jvm = jvm;
    }

    public SysEntity getSys() {
        return sys;
    }

    public void setSys(SysEntity sys) {
        this.sys = sys;
    }

    public List<SysFileEntity> getSysFiles() {
        return sysFiles;
    }

    public void setSysFiles(List<SysFileEntity> sysFiles) {
        this.sysFiles = sysFiles;
    }

    public List<InterfaceEntity> getInterfaceEntityList() {
        return interfaceEntityList;
    }

    public void setInterfaceEntityList(List<InterfaceEntity> interfaceEntityList) {
        this.interfaceEntityList = interfaceEntityList;
    }

    public List<PowerEntity> getPowerEntityList() {
        return powerEntityList;
    }

    public void setPowerEntityList(List<PowerEntity> powerEntityList) {
        this.powerEntityList = powerEntityList;
    }

    public List<ProcesseEntity> getProcesseEntityList() {
        return processeEntityList;
    }

    public void copyTo() throws Exception
    {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        setCpuInfo(hal.getProcessor());
        setMemInfo(hal.getMemory());
        setSysInfo();
        setJvmInfo();
        setSysFiles(si.getOperatingSystem());
        setSysNetworkInterfaces(hal.getNetworkIFs());
        setPowerSources(hal.getPowerSources());
        OperatingSystem os = si.getOperatingSystem();
        setProcesses(os, hal.getMemory());
    }

    private void setProcesses(OperatingSystem os, GlobalMemory memory) {
        List<OSProcess> procs = Arrays.asList(os.getProcesses(5, OperatingSystem.ProcessSort.CPU));
        for (int i = 0; i < procs.size() && i < 5; i++) {
            OSProcess p = procs.get(i);
            ProcesseEntity entity = new ProcesseEntity(
                    p.getProcessID(),
                    100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
                    100d * p.getResidentSetSize() / memory.getTotal(),
                    FormatUtil.formatBytes(p.getVirtualSize()),
                    FormatUtil.formatBytes(p.getResidentSetSize()), p.getName()
            );
            processeEntityList.add(entity);
        }
    }

    private  void setPowerSources(PowerSource[] powerSources) {
        if (powerSources.length != 0) {
            for (PowerSource powerSource : powerSources) {
                PowerEntity entity = new PowerEntity(
                        powerSource.getName(),
                        powerSource.getDeviceName(),
                        powerSource.getRemainingCapacityPercent(),
                        powerSource.isPowerOnLine(),
                        powerSource.getTemperature(),
                        powerSource.getPowerUsageRate()
                );
                powerEntityList.add(entity);
            }
        }
    }

    private void setSysNetworkInterfaces(NetworkIF[] networkIFs) {

        if (networkIFs.length != 0) {
            for (NetworkIF net : networkIFs) {
                InterfaceEntity entity = new InterfaceEntity(
                        net.getName(),
                        net.getMacaddr(),
                        net.getSpeed(),
                        net.getBytesRecv(),
                        net.getBytesSent(),
                        net.getPacketsRecv(),
                        net.getPacketsSent(),
                        net.getInErrors(),
                        net.getOutErrors(),
                        net.getInDrops()
                );
                interfaceEntityList.add(entity);
            }
        }
    }

    /**
     * 设置CPU信息
     */
    private void setCpuInfo(CentralProcessor processor)
    {
        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        cpu.setCpuNum(processor.getLogicalProcessorCount());
        cpu.setTotal(totalCpu);
        cpu.setSys(cSys);
        cpu.setUsed(user);
        cpu.setWait(iowait);
        cpu.setFree(idle);
    }

    /**
     * 设置内存信息
     */
    private void setMemInfo(GlobalMemory memory)
    {
        mem.setTotal(memory.getTotal());
        mem.setUsed(memory.getTotal() - memory.getAvailable());
        mem.setFree(memory.getAvailable());
    }

    /**
     * 设置服务器信息
     */
    private void setSysInfo()
    {
        Properties props = System.getProperties();
        sys.setComputerName(IpUtils.getHostName());
        sys.setComputerIp(IpUtils.getHostIp());
        sys.setOsName(props.getProperty("os.name"));
        sys.setOsArch(props.getProperty("os.arch"));
        sys.setUserDir(props.getProperty("user.dir"));
    }

    /**
     * 设置Java虚拟机
     */
    private void setJvmInfo() throws UnknownHostException
    {
        Properties props = System.getProperties();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
    }

    /**
     * 设置磁盘信息
     */
    private void setSysFiles(OperatingSystem os)
    {
        FileSystem fileSystem = os.getFileSystem();
        OSFileStore[] fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray)
        {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            SysFileEntity sysFile = new SysFileEntity();
            sysFile.setDirName(fs.getMount());
            sysFile.setSysTypeName(fs.getType());
            sysFile.setTypeName(fs.getName());
            sysFile.setTotal(convertFileSize(total));
            sysFile.setFree(convertFileSize(free));
            sysFile.setUsed(convertFileSize(used));
            sysFile.setUsage(Arith.mul(Arith.div(used, total, 4), 100));
            sysFiles.add(sysFile);
        }
    }

    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public String convertFileSize(long size)
    {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb)
        {
            return String.format("%.1f GB", (float) size / gb);
        }
        else if (size >= mb)
        {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        }
        else if (size >= kb)
        {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        }
        else
        {
            return String.format("%d B", size);
        }
    }

}
