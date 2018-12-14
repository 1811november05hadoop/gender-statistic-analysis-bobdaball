package com.revature.map;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FEducation30LessMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	String edu30Minus =  "Gross graduation ratio";

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
					throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
		String line = value.toString();	
		
		if (line.contains(edu30Minus) && line.contains("female")) {
			String[] columns = line.substring(1,line.length()-2).split("\",\"", -1);
			String doubleStr;
			int year = -1;
			double graduationRate = -1;
			
			for (int i = 4; i < columns.length; i++) {
				doubleStr = columns[i];
				
				if (doubleStr.length() > 0) {
					year = i + 1954;
					graduationRate = Double.parseDouble(doubleStr);
					
				}
			}
			if (year > 0 && graduationRate > -1 && graduationRate < 30) {
				graduationRate = Math.round(graduationRate * (double)100) / (double) 100;
				context.write(new Text("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + "(" + columns[0] + ")  |" + columns[2] + "| graduation rate: " + graduationRate +"% |"), new IntWritable(year));	
			}
		}
	}
}
