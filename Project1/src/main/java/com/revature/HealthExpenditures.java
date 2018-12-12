package com.revature;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.revature.map.HealthExpendituresMapper;
import com.revature.reduce.HealthStatsReducer;

public class HealthExpenditures {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		if (args.length != 2) {
			System.out.printf(
					"Usage: HealthExpenditures <input dir> <output dir>\n");
			System.exit(-1);
		}
/*
 * Health expenditure per capita, PPP (constant 2011 international $)
 * Health expenditure, public (% of GDP)
 * 
 * Outstanding loan for health or emergencies, female(% age 15+)
 * Outstanding loan for health or emergencies, male (% age 15+)
 */
		Job job = new Job();

		job.setJarByClass(HealthExpenditures.class);

		job.setJobName("Health Expenditures");

		FileInputFormat.setInputPaths(job,  new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(HealthExpendituresMapper.class);
		job.setReducerClass(HealthStatsReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);

		boolean success = job.waitForCompletion(true);
		System.exit(success ? 0 : 1);
	}
}