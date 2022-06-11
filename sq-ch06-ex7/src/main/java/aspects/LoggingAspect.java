package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.util.logging.Logger;

@Aspect
@Order(2)
public class LoggingAspect {

  private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

  @Around(value = "@annotation(ToLog)")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
    logger.info("Logging Aspect: Calling the intercepted method");
    Object returnedValue = joinPoint.proceed();
    logger.info("Logging Aspect: Method executed and returned " + returnedValue);
    return returnedValue;
  }

  public void setLogger(Logger logger) {
    this.logger = logger;
  }
}
