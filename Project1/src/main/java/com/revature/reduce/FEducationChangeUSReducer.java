package com.revature.reduce;

import java.io.IOException;
import java.util.HashMap;

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

		
		/**
		 * First 2 characters in string indicate the order key:value was entered in Mapper,
		 * Thus, the method takes the substring of said characters to sort it in an array.
		 * The start value and end values are taken to measure % changes.
		 */
		int arrSize = 0;
		HashMap<Integer, Double> indexMap = new HashMap<Integer, Double>();
		
		for (Text value : values) {
			String valStr = value.toString();
			int valStrSize = valStr.length();
			int indexValue = Integer.parseInt(valStr.substring(0,2).trim());
			double doubleValue = Double.parseDouble(valStr.substring(2, valStrSize));
			indexMap.put(indexValue, doubleValue);
			arrSize++;
		}
		double[] dubArray = new double[arrSize];
		
		for (int i = 0; i < arrSize; i++) {
			dubArray[i] = indexMap.get(i);
		}
		
		double prevVal = dubArray[0];
		double newVal;

		double changeSums = 0;
		double percentChange;
		for (int i = 1; i < arrSize; i++) {
			newVal = dubArray[i];
			percentChange = ((newVal - prevVal) / prevVal) * (double) 100;
			changeSums += percentChange;
			prevVal = newVal;
		}

		changeSums /= (double) (arrSize - 1);
		changeSums = Math.round(changeSums * (double)100) / (double) 100;
		context.write(key, new Text("Average increase: " + Double.toString(changeSums) + "%\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));
	}
}
