package com.jcity.pipeline;

import org.apache.commons.chain.*;
import org.apache.commons.logging.*;

public abstract class LogEnabledCommand implements Command{

	protected Log logger = LogFactory.getLog(this.getClass());
}
