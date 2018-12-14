package com.revature.test;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import com.revature.map.FEducation30LessMapper;


public class FEducation30LessTest {

	private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;

	String criterion = "Gross graduation ratio, tertiary, female (%)";
	String exampleInput = "\"Chile\",\"CHL\",\"Gross graduation ratio, tertiary, female (%)\",\"SE.TER.CMPL.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"17.36532\",\"21.87258\",\"12.64567\",\"\",\"\",\"16.68547\",\"\",\"\",\"17.39141\",\"18.2503\",\"18.80552\",\"24.19107\",\"22.18887\",\"\",\"22.66161\",\"10.89959\",\"12.6661\",\"\",\"\",";
	@Before
	public void setUp() {
		FEducation30LessMapper mapper = new FEducation30LessMapper();
		mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
		mapDriver.setMapper(mapper);
	}

	@Test
	public void returnsLatest30MinusYearMapper() {
		/*
		 * set mapper input here
		 */
		
		mapDriver.withInput(new LongWritable(1), new Text(exampleInput));
		mapDriver.withOutput(new Text("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n(Chile)  |Gross graduation ratio, tertiary, female (%)| graduation rate: 12.67% |"), new IntWritable(2012));
		mapDriver.runTest();
	}
}
