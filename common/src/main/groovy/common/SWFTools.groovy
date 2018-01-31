package common

/**
 * Created by lvchanglong on 2017/2/15.
 */
class SWFTools {

    String root = null //安装根目录
    String pdf2swf = null //可执行文件
    String png2swf = null //可执行文件

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
     *
     * @param root ${servletContext.getRealPath("/")}tools/SWFTools-Home/${SWFTools.isLinux()?"linux/swftools":"windows/SWFTools"}
     */
    SWFTools(String root = "") {
        if(!root) {//设置默认值
            def properties = new Properties()
            properties.load(this.class.getResourceAsStream("/config_${SWFTools.getOSName()}.properties"))
            this.root = properties.getProperty("SWFTools")
        } else {
            this.root = root
        }

        if(SWFTools.isLinux()) {//Linux
            this.pdf2swf = this.root + "/bin/pdf2swf"
            this.png2swf = this.root + "/bin/png2swf"
        } else {//Windows
            this.pdf2swf = this.root + "/pdf2swf.exe"
            this.png2swf = this.root + "/png2swf.exe"
        }
    }

    /**
     * 获取文件类型(小写)
     * @param fileName
     * @return
     */
    private static def getFileType(String fileName) {
        def splits = fileName.split(/\./)
        return splits[splits.length - 1].toLowerCase()
    }

    /**
     * 转化为swf
     * @param inputFile
     * @param index "_%" 每页为1个swf(_1, _2, _3...)
     * @return
     */
    File[] convert2swf(File inputFile = new File("F:\\2pdf", "中期检查.pdf"), String index = "_%") {
        if(inputFile.exists()) {
            String fileName = inputFile.getName()
            int endIndex = fileName.lastIndexOf(".")
            String name = fileName.substring(0, endIndex)

            String parentName = inputFile.getParentFile().getName()
            File outputFile = FileHelper.getFile(inputFile.getParent() + System.getProperty("file.separator") + parentName, "${name}${index}.swf")
            try {
                this.convert(inputFile, outputFile)
            } catch (Exception e) {
                e.printStackTrace()
                return null
            }
            return outputFile.getParentFile().listFiles()
        }
        return null
    }

    /**
     * 文件格式转化 pdf2swf + " ${inputFile.getPath()} -o ${outputFile.getPath()} -f -T 9 -t -s disablelinks -s languagedir=${""}
     * @param inputFile
     * @param outputFile
     * @return
     */
    void convert(File inputFile = new File("F:\\2pdf", "中期检查.pdf"), File outputFile = new File("F:\\2pdf", "中期检查.swf")) {
        try {
            List<String> command = new ArrayList<String>()

            switch(this.getFileType(inputFile.getName())) {
                case "png":
                    command.add(png2swf)
                    command.add("-o")
                    command.add(outputFile.getPath())
                    command.add(inputFile.getPath())
                    command.add("-T 9")
                    break
                default:
                    command.add(pdf2swf)
                    command.add(inputFile.getPath())
                    command.add("-o")
                    command.add(outputFile.getPath())
                    command.add("-f")
                    command.add("-T 9")
                    command.add("-t")
                    command.add("-s")
                    command.add("disablelinks")
                    command.add("-s")
                    command.add("languagedir=" + "D:\\fonts")
            }
            ProcessBuilder processBuilder= new ProcessBuilder(command)
            processBuilder.redirectErrorStream(true)
            Process process = processBuilder.start()
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))
            String line = null;
            while(null != (line = bufferedReader.readLine())) {
                //println "."
            }
            process.waitFor()
            bufferedReader.close()
            process.destroy()
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    static main(args) {
        def properties = new Properties()
        properties.load(this.class.getResourceAsStream("/config_windows.properties"))
//        println properties.getProperty("SWFTools")
//        return

        def tools = new SWFTools(properties.getProperty("SWFTools"))
        tools.convert(FileHelper.getFile("D:\\1234576", "c3f4de3426257640cb95abc0538f0de5.pdf"), FileHelper.getFile("D:\\1234576", "c3f4de3426257640cb95abc0538f0de5.pdf.swf"))
        def swfs = tools.convert2swf(new File("D:\\123456.pdf"))
        swfs.each {swf->
            println swf.getAbsolutePath()
        }
        tools.convert(new File("D:\\123456.pdf"), new File("D:\\eTailers ISS GmbH - Prüfbericht 2016.swf"))
    }

}
