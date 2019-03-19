package com.hrovina.onlinestore.controllers.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static List<Integer> decodeIntList(String strList){
        return Arrays.asList(strList.split(","))
                        .stream().map(x -> Integer.parseInt(x))
                        .collect(Collectors.toList());
    }

    public static String decodeParam(String str){
        try{
            return URLDecoder.decode(str, "UTF-8");
        }catch (UnsupportedEncodingException e){
            return "";
        }

    }
}
