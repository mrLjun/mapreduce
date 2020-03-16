package com.hadoop.demo.mapreduce.job.FileUpload;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class UploadFile {

    public String upload() throws IOException {
        Configuration conf = new Configuration();
        //本地路径
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path("/Users/liujun/Documents/javaPro/mapreduce/src/main/java/com/hadoop/demo/mapreduce/job/FileUpload/word");
        // 这个是你hdfs下的路径
        Path dstPath = new Path("/mytext");
        //上传文件
        fs.copyFromLocalFile(srcPath, dstPath);
        //检测文件是否存在
        String filename = "hdfs://192.168.0.2:9000/mytext/word";
        if (fs.exists(new Path(filename))) {
            // 打开上传的文件 并且输出里面的内容
//            FSDataInputStream out = fs.open(new Path(filename));
//            IOUtils.copyBytes(out, System.out, 1024, true);
            return filename;
        } else {
            return null;
        }

    }
}
