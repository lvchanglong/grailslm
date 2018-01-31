package common

import com.artofsolving.jodconverter.DocumentConverter
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter

/**
 * Created by lvchanglong on 2017/2/14.
 * 需要服务启动
 */
class OpenOffice extends ServerSocket {

    String root = null //安装根目录
    String soffice = null //可执行文件
    Thread thread = null //监听服务线程

    /**
     * 是否是linux操作系统
     */
    static def isLinux() {
        return CommonHelper.isLinux()
    }

    /**
     * 操作系统名称 linux / windows
     */
    static String getOSName() {
        return this.isLinux()?"linux":"windows"
    }

    /**
     * 服务初始化（包括验证服务是否开启，若关闭则自动开启）
     */
    OpenOffice(String root = "") {
        if(!root) {//设置默认值
            def properties = new Properties()
            properties.load(this.class.getResourceAsStream("/config_${OpenOffice.getOSName()}.properties"))
            this.root = properties.getProperty("OpenOffice")
        } else {
            this.root = root
        }

        if(OpenOffice.isLinux()) {//Linux
            this.soffice = this.root + "/program/soffice"
        } else {//Windows
            this.soffice = this.root + "/program/soffice.exe"
        }

        if(this.isGoing()) {//服务是否正在运行
//            println "监听服务已启动"
        } else {
            println "监听服务未启动， 启动服务"
            this.start() //启动监听服务
        }
    }

    /**
     * 服务是否可用
     * true可用 false不可用
     * @return
     */
    def isGoing() {
//        println isBind("0.0.0.0", 8100)
//        println isBind(InetAddress.getLocalHost().getHostName(), 8100)
//        println isBind(InetAddress.getLocalHost().getHostAddress(), 8100)
        return isBind("0.0.0.0", 8100) || isBind(InetAddress.getLocalHost().getHostName(), 8100) || isBind(InetAddress.getLocalHost().getHostAddress(), 8100)
    }

    /**
     * 端口号是否被绑定
     * @param hostname
     * @return
     */
    boolean isBind(String hostname = "0.0.0.0", int port = 8100) {
        Socket socket = new Socket()
        try {
            //println hostname
            socket.bind(new InetSocketAddress(hostname, port))
            socket.close()
            return false
        } catch (Exception e) {
            //e.printStackTrace()
        }
        return true //端口被占用说明服务正在运行
    }

    /**
     * 启动监听服务 soffice + ' -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard'
     */
    void start() {
        try {
            thread = new Thread(new Runnable() {
                @Override
                void run() {
//                    println "监听服务8100运行中..."

                    List<String> command = new ArrayList<String>()
                    command.add(soffice)
                    command.add("-headless")
                    command.add("-accept=\"socket,host=127.0.0.1,port=8100;urp;\"")
                    command.add("-nofirststartwizard")
                    if(OpenOffice.isLinux()) {//Linux
                        command.add("&")
                    }
                    ProcessBuilder processBuilder= new ProcessBuilder(command)
                    processBuilder.redirectErrorStream(true)
                    Process process = processBuilder.start()

                    //Process process = Runtime.getRuntime().exec("${this.soffice} -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard")

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))
                    String line = null;
                    while(null != (line = bufferedReader.readLine())) {
                        //println "."
                    }

                    println "监听服务永远不会到达这里！！！"
                    process.waitFor()
                    bufferedReader.close()
                    process.destroy()
                }
            })
            thread.start()
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    /**
     * 转化为pdf
     * @param inputFile
     * @return
     */
    File convert2pdf(File inputFile = new File("F:\\2pdf", "中期检查.doc")) {
        if(inputFile.exists()) {
            String fileName = inputFile.getName()
            if(fileName.toLowerCase().endsWith(".pdf")) {
                return inputFile //无需转换
            }
            int endIndex = fileName.lastIndexOf(".")
            String name = fileName.substring(0, endIndex)
            File outputFile = new File(inputFile.getParent(), "${name}.pdf")
            try {
                this.convert(inputFile, outputFile)
            } catch (Exception e) {
                e.printStackTrace()
                return null
            }
            return outputFile
        }
        return null
    }

    /**
     * 文件格式转化
     * @param inputFile
     * @param outputFile
     * @return
     */
    void convert(File inputFile = new File("F:\\2pdf", "中期检查.doc"), File outputFile = new File("F:\\2pdf", "中期检查.pdf")) {
        // connect to an OpenOffice.org instance running on port 8100
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100)
        connection.connect()

        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection)
        converter.convert(inputFile, outputFile)

        // close the connection
        connection.disconnect()
    }

    static main(args) {
        def properties = new Properties()
        properties.load(this.class.getResourceAsStream("/config_windows.properties"))
        def openOffice = new OpenOffice(properties.getProperty("OpenOffice"))
        def pdf = openOffice.convert2pdf(new File("D:\\open\\123456.docx"))
        println pdf.getPath()
    }

}
