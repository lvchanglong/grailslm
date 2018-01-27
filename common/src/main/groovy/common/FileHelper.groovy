package common
/**
 * 文件管理
 * @author 吕常龙
 *
 */
class FileHelper {

	/**
	 * 获得文件夹引用(不存在则创建)
	 * @param strDirPath
	 */
	static File getFolder(String strDirPath) {
		def fileDir = new File(strDirPath)
		if (!fileDir.exists()) {
			fileDir.mkdirs()
		}
		return fileDir
	}

	static File getDir(String strDirPath) {
		return this.getFolder(strDirPath)
	}

	/**
	 * 获得文件引用
	 * @param strDirPath
	 * @param strFileName
	 */
	static File getFile(String strDirPath, String strFileName) {
		this.getDir(strDirPath)
		def fileTar = new File(strDirPath + System.getProperty("file.separator") + strFileName)
		return fileTar
	}

	/**
	 * 通过文件名获得文件类型
	 * @param fileName 文件名
	 */
	static String getFileType(String fileName) {
		def splits = fileName.split(/\./)
		return splits[splits.length - 1]
	}

	static File findFile(String strDirPath, String strFileName) {
		def dir = new File(strDirPath)
		if (!dir.exists()) {
			return null
		}
		File tarfile = null
		dir.listFiles().each {file->
			if (file.isDirectory()) {
				tarfile = this.findFile(file.getPath(), strFileName)
			} else {
				if (file.getName().equals(strFileName)) {
					tarfile = file
				}
			}
		}
		return tarfile
	}

	/**
	 * 删除文件或文件夹
	 * @param strPath
	 */
	static void del(String strPath) {
		 this.del(new File(strPath))
	}

	/**
	 * 删除文件或文件夹
	 * @param currentFile
	 */
	static void del(File currentFile) {
		if (true == currentFile.isDirectory()) {
			for (File file : currentFile.listFiles()) {
				del(file)
			}
		}
		if (currentFile.exists()) {
			currentFile.delete()
		}
	}

	/**
	 * 为文件或者文件夹重新命名
	 * @param strPath
	 * @param newnm 新名字
	 */
	static void renameTo(String strPath, String strNewName) {
		this.renameTo(new File(strPath), strNewName)
	}

	/**
	 * 为文件或者文件夹重新命名
	 * @param currentFile
	 * @param newnm 新名字
	 */
	static void renameTo(File currentFile, String strNewName) {
		currentFile.renameTo(new File(currentFile.getParent()+ System.getProperty("file.separator") + strNewName))
	}

	/**
	 * 获得指定文件夹下的文件列表
	 * @param strDirPath
	 * @param bIncludeDir 是否包含文件夹
	 */
	static File[] listFiles(String strDirPath, Boolean bIncludeDir) {
		def dir = this.getDir(strDirPath)

		if (bIncludeDir) {//包含文件夹
			return dir.listFiles()
		} else {
			def fileList = new ArrayList()
			dir.listFiles().each {file->
				if (file.isFile()) {
					fileList.add(file)
				}
			}
			return fileList
		}
	}

	static String[] list(String strDirPath) {
		def dir = this.getDir(strDirPath)
		return dir.list()
	}

	/**
	 * 文件拷贝
	 * @param srcFilePath 源文件
	 * @param tarFilePath 目的文件
	 */
	static void copyTo(String srcFilePath, String tarFilePath) {
		def src = new File(srcFilePath)
		def tar = new File(tarFilePath)
		this.copyTo(src, tar)
	}

	static void copyTo(File src, File tar) {
		def input = src.newDataInputStream()
		this.getDir(tar.getParent())
		def output = tar.newDataOutputStream()

		output << input

		input.close()
		output.flush()
		output.close()
	}

	static main(args) {
		FileHelper.copyTo("D:/a/1.txt", "D:/a/tra/1.txt")
	}
}
