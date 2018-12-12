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
		
		if (line.contains(eduAttain) && line.contains("female") && !line.contains(cumu) && line.contains("United States") && !line.contains("no education")) {
			String[] columns = line.substring(1,line.length()-2).split("\",\"", -1);
			String category = columns[2];
			for (int i = 44; i < columns.length; i++) {
				String doubleStr = columns[i].trim();
				
				if (doubleStr.length() > 0) {
					
					context.write(new Text(category + ": "), new DoubleWritable(Double.parseDouble(doubleStr)));
				}
			}
		}
		
	}
}
