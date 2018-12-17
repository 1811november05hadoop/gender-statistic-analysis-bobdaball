package com.revature.reduce;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


/**
 * Calculates Educational Attainment % change from 2000 to present, (or at least the closest years it can get)
 * It guarantees the order of insertion from the prepended 2 digit string of the value.
 * This gets converted to an integer, which is then used to hashmap and iterate through said
 * Hashmap on to an array, to compare values between years.
 */
public class FEducationChangeUSReducer extends Reducer<Text, DoubleWritable, Text, Text>{

	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> values,
			Reducer<Text, DoubleWritable, Text, Text>.Context context)
					throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		int arrSize = 0;

		double totalInc = 0;

		for (DoubleWritable value: values) {
			totalInc += value.get();
			arrSize++;
		}
		totalInc = totalInc / (double) arrSize;

		totalInc = Math.round(totalInc * (double)100) / (double) 100;

		context.write(key, new Text("Average increase: " + Double.toString(totalInc) + "%\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));
	}
}
