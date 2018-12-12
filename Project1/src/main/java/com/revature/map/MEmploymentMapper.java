package com.revature.map;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MEmploymentMapper extends Mapper<LongWritable, Text, Text, Text >{
	String criterion = "Employment to population ratio, 15+, male (%) (modeled ILO estimate)";
	double numOne = -1;
	double numTwo = -1;
	@Override
	protected void map(
			LongWritable key,
			Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		boolean isCountry = false;
		
		if (!isCountry) {
			if (line.contains("Afghanistan")) {
				isCountry = true;
			} 
		} 
		
		if (isCountry) {
			if (line.contains(criterion)) {
				String[] columns = line.substring(1,line.length()-2).split("\",\"", -1);
				
				for (int i = 44; i < columns.length; i++) {
					String doubleStr = columns[i];
					
					if (doubleStr.length() > 0 && !doubleStr.contains(",")) {
						if (numOne < 0) {
							numOne = Double.parseDouble(doubleStr);
						} else {
							numTwo = Double.parseDouble(doubleStr);
						}
					}
				}
				if (numOne >= 0 && numTwo >= 0) {
					context.write(new Text(columns[0]), new Text(numOne + " " + numTwo));
				}
			}
		}
	}
}
