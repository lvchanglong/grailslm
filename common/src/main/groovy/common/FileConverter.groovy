package common

import com.aspose.cells.HtmlSaveOptions
import com.aspose.cells.Workbook
import com.aspose.slides.Presentation
import com.aspose.slides.SaveFormat
import com.aspose.words.Document

/**
 * Created by lvchanglong on 2017/12/18.
 */
class FileConverter {

	static {
		AsposeLicense.setLicense()
	}

	static def convertTo(File srcFile, File tarFile) {
		try {
			def fileName = srcFile.getName()
			def fileType = FileHelper.getFileType(fileName).toLowerCase()
			switch (fileType) {
				case "doc":
				case "docx":
				case "dot":
				case "dotx":
					Document document = new Document(srcFile.newInputStream())
					document.removeMacros()
					document.save(tarFile.getAbsolutePath())
					break
				case "xls":
				case "xlsx":
					Workbook workbook = new Workbook(srcFile.newInputStream())
					workbook.save(tarFile.getAbsolutePath())
					break
				case "ppt":
				case "pptx":
					Presentation presentation = new Presentation(srcFile.newInputStream());
					def tarName = tarFile.getName()
					def tarType = FileHelper.getFileType(tarName).toLowerCase()
					int format = SaveFormat.Pdf
					switch (tarType) {
						case "ppt":
							format = SaveFormat.Ppt
							break
						case "pdf":
							format = SaveFormat.Pdf
							break
						case "pptx":
							format = SaveFormat.Pptx
							break
						case "tiff":
							format = SaveFormat.Tiff
							break
						case "html":
							format = SaveFormat.Html
							break
						case "swf":
							format = SaveFormat.Swf
							break
						case "xps":
							format = SaveFormat.Xps
							break
					}
					presentation.save(tarFile.getAbsolutePath(), format)
					break
				case "pdf":
					def pdfDocument = new com.aspose.pdf.Document(srcFile.newInputStream());
					def tarName = tarFile.getName()
					def tarType = FileHelper.getFileType(tarName).toLowerCase()
					int format = com.aspose.pdf.SaveFormat.Xps
					switch (tarType) {
						case "doc":
							format = com.aspose.pdf.SaveFormat.Doc
							break
						case "docx":
							format = com.aspose.pdf.SaveFormat.DocX
							break
						case "pptx":
							format = com.aspose.pdf.SaveFormat.Pptx
							break
						case "xls":
						case "xlsx":
							format = com.aspose.pdf.SaveFormat.Excel
							break
						case "xps":
							format = com.aspose.pdf.SaveFormat.Xps
							break
					}
					pdfDocument.save(new FileOutputStream(tarFile), format)
					break
				default :
//					println "未支持的格式"
					return false
			}
			return true
		} catch (Exception e) {
//			e.printStackTrace()
		}
		return false
	}

	static main(args) {
//		File fFile = new File("D:\\open", "安装与固定.dot")
//		File tFile = new File("D:\\open", "安装与固定.pdf") //docx,pdf,swf
//		FileConverter.convertTo(fFile, tFile)

//		File fFile = new File("D:\\open", "123456.pdf")
//		File tFile = new File("D:\\open", "123456.pdf.xps") //docx,pdf,swf
//		FileConverter.convertTo(fFile, tFile)

//		File fFile = new File("D:\\open", "123456.ppt")
//		File tFile = new File("D:\\open", "123456.ppt.swf") //docx,pdf,swf
//		FileConverter.convertTo(fFile, tFile)

		File serverFile = new File("D:\\bk", "新建项目&用户的方法.txt")
		File targetFile = new File("D:\\bk", "新建项目&用户的方法.txt.pdf") //docx,pdf,swf
		println FileConverter.convertTo(serverFile, targetFile)
	}

}
