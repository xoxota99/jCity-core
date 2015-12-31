package com.jcity.pipeline;

import org.apache.commons.chain.*;
import org.apache.commons.logging.*;

public abstract class LogEnabledFilter implements Filter {

	protected Log logger = LogFactory.getLog(this.getClass());
}
