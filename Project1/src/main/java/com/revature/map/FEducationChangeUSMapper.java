package com.revature.map;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FEducationChangeUSMapper extends Mapper<LongWritable, Text, Text, Text>{

	String eduAttain = "Educational attainment, at least";
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String line = value.toString();
		
		if (line.contains("United States") && line.contains(eduAttain) && line.contains("female")) {
			String[] columns = line.substring(1,line.length()-2).split("\",\"", -1);
			String category = columns[2];
			int integer = 0;
			for (int i = 44; i < columns.length; i++) {
				String doubleStr = columns[i].trim();
				
				if (doubleStr.length() > 0) {
					String indexVal = integer <= 9 ? integer + " " : Integer.toString(integer);
					
					context.write(new Text(category + ": "), new Text(indexVal + doubleStr.toString()));
					integer++;	
				}
			}
		}
		
	}
}
