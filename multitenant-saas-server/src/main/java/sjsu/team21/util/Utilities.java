package sjsu.team21.util;

import java.util.List;

import com.google.gson.Gson;

public class Utilities {

public static String getJsonFromList(List result) {

Gson g = new Gson();
String temp = new String(g.toJson(result)) ;
System.out.println("Before Replacing : " + temp);
temp = temp.replace("\",\"", "\":\"");
temp = temp.replace("],[", ",");
temp = temp.replace("[[", "{");
temp = temp.replace("]]", "}");

System.out.println(temp );
return temp;
}

}