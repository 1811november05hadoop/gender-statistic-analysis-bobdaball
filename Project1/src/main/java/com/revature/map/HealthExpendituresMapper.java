package com.revature.map;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HealthExpendituresMapper extends
Mapper<LongWritable, Text, Text, Text> {

	String criterion = "Health expenditure per capita, PPP";
	/**
	 * It filters out countries / regions that do not have health expenditure values at 19995 and 2014.
	 * Upon filtering it, it passes to the reducer. 
	 */
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
					throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		/*
		 * rate of increase from 1990 to 2014
		 * Health expenditure per capita, PPP (constant 2011 international $)
		 * 34 = 90
		 * 44 = 2000
		 * 54 = 2010
		 * 58 = 2014
		 */
		String line = value.toString();
		double initialAmount = -1.00;
		double newAmount = -1.00;

		
		if (line.contains(criterion)) {
			String[] columns = line.substring(1,  line.length() - 2).split("\",\"", -1);
			if (columns[39].length() > 0) {
				initialAmount = Double.parseDouble(columns[39]);
			}
			if (columns[58].length() > 0) {
				newAmount = Double.parseDouble(columns[58]);
			}
			if (initialAmount > -1 && newAmount > -1) {
				String str = columns[0];				
				context.write(new Text(str), new Text(initialAmount +" "+ newAmount));
			}
		}
	}
}
