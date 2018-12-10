package com.revature.reduce;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FEducationChangeUSReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{

	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> values,
			Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context)
					throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		double previous = -5000.00;
		double change = -5000.00;

		for (DoubleWritable value : values) {
			if (previous == -5000.00) {
				previous = value.get();
			} else {
				change = (value.get() - previous) / previous * 100;
				previous = value.get();
			}
		}

		if (previous != -5000.00 && change != -5000.00) {
			context.write(key, new DoubleWritable(Math.round(change * (double)100) / (double) 100));	
		}
	}
}
