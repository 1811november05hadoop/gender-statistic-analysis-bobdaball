package com.revature.map;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FEducationChangeUSMapper extends Mapper<LongWritable, Text, Text, DoubleWritable>{

	String eduAttain = "Educational attainment, at least";
	/**
	 * Upon receiving relevant data, it prepends the index value as a string for the value.
	 * 
	 */
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String line = value.toString();
		
		if (line.contains("United States") && line.contains(eduAttain) && line.contains("female")) {
			String[] columns = line.substring(1,line.length()-2).split("\",\"", -1);
			String category = columns[2];
			double prevVal = -1;
			double newVal = -1;
			double increase;
			
			for (int i = 44; i < columns.length; i++) {
				String doubleStr = columns[i].trim();
				
				if (doubleStr.length() > 0) {
					newVal = Double.parseDouble(doubleStr);
					if (prevVal != -1) {
						increase = newVal - prevVal;
						context.write(new Text(category), new DoubleWritable(increase));
					}
					prevVal = newVal;
				}
				
			}
		}
		
	}
}
