package com.revature.reduce;

//import org.apache.hadoop.io.DoubleWritable;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FEducation30LessReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		IntWritable years30Less = null;
		
		for (IntWritable value : values) {
			years30Less = value;
		}
		
		context.write(key, years30Less);
	}
}
