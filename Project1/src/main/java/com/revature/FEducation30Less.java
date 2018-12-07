package com.revature;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.revature.map.FEmploymentMapper;

public class FEducation30Less {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.printf(
					"Usage: FEducation30Less <input dir> <output dir>\n");
			System.exit(-1);
		}
		
		Job job = new Job();
		
		job.setJarByClass(FEducation30Less.class);
		
		job.setJobName("Word Count");
		
		FileInputFormat.setInputPaths(job,  new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(FEmploymentMapper.class);
//		job.setReducerClass(FEmploymentPercentReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
	}
}
