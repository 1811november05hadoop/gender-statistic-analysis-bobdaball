package com.revature.reduce;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HealthStatsReducer extends
Reducer<Text, Text, Text, Text> {

	/**
	 * It calculates the percentage change between 1995 value and 2014.
	 * If it's greater than the change that occurred in the US, AND ends up being
	 * more than 1k$, it gets outputted.
	 */
	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException,
			InterruptedException {
		// TODO Auto-generated method stub
		for (Text value : values) {
			String[] healthValues = value.toString().split(" ");
			double newAmount = Double.parseDouble(healthValues[1]);
			double initialAmount = Double.parseDouble(healthValues[0]);
			double rateRise;
			double usRate = 148.18;
			
			rateRise = ((newAmount - initialAmount) / initialAmount) * 100;
			rateRise = Math.round(rateRise * (double) 100) / (double) 100;
			
			initialAmount = Math.round(initialAmount * (double) 100) / (double) 100;
			newAmount = Math.round(newAmount * (double) 100) / (double) 100;
			if (rateRise > usRate && (newAmount - initialAmount) > 1000.00) {
				context.write(new Text("(" + key + ")"), new Text("| in 2011$ values PPP, 1995 health cost: " + initialAmount + "| 2014 health cost: " + newAmount + "| cost increase (%): " + rateRise + "%\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));
			}
		}
	}
		
}
