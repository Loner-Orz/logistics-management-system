package com.ruoyi.common.utils;

public class SnowflakeIdGenerator {
    private static final long START_EPOCH = 1614556800000L; // 2021-03-01 00:00:00
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 12L;
    private static final long MAX_WORKER_ID = ~(Long.MAX_VALUE << WORKER_ID_BITS);
    private static final long MAX_DATACENTER_ID = ~(Long.MAX_VALUE << DATACENTER_ID_BITS);

    private final long workerId;
    private final long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0)
            throw new IllegalArgumentException(String.format("Worker ID cannot be greater than %d or less than 0", MAX_WORKER_ID));
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0)
            throw new IllegalArgumentException(String.format("Datacenter ID cannot be greater than %d or less than 0", MAX_DATACENTER_ID));
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();
        if (currentTimestamp < lastTimestamp)
            throw new RuntimeException("Clock moved backwards. Refusing to generate id.");
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & ((1L << SEQUENCE_BITS) - 1);
            if (sequence == 0) {
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - START_EPOCH) << (DATACENTER_ID_BITS + WORKER_ID_BITS + SEQUENCE_BITS)) |
                (datacenterId << (WORKER_ID_BITS + SEQUENCE_BITS)) |
                (workerId << SEQUENCE_BITS) |
                sequence;
    }

    private long waitNextMillis(long currentTimestamp) {
        long nextTimestamp = System.currentTimeMillis();
        while (nextTimestamp <= currentTimestamp) {
            nextTimestamp = System.currentTimeMillis();
        }
        return nextTimestamp;
    }
}

