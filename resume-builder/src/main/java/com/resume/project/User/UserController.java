package com.resume.project.User;

import com.resume.project.Config.GenericDataDto;
import com.resume.project.Config.UrlConstant;
import com.resume.project.pdfGenerator.ResumeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(UrlConstant.BASEURL + UrlConstant.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public GenericDataDto save(@RequestBody User user){
        GenericDataDto genericDataDto = new GenericDataDto();
        genericDataDto.setResponseCode(HttpStatus.OK.value());
        genericDataDto.setResponseMessage("Data Successfully updated");
        genericDataDto.setData(userService.saveEntity(user));
        return genericDataDto;
    }

    @PutMapping("/update")
    public GenericDataDto update(@RequestBody User user){
        GenericDataDto genericDataDto = new GenericDataDto();
        genericDataDto.setResponseCode(HttpStatus.OK.value());
        genericDataDto.setResponseMessage("Data Successfully updated");
        if(null != user.getId()){
            user = userService.updateEntity(user);
            if (null != user){
                genericDataDto.setData(user);
            }
            else {
                genericDataDto.setResponseMessage("Data not found");
                genericDataDto.setResponseCode(HttpStatus.NOT_FOUND.value());
            }
        }
        else{
            genericDataDto.setResponseMessage("Data not found");
            genericDataDto.setResponseCode(HttpStatus.NOT_FOUND.value());
        }
        return genericDataDto;
    }

    @GetMapping("/")
    public GenericDataDto getAll(){
        GenericDataDto genericDataDto = new GenericDataDto();
        genericDataDto.setResponseCode(HttpStatus.OK.value());
        genericDataDto.setResponseMessage("Data Successfully retrieved");
        genericDataDto.setDatalist(Arrays.asList(userService.getUsers().toArray()));
        return genericDataDto;
    }

    @GetMapping("/generatePdf")
    public GenericDataDto generatePdf(){
        GenericDataDto genericDataDto = new GenericDataDto();
        genericDataDto.setResponseCode(HttpStatus.OK.value());
        genericDataDto.setResponseMessage("Pdf Successfully Created");
        ResumeGenerator.generateResume();
        return genericDataDto;
    }

    @GetMapping("/{userId}")
    public GenericDataDto getById(@PathVariable Long userId){
        GenericDataDto genericDataDto = new GenericDataDto();
        genericDataDto.setResponseCode(HttpStatus.OK.value());
        genericDataDto.setResponseMessage("Data Successfully retrieved");
        User user = userService.getById(userId);
        if(null != user){
            genericDataDto.setData(user);
        }
        else{
            genericDataDto.setResponseCode(HttpStatus.NOT_FOUND.value());
            genericDataDto.setResponseMessage("Data Not Found");
        }
        return genericDataDto;
    }

    @DeleteMapping("/{userId}")
    public GenericDataDto delete(@PathVariable Long userId){
        GenericDataDto genericDataDto = new GenericDataDto();
        User user =userService.deleteEntity(userId);
        if (null != user){
            genericDataDto.setResponseMessage("Data Successfully Deleted");
            genericDataDto.setResponseCode(HttpStatus.OK.value());
        }
        else{
            genericDataDto.setResponseMessage("User not found");
            genericDataDto.setResponseCode(HttpStatus.NOT_FOUND.value());
        }
        return genericDataDto;
    }
}
