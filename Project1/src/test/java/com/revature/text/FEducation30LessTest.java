//package com.revature.text;
//
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mrunit.mapreduce.MapDriver;
//import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
//import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
//import org.junit.Before;
//
//import com.revature.map.FEducation30LessMapper;
//import com.revature.reduce.FEducation30LessReducer;
//
//
//public class FEducation30LessTest {
//
//	private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
//
//	private ReduceDriver<Text, IntWritable, Text, Text> reduceDriver;
//
//	private MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, Text> mapReduceDriver;
//
//	@Before
//	public void setUp() {
//		FEducation30LessMapper mapper = new FEducation30LessMapper();
//		mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
//		mapDriver.setMapper(mapper);
//
//		FEducation30LessReducer reducer = new FEducation30LessReducer();
//		reduceDriver = new ReduceDriver<Text, IntWritable, Text, Text>();
//		reduceDriver.setReducer(reducer);
//
//		mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, Text>();
//		mapReduceDriver.setMapper(mapper);
//		mapReduceDriver.setReducer(reducer);
//	}
//
//		
//}
