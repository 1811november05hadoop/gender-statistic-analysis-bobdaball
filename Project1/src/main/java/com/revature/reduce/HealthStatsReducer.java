package com.revature.reduce;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HealthStatsReducer extends
		Reducer<Text, DoubleWritable, Text, DoubleWritable> {
//	3788.30984177 - '95
//	9402.53697133 -'14
//	1.48198
	
	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> values,
			Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		double usRate = 148.00;
		
		for (DoubleWritable value : values) {
			if (value.get() > usRate) {
				context.write(new Text(key.toString() + ": "), value);			}
		}
	}
}
