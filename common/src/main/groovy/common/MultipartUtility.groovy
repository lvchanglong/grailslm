package common

import java.text.MessageFormat
import java.util.logging.Logger

public class MultipartUtility {
    private static final Logger log = Logger.getLogger(MultipartUtility.class.getName());

    private static final String CRLF = "\r\n";
    private static final String CHARSET = "UTF-8";

    private static final int CONNECT_TIMEOUT = 50000;
    private static final int READ_TIMEOUT = 50000;

    private final HttpURLConnection connection;
    private final OutputStream outputStream;
    private final PrintWriter writer;
    private final String boundary;

    // for log formatting only
    private final URL url;
    private final long start;

    public MultipartUtility(final URL url) throws IOException {
        start = System.currentTimeMillis();
        this.url = url;

        boundary = "---------------------------" + System.currentTimeMillis();

        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept-Charset", CHARSET);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        outputStream = connection.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, CHARSET), true);
    }

    public void addFormField(final String name, final String value) {
        writer.append("--").append(boundary).append(CRLF)
                .append("Content-Disposition: form-data; name=\"").append(name)
                .append("\"").append(CRLF)
                .append("Content-Type: text/plain; charset=").append(CHARSET)
                .append(CRLF).append(CRLF).append(value).append(CRLF);
    }

    public void addFilePart(final String fieldName, final File uploadFile)
            throws IOException {
        final String fileName = uploadFile.getName();
        writer.append("--").append(boundary).append(CRLF)
                .append("Content-Disposition: form-data; name=\"")
                .append(fieldName).append("\"; filename=\"").append(fileName)
                .append("\"").append(CRLF).append("Content-Type: ")
                .append(URLConnection.guessContentTypeFromName(fileName)).append(CRLF)
                .append("Content-Transfer-Encoding: binary").append(CRLF)
                .append(CRLF);

        writer.flush();
        outputStream.flush();
        final FileInputStream inputStream = new FileInputStream(uploadFile);

        final byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();

        writer.append(CRLF);
    }

    public void addHeaderField(String name, String value) {
        writer.append(name).append(": ").append(value).append(CRLF);
    }

    public byte[] finish() throws IOException {
        writer.append(CRLF).append("--").append(boundary).append("--").append(CRLF);
        writer.close();

        final int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            throw new IOException(MessageFormat.format("{0} failed with HTTP status: {1}", url, status));
        }

        final InputStream is = connection.getInputStream()
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        final byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            bytes.write(buffer, 0, bytesRead);
        }
        def byteArray = bytes.toByteArray();
//        log.log(INFO, format("{0} took {4} ms", url, (currentTimeMillis() - start)));
        return byteArray;
    }

    public void disconnect() {
        connection.disconnect();
        writer.flush();
        outputStream.flush();
        writer.close();
        outputStream.close();
        System.gc();
    }

    static main(args) {
//        MultipartUtility multipartUtility = null
//        try {
//            multipartUtility = new MultipartUtility(new URL(urlUpload))
//            multipartUtility.addFormField("access_token", accessToken)
//            multipartUtility.addFormField("fileid", fileId)
//            multipartUtility.addFormField("filenm", name)
//            multipartUtility.addFormField("size", size)
//            multipartUtility.addFilePart("file", file)
//            multipartUtility.finish()
//        } catch (Exception e) {
//            //e.printStackTrace()
//        } finally {
//            multipartUtility.disconnect()
//        }
    }

}