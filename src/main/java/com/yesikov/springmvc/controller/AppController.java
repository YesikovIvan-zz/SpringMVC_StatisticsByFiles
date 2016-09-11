package com.yesikov.springmvc.controller;

import com.yesikov.springmvc.model.FileBucket;
import com.yesikov.springmvc.model.StatisticsFile;
import com.yesikov.springmvc.service.StatisticsFileService;
import com.yesikov.springmvc.util.CalculatingStatistics;
import com.yesikov.springmvc.util.FileHelper;
import com.yesikov.springmvc.util.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    StatisticsFileService statisticsFileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @RequestMapping(value = { "/", "/files-list" }, method = RequestMethod.GET)
    public String getFilesList(ModelMap model) {
        List<StatisticsFile> statisticsFiles = statisticsFileService.findAllStatisticsFiles();
        model.addAttribute("statisticsFiles", statisticsFiles);
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "filesList";
    }

    @RequestMapping(value = { "/get-statistics-{id}" }, method = RequestMethod.GET)
    public String getStatisticsFile(@PathVariable int id, ModelMap model) {
        StatisticsFile statisticsFiles = statisticsFileService.findById(id);
        model.addAttribute("statisticsFiles", statisticsFiles);
        return "statistics";
    }

    @RequestMapping(value = { "/upload-text" }, method = RequestMethod.POST)
    public String uploadText(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {
        List<StatisticsFile> statisticsFiles = statisticsFileService.findAllStatisticsFiles();
        model.addAttribute("statisticsFiles", statisticsFiles);
        if (result.hasErrors()) {
            return "filesList";
        }
        if(fileBucket.getName().equals("")){
            FieldError nameError =new FieldError("fileBucket","name",messageSource.getMessage("notEmpty.fileBucket.name", new String[]{""}, Locale.getDefault()));
            result.addError(nameError);
            return "filesList";
        }
        if(fileBucket.getContent().equals("")){
            FieldError contentError =new FieldError("fileBucket","content",messageSource.getMessage("notEmpty.fileBucket.content", new String[]{""}, Locale.getDefault()));
            result.addError(contentError);
            return "filesList";
        }
        statisticsFileService.saveStatisticsFile(CalculatingStatistics.getStatisticsFile(fileBucket));
        return "redirect:/files-list";
    }

    @RequestMapping(value = { "/upload-file" }, method = RequestMethod.POST)
    public String uploadFileBucket(@Valid FileBucket fileBucket, BindingResult result) throws IOException{
        if (result.hasErrors()) {
            return "uploadFile";
        }
        statisticsFileService.saveStatisticsFile(CalculatingStatistics.getStatisticsFile(fileBucket));
        return "redirect:/files-list";
    }

    @RequestMapping(value = { "/upload-file" }, method = RequestMethod.GET)
    public String newFileBucket(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "uploadFile";
    }

    @RequestMapping(value = "/get-filtered-files", method = RequestMethod.GET, produces = { "text/html; charset=UTF-8" })
    String getFilteredFiles(@RequestParam String condition, ModelMap model) {
        List<StatisticsFile> statisticsFiles = FileHelper.getFilesByCondition(condition, statisticsFileService.findAllStatisticsFiles());
        model.addAttribute("statisticsFiles", statisticsFiles);
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "filesList";
    }

}
