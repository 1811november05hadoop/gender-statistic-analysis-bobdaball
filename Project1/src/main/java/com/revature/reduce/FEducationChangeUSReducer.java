package com.revature.reduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


/**
 * Calculates Educational Attainment % change from 2000 to present, (or at least the closest years it can get)
 */
public class FEducationChangeUSReducer extends Reducer<Text, Text, Text, Text>{

	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context)
					throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		List<Double> strArray = new ArrayList<Double>();
		
		/**
		 * First 2 characters in string indicate the order key:value was entered in Mapper,
		 * Thus, the method takes the substring of said characters to sort it in an array.
		 * The start value and end values are taken to measure % changes.
		 */
		for (Text value : values) {
			String valStr = value.toString();
			int indexValue = Integer.parseInt(valStr.substring(0,2).trim());
			
			strArray.add(indexValue, Double.parseDouble(valStr.substring(2, value.getLength())));
		}
		
		double prevVal = strArray.get(0);
		double newVal;
		int arrSize = strArray.size();
		
		double changeSums = 0;
		double percentChange;
		for (int i = 1; i < arrSize; i++) {
			newVal = strArray.get(i);
			percentChange = ((newVal - prevVal) / prevVal);
			changeSums += percentChange;
			prevVal = newVal;
		}


	
//		double percentChange = ((finalVal - initialVal) / initialVal) * (double) 100;
//		percentChange = Math.round(percentChange * (double) 100) / (double) 100;		
		context.write(key, new Text("Average increase: " + Double.toString(changeSums / (double) arrSize) + "%\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));
	}
}
