package com.revature.map;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FEducation30LessMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	String edu30Minus =  "Gross graduation ratio, tertiary, female (%)";

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
					throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
		String line = value.toString();	
		
		if (line.contains(edu30Minus)) {
			String[] columns = line.split("\",\"", -1);
			
			for (int i = 4; i < columns.length; i++) {
				String doubleStr = columns[i];
				if (doubleStr.length() > 0) {
					context.write(new Text(columns[0]), new IntWritable(i + 1954));
				}
			}
		}
	}
}
