package com.revature.model;

import java.util.Arrays;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;


public class DoubleArrayWritable extends ArrayWritable {

	public DoubleArrayWritable() {
		super(DoubleWritable.class);
	}
	
	public DoubleArrayWritable(DoubleWritable[] dubVs) {
		super(DoubleWritable.class, dubVs);
	}
	
	@Override
	public String toString() {
		return "[" + Arrays.toString(get()) + "]";
	}
}
