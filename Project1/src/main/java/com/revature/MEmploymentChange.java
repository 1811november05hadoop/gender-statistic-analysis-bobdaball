package com.revature;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.revature.map.MEmploymentMapper;
import com.revature.reduce.EmploymentPercentReducer;

public class MEmploymentChange {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.printf(
					"Usage: MEmploymentChange <input dir> <output dir>\n");
			System.exit(-1);
		}

		Job job = new Job();


		job.setJarByClass(MEmploymentChange.class);

		job.setJobName("Male Employemnt Change");

		FileInputFormat.setInputPaths(job,  new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(MEmploymentMapper.class);
		job.setReducerClass(EmploymentPercentReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
	}
}
