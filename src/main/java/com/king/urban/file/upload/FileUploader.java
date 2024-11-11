package com.king.urban.file.upload;

import io.minio.*;
import io.minio.errors.MinioException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient = MinioClient.builder()
                    .endpoint("106.13.64.83", 9000, false)
                    .credentials("X0bNIWpmhGGJPlA3xw69", "PpxEfnDHtCGAU38rrMACS2RY62h7AWuOt3a6btmb")
                    .build();

            String filename = "QQ20230925-140501.png";
            String filePath = "/Users/liukai/Pictures" + "/" + filename;

            ObjectWriteResponse objectWriteResponse = minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("urban")
                            .object(filename)
                            .filename(filePath)
                            .build()
            );

            System.out.println(objectWriteResponse);

            GetObjectResponse getObjectResponse = minioClient.getObject(GetObjectArgs.builder().bucket("urban").object(filename).build());
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}
