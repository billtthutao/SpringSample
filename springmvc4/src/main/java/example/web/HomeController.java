package example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@RequestMapping(value="/", method=GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/", method=POST)
	//public String processRegistration(@RequestPart("profilePicture") byte[] profilePicture, Errors errors, Model model) throws IOException {
	public String processRegistration(FileBucket fileBucket, Errors errors, Model model) throws IllegalStateException, IOException {
		System.out.println("test");
        MultipartFile multipartFile = fileBucket.getFile();
		System.out.println("Bytes:"+multipartFile.getSize());
		System.out.println("Name:"+multipartFile.getName());
		
		multipartFile.transferTo(new File("/"+multipartFile.getName()));
		
		model.addAttribute("message", "Files are updated!");
		return "home";
	}
	
	
	@RequestMapping(value="/multUpload", method=GET)
	public String multUpload(Model model) {
        MultiFileBucket filesModel = new MultiFileBucket();
        model.addAttribute("multiFileBucket", filesModel);
		return "multUpload";
	}
	
	@RequestMapping(value="/multUpload", method=POST)
	//public String processRegistration(@RequestPart("profilePicture") byte[] profilePicture, Errors errors, Model model) throws IOException {
	public String processRegistration1(MultiFileBucket multiFileBucket, Errors errors, Model model) throws IllegalStateException, IOException {
		System.out.println("test1");
		
		for(FileBucket fileBucket:multiFileBucket.getFiles()) {
			MultipartFile multipartFile = fileBucket.getFile();
			System.out.println("Bytes:"+multipartFile.getSize());
			System.out.println("Name:"+multipartFile.getName());
		
			multipartFile.transferTo(new File("/"+multipartFile.getName()));
		}
		model.addAttribute("message", "Files are updated!");
		return "multUpload";
	}
}
