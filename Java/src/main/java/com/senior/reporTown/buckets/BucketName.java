package com.senior.reporTown.buckets;

public enum BucketName {

    //define enum with specific bucket name "private final String bucketName"
    REPORT_IMAGE("reportown-senior");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
