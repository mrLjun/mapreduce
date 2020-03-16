package com.hadoop.demo.mapreduce.job.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author liujun
 */
public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String item = value.toString();
        String[] items = item.split(" ");
        if (items.length >= 1) {
            for (String s : items) {
                context.write(new Text(s), new IntWritable(1));
            }
        }
    }
}
