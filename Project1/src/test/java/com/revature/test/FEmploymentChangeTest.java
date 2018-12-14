//package com.revature.test;
//
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mrunit.mapreduce.MapDriver;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.revature.map.FEmploymentMapper;
//
//public class FEmploymentChangeTest {
//	private MapDriver<LongWritable, Text, Text, Text> mapDriver;
//	String afghanInput = "\"Afghanistan\",\"AFG\",\"Employment to population ratio, ages 15-24, female (%) (modeled ILO estimate)\",\"SL.EMP.1524.SP.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"15.4689998626709\",\"15.4790000915527\",\"15.2530002593994\",\"15.1709995269775\",\"15.423999786377\",\"15.0170001983643\",\"14.8699998855591\",\"14.7720003128052\",\"14.7229995727539\",\"14.1230001449585\",\"14.3649997711182\",\"14.9460000991821\",\"11.8660001754761\",\"12.0349998474121\",\"12.1820001602173\",\"12.0270004272461\",\"11.9029998779297\",\"11.8439998626709\",\"11.8859996795654\",\"12.0520000457764\",\"12.4969997406006\",\"12.9779996871948\",\"13.4490003585815\",\"13.9359998703003\",\"13.9479999542236\",\"13.9890003204346\",";
//	String hkgFEmployment = "\"Hong Kong SAR, China\",\"HKG\",\"Employment to population ratio, 15+, female (%) (modeled ILO estimate)\",\"SL.EMP.TOTL.SP.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"47.3639984130859\",\"46.0289993286133\",\"45.4220008850098\",\"45.9609985351563\",\"46.0929985046387\",\"46.5499992370605\",\"46.851001739502\",\"46.3349990844727\",\"46.0929985046387\",\"47.0610008239746\",\"47.8479995727539\",\"48.0880012512207\",\"47.8079986572266\",\"48.4930000305176\",\"49.3800010681152\",\"50.048999786377\",\"50.7109985351563\",\"51.1020011901855\",\"50.5900001525879\",\"50.0120010375977\",\"51.0299987792969\",\"51.3839988708496\",\"52.0900001525879\",\"52.1360015869141\",\"51.7809982299805\",\"51.351001739502\",";
//	@Before
//	public void setUp() {
//		FEmploymentMapper mapper = new FEmploymentMapper();
//		mapDriver = new MapDriver<LongWritable, Text, Text, Text>();
//		mapDriver.setMapper(mapper);	
//	}
//
//	@Test
//	public void testMapper() {
//		mapDriver.withInput(new LongWritable(1), new Text(afghanInput));
////		mapDriver.withInput(new LongWritable(2), new Text(hkgFEmployment));
//
//		/*
//		 * The expected output is "cat 1", "cat 1", and "dog 1".
//		 */
//		mapDriver.withOutput(new Text("Hong Kong SAR, China"), new Text("14.50"));
//
//		/*
//		 * Run the test.
//		 */
//		mapDriver.runTest();
//	}
//}
