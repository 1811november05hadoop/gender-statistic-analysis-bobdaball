package com.revature.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.revature.map.HealthExpendituresMapper;
import com.revature.reduce.HealthStatsReducer;

public class HealthExpendituresTest {
	/*
	 * Declare harnesses that let you test a mapper, a reducer, and
	 * a mapper and a reducer working together.
	 */
	private MapDriver<LongWritable, Text, Text, Text> mapDriver;
	private ReduceDriver<Text, Text, Text, Text> reduceDriver;
	private MapReduceDriver<LongWritable, Text, Text, Text, Text, Text> mapReduceDriver;
	String swedenHealth = "\"Sweden\",\"SWE\",\"Health expenditure per capita, PPP (constant 2011 international $)\",\"SH.XPD.PCAP.PP.KD\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"1745.09564282\",\"1859.55854074\",\"1884.67655027\",\"1981.5100498\",\"2130.40351776\",\"2290.37911039\",\"2506.82702371\",\"2706.65103263\",\"2842.92584387\",\"2960.40614879\",\"2969.48950487\",\"3207.97406876\",\"3442.83172764\",\"3670.79444695\",\"3740.80816178\",\"3761.53364216\",\"4886.52023768\",\"5007.48076242\",\"5177.37484235\",\"5218.86073424\",\"\",\"\",";
	/*
	 * Set up the test. This method will be called before every test.
	 */
	@Before
	public void setUp() {

		/*
		 * Set up the mapper test harness.
		 */
		HealthExpendituresMapper mapper = new HealthExpendituresMapper();
		mapDriver = new MapDriver<LongWritable, Text, Text, Text>();
		mapDriver.setMapper(mapper);

		/*
		 * Set up the reducer test harness.
		 */
		HealthStatsReducer reducer = new HealthStatsReducer();
		reduceDriver = new ReduceDriver<Text, Text, Text, Text>();
		reduceDriver.setReducer(reducer);

		/*
		 * Set up the mapper/reducer test harness.
		 */
		mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, Text, Text, Text>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
	}

	/*
	 * Test the mapper.
	 */
	@Test
	public void testMapper() {

		/*
		 * For this test, the mapper's input will be "1 cat cat dog" 
		 */
		mapDriver.withInput(new LongWritable(1), new Text(swedenHealth));

		/*
		 * The expected output is "cat 1", "cat 1", and "dog 1".
		 */
		mapDriver.withOutput(new Text("Sweden"), new Text("1745.09564282 5218.86073424"));
		/*
		 * Run the test.
		 */
		mapDriver.runTest();
	}

	/*
	 * Test the reducer.
	 */
	@Test
	public void testReducer() {

		List<Text> values = new ArrayList<Text>();
		values.add(new Text("1745.09564282 5218.86073424"));

		/*
		 * For this test, the reducer's input will be "cat 1 1".
		 */
		reduceDriver.withInput(new Text("Sweden"), values);

		/*
		 * The expected output is "cat 2"
		 */
		reduceDriver.withOutput(new Text("(Sweden)"), new Text("| in 2011$ values PPP, 1995 health cost: 1745.1| 2014 health cost: 5218.86| cost increase (%): 199.06%\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));

		/*
		 * Run the test.
		 */
		reduceDriver.runTest();
	}

	/*
	 * Test the mapper and reducer working together.
	 */
	@Test
	public void testMapReduce() {

		/*
		 * For this test, the mapper's input will be "1 cat cat dog" 
		 */
		mapReduceDriver.withInput(new LongWritable(1), new Text(swedenHealth));

		/*
		 * The expected output (from the reducer) is "cat 2", "dog 1". 
		 */
		mapReduceDriver.addOutput(new Text("(Sweden)"), new Text("| in 2011$ values PPP, 1995 health cost: 1745.1| 2014 health cost: 5218.86| cost increase (%): 199.06%\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));

		/*
		 * Run the test.
		 */
		mapReduceDriver.runTest();
	}
}
