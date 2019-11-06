package cadt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.expression.Expression;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.AssertTrue;
import java.io.File;

@ConfigurationProperties("file")
@Validated
public class FileSink1 {

  
  private String name = "fileSink";

  /**
   * The expression to evaluate for the name of the target file.
   */
  private String nameExpression;

  /**
   * The suffix to append to file name.
   */
  private String suffix = "";

  public String getNameExpression() {
    return (nameExpression != null)
        ? nameExpression + " + '" + getSuffix() + "'"
        : "'" + name + getSuffix() + "'";
  }

  public String getSuffix() {
    String suffixWithDotIfNecessary = "";
    if (StringUtils.hasText(suffix)) {
      suffixWithDotIfNecessary = suffix.startsWith(".") ? suffix : "." + suffix;
    }
    return suffixWithDotIfNecessary;
  }

}

