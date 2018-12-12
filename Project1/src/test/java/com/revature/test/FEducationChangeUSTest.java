package com.revature.test;

import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class FEducationChangeUSTest {
	private MapDriver<> mapDriver;
	private ReduceDriver<> reduceDriver;
	private MapReduceDriver<> mapReduceDriver;


	@Before
	public void setUp() {

	    /*
	     * Set up the mapper test harness.
	     */
	    WordMapper mapper = new WordMapper();
	    mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
	    mapDriver.setMapper(mapper);

	    /*
	     * Set up the reducer test harness.
	     */
	    SumReducer reducer = new SumReducer();
	    reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>();
	    reduceDriver.setReducer(reducer);

	    /*
	     * Set up the mapper/reducer test harness.
	     */
	    mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>();
	    mapReduceDriver.setMapper(mapper);
	    mapReduceDriver.setReducer(reducer);
	}

	@Test
	public void testMapper() {

	    /*
	     * For this test, the mapper's input will be "1 cat cat dog" 
	     */
	    mapDriver.withInput(new LongWritable(1), new Text("cat cat dog"));

	    /*
	     * The expected output is "cat 1", "cat 1", and "dog 1".
	     */
	    mapDriver.withOutput(new Text("cat"), new IntWritable(1));
	    mapDriver.withOutput(new Text("cat"), new IntWritable(1));
	    mapDriver.withOutput(new Text("dog"), new IntWritable(1));

	    /*
	     * Run the test.
	     */
	    mapDriver.runTest();
	}

	@Test
	public void testReducer() {

	    List<IntWritable> values = new ArrayList<IntWritable>();
	    values.add(new IntWritable(1));
	    values.add(new IntWritable(1));

	    /*
	     * For this test, the reducer's input will be "cat 1 1".
	     */
	    reduceDriver.withInput(new Text("cat"), values);

	    /*
	     * The expected output is "cat 2"
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
		 * For this test, the mapper's input will be "1 cat cat dog" 
		 */
		mapReduceDriver.withInput(new LongWritable(1), new Text("cat cat dog"));

		/*
		 * The expected output (from the reducer) is "cat 2", "dog 1". 
		 */
		mapReduceDriver.addOutput(new Text("cat"), new IntWritable(2));
		mapReduceDriver.addOutput(new Text("dog"), new IntWritable(1));

		/*
		 * Run the test.
		 */
		mapReduceDriver.runTest();
	}
}
