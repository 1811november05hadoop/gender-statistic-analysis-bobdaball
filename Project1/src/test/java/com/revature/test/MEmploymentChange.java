//package com.revature.test;
//
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mrunit.mapreduce.MapDriver;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.revature.map.MEmploymentMapper;
//
//public class MEmploymentChange {
//	 /*
//	   * Declare harnesses that let you test a mapper, a reducer, and
//	   * a mapper and a reducer working together.
//	   */
//	private MapDriver<LongWritable, Text, Text, Text> mapDriver;
//
//	  /*
//	   * Set up the test. This method will be called before every test.
//	   */
//	  @Before
//	  public void setUp() {
//
//	    /*
//	     * Set up the mapper test harness.
//	     */
//	    MEmploymentMapper mapper = new MEmploymentMapper();
//	    mapDriver = new MapDriver<LongWritable, Text, Text, Text>();
//	    mapDriver.setMapper(mapper);
//	  }
//
//	  /*
//	   * Test the mapper.
//	   */
//	  @Test
//	  public void testMapper() {
//
//	    /*
//	     * For this test, the mapper's input will be "1 cat cat dog" 
//	     */
//	    mapDriver.withInput(new LongWritable(1), new Text("cat cat dog"));
//
//	    /*
//	     * The expected output is "cat 1", "cat 1", and "dog 1".
//	     */
//	    mapDriver.withOutput(new Text("cat"), new IntWritable(1));
//	    mapDriver.withOutput(new Text("cat"), new IntWritable(1));
//	    mapDriver.withOutput(new Text("dog"), new IntWritable(1));
//
//	    /*
//	     * Run the test.
//	     */
//	    mapDriver.runTest();
//	  }
//
//	  /*
//	   * Test the reducer.
//	   */
//}
