package com.revature.test;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import com.revature.map.MEmploymentMapper;

public class MEmploymentChange {
	 /*
	   * Declare harnesses that let you test a mapper, a reducer, and
	   * a mapper and a reducer working together.
	   */
	private MapDriver<LongWritable, Text, Text, Text> mapDriver;
	String afghanStr = "\"Afghanistan\",\"AFG\",\"Employment to population ratio, 15+, male (%) (modeled ILO estimate)\",\"SL.EMP.TOTL.SP.MA.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"83.3199996948242\",\"83.4339981079102\",\"83.1510009765625\",\"83.1660003662109\",\"83.75\",\"83.3600006103516\",\"83.4100036621094\",\"83.4589996337891\",\"83.4899978637695\",\"83.1900024414063\",\"83.2279968261719\",\"83.8050003051758\",\"77.713996887207\",\"77.7620010375977\",\"77.8130035400391\",\"77.8580017089844\",\"77.8779983520508\",\"77.8639984130859\",\"77.8089981079102\",\"77.7160034179688\",\"77.6490020751953\",\"77.6050033569336\",\"77.4079971313477\",\"77.2649993896484\",\"77.1809997558594\",\"77.1370010375977\",";
	  /*
	   * Set up the test. This method will be called before every test.
	   */
	  @Before
	  public void setUp() {

	    /*
	     * Set up the mapper test harness.
	     */
	    MEmploymentMapper mapper = new MEmploymentMapper();
	    mapDriver = new MapDriver<LongWritable, Text, Text, Text>();
	    mapDriver.setMapper(mapper);
	  }

	  /*
	   * Test the mapper.
	   */
	  @Test
	  public void testMapper() {
		  String expected = "Employment % change: -7.28%\n~~~~~~~~~~~~~~~~~~~~~~~";
	    /*
	     * For this test, the mapper's input will be "1 cat cat dog" 
	     */
	    mapDriver.withInput(new LongWritable(1), new Text(afghanStr));

	    /*
	     * The expected output is "cat 1", "cat 1", and "dog 1".
	     */
	    mapDriver.withOutput(new Text("(Afghanistan)"), new Text(expected));

	    /*
	     * Run the test.
	     */
	    mapDriver.runTest();
	  }
}
