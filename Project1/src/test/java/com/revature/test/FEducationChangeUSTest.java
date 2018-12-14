//package com.revature.test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.hadoop.io.DoubleWritable;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mrunit.mapreduce.MapDriver;
//import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
//import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.revature.map.FEducationChangeUSMapper;
//import com.revature.reduce.FEducationChangeUSReducer;
//
//public class FEducationChangeUSTest {
//	private MapDriver<LongWritable, Text, Text, Text> mapDriver;
//	private ReduceDriver<Text, Text, Text, Text> reduceDriver;
//	private MapReduceDriver<LongWritable, Text, Text, Text, Text, Text> mapReduceDriver;
//
//	String entry1 = "\"United States\",\"USA\",\"Educational attainment, at least Bachelor's or equivalent, population 25+, female (%) (cumulative)\",\"SE.TER.CUAT.BA.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"31.39076\",\"32.00147\",\"32.67396\",\"\",";
//
//	String entry2 = "\"United States\",\"USA\",\"Educational attainment, at least completed lower secondary, population 25+, female (%) (cumulative)\",\"SE.SEC.CUAT.LO.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"93.01367\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"93.89355\",\"93.88051\",\"94.46581\",\"\",\"94.70972\",\"94.71217\",\"94.93398\",\"95.04402\",\"95.14301\",\"95.39159\",\"95.42985\",\"95.47838\",\"\",";
//
//	String entry3 = "\"United States\",\"USA\",\"Educational attainment, at least completed post-secondary, population 25+, female (%) (cumulative)\",\"SE.SEC.CUAT.PO.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"18.7\",\"\",\"\",\"\",\"\",\"22.2\",\"\",\"\",\"\",\"26.9\",\"28.10064\",\"28.02803\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"44.54951\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"35.37453\",\"36.00504\",\"37.52263\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",";
//
//	String entry4 = "\"United States\",\"USA\",\"Educational attainment, at least completed primary, population 25+ years, female (%) (cumulative)\",\"SE.PRM.CUAT.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"95\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"96.64463\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"98.59983\",\"98.4959\",\"98.64088\",\"\",\"98.73201\",\"98.62149\",\"98.70539\",\"98.70377\",\"98.82814\",\"98.86884\",\"98.76638\",\"98.76783\",\"\",";
//
//	String entry5 = "\"United States\",\"USA\",\"Educational attainment, at least completed short-cycle tertiary, population 25+, female (%) (cumulative)\",\"SE.TER.CUAT.ST.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"35.37453\",\"36.00504\",\"37.52263\",\"\",\"38.44067\",\"39.15297\",\"39.89922\",\"40.53132\",\"41.12231\",\"42.16468\",\"42.87372\",\"43.4329\",\"\",";
//
//	String entry6 = "\"United States\",\"USA\",\"Educational attainment, at least completed upper secondary, population 25+, female (%) (cumulative)\",\"SE.SEC.CUAT.UP.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"66.8\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"77.17914\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"85.43373\",\"85.43646\",\"86.38113\",\"\",\"87.2264\",\"87.13267\",\"87.66015\",\"88.04637\",\"88.03673\",\"88.62399\",\"88.85883\",\"88.75894\",\"\",";
//
//	String entry7 = "\"United States\",\"USA\",\"Educational attainment, at least Master's or equivalent, population 25+, female (%) (cumulative)\",\"SE.TER.CUAT.MS.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"11.20828\",\"11.61702\",\"11.98897\",\"\",";
//
//
//	@Before
//	public void setUp() {
//
//	    /*
//	     * Set up the mapper test harness.
//	     */
//	    FEducationChangeUSMapper mapper = new FEducationChangeUSMapper();
//	    mapDriver = new MapDriver<LongWritable, Text, Text, Text>();
//	    mapDriver.setMapper(mapper);
//
//	    /*
//	     * Set up the reducer test harness.
//	     */
//	    FEducationChangeUSReducer reducer = new FEducationChangeUSReducer();
//	    reduceDriver = new ReduceDriver<Text, Text, Text, Text>();
//	    reduceDriver.setReducer(reducer);
//
//	    /*
//	     * Set up the mapper/reducer test harness.
//	     */
//	    mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, Text, Text, Text>();
//	    mapReduceDriver.setMapper(mapper);
//	    mapReduceDriver.setReducer(reducer);
//	}
//
//	@Test
//	public void testMapper() {
//
//	    /*
//	     * For this test, the mapper's input will be \"1 cat cat dog\" 
//	     */
//	    mapDriver.withInput(new LongWritable(1), new Text(entry1));
//	    /*
//	     * The expected output is \"cat 1\", \"cat 1\", and \"dog 1\".
//	     */
////	    mapDriver.withOutput(new Text("\"Educational attainment, at least Bachelor's or equivalent, population 25+, female (%) (cumulative)\""), new Text("31.39076"));
//	    mapDriver.addOutput(new Text("Educational attainment, at least Bachelor's or equivalent, population 25+, female (%) (cumulative): "), new Text("0 31.39076"));
//	    mapDriver.addOutput(new Text("Educational attainment, at least Bachelor's or equivalent, population 25+, female (%) (cumulative): "), new Text("1 32.00147")); 
//	    mapDriver.addOutput(new Text("Educational attainment, at least Bachelor's or equivalent, population 25+, female (%) (cumulative): "), new Text("2 32.67396"));
//	    /*
//	     * Run the test.
//	     */
//	    mapDriver.runTest();
//	}
//
//	@Test
//	public void testReducer() {
//
//	    List<IntWritable> values = new ArrayList<IntWritable>();
//	    values.add(new IntWritable(1));
//	    values.add(new IntWritable(1));
//
//	    /*
//	     * For this test, the reducer's input will be \"cat 1 1\".
//	     */
//	    reduceDriver.withInput(new Text("cat"), values);
//
//	    /*
//	     * The expected output is \"cat 2\"
//	     */
//	    reduceDriver.withOutput(new Text("cat"), new IntWritable(2));
//
//	    /*
//	     * Run the test.
//	     */
//	    reduceDriver.runTest();
//	}
//
//	@Test
//	public void testMapReduce() {
//		/*
//		 * For this test, the mapper's input will be \"1 cat cat dog\" 
//		 */
//		mapReduceDriver.withInput(new LongWritable(1), new Text("cat cat dog"));
//
//		/*
//		 * The expected output (from the reducer) is \"cat 2\", \"dog 1\". 
//		 */
//		mapReduceDriver.addOutput(new Text("cat"), new IntWritable(2));
//		mapReduceDriver.addOutput(new Text("dog"), new IntWritable(1));
//
//		/*
//		 * Run the test.
//		 */
//		mapReduceDriver.runTest();
//	}
//}
