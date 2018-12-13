package com.revature.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.revature.map.FEducation30LessMapper;
import com.revature.reduce.FEducation30LessReducer;


public class FEducation30LessTest {

	private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;

	private ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;

	private MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

	String criterion = "Gross graduation ratio, tertiary, female (%)";
	String exampleInput = "\"Chile\",\"CHL\",\"Gross graduation ratio, tertiary, female (%)\",\"SE.TER.CMPL.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"17.36532\",\"21.87258\",\"12.64567\",\"\",\"\",\"16.68547\",\"\",\"\",\"17.39141\",\"18.2503\",\"18.80552\",\"24.19107\",\"22.18887\",\"\",\"22.66161\",\"10.89959\",\"12.6661\",\"\",\"\",";
	@Before
	public void setUp() {
		FEducation30LessMapper mapper = new FEducation30LessMapper();
		mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
		mapDriver.setMapper(mapper);

		FEducation30LessReducer reducer = new FEducation30LessReducer();
		reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>();
		reduceDriver.setReducer(reducer);

		mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
	}

	@Test
	public void testMapper() {
		/*
		 * set mapper input here
		 */
		mapDriver.withInput(new LongWritable(1), new Text(exampleInput));
		mapDriver.withOutput(new Text("Chile"), new IntWritable(2014));
		mapDriver.runTest();
	}
	
	@Test
	public void testReducer() {
		List<IntWritable> values = new ArrayList<IntWritable>();
	    values.add(new IntWritable(1));
	    values.add(new IntWritable(1));

	    /*
	     * For this test, the reducer's input will be \"cat 1 1\".
	     */
	    reduceDriver.withInput(new Text("cat"), values);

	    /*
	     * The expected output is \"cat 2\"
	     */
	    reduceDriver.withOutput(new Text("cat"), new IntWritable(2));

	    /*
	     * Run the test.
	     */
	    reduceDriver.runTest();
	}
	
	@Test
	  public void testMapReduce() {

	    /*
	     * For this test, the mapper's input will be \"1 cat cat dog\" 
	     */
	    mapReduceDriver.withInput(new LongWritable(1), new Text("cat cat dog"));

	    /*
	     * The expected output (from the reducer) is \"cat 2\", \"dog 1\". 
	     */
	    mapReduceDriver.addOutput(new Text("cat"), new IntWritable(2));
	    mapReduceDriver.addOutput(new Text("dog"), new IntWritable(1));

	    /*
	     * Run the test.
	     */
	    mapReduceDriver.runTest();
	  }
}
