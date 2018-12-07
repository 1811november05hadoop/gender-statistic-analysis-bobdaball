package com.revature.map;

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FEmploymentMapper extends Mapper<LongWritable, Text, Text, ArrayWritable> {
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, ArrayWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		if (key.get() == 28716) {
			
		} else if (key.get() == 28718) {
			
		}
		
		super.map(key, value, context);
	}
}
