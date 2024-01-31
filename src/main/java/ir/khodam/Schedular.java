package ir.khodam;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@Service
public class Schedular {
    private static List<String> cardList = new ArrayList<>();
    private int counter = 0;
    static Map<String, Object[]> studentData = new TreeMap<>();
    private int cc = 0;


    @Scheduled(initialDelay = 1L, fixedDelay = 1_000_000_000)
    void runner() throws IOException, InterruptedException {
        var executorService = Executors.newFixedThreadPool(3);

        listReader();

//        IntStream.range(1, 3).forEach(id -> {
//            executorService.submit(() -> {
////                System.out.println(Thread.currentThread().getId() + " * " + Thread.currentThread().getPriority() + " * " + Thread.currentThread().getName());
//                var strings = cardChooser().split(",");
//                migrate(id, strings[0], strings[1], strings[2]);
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        });

//        for (int i = 0; i < 110; i++) {

        IntStream.range(0, cardList.size()).forEach(i -> {
//        for (int i = 0; i < cardList.size(); i++) {
            executorService.submit(() -> {
                try {
                    var strings = cardList.get(i).split(",");

                    migrate(strings[0], strings[1], strings[2]);

                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (Exception e) {
                    log.error("{}",e.getMessage(),e);
                    throw new RuntimeException(e);
                }

                System.out.println("Size: " + studentData.size());
                if (studentData.size() == cardList.size()) {
                    try {
                        write("", studentData);
                        cc++;
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
//           if (studentData.size() == 40) {
//               try {
//                   write(String.valueOf(cc), studentData);
//               } catch (IOException e) {
//                   throw new RuntimeException(e);
//               }
//               cc++;
//               try {
//                   TimeUnit.MILLISECONDS.sleep(5000);
//               } catch (InterruptedException e) {
//                   throw new RuntimeException(e);
//               }
//               studentData.clear();
//               System.out.println("Bye");
//               System.exit(1);
//           }
            });
        });
    }

    private void listReader() {
        try {
            var sc = new Scanner(new File("/Users/.../kesha3.csv"));
            sc.useDelimiter("\n");
            while (sc.hasNext()) {
                try {
                    cardList.add(sc.next());
                } catch (Exception e) {
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    private String cardChooser() {
        return cardList.get(counter++);
    }

//    void migrate(String rowNumber, String trackId, String postalCode) {
//        var restTemplate = new RestTemplateBuilder().build();
//        var headers = new HttpHeaders();
//        headers.setBearerAuth("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJpZGVudGljYXRvciIsInN1YiI6IkVQUG5vIiwiYWNjZXNzIjp0cnVlLCJpc3MiOiJodHRwczovL2ppYml0LmlyIiwiZXhwIjoxNjk0MzI4MDM2fQ.SCQnAjNN528xikRqHopVs2VUp1UvUw5TArsWewimxt2z59n8Pv9ShEIGx_T8H66npsIVMPD7PQI10yrsTjk7xQ");
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("X-TRACK-ID", rowNumber + "_" + trackId);
////        HttpEntity<AccountInfoRequestDTO> requestEntity = new HttpEntity(new TokenReq("Jm71mJ1Ycu", "IAAM3ILG5xMkBEXJKUVxCUA5M"), headers);
////        ResponseEntity<String> response = restTemplate.postForEntity("https://api.jibit.cloud" + "/ide/v1/tokens/generate", requestEntity, String.class);
//
//        var requestEntity = new HttpEntity<>(headers);
//
//        try {
//            System.out.println(rowNumber + "  " + trackId + "  " + postalCode);
//
//            String body = restTemplate.exchange(
////                    "https://napi.jibit.ir/ide/v1/services/identity?nationalCode=" + (nc.length() != 10 ? (nc.length() == 9 ? "0".concat(nc) : "00".concat(nc)) : nc) + "&nationalSerial=" + ns,
//                    "https://napi.jibit.ir/ide/v1/services/postal?code=" + postalCode,
//                    HttpMethod.GET,
//                    requestEntity,
//                    String.class).getBody();
////            if (body.contains("503 Service Unavailable")) {
////                write(postalCode);
////            }
//            System.out.println(body);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//        }
//    }

    void migrate(String ro, String name, String iban) {
        var restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(5)).setReadTimeout(Duration.ofSeconds(5)).build();
        var headers = new HttpHeaders();
        headers.setBearerAuth("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJpZGVudGljYXRvciIsInN1YiI6IkUxbGJHIiwiYWNjZXNzIjp0cnVlLCJpc3MiOiJodHRwczovL2ppYml0LmlyIiwiZXhwIjoxNzAwNTU3OTk1fQ.zNv4iJ0_YlcdNL7SClcY0-lOS7yezDDDv-8gA2ffPNqJ2ewu-EScV84F7pBZV5gmJsEoWWhDO4zorR06rlt5bg");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-TRACK-ID", ro);
        var requestEntity = new HttpEntity<>(headers);

        System.out.println("Calling! for " + name + "," + iban);
        try {

            String body = restTemplate.exchange(
//                    "https://napi.jibit.ir/ide/v1/ibans?value=" + nc,
                    "https://napi.jibit.ir/ide/v1/ibans?value=IR" + iban.replace(",", "").trim(),
                    HttpMethod.GET,
                    requestEntity,
                    String.class).getBody();
            System.out.println(body);
            studentData.put(ro, new Object[]{ro, name, iban, body});
        } catch (Exception e) {
            System.out.println("Putting for " + name + "," + iban);
            studentData.put(String.valueOf(ro), new Object[]{ro, name, iban, e.getMessage()});
        }
    }

    public void write(String pi, Map<String, Object[]> data) throws IOException {
        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet = workbook.createSheet(" Data ");

        // creating a row object
        XSSFRow row;

        Set<String> keyid = data.keySet();

        int rowid = 0;

        // writing the data into the sheets...

        for (String key : keyid) {

            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = data.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }

        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
        FileOutputStream out = new FileOutputStream("src/main/resources/sheet" + cc + ".xlsx");

        workbook.write(out);
        out.close();
        System.out.println("Finish");
    }
}
