package com.king.urban.file.upload;

import com.github.tobato.fastdfs.service.FastFileStorageClient;

/**
 * 文件上传服务
 *
 * @author liukai
 */
public class FileUploadClient {


    private FastFileStorageClient storageClient;

    /**
     * 上传文件到fastdfs
     */
//    public String uploadFileToFastDFS(InputStream inputStream,) throws IOException {
//        String originalFilename = file.getOriginalFilename();
//        String extension = FileUtil.getSuffix(originalFilename);
//        StorePath storePath = storageClient.uploadFile(inputStream, file.getSize(), extension, null);
//        return storePath.getFullPath();
//    }


    /**
     * 上传文件到服务器
     */
//    public String uploadFileToLocal(MultipartFile file) throws IOException {
//        String tmpDirPath = FileUtil.getTmpDirPath();
//        String path = tmpDirPath + file.getOriginalFilename();
//        File dest = new File(path);
//        file.transferTo(dest);
//        return path;
//    }

}
