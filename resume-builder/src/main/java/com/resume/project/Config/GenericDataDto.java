package com.resume.project.Config;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class GenericDataDto {
    private int responseCode;
    private String responseMessage;
    private Object data = null;
    private List<Object> datalist = new ArrayList<>();
}
