package com.jcity.pipeline;

import java.lang.reflect.*;
import java.util.*;

import javax.activation.*;

import org.apache.commons.chain.*;

import com.jcity.util.*;

/**
 * (Step 1) Command responsible for loading Config from file into context.
 * 
 * @author philippd
 * 
 */
public class ContextLoader extends LogEnabledCommand {

	private String resourcePath;

	public ContextLoader(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean execute(Context ctx) throws Exception {
		Properties prop = PropertyLoader.loadProperties(resourcePath);
		for (Map.Entry<Object, Object> entry : prop.entrySet()) {
			if (entry.equals("randomSeed")) {
				System.out.println("boink.");
			}
			logger.debug("Loading config value [" + (String) entry.getKey() + " = " + entry.getValue() + "]");

			// Determine the field type and coerce from String.
			String sKey = (String) entry.getKey();
			String sVal = (String) entry.getValue();

			try {
				Field fld = CityContext.class.getDeclaredField(sKey);
				Class<?> type = fld.getType();
				if (type == String.class) {
					ctx.put(sKey, sVal);
				} else if (type == Integer.TYPE || type == Integer.class) {
					ctx.put(sKey, Integer.valueOf(sVal));
				} else if (type == Double.TYPE || type == Double.class) {
					ctx.put(sKey, Double.valueOf(sVal));
				} else if (type == Float.TYPE || type == Float.class) {
					ctx.put(sKey, Float.valueOf(sVal));
				} else if (type == Long.TYPE || type == Long.class) {
					ctx.put(sKey, Long.valueOf(sVal));
				} else if (type == Boolean.TYPE || type == Boolean.class) {
					ctx.put(sKey, Boolean.valueOf(sVal));
				} else {
					throw new UnsupportedDataTypeException("data type " + type.toString() + " is not supported for property '" + sKey + "'");
				}

			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// Do nothing.
				logger.info("No such field: '" + sKey + "'");
			}

		}

		return false;
	}

}
