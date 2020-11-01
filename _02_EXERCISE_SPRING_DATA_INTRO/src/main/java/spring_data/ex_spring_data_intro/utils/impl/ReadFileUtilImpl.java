package spring_data.ex_spring_data_intro.utils.impl;

import org.springframework.stereotype.Component;
import spring_data.ex_spring_data_intro.utils.ReadFileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadFileUtilImpl implements ReadFileUtil {

    @Override
    public String[] read(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();
        String line;
        while((line = bf.readLine()) != null) {
            if(!"".equals(line)) {
                lines.add(line);
            }
        }
        return lines.toArray(String[]::new);
    }
}
