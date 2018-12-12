package com.revature.reduce;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HealthStatsReducer extends
Reducer<Text, Text, Text, Text> {

	protected void reduce(Text key, java.lang.Iterable<Text> values, org.apache.hadoop.mapreduce.Reducer<Text,DoubleWritable,Text,Text>.Context context) throws IOException ,InterruptedException {
		for (Text value : values) {
			context.write(new Text(key + " (1995 val, 2014 val, Expenditure % increase): "), value);
		}
	};
		
}
