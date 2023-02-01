package webserver;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.IOUtils;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line = "http start";
            String htmlFile = "";

            while (!"".equals(line)) {
                line = br.readLine();
                if (line == null) {
                    continue;
                }
                System.out.println(line);

                // 요구사항 1. index.html 응답하기
                if (line.contains("HTTP/1.1")) {
                    String[] tokens = line.split(" ");
                    htmlFile = tokens[1];
                    System.out.println("tokens = " + tokens);
                }

                // 요구사항 2. GET 방식으로 회원가입하기
                if ( line.contains("GET") && line.contains("/user/create?") ) {
                    String getUrl = line.split("\\?")[1];
                    User user = getUser(getUrl);
                    System.out.println("user = " + user);
                }

                // 요구사항 3. POST 방식으로 회원가입하기
                if ( line.contains("Content-Length") ) {
                    int contentLength = Integer.parseInt( line.split(" ")[1] );
                    System.out.println("contentLength = " + contentLength);
                    String postUrl = IOUtils.readData(br, contentLength);
                    System.out.println("postUrl = " + postUrl);
                    User user = getUser(postUrl);
                    System.out.println("user = " + user);
                }


            }


            DataOutputStream dos = new DataOutputStream(out);
            byte[] body = Files.readAllBytes( new File("./webapp" + htmlFile).toPath() );
            //byte[] body = "Hello World".getBytes();
            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


    private User getUser(String line) {
        String tokens[] = line.split("&| ");
        String userId = "";
        String password = "";
        String name = "";
        String email = "";

        for (String s : tokens) {
            if (s.contains("=")) {
                String[] mapToken = s.split("=");
                if (mapToken[0].equals("userId"))
                    userId = mapToken[1];
                if (mapToken[0].equals("password"))
                    password = mapToken[1];
                if (mapToken[0].equals("name"))
                    name = mapToken[1];
                if (mapToken[0].equals("email"))
                    email = mapToken[1];
            } else {
                continue;
            }
        }

        User user = new User(userId, password, name, email);
        return user;
    }
}
