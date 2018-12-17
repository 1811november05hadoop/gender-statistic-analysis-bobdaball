package com.revature.test;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import com.revature.map.FEmploymentMapper;

public class FEmploymentChangeTest {
	private MapDriver<LongWritable, Text, Text, Text> mapDriver;
	String afghanInput = "\"Afghanistan\",\"AFG\",\"Employment to population ratio, 15+, female (%) (modeled ILO estimate)\",\"SL.EMP.TOTL.SP.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"16.0520000457764\",\"15.9750003814697\",\"15.7880001068115\",\"15.6540002822876\",\"15.6859998703003\",\"15.3699998855591\",\"15.1820001602173\",\"15.0469999313354\",\"14.9689998626709\",\"14.7880001068115\",\"14.960000038147\",\"15.3400001525879\",\"13.7880001068115\",\"14.0319995880127\",\"14.289999961853\",\"14.1940002441406\",\"14.1420001983643\",\"14.1619997024536\",\"14.2810001373291\",\"14.5129995346069\",\"14.9200000762939\",\"15.4130001068115\",\"15.9630002975464\",\"16.6259994506836\",\"16.7220001220703\",\"16.8190002441406\",";
	String hkgFEmployment = "\"Hong Kong SAR, China\",\"HKG\",\"Employment to population ratio, 15+, female (%) (modeled ILO estimate)\",\"SL.EMP.TOTL.SP.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"47.3639984130859\",\"46.0289993286133\",\"45.4220008850098\",\"45.9609985351563\",\"46.0929985046387\",\"46.5499992370605\",\"46.851001739502\",\"46.3349990844727\",\"46.0929985046387\",\"47.0610008239746\",\"47.8479995727539\",\"48.0880012512207\",\"47.8079986572266\",\"48.4930000305176\",\"49.3800010681152\",\"50.048999786377\",\"50.7109985351563\",\"51.1020011901855\",\"50.5900001525879\",\"50.0120010375977\",\"51.0299987792969\",\"51.3839988708496\",\"52.0900001525879\",\"52.1360015869141\",\"51.7809982299805\",\"51.351001739502\",";
	@Before
	public void setUp() {
		FEmploymentMapper mapper = new FEmploymentMapper();
		mapDriver = new MapDriver<LongWritable, Text, Text, Text>();
		mapDriver.setMapper(mapper);	
	}

	@Test
	public void testMapper() {
		mapDriver.withInput(new LongWritable(1), new Text(afghanInput));
		/*
		 * The expected output is "cat 1", "cat 1", and "dog 1".
		 */
		mapDriver.withOutput(new Text("(Afghanistan)"), new Text("Female Employment % change(2000-2016): 13.73%\n~~~~~~~~~~~~~~~~~~~~~~~"));

		/*
		 * Run the test.
		 */
		mapDriver.runTest();
	}
}
