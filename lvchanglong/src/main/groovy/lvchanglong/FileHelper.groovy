package lvchanglong

class FileHelper {
    /**
     * 通过文件名获得文件类型
     * @param fileName 文件名
     */
    static String getFileType(String fileName) {
        def splits = fileName.split(/\./)
        return splits[splits.length - 1]
    }

    /**
     * 强制获得文件夹
     * @param path 文件夹路径
     */
    static File getFolder(String path) {
        File folder = new File(path)
        if(false == folder.exists()) {
            folder.mkdirs()
        }
        return folder
    }

    /**
     * 强制获得文件
     * @param path 文件路径
     */
    static File getFile(String path) {
        File file = new File(path)
        this.getFolder(file.getParent())//确保文件夹存在
        return file
    }

    static main(args) {
        println "文件类型：" + this.getFileType("fileName.txt")
    }
}
