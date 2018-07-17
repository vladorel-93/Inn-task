
public class HttpClientClass {
    public static void main(String[] args){

        RequestMaker requestMaker = new RequestMaker();
        String inn = requestMaker.readInnAndPrepandCurrentDate();
        requestMaker.makeRequest(inn);
    }
}
