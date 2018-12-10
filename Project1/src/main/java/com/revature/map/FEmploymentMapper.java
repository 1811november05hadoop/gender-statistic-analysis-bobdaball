package com.revature.map;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FEmploymentMapper extends Mapper<LongWritable, Text, Text, Text> {

	boolean isCountry = false;

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
					throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String criterion = "Employment to population ratio, 15+, female (%) (modeled ILO estimate)";
		String line = value.toString();
		double[] numDiff = new double[2];

		if (!isCountry) {
			if (line.contains("Afghanistan")) {
				isCountry = true;
			} 
		} 

		if (isCountry) {
			if (line.contains(criterion)) {
				String[] columns = line.split("\",\"", -1);

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
				context.write(new Text(columns[0]), new Text(Double.toString(numDiff[0]) + " " + Double.toString(numDiff[1])));
			}
		}


	}
}
