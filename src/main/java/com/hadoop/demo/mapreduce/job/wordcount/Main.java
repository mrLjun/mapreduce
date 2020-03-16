package com.hadoop.demo.mapreduce.job.wordcount;


import com.hadoop.demo.mapreduce.job.FileUpload.UploadFile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Properties;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        Properties properties = System.getProperties();
        properties.setProperty("HADOOP_USER_NAME", "root");
        Configuration configuration = new Configuration(true);
        Job job = Job.getInstance(configuration, "word count demo");

//        configuration.set("mapreduce.job.jar", "/Users/liujun/Documents/javaPro/mapreduce/out/artifacts/mapreduce_jar/mapreduce.jar");

        // 放到nameNode下执行时要把上面一句改成下面这句！！不然找不到target文件夹，会报错。
        job.setJarByClass(Main.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);



        //job 的相关方法api http://hadoop.apache.org/docs/current/api/org/apache/hadoop/mapreduce/Job.html

        UploadFile uploadFile = new UploadFile();
        String path = uploadFile.upload();
        if (path != null) {

            FileInputFormat.addInputPath(job, new Path(path));
            FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.0.2:9000/mytest/word_count2"));

            System.exit(job.waitForCompletion(true) ? 0 : 1);
        }

    }


}
