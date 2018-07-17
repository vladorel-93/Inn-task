import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RequestMaker {

    private static String url = "http://npchk.nalog.ru/ajax.html";

    public void makeRequest(String inn) {

        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(inn);

        try {
            client.executeMethod(method);
            System.out.println("Признак состояния:" + method.getResponseBodyAsString());
        } catch (IOException z) {
            System.out.println("fail to make request");
        }
        finally {
            method.releaseConnection();
        }
    }


    public String readInnAndPrepandCurrentDate(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inn = "";
        while (!(inn.length() == 10 || inn.length() == 12)) {
            try {
                System.out.print("Введите 10- или 12-значный инн:");
                inn = reader.readLine();
            } catch (IOException x) {
                System.out.println("Ошибка ввода данных");
            }
        }
        LocalDate currentDate = LocalDate.now();
        String formattedDate =  currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return url + "?inn=" + inn + "&dt=" + formattedDate;
    }
}
