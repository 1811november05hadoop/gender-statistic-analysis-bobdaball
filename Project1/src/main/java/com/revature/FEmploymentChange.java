package com.revature;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.revature.map.FEmploymentMapper;

public class FEmploymentChange {

		public static void main(String[] args) throws IOException, Exception, InterruptedException {
			if (args.length != 2) {
				System.out.printf(
						"Usage: FEmploymentChange <input dir> <output dir>\n");
				System.exit(-1);
			}
			
			Job job = new Job();
			
			job.setJarByClass(FEmploymentChange.class);
			
			job.setJobName("Female Employment Change");
			
			FileInputFormat.setInputPaths(job,  new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			job.setMapperClass(FEmploymentMapper.class);
			job.setNumReduceTasks(0);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			
			boolean success = job.waitForCompletion(true);
			System.exit(success ? 0 : 1);
		}
}
