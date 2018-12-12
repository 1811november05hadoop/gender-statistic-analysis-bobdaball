package com.revature.reduce;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EmploymentPercentReducer extends Reducer<Text, Text, Text, Text> {
	
	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		for (Text value: values) {
			String[] numbers = value.toString().split(" ");
			double numOne = Double.parseDouble(numbers[0]);
			double numTwo = Double.parseDouble(numbers[1]);
			
			double rate = ((numTwo - numOne) / numOne) * 100;
			rate = Math.round(rate * (double)100) / (double) 100;
			
			context.write(key, new Text(Double.toString(rate)));
		}
	}
}
