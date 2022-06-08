package aes256.szyfrowanie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WriteFile {
    private static final String BUSINESS_ID_TO_UPDATE_LIST_FILE = "C:\\Czarek\\ADA\\PR-3955\\TIBCO\\response\\notifySimCardChanged_businessIdToUpdate_list.txt";



    public  void saveNotifyPartnerProductStateToFile(String businessId) throws FileNotFoundException, UnsupportedEncodingException {
        //String businessId = "43242344";
        String success = "success";

        PrintWriter writer = new PrintWriter(BUSINESS_ID_TO_UPDATE_LIST_FILE, "UTF-8");

        writer.write(String.format("%s\r\n", businessId + "/" + success));
        writer.close();

    }

    public  List<String> read() throws IOException {
        List<String> list = new ArrayList<>();
        InputStream fStream = new FileInputStream(BUSINESS_ID_TO_UPDATE_LIST_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
        String line;
        while ((line = br.readLine()) != null)   {
            list.add(line);
        }
        fStream.close();
        return list;

    }
}
