package com.revature.map;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FEducationChangeUSMapper extends Mapper<LongWritable, Text, Text, DoubleWritable>{

	String eduAttain = "Educational attainment";
	String cumu = "cumulative";
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String line = value.toString();
		
		if (line.contains(eduAttain) && line.contains("female") && !line.contains(cumu) && line.contains("United States")) {
			String[] columns = line.split("\",\"", -1);
			String category = columns[2];
			for (int i = 44; i < columns.length; i++) {
				String doubleStr = columns[i].trim();
				
				if (doubleStr.length() > 0 && !doubleStr.contains(",")) {
					
					context.write(new Text(category + ": "), new DoubleWritable(Double.parseDouble(doubleStr)));
				}
			}
		}
		
	}
}
