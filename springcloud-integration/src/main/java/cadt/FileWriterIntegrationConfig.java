package cadt;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.file.DefaultFileNameGenerator;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.GenericTransformer;

@Configuration
@EnableBinding(MySink.class)
@EnableConfigurationProperties(FileSinkProperties.class)
public class FileWriterIntegrationConfig {

	//@Bean
	//@Transformer(inputChannel="textInChannel", outputChannel="fileWriterChannel")
	//public GenericTransformer<String, String> upperCaseTransformer() {
	//	return text -> text.toUpperCase();
	//}
	
	@Bean
	@ServiceActivator(inputChannel=MySink.input)
	public FileWritingMessageHandler fileWriter(FileNameGenerator fileNameGenerator, FileSinkProperties properties) {
		FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("c:\\"));
		handler.setExpectReply(false);
		handler.setFileExistsMode(FileExistsMode.APPEND);
		handler.setAppendNewLine(true);
		handler.setFileNameGenerator(fileNameGenerator);
		return handler;
	}
	
	  @Bean
	  public FileNameGenerator fileNameGenerator(FileSinkProperties properties) {
	    DefaultFileNameGenerator fileNameGenerator = new DefaultFileNameGenerator();
	    fileNameGenerator.setExpression(properties.getNameExpression());
	    return fileNameGenerator;
	  }
	
	  //@Bean
	  //public FileNameGenerator fileNameGenerator(FileSink1 properties) {
	  //  DefaultFileNameGenerator fileNameGenerator = new DefaultFileNameGenerator();
	  //  fileNameGenerator.setExpression(properties.getNameExpression());
	  //  return fileNameGenerator;
	  //}
	  
	  //@Bean
	  //public FileNameGenerator fileNameGenerator1() {
	  //	    DefaultFileNameGenerator fileNameGenerator = new DefaultFileNameGenerator();
	  	    //the file name must be enclose with single quote 'file-name'
	  //	    fileNameGenerator.setExpression("'fileSink'");
	  //	    return fileNameGenerator;
	  //}
}
