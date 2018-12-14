package com.revature.map;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MEmploymentMapper extends Mapper<LongWritable, Text, Text, Text >{

	
	boolean isCountry = false;
	
	@Override
	protected void map(
			LongWritable key,
			Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String criterion = "Employment to population ratio, 15+, male (%) (modeled ILO estimate)";
		String line = value.toString();
		double[] numDiff = new double[2];

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
					
					if (doubleStr.length() > 0) {
						if (numDiff[0] == 0) {
							numDiff[0] = Double.parseDouble(doubleStr);
						} else {
							numDiff[1] = Double.parseDouble(doubleStr);
						}
					}
				}

				if (numDiff[1] > 0) {
					double rate = ((numDiff[1] - numDiff[0]) / numDiff[0]) * 100;
					rate = Math.round(rate * (double)100) / (double) 100;
					
					context.write(new Text("(" + columns[0] + ") Employment % change: "), new Text(Double.toString(rate) + "%\n~~~~~~~~~~~~~~~~~~~~~~~" ));
				}
			}
		}
	}
}
